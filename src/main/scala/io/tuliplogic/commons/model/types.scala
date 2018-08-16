package io.tuliplogic.commons.model
import io.circe.{Decoder, Encoder}

trait Wrapped[T] { def value: T }

case class Id[T](id: Long)
object Id {
  implicit def encoder[A]: Encoder[Id[A]] = Encoder.encodeLong.contramap(_.id)
  implicit def decoder[A]: Decoder[Id[A]] = Decoder.decodeLong.map(l =>Id[A](l))
}

case class Url(value: String) extends Wrapped[String]

sealed trait Error {
  def message: String
}

case class UserServiceError(message: String) extends Error
case class ShoesServiceError(message: String) extends Error
case class MultimediaServiceError(message: String) extends Error
case class InventoryServiceError(message: String) extends Error
case class GeneralError(message: String) extends Error

case class ConfigLoadException(message: String) extends Exception //This is required to work with Sync, but it would be nicer to have something like ZIO with custom error
