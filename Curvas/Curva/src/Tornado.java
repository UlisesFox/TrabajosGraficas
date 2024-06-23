import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tornado extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tornados(g);
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    private void pix2(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 3, 3);
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

    private void Tornados(Graphics g) {
        int pasos = 100;
        double inicio = 15;
        double ultimo = Math.PI * 7;
        double pas = (ultimo - inicio) / (pasos - 1);

        int[] x1 = {750, 100};
        int[] y1 = {770, 200};

        int[] arrayx = new int[pasos];
        int[] arrayy = new int[pasos];

        for (int i = 0; i < pasos; i++) {
            double x = inicio + pas * i;
            double y = Math.sin(x) * 60 * (i / (double) pasos);
            arrayx[i] = (int) Math.round(x1[0] - (y * 5));
            arrayy[i] = (int) Math.round(y1[0] + (x - inicio) * (y1[1] - y1[0]) / (ultimo - inicio));
            pix2(g, arrayx[i], arrayy[i], Color.RED);
        }

        for (int i = 0; i < pasos - 1; i++) {
            LineaMedia(g, arrayx[i], arrayy[i], arrayx[i + 1], arrayy[i + 1], Color.BLACK);
        }
    }    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tornado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Tornado tornado = new Tornado();
        frame.add(tornado);
        frame.setVisible(true);
    }
}
