package io.tuliplogic.multimedia
import io.tuliplogic.commons.model.{Id, Url}
import io.tuliplogic.shoes.model.ShoeModel

/**
 * 
 * event-sourcing-zio - 15/08/2018
 * Created with ♥ in Amsterdam
 */
object model {
  type ImageId = Id[Image]
  case class Metadata(
    photoUrl: Url,
  )

  case class Image(
    id: ImageId,
    petId: Id[ShoeModel],
    metadata: Metadata,
    content: Array[Byte]
  )
}
