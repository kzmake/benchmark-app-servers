defmodule CowboyHandler do
  def init(_type, req, _opts) do
    {:ok, req, :nostate}
  end

  def handle(request, state) do
    {:ok, reply} =
      :cowboy_req.reply(
        200,
        [{"content-type", "text/plain"}],
        "Hello world",
        request
      )

    {:ok, reply, state}
  end

  def terminate(_reason, _request, _state), do: :ok
end
