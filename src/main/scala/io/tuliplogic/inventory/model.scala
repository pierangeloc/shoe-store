package io.tuliplogic.inventory
import java.time.ZonedDateTime

import io.tuliplogic.commons.model.{Id, Wrapped}
import io.tuliplogic.shoes.model.ModelId

/**
 * 
 * event-sourcing-zio - 15/08/2018
 * Created with ♥ in Amsterdam
 */
object model {
  type Sku = Id[Item]
  type OrderId = Id[Order]

  case class ShoeSize(value: Int) extends Wrapped[Int]
  case class Item(
    sku: Sku,
    shoeModelId: ModelId,
    size: ShoeSize,
    quantity: Int
  )

  // order is very simple, it contains only 1 sku in arbitrary quantity
  case class Order(
    id: OrderId,
    sku: Sku,
    quantity: Int,
    shipDate: ZonedDateTime,
    status: String
  )
}
