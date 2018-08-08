package io.tuliplogic

import scalaz._, Scalaz._

object Eventsourcingzio {

  def main(args : Array[String]) : Unit = {

    val x = 3.just
    val y = 5.just

    val sum = (x |@| y){_ + _}

    println(s"$x + $y = $sum")

  }

}
