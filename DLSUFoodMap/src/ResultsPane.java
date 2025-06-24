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

public class ResultsPane extends JPanel {

    private JLabel title, score, path, timeComp, memComp, optimality;

    public ResultsPane(String type) {
        this.setLayout(new FlowLayout());

        this.title = new JLabel("Search Alg " + type);
        this.score = new JLabel("Score: 0");
        this.path = new JLabel("Path: ");
        this.timeComp = new JLabel("Time Complexity: ");
        this.memComp = new JLabel("Memory Complexity: ");
        this.optimality = new JLabel("Optimal: Yes");
    }

    public void UpdateTexts(int score, String path, String tc, String mc, String optimality){
        this.score.setText("Score: " + score);
        this.path.setText("Path: " + path);
        this.timeComp.setText("Time Complexity: " + tc);
        this.memComp.setText("Memory Complexity: " + mc);
        this.optimality.setText("Optimal: Yes" + optimality);
    }
}
