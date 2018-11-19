package com.github.kzmake.akka.http

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.duration.Duration
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.util.{ Failure, Success }

object HelloWorldServer extends App with HelloWorldRoutes {

  implicit val system: ActorSystem = ActorSystem("helloWorldHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val helloWorldActor: ActorRef = system.actorOf(HelloWorldActor.props, "helloWorldActor")

  lazy val routes: Route = helloWorldRoutes

  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandleAsync(Route.asyncHandler(routes), "0.0.0.0", 4000, parallelism = 16)

  serverBinding.onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}
