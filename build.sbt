scalaVersion in Global := "2.11.0"

organization in Global := "org.improving"

version in Global := "0.1.1-SNAPSHOT"

exportJars in Global := true

initialCommands in console := s"cat ${baseDirectory.value}/src/main/resources/replStartup.scala".!!

libraryDependencies ++= Seq(
  "org.scala-lang"  % "scala-compiler"  % scalaVersion.value,
  "jline"           % "jline"           %       "2.11",
  "ch.qos.logback"  % "logback-classic" %      "1.0.9",
  "org.scalacheck" %% "scalacheck"      %      "1.11.3"       % "test",
  "com.lihaoyi"    %% "utest"           %       "0.1.4"       % "test"
)

lazy val root = project in file(".") settings (
  name                      := "psp-view",
  description               := "psp alternate view implementation",
  homepage                  := Some(url("https://github.com/paulp/psp-view")),
  licenses                  := Seq("Apache" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
  shellPrompt               := (s => name.value + projectString(s) + "> "),
  parallelExecution in Test := false,
  fork in Test              := true
)

testFrameworks += new TestFramework("utest.runner.JvmFramework")

def projectString(s: State): String = (
  (Project extract s).currentRef.project.toString match {
    case s if s startsWith "default-" => ""
    case s                            => "#" + s
  }
)
