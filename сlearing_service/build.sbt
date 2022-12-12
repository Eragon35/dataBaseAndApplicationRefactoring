name := "сlearing_service"

version := "1.0"

lazy val `сlearing_service` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(jdbc, ehcache, ws, specs2 % Test, guice)


libraryDependencies ++= Seq("com.typesafe.slick" %% "slick" % "3.3.3",
    "com.typesafe.play" %% "play-json" % "2.9.2",
    "org.squeryl" %% "squeryl" % "0.9.16",
    "org.postgresql" % "postgresql" % "42.3.1",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
    "com.lihaoyi" %% "requests" % "0.6.9",
    "com.typesafe.play" %% "play-ahc-ws-standalone" % "2.1.10",
    "com.typesafe.play" %% "play-ws-standalone-json" % "2.1.10"
)