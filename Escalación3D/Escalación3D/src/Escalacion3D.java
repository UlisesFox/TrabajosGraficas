import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Escalacion3D extends JPanel{    
    double[] punto1 = {0, 0};
    double[] punto2 = {0, 0};
    double[] punto3 = {0, 0};
    double[] punto4 = {0, 0};
    double[] punto5 = {0, 0};
    double[] punto6 = {0, 0};
    double[] punto7 = {0, 0};
    double[] punto8 = {0, 0};

    double[] puntoA = {200, 200, 10};
    double[] puntoB = {400, 200, 10};
    double[] puntoC = {200, 400, 10};
    double[] puntoD = {400, 400, 10};
    double[] puntoE = {100, 100, 20};
    double[] puntoF = {100, 300, 20};
    double[] puntoG = {300, 100, 20};
    double[] puntoH = {300, 300, 20};
    private double[] puntoP = {-350, -300, -100};
    private double[][] matrizTraslacion;
    private Timer timer;
    private int targetX = -315;
    
    public Escalacion3D() {
        double tx = 100;
        double ty = 100;
        double tz = 100;

        matrizTraslacion = new double[][] {
            {tx, 0, 0, 0},
            {0, ty, 0, 0},
            {0, 0, tz, 0},
            {0, 0, 0, 1}
        };

        timer = new Timer(50, e -> {
                if (puntoP[0] < targetX) {
                    mov();
                    Traslacion();
                    repaint();
                } else {
                    timer.stop();
                }
            });
            timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        fillCube(g, Color.cyan);
        Cube(g, Color.MAGENTA);
    }

    private void Traslacion() {
        double uA = -(puntoA[2] / puntoP[2]);
        double uB = -(puntoB[2] / puntoP[2]);
        double uC = -(puntoC[2] / puntoP[2]);
        double uD = -(puntoD[2] / puntoP[2]);
        double uE = -(puntoE[2] / puntoP[2]);
        double uF = -(puntoF[2] / puntoP[2]);
        double uG = -(puntoG[2] / puntoP[2]);
        double uH = -(puntoH[2] / puntoP[2]);

        // Calcular A
        punto1[0] = puntoA[0] + puntoP[0] * uA;
        punto1[1] = puntoA[1] + puntoP[1] * uA;
        // Calcular B
        punto2[0] = puntoB[0] + puntoP[0] * uB;
        punto2[1] = puntoB[1] + puntoP[1] * uB;
        // Calcular C
        punto3[0] = puntoC[0] + puntoP[0] * uC;
        punto3[1] = puntoC[1] + puntoP[1] * uC;
        // Calcular D
        punto4[0] = puntoD[0] + puntoP[0] * uD;
        punto4[1] = puntoD[1] + puntoP[1] * uD;
        // Calcular E
        punto5[0] = puntoE[0] + puntoP[0] * uE;
        punto5[1] = puntoE[1] + puntoP[1] * uE;
        // Calcular F
        punto6[0] = puntoF[0] + puntoP[0] * uF;
        punto6[1] = puntoF[1] + puntoP[1] * uF;
        // Calcular G
        punto7[0] = puntoG[0] + puntoP[0] * uG;
        punto7[1] = puntoG[1] + puntoP[1] * uG;
        // Calcular H
        punto8[0] = puntoH[0] + puntoP[0] * uH;
        punto8[1] = puntoH[1] + puntoP[1] * uH;
    }

    private void mov(){
        targetX -= 1;
        puntoA[0] += 1;
        puntoA[1] += 1;
        puntoB[0] -= 1;
        puntoB[1] += 1;
        puntoC[0] += 1;
        puntoC[1] -= 1;
        puntoD[0] -= 1;
        puntoD[1] -= 1;
        puntoE[0] += 1;
        puntoE[1] += 1;
        puntoE[2] -= 1;
        puntoF[0] += 1;
        puntoF[1] -= 1;
        puntoF[2] -= 1;
        puntoG[0] -= 1;
        puntoG[1] += 1;
        puntoG[2] -= 1;
        puntoH[0] -= 1;
        puntoH[1] -= 1;
        puntoH[2] -= 1;
    }

    private void matriz(){
        double[] puntoA = {200, 200, 10};
        double[] puntoB = {400, 200, 10};
        double[] puntoC = {200, 400, 10};
        double[] puntoD = {400, 400, 10};
        double[] puntoE = {100, 100, 20};
        double[] puntoF = {100, 300, 20};
        double[] puntoG = {300, 100, 20};
        double[] puntoH = {300, 300, 20};

        puntoA = multiplicarMatrizPorPunto(matrizTraslacion, puntoA);
        puntoB = multiplicarMatrizPorPunto(matrizTraslacion, puntoA);
        puntoC = multiplicarMatrizPorPunto(matrizTraslacion, puntoB);
        puntoA = multiplicarMatrizPorPunto(matrizTraslacion, puntoC);
        puntoD = multiplicarMatrizPorPunto(matrizTraslacion, puntoD);
        puntoE = multiplicarMatrizPorPunto(matrizTraslacion, puntoE);
        puntoF = multiplicarMatrizPorPunto(matrizTraslacion, puntoF);
        puntoG = multiplicarMatrizPorPunto(matrizTraslacion, puntoG);
        puntoH = multiplicarMatrizPorPunto(matrizTraslacion, puntoH);

        punto1[0] = puntoA[0] / puntoA[3];
        punto1[1] = puntoA[1] / puntoA[3];
        punto1[0] = puntoB[0] / puntoB[3];
        punto1[1] = puntoB[1] / puntoB[3];
        punto1[0] = puntoC[0] / puntoC[3];
        punto1[1] = puntoC[1] / puntoC[3];
        punto1[0] = puntoD[0] / puntoD[3];
        punto1[1] = puntoD[1] / puntoD[3];
        punto1[0] = puntoE[0] / puntoE[3];
        punto1[1] = puntoE[1] / puntoE[3];
        punto1[0] = puntoF[0] / puntoF[3];
        punto1[1] = puntoF[1] / puntoF[3];
        punto1[0] = puntoG[0] / puntoG[3];
        punto1[1] = puntoG[1] / puntoG[3];
        punto1[0] = puntoH[0] / puntoH[3];
        punto1[1] = puntoH[1] / puntoH[3];
    }

    private double[] multiplicarMatrizPorPunto(double[][] matriz, double[] punto) {
        matriz();
        double[] resultado = new double[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                resultado[i] += matriz[i][j] * punto[j];
            }
        }

        return resultado;
    }

    private void Cube(Graphics g, Color c){
        LineaMedia(g, (int)punto5[0], (int)punto5[1], (int)punto6[0], (int)punto6[1], c);
        LineaMedia(g, (int)punto6[0], (int)punto6[1], (int)punto8[0], (int)punto8[1], c);
        LineaMedia(g, (int)punto8[0], (int)punto8[1], (int)punto7[0], (int)punto7[1], c);
        LineaMedia(g, (int)punto7[0], (int)punto7[1], (int)punto5[0], (int)punto5[1], c);
        LineaMedia(g, (int)punto5[0], (int)punto5[1], (int)punto1[0], (int)punto1[1], c);
        LineaMedia(g, (int)punto7[0], (int)punto7[1], (int)punto2[0], (int)punto2[1], c);
        LineaMedia(g, (int)punto8[0], (int)punto8[1], (int)punto4[0], (int)punto4[1], c);
        LineaMedia(g, (int)punto6[0], (int)punto6[1], (int)punto3[0], (int)punto3[1], c);
        LineaMedia(g, (int)punto1[0], (int)punto1[1], (int)punto2[0], (int)punto2[1], c);
        LineaMedia(g, (int)punto2[0], (int)punto2[1], (int)punto4[0], (int)punto4[1], c);
        LineaMedia(g, (int)punto4[0], (int)punto4[1], (int)punto3[0], (int)punto3[1], c);
        LineaMedia(g, (int)punto3[0], (int)punto3[1], (int)punto1[0], (int)punto1[1], c);
    }

    private void fillCube(Graphics g, Color c) {
        RectanguloRelleno(g, (int)punto5[0], (int)punto5[1], (int)punto8[0], (int)punto8[1], c);
        RectanguloRelleno(g, (int)punto1[0], (int)punto1[1], (int)punto4[0], (int)punto4[1], c);
        RectanguloRelleno(g, (int)punto8[0], (int)punto8[1], (int)punto5[0], (int)punto5[1], c);
        RectanguloRelleno(g, (int)punto4[0], (int)punto4[1], (int)punto1[0], (int)punto1[1], c);
        TranguloRelleno(g, (int)punto7[0], (int)punto7[1], (int)punto4[0], (int)punto4[1], (int)punto2[0], (int)punto2[1], c);
        TranguloRelleno(g, (int)punto7[0], (int)punto7[1], (int)punto4[0], (int)punto4[1], (int)punto8[0], (int)punto8[1], c);
        TranguloRelleno(g, (int)punto7[0], (int)punto7[1], (int)punto5[0], (int)punto5[1], (int)punto2[0], (int)punto2[1], c);
        TranguloRelleno(g, (int)punto1[0], (int)punto1[1], (int)punto5[0], (int)punto5[1], (int)punto2[0], (int)punto2[1], c);
        TranguloRelleno(g, (int)punto6[0], (int)punto6[1], (int)punto5[0], (int)punto5[1], (int)punto3[0], (int)punto3[1], c);
        TranguloRelleno(g, (int)punto1[0], (int)punto1[1], (int)punto5[0], (int)punto5[1], (int)punto3[0], (int)punto3[1], c);
        TranguloRelleno(g, (int)punto3[0], (int)punto3[1], (int)punto6[0], (int)punto6[1], (int)punto4[0], (int)punto4[1], c);
        TranguloRelleno(g, (int)punto8[0], (int)punto8[1], (int)punto6[0], (int)punto6[1], (int)punto4[0], (int)punto4[1], c);
    }

    public void RectanguloRelleno(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        for (int y = y0; y <= y1; y++) {
            LineaMedia(g, x1, y, x0, y, c);
        }
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
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
        JFrame frame = new JFrame("Escalacion 3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Escalacion3D escalacion3D = new Escalacion3D();
        frame.add(escalacion3D);
        frame.setVisible(true);
    }
}
