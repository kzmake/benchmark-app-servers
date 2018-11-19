from japronto import Application


def hello_world(request):
    return request.Response(text='Hello world')


app = Application()
app.router.add_route('/', hello_world)
app.run(host='0.0.0.0', port=4000, worker_num=4)
