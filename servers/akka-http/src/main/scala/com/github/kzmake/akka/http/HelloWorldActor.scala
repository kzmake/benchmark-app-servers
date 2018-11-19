package com.github.kzmake.akka.http

import akka.actor.{ Actor, Props }
import akka.http.scaladsl.model.HttpCharsets.`UTF-8`
import akka.http.scaladsl.model.{ HttpEntity, HttpResponse, MediaType }

object HelloWorldActor {

  case object PlainText

  def props: Props = Props[HelloWorldActor]
}

class HelloWorldActor extends Actor {

  import HelloWorldActor._

  val helloWorldResponse = HttpResponse(entity = HttpEntity(MediaType.customWithFixedCharset("text", "plain", `UTF-8`), "Hello world"))

  def receive: Receive = {
    case PlainText => sender ! helloWorldResponse
  }
}
