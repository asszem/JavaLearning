/*Chapter 3 - Loops and Logic
You can use a boolean variable to store the result of a comparison
	boolean state = false; // Define and initialize the variable
	state = x - y < a + b; // Store the result of comparing x-y with a+b

Comparison operators are all of lower precedence than the arithmetic operators, so arithmetic operations are always completed before any comparisons

Simple if statement - note that {} is not required!
	if(number%2 != 0) // Test if number is odd
	  ++number; 				// If so make it even

Boolean operators
	& logical AND							- TRUE if both true
	&& conditional AND 				- does not bother to evaluate the right-hand operand if the left-hand operand is FALSE 
	| logical OR 							- TRUE if either OR both true
	|| conditional OR					- it omits the evaluation of the righthand operand when the left-hand operand is TRUE
	^ exclusive OR (XOR)		  - TRUE if either ONLY true
	! logical negation (NOT)  - the result is the INVERSE of the operand value - "nem igaz, hogy X"
														- state = true  ---->   !state = false   ---->  if (!state) = FALSE
														- state = false ---->   !state = true    ---->  if (!state) = TRUE 
	If the value of a boolean variable, state, is true then the expression !state has the value false, and if it is false, then !state evaluates to true

	Precedence
		The relational operators are executed before the && operator, so no parentheses are necessary
	 	&& has a higher precedence than ||

	Example When to use & instead of &&
		if(++value%2 == 0 & ++count < limit) { // Do something } - the right hand count increment will be executed regardless of left hand evaulation result

Character testing methods in Character class
	isDigit()			 			Returns the value true if the argument is a digit (0 to 9), and false otherwise.
	isLetter() 					Returns the value true if the argument is a letter, and false otherwise.
	isLetterOrDigit() 	Returns the value true if the argument is a letter or a digit, and false otherwise.
	isWhitespace() 			Returns the value true if the argument is whitespace, which is any one of the following characters:
												space (‘ ‘)
												tab (‘\t’)
												newline (‘\n’)
												carriage return (‘\r’)
												form feed (‘\f’)
												The method returns false otherwise.

Ternary or Conditional operator 
	logical_expression ? expression1 : expression2
	
	The value of older variable depends on the evaulation of yourAge>myAge:
	older = yourAge > myAge ? yourAge : myAge;

SWITCH statemen
	The expression that controls a switch statement can result in a value of type char, byte, short, or int, an enumeration constant, or a String object
	The default case is selected when the value of the switch expression does not correspond to any of the values for the other cases.
	When a break statement is executed here, it causes execution to continue with the statement following the closing brace for the switch.

SCOPE
	The scope of a LOCAL variable is the part of the program over which the variable name can be referenced — where you can use the variable in the program. 
	Class variables exist until the program is executing.

for (initialization:evaluation:iteration)
	multiple variables can used in initialization and multiple variables can be added to the iteration
	variables initialized inside the for loop live only until the end of the for loop
		for (int i = 1, j = 0; i <= limit; ++i, ++j) {
		for (int i = 1; i <= limit; ++i, sum += i) { // Wrong!!! - the iterations are evaulated from left to right.
		for (i=0; ; i++){  //infinite loop, as no expression is evaulated.

continue;
	Stops the current iteration of the inner loop.
	The continue statement can appear anywhere within a block of loop statements. You may even have more than one continue in a loop

labeled continue;
	Stops the iteration and continue at the beginning of the the next iteration where the label is
	You can use the labeled continue to exit from an inner loop to any enclosing outer loop, 
	not just the one immediately enclosing the loop containing the labeled continue statement.

break;
	Stops the loop immediately, and execution continueswith the first statement following the loop
	An indefi nite loop is a loop where the control condition is such that the loop apparently
	continues to execute indefi nitely. In this case, the mechanism to end the loop must be in the body of the loop.
	Example:
		waiting for keyboard input but don't know how many characters. Break if char='y'...

labeled break;
	A blokk ELEJÉN lévő címkét keresi meg és annak a blokknak a VÉGÉRE ugrik

 */
package LoopsAndLogic;

public class H_Chapter_3_LoopsAndLogic {

	enum Evszakok {
		tavasz, nyar, ősz, tél
	}

	public static void main(String[] args) {
		Evszakok aktualisEvszakValtozo = Evszakok.ősz;
		Evszakok kedvencEvszak = Evszakok.ősz;
		Evszakok november = aktualisEvszakValtozo;
		if (kedvencEvszak.equals(aktualisEvszakValtozo)) {
			System.out.println("Juhú, kedvenc hónapom van! " + aktualisEvszakValtozo);
		}

		boolean state = true;
		System.out.println("state= " + state);
		System.out.println("!state = " + !state);
		if (!state) { //ha a változó aktuális állapotával ellentétes érték az igaz. if !state=false, ami itt nem igaz, mert a !state = false.
			System.out.println("(!state = true) evaulated as TRUE");
		} else {
			System.out.println("(!state = true) evaulated as FALSE");
		}
		state = false;
		System.out.println("state= " + state);
		System.out.println("!state = " + !state);
		if (!state) { //A (kiértékelés) = TRUE abban az esetben, ha a benne lévő lévő kiértékelés eredménye TRUE. A változó alapértéke HAMIS, ennek ellentéte IGAZ, tehát a kiértékelés EZÉRT igaz. Úgy is le lehetne írni, hogy if !state = true
			System.out.println("(!state = true) evaulated as TRUE");
		} else {
			System.out.println("(!state = true) evaulated as FALSE");
		}

		//Terniary operator example in controlling what to print out.
		int nHats = 1; // Number of hats
		System.out.println("I have " + nHats + " hat" + (nHats == 1 ? "." : "s."));
		nHats++; // Increment number of hats
		System.out.println("I have " + nHats + " hat" + (nHats == 1 ? "." : "s."));

		state = true;
		System.out.println("Ha state=" + state + ", akkor if (!state) kiértékelése: " + (!state ? "igaz" : "hamis"));
		state = false;
		System.out.println("Ha state=" + state + ", akkor if (!state) kiértékelése: " + (!state ? "igaz" : "hamis"));

		int sum, limit;
		sum = limit = 0;
		for (int i = 1, j = 0; i <= limit; ++i, ++j) {
			sum += i * j; // Add the current value of i*j to sum
		}

		//Factorial számítás  3! = 1*2*3
		//to omit the calculation of factorials of odd numbers greater than 10.
		long fact = 20L; //Ennek keressük
		long factSum = 1L;
		for (int i = 1; i <= fact; i++) {
			if (i % 2 == 0 || i < 10) {
				System.out.println(i + "! " + factSum);
				factSum *= i + 1;
			}
		}

		int assertionTest = 10; //Run project with -enableassertions parameter !
		assert assertionTest == 1 : "Az assertion teszt elbukott";

		OuterLoopTest: //Continues the FOR from the NEXT iteration, not from the start!!!!!!!
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				continue OuterLoopTest;
			}
			System.out.print(i + ", ");
		}

		{
			labeledBreakTest:
			{
				int lbtest = 0;
				{
					if (++lbtest == 5) {
						break labeledBreakTest;
					}
				}
				System.out.println("Labeled Break never gets here");
			}
			System.out.println("Labeled Break jumps here");
		}
		int ennyiPrimet = 5;
		PrimOuterLoop:
		for (int i = 2;; i++) {  //infinite loop, as no expression is evaulated. Start from 2, 
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					continue PrimOuterLoop; //A szám nem prim, folytassa a külső for ciklust a következő i értékkel
				}
			}
			//Itt ellenőrzöm a kiléptetést, úgy, ha az i elérte az ennyiPrimet értékét
			System.out.println(i);
			if (--ennyiPrimet == 0) {
				break;
			}
		}
//		System.out.println(fact+"!="+factSum);
	}
}
