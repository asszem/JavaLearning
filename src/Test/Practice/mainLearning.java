/*
In Java, every line of code that can actually run needs to be inside a class. 
This line declares a class named Main, which is public, that means that any other class can access it.
Notice that when we declare a public class, we must declare it inside a file with the same name (Main.java), otherwise we'll get an error when compiling.
 
When Java runs your program, the code inside of the main method is executed
*/
package Test.Practice;


public class mainLearning {
    public static void main(String[] args) {
    // public   - anyone can access it. Specifies that the method is accessible from outside the mainLearning class.
    // static   - method can be run without creating an instance (object) of the class containing the main method. 
    //              the method is a class method that is to be executable, even though no class objects have been created.
    //              main() can be called in the class without any object, because it is a static class method.
    // void     - method doesn't return any value
    // main()   - is the name of the method called and it will be executed.
    //      ()  - methods parameters are declared inside the ()
    // String[] - A String class is initialized, which will be an array [], holding multiple values
    // args     - The name of the String. Convention that it is args, but can be anything else. 
    
    //Why main must be static?
    /*
    Before an application starts execution, no objects exist, so to start execution, you need a method that is executable even though there are no objects around — a static method therefore.
    */
    }
}
