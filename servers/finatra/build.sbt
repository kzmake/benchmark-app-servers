name := "finatra"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.7"

def finatraVersion = "18.11.0"

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases")
)

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % finatraVersion
)


