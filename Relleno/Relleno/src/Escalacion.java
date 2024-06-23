import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Escalacion extends JPanel implements ActionListener {
    private float[][] squareMatrix;
    private float scaleFactor = 1.0f;
    private Timer timer;
    private List<Integer> scanlineY;

    public Escalacion() {
        squareMatrix = new float[][] {
            {100, 100, 1},
            {600, 100, 1},
            {600, 600, 1},
            {100, 600, 1},
            {100, 100, 1}
        };

        timer = new Timer(150, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        scanlineY = new ArrayList<>();
        for (int i = 0; i < squareMatrix.length - 1; i++) {
            int x1 = Math.round(squareMatrix[i][0]);
            int y1 = Math.round(squareMatrix[i][1]);
            int x2 = Math.round(squareMatrix[i + 1][0]);
            int y2 = Math.round(squareMatrix[i + 1][1]);
            LineaMedia(g2d, x1, y1, x2, y2, Color.magenta);

            scanlineY.add(y1);
        }

        scanlineY.add(Math.round(squareMatrix[squareMatrix.length - 1][1]));
        relleno(g2d, scanlineY, Color.orange);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scaleFactor -= 0.005f;
        for (int i = 0; i < squareMatrix.length; i++) {
            squareMatrix[i][0] *= scaleFactor;
            squareMatrix[i][1] *= scaleFactor;
        }

        int maxX = Math.round(squareMatrix[2][0]);
        int maxY = Math.round(squareMatrix[2][1]);

        int stopX = 100;
        int stopY = 100;

        if (maxX <= stopX && maxY <= stopY) {
            timer.stop();
        }

        repaint();
    }

    public void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    public void Linea(Graphics g, int x1, int y1, int x2, int y2, Color c) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(g, Math.round(x), Math.round(y), c);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void relleno(Graphics g, List<Integer> scanlineY, Color fill) {
    int minY = scanlineY.stream().mapToInt(Integer::intValue).min().orElse(0);
    int maxY = scanlineY.stream().mapToInt(Integer::intValue).max().orElse(0);

    for (int y = minY; y < maxY; y++) {
        List<Integer> intersections = new ArrayList<>();
        for (int i = 0; i < scanlineY.size() - 1; i++) {
            int y1 = scanlineY.get(i);
            int y2 = scanlineY.get(i + 1);
            int x1 = Math.round(squareMatrix[i][0]);
            int x2 = Math.round(squareMatrix[i + 1][0]);

            if ((y1 <= y && y < y2) || (y2 <= y && y < y1)) {
                int x = (int) (x1 + (x2 - x1) * (y - y1) / (y2 - y1));
                intersections.add(x);
            }
        }

        intersections.sort(Integer::compare);

        for (int i = 0; i < intersections.size() - 1; i += 2) {
            int x1 = intersections.get(i);
            int x2 = intersections.get(i + 1);
            Linea(g, x1, y, x2, y, fill);
        }
    }
}

    public void LineaMedia(Graphics g, int x0, int y0, int x1, int y1, Color c) {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Escalación Diagonal de Cuadrado con Relleno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new Escalacion());
        frame.setVisible(true);
    }
}