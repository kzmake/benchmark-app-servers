from django.http import HttpResponse


def hello(request):
    return HttpResponse('Hello world', content_type="text/plain")
