import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RotacionNH extends JPanel implements ActionListener {

    private int x0 = 750;
    private int y0 = 300;
    private int lado = 300;

    private double angulo = 0;
    private double velocidad = Math.toRadians(3);

    private Timer timer;

    public RotacionNH() {
        timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarCuadrado(g);
    }

    private void dibujarCuadrado(Graphics g) {
        int x1 = x0 - lado / 2;
        int y1 = y0 - lado / 2;
        int x2 = x0 + lado / 2;
        int y2 = y0 - lado / 2;
        int x3 = x0 + lado / 2;
        int y3 = y0 + lado / 2;
        int x4 = x0 - lado / 2;
        int y4 = y0 + lado / 2;

        int[] puntosX = {x1, x2, x3, x4};
        int[] puntosY = {y1, y2, y3, y4};
        int[] puntosXRotados = new int[4];
        int[] puntosYRotados = new int[4];

        for (int i = 0; i < 4; i++) {
            puntosXRotados[i] = (int) (x0 + (puntosX[i] - x0) * Math.cos(angulo) - (puntosY[i] - y0) * Math.sin(angulo));
            puntosYRotados[i] = (int) (y0 + (puntosX[i] - x0) * Math.sin(angulo) + (puntosY[i] - y0) * Math.cos(angulo));
        }

        Color magenta = Color.MAGENTA;
        Color orange = Color.ORANGE;

        Linea(g, puntosXRotados[0], puntosYRotados[0], puntosXRotados[1], puntosYRotados[1], magenta);
        Linea(g, puntosXRotados[1], puntosYRotados[1], puntosXRotados[2], puntosYRotados[2], magenta);
        Linea(g, puntosXRotados[2], puntosYRotados[2], puntosXRotados[3], puntosYRotados[3], magenta);
        Linea(g, puntosXRotados[3], puntosYRotados[3], puntosXRotados[0], puntosYRotados[0], magenta);

        int[] puntosXRelleno = {puntosXRotados[0] + 1, puntosXRotados[1] - 1, puntosXRotados[2] - 1, puntosXRotados[3] + 1};
        int[] puntosYRelleno = {puntosYRotados[0] + 1, puntosYRotados[1] + 1, puntosYRotados[2] - 1, puntosYRotados[3] - 1};
        RectanguloRelleno(g, puntosXRelleno, puntosYRelleno, orange);
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

    public void RectanguloRelleno(Graphics g, int[] x, int[] y, Color c) {
    int minY = y[0];
    int maxY = y[0];

    for (int i = 1; i < 4; i++) {
        if (y[i] < minY) {
            minY = y[i];
        }
        if (y[i] > maxY) {
            maxY = y[i];
        }
    }

    for (int j = minY; j <= maxY; j++) {
        int[] intersecciones = new int[4];
        int interseccionesCount = 0;

        for (int i = 0; i < 4; i++) {
            int next = (i + 1) % 4;
            if ((y[i] < j && y[next] >= j) || (y[i] > j && y[next] <= j)) {
                intersecciones[interseccionesCount++] = (int) (x[i] + (x[next] - x[i]) * (j - y[i]) / (y[next] - y[i]));
            }
        }

        if (interseccionesCount >= 2) {
            Arrays.sort(intersecciones, 0, interseccionesCount);

            Linea(g, intersecciones[0], j, intersecciones[1], j, c);
        }
    }
}


    @Override
    public void actionPerformed(ActionEvent e) {
        angulo += velocidad;

        //if (Math.toDegrees(angulo) >= 45) {
        //    timer.stop();
        //}

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotacion Uniforme con Detención");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        RotacionNH rotacion = new RotacionNH();
        frame.add(rotacion);
        frame.setVisible(true);
    }
}
