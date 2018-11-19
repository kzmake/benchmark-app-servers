name := "colossus"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.7"

val colossusVersion = "0.11.0"
val akkaVersion = "2.5.18"

libraryDependencies ++= Seq(
  "com.tumblr" %% "colossus" % colossusVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)
