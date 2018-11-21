organization := "com.github.kzmake"

name := "finch"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.7"

val finchVersion = "0.26.0"
val circeVersion = "0.10.1"
val scalatestVersion = "3.0.5"

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % finchVersion,
  "com.github.finagle" %% "finchx-circe" % finchVersion,
  "io.circe" %% "circe-generic" % circeVersion
)
