import scala.language.postfixOps

def sum(a: Int, b: Int): Int = a + b

val sumAsValue: (Int, Int) => Int = (a, b) => a + b

//High-Order functions

val list = 1 to 10 toList

def map(list: List[Int], f: Int => String) = for {
  el <- list
} yield f(el)

def reduce(list: List[Int], g: (Int, Int) => Int) = {
  def loop(list: List[Int], acc: Int): Int = list match {
    case Nil => acc
    case head :: tail => loop(tail, g(acc, head))
  }
  loop(list, 0)
}