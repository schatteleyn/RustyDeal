import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "rusty"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "org.ancelin.play2.couchbase" %% "play2-couchbase" % "0.1-SNAPSHOT"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
    resolvers += "ancelin" at "https://raw.github.com/mathieuancelin/play2-couchbase/master/repository/snapshots",
    resolvers += "Spy Repository" at "http://files.couchbase.com/maven2"
  )

}
