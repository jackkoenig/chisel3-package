import mill._
import mill.scalalib._

import ammonite.ops._

import $file.node_modules.`firrtl-package`.firrtlBuild
import $file.chisel3Build

object firrtl extends firrtlBuild.FirrtlModule {
  def millSourcePath = super.millSourcePath / up / "node_modules" / "firrtl-package" / "firrtl"
}

object chisel3 extends chisel3Build.Chisel3Module {
  def sharedDeps = Seq(firrtl)
}
