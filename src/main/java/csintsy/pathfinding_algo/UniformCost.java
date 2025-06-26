package csintsy.pathfinding_algo;

import java.util.*;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import csintsy.graphrel.Edge;
import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;

/**
 * UniformCost
 */
public class UniformCost {
  Graph g; // graph
  PriorityQueue<Integer> nodeUids; //pq
  Set<Integer> visited; //visited list
  ArrayList<Edge> path; //path cost
  int numOfNodes; //total num of nodes
  int fromUid; //source
  int toUid; //destination

  public UniformCost(Graph g) {
    this.g = g;
    this.numOfNodes = g.getNumberOfNodes();
  }

  
  public void calcPath(int fromUid, int toUid) {
    
    // this.fromUid = fromUid;
    // this.toUid = toUid;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {if (a[1] != b[1]) return Integer.compare(a[1], b[1]); return Integer.compare(a[0], b[0]);});//inside is uid, cost
    //sorted alphabetically and cost wise | it will return the int[] with lowest cost, if same, chooses lower uid which is smaller alphabet
    Set<Integer> visited = new HashSet<>();
    Map<Integer, Integer> parentMap = new HashMap<>(); //key = current uid, value = parent node uid
    Map<Integer, Integer> costingMap = new HashMap<>(); //key = uid, value = total cost
    
    
    //addUidsToPQ(); //insert all nodes to queue
    
    pq.add(new int[]{fromUid, 0}); 
    costingMap.put(fromUid, 0); //no cost to reach start node
    parentMap.put(fromUid, -1); //start has no parent

    while (!pq.isEmpty()){ //while there are nodes it keeps looping
      int[] current = pq.poll(); //removes the node with lowest cost
      int currentUid = current[0]; //uid of current id
      int currentCost = current[1]; //total cost 

      if (visited.contains(currentUid)) { //checks the current node thats visited and skips if it is
        continue;
      }

      visited.add(currentUid); //adds the current node to visited list
    
      if (currentUid == toUid) { //found goal
        break; //return the path and cost
      }

      Node node = g.getUidToNode().get(currentUid); //get node object from the graph
      if (node == null) continue; //skip if doesnt exist

      //main loop to check all cost of the same level
      for(Edge e : g.getNodeEdges(toUid)){
        int neighbor = e.getDest(); //get neighbor node
        int cost = e.getWeight() + currentCost; //

        if(!costingMap.containsKey(neighbor) || cost < costingMap.get(neighbor)){ //if cost < curr node cost / node dne
          costingMap.put(neighbor, cost);
          parentMap.put(neighbor, currentUid);
          pq.add(new int[]{neighbor, cost});
        }
      }

      //to get actual path
      List<Integer> pathing = new ArrayList<>(); //makes a path going back from destination to start (gonna use parent map)
      Integer cur = toUid; // starting from the current
      while(cur != -1){ //while cur is not -1
        pathing.add(cur); //adds the current node to pathing list
        cur = parentMap.get(cur); //gets the parent node
      }
    }

  // private void addUidsToPQ() {
  //    nodeUids.addAll(this.g.getUidToNode().keySet());
  // }

  }
}