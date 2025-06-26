package csintsy.pathfinding_algo;

import java.util.Comparator;

import csintsy.graphrel.Edge;

/**
 * EdgeComparator a custom Comparator for Edge class used for PriorityQueue 
 * within pathfinding algorithms
 */
public class EdgeComparator implements Comparator<Edge> {
  public int compare(Edge a, Edge b) {
    if (a.getWeight() > b.getWeight()) {
      return 1;
    } else {
      return 0;
    }
  }

}
