import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cubo extends JPanel{    
    double[] punto1 = {0, 0};
    double[] punto2 = {0, 0};
    double[] punto3 = {0, 0};
    double[] punto4 = {0, 0};
    double[] punto5 = {0, 0};
    double[] punto6 = {0, 0};
    double[] punto7 = {0, 0};
    double[] punto8 = {0, 0};
    
    public Cubo() {
        double[] puntoA = {200, 200, 10};
        double[] puntoB = {400, 200, 10};
        double[] puntoC = {200, 400, 10};
        double[] puntoD = {400, 400, 10};
        double[] puntoE = {100, 100, 20};
        double[] puntoF = {100, 300, 20};
        double[] puntoG = {300, 100, 20};
        double[] puntoH = {300, 300, 20};
        double[] puntoP = {-350, -300, -100};

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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cube(g, Color.MAGENTA);
    }

    private void Cube(Graphics g, Color c){
        //pixPunto(g, (int)punto1[0], (int)punto1[1], c);
        //pixPunto(g, (int)punto2[0], (int)punto2[1], c);
        //pixPunto(g, (int)punto3[0], (int)punto3[1], c);
        //pixPunto(g, (int)punto4[0], (int)punto4[1], c);
        //pixPunto(g, (int)punto5[0], (int)punto5[1], c);
        //pixPunto(g, (int)punto6[0], (int)punto6[1], c);
        //pixPunto(g, (int)punto7[0], (int)punto7[1], c);
        //pixPunto(g, (int)punto8[0], (int)punto8[1], c);

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

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    //private void pixPunto(Graphics g, int x, int y, Color c) {
    //    g.setColor(c);
    //    g.fillRect(x, y, 1, 1);
    //}

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
        JFrame frame = new JFrame("Cubo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Cubo cubo = new Cubo();
        frame.add(cubo);
        frame.setVisible(true);
    }
}
