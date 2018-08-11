package io.tuliplogic.algebras

import io.tuliplogic.common.Id
import io.tuliplogic.multimedia.{Image, Metadata}
import io.tuliplogic.shoes.ShoeModel
import simulacrum.typeclass

/**
  * A Multimedia service, responsible for CRD opeerations on images
  * @tparam F the effect of our computation
  * @tparam S the way we want these images to be saved/retrieved. can be a stream, an array, whatever
  */
@typeclass
trait Multimedia[F[_], S[_]] {
  def addImage(bytes: S[Byte], shoeId: Id[ShoeModel]): F[Metadata]
  def getImage(photoId: Id[Image]): F[(Metadata, S[Byte])]
  def deleteImage(photoId: Id[Image]): F[Unit]
}
