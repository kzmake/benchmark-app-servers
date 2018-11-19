package com.github.kzmake.akka.http

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._

trait HelloWorldRoutes {

  // we leave these abstract, since they will be provided by the App
  implicit def system: ActorSystem

  // other dependencies that UserRoutes use
  def helloWorldActor: ActorRef

  // Required by the `ask` (?) method below
  implicit lazy val timeout: Timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  lazy val helloWorldRoutes: Route =
    path("") {
      val response = (helloWorldActor ? HelloWorldActor.PlainText).mapTo[HttpResponse]
      complete(response)
    }
}
