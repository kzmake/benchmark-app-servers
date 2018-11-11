defmodule CowboyTest do
  use ExUnit.Case
  doctest Cowboy

  test "greets the world" do
    assert Cowboy.hello() == :world
  end
end
