import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TraslacionNH extends JPanel implements ActionListener {

    private int x0 = 200;
    private int y0 = 500;
    private int x1 = 400;
    private int y1 = 700;
    private Color colorBorde = Color.MAGENTA;
    private Color colorRelleno = Color.orange;
    private int incremento = 1;
    private int objetivoX0 = 1000; // Coordenada X final
    private int objetivoY0 = 50; // Coordenada Y final

    public TraslacionNH() {
        Timer timer = new Timer(0, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Traslacions(g);
    }

    private void Traslacions(Graphics g) {
        Rectangulo(g, x0, y0, x1, y1, colorBorde);
        RectanguloRelleno(g, x0 + 1, y0 + 1, x1 - 1, y1 - 1, colorRelleno);
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    public void Linea(Graphics g, int x1, int y1, int x2, int y2, Color c) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(g, Math.round(x), Math.round(y), c);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void LineaMedia(Graphics g, int x0, int y0, int x1, int y1, Color c) {
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
        int x = x0;
        int y = y0;

        while (true) {
            pix(g, x, y, c);

            if (x == x1 && y == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x = x + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y = y + sy;
            }
        }
    }

    public void Rectangulo(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        LineaMedia(g, x0, y0, x1, y0, c);
        LineaMedia(g, x1, y0, x1, y1, c);
        LineaMedia(g, x1, y1, x0, y1, c);
        LineaMedia(g, x0, y1, x0, y0, c);
    }

    public void RectanguloRelleno(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        for (int y = y0; y <= y1; y++) {
            Linea(g, x1, y, x0, y, c);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //abajo
        //if (x0 < objetivoX0 && y0 < objetivoY0) {
            //x0 += incremento;
            //y0 += incremento;
            //x1 += incremento;
            //y1 += incremento;
        //} else {
            //((Timer) e.getSource()).stop();
        //}

        //repaint();

        //arriba
        if (x0 < objetivoX0 && y0 > objetivoY0) {
            x0 += incremento;
            y0 -= incremento;
            x1 += incremento;
            y1 -= incremento;
        } else {
            ((Timer) e.getSource()).stop();
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Traslacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        TraslacionNH traslacion = new TraslacionNH();
        frame.add(traslacion);
        frame.setVisible(true);
    }
}