import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class linea extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public linea() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Linea recta");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void drawLine(int x1, int y1, int x2, int y2, Color c) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(Math.round(x), Math.round(y), c);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLine(120, 120, 230, 120, Color.magenta);
    }

    public static void main(String[] args) {
        new linea();
    }
}
