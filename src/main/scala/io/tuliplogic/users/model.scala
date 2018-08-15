package io.tuliplogic.users
import io.tuliplogic.commons.model.Id

/**
 * 
 * event-sourcing-zio - 16/08/2018
 * Created with ♥ in Amsterdam
 */
object model {
  type UserId = Id[User]

  sealed trait UserStatus
  object UserStatus {
    case object Active extends UserStatus
    case object Deleted extends UserStatus
  }
  case class User(
    id: UserId,
    username: String,
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    status: UserStatus
  )
}
