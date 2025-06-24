package src.graphrel;

import java.util.Scanner;

/**
 * main
 */
public class Main {
  public static void main(String[] args) {

    Graph currGraph = new Graph();
    // currGraph.addNode(new Node("A", 100));
    // currGraph.addNode(new Node("B", 200));

    Scanner myObj = new Scanner(System.in);

    while (true) {
      
      System.out.println("Graph Test: ");
      System.out.println("1. Print All Nodes");
      System.out.println("2. Print Node and Edges");
      System.out.println("3. Add Node");
      System.out.println("4. Remove Node");
      System.out.println("5. Exit");
      char cIn = myObj.next().charAt(0);
      switch (cIn) {
        case '1':
          System.out.println();
         currGraph.printAllNodes(); 
          System.out.println();
          break;
        case '2':
          System.out.println();
          currGraph.printNodeEdges();
          System.out.println();
          break;
        case '3':
          // too tedious to implement but it should be easy
          // a lot of menuing to ask for the user 
          // + Node Name
          // + Connections to
          // + Edge weight
          // possibly just create another menu to modify connections of a node
          // by printing all node and their edges connection
          break;
        case '4':
          System.out.println("Enter Node Name String: ");
          String nodeName = myObj.next();
          currGraph.removeNodeByName(nodeName);
          break;
        case'5':
          System.exit(0);
          myObj.close();
          break;

        default:
          break;
      }
      System.out.println();
    }
  }
}
