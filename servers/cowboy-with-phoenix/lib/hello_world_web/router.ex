defmodule HelloWorldWeb.Router do
  use HelloWorldWeb, :router

  pipeline :api do
    plug :accepts, ["text"]
  end

  scope "/", HelloWorldWeb do
    pipe_through :api

    get "/", HelloWorldController, :index
  end
end
