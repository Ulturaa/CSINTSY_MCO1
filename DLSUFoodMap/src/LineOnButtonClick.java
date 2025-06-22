import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineOnButtonClick {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LineOnButtonClick().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Draw Lines on Button Click");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null); // absolute positioning

        // Custom drawing panel
        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBounds(100, 100, 300, 250);
        frame.add(drawingPanel);

        // Search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(200, 370, 100, 30);
        frame.add(searchButton);

        // Button action: enable line drawing and repaint
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawingPanel.showLines = true;
                drawingPanel.repaint();
            }
        });

        frame.setVisible(true);
    }

    // Panel that handles drawing
    class DrawingPanel extends JPanel {
        Point pointA = new Point(40, 40);
        Point pointB = new Point(200, 100);
        Point pointC = new Point(80, 180);

        boolean showLines = false;

        public DrawingPanel() {
            setBackground(Color.LIGHT_GRAY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Draw points (gray)
            g2.setColor(Color.DARK_GRAY);
            drawPoint(g2, pointA, "A");
            drawPoint(g2, pointB, "B");
            drawPoint(g2, pointC, "C");

            if (showLines) {
                // Line A → B (Blue)
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(pointA.x, pointA.y, pointB.x, pointB.y);

                // Line B → C (Red)
                g2.setColor(Color.RED);
                g2.drawLine(pointB.x, pointB.y, pointC.x, pointC.y);
            }
        }

        private void drawPoint(Graphics2D g2, Point p, String label) {
            g2.fillOval(p.x - 5, p.y - 5, 10, 10); // the dot
            g2.drawString(label, p.x + 8, p.y - 8); // label near the dot
        }
    }
}
