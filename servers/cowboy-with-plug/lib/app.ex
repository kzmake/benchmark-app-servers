defmodule HelloWorld.App do
  use Application

  def start(_type, _args) do
    port = Application.get_env(:hello_world, :cowboy_port, 4000)

    children = [
      Plug.Adapters.Cowboy.child_spec(:http, HelloWorld.Server, [], port: port)
    ]

    opts = [strategy: :one_for_one, name: HelloWorld.App.Supervisor]
    Supervisor.start_link(children, opts)
  end
end