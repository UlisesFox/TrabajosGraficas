import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Perspectiva extends JPanel{    
    double[] punto1 = {0, 0};
    double[] punto2 = {0, 0};
    double[] punto3 = {0, 0};
    double[] punto4 = {0, 0};
    double[] punto5 = {0, 0};
    double[] punto6 = {0, 0};
    double[] punto7 = {0, 0};
    double[] punto8 = {0, 0};
    
    public Perspectiva() {
        double[] puntoA = {300,200,100,100};
        double[] puntoB = {300,400,100,100};
        double[] puntoC = {500,200,100,100};
        double[] puntoD = {500,400,100,100};
        double[] puntoE = {200,150,300,100};
        double[] puntoF = {200,350,300,100};
        double[] puntoG = {400,150,300,100};
        double[] puntoH = {400,350,300,100};
        double[] puntoP = {200,200,500};

        double uA = (-puntoP[2] / (puntoA[2]-puntoP[2]));
        double uB = (-puntoP[2] / (puntoB[2]-puntoP[2]));
        double uC = (-puntoP[2] / (puntoC[2]-puntoP[2]));
        double uD = (-puntoP[2] / (puntoD[2]-puntoP[2]));
        double uE = (-puntoP[2] / (puntoE[2]-puntoP[2]));
        double uF = (-puntoP[2] / (puntoF[2]-puntoP[2]));
        double uG = (-puntoP[2] / (puntoG[2]-puntoP[2]));
        double uH = (-puntoP[2] / (puntoH[2]-puntoP[2]));

        // Calcular A
        punto1[0] = puntoP[0] + (puntoA[0]-puntoP[0]) * uA;
        punto1[1] = puntoP[1] + (puntoA[1]-puntoP[1]) * uA;
        // Calcular B
        punto2[0] = puntoP[0] + (puntoB[0]-puntoP[0]) * uB;
        punto2[1] = puntoP[1] + (puntoB[1]-puntoP[1]) * uB;
        // Calcular C
        punto3[0] = puntoP[0] + (puntoC[0]-puntoP[0]) * uC;
        punto3[1] = puntoP[1] + (puntoC[1]-puntoP[1]) * uC;
        // Calcular D
        punto4[0] = puntoP[0] + (puntoD[0]-puntoP[0]) * uD;
        punto4[1] = puntoP[1] + (puntoD[1]-puntoP[1]) * uD;
        // Calcular E
        punto5[0] = puntoP[0] + (puntoE[0]-puntoP[0]) * uE;
        punto5[1] = puntoP[1] + (puntoE[1]-puntoP[1]) * uE;
        // Calcular F
        punto6[0] = puntoP[0] + (puntoF[0]-puntoP[0]) * uF;
        punto6[1] = puntoP[1] + (puntoF[1]-puntoP[1]) * uF;
        // Calcular G
        punto7[0] = puntoP[0] + (puntoG[0]-puntoP[0]) * uG;
        punto7[1] = puntoP[1] + (puntoG[1]-puntoP[1]) * uG;
        // Calcular H
        punto8[0] = puntoP[0] + (puntoH[0]-puntoP[0]) * uH;
        punto8[1] = puntoP[1] + (puntoH[1]-puntoP[1]) * uH;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fillcube(g, Color.cyan);
        Cube(g, Color.MAGENTA);
    }

    private void Cube(Graphics g, Color c){
        LineaMedia(g, (int)punto5[0], (int)punto5[1], (int)punto6[0], (int)punto6[1], c);
        LineaMedia(g, (int)punto6[0], (int)punto6[1], (int)punto8[0], (int)punto8[1], c);
        LineaMedia(g, (int)punto8[0], (int)punto8[1], (int)punto7[0], (int)punto7[1], c);
        LineaMedia(g, (int)punto7[0], (int)punto7[1], (int)punto5[0], (int)punto5[1], c);
        LineaMedia(g, (int)punto5[0], (int)punto5[1], (int)punto1[0], (int)punto1[1], c);
        LineaMedia(g, (int)punto6[0], (int)punto6[1], (int)punto2[0], (int)punto2[1], c);
        LineaMedia(g, (int)punto8[0], (int)punto8[1], (int)punto4[0], (int)punto4[1], c);
        LineaMedia(g, (int)punto7[0], (int)punto7[1], (int)punto3[0], (int)punto3[1], c);
        LineaMedia(g, (int)punto1[0], (int)punto1[1], (int)punto2[0], (int)punto2[1], c);
        LineaMedia(g, (int)punto2[0], (int)punto2[1], (int)punto4[0], (int)punto4[1], c);
        LineaMedia(g, (int)punto4[0], (int)punto4[1], (int)punto3[0], (int)punto3[1], c);
        LineaMedia(g, (int)punto3[0], (int)punto3[1], (int)punto1[0], (int)punto1[1], c);
    }

    private void fillcube(Graphics g, Color c){
        RectanguloRelleno(g, (int)punto5[0], (int)punto5[1], (int)punto8[0], (int)punto8[1], c);
        RectanguloRelleno(g, (int)punto1[0], (int)punto1[1], (int)punto4[0], (int)punto4[1], c);
        TranguloRelleno(g, (int)punto4[0], (int)punto4[1], (int)punto8[0], (int)punto8[1], (int)punto7[0], (int)punto7[1], c);
        TranguloRelleno(g, (int)punto3[0], (int)punto3[1], (int)punto4[0], (int)punto4[1], (int)punto7[0], (int)punto7[1], c);
        TranguloRelleno(g, (int)punto6[0], (int)punto6[1], (int)punto2[0], (int)punto2[1], (int)punto8[0], (int)punto8[1], c);
        TranguloRelleno(g, (int)punto2[0], (int)punto2[1], (int)punto4[0], (int)punto4[1], (int)punto8[0], (int)punto8[1], c);
        TranguloRelleno(g, (int)punto5[0], (int)punto5[1], (int)punto3[0], (int)punto3[1], (int)punto7[0], (int)punto7[1], c);
        TranguloRelleno(g, (int)punto5[0], (int)punto5[1], (int)punto1[0], (int)punto1[1], (int)punto3[0], (int)punto3[1], c);
        TranguloRelleno(g, (int)punto5[0], (int)punto5[1], (int)punto6[0], (int)punto6[1], (int)punto1[0], (int)punto1[1], c);
        TranguloRelleno(g, (int)punto1[0], (int)punto1[1], (int)punto2[0], (int)punto2[1], (int)punto6[0], (int)punto6[1], c);
    }

    public void RectanguloRelleno(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        for (int y = y0; y <= y1; y++) {
            LineaMedia(g, x1, y, x0, y, c);
        }
    }

    private void TranguloRelleno(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color c) {
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
        int areaTriangulo = Math.abs((x0 * (y1 - y2) + x1 * (y2 - y0) + x2 * (y0 - y1)));
        int area1 = Math.abs((x * (y1 - y2) + x1 * (y2 - y) + x2 * (y - y1)));
        int area2 = Math.abs((x0 * (y - y2) + x * (y2 - y0) + x2 * (y0 - y)));
        int area3 = Math.abs((x0 * (y1 - y) + x1 * (y - y0) + x * (y0 - y1)));
        return areaTriangulo == area1 + area2 + area3;
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
        JFrame frame = new JFrame("Perspectiva");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Perspectiva perspectiva = new Perspectiva();
        frame.add(perspectiva);
        frame.setVisible(true);
    }
}
