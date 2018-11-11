from bottle import route, run, HTTPResponse

@route('/')
def app():
    body = 'Hello world'
    res = HTTPResponse(status=200, body=body)
    res.set_header('Content-Type', 'text/plain')
    return res

