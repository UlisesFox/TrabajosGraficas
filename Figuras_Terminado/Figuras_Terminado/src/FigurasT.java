import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FigurasT extends JFrame{

    private BufferedImage buffer;
    JPanel jPanel;

    public FigurasT(){
        jPanel = new JPanel();
        add(jPanel);

        setTitle("Ventana Figuras Terminado");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
    }

    //Pixel
    public void pix(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    //Linea Basica
    public void Linea(int x1, int y1, int x2, int y2, Color c) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(Math.round(x), Math.round(y), c);
            x += xIncrement;
            y += yIncrement;
        }
    }

    //Linea DDA
    public void LineaDDA(int x1, int y1, int x2, int y2, Color color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pix(Math.round(x), Math.round(y), color);
            x += xIncrement;
            y += yIncrement;
        }
    }

    //Linea Bresenham
    public void LineaBresenham(int x0, int y0, int x1, int y1, Color c) {
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
            pix(x0, y0, c);

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

    //Linea media
    public void LineaMedia(int x0, int y0, int x1, int y1, Color c) {
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
            pix(x, y, c);

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

    //Circulo punto medio
    public void CirculoPuntoMedio(int xc, int yc, int r, Color c) {
        int x = r;
        int y = 0;
        int error = 1 - r;

        while (x >= y) {
            pix(xc + x, yc + y, c);
            pix(xc - x, yc + y, c);
            pix(xc + x, yc - y, c);
            pix(xc - x, yc - y, c);
            pix(xc + y, yc + x, c);
            pix(xc - y, yc + x, c);
            pix(xc + y, yc - x, c);
            pix(xc - y, yc - x, c);

            y++;

            if (error > 0) {
                x--;
                error += 2 * (y - x) + 1;
            } else {
                error += 2 * y + 1;
            }
        }
    }

    //Circulo basico
    public void Circulo(int xc, int yc, int radio) {
        for (int x = xc - radio; x <= xc + radio; x++) {
            int y1 = yc + (int) Math.sqrt(radio * radio - (x - xc) * (x - xc));
            int y2 = yc - (int) Math.sqrt(radio * radio - (x - xc) * (x - xc));
            pix(x, y1, Color.red);
            pix(x, y2, Color.red);
        }
    }

    //Rectangulo
    public void Rectangulo(int x0, int y0, int x1, int y1, Color c) {
        LineaMedia(x0, y0, x1, y0, c);
        LineaMedia(x1, y0, x1, y1, c);
        LineaMedia(x1, y1, x0, y1, c);
        LineaMedia(x0, y1, x0, y0, c);
    }

    //Elipse
    public void Elipse(int xc, int yc, int a, int b, Color c) {
        int a2 = a * a;
        int b2 = b * b;
        int twoa2 = 2 * a2;
        int twob2 = 2 * b2;
        int p;
        int x = 0;
        int y = b;
        int px = 0;
        int py = twoa2 * y;

        pix(xc, yc + y, c);
        pix(xc, yc - y, c);

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
            pix(xc + x, yc + y, c);
            pix(xc - x, yc + y, c);
            pix(xc + x, yc - y, c);
            pix(xc - x, yc - y, c);
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
            pix(xc + x, yc + y, c);
            pix(xc - x, yc + y, c);
            pix(xc + x, yc - y, c);
            pix(xc - x, yc - y, c);
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        LineaDDA(80, 80, 160, 160, Color.red);
        Linea(280, 120, 480, 120, Color.magenta);
        LineaBresenham(600, 190, 650, 80, Color.green);
        LineaMedia(1000, 120, 800, 120, Color.cyan);
        CirculoPuntoMedio(200, 400, 100, Color.green);
        Circulo(200, 400, 75);
        CirculoPuntoMedio(200, 400, 50, Color.green);
        Circulo(200, 400, 25);
        Rectangulo(380, 300, 700, 500, Color.blue);
        Rectangulo(420, 350, 660, 450, Color.cyan);
        Elipse(970, 400, 195, 95, Color.magenta);
        Elipse(970, 400, 170, 70, Color.orange);
        Elipse(970, 400, 145, 45, Color.magenta);
        Elipse(970, 400, 120, 20, Color.orange);
    }

    public static void main(String[] args) {
        new FigurasT();
    }
}