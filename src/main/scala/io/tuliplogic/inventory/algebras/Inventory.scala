package io.tuliplogic.inventory.algebras

import io.tuliplogic.inventory.model.{Item, ShoeSize, Sku}
import io.tuliplogic.shoes.model.ModelId
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
  def getItemsByModel(modelId: ModelId): F[List[Item]]
  def createItem(sku: Sku,
                 modelId: ModelId,
                 size: ShoeSize,
                 initialStock: Int)
  def increaseStock(sku: Sku)

  //TODO: order management, we can do this separately

//  val s: Seq[Int] = Seq(1,2,3)
//  s match {
//    case x :: xs => true
//    case Seq() => false
//  }
}
