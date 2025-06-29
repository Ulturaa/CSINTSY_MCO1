package csintsy.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import csintsy.graphrel.Edge;
import csintsy.graphrel.Graph;
import csintsy.graphrel.Node;

public class appview extends JFrame{

    private JPanel mapPanel, boxPanel, resultsPanel;
    private JButton rstBtn, clrBtn, mapBtn, addBtn, delBtn, addEdgeBtn;
    private JCheckBox[] cBox;
    private JLabel map;
    private ResultsPane rA, rB;
    
    // Create a new class for the results tab? Since both sections will contain the same thing IDK

    private ArrayList<Node> nodes;
    
    public appview(ArrayList<foodSpot> nodes){

        this.nodes = nodes;

        ImageIcon ico = new ImageIcon("images/Icon.png");
        this.setIconImage(ico.getImage());
        // Create the Frame
        this.setTitle("DLSU Food Map");
        this.setSize(1239, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);  
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(58,79,60));

        mapPanel = new JPanel();
        mapPanel.setLayout(null);
        mapPanel.setBackground(Color.CYAN);
        mapPanel.setBounds(15, 15, 756, 423);
        
        Image img = new ImageIcon("images/map.png").getImage();                                     // This is the map image
        map = new JLabel(new ImageIcon(img.getScaledInstance(756, 423, Image.SCALE_SMOOTH)));   // Scale down the map to fit within the panel
        map.setBounds(0, 0, 756, 423);
        mapPanel.add(map);

        boxPanel = new JPanel();
        boxPanel.setLayout(new FlowLayout());
        boxPanel.setBackground(new Color(56,94,60));
        boxPanel.setBounds(15, 453, 600, 200);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout());
        resultsPanel.setBackground(new Color(56,94,60));
        resultsPanel.setBounds(805, 15, 404, 650);
        
        updateBoxes(nodes);

        clrBtn = new JButton("Clear");
        clrBtn.setForeground(Color.BLACK);
        clrBtn.setFocusable(false);
        clrBtn.setBounds(630, 453, 75, 30);

        rstBtn = new JButton("Reset");
        rstBtn.setForeground(Color.BLACK);
        rstBtn.setFocusable(false);
        rstBtn.setBounds(720, 453, 75, 30);

        mapBtn = new JButton("Map");
        mapBtn.setForeground(Color.BLACK);
        mapBtn.setFocusable(false);
        mapBtn.setBounds(630, 623, 75, 30);

        addBtn = new JButton("Add");
        addBtn.setForeground(Color.BLACK);
        addBtn.setFocusable(false);
        addBtn.setBounds(630, 503, 75, 30);
        
        delBtn = new JButton("Delete");
        delBtn.setForeground(Color.BLACK);
        delBtn.setFocusable(false);
        delBtn.setBounds(720, 503, 75, 30);

        addEdgeBtn = new JButton("Add Edge");
        addEdgeBtn.setForeground(Color.BLACK);
        addEdgeBtn.setFocusable(false);
        addEdgeBtn.setBounds(630, 553, 90, 30);

        rA = new ResultsPane("Blind Search");
        rA.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // added boarder to look like padding
        rB = new ResultsPane("Heuristic Search");
        rB.setBorder(BorderFactory.createEmptyBorder(10,0,10,10));  // added boarder to look like padding
        resultsPanel.add(rA);
        resultsPanel.add(rB);

        this.add(mapPanel);
        this.add(boxPanel);
        this.add(resultsPanel);
        this.add(clrBtn);
        this.add(rstBtn);
        this.add(mapBtn);
        this.add(addBtn);
        this.add(delBtn);
        this.add(addEdgeBtn);
        this.setVisible(true);
    }

    /*
     * Method to update labels on the resultsPanel later
     */
    public void updateLabels() {
        
    }

    public void clearCB() {
        for(int i=0; i < cBox.length; i++){
            this.cBox[i].setSelected(false);
        }
    }

    public void resetCB() {
        for(int i=0; i < cBox.length; i++){
            this.cBox[i].setSelected(true);
        }
    }

    /*
     * Button listeners
     */

    public void clrBtnActionListener(ActionListener actionListener) {
		this.clrBtn.addActionListener(actionListener);
	}

    public void rstBtnActionListener(ActionListener actionListener) {
		this.rstBtn.addActionListener(actionListener);
	}

    public void mapBtnActionListener(ActionListener actionListener) {
		this.mapBtn.addActionListener(actionListener);
	}

    public void addBtnActionListener(ActionListener actionListener) {
		this.addBtn.addActionListener(actionListener);
	}

    public void delBtnActionListener(ActionListener actionListener) {
		this.delBtn.addActionListener(actionListener);
	}

    public void addEdgeBtnActionListener(ActionListener actionListener) {
		this.addEdgeBtn.addActionListener(actionListener);
	}

    // hardcoded values for now
    public void updateBlindSearch(String text) {
        rA.UpdateTexts(0, text, "--", "--", "Yes");
    }

    public void updateHeuristicSearch(String text) {
        rB.UpdateTexts(0, text, "--", "--", "Yes");
    }

    public void updateBlindSearch(int score, String path, String time, String memory, String optimal) {
        rA.UpdateTexts(score, path, time, memory, optimal);
    }

    public void updateHeuristicSearch(int score, String path, String time, String memory, String optimal) {
        rB.UpdateTexts(score, path, time, memory, optimal);
    }

    public void updateBoxes(ArrayList<foodSpot> nodes){
        boxPanel.removeAll();

        cBox = new JCheckBox[this.nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            cBox[i] = new JCheckBox(this.nodes.get(i).getID() + " | " + this.nodes.get(i).getName());
            cBox[i].setFocusable(false);
            cBox[i].setOpaque(false);
            cBox[i].setForeground(Color.BLACK);
            cBox[i].setSelected(true);
            boxPanel.add(cBox[i]);
        }

        boxPanel.revalidate();;
        boxPanel.repaint();
    }
}
