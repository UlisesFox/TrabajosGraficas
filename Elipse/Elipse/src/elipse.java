import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class elipse extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public elipse() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Elipse");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void Elipse(int xc, int yc, int a, int b, Color c) {
        int a2 = a * a;
        int b2 = b * b;
        int twoa2 = 2 * a2;
        int twob2 = 2 * b2;
        int p;
        int x = 0;
        int y = b;
        int px = 0;
        int py = twoa2 * y;

        pix(xc, yc + y, c);
        pix(xc, yc - y, c);

        p = (int) (b2 - (a2 * b) + (0.25 * a2));
        
        while (px < py) {
            x++;
            px += twob2;
            if (p < 0) {
                p += b2 + px;
            } else {
                y--;
                py -= twoa2;
                p += b2 + px - py;
            }
            pix(xc + x, yc + y, c);
            pix(xc - x, yc + y, c);
            pix(xc + x, yc - y, c);
            pix(xc - x, yc - y, c);
        }

        p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
        
        while (y > 0) {
            y--;
            py -= twoa2;
            if (p > 0) {
                p += a2 - py;
            } else {
                x++;
                px += twob2;
                p += a2 - py + px;
            }
            pix(xc + x, yc + y, c);
            pix(xc - x, yc + y, c);
            pix(xc + x, yc - y, c);
            pix(xc - x, yc - y, c);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Elipse(250, 250, 150, 100, Color.magenta);
    }

    public static void main(String[] args) {
        new elipse();
    }
}
