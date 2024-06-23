import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sol extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        resorte(g);
        resortepix(g);
    }

    private void resorte(Graphics g){
        double startX = 400; 
        double startY = 400; 
        double scale = 12;

        double prevX = Double.NaN;
        double prevY = Double.NaN;

        for (double t = 0; t <= 35 * Math.PI; t += 0.001) {
            double x = calcularX(t) * scale + startX;
            double y = -calcularY(t) * scale + startY;
            
            if (!Double.isNaN(prevX)) {
                LineaMedia(g, (int) prevX, (int) prevY, (int) x, (int) y, Color.RED);
            }
            
            prevX = x; 
            prevY = y;
        }
    }

    private void resortepix(Graphics g){
        double startX = 1100; 
        double startY = 400; 
        double scale = 12;

        for (double t = 0; t <= 35 * Math.PI; t += 0.001) {
            double x = calcularX(t) * scale + startX;
            double y = -calcularY(t) * scale + startY;
            pix(g, (int) x, (int) y, Color.RED);
        }
    }

    private double calcularX(double t) {
        return 17 * Math.cos(t) + 7 * Math.cos((17.0 / 7.0) * t);
    }
    
    private double calcularY(double t) {
        return 17 * Math.sin(t) - 7 * Math.sin((17.0 / 7.0) * t);
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
        JFrame frame = new JFrame("Sol");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Sol sol = new Sol();
        frame.add(sol);
        frame.setVisible(true);
    }
}
