package io.tuliplogic.algebras

import io.tuliplogic.users.{User, UserId}
import simulacrum.typeclass

import scala.language.higherKinds

/**
  *
  * event-sourcing-zio - 09/08/2018
  * Created with ♥ in Amsterdam
  */
@typeclass
trait Users[F[_]] {
  def add(user: User): F[Unit]
  def delete(user: UserId): F[Unit]
  def get(userId: UserId): F[Option[User]]
}

@typeclass
trait AnotherService[F[_]] {
  def getShit: F[String]
}