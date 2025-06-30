package csintsy.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;

import csintsy.ResultsPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Appview extends JFrame{

    private JPanel mapPanel, boxPanel, resultsPanel;
    private JButton rstBtn, clrBtn, mapBtn, addBtn, delBtn, addEdgeBtn;
    private JCheckBox[] cBox;
    private JLabel map;
    private ResultsPane rA, rB;
    
    // Create a new class for the results tab? Since both sections will contain the same thing IDK

    private ArrayList<String> nodes;
    
    public Appview(ArrayList<String> nodes){

        this.nodes = new ArrayList<String>();

        this.nodes = nodes;

        initAV();
    }


    private void initAV() {
        int mapBoundX = 756;
        int mapBoundY = 423;
        // https://stackoverflow.com/questions/56387179/java-swing-maven-how-to-load-imageicon-in-jar-file
        ImageIcon ico = new ImageIcon(getClass().getResource("/images/Icon.png"));
        this.setIconImage(ico.getImage());
        // Create the Frame
        this.setTitle("DLSU Food Map");
        setPreferredSize(new Dimension(1239, 720));
        pack();
        // this.setSize(1239, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);  
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(58,79,60));

        mapPanel = new JPanel();
        mapPanel.setLayout(null);
        mapPanel.setBackground(Color.CYAN);
        mapPanel.setBounds(15, 15, mapBoundX, mapBoundY);
        
        Image img = new ImageIcon(getClass().getResource("/images/map.png")).getImage();                                     // This is the map image
        map = new JLabel(new ImageIcon(img.getScaledInstance(mapBoundX, mapBoundY, Image.SCALE_SMOOTH)));   // Scale down the map to fit within the panel
        map.setBounds(0, 0, mapBoundX, mapBoundY);
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

        rA = new ResultsPane("UCS");
        rA.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // added boarder to look like padding
        rB = new ResultsPane("A*");
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

    // class ImageOverlayPanel extends JPanel {}
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
    // public void updateBlindSearch(String text) {
    //     rA.UpdateTexts(0, text, "--", "--", "Yes");
    // }
    //
    // public void updateHeuristicSearch(String text) {
    //     rB.UpdateTexts(0, text, "--", "--", "Yes");
    // }

    public void updateBlindSearch(int score, String path) {
        rA.UpdateTexts(score, path);
    }

    public void updateHeuristicSearch(int score, String path) {
        rB.UpdateTexts(score, path);
    }

    // public void updateHeuristicSearch(int score, String path, String time, String memory, String optimal) {
    //     rB.UpdateTexts(score, path, time, memory, optimal);
    // }

    public void dumpNodes() {
        System.out.println("==Dumping all nodes==");
        for (String name : nodes) {
            System.out.println(name);
        }
    }

    public void updateBoxes(ArrayList<String> nodes){
        boxPanel.removeAll();

        cBox = new JCheckBox[this.nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            cBox[i] = new JCheckBox(this.nodes.get(i) + " | " + this.nodes.get(i));
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
