package csintsy.pathfinding_algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import csintsy.graphrel.Edge;
import csintsy.graphrel.Graph;

/**
 * UniformCost Search Implementation 
 */
public class UniformCost {
    Graph g;
    PriorityQueue<Path> PQpaths;
    Set<Integer> visited;
    ArrayList<Edge> path;
    Path finalPath;
    int numOfNodes;
    int fromUid;
    int toUid;

    public UniformCost(Graph g) {
        this.numOfNodes = g.getNumberOfNodes();
        this.PQpaths = new PriorityQueue<Path>(g.getNumberOfNodes(), new PathComparator());
        this.g = g;
    }

    void dumpPQElem() {
        System.out.println("PQ Elements: " + PQpaths.size());
        if (!PQpaths.isEmpty()) {
            System.out.println("Head-Cost:" + PQpaths.peek().cost);
            System.out.println("Head: " + g.getUidToName(PQpaths.peek().getCurrentNodeUid()));
        } else {
            System.out.println("PQpaths is empty");
        }

        for (Path pth : PQpaths) {
            System.out.println("Path cost: " + pth.cost);
        }
    }

    public void dumpVisited() {
        System.out.println();
        System.out.print("Visited: ");
        if (visited != null) {
            for (Integer uid : visited) {
                System.out.print(g.getUidToName(uid) + " ");
            }
        }
        System.out.println();
    }

    public void printFinalPathInfo() {
        List<Integer> pathSeq = finalPath.getNodeSequence();

        System.out.println("Path: ");
        int e = pathSeq.get(pathSeq.size() - 1);
        for (Integer uid : pathSeq) {
            String name = g.getUidToName(uid);
            if (e == uid) {
                System.out.print(name);
            } else {
                System.out.print(name + " -> ");
            }
        }
        System.out.println();
        System.out.println("Total Cost: " + finalPath.getCost());
    }

    public void calcPath(int fromUid, int toUid) {
        this.fromUid = fromUid;
        this.toUid = toUid;

        // Clear and initialize data structures
        PQpaths.clear();
        visited = new HashSet<Integer>();

        // Start with initial path containing only the source node
        Path initialPath = new Path(fromUid);
        PQpaths.add(initialPath);

        while (!PQpaths.isEmpty()) {

            Path currentPath = PQpaths.poll();
            int currentNodeUid = currentPath.getCurrentNodeUid();

            // Check if goal is reached
            if (currentNodeUid == toUid) {
                this.finalPath = currentPath;
                return;
            }

            if (visited.contains(currentNodeUid)) {
                continue;
            }

            visited.add(currentNodeUid);

            // Get edges from current node
            ArrayList<Edge> nodeEdges = g.getNodeEdges(currentNodeUid);

            // Explore all neighbors
            if (nodeEdges != null) {
                for (Edge edge : nodeEdges) {
                    int neighborUid = edge.getdestUid();

                    if (!visited.contains(neighborUid)) {
                        // Create new path by extending current path
                        Path newPath = currentPath.clone().addEdge(edge);
                        PQpaths.add(newPath);

                    }                
                }
            }             
        }

        // No path found
        System.out.println("No path found from " + g.getUidToName(fromUid) + 
                " to " + g.getUidToName(toUid));
        finalPath = null;
        return;
    }
}
