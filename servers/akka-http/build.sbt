name := "akka-http"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.7"

val akkaHttpVersion = "10.1.5"
val akkaVersion = "2.5.18"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)
