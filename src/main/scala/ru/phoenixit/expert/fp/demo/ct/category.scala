package ru.phoenixit.expert.fp.demo

package object ct {

  trait Monoid[A] {
    def op(x: A, y: A): A
    def zero: A
  }

  val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def op(x: Int, y: Int): Int = x + y
    override def zero = 0
  }

  // Higer-Kinded types
  // List => List[_] => List[Int]
  // A => List[A] Option[List[Int]] => Option[F[_]]
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
  }

  trait Monad[F[_]] extends Functor[F] {
    //unit
    def apply[A](x: => A): F[A]
    //bind
    def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

    def map[A, B](ma: F[A])(f: A => B): F[B] =
      flatMap(ma)(x => apply(f(x)))
  }
}
