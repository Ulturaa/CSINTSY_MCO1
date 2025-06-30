package csintsy.pathfinding_algo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

import csintsy.graphrel.Edge;

/**
 * Path 
 */
public class Path implements Cloneable {
    Queue<Edge> pth;
    int cost;
    int startNodeUid; 

    public Path(int startUid) {
        this.pth = new LinkedList<Edge>();
        this.startNodeUid = startUid;
        this.cost = 0;
    }

    public Path(Queue<Edge> pth, int cost, int startNodeUid) {
        this.pth = new LinkedList<Edge>(pth); 
        this.cost = cost;
        this.startNodeUid = startNodeUid;
    }

    public int getCost() {
        return cost;
    }

    public Path addEdge(Edge e) {
        this.pth.add(e);
        this.cost += e.getWeight();
        return this;
    }

    public Path clone() {
        Queue<Edge> clonedQueue = new LinkedList<Edge>();
        for (Edge edge : this.pth) {
            clonedQueue.add(new Edge(edge.getdestUid(), edge.getWeight()));
        }
        return new Path(clonedQueue, this.cost, this.startNodeUid);
    }

    // Get current node (where the path currently ends)
    public int getCurrentNodeUid() {
        if (pth.isEmpty()) {
            return startNodeUid; // If no edges, we're still at start
        }
        
        Edge[] edges = pth.toArray(new Edge[0]);
        return edges[edges.length - 1].getdestUid();
    }

    // Get start node
    public int getStartNodeUid() {
        return startNodeUid;
    }

    public List<Integer> getNodeSequence() {
        List<Integer> nodes = new ArrayList<>();
        nodes.add(startNodeUid); // Add start node
        
        // Add destination of each edge
        for (Edge edge : pth) {
            nodes.add(edge.getdestUid());
        }
        
        return nodes;
    }

    // Get path edges (the actual edges traversed)
    public Edge[] getPathEdges() {
        return pth.toArray(new Edge[0]);
    }

    // Get number of steps (edges) in the path
    public int getStepCount() {
        return pth.size();
    }

    // Check if path is empty (only contains start node)
    public boolean isEmpty() {
        return pth.isEmpty();
    }

    // Debugging purposes
    public void dumpPth() {
        System.out.println("\n=== PATH DETAILS ===");
        System.out.println("Start node: " + startNodeUid);
        
        if (pth.isEmpty()) {
            System.out.println("No edges (still at start)");
        } else {
            System.out.println("Edges traversed:");
            int step = 1;
            for (Edge edge : pth) {
                System.out.println("  Step " + step + ": -> " + 
                                 edge.getdestUid() + " (cost: " + edge.getWeight() + ")");
                step++;
            }
        }
        System.out.println("Total Cost: " + cost);
        System.out.println("Current node: " + getCurrentNodeUid());
    }

}

