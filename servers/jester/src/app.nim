import jester, asyncdispatch

settings:
  port = Port(4000)
  bindAddr = "0.0.0.0"

routes:
  get "/":
    resp "Hello world"

runForever()
