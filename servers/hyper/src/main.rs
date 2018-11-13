extern crate hyper;

use hyper::{Server, Body, Response, Request};
use hyper::header::{CONTENT_TYPE, HeaderValue};
use hyper::rt::Future;
use hyper::service::service_fn_ok;


const PHRASE: &str = "Hello world";

fn hello_world(_req: Request<Body>) -> Response<Body> {
    let response = Response::builder()
        .status(200)
        .header(CONTENT_TYPE, HeaderValue::from_static("text/plain;charset=utf-8"))
        .body(Body::from(PHRASE))
        .unwrap();

    response
}

fn main() {
    let addr = ([0, 0, 0, 0], 4000).into();

    let new_svc = || {
        service_fn_ok(hello_world)
    };

    let server = Server::bind(&addr)
        .serve(new_svc)
        .map_err(|e| eprintln!("server error: {}", e));

    hyper::rt::run(server);
}
