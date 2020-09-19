import scala.util.Try

val option: Int => Option[Int] = (x: Int) => if(x < 0) Option.empty[Int] else Option(x)

Option(5).flatMap(option).equals(option(5))
Option(-1).flatMap(option).equals(option(-1))

val f = (x: String) => Try(x.toInt).toOption
val g = (x: Int) => x.toString.headOption

Seq("", "23", "AB").map(Option(_)).map { opS =>
  opS.flatMap(f).flatMap(g) == opS.flatMap(x => f(x).flatMap(g))
}