package csintsy.graphrel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import csintsy.file_man.ReadFile;


/**
 * Graph
 */
public class Graph {
  public static final String FILENAME = "distances.csv";
  public static final String FILE_H = FILENAME.split("\\.")[0] + "_h.csv";
  ReadFile rf;
  // Map implmentation from https://www.baeldung.com/java-graphs
  private Map<Integer, ArrayList<Edge>> adjVertices;
  public Map<String, Integer> nameToUid;
  Map<Integer, Node> UidToNode;
  boolean hasHeuristicFile;

  /**
   * Initialize Nodes and Edges within Graph constructor.
   */
  public Graph(){
    // System.out.println("FILE_H: " + FILE_H);
    rf = new ReadFile();
    hasHeuristicFile = rf.initRead(FILE_H);            // read file with node and heuristic val first
    adjVertices = new HashMap<>();
    nameToUid = new HashMap<>();
    UidToNode = new HashMap<>();
    // printAllNodes();
    initGraph();
  };

  /**
   * Initialize Graph with the values read from file the file given
   */
  private void initGraph() {
    // read heuristic file first
    if (hasHeuristicFile) {
      for (List<String> row : rf.records) {
        String nodeName = row.get(0);
        int heuristicVal;
        try {
          heuristicVal = Integer.parseInt(row.get(1));
        } catch (NumberFormatException e) {
          heuristicVal = 0;
        }
        if (!nameToUid.containsKey(nodeName)) {
          Node newFromNode = new Node(nodeName, heuristicVal);
          addNode(newFromNode);
        }
      }
    }
    // read csv containing edges of node
    rf.initRead(FILENAME);

    for (List<String> row : rf.records) {
      if (row.size() < 3) continue;

      String fromNodeName = row.get(0);
      String toNodeName = row.get(1);

      int weight;
      try {
        weight = Integer.parseInt(row.get(2));
      } catch (NumberFormatException e) {
        weight = 0;
      }

      // int heuristic = 0;
      // if (row.size() >= 4) {
      //   try {
      //     heuristic = Integer.parseInt(row.get(3));
      //   } catch (NumberFormatException e) {
      //     heuristic = 0;
      //   }
      // }

      // Create or update FROM node
      if (!nameToUid.containsKey(fromNodeName)) {
        Node newFromNode = new Node(fromNodeName);
        addNode(newFromNode);
      }

      // Create to node
      if (!nameToUid.containsKey(toNodeName)) {
        Node newToNode = new Node(toNodeName, 0);
        addNode(newToNode);
      }

      // Add undirected edge
      int fromUid = nameToUid.get(fromNodeName);
      int toUid = nameToUid.get(toNodeName);
      addEdgeByUid(fromUid, toUid, weight);
      addEdgeByUid(toUid, fromUid, weight);
    }
  } 

  public void printAllNodes() {
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
    Node currentNode = UidToNode.get(key);

    System.out.print(currentNode.getName() + " (h=" + currentNode.getVal() + ") -> ");

    for (Edge edge : edges) {
      Node destNode = UidToNode.get(edge.destUid);
      System.out.print(destNode.getName() + "[" + edge.weight + "] ");
    }
    System.out.println();
  }
}

public String getUidToName(int uid) {
  return UidToNode.get(uid).getName();
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

  private int getHeuristic(String nodeName) { // might use this for computing the total cost
      return 0;
  }

  public void removeNodeConnections(int uid) {
    ArrayList<Edge> connections = adjVertices.get(uid);
    for (Edge edge : connections) {
      adjVertices.get(edge.destUid).removeIf(e -> e.destUid == uid);
    }
  }

  public ArrayList<Edge> getNodeEdges(int nodeUid) {
    return adjVertices.get(nodeUid);
  }

  public int getNumberOfNodes() {
    return this.UidToNode.size();
  }

  public Map<Integer, Node> getUidToNode() {
    return UidToNode;
  }

  //  A-STAR UPDATES

  public Integer getUidByName(String name) {
      return nameToUid.get(name);
  }

  public Node getNodeByUid(int uid) {
      return UidToNode.get(uid);
  }

  public Map<String, Integer> getNameToUid() {
      return nameToUid;
  }

}
