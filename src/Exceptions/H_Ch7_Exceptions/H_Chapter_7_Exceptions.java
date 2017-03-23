/*
Exceptions in general
	One major benefit of having an error signaled by an exception is that it separates the code that deals with errors from the code that is executed when things are moving along smoothly
	An exception usually signals an error and is so called because errors in your Java programs are bound
to be the exception rather than the rule — by definition!

Checked exception
	You must include code in your program to deal with them; otherwise, your code does not compile

Unchecked exception
	You have the option of dealing with them or not

Exceptions are OBJECTS
	An exception in Java is an object that's created when an abnormal situation arises in your program
	Exceptions created by:
		- the JVM
		-standard library class methods
		- your application code

Exceptions are THROWN
	The object identifying the exceptional circumstance is TOSSED as an argument to a specific piece of program code that has been written specifically to deal with that kind of problem
	The code receiving the exception object as a parameter is said to CATCH it.

THROWABLE
	An exception is always an object of some subclass of the standard class Throwable.
	Error extends Throwable extends Object.

ERROR Subclass
	Unchecked exceptions

EXCEPTION subclass
	Checked exceptions (must be dealt with) - except for RuntimeExceptions, they are unchecked
	In methods whose code has the potential to throw a checked exception, you must 
		- either handle the checked exception within the method 
		- or register/indicate that your method may throw such an exception

Register - if don't know how to handle yet...
	- add the THROWS to the method signature
	- double myMethod() throws IOException, FileNotFoundException {}
	- This propagates if another method calls this method, it too must take account of the exceptions this method can throw. The calling method definition must either deal with the exceptions or declare that it can throw these exceptions as well
	
Handling exceptions - TRY, CATCH, FINALLY
	- Try block {} - encloses code that might throw an exception
	- Catch block {} - must immediately follow catch block, executed when exception is thrown
	- Finally block {} - will be always executed, regardles of execution (usefull for closing files)

TRY block
	Variable scope ends at the end of a try block!
	A try block is no diﬀerent to any other block between braces when it comes to variable scope. Variables declared in a try block are available only until the closing brace for the block

CATCH block
	The order of Catch blocks are important. 
	The catch blocks must be in sequence with the most derived type first and the most basic type last.
	Example: exceptions of type ArithmeticException could never reach the second catch block because they are always caught by the first:
		try {
		// try block code
		} catch(Exception e) {
		// Generic handling of exceptions
		} catch(ArithmeticException e) {
		// Specialized handling for these exceptions
		}
	In principle, if you're only interested in generic exceptions, all the error handling code can be localized in one catch block for exceptions of the superclass type.

	Catching multiple extensions 
		}catch(ArithmeticException | ArrayStoreException hiba){}

FINALLY
The primary purpose for the try block is to identify code that may result in an
exception being thrown. However, you can use it to contain code that doesn't throw
exceptions for the convenience of using a finally block. This can be useful when
the code in the try block has several possible exit points —break or return
statements, for example — but you always want to have a specific set of statements
executed after the try block has been executed to make sure things are tidied up,
such as closing any open fi les. You can put these in a finally block. Note that if a
value is returned within a finally block, this return overrides any return statement
executed in the try block.

If there's a return statement in the try block, this is executed immediately after the finally block completes execution — so this prevents the execution of any code following the finally block. A return statement in a finally block causes an immediate return to the calling point, and the code following the finally block isn't executed in this case.

Throwing own exceptions
	1. create class that extends to Throwable/Exceptions/Anything specific
		public class MyException extends Exceptions {}
	2. create constructors in own class with String parameter 
		public MyException(String hibaok){
			super(hibaok);
		}
	3. declare variable that refers to new exception object in method
		DreadfulProblemException e = new DreadfulProblemException(“Uh-Oh, trouble.”)
	4. throw the method
		throw e

Own Exception handling strategy
	1. Method catch a general exception (example: ArithmeticException)
	2. The catch block has code to analyze the exception to throw specific exceptions
	3. The calling code catches and handles the specific exceptions

Exception chainging
	Source: http://stackoverflow.com/questions/5800629/setting-exception-cause-in-java
	An Exception has the attributes message and cause. 
	The message is a description, telling a human reader more or less exactly, what went wrong.
	The cause is something different: it is, if available, another (nested) Throwable.
	catch(IOException e) {
	  throw new ApplicationException("Failed on reading file soandso", e);
	  //                              ^ Message                        ^ Cause
}

*/
package Exceptions.H_Ch7_Exceptions;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class H_Chapter_7_Exceptions {

}
