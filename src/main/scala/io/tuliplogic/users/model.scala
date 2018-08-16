package io.tuliplogic.users
import io.circe.{Decoder, Encoder}
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

    implicit val encoder: Encoder[UserStatus] = Encoder.encodeString.contramap {
      case Active => "Active"
      case Deleted => "Deleted"
    }

    implicit val decoder: Decoder[UserStatus] = Decoder.decodeString.map {
      case "Active" => Active
      case "Deleted" => Deleted
    }
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
