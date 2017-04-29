package PracticeAndTry.Practice;

/**
 *
 * @author Andras Olah (olahandras78@gmail.com)
 */
public class ReferenceNextObject {

	private ReferenceNextObject next; //egy változó, ami magát az osztályt tárolja
	private String objectName;

//Constructor
	public ReferenceNextObject(String name){
		objectName = name;
		next=null;
	}
//Method
	public void setNext(ReferenceNextObject thisToBeNext) {
		this.next = thisToBeNext; //magára az objektumra kell hivatkozni, NEM a köv objra hivatkozó változóra. Ez fogja az objektumot életben tartani, amíg a hivatkozó objektum is életben van. 
	}
	
	public ReferenceNextObject getNext(){
		return next;
	}
	public String toString(){
		return "Object name: "+objectName+"\nnext object of "+objectName+": "+next;
	}
	public static void main(String[] args) {
		ReferenceNextObject a = new ReferenceNextObject("a");
		ReferenceNextObject b = new ReferenceNextObject("b");
		ReferenceNextObject c = new ReferenceNextObject("c");
		a.setNext(b);
		System.out.println(a);  //a next változó végigmegy a teljes láncon!
		b.setNext(c);
		//c.setNext(a);  //Ekkor végtelen hurokbe kerül :D
//		System.out.println("a.next: "+a.next);
//		System.out.println("b.next: "+b.next);
//		System.out.println("c.next: "+c.next);

		System.out.println(a.getNext());
	}
}
