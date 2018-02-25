package Java8Stuff.LambdaExpressions;

public class basicLambda {

	public static void main(String[] args) {
		InterFaceWithDifferentArguments instance = (i, s) -> System.out.println(i + " " + s);
		instance.methodWithDifferentArguments(42, "text");
	}
}

interface InterFaceWithDifferentArguments {

	void methodWithDifferentArguments(int number, String string);
}
