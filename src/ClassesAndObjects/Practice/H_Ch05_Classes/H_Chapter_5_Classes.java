package ClassesAndObjects.Practice.H_Ch05_Classes;
//<editor-fold desc="Chapter 5 - Defining Classes">

import ClassesAndObjects.Practice.H_Ch05_Classes.Sphere;

/*
TODO Read my own comments!

*********************************************************************************************
* Classes specify the objects you use in object-oriented programming.						*
* A class is a prescription for a particular kind of object — it defines a new type. 		*
* You use the definition of a class to create objects of that class type 					*
* that incorporate all the components specified as belonging to that class.					*
*********************************************************************************************

An object of a class is also referred to as an instance of that class. When you create an object, the object contains all the fields that were included in the class definition

Class definition = fields (data members = instance variables,static class variables) + methods (static class methods, instance methods)

CLASS variables vs INSTANCE variables
    1.1 - static Class variables - A given class has only one copy of each of its static fields or classvariables, and these are shared between and among all the objects of the class.
    1.2 - non-static Instance variables - Non-static fields, also called instance variables: Each object of the class has its own copy of each of the non-static fields or instance variables that appear in the class definition

CLASS methods vs INSTANCE methods
You can execute class methods even when no objects of a class exist, whereas instance methods can be executed only in relation to a particular object, so if no objects exist, you have no way to execute any of the instance methods defi ned in the class.

Referencing
    You can access a static member of a class using the class name, followed by a period, followed by the member name. 
        double rootPi = Math.sqrt(Math.PI);
    Instance variables and methods can be called only using an object reference, because by definition they relate to a particular object. 
        double ballVolume = ball.volume();
	Nonprimitive variables are references to object
	An object is an instance of a class
	Object can have multiple references

Methods
    Pass-by-value just means that for each argument value that you pass to a method, a copy of the value is made, and it is the copy that is passed to the method and referenced through the parameter name, not the original variable. 

Data sources for methods:
    1. Arguments passed to the method, which you refer to by using the parameter names
    2. Data members, both instance variables and class variables, which you refer to by their names
    3. Local variables that you declare in the body of the method
    4. Values that are returned by other methods that are called from within the method

Initialization blocks -  The static blocks execute when the class is loaded, and the non-static blocks execute when each object is created. 
    1 - A static initialization block is a block defined using the keyword static and is executed once when the class is loaded. A static initialization block can initialize only static data members of the class.
    2 - A non-static initialization block is executed for each object that is created and thus can initialize instance variables in a class.
    Any initialization blocks that you have defined in a class are always executed before a constructor

CONSTRUCTORS
    When you create an object of a class, a special kind of method called a constructor is always invoked. 
    The primary purpose of a constructor is to provide you with the means of initializing the instance variables uniquely for the object that is being created.
    Any initialization blocks that you have defined in a class are always executed before a constructor
    A constructor never returns a value, and you must not specify a return type — not even of type void.
    A constructor always has the same name as the class.

Pass By Reference
	When you pass an object as an argument to a method, the mechanism that applies is called pass-by-reference
	because a copy of the reference contained in the variable is transferred to the method, not a copy of the object itself.

Lifetime of an object
	The object survives as long as a variable still exists somewhere that references the object.

Autoboxing
	Pass values of a primitive type to a method that requires the argument to be a reference to an object. 
	The compiler supplies automatic conversions of primitive values to the corresponding class type when circumstances permit this

Access controlls (public, protected, private)
	For class members - only if class is Public!
					Same package Class Same package Subclass	Different package SubClass	Different package Class
	(no controll)		Y				Y						N							N
	public				Y				Y						Y							Y
	protected			Y				Y						Y							N
	private				N				N						N							N

Accessors (getters) and Mutators (setters)
	Public methods to get/set the values of private instance variables
	The main advantage of using a method in this way is 
	that you can apply validity checks on the new value that is to be set and prevent inappropriate values from being assigned

Access control best practices
	The variables in a public class should be private and the methods that are called from outside the class should be public.

Nested Classes







 */
//</editor-fold> 

//<editor-fold desc="examples">
/*Examples
In case that's too abstract, look back to the previous chapter where you used the String class. The String class is a comprehensive definition for a String object,with all the operations you are likely to need specified. Whenever you create a new String object, you are creating an object with all the characteristics and operations specified by the class definition. Every String object has all the methods that the String class defines built in. This makes String objects indispensable and string handling within a program easy

Use of Class variables
- One use for class variables is to hold constant values such as π that are common to all objects of the class. 
- Another use for class variables is to track data values that are common to all objects of a class and that need to be available even when no objects have been defined

Use of Class methods
The most common use for static methods is when you use a class just as a container for a bunch of utility methods, rather than as a specification for a set of objects. The mathematical functions that are implemented as class methods in the standard Math class are good examples
The Math class also contains some class variables containing useful mathematical constants such as e and π
 */
//</editor-fold>

/*Saját szavaimmal
Osztályt a CLASS Definition határoz meg. Ezek lesznek a tulajdonságai. Kétféle dolgot lehet megadni:
1. FIELD (adatok, data members)
    1.1 static, class variable = a változó értéke minden objektumban ugyanaz marad (pl. letrehozottObjektumokSzama változó ugyanaz minden objektumban)
    1.2 non-static, instance variable = a változó értéke objektumonként eltérhet (pl. jozsi.telefon és bela.telefon a telefon változó más a két objektumnál )
2. METHOD (műveletek)
    1.1 static class method - minden objektuomon és önmagában a main()-en belül is futtatható
    1.2 non-static instance method - csak egy adott objektuomon futtatható

Hivatkozás
Statikus változók/metódusok hivatkozása
    1. Másik public osztályban lévő PUBLIKUS statikus változóra vagy metódusra
        CsomagNev.OsztalyNev.metodusNev(Osztalynev.valtozoNev)
    2. A változó/metódust be lehet importálni és akkor nem kell az osztályt kiírni
        import static quicktest.ClassTest.classTestStaticMethod;   Itt Package.Osztály.Metódus 
Instance változók/metódusok hivatkozása csak objektumonlehet morzsa.vau();

Metódusok
    Egy metódus akárhányszor felhasználható a programban. A metódus meghívása a neve alapján történik. Végrehajtás után adhat vissza értéket, vagy csak lefut és végrehajtja a benne lévő utasításokat. Ha vissza ad értéket, meg kell határozni, hogy milyen típusú legyen

    return_type methodName (param1, param2, param3) {executable code in the body of the method}
    Paraméter = amit a metódus létrehozásánál a zárójelben megadok. Nem konkrét értékek, hanem változó típusok.
    Argument = amit a metódus meghívásánál a zárójelben megadok konkrét értékek

    Pass-By-Value mechanism - a metódus argumentumaként megadott változó értéke lesz használva a metódusban, nem maga a változó. 
        Például: int x = objektum.noveldEggyel(valtozo) esetében a NoveldEggyel metódus a változó értékéhez hozzáad egyet és ezt adja x-nek, de a változó ugyanaz marad. 

Inicializációs blokkok
    1. statikus inicializáló blokk -> csak egyszer fut le, amikor az osztály létrejön (minden objektum ugyanazt az értékét kapja)
            static {parancsok}
    2. nem-statikus inicializáló blokk -> minden egyes objektum létrehozása előtt lefut, így minden obj. más értéket kap. 
            {parancsok}

Konstruktorok
    A konstruktorok speciális metódusok, amik az objektumok létrehozására valók. 
    Paraméter nélkül (no-args) minden osztályban van, ekkor az eredetileg megadott változó értékeket kapják az objektumok.
    Ha specifikus konstruktort hozok létre, akárhány paramétert kaphat. Nem kell neki void és nem adhat Return-t.
    Mindenképp ugyanaz kell, hogy legyen a neve, mint az osztálynak.
    Ha vannak non-static inicializátorok, akkor azok MINDIG lefutnak a konstruktor kódja ELŐTT.
    Ha egyszer létrehoztam paraméteres konstruktort és mégis szükség lenne paraméter nélkülire, akkor azt is létre kell hozni, így:
        ClassName(){}
	Az objektum és a rá hivatkozó változó két KÜLÖNBÖZŐ entitás
		Spheres foci = new Spheres(); ----> a "foci" egy Spheres osztályú változó, ami hivatkozik egy objektumra, de nem az objektum maga.
		Spheres laszti = foci;		  ----> ez is tök ugyanarra az objektumra hivatkozik, csak a változó neve más
	Egy objektumra AKÁRHÁNY változó hivatkozhat!

Method (constructor) Overloading
    Lehet ugyanaz a neve több metódusnak, ha a signature-ük (paraméterek száma és publicitás) eltérnek. 

Konstruktorra hivatkozás konstruktorban
  Sphere(double theRadius, double x, double y, double z) {
      this(x, y, z); // Call the 3 argument constructor
      radius = theRadius; // Set the radius
  }

Új objektum léterhozása régi objektum paraméterként megadásával
  konstruktorNev (final Osztaly oldObject) {
    radius = oldObject.radius;
	} 
  konstruktorNev ujObjekt = new konstruktorNev(regiObject)

Csomagok
	A csomagok lényege, hogy megelőzze az osztálynevek keveredését. 
	Netbeans nem mutatja a teljes hierarchiát projekt nézetben, de a fájlok packagename.subpackage.subpackage mappastruktúrában vannak
 */
public class H_Chapter_5_Classes {

	static final double PI = 3.14;  //static class variable, final
	static int count = 0;           //static class variable used to count number of objects

	double xCenter;                 //non-static instance variables
	double zCenter;
	double yCenter;
	double radius;

	void metodus() {
	}                //non-static, instance method without returning any value

	int metodus2(int x) {
		return x;
	}  //non-static, instance method with one argument. The method returns the value(argument) provided for local variable x

	// Constructor - must be the same as the class name, no void and no RETURN specified!
	H_Chapter_5_Classes(double theRadius, double x, double y, double z) { //Constructor with parameteres to initialize  objects  with specific instance variables
		radius = theRadius;   // Set the radius
		xCenter = x;          // Set the coordinates of the center
		yCenter = y;
		zCenter = z;
		++count;              // Update object count
	}

	// no-args Constructor, if I create other constructors with parameteres,and still want to be able to create without params, i need to add this:
	// this is also an example of method/constructor overloading - method/constructor with same name but different parameters as signature
	H_Chapter_5_Classes() {
	}

	// Create a sphere from an existing object
	// Constructor to create a new object from an existing object
	H_Chapter_5_Classes(final H_Chapter_5_Classes oldObject) {
		radius = oldObject.radius;
		xCenter = oldObject.xCenter;
		yCenter = oldObject.yCenter;
		zCenter = oldObject.yCenter;
		++count; // Increment the object count
	}

	static int getCount() {       //static, class method, that returns the value of static class variable count.
		//int x = radius;           //non-static variable radius CAN NOT be referenced within a static method
		System.out.println("Runnning static int GetCount(). A count változó értéke:" + count);
		return count;
	}

	void changeRadius(double radius) { //non-static, instance method without returning any value, but changing the radius instance var of the selected object
		//The variable name by itself always refers to the variable that is local to the method, not the instance variable.
		this.radius = radius;       // Change the instance variable to the argument value
	}

	double volume() {                //non-static instance method without any parameters, which will return a Double value
		return 4.0 / 3.0 * Math.PI * Math.pow(this.radius, 3);      //Returns the volume of a given object using the object's instant variable RADIUS. 
		//THIS refers to the object on which the method is used. 
	}

	public static void main(String[] args) {
		for (String argumentumok:args){
		System.out.print(argumentumok);
		}
		Sphere foci;
		foci = new Sphere();  //don't forget to make the Sphere() constructor public!
		Sphere laszti = foci;


		System.out.println(String.valueOf(5 / 2.0));
		//<editor-fold desc="Saját példák és gyakorlás">
//Objektumra változóban hivatkozás
		H_Chapter_5_Classes objektumTeszt;   // Létrehoz egy objektumTeszt változót, de ennek még nincs értéke és nem objektum, mert nem lett konstruktor hívva.
		objektumTeszt = new H_Chapter_5_Classes(1000, 100, 10, 1); //Ekkor hozza létre az objektumot.
		//    System.out.println(objektumTeszt.radius);
		H_Chapter_5_Classes masodikObjektumValtozo = objektumTeszt;     //Itt nem új objektum lett létrehozva, ahhoz mindig new kell, hanem változó, ami az objektumra mutat.
		System.out.println("masodikObjektumValtozo a teszt objektum radius instance változójára hivatkozik:" + masodikObjektumValtozo.radius);
		masodikObjektumValtozo.radius += 22;
		System.out.println("objektumTeszt.radius értéke: " + objektumTeszt.radius);
		System.out.println("masodikObjektumValtozo.radius értéke:" + masodikObjektumValtozo.radius);
		System.gc(); //Garbage collection
// 	  getCount();
//    System.out.print("getCount() kiíratva: "+getCount());
//    H_Chapter_5_Classes labdaObject = new H_Chapter_5_Classes();
//    labdaObject.changeRadius(50);
//    System.out.println(labdaObject.volume());
		//</editor-fold>
	}

}
