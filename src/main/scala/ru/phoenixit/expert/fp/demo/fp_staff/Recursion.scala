package ru.phoenixit.expert.fp.demo.fp_staff

import scala.annotation.tailrec

object Recursion {
  def factorial(n: Int): Int =
    if (n == 1) 1 else n * factorial(n - 1)

  def factorialTailRec(n: Int): Int = {
    @tailrec
    def loop(n: Int, acc: Int): Int = {
      if(n == 1) 1 else loop(n - 1, acc * n)
    }
    loop(n, 1)
  }

  @tailrec
  def gcd(a: Int, b: Int):Int = b match {
    case 0 => a
    case value if value > a => gcd(value, a)
    case value => gcd(value, a % value)
  }
}
