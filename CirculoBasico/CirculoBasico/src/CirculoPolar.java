import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoPolar extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public CirculoPolar() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Circulo Polar");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void Circulopolar(int xc, int yc, int r, Color c) {
        double theta = 0;
        while (theta <= Math.PI / 4) {
            int x = (int) (xc + r * Math.cos(theta));
            int y = (int) (yc + r * Math.sin(theta));
            pix(x, y, c);
            
            pix(x, 2 * yc - y, c);
            pix(2 * xc - x, y, c);
            pix(2 * xc - x, 2 * yc - y, c);
            pix(y, x, c);
            pix(y, 2 * xc - x, c);
            pix(2 * yc - y, x, c);
            pix(2 * yc - y, 2 * xc - x, c);

            theta += 0.01;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Circulopolar(250, 250, 100, Color.magenta);
    }

    public static void main(String[] args) {
        new CirculoPolar();
    }
}
