package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

public class Person implements Comparable<Person> {
  public Person(String name) {
    this.name = name;
  }

  public int compareTo(Person person) {
    if( person == this) {
      return 0;
    }
    return this.name.compareTo(person.name);
  }

  @Override
  public String toString() {
    return name;
  }

  protected String name;
}
