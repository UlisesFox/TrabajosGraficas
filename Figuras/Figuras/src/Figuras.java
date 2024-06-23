import javax.swing.*;
import java.awt.*;

public class Figuras extends JFrame {

    JPanel panel;

    public Figuras(){
        initPanel();
        initPantalla();

    }

    void initPanel(){

        panel = new JPanel();
        add(panel);
        panel.setPreferredSize(new Dimension(500,500));

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        //Rectángulo
        g.setColor(Color.ORANGE);
        g.fillRect(150, 250, 350, 60);

        //cuadrado
        g.setColor(Color.CYAN);
        g.fillRect(300, 100, 100, 100);

        //Círculo
        g.setColor(Color.MAGENTA);
        g.fillOval(60, 120, 100, 100);
    }

    private void initPantalla(){

        setTitle("Casita");
        setSize(800,600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Figuras();
    }
}