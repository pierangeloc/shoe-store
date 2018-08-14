package io.tuliplogic

import cats.effect.{IO, Sync}
import io.tuliplogic.common.ConfigLoadException
import io.tuliplogic.config.HttpConfig
import pureconfig._
import simulacrum.typeclass

import scala.language.higherKinds

/**
  *
  * event-sourcing-zio - 10/08/2018
  * Created with ♥ in Amsterdam
  */
object config {

  case class DatabaseConfig(hosts: List[String], port: Int, keyspace: String)

  @typeclass
  trait DatabaseConfigCapability[F[_]] {
    def getConfig: F[DatabaseConfig]
  }

  case class HttpConfig(baseUrl: String, hostname: String, port: Int)

  @typeclass
  trait HttpConfigCapability[F[_]] {
    def getConfig: F[HttpConfig]
  }

  def config[F[_]: Sync](service: String): F[(HttpConfig, DatabaseConfig)] = {
    val resEither = for {
        httpConfig <- loadConfig[HttpConfig](s"$service.http")
        dbConfig <- loadConfig[DatabaseConfig](s"$service.db")
      } yield (httpConfig, dbConfig)

    resEither.fold(e => Sync[F].raiseError(ConfigLoadException(e.toString)), cfg => Sync[F].pure(cfg))
  }
}

object T extends App {
  println(loadConfig[HttpConfig](s"shoes.http"))
}
