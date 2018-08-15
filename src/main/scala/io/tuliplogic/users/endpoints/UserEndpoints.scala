package io.tuliplogic.users.endpoints

import cats.effect.Effect
import cats.data.EitherT
import cats.implicits._
import io.tuliplogic.commons.config.HttpConfig
import org.http4s.{EntityDecoder, HttpService}
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import io.tuliplogic.commons.model.UserServiceError
import io.tuliplogic.users.algebras.Users
import io.tuliplogic.users.model.User

import scala.language.higherKinds

/**
  *
  * event-sourcing-zio - 10/08/2018
  * Created with ♥ in Amsterdam
  */
class UserEndpoints[F[_]: Effect](httpConfig: HttpConfig) extends Http4sDsl[F] {

  implicit val userDecoder: EntityDecoder[F, User] = jsonOf

  def addUserEndpoint(users: Users[F]): HttpService[F] = HttpService[F] {
    case req @ POST -> Root / httpConfig.baseUrl =>
      val action: EitherT[F, UserServiceError, User] = for {
        userReq <- EitherT.liftF(req.as[User])
        _ <- EitherT.liftF(users.add(userReq))
      } yield userReq

      action.value.flatMap {
        case Left(error) => BadRequest(s"Something went wrong $error")
        case Right(user) => Ok(user.asJson)
      }
  }

  def endpoints(implicit users: Users[F]): HttpService[F] = addUserEndpoint(users)
}

object UserEndpoints {
  def allEndpoints[F[_]: Effect: Users](httpConfig: HttpConfig): HttpService[F] =
    new UserEndpoints[F](httpConfig).endpoints
}
