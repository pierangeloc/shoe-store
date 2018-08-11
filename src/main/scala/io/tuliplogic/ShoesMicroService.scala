package io.tuliplogic

import cats.implicits._
import cats.effect.Effect
import io.tuliplogic.algebras.Users
import io.tuliplogic.common.Error
import io.tuliplogic.config.HttpConfigCapability
import io.tuliplogic.endpoints.UserEndpoints
import org.http4s.server.blaze.BlazeBuilder
import scalaz.zio.{App, IO}

object ShoesMicroService extends App {

//  import scala.concurrent.ExecutionContext.Implicits.global
  type AppEffect[A] = IO[Error, A]

  override def run(
      args: List[String]): IO[Nothing, ShoesMicroService.ExitStatus] = {
    appSetup.attempt.map(_.fold(_ => 1, _ => 0)).map(ExitStatus.ExitNow(_))
  }

  def bootstrap[F[_]: Effect: HttpConfigCapability: Users]: F[Unit] =
    for {
      conf <- HttpConfigCapability[F].getConfig
      server <- BlazeBuilder[F]
                 .bindHttp(conf.port, conf.hostname)
                 .mountService(UserEndpoints.createUserEndpoints(conf))
                 .start
    } yield ()

  def appSetup: IO[Error, Unit] = IO.point(())
}
