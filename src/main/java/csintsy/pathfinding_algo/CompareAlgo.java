package csintsy.pathfinding_algo;

import java.util.ArrayList;

import csintsy.graphrel.Graph;

/**
 * CompareAlgo class compares UCS and AStar
 * by testing all possible pairs of starting and goal nodes
 * then printing out the number of paths that differ between
 * the two and its info (average rating for all nodes within the path)
 */

public class CompareAlgo {
    Graph graph;
    ArrayList<Integer> allNodes;
    UniformCost ucs;
    AStar aStar;

    public CompareAlgo(Graph g) {
        this.graph = g;
        this.ucs = new UniformCost(g);
        this.aStar = new AStar(g);
    }

    // TODO: Finish implementation
    public void compare() {

    }

}
