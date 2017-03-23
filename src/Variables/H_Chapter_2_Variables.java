package Variables;
/**
@author Andras
{@link}
* @version 1.0
* @see alma
*/

/*
					bits			bytes			range
byte			8					1
short			16				2
int				32				4
long			64				8

float			32				4
double		64				8

char			16				2

123E8 = 123x10^8


1. If either operand is of type double, the other is converted to double before the operation is carried out.
2. If either operand is of type float, the other is converted to float before the operation is carried out.
3. If either operand is of type long, the other is converted to long before the operation is carried out.

//NaN meaning - Not a Number

Automatic Type Conversions in Assignments
byte→ short→ int→ long→ float→ double

Math Class
The methods that support various additional mathematical functions are implemented in the Math class as
static methods, so to reference a particular function you can just write Math and the name of the method
you want to use separated by a period.

 */
import static java.lang.Math.*; // Import static class members, így nem kell minden metódusnál kiírni.
import static java.lang.Math.PI; // Import PI - csak a PI metódust importálja

public class H_Chapter_2_Variables {
	//Enumeration    
	//This defines a new type, Day, for variables that can store only one or other of the values specified between the braces. Note that enum types cannot be local to a method (nem lehet a main-ben)

	enum Day {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	} //nem kell ; a végére!

	enum Emberek {
		Andras, Barni, Benő, Anikó
	} //A nagybetűvel kezdés konvenció

	enum Month {
		January, February, March, April, May, June,
		July, August, September, October, November, December
	}
	static Emberek csaladValtozo = Emberek.Benő; // static - method can be run without creating an instance (object) of the class containing the main method
//Variables
	int miles = 0, // One mile is 8 furlongs
					furlongs = 0, // One furlong is 220 yards
					yards = 0, // One yard is 3 feet
					feet = 0;
	double sunDistance = 1.496E8;
	float electronMass = 9E-28F;

	final int FEET_PER_YARD = 3; // Constant values
	final double MM_PER_INCH = 25.4; // that cannot be changed

	public static void main(String[] args) {
//		int a, b, c, d;
//		a = b = c = d = 1;
		double blo=4.323432;
		System.out.println("Stringgé alakítva: "+Double.toString(blo).substring(0, 4));

		int unicode=65;
		do {
			System.out.println("Unicode: "+unicode+" "+Character.toString((char) unicode++)+" :karakter");
		} while (unicode<120);
		String toUnicode="á";
		System.out.println(toUnicode+" unikód: ");
		Math.sqrt(23);
		System.out.println(Math.exp(3));
		int variableScope1;
		{
			int variableScope2;
			{
				variableScope1 = 1;
				variableScope2 = 2;
				int variableScope3;
			}//VariableScope3 dies here
		}//VariableScope2 dies here
		//variableScop1 lives up until the end of Main()

		Emberek cs2 = Emberek.Andras; //a cs2 változónak nem kell staticnak lennie, mert a main methodban lett létrehozva. 
		System.out.println("csaladValtozo=" + csaladValtozo + " cs2= " + cs2 + " a kettő ugyanaz?" + (csaladValtozo == cs2 ? " Igen!" : " Nem!"));
		switch (cs2) {
			case Andras:
				break;
			case Anikó:
				break;
			case Barni:
				break;
			case Benő:
				break;
			default:
				throw new AssertionError();
		}

		char yesno = 'N';
		System.out.println("Yes lett kiválasztva? " + (Character.toLowerCase(yesno) == 'y' ? "igen" : "nem"));

		int count = 10;
		++count;    //Add 1 to count
		--count;    //Subtracts one from count
		count += 5; //Adds +5 to count
		count /= 2; //Divides count with 2
		int countTotal;
		countTotal = count++ + 10; //increments count AFTER using its value to Total

		//Casting
		short a = 10, b = 5, c = 0;
		c = (short) (a + b); //Bár a és b is short, de az összegük már nem fér el egy short változóban.
		//A cast után zárójelben lévő változóban lévő dologra vonatkozik a cast. 

		//Math Class arithmetical methods
		int kerekitesFel = (int) Math.ceil(a);
		double kerekitesLe = floor(c);
		System.out.println("A pí értéke:" + PI); //azért nem kell a Math.PI, mert be lett importálva a Math class az elején

		//Unicode
		char myCharacter = '\u00c3'; //www.unicode.org/    http://unicode-table.com/en/#control-character
		String alma = "Unicode \u00c3";
		System.out.println(myCharacter + alma);

		//Strings
		/*
        CHARACTER DESCRIPTION
        \b Backspace
        \f Form feed
        \n New line
        \r Carriage return
        \t Tab*    
		 */
		System.out.println("Teszt \\b ah\balma\t\t asdf");
	}

}
