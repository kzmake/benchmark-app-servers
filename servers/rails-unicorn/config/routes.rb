Rails.application.routes.draw do
  get "" => "hello_world#plaintext"
end
