package csintsy.pathfinding_algo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

import csintsy.graphrel.Edge;
import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;

/**
 * UniformCost
 */
public class UniformCost {
  Graph g;
  PriorityQueue<Integer> nodeUids;
  Set<Integer> visited;
  ArrayList<Edge> path;
  int numOfNodes;
  int fromUid;
  int toUid;

  public UniformCost(Graph g) {
    this.numOfNodes = g.getNumberOfNodes();
    this.nodeUids = new PriorityQueue<Integer>();
  }

  public calcPath(int fromUid, int toUid) {
    addUidsToPQ();
    this.fromUid = fromUid;
    this.toUid = toUid;

  }

  private addUidsToPQ() {
     = this.g.getUidToNode().keySet();
  }

}
