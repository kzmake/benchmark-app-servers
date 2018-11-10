defmodule HelloWorldWeb.HelloWorldController do
  use HelloWorldWeb, :controller

  def index(conn, _params) do
    conn
    |> put_resp_content_type("text/plain")
    |> render("index.text")
  end
end