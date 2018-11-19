package com.github.kzmake.colossus

import colossus.core.InitContext
import colossus.protocols.http.Initializer

class HelloWorldInitializer(context: InitContext) extends Initializer(context) {

  override def onConnect: RequestHandlerFactory = context => new HelloWorldRequestHandler(context)

}
