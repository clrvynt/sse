/**
 * @author kal
 */
import play.api._
import models._
import akka.actor._
import play.api.libs.iteratee._
import play.api.libs.concurrent.Akka
import play.api.Play.current

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    class Listener extends Actor {
      var out: Option[Concurrent.Channel[String]] = None
      def receive = {
        case Start(out)       => this.out = Some(out)
        case UserRegistration => this.out.map(_.push("user"))
        case NewMessage       => this.out.map(_.push("message"))
      }
    }
    val listener = Akka.system.actorOf(Props[Listener], "listener")
  }
}