package jsy.student

import jsy.lab1._

object Lab1 extends jsy.util.JsyApplication with jsy.lab1.Lab1Like {
  import jsy.lab1.Parser
  import jsy.lab1.ast._

  /*
   * CSCI 3155: Lab 1
   * <Evan James>
   *
   * Partner: <Carly Romig>
   * Collaborators: <Nate Ewan>
   */

  /*
   * Fill in the appropriate portions above by replacing things delimited
   * by '<'... '>'.
   *
   * Replace the '???' expression with your code in each function. The
   * '???' expression is a Scala expression that throws a NotImplementedError
   * exception.
   *
   * Do not make other modifications to this template, such as
   * - adding "extends App" or "extends Application" to your Lab object,
   * - adding a "main" method, and
   * - leaving any failing asserts.
   *
   * Your lab will not be graded if it does not compile.
   *
   * This template compiles without error. Before you submit comment out any
   * code that does not compile or causes a failing assert.  Simply put in a
   * '???' as needed to get something that compiles without error.
   */

  /*
   * Example: Test-driven development of plus
   *
   * A convenient, quick-and-dirty way to experiment, especially with small code
   * fragments, is to use the interactive Scala interpreter. The simplest way
   * to use the interactive Scala interpreter in IntelliJ is through a worksheet,
   * such as Lab1Worksheet.sc. A Scala Worksheet (e.g., Lab1Worksheet.sc) is code
   * evaluated in the context of the project with results for each expression
   * shown inline.
   *
   * Step 0: Sketch an implementation in Lab1.scala using ??? for unimmplemented things.
   * Step 1: Do some experimentation in Lab1Worksheet.sc.
   * Step 2: Write a test in Lab1Spec.scala, which should initially fail because of the ???.
   * Step 3: Fill in the ??? to finish the implementation to make your test pass.
   */

  //def plus(x: Int, y: Int): Int = ???
  def plus(x: Int, y: Int): Int = x + y

  /* Exercises */

  def abs(n: Double): Double = if (n<0) -n else n

  def xor(a: Boolean, b: Boolean): Boolean = {
    if ((a == true) & (b == false)) true
    else if ((b == true) & (a == false)) true
    else false}

  def repeat(s: String, n: Int): String = {
    require(n >= 0)
    if (n == 0) ""
    else if (n == 1) s
    else s.concat(repeat(s, n - 1))
  }
    //while (n > 0) {
      //var st = s + s;
      //val n = n - 1;
      //println(st);
    //}
  //}


  def sqrtStep(c: Double, xn: Double): Double = return (xn - ((xn*xn) - c)/(2*xn))

  def sqrtN(c: Double, x0: Double, n: Int): Double = {
    require(n >= 0)
    if (n == 0)x0
    else{
      var xy = sqrtStep(c, x0)
      sqrtN(c, xy, n-1)
    }
  }

  def sqrtErr(c: Double, x0: Double, epsilon: Double): Double = {
    require(c >= 0 & epsilon > 0)
    if(abs(x0*x0-c)<epsilon) x0
    else sqrtErr(c, sqrtStep(c, x0), epsilon)
  }

  def sqrt(c: Double): Double = {
    require(c >= 0)
    if (c == 0) 0 else sqrtErr(c, 1.0, 0.0001)
  }

  /* Search Tree */

  // Defined in Lab1Like.scala:
  //
  // sealed abstract class SearchTree
  // case object Empty extends SearchTree
  // case class Node(l: SearchTree, d: Int, r: SearchTree) extends SearchTree

  def repOk(t: SearchTree): Boolean = {
    def check(t: SearchTree, min: Int, max: Int): Boolean = t match {
      case Empty => true
      case Node(l, d, r) => if ((min <= d) && (d < max)) (check(l,min,d) && check(r,d,max)) else false
    }
    check(t, Int.MinValue, Int.MaxValue)
  }

  def insert(t: SearchTree, n: Int): SearchTree = {
    t match{
      case Empty => Node(Empty, n, Empty) //inserting first node into tree
      case Node(l, d, r) => if (n < d){    //if node is less than root
        Node(insert(l, n), d, r)         //return left subtree with n
      }
      else{
        Node(l, d, insert(r,n))         //return right subtree with n
      }
    }
  }

  def deleteMin(t: SearchTree): (SearchTree, Int) = {
    require(t != Empty)
    (t: @unchecked) match {
      case Node(Empty, d, r) => (r, d)
      case Node(l, d, r) =>
        val (l1, m) = deleteMin(l)
        (Node(l1, d, r), m)
    }
  }

  def delete(t: SearchTree, n: Int): SearchTree = t match {
    case Empty => Empty
    case Node(l,d,r) => {
      if (n<d) Node(delete(l, n), d, r)
      else if (n>d) Node(l, d, delete(r, n))
      else{
        if(r!=Empty) {
          val(r1,d1) = deleteMin(r)
          Node(l, d1, r1)
        }
        else l
        }
      }
  }

  /* JavaScripty */

  def eval(e: Expr): Double = e match {
    case N(n) => n
    case Unary(Neg, e)=> -eval(e)
    case Binary(Plus, e1, e2)=> eval(e1) + eval(e2)
    case Binary(Minus, e1, e2)=> eval(e1) - eval(e2)
    case Binary(Times, e1, e2)=> eval(e1)*eval(e2)
    case Binary(Div, e1, e2)=> {
      if (eval(e2) == 0) {
        if (eval(e1) > 0) Double.PositiveInfinity
        else if(eval(e1) < 0) Double.NegativeInfinity
        else Double.NaN
      } else eval(e1)/eval(e2)
    }
  }

 // Interface to run your interpreter from a string.  This is convenient
 // for unit testing.
 def eval(s: String): Double = eval(Parser.parse(s))



 /* Interface to run your interpreter from the command-line.  You can ignore the code below. */

 def processFile(file: java.io.File) {
    if (debug) { println("Parsing ...") }

    val expr = Parser.parseFile(file)

    if (debug) {
      println("\nExpression AST:\n  " + expr)
      println("------------------------------------------------------------")
    }

    if (debug) { println("Evaluating ...") }

    val v = eval(expr)

    println(prettyNumber(v))
  }

}
