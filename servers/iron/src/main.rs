extern crate iron;
 
use iron::prelude::*;
use iron::status;
 
fn main() {
    Iron::new(|_: &mut Request| {
        Ok(Response::with((status::Ok, "Hello world!")))
    }).http("0.0.0.0:4000").unwrap();
}

