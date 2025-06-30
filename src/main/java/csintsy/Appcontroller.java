package csintsy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;
import csintsy.gui.Appview;
import csintsy.pathfinding_algo.AStar;
import csintsy.pathfinding_algo.UniformCost;

import javax.swing.JPanel;
import javax.swing.Box;

public class Appcontroller {

    private Appview av;
    // private Appmodel am;
    private Graph graph;
    AStar astar;
    UniformCost ucs;
    private ArrayList<String> nodes;

    public Appcontroller() {
        this.nodes = new ArrayList<String>();
        this.graph = new Graph();       // initialize Graph
                                        // this.am = new Appmodel();
        this.ucs = new UniformCost(graph);
        this.astar = new AStar(graph);

        graph.printNodeEdges();
        nodes.addAll(graph.getAllNodeNames()); // store node names in nodes
                                               // graph.printNodeEdges();
        this.av = new Appview(graph, nodes);
        // av.dumpNodes();

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

                do {
                    toNode.setText("");
                    fromNode.setText("");
                    foundTo = false;
                    foundFrom = false;

                    val = JOptionPane.showConfirmDialog(null, mapP, "Enter Node details", JOptionPane.OK_CANCEL_OPTION);
                    if (val == 0) {
                        // Check for invalid inputs
                        if((fromNode.getText().trim().length() == 1
                                    && fromNode.getText().trim().matches("[A-Z]{1}")) 
                                && (toNode.getText().trim().length() == 1 
                                    && toNode.getText().trim().matches("[A-Z]{1}"))) {   // Use regex to verify
                                                                                         // Check if nodes exist
                                                                                         //
                                                                                         // for(int i=0; i<nodes.size(); i++){
                                                                                         //     if(toNode.getText().trim() == nodes.get(i)){       // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                                                                         //         foundTo = true;
                                                                                         //         i = nodes.size(); // Force end the loop
                                                                                         //     }
                                                                                         // }
                                                                                         // foundFrom = graph.nameToUid(fromNode.getText());
                            String fromNodeStr = fromNode.getText().trim();
                            String toNodeStr = toNode.getText();

                            for(int i=0; i<nodes.size(); i++){
                                if(fromNode.getText().trim() == nodes.get(i)){       // Convert to char and compare with ID. *** Change this later to work with nodes ***
                                    foundFrom = true;
                                    i = nodes.size(); // Force end the loop
                                }
                            }

                            // checks if fromNode exists
                            if (graph.getUidByName(fromNodeStr) == null) {
                                JOptionPane.showMessageDialog(null, "ERROR: Node " + fromNodeStr + " not found", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            // checks if toNode exists
                            if (graph.getUidByName(toNodeStr) == null) {
                                JOptionPane.showMessageDialog(null, "ERROR: Node " + toNodeStr + " not found", "Error", JOptionPane.ERROR_MESSAGE);
                                break;
                            }

                            System.out.println("Calculating Path");
                            int fromUid = graph.nameToUid(fromNodeStr);
                            int toUid = graph.nameToUid(toNodeStr);
                            System.out.println("fromUid: " + fromUid + " toUid: " + toUid);
                            ucs.calcPath(fromUid, toUid);
                            astar.findPath(fromUid, toUid);
                            av.updateBlindSearch(ucs.getFinalCost(), ucs.getFinalPathSB().toString());
                            av.updateHeuristicSearch(astar.getFinalCost(), astar.getFinalPathSB().toString());

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
                // node id is not needed
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
                        String idFormStr = id.getText().trim();
                        System.out.println("idFormStr: " + idFormStr);
                        if(idFormStr.length() == 1 && idFormStr.matches("[A-Z]{1}")){   // Use regex to verify

                            // Check if duplicate
                            try {
                                // int idForm = Integer.parseInt(idFormStr);
                                if (graph.nameToUid(idFormStr) != null) {   // node already exists
                                    JOptionPane.showMessageDialog(null, "ERROR: Node already exists", "Error", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                break;
                            }


                                // Check for hVal
                                // Check if if integer input
                            
                            if (hVal.getText().matches("\\d+")) {
                                /*
                                 * Code to add to list goes here
                                 */

                                // Creates  a FoodSpot and adds it into the graph??
                                // nodes.add(new FoodSpot(name.getText().trim(), id.getText().trim().charAt(0), 1, 0, 0));
                                // graph.addFS(nodes.get(nodes.size()-1));
                                try {
                                    String StrhValForm = hVal.getText().trim();
                                    float hValForm = Float.parseFloat(StrhValForm);
                                    graph.addNode(new Node(name.getText(), hValForm));
                                    nodes.add(idFormStr);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    break;
                                }

                                av.updateBoxes(nodes);
                                JOptionPane.showMessageDialog(null, "Success! Added Node " + id.getText().trim() + " | " + name.getText().trim(), "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                                valid = true;
                                formVal = 1;

                            } else {
                                JOptionPane.showMessageDialog(null, "ERROR: Please enter a positive integer", "ERROR", JOptionPane.ERROR_MESSAGE);
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
                                                                    // stores node names to resto
                    resto[i] = nodes.get(i);
                } 
                String op = (String)JOptionPane.showInputDialog(null, "Which node to delete?", "Delete Node", JOptionPane.PLAIN_MESSAGE, null, resto, resto[0]);

                if((op != null) && (op.length() > 0)){  // Print if confirmed


                    int index = -1;
                    for(int i=0; i<nodes.size(); i++){
                        if(nodes.get(i) == op){
                            index = i;
                            i = nodes.size();
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Node " + op + " successfully deleted!", "", JOptionPane.PLAIN_MESSAGE);
                    graph.removeNode(graph.getNodeByUid(graph.getUidByName(op)));	// Delete node from graph
                    nodes.remove(index);											// Delete node from nodes array list
                    av.updateBoxes(nodes);											// Update check boxes
                }

                System.out.println("String op: " + op);
            }
        });

        this.av.addEdgeBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean exit;
                String strA, strB;
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
                    strA = "";
                    strB = "";
                    exit = false;
                    if(JOptionPane.showConfirmDialog(null, connectNode, "Enter Node details", JOptionPane.OK_CANCEL_OPTION) == 0){
                        // Check for data type
                        if((nodeA.getText().trim().length() == 1 && nodeA.getText().trim().matches("[A-Z]{1}")) && (nodeB.getText().trim().length() == 1 && nodeB.getText().trim().matches("[A-Z]{1}"))){
                            // check if Existing input
                        	
                        	strA = nodeA.getText().trim();
                        	strB = nodeB.getText().trim();

                            // Logic and error handling
                            if(graph.getUidByName(strA) != null) {
                                if(graph.getUidByName(strB) != null) {
                                    if(weight.getText().matches("\\d+")) {
                                        // add edge
                                        // graph.addEdge(nodeA.getText().trim().charAt(0), nodeB.getText().trim().charAt(0), Integer.parseInt(weight.getText()));
                                    	graph.addEdgeByUid(graph.getUidByName(strA), graph.getUidByName(strB), Integer.parseInt(weight.getText()));
                                    	graph.addEdgeByUid(graph.getUidByName(strB), graph.getUidByName(strA), Integer.parseInt(weight.getText()));
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
}
