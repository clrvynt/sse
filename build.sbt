name := """sse"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
