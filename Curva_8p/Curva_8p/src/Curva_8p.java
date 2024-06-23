import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Curva_8p extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Curva8p(g);
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    private void pix2(Graphics g, int x, int y, Color c) {
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

    private void Curva8p(Graphics g) {
        int pasos = 8;
        double inicio = 0;
        double ultimo = Math.PI;
        double pas = (ultimo - inicio) / (pasos - 1);

        int[] x1 = {100, 450};
        int[] y1 = {550, 250};

        int[] arrayx = new int[pasos];
        int[] arrayy = new int[pasos];

        for (int i = 0; i < pasos; i++) {
            double x = inicio + pas * i;
            double y = Math.sin(x);
            arrayx[i] = (int) Math.round(x1[0] + (x - inicio) * (x1[1] - x1[0]) / (ultimo - inicio));
            arrayy[i] = (int) Math.round(y1[0] - (y * 300));
            pix2(g, arrayx[i], arrayy[i], Color.RED);
        }

        for (int i = 0; i < pasos - 1; i++) {
            LineaMedia(g, arrayx[i], arrayy[i], arrayx[i + 1], arrayy[i + 1], Color.BLACK);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Curva 8 puntos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Curva_8p curva = new Curva_8p();
        frame.add(curva);
        frame.setVisible(true);
    }
}
