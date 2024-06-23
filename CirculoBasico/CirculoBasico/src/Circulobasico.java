import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Circulobasico extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public Circulobasico() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Circulo basico");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void Circulo(int xc, int yc, int radio) {
        for (int x = xc - radio; x <= xc + radio; x++) {
            int y1 = yc + (int) Math.sqrt(radio * radio - (x - xc) * (x - xc));
            int y2 = yc - (int) Math.sqrt(radio * radio - (x - xc) * (x - xc));
            pix(x, y1, Color.red);
            pix(x, y2, Color.red);
            if (x == 50 && (y1 == 50 || y2 == 50)) {
                pix(x, 50, Color.green);
            }
        }

        int x1 = xc;
        int y1 = yc;
        pix(x1, y1, Color.green);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Circulo(220, 220, 85);
    }

    public static void main(String[] args) {
        new Circulobasico();
    }
}
