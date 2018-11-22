package com.github.kzmake.http4s

import cats.effect._
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.server.blaze._


object Main extends IOApp {

  val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root =>
      Ok("Hello world")
  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(4000, "0.0.0.0")
      .withHttpApp(helloWorldService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
