package models

import play.api.libs.iteratee._

case class Start(out: Concurrent.Channel[String])
case class UserRegistration()
case class NewMessage()