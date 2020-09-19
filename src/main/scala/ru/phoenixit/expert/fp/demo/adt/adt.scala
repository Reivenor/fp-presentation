package ru.phoenixit.expert.fp.demo

package object adt {
  val opInt: Option[Int] = Option(2)
  val realyInt: Option[Int] = Some(2)
  val noAnyInt: Option[Int] = None

  val lst = List(1, 2, 3)
  val empty: List[Int] = Nil
  val nonEmpty: List[Int] = ::(3, ::(2, ::(1, Nil)))

  sealed trait MyStatus[A]
  case object NoResult extends MyStatus[Unit]
  case class Success[A](value: A) extends MyStatus[A]
  case class Error(message: String) extends MyStatus[Nothing]

}
