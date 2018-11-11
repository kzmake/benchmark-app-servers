def app(_, response):
        data = b"Hello world"
        response(
            "200 OK",
            [
                ("Content-Type", "text/plain"),
                ("Content-Length", str(len(data)))
            ])
        return iter([data])
