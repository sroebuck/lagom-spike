organization in ThisBuild := "sample.helloworld"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

lazy val locatorService = scalaProject("lagom-service-locator-consul-java")
  .settings(version := "1.0-SNAPSHOT")
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      "com.ecwid.consul" % "consul-api" % "1.1.11"
    )
  )

lazy val echoApi = project("echo-api")
  .settings(version := "1.0-SNAPSHOT")
  .settings(
    libraryDependencies += lagomJavadslApi
  )

lazy val echoImpl = project("echo-impl")
  .settings(version := "1.0-SNAPSHOT")
  .enablePlugins(LagomJava && LagomPlay)
  .settings(
    libraryDependencies += lagomJavadslTestKit
  )
  .dependsOn(echoApi, locatorService)

//lazy val spreadApi = project("spread-api")
//  .settings(version := "1.0-SNAPSHOT")
//  .settings(
//    libraryDependencies += lagomJavadslApi
//  )
//
//lazy val spreadImpl = project("spread-impl")
//  .settings(version := "1.0-SNAPSHOT")
//  .enablePlugins(LagomJava)
//  .settings(
//    libraryDependencies += lagomJavadslTestKit
//  )
//  .dependsOn(spreadApi, trafficApi)
//
//lazy val trafficApi = project("traffic-api")
//  .settings(version := "1.0-SNAPSHOT")
//  .settings(
//    libraryDependencies += lagomJavadslApi
//  )
//
//lazy val trafficImpl = project("traffic-impl")
//  .settings(version := "1.0-SNAPSHOT")
//  .enablePlugins(LagomJava)
//  .settings(
//    libraryDependencies ++= Seq(
//      lagomJavadslImmutables,
//      lagomJavadslPersistenceCassandra,
//      lagomJavadslPubSub,
//      lagomJavadslTestKit
//    )
//  )
//  .settings(lagomForkedTestSettings: _*)
//  .dependsOn(trafficApi, echoApi)

def scalaProject(id: String) = Project(id, base = file(id))

def project(id: String) = Project(id, base = file(id))
  .settings(javacOptions in compile ++= Seq(
    "-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation"))
  .settings(jacksonParameterNamesJavacSettings: _*) // applying it to every project even if not strictly needed.

// See https://github.com/FasterXML/jackson-module-parameter-names
lazy val jacksonParameterNamesJavacSettings = Seq(
  javacOptions in compile += "-parameters"
)
