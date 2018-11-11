defmodule HelloWorld.App do
  use Application

  def start(_type, _args) do
    port = Application.get_env(:hello_world, :cowboy_port, 4000)

    children = [
      {Plug.Cowboy, scheme: :http, plug: HelloWorld.Server, options: [port: port]}
    ]

    Supervisor.start_link(children, strategy: :one_for_one)
  end
end