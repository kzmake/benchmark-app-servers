# app-servers


## Elixir: cowboy + phoenix

Created from `mix phx.new cowboy-with-phoenix --app hello_world --no-ecto --no-brunch`

### Bootstrap

```sh
$ MIX_ENV=prod mix do deps.get, compile, phx.digest
$ PORT=4000 MIX_ENV=prod elixir --detached -S mix phx.server
```

### Result

```sh
$ wrk -t 4 -c 100 -d30s --timeout 2000 http://127.0.0.1:4000
Running 30s test @ http://127.0.0.1:4000
  4 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.01ms    3.65ms 154.56ms   92.46%
    Req/Sec     4.32k   359.73     8.24k    91.92%
  516471 requests in 30.01s, 96.63MB read
Requests/sec:  17210.01
Transfer/sec:      3.22MB
```

