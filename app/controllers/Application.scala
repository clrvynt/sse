package controllers

import play.api._
import play.api.mvc._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.concurrent.Akka
import play.api.Play.current
import models._

import play.api.libs.EventSource
import play.api.libs.iteratee._

class Application extends Controller {

  val (out, channel) = Concurrent.broadcast[String]
  val listener = Akka.system.actorSelection("akka://application/user/listener")
  listener ! Start(channel)

  def dashboard = Action {
    Ok(views.html.dashboard())
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  def createNewUser = Action.async {
    listener ! UserRegistration
    Future.successful(Ok(""))
  }

  def sendMessage = Action.async {
    listener ! NewMessage
    Future.successful(Ok(""))
  }
  def stream = Action { implicit req =>
        Ok.feed(out &> EventSource()).as("text/event-stream")
  }
}

object Application extends Application
