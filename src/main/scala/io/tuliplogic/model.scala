package io.tuliplogic

import java.time.ZonedDateTime

import io.tuliplogic.common.{Id, Url, Wrapped}
import io.tuliplogic.shoes.ShoeModel

object common {
  trait Wrapped[T]{def value: T}

  case class Id[T](id: Long)
  case class Url(value: String) extends Wrapped[String]

}


object multimedia {

  case class Metadata(
    photoUrl: Url,
  )

  case class Photo(
  id: Id[Photo],
  petId: Id[ShoeModel],
  metadata: Metadata
  )
}

object shoes {

  case class Tag(id: Int, name: String)

  case class ShoeModel(
    id: Id[ShoeModel],
    name: String,
    photoUrl: List[Url],
    tags: List[Tag]
  )
}

object store {
  case class ShoeSize(value: Int) extends Wrapped[Int]
  case class Item(
    sku: Id[Item],
    shoeModelId: Id[ShoeModel],
    size: ShoeSize,
    quantity: Int
  )

  // order is very simple, it contains 1 sku in arbitrary quantity
  case class Order(
    id: Id[Order],
    sku: Id[Item],
    quantity: Int,
    shipDate: ZonedDateTime,
    status: String
  )
}