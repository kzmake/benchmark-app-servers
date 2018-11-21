package com.github.kzmake.finch

import cats.effect.IO
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.Await
import io.finch._
import io.finch.catsEffect._

object Main extends App {

  def helloWorld: Endpoint[IO, String] = get(pathEmpty) {
    Ok("Hello world")
  }

  def service: Service[Request, Response] =
    Bootstrap
      .serve[Text.Plain](helloWorld)
      .toService

  Await.ready(Http.server.serve(":4000", service))
}