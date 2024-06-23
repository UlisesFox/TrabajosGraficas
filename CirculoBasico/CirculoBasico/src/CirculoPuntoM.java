import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoPuntoM extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public CirculoPuntoM() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Circulo Punto Medio");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void CirculoPuntoMedio(int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int error = 1 - r;

        while (x >= y) {
            pix(xc + x, yc + y, c);
            pix(xc - x, yc + y, c);
            pix(xc + x, yc - y, c);
            pix(xc - x, yc - y, c);
            pix(xc + y, yc + x, c);
            pix(xc - y, yc + x, c);
            pix(xc + y, yc - x, c);
            pix(xc - y, yc - x, c);

            y++;

            if (error > 0) {
                x--;
                error += 2 * (y - x) + 1;
            } else {
                error += 2 * y + 1;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        CirculoPuntoMedio(250, 250, 100, Color.orange);
    }

    public static void main(String[] args) {
        new CirculoPuntoM();
    }
}
