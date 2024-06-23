import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rotacion extends JPanel implements ActionListener {
    private double[][] square;
    private double angle;
    private Timer timer;

    public Rotacion() {
        square = new double[][]{
            {-100, -100, 1},
            {100, -100, 1},
            {100, 100, 1},
            {-100, 100, 1}
        };

        angle = 0.0;

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        double[][] rotatedSquare = new double[4][3];
        for (int i = 0; i < 4; i++) {
            rotatedSquare[i][0] = square[i][0] * cos - square[i][1] * sin;
            rotatedSquare[i][1] = square[i][0] * sin + square[i][1] * cos;
            rotatedSquare[i][2] = square[i][2];
        }

        g.setColor(Color.BLUE);
        for (int i = 0; i < 3; i++) {
            int x1 = (int) (rotatedSquare[i][0] / rotatedSquare[i][2]) + centerX;
            int y1 = (int) (rotatedSquare[i][1] / rotatedSquare[i][2]) + centerY;
            int x2 = (int) (rotatedSquare[i + 1][0] / rotatedSquare[i + 1][2]) + centerX;
            int y2 = (int) (rotatedSquare[i + 1][1] / rotatedSquare[i + 1][2]) + centerY;
            LineaMedia(g, x1, y1, x2, y2, Color.BLUE);
        }
        int x1 = (int) (rotatedSquare[3][0] / rotatedSquare[3][2]) + centerX;
        int y1 = (int) (rotatedSquare[3][1] / rotatedSquare[3][2]) + centerY;
        int x2 = (int) (rotatedSquare[0][0] / rotatedSquare[0][2]) + centerX;
        int y2 = (int) (rotatedSquare[0][1] / rotatedSquare[0][2]) + centerY;
        LineaMedia(g, x1, y1, x2, y2, Color.magenta);

        RellenarCuadrado(g, rotatedSquare, centerX, centerY, Color.orange);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angle += Math.toRadians(5);
        repaint();
    }

    private void LineaMedia(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx, sy;

        if (x0 < x1) {
            sx = 1;
        } else {
            sx = -1;
        }

        if (y0 < y1) {
            sy = 1;
        } else {
            sy = -1;
        }

        int err = dx - dy;
        int x = x0;
        int y = y0;

        while (true) {
            pix(g, x, y, c);

            if (x == x1 && y == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x = x + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y = y + sy;
            }
        }
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    private void RellenarCuadrado(Graphics g, double[][] square, int centerX, int centerY, Color c) {
        int[] yPoints = new int[4];
        int[] xPoints = new int[4];

        for (int i = 0; i < 4; i++) {
            xPoints[i] = (int) (square[i][0] / square[i][2]) + centerX;
            yPoints[i] = (int) (square[i][1] / square[i][2]) + centerY;
        }

        g.setColor(c);
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Square");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new Rotacion());
        frame.setVisible(true);
    }
}
