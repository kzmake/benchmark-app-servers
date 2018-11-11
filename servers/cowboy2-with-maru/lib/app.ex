defmodule HelloWorld.Application do
  use Application

  def start(_type, _args) do
    children = [
      HelloWorld.Server
    ]

    opts = [strategy: :one_for_one, name: HelloWorld.Supervisor]
    Supervisor.start_link(children, opts)
  end
end