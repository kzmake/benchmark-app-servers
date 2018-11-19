package com.github.kzmake.colossus

import colossus.core.ServerContext
import colossus.protocols.http.HttpMethod.Get
import colossus.protocols.http.{ Http, RequestHandler }
import colossus.protocols.http.UrlParsing.{ /, Root, on }
import colossus.service.Callback
import colossus.service.GenRequestHandler.PartialHandler

class HelloWorldRequestHandler(context: ServerContext) extends RequestHandler(context) {

  override def handle: PartialHandler[Http] = {
    case request @ Get on Root => {
      Callback.successful(request.ok("Hello world"))
    }
  }

}
