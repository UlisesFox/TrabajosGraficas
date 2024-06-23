import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Rectangulo extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public Rectangulo() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Rectangulo");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void Linea(int x0, int y0, int x1, int y1, Color c) {
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
            pix(x, y, c);
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

    public void dibujarRectangulo(int x0, int y0, int x1, int y1, Color c) {
        Linea(x0, y0, x1, y0, c);
        Linea(x1, y0, x1, y1, c);
        Linea(x1, y1, x0, y1, c);
        Linea(x0, y1, x0, y0, c);
    }

    public void paint(Graphics g) {
        super.paint(g);
        dibujarRectangulo(100, 100, 400, 280, Color.magenta);
    }

    public static void main(String[] args) {
        new Rectangulo();
    }
}
