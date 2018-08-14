package io.tuliplogic

import cats.implicits._
import cats.effect._
import fs2.StreamApp
import fs2.Stream
import fs2.StreamApp.ExitCode
import io.tuliplogic.algebras.Users
import io.tuliplogic.common.Error
import io.tuliplogic.config.HttpConfigCapability
import io.tuliplogic.endpoints.UserEndpoints
import org.http4s.server.blaze.BlazeBuilder
//import scalaz.zio.{App, IO}

object ShoesMicroService extends StreamApp[IO] {

  import scala.concurrent.ExecutionContext.Implicits.global
//  type AppEffect[A] = IO[Error, A]

//  def syncIO[E](exceptionHandler: Throwable => E): Sync[IO[E, ?]] = new Sync[IO[E, ?]] {
//
//    override def suspend[A](thunk: => IO[E, A]): IO[E, A] = IO.suspend(thunk)
//    override def raiseError[A](e: Throwable): IO[E, A] = IO.fail(exceptionHandler(e))
//    override def handleErrorWith[A](fa: IO[E, A])(f: Throwable => IO[E, A]): IO[E, A] = ???
//    override def pure[A](x: A): IO[E, A] = IO.point(x)
//    override def flatMap[A, B](fa: IO[E, A])(f: A => IO[E, B]): IO[E, B] = fa.flatMap(f)
//    override def tailRecM[A, B](a: A)(f: A => IO[E, Either[A, B]]): IO[E, B] = ???
//}
//
//  override def run(
//      args: List[String]): IO[Nothing, ShoesMicroService.ExitStatus] = {
//    appSetup.attempt.map(_.fold(_ => 1, _ => 0)).map(ExitStatus.ExitNow(_))
//  }

  def bootstrap[F[_]: Effect: HttpConfigCapability: Users]: Stream[F, ExitCode] =
    for {
      conf <- Stream.eval(HttpConfigCapability[F].getConfig)
      server <- BlazeBuilder[F]
                 .bindHttp(conf.port, conf.hostname)
                 .mountService(UserEndpoints.allEndpoints(conf))
                 .serve
    } yield server


  implicit val ioHttpConfigCapability: HttpConfigCapability[IO] = new HttpConfigCapability[IO] {
    override def getConfig: IO[config.HttpConfig] = config.config[IO]("shoes").map(_._1)

  }

  override def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
  (bootstrap[IO])
}
