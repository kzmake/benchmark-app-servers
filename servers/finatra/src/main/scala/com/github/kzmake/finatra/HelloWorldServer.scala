package com.github.kzmake.finatra

import com.twitter.finagle.Http.Server
import com.twitter.finagle.http.Request
import com.twitter.finagle.stack.nilStack
import com.twitter.finagle.stats.NullStatsReceiver
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.HttpResponseFilter
import com.twitter.finatra.http.routing.HttpRouter

class HelloWorldServer extends HttpServer {

  override def configureHttpServer(server: Server) = {
    server
      .withCompressionLevel(0)
      .withStatsReceiver(NullStatsReceiver)
      .withStack(nilStack)
  }

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[HttpResponseFilter[Request]]
      .add[HelloWorldController]
  }
}
