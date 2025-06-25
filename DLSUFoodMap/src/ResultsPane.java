import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class ResultsPane extends JPanel {

    private JLabel title, score, path, timeComp, memComp, optimality;

    public ResultsPane(String type) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.title = new JLabel("Search Alg " + type);
        this.score = new JLabel("Score:\n   0");
        this.path = new JLabel("Path:\n   ABCD");
        this.timeComp = new JLabel("Time Complexity:\n   0s");
        this.memComp = new JLabel("Memory Complexity:\n   0mp");
        this.optimality = new JLabel("Optimal:\n   Yes");
        this.setOpaque(false);

        this.add(title);
        this.add(score);
        this.add(path);
        this.add(timeComp);
        this.add(memComp);
        this.add(optimality);
    }

    public void UpdateTexts(int score, String path, String tc, String mc, String optimality){
        this.score.setText("Score:\n   " + score);
        this.path.setText("Path:\n   " + path);
        this.timeComp.setText("Time Complexity:\n   " + tc);
        this.memComp.setText("Memory Complexity:\n   " + mc);
        this.optimality.setText("Optimal:\n   " + optimality);
    }
}
