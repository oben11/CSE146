
// Oliver Benjamin
// CSE 146
// Homework05

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class sierpinskiTriangle extends JPanel {


    private static void drawSierpinski(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int depth) {
            if (depth == 0) {
                // Generate a random color for each triangle every time we start a new recursion
                Random random = new Random();
                Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                g.setColor(randomColor);
                g.fillPolygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
            } else {
                // mid points (ex. midx12 is the midpoint of x1+x2)
                int midX12 = (x1 + x2) / 2;
                int midY12 = (y1 + y2) / 2;
                int midX23 = (x2 + x3) / 2;
                int midY23 = (y2 + y3) / 2;
                int midX31 = (x3 + x1) / 2;
                int midY31 = (y3 + y1) / 2;
    
                // draw the triangles
                drawSierpinski(g, x1, y1, midX12, midY12, midX31, midY31, depth - 1);
                drawSierpinski(g, midX12, midY12, x2, y2, midX23, midY23, depth - 1);
                drawSierpinski(g, midX31, midY31, midX23, midY23, x3, y3, depth - 1);
            }
        }
    
        public static void main(String[] args) {
        // configurable depth
        int depth = 6;
        // basic jpanel setup with initial recurring function call
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    // creates the traingles x and y points based of the width and height of our panel
                    super.paintComponent(g);
                    int width = getWidth();
                    int height = getHeight();
    
                    int[] xPoints = {width / 2, 0, width};
                    int[] yPoints = {0, height, height};
    
                    drawSierpinski(g, xPoints[0], yPoints[0], xPoints[1], yPoints[1], xPoints[2], yPoints[2], depth);
            }
        };

        // basic jframe setup
        JFrame frame = new JFrame("Triangles Triangles Triangles!!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(512, 512);
        frame.setVisible(true);
    }
}