package io.tuliplogic.shoes
import io.tuliplogic.commons.model.{Id, Url}

/**
 * 
 * event-sourcing-zio - 15/08/2018
 * Created with ♥ in Amsterdam
 */
object model {
  type ModelId = Id[ShoeModel]

  case class Tag(id: Int, name: String)
  case class ShoeModel(
    id: Id[ShoeModel],
    name: String,
    photoUrl: List[Url],
    tags: List[Tag]
  )
}
