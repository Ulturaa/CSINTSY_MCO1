package csintsy.pathfinding_algo;

import java.util.Comparator;

import csintsy.graphrel.Edge;

/**
 * PathComparator a custom Comparator for Path class used for PriorityQueue 
 * within pathfinding algorithms
 */
public class PathComparator implements Comparator<Path> {
    public int compare(Path a, Path b) {
        if (a.getCost() > b.getCost()) {
            return 1;
        } else if (b.getCost() > a.getCost()){
            return -1;
        } else {
            return 0;
        }
    }

}
