import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mallado extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarMallado(g, 100, 100, 400, 400, 20, Color.BLUE);
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    private void dibujarMallado(Graphics g, int x0, int y0, int x1, int y1, int paso, Color c) {
        for (int x = x0; x <= x1; x += paso) {
            LineaMedia(g, x, y0, x, y1, c);
        }

        for (int y = y0; y <= y1; y += paso) {
            LineaMedia(g, x0, y, x1, y, c);
        }
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mallado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Mallado mallado = new Mallado();
        frame.add(mallado);
        frame.setVisible(true);
    }
}
