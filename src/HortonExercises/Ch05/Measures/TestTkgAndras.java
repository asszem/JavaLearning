package HortonExercises.Ch05.Measures;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class TestTkgAndras {

	public static void test(int input) {
		tkgWeightAndras test = new tkgWeightAndras(input);
		System.out.println(test);
	}
	public static void test(int a, int b, int c) {
		tkgWeightAndras test = new tkgWeightAndras(a,b,c);
		System.out.println(test);
	}

	public static void main(String[] args) {
		//test(1010100);
		//test(1);
		//test(1000);
		//test(1500);
		//test(1,2,3);
		//test(2,2000,1000);
		tkgWeightAndras o1 = new tkgWeightAndras(100.1);
//		tkgWeightAndras o2 = new tkgWeightAndras(1,1500,900);
		System.out.println(o1);
////		System.out.println(o2);
////		System.out.println(o1.substract(o2));
//		System.out.println(o1.multiply(10));
//		System.out.println(o1.divide(10));
	}
}
