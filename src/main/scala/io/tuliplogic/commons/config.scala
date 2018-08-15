package io.tuliplogic.commons

import cats.effect.Sync
import io.tuliplogic.commons.config.HttpConfig
import io.tuliplogic.commons.model.ConfigLoadException
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
