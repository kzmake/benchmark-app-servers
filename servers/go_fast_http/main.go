package main

import (
	"fmt"

	"github.com/valyala/fasthttp"
)

func helloWorldHandler(ctx *fasthttp.RequestCtx) {
	ctx.SetContentType("text/plain; charset=utf-8")
	ctx.SetStatusCode(fasthttp.StatusOK)
	fmt.Fprintf(ctx, "Hello world")
}

func main() {
	fasthttp.ListenAndServe("0.0.0.0:4000", helloWorldHandler)
}
