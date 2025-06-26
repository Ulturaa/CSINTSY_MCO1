import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class appcontroller {

    private appview av;
    private appmodel am;
    private Graph graph;

    public appcontroller(foodSpot[] nodes) {
        this.am = new appmodel();
        this.av = new appview(nodes);
        this.graph = new Graph();

        for (foodSpot node : nodes) {
            graph.addFS(node);
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            graph.addEdge(nodes[i].getID(), nodes[i + 1].getID(), 1);
        }

        this.av = new appview(nodes);

        this.av.clrBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                av.clearCB();
            }
        });

        this.av.rstBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                av.resetCB();
            }
        });

        this.av.mapBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runAlgorithms();
            }
        });
    }

    // crap doesnt work properly yet, the start and end nodes still hardcoded
    private void runAlgorithms() {
        char start = 'A';
        char goal = 'U';

        // bfs
        long bfsStart = System.nanoTime();
        List<Character> bfsPath = am.bfs(graph, start, goal);
        long bfsEnd = System.nanoTime();
        long bfsDuration = bfsEnd - bfsStart;

        // aStar
        long aStarStart = System.nanoTime();
        List<Character> aStarPath = am.aStar(graph, start, goal);
        long aStarEnd = System.nanoTime();
        long aStarDuration = aStarEnd - aStarStart;

        // Memory usage
        int memoryUsed = (int) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;

        // Format paths to string without brackets and commas
        String bfsFormatted = String.join(" → ", bfsPath.stream().map(Object::toString).toArray(String[]::new));
        String aStarFormatted = String.join(" → ", aStarPath.stream().map(Object::toString).toArray(String[]::new));

        av.updateBlindSearch(
            bfsPath.size(),
            bfsFormatted,
            (bfsDuration >= 1_000_000 ? bfsDuration / 1_000_000 + " ms" : bfsDuration / 1_000 + " µs"),
            memoryUsed + " KB",
            "Yes"
        );

        av.updateHeuristicSearch(
            aStarPath.size(),
            aStarFormatted,
            (aStarDuration >= 1_000_000 ? aStarDuration / 1_000_000 + " ms" : aStarDuration / 1_000 + " µs"),
            memoryUsed + " KB",
            "Yes"
        );
    }
}
