/* Category Theory
*  - objects = types A, B
*  - arrows = functions  A => B
*  - A => B -> domain f = A, codomain f = B
*  - identity law: ID_A: A => A
*     f: A => B ID_B(f) = f(ID_A)
*  - associativity law:
*    f: A => B, g: B => A
*    f(g(_)) = g(f(_))
* */

val f: Int => String = _.toString

//Identity
val idInt: Int => Int = x => x
val idString: String => String = s => s

idString compose f
f compose idInt

//Associativity
val g: String => Int = _.length
//f(g(x))
f.compose(g) // 2 _ 1
g.andThen(f) // 1 _ 2