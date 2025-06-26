import javax.swing.*;
import java.awt.*;

public class ResultsPane extends JPanel {

    private JLabel title;
    private JTextArea text;

    public ResultsPane(String type) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.title = new JLabel(type);
        title.setFont(new Font("Dialog", Font.PLAIN, 25));
        title.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);  // Wrap at word boundaries
        text.setEditable(false);      // Make it read-only
        text.setOpaque(false);        // Optional: make it blend with background
        text.setFocusable(false);     // Optional: skip tab focus
        text.setBorder(null);         // Optional: remove borders
        text.setFont(new Font("Dialog", Font.PLAIN, 15));
        
        this.setOpaque(false);
        this.add(title);
        this.add(text);
    }

    public void UpdateTexts(int score, String path, String tc, String mc, String optimality){
        this.text.setText("Score:" + score + "\n\nPath: " + path + "\n\nTime Complexity: " + tc + "\n\nMemory Complexity: " + mc + "\n\nOptimal: " + optimality);
    }
}
