package HortonExercises.Ch03;

public class ch3_ex2 {
	public static void main(String[] args) {
		/*
	When testing whether an integer is a prime, it is suf cient to try to divide by integers up to and including
the square root of the number being tested. Rewrite the program example from this chapter to use this
approach
		*/
	Outer:
	for (int i=2;i<=100;i++){
		for (int j=2;j<=(int)(Math.sqrt(i));j++){
			if (i%j==0){
				continue Outer;
			}
		}
			System.out.print(i+", ");
	}
	int test= (int) (Math.random() * 100);
	int sroot = (int)	(Math.sqrt(test));
		System.out.println("\nA tesztelt szám: "+test+"\nGyöke kerekítve: "+sroot);
	boolean isPrime=true;
	for (int i=2; i<=sroot;i++){
		if (test%i==0){
			System.out.println("A szám nem prímszám, mert osztója: "+i);
			isPrime=false;
			break;
		}
	}
		System.out.println("A szám "+(isPrime?"prím":"nem prím"));
		
	}
}
