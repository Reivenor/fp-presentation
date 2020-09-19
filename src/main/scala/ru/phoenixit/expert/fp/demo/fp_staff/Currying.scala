package ru.phoenixit.expert.fp.demo.fp_staff

object Currying {

  def sum(x: Int, y: Int): Int = x + y

  def add(x: Int): Int => Int = y => x + y

  def addConform(x: Int)(y: Int): Int = x + y

  val resultStandard: Int = sum(1, 2)
  val increment: Int => Int = add(1)
  val resultCurry: Int = increment(2)
}
