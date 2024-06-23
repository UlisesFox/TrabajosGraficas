import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Figura2 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Triangulo(g, 199, 501, 400, 49, 601, 501, Color.MAGENTA);
        ScaneLine(g, 200, 500, 400, 50, 600, 500, Color.green);
        Rombo(g, 1000, 300, 502, Color.RED);
        RellenoRombo(g, 1000, 300, 500, Color.magenta);
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }
    
    private void LineaMedia(Graphics g, int x0, int y0, int x1, int y1, Color c) {
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
    
    private void Triangulo(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color c){
        LineaMedia(g, x0, y0, x1, y1, c);
        LineaMedia(g, x1, y1, x2, y2, c);
        LineaMedia(g, x2, y2, x0, y0, c);
    }

    private void ScaneLine(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color c) {
        int minX = Math.min(Math.min(x0, x1), x2);
        int minY = Math.min(Math.min(y0, y1), y2);
        int maxX = Math.max(Math.max(x0, x1), x2);
        int maxY = Math.max(Math.max(y0, y1), y2);

        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (PuntoEnTriangulo(x, y, x0, y0, x1, y1, x2, y2)) {
                    pix(g, x, y, c);
                }
            }
        }
    }

    private boolean PuntoEnTriangulo(int x, int y, int x0, int y0, int x1, int y1, int x2, int y2) {
        int areaTriangulo = Math.abs((x0 * (y1 - y2) + x1 * (y2 - y0) + x2 * (y0 - y1)) / 2);
        int area1 = Math.abs((x * (y1 - y2) + x1 * (y2 - y) + x2 * (y - y1)) / 2);
        int area2 = Math.abs((x0 * (y - y2) + x * (y2 - y0) + x2 * (y0 - y)) / 2);
        int area3 = Math.abs((x0 * (y1 - y) + x1 * (y - y0) + x * (y0 - y1)) / 2);
        return areaTriangulo == area1 + area2 + area3;
    }
    
    public void Rombo(Graphics g, int centroX, int centroY, int lado, Color c) {
        int x1 = centroX - lado / 2;
        int y1 = centroY;
        int x2 = centroX;
        int y2 = centroY - lado / 2;
        int x3 = centroX + lado / 2;
        int y3 = centroY;
        int x4 = centroX;
        int y4 = centroY + lado / 2;
    
        LineaMedia(g, x1, y1, x2, y2, c);
        LineaMedia(g, x2, y2, x3, y3, c);
        LineaMedia(g, x3, y3, x4, y4, c);
        LineaMedia(g, x4, y4, x1, y1, c);
        
    }

    private void RellenoRombo(Graphics g, int centroX, int centroY, int lado, Color c) {
        int x1 = centroX - lado / 2;
        int y1 = centroY;
        int x2 = centroX;
        int y2 = centroY - lado / 2;
        int x3 = centroX + lado / 2;
        int y3 = centroY;
        int x4 = centroX;
        int y4 = centroY + lado / 2;

        ScaneLine(g, x2, y2, x3, y3, x4, y4, c);
        ScaneLine(g, x2, y2, x1, y1, x4, y4, c);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Triangulo y Rombo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Figura2 figuras2 = new Figura2();
        frame.add(figuras2);
        frame.setVisible(true);
    }
}
