import ammonite.ops._
import ammonite.ops.ImplicitWd._
import mill._
import mill.scalalib._
import mill.scalalib.publish._
import mill.eval.{Evaluator, Result}
import mill.define.Task
import mill.modules.Jvm._

import $file.common
import $file.BuildInfo

trait ChiselCommonModule extends SbtModule with common.CommonOptions {
  val macroPlugins = Agg(ivy"org.scalamacros:::paradise:2.1.0")
  def scalacPluginIvyDeps = macroPlugins
  def compileIvyDeps = macroPlugins

  def ivyDeps = Agg(
    ivy"com.typesafe.scala-logging::scala-logging:3.7.2",
    ivy"net.jcazevedo::moultingyaml:0.4.0"
  )
}

// Define the common chisel module.
trait Chisel3Module extends ChiselCommonModule with BuildInfo.BuildInfo with common.SingleJar { outer =>

  def millSourcePath = super.millSourcePath / up / 'chisel3
  def sharedDeps: Seq[ScalaModule]

  object coreMacros extends ChiselCommonModule with common.CommonOptions {
    def millSourcePath = outer.millSourcePath / 'coreMacros
  }

  object chiselFrontend extends ChiselCommonModule with common.CommonOptions {
    def millSourcePath = outer.millSourcePath / 'chiselFrontend
    def moduleDeps = sharedDeps :+ coreMacros
  }

  override def moduleDeps: Seq[ScalaModule] = Seq(coreMacros, chiselFrontend)

  override def ivyDeps = super.ivyDeps() ++ Agg(
    ivy"com.github.scopt::scopt:3.6.0"
  )

  override def buildInfoMembers = T {
    Map[String, String](
      "buildInfoPackage" -> artifactName(),
      "version" -> "3.2-SNAPSHOT",
      "scalaVersion" -> scalaVersion()
    )
  }
}
