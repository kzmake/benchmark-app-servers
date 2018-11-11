defmodule HelloWorld.Server do
  use Maru.Server, otp_app: :hello_world
end

defmodule Router.HelloWorld do
  use HelloWorld.Server

  resources do
    get do
      conn
      |> put_resp_content_type("text/plain")
      |> send_resp(200, "Hello world")
    end
  end
end

defmodule HelloWorld.API do
  use HelloWorld.Server

  plug Plug.Parsers,
       pass: ["*/*"],
       json_decoder: Jason,
       parsers: [:urlencoded, :json, :multipart]

  mount Router.HelloWorld
end