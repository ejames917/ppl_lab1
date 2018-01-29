# Lab 1 Writeup: Evan James and Carly Romig

## Question 1: Binding and Scope

### (a) Consider the following Scala code.
![alt text](https://github.com/ejames917/ppl_lab1/blob/master/images/p1.png)

The use of pi at line 4 is bound at which line? 

	3.14159 at line 3, because it is in the scope of the circumference function.

The use of pi at line 7 is bound at which line?

	3.14 on line 1, since pi is not defined in the function scope it uses the value definted in the global scope.

### (b) Consider the following Scala code.
![alt text](https://github.com/ejames917/ppl_lab1/blob/master/images/p2.png)

The use of x at line 3 is bound at which line? 

	Uses the value for x on line 2 in the function definition.

The use of x at line 6 is bound at which line? 

	Using the value defined on line 5.

The use of x at line 10 is bound at which line? 

	Using the value taken from line 5, which is defined in the match statement at line 3.

The use of x at line 13 is bound at which line?

	Since it is out of the scope of f(), it uses the value of x definted on line 1.

## Question 2: Typing
In the following, I have left off the return type of function g.The body of g is well-typed if we can come up with a valid return type. 

![alt text](https://github.com/ejames917/ppl_lab1/blob/master/images/p3.png)

Is the body of g well-typed?

If so, give the return type of g and explain how you determined this type.

	The body of g is well typed, because it returns a tuple of type ((Int, Int), Int). 
		a = Int
		b = Tuple (Int, Int)

		val(a,b): (Int, (Int,Int)) because
			a = 1: Int
			b = (x,3): tuple (Int, Int) because
				input x: Int
				3: int