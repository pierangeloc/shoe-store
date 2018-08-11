package io.tuliplogic

import java.time.ZonedDateTime

import io.tuliplogic.common.{Id, Url, Wrapped}
import io.tuliplogic.shoes.ShoeModel

object common {
  trait Wrapped[T] { def value: T }

  case class Id[T](id: Long)
  case class Url(value: String) extends Wrapped[String]

  sealed trait Error {
    def message: String
  }

  case class UserServiceError(message: String) extends Error
  case class ShoesServiceError(message: String) extends Error
  case class MultimediaServiceError(message: String) extends Error
  case class InventoryServiceError(message: String) extends Error
  case class GeneralError(message: String) extends Error
}

object multimedia {

  case class Metadata(
      photoUrl: Url,
  )

  case class Image(
      id: Id[Image],
      petId: Id[ShoeModel],
      metadata: Metadata,
      content: Array[Byte]
  )
}

object shoes {
  type ModelId = Id[ShoeModel]

  case class Tag(id: Int, name: String)
  case class ShoeModel(
      id: Id[ShoeModel],
      name: String,
      photoUrl: List[Url],
      tags: List[Tag]
  )
}

object store {
  type Sku = Id[Item]
  type OrderId = Id[Order]

  case class ShoeSize(value: Int) extends Wrapped[Int]
  case class Item(
      sku: Sku,
      shoeModelId: Id[ShoeModel],
      size: ShoeSize,
      quantity: Int
  )

  // order is very simple, it contains 1 sku in arbitrary quantity
  case class Order(
      id: OrderId,
      sku: Sku,
      quantity: Int,
      shipDate: ZonedDateTime,
      status: String
  )
}

object users {
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
