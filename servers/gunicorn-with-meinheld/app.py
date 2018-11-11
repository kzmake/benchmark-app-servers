def app(_, response):
    status = '200 OK'
    res = b"Hello world"
    response_headers = [('Content-type', 'text/plain'), ('Content-Length', str(len(res)))]
    response(status, response_headers)
    return [res]

