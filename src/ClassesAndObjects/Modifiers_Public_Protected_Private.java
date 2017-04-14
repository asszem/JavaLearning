/*
        	| Class | Package | Subclass | Subclass | World
            |       |         |(same pkg)|(diff pkg)| 
————————————+———————+—————————+——————————+——————————+————————
public      |   +   |    +    |    +     |     +    |   +     
————————————+———————+—————————+——————————+——————————+————————
protected   |   +   |    +    |    +     |     +    |   o     
————————————+———————+—————————+——————————+——————————+————————
no modifier |   +   |    +    |    +     |     o    |   o
————————————+———————+—————————+——————————+——————————+————————
private     |   +   |    o    |    o     |     o    |   o

+ : accessible
o : not accessible
source: http://stackoverflow.com/questions/215497/in-java-difference-between-default-public-protected-and-private


*/
package ClassesAndObjects;

/**
 * 
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class Modifiers_Public_Protected_Private {

}
