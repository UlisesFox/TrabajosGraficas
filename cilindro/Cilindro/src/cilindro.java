import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class cilindro extends JPanel{
    static double uuuuu;
    static double uuuu;
    static double vvvv;
    static double uuu;
    static double vvv;
    static double uu;
    static double vv;
    static double u;
    static double v;
    static double u1;
    static double v1;
    static double x;
    static double xx;
    static double y;
    static double yy;
    static double w;
    static double ww;

    public static void main(String[] args) {
        JFrame frame = new JFrame("cilindro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cilindro cil = new cilindro();
        frame.add(cil);
        frame.setVisible(true);

        System.out.println("U");
        while (uu < 40) {
            uu += 1;
            u = u + u1;
            System.out.println(u);
        }
        
        System.out.println("");
        System.out.println("V");
        while (vv < 40) {
            vv += 1;
            v = v + v1;
            System.out.println(v);
        }

        System.out.println("");
        System.out.println("X");
        while (x < 40){
            x += 1;
            uuu = uuu + u1;
            vvv = vvv + v1;
            xx = uuu * Math.cos(vvv);
            uuuu = uuuu + u1;
            vvvv = vvvv + v1;
            yy = uuuu * Math.sin(vvvv);
            System.out.println(xx);
        }

        System.out.println("");
        System.out.println("Y");
        while (y < 40){
            y += 1;
            uuuu = uuuu + u1;
            vvvv = vvvv + v1;
            yy = uuuu * Math.sin(vvvv);
            System.out.println(yy);
        }

        System.out.println("");
        System.out.println("U");
        while (ww < 40){
            ww += 1;
            uuuuu = uuuuu + u1;
            w = Math.pow(uuuuu, 2);
            System.out.println(w);
        }
    }  

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        colocar(g);
    }

    public void pix(Graphics g, double x, double y, Color c) {
        int roundedX = (int) Math.round(x);
        int roundedY = (int) Math.round(y);
        g.setColor(c);
        g.fillRect(roundedX, roundedY, 10, 10);
    }
    
    public void colocar(Graphics g){
        u1 = 2.5 / 40;
        v1 = (2*Math.PI) / 40;

        while (x < 40){
            x += 1;
            uuu = uuu + u1;
            vvv = vvv + v1;
            xx = uuu * Math.cos(vvv);
            uuuu = uuuu + u1;
            vvvv = vvvv + v1;
            yy = uuuu * Math.sin(vvvv);
            
        }
        
        pix(g, xx, yy, Color.BLACK);
    }
}