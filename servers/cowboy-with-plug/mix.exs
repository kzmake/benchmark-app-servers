defmodule HelloWorld.MixProject do
  use Mix.Project

  def project do
    [
      app: :hello_world,
      version: "0.1.0",
      elixir: "~> 1.7",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  def application do
    [
      applications: [:cowboy, :plug],
      mod: {HelloWorld.App, []},
      env: [cowboy_port: 4000]
    ]
  end

  defp deps do
    [
      {:cowboy, "~> 1.1.0"},
      {:plug, "~> 1.5"},
      {:plug_cowboy, "~> 1.0"}
    ]
  end
end
