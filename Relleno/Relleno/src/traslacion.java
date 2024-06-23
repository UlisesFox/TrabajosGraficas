import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class traslacion {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Traslacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        MyPanel panel = new MyPanel();
        frame.add(panel);

        frame.setVisible(true);
        panel.startAnimation();
    }
}

class MyPanel extends JPanel {
    private double[][] squareVertices = {
        {0, 0, 1},
        {1550, 0, 1},
        {800, 800, 1},
        {0, 800, 1}
    };

    private Timer animationTimer;
    private double tx = 0;
    private double ty = 0;
    private double deltaTx = -3;
    private double deltaTy = 0;

    public MyPanel() {
        animationTimer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tx = deltaTx;
                ty = deltaTy;
                applyTranslation(tx, ty);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        RellenarCuadrado(g2d, squareVertices);
    }

    public void startAnimation() {
        animationTimer.start();
    }

    public void applyTranslation(double deltaX, double deltaY) {
        double[][] translationMatrix = {
            {1, 0, deltaX},
            {0, 1, deltaY},
            {0, 0, 1}
        };

        for (int i = 0; i < 4; i++) {
            squareVertices[i] = matrixMultiply(translationMatrix, squareVertices[i]);
        }
    }

    public double[] matrixMultiply(double[][] matrixA, double[] vectorB) {
        int m = matrixA.length;
        int n = matrixA[0].length;
        double[] result = new double[m];

        for (int i = 0; i < m; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrixA[i][j] * vectorB[j];
            }
            result[i] = sum;
        }

        return result;
    }

    public void cuadrado(Graphics2D g2d, double[][] vertices) {
        int n = vertices.length;
        for (int i = 0; i < n; i++) {
            int x1 = (int) vertices[i][0];
            int y1 = (int) vertices[i][1];
            int x2 = (int) vertices[(i + 1) % n][0];
            int y2 = (int) vertices[(i + 1) % n][1];
            Linea(g2d, x1, y1, x2, y2);
        }
    }

    public void RellenarCuadrado(Graphics2D g2d, double[][] vertices) {
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            int x = (int) vertices[i][0];
            int y = (int) vertices[i][1];

            xMin = Math.min(xMin, x);
            xMax = Math.max(xMax, x);
            yMin = Math.min(yMin, y);
            yMax = Math.max(yMax, y);
        }

        for (int y = yMin; y <= yMax; y++) {
            int x1 = xMin;
            int x2 = xMax;
            Linea(g2d, x1, y, x2, y);
        }
    }

    public void Linea(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(g, Math.round(x), Math.round(y));
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void pix(Graphics g, int x, int y) {
        g.fillRect(x, y, 1, 1);
    }
}
