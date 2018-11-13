class HelloWorldController < ApplicationController
  def plaintext
    response.headers['Content-Type'] = "text/plain"
    render :plain => "Hello world"
  end
end