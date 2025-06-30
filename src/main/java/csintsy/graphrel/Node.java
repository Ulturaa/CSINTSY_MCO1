package csintsy.graphrel;

/**
 * Node
 *
 */
public class Node {
  private int val;
  private static int idCounter = 0;
  private int uid; 
  private String name;
  private int x = 0; // initialize Heuristic val to 0


  /**
   * Class Constructor
   * @param name    Name of the node
   * @param nodeVal Value of the node (Heuristic)??
   */

  public Node(String name) {
    this.uid = idCounter++;
    this.name = name;
  }

  public Node(String name, int nodeVal){
    this.uid = idCounter++;
    this.val = nodeVal;
    this.name = name;
  }


  // Get methods
  // Can implemenet this method by possible having same string
  // or name for a node differing with its uid
  public int getUid() {
    return this.uid;
  }

  public String getName() {
    return this.name;
  }

  public int getVal() {
    return this.val;
  }

  // A STAR
public int getX() {
    return x;
}

public void setVal(int val) {
    this.val = val;
}

}
