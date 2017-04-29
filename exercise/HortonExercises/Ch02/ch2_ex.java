package HortonExercises.Ch02;
public class ch2_ex {
    public static void main(String[] args) {
/*<editor-fold desc="Excercise 1. - byte variable multiplication, sign bit overflow">
        // Write a console program to defi ne and initialize a variable of type byte to 1, and then successively multiply
it by 2 and display its value 8 times. Explain the reason for the last result.

    byte bitValtozo = 0b1;
    int intValtozo = 1;
    
    for (int i = 1; i <= 8; i++) {
        bitValtozo *= 2; //each itaration multiplies bitValtozo with 2
        intValtozo *= 2; //intValtozo = intvaltozo * 2
        System.out.println("Iteration "+i+" bitValtozo erteke:" +bitValtozo+" intValtozo"+intValtozo);        
    }
 
    */
    //The reason for the last result is that a byte variable can hold only value -128 + 127
    //byte: 10000000 = - 128
    //byte: 00000001 = 1
    //byte: 01111111 = 127 (1x1+ 1x2 + 1x4 + 1x8 +1x16 + 1x32+ 1x64 )
    //The last byte (on the left) is the "sign bit". If it is 1 the number is negative, if it is 0 the number is positive
    //</editor-fold>
    
//<editor-fold desc="Exercise 2 - retrieving fractions as integrals using Math.pow"> 
/*
DONE Write a console program to declare and initialize a double variable with some value such as 1234.5678.
DONE Retrieve the integral part of the value and store it in a variable of type long,
DONE Retrieve the first four digits of the fractional part 
DONE and store them in an integer of type short. 
Display the value of the double variable by outputting the two values stored as integers.*/ 
    
/*
Integer (egész) variables:
byte - > short -> int -> long

Floating (tört) variables
float -> double

*/
/*
.1101
1.101

double tort = 1234.1608;
int tizedes = 4;
long egeszResz = (long) tort;
//System.out.print(egeszResz);

double tortNyers = (tort - egeszResz); //csak a tört részeket meghagyva
double tortResz = (tort - egeszResz) * Math.pow(10, tizedes); //megszorozza a 10 annyiadik hatványával, ahány számból áll, így egésszé alakítva


System.out.println("Tört nyers = "+tortNyers);
double helyiErtek2;
int helyiErtek3;
for (int i=1; i<=tizedes;i++){
    double helyiErtek = tortNyers * Math.pow(10,i);
    int helyiEgesz = (int)helyiErtek;
    helyiErtek2 = helyiErtek - helyiEgesz;
    System.out.println("i="+i+" tört = "+tort+" * " + Math.pow(10,i) + " = " + helyiErtek + " és " +helyiErtek2);
    
    }
if (tortNyers * 10 <1){ //ha az adott tizedeshelyen nem 0 van
    System.out.println("Az első tizedesjegyen 0 van!");    
    int eleje = (int) egeszResz;
    String nullas = "0";
    double tortResz2 = tortNyers * Math.pow(10, tizedes);
    String vege = nullas + (int) tortResz2;
    System.out.println(eleje);
    System.out.println(vege);
} else {
    System.out.println("Az első tizedesjegy nem nulla.");
    int eleje = (int) egeszResz;
    int vege = (int) tortResz;
    System.out.println(eleje);
    System.out.println(vege);
}

//Simple cheat
double csalas = tort / Math.pow(10,tizedes);
double csalas2 = csalas * Math.pow(10,tizedes*2);
System.out.println(1-csalas+" és  "+csalas2);
/*
System.out.println(tortResz);
int eleje = (int) egeszResz;
int vege = (int) tortResz;
System.out.println(eleje);
System.out.println(vege);
*/
//</editor-fold>


//<editor-fold desc="Exercise 3 - TaxCalc">
    /*
    Write a program that defines a floating-point variable initialized with a dollar value for your income and a
    second floating-point variable initialized with a value corresponding to a tax rate of 35 percent.
    Calculate and output the amount of tax you must pay with the dollars and cents stored as separate integer values
    (use two variables of type int to hold the tax, perhaps taxDollars and taxCents).
    */

/*

//MySolution
float incomeA = 87562.34f;
float taxRateA = 35f;
double taxToPayA = incomeA*(taxRateA/100);
int taxDollarsA = (int)taxToPayA;
double taxCentsA = Math.round((taxToPayA - taxDollarsA)*100);
//1 dollar = 100 cent
System.out.println("taxToPay:"+ taxToPayA + "  taxDollars:"+taxDollarsA+" taxCents:"+ (int) taxCentsA);


//Solution:
 double income = 87562.34;
    double taxRate = 0.35;                                             // 35%  corresponds to 0.35
    double tax = income*taxRate;

    int taxDollars = (int)tax;
    int taxCents = (int)Math.round((tax - taxDollars)*Math.pow(10.0, 2.0));

    // Output the results:
    System.out.println("Gross income is $" + (int)income +"." + (int)Math.round((income - (int)income)*Math.pow(10.0, 2.0)));
    System.out.println("Tax to pay is " + taxDollars + " dollars and " + taxCents + " cents.");
*/
//</editor-fold>

//<editor-fold desc="Exercise 4. - Volume calculation">
    /*
    The diameter of the sun is approximately 865,000 miles.
    The diameter of Earth is approximately 7600
    miles. Use the methods in the class Math to calculate the following:
    The volume of Earth in cubic miles
    The volume of the sun in cubic miles
    The ratio of the volume of the sun to the volume of Earth
        */
    
double napAtmero = 865000.0;
double foldAtmero = 7600.0;
//Gömb térfogat számítás: V= (4/3)*Pi*r^3
//http://www.wikihow.com/Calculate-the-Volume-of-a-Sphere
//átmérő (d) a kör sugarának (r) kétszerese
double napTerfogat = (4.0/3.0)*Math.PI*Math.pow(napAtmero/2,3); //az átmérőt el kell osztani kettővel, hogy a sugarat kapjuk
double foldTerfogat = (4.0/3.0)*Math.PI*Math.pow(foldAtmero/2,3);
System.out.println("The volume of Earth is "+foldTerfogat+" cubic meters");
System.out.println("The volume of Sun is "+napTerfogat+" cubic meters");
System.out.println("The ratio of the volume of sun to the volume of Earth is "+ napTerfogat / foldTerfogat+"\n");


//Megoláds
double sunRad = 865000.0/2.0;                                      // Sun radius in miles is half the diameter
    double earthRad = 7600.0/2.0;                                      // Earth radius likewise
    double fourOverThree = 4.0/3.0;                                    // A convenient constant, 4/3
    double sunVol = 0;
    double earthVol = 0;
    double ratioVol = 0;

    // Find the volumes of earth and sun:
    earthVol = fourOverThree*Math.PI*Math.pow(earthRad,3);
    sunVol = fourOverThree*Math.PI*Math.pow(sunRad,3);

    // Find the ratio of their volumes:
    ratioVol = sunVol/earthVol;

    // Output the results:
    System.out.println("Volume of the earth is " + earthVol + " cubic miles");
    System.out.println("Volume of the sun is " + sunVol + " cubic miles");
    System.out.println("The sun's volume is " + ratioVol + " times greater than the earth's.");
/*
    You can declare several variables of the same type in a single statement if you wish,
    so we could write the definitions as:

     double sunRad = 865000.0/2.0, earthRad = 7600.0/2.0,
     double fourOverThree = 4.0/3.0;
     double sunVol = 0, earthVol = 0, ratioVol = 0;

   However, except in trivial cases it is better to declare variables in separate statements
   as this is clearer and reduces the possibility of errors.
*/
    
    
    
    
//</editor-fold>

    }
    
}
