package controllers

import javax.inject.{ Inject, Singleton }

import play.api.mvc._
import play.mvc.Http
import play.api.http.MimeTypes

@Singleton
class HelloWorldController @Inject() (cc: ControllerComponents)
  extends AbstractController(cc) {

  val plaintext = Action {
    Ok("Hello world").as(MimeTypes.TEXT)
  }
}
