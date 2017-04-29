package PracticeAndTry.Practice;

 public class classLearning {
	 public String toString(){
		System.out.println("alma");
		 return "Hát kérem ennek az OBJEKTUMNAK a neve, hogy ";
	 }
    int nonStaticInstanceVariable1;
    int nonStaticInstanceVariable2;
    int nonStaticInstanceVariable3;
    int nonStaticInstanceVariable4;
    
    static int classVariable1;
            
    //STATIC Initialization Block
        static int[] initStaticArray = new int[10];                 //creates a new array for 10 elements, without any value
        static {                                                    // Static Initialization block
            System.out.println("Running STATIC initialization block.");
            for(int i = 0 ; i < initStaticArray.length ; ++i) {
                initStaticArray[i] = (int)(100.0*Math.random());        //assign a random number for each element in the array
            }
        }

        void listStaticInitializedObjectValues() {                                         // List values in the array for an object
            System.out.println("Calling a non-static method to print object-specific STATIC initialization values ");
            for(int value : initStaticArray) {
                System.out.print(value+" ");                      // Display values
            }
            System.out.println(); // Start a new line
        }
        
//        static void listStaticInitialisedValues() {                                         // List values in the array for an object
//            System.out.println("Printing static initialization values from a static method"); 
//            for(int value : initStaticArray) {
//                System.out.print(value+" ");                      // Display values
//            }
//            System.out.println(); // Start a new line
//        }
    
    //Non-Static Initialization Block
        int[] initNonStaticArray = new int[10];                 //creates a new array for 10 elements, without any value
        {                                                    // non-Static Initialization block
            System.out.println("Running NON-static initialization block.");
            for(int i = 0 ; i < initNonStaticArray.length ; ++i) {
                initNonStaticArray[i] = (int)(100.0*Math.random());        //assign a random number for each element in the array
            }
        }
        void listNonStaticInitializedObjectValues() {                                         // List values in the array for an object
            System.out.println("Calling a non-static method to print object-specific NON-static initialization values ");
            for(int value : initNonStaticArray) {
                System.out.print(value+" ");                      // Display values
            }
            System.out.println(); // Start a new line
        }
    
    
    public classLearning (int parameterInput1, int parameterInput2) {
        nonStaticInstanceVariable1 = parameterInput1;
        nonStaticInstanceVariable2 = parameterInput2;
    }
     public classLearning (int parameterInput1, int parameterInput2,int parameterInput3, int parameterInput4) {
        nonStaticInstanceVariable1 = parameterInput1;
        nonStaticInstanceVariable2 = parameterInput3;
    }
    
    public static void main(String[] args) {
       // listStaticInitialisedValues();   //Method can be called without any object because it is static
        // class            object         assingment  constructor  arguments
        classLearning newClassLearningObject = new classLearning(21,42);
        System.out.println("\nfirst object:");
        newClassLearningObject.listStaticInitializedObjectValues();
        newClassLearningObject.listNonStaticInitializedObjectValues();
        System.out.println(newClassLearningObject);
		
        classLearning newClassLearningObject2 = new classLearning(21,42);
        System.out.println("\nsecond object:");
        newClassLearningObject2.listStaticInitializedObjectValues();
        newClassLearningObject2.listNonStaticInitializedObjectValues();
    
        
    }
}
