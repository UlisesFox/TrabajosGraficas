import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class mm extends JPanel {
    private List<Point> points;
    private Point startPoint;
    private Point endPoint;
    private Rectangle clipRectangle;

    public mm() {
        points = new ArrayList<>();
        setBackground(Color.WHITE);
        clipRectangle = new Rectangle(200, 100, 1200, 600);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                points.add(startPoint);
                points.add(endPoint);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Recortes(g);

        g.setColor(Color.BLACK);
        for (int i = 0; i < points.size(); i += 2) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            if (clipLine(p1, p2)) {
                Rectangulo(g, p1.x, p1.y, p2.x, p2.y, Color.black);
            }
        }
    }

    private boolean clipLine(Point p1, Point p2) {
        int x0 = p1.x;
        int y0 = p1.y;
        int x1 = p2.x;
        int y1 = p2.y;

        int code1 = computeOutCode(x0, y0);
        int code2 = computeOutCode(x1, y1);

        boolean accept = false;

        while (true) {
            if ((code1 | code2) == 0) {
                accept = true;
                break;
            } else if ((code1 & code2) != 0) {
                break;
            } else {
                int codeOut = (code1 != 0) ? code1 : code2;

                double x, y;

                if ((codeOut & TOP) != 0) {
                    x = x0 + (x1 - x0) * (clipRectangle.y - y0) / (y1 - y0);
                    y = clipRectangle.y;
                } else if ((codeOut & BOTTOM) != 0) {
                    x = x0 + (x1 - x0) * (clipRectangle.y + clipRectangle.height - y0) / (y1 - y0);
                    y = clipRectangle.y + clipRectangle.height;
                } else if ((codeOut & RIGHT) != 0) {
                    y = y0 + (y1 - y0) * (clipRectangle.x + clipRectangle.width - x0) / (x1 - x0);
                    x = clipRectangle.x + clipRectangle.width;
                } else if ((codeOut & LEFT) != 0) {
                    y = y0 + (y1 - y0) * (clipRectangle.x - x0) / (x1 - x0);
                    x = clipRectangle.x;
                } else {
                    x = 0;
                    y = 0;
                }

                if (codeOut == code1) {
                    x0 = (int) x;
                    y0 = (int) y;
                    code1 = computeOutCode(x0, y0);
                } else {
                    x1 = (int) x;
                    y1 = (int) y;
                    code2 = computeOutCode(x1, y1);
                }
            }
        }

        if (accept) {
            p1.setLocation(x0, y0);
            p2.setLocation(x1, y1);
        }

        return accept;
    }

    private int computeOutCode(int x, int y) {
        int code = 0;
        if (y < clipRectangle.y) {
            code |= TOP;
        } else if (y > clipRectangle.y + clipRectangle.height) {
            code |= BOTTOM;
        }
        if (x > clipRectangle.x + clipRectangle.width) {
            code |= RIGHT;
        } else if (x < clipRectangle.x) {
            code |= LEFT;
        }
        return code;
    }

    private static final int TOP = 1;
    private static final int BOTTOM = 2;
    private static final int RIGHT = 4;
    private static final int LEFT = 8;

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    private void Recortes(Graphics g) {
        Rectangulo(g, 200, 100, 1400, 700, Color.MAGENTA);
        RectanguloRelleno(g, 201, 101, 1399, 699, Color.ORANGE);
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujo en Rectángulo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mm recorte = new mm();
        frame.add(recorte);
        frame.setVisible(true);
    }
}