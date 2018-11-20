# はじめに

* [TechEmpower/FrameworkBenchmarks](https://github.com/TechEmpower/FrameworkBenchmarks)
* [costajob/app-servers](https://github.com/costajob/app-servers)

に影響されて自分用に検証してみます


## 🔥Benchmarks

### Results

c: connections

|        Language         |                            Frameworks                            | RPS(c=10)  | RPS(c=100)  |  RPS(c=1K)  |
| :---------------------- | :--------------------------------------------------------------- | ---------: | ----------: | ----------: |
| [Python](#python)       | [japronto](#python-japronto)                                     | `35192.51` | `182209.20` | `313111.38` |
| [Rust](#rust)           | [hyper](#rust-hyper)                                             | `31478.74` | `181978.18` | `250753.83` |
| [Nim](#nim)             | [jester](#nim-jester)                                            | `37875.16` | `180562.05` | `267158.10` |
| [Golang](#golang)       | [fasthttp](#go-fasthttp)                                         | `32896.92` | `178742.38` | `272827.53` |
| [Scala](#scala)         | [colossus](#scala-colossus)                                      | `30223.88` | `153630.81` | `173208.76` |
| [Python](#python)       | [gunicorn + meinheld](#python-gunicorn--meinheld)                |            | `142269.85` |             |
| [Golang](#golang)       | [net/http](#go-nethttp)                                          |            | `112160.14` |             |
| [Golang](#golang)       | [echo](#go-echo)                                                 |            | `107365.95` |             |
| [Golang](#golang)       | [gin](#go-gin)                                                   |            | `102053.41` |             |
| [Scala](#scala)         | [Finatra](#scala-finatra)                                        |            | `101039.92` |             |
| [Rust](#rust)           | [iron](#rust-iron)                                               |            |  `79042.21` |             |
| [Elixir](#elixir)       | [cowboy](#elixir-cowboy)                                         | `21982.09` |  `62189.22` |  `58050.46` |
| [Scala](#scala)         | [akka-http](#scala-akka-http)                                    | `14829.31` |  `60694.92` |  `65393.73` |
| [Scala](#scala)         | [Play Framework 2.x + netty](#scala-play-framework-2x--netty)    | `21157.73` |  `54774.22` |  `59055.88` |
| [Rust](#rust)           | [rocket](#rust-rocket)                                           |      `nan` |  `50183.59` |       `nan` |
| [Elixir](#elixir)       | [cowboy + plug](#elixir-cowboy--plug)                            | `20930.40` |  `48501.17` |  `42336.23` |
| [Elixir](#elixir)       | [cowboy2 + plug](#elixir-cowboy2--plug)                          | `17279.92` |  `33547.23` |  `30558.31` |
| [Ballerina](#ballerina) | [Ballerina](#ballerina-pure)                                     | `13055.05` |  `26570.28` |  `32383.85` |
| [Python](#python)       | [gunicorn + flask + meinheld](#python-gunicorn--flask--meinheld) |            |  `21216.30` |             |
| [Elixir](#elixir)       | [cowboy + phoenix](#elixir-cowboy--phoenix)                      |            |  `19897.84` |             |
| [Python](#python)       | [bjoern + bottle](#python-bjoern--bottle)                        |            |  `17342.36` |             |
| [Elixir](#elixir)       | [cowboy2 + maru](#elixir-cowboy2--maru)                          |            |  `15345.62` |             |
| [Python](#python)       | [gunicorn](#python-gunicorn)                                     |            |  `11505.21` |             |
| [Python](#python)       | [uwsgi + flask](#python-uwsgi--flask)                            |            |   `8327.47` |             |
| [Python](#python)       | [gunicorn + flask](#python-gunicorn--flask)                      |            |   `7499.01` |             |
| [Ruby](#ruby)           | [Ruby on Rails 5.x + unicorn](#ruby-rails-5x--unicorn)           |            |   `3748.68` |             |
| [Python](#python)       | [bottle](#python-bottle)                                         |            |   `2318.08` |             |
| [Ruby](#ruby)           | [Ruby on Rails 5.x + puma](#ruby-rails-5x--puma)                 |            |   `2134.67` |             |
| [Python](#python)       | [flask](#python-flask)                                           |            |   `1207.28` |             |

## 📝Table of Contents

* [Scope](#scope)
  * [Server](#server)
  * [Disclaimer](#disclaimer)
* [Languages](#languages)
  * [Elixir](#elixir)
  * [Python](#python)
  * [Nim](#nim)
  * [Golang](#golang)
  * [Rust](#rust)
  * [Scala](#scala)
  * [Ruby](#ruby)
  * [Ballerina](#ballerina)
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
言語やアプリケーションサーバー、フレームワーク毎にそれぞれ優れている点があり、パフォーマンスだけに基づいて選ぶべきではないと思います。

## 🌐Languages

### Elixir

* Erlang/OTP 21
* Elixir 1.7.4 (compiled with Erlang/OTP 21)

### Python

* Python 3.7.1

### Nim

* Nim 0.19.0

### Golang

* Golang 1.11.2

### Rust

* Rust 1.30.1

### Scala

* Scala 2.12.7
* sbt 1.2.6

### Ruby

* Ruby 2.5.1


### Ballerina

* Ballerina 0.983.0


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
wrk -t 4 -c 100 -d 30s --timeout 2000 http://0.0.0.0:9292
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

## 🚀Python: bottle

* [bottle](https://github.com/bottlepy/bottle)

### Bootstrap

```bash
cd servers/bottle
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   547.07ms    2.89s   26.72s    96.07%
    Req/Sec   626.82    291.02     1.74k    75.02%
  69580 requests in 30.02s, 10.02MB read
Requests/sec:   2318.08
Transfer/sec:    341.83KB
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

## 🚀Rust: iron

* [iron](https://github.com/iron/iron)

### Bootstrap

```bash
cd servers/iron
cargo run --release
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   410.29us  746.75us  40.81ms   99.88%
    Req/Sec    39.72k     7.50k   48.68k    62.96%
  2379133 requests in 30.10s, 258.66MB read
Requests/sec:  79042.21
Transfer/sec:      8.59MB
```

## 🚀Rust: rocket

* [rocket](https://rocket.rs)

### Bootstrap

```bash
cd servers/rocket
cargo run --release
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.05ms  288.93us   7.84ms   92.74%
    Req/Sec    12.61k   387.69    13.32k    70.68%
  1510538 requests in 30.10s, 207.44MB read
  Socket errors: connect 0, read 1510538, write 0, timeout 0
Requests/sec:  50183.59
Transfer/sec:      6.89MB
```

## 🚀Go: net/http

* [net/http](https://golang.org/pkg/net/http/)

### Bootstrap

```bash
cd servers/go-net-http
go build main.go
./go-net-http
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.91ms  739.03us  34.46ms   96.93%
    Req/Sec    28.25k     1.32k   58.73k    91.59%
  3375936 requests in 30.10s, 412.10MB read
Requests/sec: 112160.14
Transfer/sec:     13.69MB
```


## 🚀Go: fasthttp

* [fasthttp](https://github.com/valyala/fasthttp)

### Bootstrap

```bash
cd servers/go-fasthttp
go build main.go
./go-fasthttp
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   604.77us    1.32ms  41.20ms   99.64%
    Req/Sec    44.92k     1.78k   47.38k    96.10%
  5380177 requests in 30.10s, 749.12MB read
Requests/sec: 178742.38
Transfer/sec:     24.89MB
```

## 🚀Nim: jester
   
* [jester](https://github.com/dom96/jester)

### Boostrap

```bash
cd servers/jester
nim c -d:release --threads:on src/app.nim
./src/app
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   560.12us    0.86ms  40.91ms   99.85%
    Req/Sec    45.45k     1.82k   59.51k    83.94%
  5434775 requests in 30.10s, 751.54MB read
Requests/sec: 180562.05
Transfer/sec:     24.97MB
```

## 🚀Rust: hyper

* [hyper](https://hyper.rs)

### Bootstrap

```bash
cd servers/hyper
cargo run --release
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   551.41us    0.86ms  40.94ms   99.84%
    Req/Sec    45.84k     1.34k   65.29k    90.26%
  5477391 requests in 30.10s, 663.40MB read
Requests/sec: 181978.18
Transfer/sec:     22.04MB
```

## 🚀Scala: Play Framework 2.x + netty

* [Play Framework 2.x](https://www.playframework.com)
* [Netty](https://netty.io)

### Bootstrap

```bash
cd servers/play2-netty
sbt "start -Dhttp.port=4000"
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.22ms    2.53ms  62.23ms   92.11%
    Req/Sec    13.76k     0.96k   16.61k    82.50%
  1643383 requests in 30.00s, 200.61MB read
Requests/sec:  54774.22
Transfer/sec:      6.69MB
```


## 🚀Ruby: Rails 5.x + puma

* [Ruby on Rails](https://rubyonrails.org/)
* [puma](http://puma.io)

### Bootstrap

```bash
cd servers/rails-puma
rails server --environment production
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     9.35ms    6.72ms  99.99ms   78.65%
    Req/Sec     1.07k   658.39     2.24k    54.50%
  64117 requests in 30.04s, 16.08MB read
Requests/sec:   2134.67
Transfer/sec:    548.26KB
```

## 🚀Ruby: Rails 5.x + unicorn

* [Ruby on Rails](https://rubyonrails.org/)
* [unicorn](https://bogomips.org/unicorn/)

### Bootstrap

```bash
cd servers/rails-puma
bundle exec unicorn_rails -c config/unicorn.rb --env production
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    26.24ms  691.46us  41.35ms   87.10%
    Req/Sec     0.94k    34.01     1.01k    75.00%
  112502 requests in 30.01s, 30.15MB read
  Socket errors: connect 0, read 112502, write 0, timeout 0
Requests/sec:   3748.68
Transfer/sec:      1.00MB
```


## 🚀Ballerina: pure

* [Ballerina](https://ballerina.io)

### Bootstrap

```bash
cd servers/ballerina
ballerina build hello_service.bal
ballerina run target/hello_service.balx
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.18ms   10.93ms 111.40ms   88.80%
    Req/Sec     6.69k   523.04    10.85k    72.80%
  799758 requests in 30.10s, 108.30MB read
Requests/sec:  26570.28
Transfer/sec:      3.60MB
```

## 🚀Go: echo

* [echo](https://github.com/labstack/echo)

### Bootstrap

```bash
cd servers/echo
go build
./echo
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.95ms  763.11us  34.54ms   95.85%
    Req/Sec    27.02k     1.25k   46.54k    85.94%
  3231644 requests in 30.10s, 394.49MB read
Requests/sec: 107365.95
Transfer/sec:     13.11MB
```

## 🚀Go: gin

* [gin](https://github.com/gin-gonic/gin)

### Bootstrap

```bash
cd servers/gin
go build
./gin
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.99ms  480.82us  18.88ms   80.58%
    Req/Sec    25.66k     0.97k   32.58k    78.72%
  3071775 requests in 30.10s, 374.97MB read
Requests/sec: 102053.41
Transfer/sec:     12.46MB
```

## 🚀Scala: Finatra

* [Finatra](https://github.com/twitter/finatra)

### Bootstrap

```bash
cd servers/finatra
sbt "run -http.port=:4000"
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     1.37ms    1.72ms  38.96ms   90.10%
    Req/Sec    25.43k     1.38k   44.45k    85.77%
  3041298 requests in 30.10s, 420.56MB read
Requests/sec: 101039.92
Transfer/sec:     13.97MB
```


## 🚀Scala: akka-http

* [akka-http](https://doc.akka.io/docs/akka-http/current/index.html)

### Bootstrap

```bash
cd servers/akka-http
sbt run
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.60ms    6.70ms 270.24ms   94.87%
    Req/Sec    15.30k     2.51k   33.21k    68.67%
  1826908 requests in 30.10s, 242.18MB read
Requests/sec:  60694.92
Transfer/sec:      8.05MB
```

## 🚀Python: japronto

* [japronto](https://github.com/squeaky-pl/japronto)

### Bootstrap

```bash
cd servers/japronto
pipenv sync
pipenv run server
```

### Rps result

```bash
# wrk -t 4 -c 100 -d30s --timeout 2000 http://192.168.10.10:4000/
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   578.94us    1.21ms  41.06ms   99.73%
    Req/Sec    45.90k     1.77k   70.19k    92.09%
  5484442 requests in 30.10s, 475.96MB read
Requests/sec: 182209.20
Transfer/sec:     15.81MB
```

## 🚀Scala: colossus

* [colossus](http://tumblr.github.io/colossus/)

### Bootstrap

```bash
cd servers/colossus
sbt run
```

### Rps result

```bash
Running 30s test @ http://192.168.10.10:4000/
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     0.91ms    5.93ms 222.35ms   99.66%
    Req/Sec    38.69k     1.97k   61.96k    86.59%
  4624180 requests in 30.10s, 590.93MB read
Requests/sec: 153630.81
Transfer/sec:     19.63MB
```