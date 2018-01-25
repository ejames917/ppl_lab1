/*
 * CSCI 3155: Lab 1 Worksheet
 *
 * This worksheet demonstrates how you could experiment
 * interactively with your implementations in Lab1.scala.
 */

/*
 * Example: Test-driven development of plus
 */

// Here we can write expressions to experiment with how we might implement
// something. The expression is evaluated interactively

// The worksheet is built with all of the project files, so we can call
// a function from your jsy.student.Lab1 object (in Lab1.scala).
jsy.student.Lab1.plus(3, 4)

// We can imports all of the functions from jsy.student.Lab1
import jsy.lab1.{Empty, Node, SearchTree}
import jsy.student.Lab1._
plus(3, 4)

// We can check the implementation here, though it better to write tests
// in Lab1Spec.scala.
assert(plus(1, 1) == 2)

// Braces {} can be used wherever parentheses () can be (but not the other
// way around). Braces {} introduce scope, while () do not.
assert {
  val two = 2
  plus(1, 1) == two
}

/* Exercises */

// Call jsy.student.Lab1.abs
//abs(-3) // Will fail until implemented in Lab1.scala.

// Call the JavaScripty parser (from the provided library) on a string
jsy.lab1.Parser.parse("-4")

// We can import the parse function from jsy.lab1.Parser to experiment
// with the provided parser.
import jsy.lab1.Parser.parse
val negFourAST = parse("-4")

// We also want to import the AST nodes for convenience.
import jsy.lab1.ast._
assert {
  negFourAST match {
    case Unary(_, _) => true
    case _ => false
  }
}

// Evaluate that JavaScripty expression.
//eval(negFourAST)

// For convenience, we also have an eval function that takes a string,
// which calls the parser and then delegates to your eval function.
//eval("1 + 1")

def repeat(s: String, n: Int): String = {
  require(n>=0)
  if(n==0) ""
  else if(n==1) s
  else s.concat(repeat(s, n-1))
}

repeat("abc", 3)
repeat("", 15)
repeat("blah123", 5)
repeat("blah234", 0)

def xor(a: Boolean, b: Boolean): Boolean = {
  if(a && !b) true
  else if(b && !a) true
  else false
}

xor(true, false)
xor(true, true)
xor(false, false)

def sqrtStep(c: Double, xn: Double): Double = (xn - ((xn*xn-c)/(2*xn)))

sqrtStep(4, 1)
sqrtStep(1, 1)
sqrtStep(5, 8)


def sqrtErr(c: Double, x0: Double, epsilon: Double): Double = {
  require(c>=0 & epsilon > 0)
  if (abs(x0*x0-c) < epsilon) x0
  else {
    val newx0 = sqrtN(c, x0, 5)
    sqrtErr(c, newx0, epsilon)
  }
}

sqrtErr(4, 1, 0.1)
sqrtErr(4, 1, 0.0001)
sqrtErr(4, 1, 0.00000001)

def repOk(t: SearchTree): Boolean = {
  def check(t: SearchTree, min: Int, max: Int): Boolean = t match {
    case Empty => true
    case Node(l, d, r) => if((min<=d) && (d<max)) check(l,min,d) && check(r,d,max) else false
  }
  check(t, Int.MinValue, Int.MaxValue)
}

val t1 = Node(Empty, 2, Empty)
val t2 = Node(t1, 4, Empty)
val t3 = Node(Empty, 4, t2)
val t4 = Node(t1, 4, Node(Empty, 4, Node(Empty, 5, Empty)))
val t5 = Node(Empty, 2, t1)
val t6 = Node(Empty, 4, Empty)
val t7 = Node(Empty, 2, Node(Empty, 4, Empty))
val t8 = Node(Node(Empty, 1, Empty), 2, Node(Empty, 3, Empty))
val t9 = Node(Empty, 2, Node(Empty, 3, Empty))
val t10 = Node(Node(Empty, 1, Empty), 2, Empty)
val t11 = Node(Node(Empty, 1, Empty), 3, Empty)