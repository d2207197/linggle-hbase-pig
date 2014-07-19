import AssemblyKeys._

assemblySettings

name := """pig-hbase-ngram"""

version := "1.0"

scalaVersion := "2.11.1"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

resolvers += "JBoss" at "https://repository.jboss.org/nexus/content/groups/public/"

resolvers += "Apache Maven" at "http://mvnrepository.com/artifact"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Simile" at "http://simile.mit.edu/maven/"

resolvers += "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

// libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

libraryDependencies += "org.apache.pig" % "pig" % "0.12.0-cdh5.0.2"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.3.0-cdh5.0.1" 

libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.3.0-cdh5.0.1" 

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.3.0-cdh5.0.1" 

libraryDependencies += "org.apache.hbase" % "hbase-client" % "0.96.1.1-cdh5.0.2"

libraryDependencies += "org.apache.hbase" % "hbase-common" % "0.96.1.1-cdh5.0.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.4.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0-rc3"

libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.10" % "2.4.0-rc2"

// libraryDependencies += "joda-time" % "joda-time" % "2.1"



// libraryDependencies += "log4j" % "log4j" % "1.2.15"

// libraryDependencies += "jline" % "jline" % "0.9.94"

// libraryDependencies += "com.google.guava" % "guava" % "r09"


// libraryDependencies += "joda-time" % "joda-time" % "2.1"

// libraryDependencies += "org.antlr" % "antlr-runtime" % "3.4"

// libraryDependencies += "org.scalaj" % "scalaj-time_2.10.0-M7" % "0.6"


// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3"

// mergeStrategy in assembly := { case _ => MergeStrategy.first }

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    // case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
    case PathList("javax", "xml", xs @ _*) => MergeStrategy.first
    // case PathList("META-INF", "maven", "joda-time", xs @ _*) => MergeStrategy.first
    case PathList("org", "apache", "commons", xs @ _*) => MergeStrategy.first
    case PathList("org", "apache", "jasper", xs @ _*) => MergeStrategy.first
    // case PathList("org", "joda", "time", xs @ _*) => MergeStrategy.first
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
    case PathList("javax", "el", xs @ _*) => MergeStrategy.first
    case PathList("org", "apache", "hadoop", xs @ _*) => MergeStrategy.first
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case PathList("META-INF", xs @ _*) => MergeStrategy.first
    case PathList("com", xs @ _*) => MergeStrategy.first
    case PathList("jline", xs @ _*) => MergeStrategy.first
    case PathList("overview.html", xs @ _*) => MergeStrategy.first
    case x => old(x)
  }
}
