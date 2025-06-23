import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class appview extends JFrame{

    private JPanel mapPanel, boxPanel, resultsPanel;
    private JButton rstBtn, clrBtn, mapBtn;
    private JLabel map;

    private foodSpot[] nodes;
    
    public appview(foodSpot[] nodes){

        this.nodes = nodes;

        ImageIcon ico = new ImageIcon("images/test.jpg");
        this.setIconImage(ico.getImage());
        // Create the Frame
        this.setTitle("DLSU Food Map");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);  
        this.setLayout(null);                // Change layout later
        this.getContentPane().setBackground(new Color(58,79,60));
        
        

        mapPanel = new JPanel();
        mapPanel.setLayout(null);
        mapPanel.setBackground(Color.CYAN);
        mapPanel.setBounds(15, 15, 756, 423);
        
        Image img = new ImageIcon("images/map.png").getImage();                                 // This is the map image
        map = new JLabel(new ImageIcon(img.getScaledInstance(756, 423, Image.SCALE_SMOOTH)));   // Scale down the map to fit within the panel
        map.setBounds(0, 0, 756, 423);
        mapPanel.add(map);

        boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(4, 5));
        boxPanel.setBackground(new Color(56,94,60));
        boxPanel.setBounds(15, 453, 600, 200);

        resultsPanel = new JPanel();
        resultsPanel.setLayout(null);
        resultsPanel.setBackground(Color.PINK);
        resultsPanel.setBounds(846, 15, 404, 650);
        
        JCheckBox[] cBox = new JCheckBox[this.nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            cBox[i] = new JCheckBox(this.nodes[i].getID() + " | " + this.nodes[i].getName());
            cBox[i].setFocusable(false);
            cBox[i].setOpaque(false);
            cBox[i].setForeground(Color.BLACK);
            cBox[i].setSelected(true);
            boxPanel.add(cBox[i]);
        }

        clrBtn = new JButton("Clear");
        //clrBtn.setBorder(new EtchedBorder(10)); //10 is the radius
        clrBtn.setForeground(Color.BLACK);
        clrBtn.setFocusable(false);
        clrBtn.setBounds(630, 453, 75, 30);

        rstBtn = new JButton("Reset");
        //rstBtn.setBorder(new EtchedBorder(10)); //10 is the radius
        rstBtn.setForeground(Color.BLACK);
        rstBtn.setFocusable(false);
        rstBtn.setBounds(630, 498, 75, 30);

        mapBtn = new JButton("Map");
        //mapBtn.setBorder(new EtchedBorder(10)); //10 is the radius
        mapBtn.setForeground(Color.BLACK);
        mapBtn.setFocusable(false);
        mapBtn.setBounds(630, 623, 75, 30);

        
        

        this.add(mapPanel);
        this.add(boxPanel);
        this.add(resultsPanel);
        this.add(clrBtn);
        this.add(rstBtn);
        this.add(mapBtn);
        this.setVisible(true);
    }
}
