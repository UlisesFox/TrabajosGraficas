import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Rectangulo {
    private int x0;
    private int y0;
    private int x1;
    private int y1;
    private Color colorBorde;
    private Color colorRelleno;

    public Rectangulo(int x0, int y0, int x1, int y1, Color colorBorde, Color colorRelleno) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.colorBorde = colorBorde;
        this.colorRelleno = colorRelleno;
    }

    public void escalar(double factor) {
        x0 = (int) (x0 * factor);
        y0 = (int) (y0 * factor);
        x1 = (int) (x1 * factor);
        y1 = (int) (y1 * factor);
    }

    public void pintar(Graphics g) {
        g.setColor(colorBorde);
        g.drawRect(x0, y0, x1 - x0, y1 - y0);
        g.setColor(colorRelleno);
        g.fillRect(x0 + 1, y0 + 1, x1 - x0 - 1, y1 - y0 - 1);
    }

    public int obtenerAnchoOriginal() {
        return x1 - x0;
    }

    public int obtenerAltoOriginal() {
        return y1 - y0;
    }
}

public class EscalacionNH extends JPanel implements ActionListener {

    private Rectangulo rectangulo;
    private double escala = 1.0;
    private Timer timer;
    private boolean animacionTerminada = false;

    public EscalacionNH() {
        rectangulo = new Rectangulo(150, 100, 450, 400, Color.MAGENTA, Color.orange);
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rectangulo.pintar(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!animacionTerminada) {
            rectangulo.escalar(escala);
            escala -= 0.01;

            if (escala <= 0.85 && !animacionTerminada) {
                animacionTerminada = true;
                timer.stop();
            }

            repaint();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Escalacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Escalacion escalacion = new Escalacion();
        frame.add(escalacion);
        frame.setVisible(true);
    }
}
