package csintsy.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.print.attribute.standard.Destination;
import javax.swing.Box;

import csintsy.graphrel.Edge;
import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;

public class appcontroller {

    private appview av;
    private appmodel am;
    private Graph graph;

    public appcontroller(ArrayList<Node> nodes) {
        this.am = new appmodel();
        this.av = new appview(nodes);
        this.graph = new Graph();

        for (foodSpot node : nodes) {
            graph.addFS(node);
        }

        for (int i = 0; i < nodes.size() - 1; i++) {
            graph.addEdge(nodes.get(i).getID(), nodes.get(i+1).getID(), 1);
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

                // For reference: From = Start | To = Goal    
                Boolean foundTo, foundFrom;
                Boolean end = false;
                int val = 0;
                JTextField fromNode, toNode;
                JPanel mapP = new JPanel();
                mapP.add(new JLabel("Starting node: "));
                mapP.add(fromNode = new JTextField(3));
                mapP.add(Box.createHorizontalStrut(15));                 // spacer
                mapP.add(new JLabel("Destination node:"));
                mapP.add(toNode = new JTextField(3));

                do{
                    toNode.setText("");
                    fromNode.setText("");
                    foundTo = false;
                    foundFrom = false;

                    val = JOptionPane.showConfirmDialog(null, mapP, "Enter Node details", JOptionPane.OK_CANCEL_OPTION);
                    if(val == 0) {
                        // Check for invalid inputs
                        if((fromNode.getText().trim().length() == 1 && fromNode.getText().trim().matches("[A-Z]{1}")) && (toNode.getText().trim().length() == 1 && toNode.getText().trim().matches("[A-Z]{1}"))){   // Use regex to verify
                                // Check if nodes exist
                            for(int i=0; i<nodes.size(); i++){
                                if(toNode.getText().trim().charAt(0) == nodes.get(i).getID()){       // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                    foundTo = true;
                                    i = nodes.size(); // Force end the loop
                                }
                            }
                            for(int i=0; i<nodes.size(); i++){
                                if(fromNode.getText().trim().charAt(0) == nodes.get(i).getID()){       // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                    foundFrom = true;
                                    i = nodes.size(); // Force end the loop
                                }
                            }

                            if(foundFrom == true){
                                if(foundTo == true){
                                    runAlgorithms(fromNode.getText().trim().charAt(0), toNode.getText().trim().charAt(0)); // Run the algorithm here
                                    JOptionPane.showMessageDialog(null, "Route has been mapped!", "Success", JOptionPane.PLAIN_MESSAGE);
                                    val = 1;
                                    end = true;
                                } else
                                    JOptionPane.showMessageDialog(null, "ERROR: Node " + toNode.getText().trim() + " not found", "Error", JOptionPane.ERROR_MESSAGE);
                            }else 
                                JOptionPane.showMessageDialog(null, "ERROR: Node " + fromNode.getText().trim() + " not found", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR: Please input a character A-Z (Upper case only)", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        end = true;
                    }
                    
                } while(end == false || val == 0);
            }
        });

        this.av.addBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                    1. Option Pane pops up and requests for the name of node (ie ID; char value)
                    2. Verify that there are no duplicate IDs and that it is a valid character (A-Z only caps ig?)
                        2a. If there is an error, error pane pops up with the error (duplicate or invalid name). Closes error pane and new option pane displayed
                        2b. If no error, proceed
                    3. Prompt user for the heuristic value. Verify if valid value (positive integer)
                        3a. If not proper value (negative, float, charater, etc.) then post error and re-prompt user
                */
                String[] prompts = {"Connect this node to another", "Add another node", "Return to menu"};
                boolean valid, dupes;
                int formVal, nextVal;                                                  // Return value of the Option Panes
                JTextField name, id, hVal;
                JPanel newNode;

                newNode = new JPanel();
                newNode.add(new JLabel("Node ID: "));
                newNode.add(id = new JTextField(3));
                newNode.add(Box.createHorizontalStrut(15));                 // spacer
                newNode.add(new JLabel("Node Name: "));
                newNode.add(name = new JTextField(8));
                newNode.add(Box.createHorizontalStrut(15));                 // spacer
                newNode.add(new JLabel("Heuristic Value: "));
                newNode.add(hVal = new JTextField(3));
                
                do{
                    id.setText("");
                    name.setText("");   // Clear text field
                    hVal.setText("");
                    valid = false;
                    dupes = false;
                    
                    formVal = JOptionPane.showConfirmDialog(null, newNode, "Enter Node details", JOptionPane.OK_CANCEL_OPTION);

                    if(formVal == 0) {
                        // Check for invalid inputs
                        if(id.getText().trim().length() == 1 && id.getText().trim().matches("[A-Z]{1}")){   // Use regex to verify
                            
                            // Check if duplicate
                            for(int i=0; i<nodes.size(); i++){
                                if(id.getText().trim().charAt(0) == nodes.get(i).getID()){                                                // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                    JOptionPane.showMessageDialog(null, "ERROR: Node already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                    dupes = true;
                                    i = nodes.size(); // Force end the loop
                                }
                            }

                            if(dupes == false){
                                // Check for hVal
                                // Check if if integer input
                                if(hVal.getText().matches("\\d+")) {
                                    /*
                                     * Code to add to list goes here
                                     */

                                    nodes.add(new foodSpot(name.getText().trim(), id.getText().trim().charAt(0), 1, 0, 0));
                                    graph.addFS(nodes.get(nodes.size()-1));
                                    av.updateBoxes(nodes);
                                    JOptionPane.showMessageDialog(null, "Success! Added Node " + id.getText().trim() + " | " + name.getText().trim(), "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                                    valid = true;
                                    formVal = 1;

                                } else {
                                    JOptionPane.showMessageDialog(null, "ERROR: Please enter a positive integer", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "ERROR: Please input a character A-Z (Upper case only)", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        
                    } else {
                        valid = true;
                        formVal = 1;
                    }

                    
                }while(valid == false || formVal == 0);
            }
        });

        this.av.delBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] resto = new String[nodes.size()];          // Create array
                for(int i=0; i<nodes.size(); i++){                  // Fill array for combobox input
                    resto[i] = nodes.get(i).getName();
                } 
                String op = (String)JOptionPane.showInputDialog(null, "Which node to delete?", "Delete Node", JOptionPane.PLAIN_MESSAGE, null, resto, resto[0]);
                
                if((op != null) && (op.length() > 0)){  // Print if confirmed
                    int index = -1;
                    for(int i=0; i<nodes.size(); i++){
                        if(nodes.get(i).getName() == op){
                            index = i;
                            i = nodes.size();
                        }
                    }
                    graph.removeFS(nodes.get(index).getID());
                    nodes.remove(index);
                    av.updateBoxes(nodes);
                }
            }
        });

        this.av.addEdgeBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean foundA, foundB, exit;
                int nextVal;
                JTextField nodeA, nodeB, weight;
                JPanel connectNode;
                
                connectNode = new JPanel();
                connectNode.add(new JLabel("Node A: "));
                connectNode.add(nodeA = new JTextField(3));
                connectNode.add(Box.createHorizontalStrut(15));                 // spacer
                connectNode.add(new JLabel("Node B: "));
                connectNode.add(nodeB = new JTextField(3));
                connectNode.add(Box.createHorizontalStrut(15));                 // spacer
                connectNode.add(new JLabel("Weight: "));
                connectNode.add(weight = new JTextField(3));

                do{
                    nodeA.setText("");
                    nodeB.setText("");
                    weight.setText("");
                    exit = false;
                    foundA = false;
                    foundB = false;

                    if(JOptionPane.showConfirmDialog(null, connectNode, "Enter Node details", JOptionPane.OK_CANCEL_OPTION) == 0){
                        // Check for data type
                        if((nodeA.getText().trim().length() == 1 && nodeA.getText().trim().matches("[A-Z]{1}")) && (nodeB.getText().trim().length() == 1 && nodeB.getText().trim().matches("[A-Z]{1}"))){
                            // check if Existing input
                            for(int i=0; i<nodes.size(); i++){
                                if(nodeA.getText().trim().charAt(0) == nodes.get(i).getID()){       // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                    foundA = true;
                                    i = nodes.size(); // Force end the loop
                                }
                            }

                            for(int i=0; i<nodes.size(); i++){
                                if(nodeB.getText().trim().charAt(0) == nodes.get(i).getID()){       // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                    foundB = true;
                                    i = nodes.size(); // Force end the loop
                                }
                            }

                            // Logic and error handling
                            if(foundA == true) {
                                if(foundB == true) {
                                    if(weight.getText().matches("\\d+")) {
                                        graph.addEdge(nodeA.getText().trim().charAt(0), nodeB.getText().trim().charAt(0), Integer.parseInt(weight.getText()));
                                        JOptionPane.showMessageDialog(null, "Success! Added edge from " + nodeA.getText() + " to " + nodeB.getText(), "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                                        // If user Selects yes, nothing happens then reset. If User selects no, exit = true and exists.
                                        if(JOptionPane.showConfirmDialog(null, "Would you like to add another node", null, JOptionPane.YES_NO_OPTION) != 0)
                                            exit = true;
                                    } else
                                        JOptionPane.showMessageDialog(null, "ERROR: Weight must be a positive integer", "Error", JOptionPane.ERROR_MESSAGE);
                                } else
                                    JOptionPane.showMessageDialog(null, "ERROR: Node " + nodeB.getText().trim() + " not found", "Error", JOptionPane.ERROR_MESSAGE);
                            } else
                                JOptionPane.showMessageDialog(null, "ERROR: Node " + nodeA.getText().trim() + " not found", "Error", JOptionPane.ERROR_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "ERROR: Please input a character A-Z (Upper case only)", "Error", JOptionPane.ERROR_MESSAGE);
                        
                    } else {
                        exit = true;
                    }
                }while(exit == false);
            }
        });
    }

    // crap doesnt work properly yet, the start and end nodes still hardcoded
    private void runAlgorithms(char start, char goal) {

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
