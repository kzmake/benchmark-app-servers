defmodule HelloWorld do
  use Application

  def start(_type, _args) do
    dispatch_config =
      :cowboy_router.compile([
        {
          :_,
          [
            {:_, CowboyHandler, []}
          ]
        }
      ])

    :cowboy.start_http(:http, 100, [{:port, 4000}], [{:env, [{:dispatch, dispatch_config}]}])
  end
end
