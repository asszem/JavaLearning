package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

public class TryFlexibleBinaryTree {
  public static void main(String[] args) {

	//TODO fix. Why Manager is not accepted, while Manager class extends to person that implements comparable?
//	BinaryTree<Manager> people = new BinaryTree<>();

    Manager[] managers = { new Manager("Jane", 1), new Manager("Joe", 3),
                           new Manager("Freda", 3), new Manager("Albert", 2)};
    for(Manager manager: managers){
//      people.add(manager);
      System.out.println("Added "+ manager);
    }
    System.out.println();
//    listAll(people.sort());
  }

  // List the elements in any linked list
  public static void listAll(LinkedList<?> list) {
    for(Object obj : list) {
      System.out.println(obj);
    }
  }
}
