package io.tuliplogic.multimedia.algebras

import io.tuliplogic.multimedia.model.{ImageId, Metadata}
import io.tuliplogic.shoes.model.ModelId

import scala.language.higherKinds

/**
  * A Multimedia service, responsible for CRD opeerations on images
  * @tparam F the effect of our computation
  * @tparam S the way we want these images to be saved/retrieved. can be a stream, an array, whatever
  */
trait Multimedia[F[_], S[_]] {
  def addImage(bytes: S[Byte], shoeId: ModelId): F[Metadata]
  def getImage(photoId: ImageId): F[(Metadata, S[Byte])]
  def deleteImage(photoId: ImageId): F[Unit]
}
