package csintsy.pathfinding_algo;

import java.util.*;

import csintsy.graphrel.Edge;
import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;

public class AStar {
    private Graph graph;
    // private 

    public AStar(Graph g) {
        this.graph = g;
    }

    public ArrayList<Integer> findPath(int startUid, int goalUid) {
        Map<Integer, Node> uidToNode = graph.getUidToNode();
        PriorityQueue<NodeRecord> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.fCost));
        Map<Integer, NodeRecord> allNodes = new HashMap<>();
        Set<Integer> closedSet = new HashSet<>();

        NodeRecord start = new NodeRecord(startUid, null, 0, heuristic(uidToNode.get(startUid)));
        openSet.add(start);
        allNodes.put(startUid, start);

        while (!openSet.isEmpty()) {
            NodeRecord current = openSet.poll();

            if (current.uid == goalUid) {
                return reconstructPath(current);
            }

            closedSet.add(current.uid);

            for (Edge edge : graph.getNodeEdges(current.uid)) {
                int neighborUid = edge.getdestUid();
                if (closedSet.contains(neighborUid)) continue;

                double tentativeG = current.gCost + edge.getWeight();

                NodeRecord neighbor = allNodes.getOrDefault(neighborUid,
                        new NodeRecord(neighborUid, null, Double.POSITIVE_INFINITY, 0));
                allNodes.putIfAbsent(neighborUid, neighbor);

                if (tentativeG < neighbor.gCost) {
                    neighbor.gCost = tentativeG;
                    neighbor.fCost = tentativeG + heuristic(uidToNode.get(neighborUid));
                    neighbor.parent = current;

                    openSet.remove(neighbor); // Important for PriorityQueue to re-order
                    openSet.add(neighbor);
                }
            }
        }

        return new ArrayList<>(); // No path found
    }

   private double heuristic(Node node) {
    return node.getVal();
    }

    private ArrayList<Integer> reconstructPath(NodeRecord goal) {
        ArrayList<Integer> path = new ArrayList<>();
        NodeRecord current = goal;
        while (current != null) {
            path.add(0, current.uid); // prepend
            current = current.parent;
        }
        return path;
    }

    private static class NodeRecord {
        int uid;
        NodeRecord parent;
        double gCost;
        double fCost;

        public NodeRecord(int uid, NodeRecord parent, double gCost, double fCost) {
            this.uid = uid;
            this.parent = parent;
            this.gCost = gCost;
            this.fCost = fCost;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof NodeRecord && ((NodeRecord) o).uid == this.uid;
        }

        @Override
        public int hashCode() {
            return Objects.hash(uid);
        }
    }
}
