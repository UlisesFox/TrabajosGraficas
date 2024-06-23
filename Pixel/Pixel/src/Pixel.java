import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.awt.Color.red;

public class Pixel extends JFrame{

    private BufferedImage buffer;
    JPanel jPanel;

    public Pixel(){
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Pixel");
        setSize(500,500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
    }

    public void pix(int x, int y, Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void paint(Graphics g){
        super.paint(g);
        pix(100, 100, red);
    }

    public static void main(String[] args) {
        new Pixel();
    }
}