# はじめに

[costajob/app-servers](https://github.com/costajob/app-servers) に影響されて自分用に検証してみます

## 🔥Benchmarks

### Results

| Language                  | App Server                                                    | Req/sec             |
| :------------------------ | :------------------------------------------------------------ | ------------------: |
| [Python](#python)         | [gunicorn + meinheld](#gunicorn-meinheld)                     |        `142269.85`  |
| [Elixir](#elixir)         | [cowboy](#cowboy)                                             |         `62189.22`  |
| [Elixir](#elixir)         | [cowboy + plug](#cowboy-plug)                                 |         `48501.17`  |
| [Elixir](#elixir)         | [cowboy2 + plug](#cowboy2-plug)                               |         `33547.23`  |
| [Python](#python)         | [gunicorn + flask + meinheld](#gunicorn-flask-meinheld)       |         `21216.30`  |
| [Elixir](#elixir)         | [cowboy + phoenix](#cowboy-phoenix)                           |         `19897.84`  |
| [Python](#python)         | [bjoern + bottle](#bjoern-bottle)                             |         `17342.36`  |
| [Elixir](#elixir)         | [cowboy2 + maru](#cowboy2-maru)                               |         `15345.62`  |
| [Python](#python)         | [gunicorn](#gunicorn)                                         |         `11505.21`  |
| [Python](#python)         | [uwsgi + flask](#uwsgi-flask)                                 |          `8327.47`  |
| [Python](#python)         | [gunicorn + flask](#gunicorn-flask)                           |          `7499.01`  |
| [Python](#python)         | [flask](#flask)                                               |          `1207.28`  |

## 📝Table of Contents

* [Scope](#scope)
  * [Server](#server)
  * [Disclaimer](#disclaimer)
* [Languages](#languages)
  * [Elixir](#elixir)
  * [Python](#python)
* [Platform](#platform)
  * [App server](#app-server)
  * [Load Server](#load-server)
  * [Settings](#settings)
* [Tools](#tools)
  * [wrk](#wrk)
  * [dstat](#dstat)
  
## 👀Scope

さまざまな言語実装をリクエストの処理能力に焦点を当ててベンチマークすることが目的です。

### Server

* "Hello world" の文字列をレスポンスとして返却する

機能のみを実装し、検証を行います。

### Disclaimer

言語の宣伝や批判を行うものではありません。
言語やアプリケーションサーバー、フレームワーク毎にそれぞれ優れている点があり、パフォーマンスだけに基づいて選ぶべきではないかと思います。

## 🌐Languages

### Elixir

* Erlang/OTP 21
* Elixir 1.7.4 (compiled with Erlang/OTP 21)

### Python

* Python 3.7.1

## 🌱Platform

下記の環境を検証に利用。

### App server

* [NIFCLOUD](https://cloud.nifty.com) type: large
* Intel(R) Xeon(R) CPU E5-2697 v3 @ 2.60GHz (4 cores)
* 4 GB
* Ubuntu 18.04 LTS

### Load Server

* [NIFCLOUD](https://cloud.nifty.com) type: large16
* Intel(R) Xeon(R) CPU E5-2697 v3 @ 2.60GHz (4 cores)
* 16 GB
* Ubuntu 18.04 LTS

### Network

PrivateLan を作成し、アプリケーションサーバー と 負荷検証(wrk)サーバー 用に Network を構築します。

[App server] : 192.168.10.10 ----------- [Load server] : 192.168.10.5

### Settings

```bash
/etc/sysctl.conf
net.ipv4.ip_local_port_range = 1024 65535
net.ipv4.tcp_tw_reuse = 1
net.ipv4.ip_dynaddr = 1
net.ipv4.tcp_rfc1337 = 1
net.ipv4.tcp_fin_timeout = 10
net.ipv4.tcp_keepalive_probes = 5
net.ipv4.tcp_slow_start_after_idle = 0
net.core.somaxconn = 65535
net.ipv4.tcp_max_syn_backlog = 65535
```

```bash
/etc/security/limits.conf
*    soft nofile 65536
*    hard nofile 65536
root soft nofile 65536
root hard nofile 65536
```

```bash
/etc/pam.d/common-session
session    required     pam_limits.so
```

```bash
/etc/pam.d/common-nonsession
session    required     pam_limits.so
```

## ⚙️Tools

* [wrk](https://github.com/wg/wrk)

### wrk

負荷ツール [wrk](https://github.com/wg/wrk) で検証。 

5回計測し、 RPS がもっとも良い結果をピックアップする。

```bash
wrk -t 4 -c 100 -d30s --timeout 2000 http://0.0.0.0:9292
```

### dstat

[wrk](https://github.com/wg/wrk) による負荷検証中のモニタリング結果を取得。　　

```bash
dstat -tf -cm -C 0,1,2,3,total --noheaders 1
```

## 🚀Elixir: cowboy + plug

* [cowboy](https://github.com/ninenines/cowboy)
* [plug](https://github.com/elixir-plug/plug)

### Bootstrap

```bash
cd servers/cowboy-with-plug
MIX_ENV=prod mix do deps.get, compile
MIX_ENV=prod mix run --no-halt
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.23ms    1.96ms  46.55ms   90.02%
    Req/Sec    12.22k     1.53k   46.68k    85.68%
  1459851 requests in 30.10s, 273.14MB read
Requests/sec:  48501.17
Transfer/sec:      9.07MB
```

## 🚀Elixir: cowboy2 + plug

* [cowboy](https://github.com/ninenines/cowboy)
* [plug](https://github.com/elixir-plug/plug)

### Bootstrap

```bash
cd servers/cowboy2-with-plug
MIX_ENV=prod mix do deps.get, compile
MIX_ENV=prod mix run --no-halt
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     3.11ms    2.32ms  46.92ms   87.94%
    Req/Sec     8.45k     1.05k   33.10k    87.51%
  1009746 requests in 30.10s, 188.92MB read
Requests/sec:  33547.23
Transfer/sec:      6.28MB
```

## 🚀Elixir: cowboy

* [cowboy](https://github.com/ninenines/cowboy)

### Bootstrap

```bash
cd servers/cowboy
MIX_ENV=prod mix do deps.get, compile
MIX_ENV=prod mix run --no-halt
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.69ms    1.55ms  45.41ms   93.23%
    Req/Sec    15.66k     1.60k   50.82k    86.01%
  1871909 requests in 30.10s, 230.63MB read
Requests/sec:  62189.22
Transfer/sec:      7.66MB
```

## 🚀Elixir: cowboy + phoenix

### Bootstrap

```bash
cd servers/cowboy-with-phoenix
MIX_ENV=prod mix do deps.get, compile
PORT=4000 MIX_ENV=prod elixir --detached -S mix phx.server
```

### Rps result

* [cowboy](https://github.com/ninenines/cowboy)
* [phoenix](https://github.com/phoenixframework/phoenix)

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.14ms    2.15ms  59.25ms   99.23%
    Req/Sec     5.00k   325.16     5.42k    92.08%
  596999 requests in 30.00s, 111.70MB read
Requests/sec:  19897.84
Transfer/sec:      3.72MB
```

## 🚀Elixir: cowboy2 + maru

* [cowboy](https://github.com/ninenines/cowboy)
* [maru](https://github.com/elixir-maru/maru)

### Bootstrap

```bash
cd servers/cowboy2-with-maru
MIX_ENV=prod mix do deps.get, compile
MIX_ENV=prod mix run --no-halt
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.54ms    2.66ms  42.51ms   73.36%
    Req/Sec     3.86k   341.07     7.03k    74.75%
  460403 requests in 30.00s, 86.14MB read
Requests/sec:  15345.62
Transfer/sec:      2.87MB
```

## 🚀Python: flask

* [flask](https://github.com/pallets/flask)

### Bootstrap

```bash
cd servers/flask
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    81.80ms    3.59ms 121.52ms   93.11%
    Req/Sec   303.11     43.47   460.00     56.42%
  36258 requests in 30.03s, 5.22MB read
Requests/sec:   1207.28
Transfer/sec:    178.04KB
```

## 🚀Python: uwsgi + flask

* [uwsgi](https://github.com/unbit/uwsgi)
* [flask](https://github.com/pallets/flask)

### Bootstrap

```bash
cd servers/uwsgi-with-flask
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.42ms    2.80ms  41.85ms   79.96%
    Req/Sec     2.10k   411.37     2.60k    70.38%
  250659 requests in 30.10s, 18.17MB read
  Socket errors: connect 0, read 250625, write 0, timeout 0
Requests/sec:   8327.47
Transfer/sec:    618.05KB
```

## 🚀Python: gunicorn + flask

* [gunicorn](https://github.com/benoitc/gunicorn)
* [flask](https://github.com/pallets/flask)

### Bootstrap

```bash
cd servers/gunicorn-with-flask
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    12.79ms    1.91ms  55.56ms   97.01%
    Req/Sec     1.89k   158.63     1.99k    95.08%
  225575 requests in 30.08s, 33.77MB read
Requests/sec:   7499.01
Transfer/sec:      1.12MB
```

## 🚀Python: gunicorn + flask + meinheld

* [gunicorn](https://github.com/benoitc/gunicorn)
* [flask](https://github.com/pallets/flask)
* [meinheld](https://github.com/mopemope/meinheld)

### Bootstrap

```bash
cd servers/gunicorn-with-flask-and-meinheld
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     4.76ms    1.84ms  45.72ms   91.67%
    Req/Sec     5.34k   518.99    20.07k    96.84%
  638610 requests in 30.10s, 98.05MB read
Requests/sec:  21216.30
Transfer/sec:      3.26MB
```

## 🚀Python: bjoern + bottle

* [bjoern](https://github.com/jonashaag/bjoern)
* [bottle](https://github.com/bottlepy/bottle)

### Bootstrap

```bash
cd servers/bjoern-with-bottle
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.86ms    2.24ms 126.73ms   99.37%
    Req/Sec     4.36k   214.90     6.47k    98.08%
  520322 requests in 30.00s, 49.62MB read
Requests/sec:  17342.36
Transfer/sec:      1.65MB
```

## 🚀Python: gunicorn

* [gunicorn](https://github.com/benoitc/gunicorn)

### Bootstrap

```bash
cd servers/gunicorn
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.16ms    1.65ms  41.82ms   96.60%
    Req/Sec     2.90k   446.99     3.31k    94.90%
  346116 requests in 30.08s, 51.82MB read
Requests/sec:  11505.21
Transfer/sec:      1.72MB
```

## 🚀Python: gunicorn + meinheld

* [gunicorn](https://github.com/benoitc/gunicorn)
* [meinheld](https://github.com/mopemope/meinheld)

### Bootstrap

```bash
cd servers/gunicorn-with-meinheld
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   714.05us    0.87ms  41.18ms   99.85%
    Req/Sec    35.81k     0.97k   47.65k    93.68%
  4282263 requests in 30.10s, 657.51MB read
Requests/sec: 142269.85
Transfer/sec:     21.84MB
```