package com.github.kzmake.colossus

import akka.actor.ActorSystem
import colossus.core.IOSystem
import colossus.protocols.http.HttpServer

object HelloWorldServer extends App {

  implicit val actorSystem = ActorSystem()
  implicit val ioSystem = IOSystem()

  HttpServer.start("hello_world", 4000) { context =>
    new HelloWorldInitializer(context)
  }

}
