package com.github.kzmake.finatra

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class HelloWorldController extends Controller {
  get("/") { request: Request =>
    "Hello world"
  }
}
