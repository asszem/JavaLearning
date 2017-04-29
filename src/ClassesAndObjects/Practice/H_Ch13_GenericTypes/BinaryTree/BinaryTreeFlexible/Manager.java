package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeFlexible;

public class Manager extends Person {

	protected int level;

	public Manager(String name, int level) {
		super(name);
		this.level = level;
	}

	@Override
	public String toString() {
		return "Manager " + super.toString() + " level: " + level;
	}

}
