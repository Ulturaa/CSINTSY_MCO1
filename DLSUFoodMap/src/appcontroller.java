import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

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

                /*
                    --- Calling/running of algorithms should be done here ---
                    
                    1. Prompt user for Start and Goal nodes.
                        - Do proper error handling blah blah
                    2. If
                        2a. Path is empty
                            - JOptionPane displaying "No path found from Node A to Node B"
                            or
                            - Display "No path was found inside the results pane(?)"
                        2b. Path found
                            - Update the ResultsPane with the results of the algorithms (easy enough right?)
                */

                runAlgorithms();
            }
        });

        this.av.addBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*  Im too lazy to implement this shit

                1. Option Pane pops up and requests for the name of node (ie ID; char value)
                2. Verify that there are no duplicate IDs and that it is a valid character (A-Z only caps ig?)
                    2a. If there is an error, error pane pops up with the error (duplicate or invalid name). Closes error pane and new option pane displayed
                    2b. If no error, proceed
                3. Prompt user for the heuristic value. Verify if valid value (positive integer)
                    3a. If not proper value (negative, float, charater, etc.) then post error and re-prompt user
                4. Prompt user for new option
                    - A | Connect this node to another
                    - B | Add another node
                    - C | return to menu
                
                    4a. Connect to another node
                        - Prompt user for another node and its edge weight. Verify if it exists, else prompt error for input. Verify if valid weight (number).
                        - Update(add) edge for both edges
                        - Prompt success!
                    4b. Add another node
                        - Repeat from step 1.
                    4C. Return to menu
                        - Exit JOptionPane (return is not 0 or 1);
                */

                System.out.println("Added Record - Change later!");
            }
        });

        this.av.delBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] resto = new String[nodes.length];          // Create array
                for(int i=0; i<nodes.length; i++){                  // Fill array for combobox input
                    resto[i] = nodes[i].getName();
                } 
                String op = (String)JOptionPane.showInputDialog(null, "Which node to delete?", "Delete Node", JOptionPane.PLAIN_MESSAGE, null, resto, resto[0]);
                
                if((op != null) && (op.length() > 0)){  // Print if confirmed
                    System.out.println("Option dialogue: " + op);
                }
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
