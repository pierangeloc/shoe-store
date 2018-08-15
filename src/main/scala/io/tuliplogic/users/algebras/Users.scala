package io.tuliplogic.users.algebras

import cats.effect.IO
import io.tuliplogic.users.model.{User, UserId}
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

object Users {
  implicit val usersIo: Users[IO] = new Users[IO] {
    override def add(user: User): IO[Unit] =
      IO.pure(())
    override def delete(user: UserId): IO[Unit]        = IO.pure(())
    override def get(userId: UserId): IO[Option[User]] = IO.pure(None)
  }
}

@typeclass
trait AnotherService[F[_]] {
  def getShit: F[String]
}
