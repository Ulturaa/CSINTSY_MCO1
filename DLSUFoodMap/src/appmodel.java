import java.util.*;

class appmodel {

    /*
        Data should be stored/manipulated
        But file reading can be done in the driver for ease and easier error handling.
        To sick to implement right now
    */

    /*
        Since foodSpot and node are different, Foodspot contains the name of the node (The name of the food place)
        Name of node = ID of foodSpot
        Node - The actual class used for the algorithms
        foodSpot - for the Map interface, visual clarity of the map
     */

    public List<Character> bfs(Graph graph, char start, char goal) {
        Queue<List<Character>> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        queue.add(new ArrayList<>(List.of(start)));

        while (!queue.isEmpty()) {
            List<Character> path = queue.poll();
            char current = path.get(path.size() - 1);

            if (current == goal) return path;
            if (visited.contains(current)) continue;

            visited.add(current);
            for (char neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    List<Character> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return List.of();
    }

    public List<Character> aStar(Graph graph, char start, char goal) {
        PriorityQueue<NodeRecord> open = new PriorityQueue<>(new Comparator<NodeRecord>() {
            public int compare(NodeRecord n1, NodeRecord n2) {
                return Integer.compare(n1.f, n2.f);
            }
        });

        Map<Character, Integer> gScore = new HashMap<>();
        Set<Character> closed = new HashSet<>();

        gScore.put(start, 0);
        open.add(new NodeRecord(start, new ArrayList<>(List.of(start)), 0, heuristic(graph, start, goal)));

        while (!open.isEmpty()) {
            NodeRecord current = open.poll();
            char currNode = current.node;

            if (currNode == goal) return current.path;
            if (closed.contains(currNode)) continue;
            closed.add(currNode);

            for (char neighbor : graph.getNeighbors(currNode)) {
                int tentativeG = current.g + graph.getCost(currNode, neighbor);
                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeG);
                    List<Character> newPath = new ArrayList<>(current.path);
                    newPath.add(neighbor);
                    int h = heuristic(graph, neighbor, goal);
                    open.add(new NodeRecord(neighbor, newPath, tentativeG, tentativeG + h));
                }
            }
        }

        return List.of();
    }

    private int heuristic(Graph graph, char current, char goal) {
        foodSpot a = graph.getFoodSpot(current);
        foodSpot b = graph.getFoodSpot(goal);
        int dx = a.getX() - b.getX();
        int dy = a.getY() - b.getY();
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    private static class NodeRecord {
        char node;
        List<Character> path;
        int g;
        int f;

        NodeRecord(char node, List<Character> path, int g, int f) {
            this.node = node;
            this.path = path;
            this.g = g;
            this.f = f;
        }
    }
}  
