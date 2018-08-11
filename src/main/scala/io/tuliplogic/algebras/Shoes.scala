package io.tuliplogic.algebras

import io.tuliplogic.shoes.{ModelId, ShoeModel, Tag}
import simulacrum.typeclass

import scala.language.higherKinds

/**
  *
  * event-sourcing-zio - 09/08/2018
  * Created with ♥ in Amsterdam
  */
@typeclass
trait Shoes[F[_]] {
  def add(shoeModel: ShoeModel): F[Unit]
  def delete(shoeModelId: ModelId): F[Unit]
  def get(shoeModelId: ModelId): F[ShoeModel]
  def findByTag(tags: List[Tag]): F[List[ShoeModel]]
}
