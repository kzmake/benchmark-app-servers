package com.github.kzmake.finagle

import com.twitter.finagle.http.path._
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}

object Main extends App {
  val service = new Service[Request, Response] {
    def apply(request: Request): Future[Response] = {
      val response = Response(request.version, Status.Ok)
      Path(request.path) match {
        case _ => response.setContentString("Hello world")
      }

      Future.value(response)
    }
  }

  val server = Http.serve(":4000", service)
  Await.ready(server)
}
