import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ddainversa extends JFrame {

    private BufferedImage buffer;
    JPanel jPanel;

    public ddainversa() {
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana linea DDA inversa");
        setSize(500, 500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void drawLineDDA(int x1, int y1, int x2, int y2, Color c) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = dx / (float) steps;
        float yIncrement = dy / (float) steps;

        float x = x2;
        float y = y2;

        for (int i = 0; i <= steps; i++) {
            pix(Math.round(x), Math.round(y), c);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLineDDA(280, 280, 230, 230, Color.BLUE);
    }

    public static void main(String[] args) {
        new ddainversa();
    }
}
