package ru.phoenixit.expert.fp.demo.fp_staff

import scala.util.Try

trait LearnTypeClass[A] {
  def tell(listener: A): Try[A]
}



object TypeClasses {

  //object interface
  val stringTypeClass: LearnTypeClass[String] = new LearnTypeClass[String] {
    override def tell(listener: String) = Try(listener)
  }

}
