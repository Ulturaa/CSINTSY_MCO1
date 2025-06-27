package csintsy;

import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;
import csintsy.pathfinding_algo.UniformCost;
import csintsy.pathfinding_algo.AStar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main
 */
public class App {
  public static void main(String[] args) {

    Graph currGraph = new Graph();
    Scanner myObj = new Scanner(System.in);

    while (true) {
      System.out.println("Graph Test:");
      System.out.println("1. Print All Nodes");
      System.out.println("2. Print Node and Edges");
      System.out.println("3. Add Node");
      System.out.println("4. Remove Node");
      System.out.println("5. Exit");
      System.out.println("6. Run UCS Algorithm");
      System.out.println("7. Run A* Algorithm");
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
          boolean returnToMain = false;

          // too tedious to implement but it should be easy
          // a lot of menuing to ask for the user 
          // + Node Name
          // + Connections to
          // + Edge weight
          // possibly just create another menu to modify connections of a node
          // by printing all node and their edges connection

        // THIS FOR TESTING

        do {
                System.out.print("Enter Node Name: ");
                String newNodeName = myObj.next();

                System.out.print("Enter Heuristic value (estimate to goal): ");
                int heuristicVal = myObj.nextInt();

                currGraph.addNode(new Node(newNodeName, heuristicVal));
                System.out.println("Node '" + newNodeName + "' added successfully.");

                while (true) {
                    System.out.println("\nWhat would you like to do next?");
                    System.out.println("1. Connect this node to another");
                    System.out.println("2. Add another node");
                    System.out.println("3. Return to main menu");

                    char subOption = myObj.next().charAt(0);

                    if (subOption == '1') {
                        List<String> nodeNames = new ArrayList<>(currGraph.getNameToUid().keySet());

                        System.out.println("Available nodes:");
                        for (int i = 0; i < nodeNames.size(); i++) {
                            System.out.println(i + ". " + nodeNames.get(i));
                        }

                        System.out.print("Select node to connect to (by number): ");
                        int index = myObj.nextInt();
                        if (index < 0 || index >= nodeNames.size()) {
                            System.out.println("Invalid selection.");
                            continue;
                        }

                        String targetName = nodeNames.get(index);

                        Integer fromUid = currGraph.getUidByName(newNodeName);
                        Integer toUid = currGraph.getUidByName(targetName);

                        System.out.print("Enter edge weight: ");
                        int weight = myObj.nextInt();

                        currGraph.addEdgeByUid(fromUid, toUid, weight);
                        currGraph.addEdgeByUid(toUid, fromUid, weight);
                        System.out.println("Connected " + newNodeName + " ↔ " + targetName);
                    } else if (subOption == '2') {
                        break; // add another node
                    } else if (subOption == '3') {
                        returnToMain = true;
                        break;
                    } else {
                        System.out.println("Invalid option. Try again.");
                    }
                }

            } while (!returnToMain);

            break;
  

        case '4':
          System.out.println("Enter Node Name String: ");
          String nodeName = myObj.next();
          currGraph.removeNodeByName(nodeName);
          break;

        case '5':
          myObj.close();
          System.exit(0);
          break;
        case'6':
          UniformCost uCost = new UniformCost(currGraph);
          System.out.print("Enter node source: ");
          int source = currGraph.nameToUid.get(myObj.next());
          System.out.print("Enter destination node: ");
          int dest = currGraph.nameToUid.get(myObj.next());
          uCost.calcPath(source, dest);
          uCost.printFinalPathInfo();
        break;

        case '7':
          System.out.println("Enter START node name:");
          String fromName = myObj.next();
          System.out.println("Enter GOAL node name:");
          String toName = myObj.next();

          Integer fromUid = currGraph.getUidByName(fromName);
          Integer toUid = currGraph.getUidByName(toName);

          if (fromUid == null || toUid == null) {
            System.out.println("Invalid node name(s). Please try again.");
            break;
          }

          AStar astar = new AStar(currGraph);
          ArrayList<Integer> path = astar.findPath(fromUid, toUid);

          if (path.isEmpty()) {
            System.out.println("No path found between " + fromName + " and " + toName + ".");
          } else {
            System.out.println("A* Path: ");
            for (int uid : path) {
              String name = currGraph.getNodeByUid(uid).getName();
              System.out.print(name + " ");
            }
            System.out.println();
          }
          break;

        default:
          System.out.println("Invalid option. Try again.");
          break;
      }
      System.out.println();
    }
  }
}
