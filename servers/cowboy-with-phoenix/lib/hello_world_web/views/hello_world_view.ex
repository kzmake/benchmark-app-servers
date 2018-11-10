defmodule HelloWorldWeb.HelloWorldView do
  use HelloWorldWeb, :view

  def render("index.text", %{}) do
    "hello world"
  end
end