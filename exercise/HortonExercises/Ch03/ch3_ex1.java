package HortonExercises.Ch03;

public class ch3_ex1 {
	enum Choices {ScrambledEgg, Wafes, Fruit, Cereal, Toast, Yogurt};
	public static void main(String[] args) {
	String[] valasztek = {"Scrambled Egg" , "Wafes", "Fruit", "Cereal", "Toast", "Yogurt"};
		/*Write a program to display a random choice from a set of six Choices for breakfast (you could use any
set — for example, scrambled eggs, wafes, fruit, cereal, toast, or yogurt)*/
		
		//Hány elem van a Choices enumbban?
		int numChoices=0;
		for (Choices i : Choices.values()){
			numChoices++;
		}
		int random = (int) (Math.random()*numChoices);
		System.out.println(valasztek[random]);
		//for (int i = 0; i < numChoices; i++) {
		//System.out.println(valasztek[i]);
		//}
	}
}
