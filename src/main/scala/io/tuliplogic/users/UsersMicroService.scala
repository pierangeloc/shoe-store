package io.tuliplogic.users
import cats.effect.{ConcurrentEffect, ExitCode, IO, IOApp}
import fs2.Stream
import io.tuliplogic.commons.config
import io.tuliplogic.commons.config.HttpConfigCapability
import io.tuliplogic.users.algebras.Users
import io.tuliplogic.users.endpoints.UserEndpoints
import org.http4s.server.blaze.BlazeBuilder

import scala.language.higherKinds

/**
 * 
 * Default (Template) Project - 16/08/2018
 * Created with ♥ in Amsterdam
 */
object UsersMicroService extends IOApp {

  def bootstrap[F[_]: ConcurrentEffect: HttpConfigCapability: Users]: Stream[F, ExitCode] =
    for {
      conf <- Stream.eval(HttpConfigCapability[F].getConfig)
      server <- BlazeBuilder[F]
                 .bindHttp(conf.port, conf.hostname)
                 .mountService(UserEndpoints.allEndpoints(conf), s"/${conf.baseUrl}")
                 .serve
    } yield server


  implicit val ioHttpConfigCapability: HttpConfigCapability[IO] = new HttpConfigCapability[IO] {
    override def getConfig: IO[config.HttpConfig] = config.config[IO]("shoes").map(_._1)
  }

  override def run(args: List[String]): IO[ExitCode] =
    bootstrap[IO].compile.toList.map(_.head)
}
