package src.graphrel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import src.file_man.ReadFile;


/**
 * Graph
 */
public class Graph {
  public static final String FILELOC = "./src/test.csv";
  ReadFile rf;
  // Map implmentation from https://www.baeldung.com/java-graphs
  // TODO: Fix this, for our current implmentation disregards Node creation (does not save the node)
  // TODO: perhaps create a uid to Node type of HashMap?
  // I'm not sure about memory usage with this implementation above
  private Map<Integer, ArrayList<Edge>> adjVertices;
  Map<Integer, ArrayList<Node>> adjNodes;
  Map<String, Integer> nameToUid;
  Map<Integer, Node> UidToNode;

  /**
   * Initialize Nodes and Edges within Graph constructor.
   */
  public Graph(){
    rf = new ReadFile();
    rf.initRead(FILELOC);
    adjVertices = new HashMap<>();
    nameToUid = new HashMap<>();
    UidToNode = new HashMap<>();
    initGraph();
  };

  /**
   * Initialize Graph with the values read from file the file given
   */
  private void initGraph() {
    for (int i = 0; i < rf.records.size(); i++) {
      List<String> row = rf.records.get(i);
      String nodeName = row.get(0);
      String nodeConn = row.get(1);
      int weight;
      try {
        weight = Integer.parseInt(row.get(2));
      } catch (NumberFormatException e) {
        weight = 0;
      }
      if (nameToUid.get(nodeName) == null) { // if uid or node is still not in adjVertices
        Node newNode = new Node(nodeName);
        addNode(newNode);
      }
      // checks if nodeConn (vertex destination) is not in adjVertices
      // then create that vertex and add it to adjVertices
      if (nameToUid.get(nodeConn) == null) {
        Node newNode = new Node(nodeConn);
        addNode(newNode);
      }
      addEdgeByUid(nameToUid.get(nodeName), nameToUid.get(nodeConn), weight);
      addEdgeByUid(nameToUid.get(nodeConn), nameToUid.get(nodeName), weight);
    }
  } 


  // public void addNode(Node n) {
  //   this.nodes.add(n);
  // }

  public void printAllNodes() {
    // for (Node node : nodes) {
    //   System.out.println(node.getName() + ": " + node.getUid() + " = " + node.getVal() + "\n");
    // }
    // for (List<String> row : rf.records) {
    //   for (String string : row) {
    //     System.out.print(string + " ");
    //   }
    //   System.out.println("\n");
    // }
    Iterator<Map.Entry<Integer, ArrayList<Edge>>> it = adjVertices.entrySet().iterator();
    while (it.hasNext()) {
      Integer key = it.next().getKey();
        System.out.print(UidToNode.get(key).getName());
    }
  }

  public void printNodeEdges() {
    Iterator<Map.Entry<Integer, ArrayList<Edge>>> it = adjVertices.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<Integer, ArrayList<Edge>> entry = it.next();
      Integer key = entry.getKey();
      ArrayList<Edge> edges = entry.getValue();
      System.out.print(UidToNode.get(key).getName() + "-> "); 
      for (Edge edge : edges) {
        System.out.print(UidToNode.get(edge.dest).getName() + "[" + edge.weight + "] ");
      }
      System.out.println();
    }
  }


  public void addNode(Node n) {
    nameToUid.putIfAbsent(n.getName(), n.getUid());
    UidToNode.putIfAbsent(n.getUid(), n);
    adjVertices.putIfAbsent(n.getUid(), new ArrayList<Edge>());
  }

  public void removeNode(Node n) {
    removeNodeConnections(n.getUid());
    adjVertices.remove(n.getUid());
    nameToUid.remove(n.getName());
    UidToNode.remove(n.getUid());
  }

  public void removeNodeByUid(int uid) {
    removeNode(UidToNode.get(uid));
  }

  public void removeNodeByName(String name) {
    removeNodeByUid(nameToUid.get(name));
  }


  public void addEdgeByUid(int from, int to, int weight) {
    Edge newEdge = new Edge(to, weight);
    adjVertices.get(from).add(newEdge);
  }

  // TODO: Add removal of Node will cause removal of its connection to other edges
  // perhaps iterate to the node (adjVertices) to retrieve nodes connected
  // remove connections from other nodes 
  public void removeNodeConnections(int uid) {
    ArrayList<Edge> connections = adjVertices.get(uid);
    // remove destination edge declaration
    for (Edge edge : connections) {
      adjVertices.get(edge.dest).removeIf(e -> e.dest == uid);
    }
  }
}
