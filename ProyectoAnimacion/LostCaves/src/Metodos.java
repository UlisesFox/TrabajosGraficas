import java.awt.Color;
import java.awt.Graphics;

public class Metodos {
    void pix(Graphics g, int x, int y, Color c) {
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

    public void LineaDDA(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(g, Math.round(x), Math.round(y), color);
            x += xIncrement;
            y += yIncrement;
        }
    }

    public void LineaBresenham(Graphics g, int x0, int y0, int x1, int y1, Color c) {
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

        while (true) {
            pix(g, x0, y0, c);

            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
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

    public void CirculoPuntoMedio(Graphics g, int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int error = 1 - r;

        while (x >= y) {
            pix(g, xc + x, yc + y, c);
            pix(g, xc - x, yc + y, c);
            pix(g, xc + x, yc - y, c);
            pix(g, xc - x, yc - y, c);
            pix(g, xc + y, yc + x, c);
            pix(g, xc - y, yc + x, c);
            pix(g, xc + y, yc - x, c);
            pix(g, xc - y, yc - x, c);

            y++;

            if (error > 0) {
                x--;
                error += 2 * (y - x) + 1;
            } else {
                error += 2 * y + 1;
            }
        }
    }

    public void Circulo(Graphics g, int xc, int yc, int radio, Color c) {
        for (int x = xc - radio; x <= xc + radio; x++) {
            int y1 = yc + (int) Math.sqrt(radio * radio - (x - xc) * (x - xc));
            int y2 = yc - (int) Math.sqrt(radio * radio - (x - xc) * (x - xc));
            pix(g, x, y1, c);
            pix(g, x, y2, c);
        }
    }

    public void Rectangulo(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        LineaMedia(g, x0, y0, x1, y0, c);
        LineaMedia(g, x1, y0, x1, y1, c);
        LineaMedia(g, x1, y1, x0, y1, c);
        LineaMedia(g, x0, y1, x0, y0, c);
    }

    public void Elipse(Graphics g, int xc, int yc, int a, int b, Color c) {
        int a2 = a * a;
        int b2 = b * b;
        int twoa2 = 2 * a2;
        int twob2 = 2 * b2;
        int p;
        int x = 0;
        int y = b;
        int px = 0;
        int py = twoa2 * y;

        pix(g, xc, yc + y, c);
        pix(g, xc, yc - y, c);

        p = (int) (b2 - (a2 * b) + (0.25 * a2));
        
        while (px < py) {
            x++;
            px += twob2;
            if (p < 0) {
                p += b2 + px;
            } else {
                y--;
                py -= twoa2;
                p += b2 + px - py;
            }
            pix(g, xc + x, yc + y, c);
            pix(g, xc - x, yc + y, c);
            pix(g, xc + x, yc - y, c);
            pix(g, xc - x, yc - y, c);
        }

        p = (int) (b2 * (x + 0.5) * (x + 0.5) + a2 * (y - 1) * (y - 1) - a2 * b2);
        
        while (y > 0) {
            y--;
            py -= twoa2;
            if (p > 0) {
                p += a2 - py;
            } else {
                x++;
                px += twob2;
                p += a2 - py + px;
            }
            pix(g, xc + x, yc + y, c);
            pix(g, xc - x, yc + y, c);
            pix(g, xc + x, yc - y, c);
            pix(g, xc - x, yc - y, c);
        }
    }

    public void RectanguloRelleno(Graphics g, int x0, int y0, int x1, int y1, Color c) {
        for (int y = y0; y <= y1; y++) {
            LineaMedia(g, x1, y, x0, y, c);
        }
    }

    public void CirculoPuntoMedioRelleno(Graphics g, int xc, int yc, int r, Color c) {
        for (int y = -r; y <= r; y++) {
            for (int x = -r; x <= r; x++) {
                if (x * x + y * y <= r * r) {
                    pix(g, xc + x, yc + y, c);
                }
            }
        }
    }   

    public void Triangulonot(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color c){
        LineaMedia(g, x0, y0, x1, y1, c);
        LineaMedia(g, x1, y1, x2, y2, c);
        LineaMedia(g, x2, y2, x0, y0, c);
    }
    
    public void Triangulo(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color c){
        LineaMedia(g, x0, y0, x1, y1, c);
        LineaMedia(g, x1, y1, x2, y2, c);
        LineaMedia(g, x2, y2, x0, y0, c);

        RellenoTriangulo(g, x0, y0, x1, y1, x2, y2, c);
    }

    public void RellenoTriangulo(Graphics g, int x0, int y0, int x1, int y1, int x2, int y2, Color c) {
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

        RellenoTriangulo(g, x2, y2, x3, y3, x4, y4, c);
        RellenoTriangulo(g, x2, y2, x1, y1, x4, y4, c);
    }

    public void infinito(Graphics g, int startX, int startY, int scale, Color c){
        for (double t = 0; t <= 35 * Math.PI; t += 0.001) {
            double x = calcularXI(t) * scale + startX;
            double y = -calcularYI(t) * scale + startY;
            pix(g, (int) x, (int) y, c);
        }
    }

    public double calcularXI(double t) {
        double r = 1;
        return (r * Math.sin(t)) / (1 + Math.pow(Math.cos(t), 2));
    }
    
    public double calcularYI(double t) {
        double r = 1;
        return (r * Math.sin(t) * Math.cos(t)) / (1 + Math.pow(Math.cos(t), 2));
    }

    public void Curva(Graphics g, int x0, int y0, int x1, int y1, int pasos, double inicio, Color c) {
        double ultimo = Math.PI;
        double pas = (ultimo - inicio) / (pasos - 1);

        int[] arrayx = new int[pasos];
        int[] arrayy = new int[pasos];

        for (int i = 0; i < pasos; i++) {
            double x = inicio + pas * i;
            double y = Math.sin(x);
            arrayx[i] = (int) Math.round(x0 + (x - inicio) * (x1 - x0) / (ultimo - inicio));
            arrayy[i] = (int) Math.round(y0 - (y * 300));
        }

        for (int i = 0; i < pasos - 1; i++) {
            LineaMedia(g, arrayx[i], arrayy[i], arrayx[i + 1], arrayy[i + 1], c);
        }
    }

    public void Tornado(Graphics g, int x0, int y0, int x1, int y1, int pasos, double inicio, Color c) {
        double ultimo = Math.PI * 7;
        double pas = (ultimo - inicio) / (pasos - 1);

        int[] arrayx = new int[pasos];
        int[] arrayy = new int[pasos];

        for (int i = 0; i < pasos; i++) {
            double x = inicio + pas * i;
            double y = Math.sin(x) * 5 * (i / (double) pasos);
            arrayx[i] = (int) Math.round(x0 - (y * 5));
            arrayy[i] = (int) Math.round(y0 + (x - inicio) * (y1 - y0) / (ultimo - inicio));
        }

        for (int i = 0; i < pasos - 1; i++) {
            LineaMedia(g, arrayx[i], arrayy[i], arrayx[i + 1], arrayy[i + 1], c);
        }
    }

    public void Resorte(Graphics g, int startX, int startY, int scale, Color c){
        for (double t = 0; t <= 35 * Math.PI; t += 0.001) {
            double x = calcularXR(t) * scale + startX;
            double y = -calcularYR(t) * scale + startY;
            pix(g, (int) x, (int) y, c);
        }
    }

    public double calcularXR(double t) {
        return t - 3 * Math.sin(t);
    }

    public double calcularYR(double t) {
        return 4 - 3 * Math.cos(t);
    }

    public void Flor(Graphics g, int startX, int startY, int scale, Color c){
        for (double t = 0; t <= 35 * Math.PI; t += 0.001) {
            double x = calcularXF(t) * scale + startX;
            double y = -calcularYF(t) * scale + startY;
            pix(g, (int) x, (int) y, c);
        }
    }

    public double calcularXF(double t) {
        return Math.cos(t) + (1.0 / 2.0) * Math.cos(7 * t) + (1.0 / 3.0) * Math.sin(17 * t);
    }
    
    public double calcularYF(double t) {
        return Math.sin(t) + (1.0 / 2.0) * Math.sin(7 * t) + (1.0 / 3.0) * Math.cos(17 * t);
    }

    public void Sol(Graphics g, double startX, double startY, double scale, Color c){
        for (double t = 0; t <= 35 * Math.PI; t += 0.001) {
            double x = calcularXS(t) * scale + startX;
            double y = -calcularYS(t) * scale + startY;
            pix(g, (int) x, (int) y, c);
        }
    }

    public double calcularXS(double t) {
        return 17 * Math.cos(t) + 7 * Math.cos((17.0 / 7.0) * t);
    }
    
    public double calcularYS(double t) {
        return 17 * Math.sin(t) - 7 * Math.sin((17.0 / 7.0) * t);
    }

    public void Mallado(Graphics g, int x0, int y0, int x1, int y1, int paso, Color c) {
        for (int x = x0; x <= x1; x += paso) {
            LineaMedia(g, x, y0, x, y1, c);
        }

        for (int y = y0; y <= y1; y += paso) {
            LineaMedia(g, x0, y, x1, y, c);
        }
    }
}
