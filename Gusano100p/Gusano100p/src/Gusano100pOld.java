import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gusano100pOld extends JPanel{
    List<Double> valoresx = new ArrayList<>();
    List<Double> valoresy = new ArrayList<>();
    List<Double> valoresz = new ArrayList<>();
    List<Double> valoresX = new ArrayList<>();
    List<Double> valoresY = new ArrayList<>();
    List<Double> valoresU = new ArrayList<>();
    double[] P = {0, 25, 90};

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Resorte3D(g, 800, 600, 100, 20, 100, Color.BLUE);
    }

    private void Resorte3D(Graphics g, int xOffset, int yOffset, double escalaX, double escalaY, int numPuntos, Color c){
        for (int i = 0; i < numPuntos; i++) {
            double t = i;

            double x = calcularX(t);
            double y = calcularY(t);
            double z = calcularZ(t);
            double U = -(z / P[2]);
            double X = x + P[0] * U;
            double Y = y + P[1] * U;

            valoresx.add(x);
            valoresy.add(y);
            valoresz.add(z);
            valoresX.add(X);
            valoresY.add(Y);
            valoresU.add(U);
        }

        for (int i = 0; i < numPuntos - 1; i++) {
            int x0 = (int) (valoresX.get(i) * escalaX + xOffset);
            int y0 = (int) (valoresY.get(i) * escalaY + yOffset);
            int x1 = (int) (valoresX.get(i + 1) * escalaX + xOffset);
            int y1 = (int) (valoresY.get(i + 1) * escalaY + yOffset);
            LineaMedia(g, x0, y0, x1, y1, c);
        }
    }

    private double calcularX(double t) {
        return Math.cos(t);
    }
    
    private double calcularY(double t) {
        return Math.sin(t);
    }

    private static double calcularZ(double t) {
        return t;
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gusano 100 puntos Old");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Gusano100pOld gusano100pold = new Gusano100pOld();
        frame.add(gusano100pold);
        frame.setVisible(true);
    }
}
