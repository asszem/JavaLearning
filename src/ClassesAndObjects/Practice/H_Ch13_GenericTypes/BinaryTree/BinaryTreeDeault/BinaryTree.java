package ClassesAndObjects.Practice.H_Ch13_GenericTypes.BinaryTree.BinaryTreeDeault;

public class BinaryTree<T extends Comparable<T>> {

  // Add a value to the tree
  public void add(T value) {
    if(root == null) {                                                 // If there's no root node
      root = new Node(value);                                          // store it in the root
    } else {                                                           // Otherwise...
      add(value, root);                                                // add it recursively
    }
  }

  // Recursive insertion of an object
  private void add(T value, Node node) {
    int comparison = node.obj.compareTo(value);
    if(comparison == 0) {                                              // If it is equal to the current node
      ++node.count;                                                    // just increment the count
      return;
    }
    if(comparison > 0) {                                               // If it's less than the current node
      if(node.left == null) {                                          // and the left child node is null
        node.left = new Node(value);                                   // Store it as the left child node
      } else {                                                         // Otherwise...
        add(value, node.left);                                         // ... call add() again at the left node
      }
    } else {                                                           // It must be greater than the current node
      if(node.right == null) {                                         // so it must go to the right...
        node.right = new Node(value);                                  // store it as the right node
      } else {                                                         // ...or when right node is not null
        add(value, node.right);                                        // ...call add() again at the right node
      }
    }
  }

  // Create a list containing the values from the tree in sequence
  public LinkedList<T> sort() {
    LinkedList<T> values = new LinkedList<>();                         // Create a linked list
    treeSort(root, values);                                            // Sort the objects into the list
    return values;
  }

  // Extract the tree nodes in sequence
  private void treeSort(Node node, LinkedList<T> values) {
    if(node != null) {                                                 // If the current node isn't null
      treeSort(node.left, values);                                     // process its left child node

      // List the duplicate objects for the current node
      for(int i = 0 ; i < node.count ; ++i) {
        values.addItem(node.obj);
       }
      treeSort(node.right, values);                                    // Now process the right child node
    }
  }

  private Node root;                                                   // The root node

  // Private inner class defining nodes
  private class Node {
    Node(T value) {
      obj = value;
      count = 1;
    }

    T obj;                                                             // Object stored in the node
    int count;                                                         // Count of identical objects
    Node left;                                                         // The left child node
    Node right;                                                        // The right child node
  }
}
