package io.tuliplogic

import cats.effect.Sync
import io.tuliplogic.common.ConfigLoadException
import pureconfig._
import simulacrum.typeclass

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
        httpConfig <- loadConfig[HttpConfig](service)
        dbConfig <- loadConfig[DatabaseConfig](service)
      } yield (httpConfig, dbConfig)

    resEither.fold(e => Sync[F].raiseError(ConfigLoadException(e.toString)), cfg => Sync[F].pure(cfg))
  }
}
