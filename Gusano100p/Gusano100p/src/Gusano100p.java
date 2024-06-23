import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gusano100p extends JPanel{
    double[] P = {0, 25, 5};

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        resorte(g, 800, 600, -5, 10, 100, Color.BLUE);
    }

    private void resorte(Graphics g, int puntox, int puntoy, int inicio, int largo, int cantidad, Color c) {
        double comienza = inicio;
        double finaliza = largo * Math.PI;
        double incremento = (finaliza - comienza) / (cantidad - 1);
        double u = 0.0;

        int[] puntosX = new int[cantidad];
        int[] puntosy = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            double t = comienza + incremento * i;
            double x = (Math.cos(t));
            double y = (Math.sin(t));
            double z = t;

            u = -(z / P[2]);
            puntosX[i] = (int) Math.round((x * 50 + (P[0] * u)) + puntox);
            puntosy[i] = (int) Math.round((y * 50 + (P[1] * u)) + puntoy);

            if (i > 0) {
                LineaMedia(g, puntosX[i - 1], puntosy[i - 1], puntosX[i], puntosy[i], c);
            }
        }
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
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
        JFrame frame = new JFrame("Gusano 100 puntos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Gusano100p gusano100p = new Gusano100p();
        frame.add(gusano100p);
        frame.setVisible(true);
    }
}
