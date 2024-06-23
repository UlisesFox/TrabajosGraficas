import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class bresenham extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public bresenham() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Bresenham");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void dibujarLineaBresenham(int x0, int y0, int x1, int y1, Color c) {
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

        while (true) {
            pix(x0, y0, c);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        dibujarLineaBresenham(260, 240, 280, 266, Color.RED);
    }

    public static void main(String[] args) {
        new bresenham();
    }
}
