package HortonExercises.Ch03;

public class ch3_ex4 {

	public static void main(String[] args) {
		/*
		Write a program to generate a random sequence of capital letters that does not include vowels
		 */
		//Unicode: 65-90	
		int random;
		String[] vowels = {"A", "E", "I", "O", "U"};
		boolean tiltottEgyezes;
		for (int i = 0; i < 20; i++) {
			do {
				tiltottEgyezes = false;
				random = (int) (Math.random() * 26 + 65);  //0-26 között + 65=
				for (String str : vowels) {
					if (str.equals(Character.toString((char) random))) {
						tiltottEgyezes = true;
					}
				}
			} while (tiltottEgyezes);
//			System.out.println("Véletlen szám:" + random + " véletlen karakter: " + Character.toString((char) random));
System.out.print(Character.toString((char)random)+" ");
		}
		System.out.println("És a gyári megoldás:");
		int counter = 0;                                                   // Counter for number of capitals generated.
    int numberToGenerate = 20;                                         // Number of capitals to generate.
    char symbol = 0;                                                   // Variable to store a random character.

    // While there are still letters to generate:
    while( counter < numberToGenerate ) {
      // Generate a random symbol between A and Z:
      // This relies on the fact that the codes for the letters are in a
      // contiguous sequence from 'A' to 'Z'. If we add 1 to 'A' we get the
      // code for 'B', if we add 2 we get 'C', and so on. Thus to create a
      // random capital letter from 'A' to 'Z' we can add a random integer
      // between 0 and 25 to 'A'.
      symbol = (char)(26*Math.random() + 'A');
      switch(symbol) {
        //Vowels ignored:
        case 'A':
        case 'E':
        case 'I':
        case 'O':
        case 'U':
          break;
        default:
          //Consonant displayed:
          System.out.print(symbol + " ");
          counter++;
          break;
      }
    }
	}

}
