import javax.swing.*;
import java.awt.*;

public class Relleno extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Rellenos(g);
    }

    private void Rellenos(Graphics g) {    
        CirculoPuntoMedio(g, 400, 400, 150, Color.MAGENTA);
        CirculoPuntoMedioRelleno(g, 400, 400, 150, Color.orange);
        Rectangulo(g, 750, 260, 1300, 540, Color.MAGENTA);
        RectanguloRelleno(g, 751, 261, 1299, 539, Color.orange);
    }

    private void pix(Graphics g, int x, int y, Color c) {
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

    public void CirculoPuntoMedio(Graphics g, int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int error = 1 - r;

        while (x >= y) {
            pix(g, xc + x, yc + y, c);
            pix(g, xc - x, yc + y, c);
            pix(g, xc + x, yc - y, c);
            pix(g, xc - x, yc - y, c);
            pix(g, xc + y, yc + x, c);
            pix(g, xc - y, yc + x, c);
            pix(g, xc + y, yc - x, c);
            pix(g, xc - y, yc - x, c);

            y++;

            if (error > 0) {
                x--;
                error += 2 * (y - x) + 1;
            } else {
                error += 2 * y + 1;
            }
        }
    }

    public void Rectangulo(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        LineaMedia(g, x0, y0, x1, y0, c);
        LineaMedia(g, x1, y0, x1, y1, c);
        LineaMedia(g, x1, y1, x0, y1, c);
        LineaMedia(g, x0, y1, x0, y0, c);
    }

    //scaneline
    public void RectanguloRelleno(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        for (int y = y0; y <= y1; y++) {
            Linea(g, x1, y, x0, y, c);
        }
    }

    //inundacion
    public void CirculoPuntoMedioRelleno(Graphics g, int xc, int yc, int r, Color c) {
        for (int y = -r; y <= r; y++) {
            for (int x = -r; x <= r; x++) {
                if (x * x + y * y <= r * r) {
                    pix(g, xc + x, yc + y, c);
                }
            }
        }
    }    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Relleno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Relleno relleno = new Relleno();
        frame.add(relleno);
        frame.setVisible(true);
    }
}
