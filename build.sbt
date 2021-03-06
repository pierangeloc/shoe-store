import sbt._

lazy val eventsourcingzio = (project in file(".")).
  settings (
    name := "event-sourcing-zio",
    organization := "io.tuliplogic",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.12.6"
    // add other settings here
  )

/* scala versions and options */
scalaVersion := "2.12.6"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)


// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  , "-unchecked"
  , "-encoding", "UTF-8"
  , "-Xlint"
  , "-Xverify"
  , "-feature"
  ,"-Ypartial-unification"
//  ,"-Xfatal-warnings"
  , "-language:_",
    "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals",              // Warn if a local definition is unused.
    "-Ywarn-unused:params",              // Warn if a value parameter is unused.
    "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates",            // Warn if a private member is unused.
  //,"-optimise"
)

//javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.7", "-target", "1.7")

// javaOptions in Universal ++= Seq(
//   "-J-server",
//   "-J-Xms1g -Xmx4g",
//   "-J-XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled",
//   "-J-XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=68",
//   "-J-XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark",
//   "-J-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"
// )

val CatsVersion = "1.1.0"
val CatsEffectVersion = "1.0.0-RC2"
val CirceVersion = "0.10.0-M1"
val MonixVersion = "3.0.0-M3"
val ScalaZVersion = "7.2.23"
val ZIOVersion = "0.1.0-SNAPSHOT"
val ShapelessVersion = "2.3.3"
val Fs2Version = "1.0.0-M5"
val Http4sVersion = "0.19.0-SNAPSHOT"
val MonocleVersion = "1.5.0"
val PureConfigVersion = "0.9.1"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1",
  // -- testing --
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  // -- Logging --
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  // -- json/circe --
  "io.circe" %% "circe-core" % CirceVersion,
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-jawn" % CirceVersion,
  "io.circe" %% "circe-yaml" % "0.8.0",
  // Cats
  "org.typelevel" %% "cats-core" % CatsVersion,
//  "org.typelevel" %% "cats-effect" % CatsEffectVersion,
  // fs2
//  "co.fs2" %% "fs2-core" % Fs2Version, // For cats 1.1.0 and cats-effect 0.10
  // http4s
  "org.http4s"            %% "http4s-blaze-server"  % Http4sVersion,
  "org.http4s"            %% "http4s-circe"         % Http4sVersion,
  "org.http4s"            %% "http4s-dsl"           % Http4sVersion,
  // monix
  "io.monix" %% "monix" % MonixVersion,
  // shapeless
  "com.chuusai" %% "shapeless" % ShapelessVersion,
  // scalaz
  "org.scalaz" %% "scalaz-core" % ScalaZVersion,
  "org.scalaz" %% "scalaz-zio" % ZIOVersion,
  //pureconfig
  "com.github.pureconfig" %% "pureconfig" % PureConfigVersion,
// monocle
  "com.github.julien-truffaut" %%  "monocle-core"  % MonocleVersion,
  // type classes
  "com.github.mpilquist" %% "simulacrum" % "0.13.0",
  // li haoyi ammonite repl embed
  "com.lihaoyi" % "ammonite" % "1.1.2" % "test" cross CrossVersion.full

)

//ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

resolvers ++= Seq(
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Secured Central Repository" at "https://repo1.maven.org/maven2",
  Resolver.sonatypeRepo("snapshots")
)

// scalariform
//scalariformSettings

// ScalariformKeys.preferences := ScalariformKeys.preferences.value
//   .setPreference(AlignSingleLineCaseStatements, true)
//   .setPreference(DoubleIndentClassDeclaration, true)
//   .setPreference(IndentLocalDefs, true)
//   .setPreference(IndentPackageBlocks, true)
//   .setPreference(IndentSpaces, 2)
//   .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)

// ammonite repl
sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main().run() }""")
  Seq(file)
}.taskValue

