package io.tuliplogic

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

  trait HttpConfigCapability[F[_]] {
    def getConfig: F[HttpConfig]
  }

  object HttpConfigCapability {
    def apply[F[_]](implicit F: HttpConfigCapability[F]): HttpConfigCapability[F] = F
  }


  object My extends App {
    def summon[F[_]: HttpConfigCapability] = HttpConfigCapability[F]
  }
}
