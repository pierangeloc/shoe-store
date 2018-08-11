package io.tuliplogic.algebras

import io.tuliplogic.common.Id
import io.tuliplogic.shoes.ShoeModel
import io.tuliplogic.store.{Item, ShoeSize, Sku}
import simulacrum.typeclass

import scala.language.higherKinds

/**
  *
  * event-sourcing-zio - 09/08/2018
  * Created with ♥ in Amsterdam
  */
@typeclass
trait Inventory[F[_]] {
  def getAvailableItems(sku: Sku): F[List[Item]]
  def getItemsByModel(modelId: Id[ShoeModel]): F[List[Item]]
  def createItem(sku: Sku,
                 modelId: Id[ShoeModel],
                 size: ShoeSize,
                 initialStock: Int)
  def increaseStock(sku: Sku)

  //TODO: order management, we can do this separately
}
