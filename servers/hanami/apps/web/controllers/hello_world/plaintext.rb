module Web::Controllers::HelloWorld
  class Plaintext
    include Web::Action

    def call(params)
      self.headers.merge!({ 'Content-Type' => 'text/plain' })
      status 200, 'Hello world'
    end
  end
end
