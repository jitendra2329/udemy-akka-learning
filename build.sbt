ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "udemy-akka-learning"
  )

val akkaVersion = "2.8.0"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15"

//libraryDependencies += "com.typesafe.akka" %% "akka-actor-testkit-typed"    % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed"            % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-coordination"           % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-cluster"                % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-cluster-typed"          % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-cluster-metrics"        % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-cluster-sharding"       % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-cluster-sharding-typed" % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-cluster-tools"          % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-discovery"              % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-distributed-data"       % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-multi-node-testkit"     % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-persistence"            % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-persistence-typed"      % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-persistence-query"      % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-protobuf-v3"            % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-remote"                 % akkaVersion // Scala 2.12/2.13/3.2
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j"                  % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-stream"                 % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit"         % akkaVersion // Scala 2.12/2.13/3.2
//libraryDependencies += "com.typesafe.akka" %% "akka-stream-typed"           % akkaVersion // Scala 2.12/2.13/3.2