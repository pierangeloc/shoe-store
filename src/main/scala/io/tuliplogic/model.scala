package io.tuliplogic

import io.tuliplogic.common.{Id, Url}
import io.tuliplogic.pet.Pet
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
