import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Casita extends JFrame {

    JPanel panel;
    Color blancogris = new Color(190, 190, 190);
    Color gray = new Color(0, 7, 74);
    Color green = new Color(36, 79, 32);
    Color nubes = new Color(110, 110, 110);
    Color cafe = new Color(56, 36, 32);
    Color hojas = new Color(17, 84, 13);
    Color tras = new Color(8, 41, 6);
    Color cas1 = new Color(102, 24, 9);
    Color cas2 = new Color(43, 0, 89);
    Color cas3 = new Color(69, 42, 29);
    Color cas4 = new Color(255, 255, 85);
    Color cas5 = new Color(89, 54, 37);

    Random random = new Random();
    int numCircles = 500;

    public Casita(){
        initPantalla();

    }

    @Override
    public void paint(Graphics g){
        super.paint(g);

        g.setColor(gray);
        g.fillRect(0, 0, 2000, 2000);

        for (int i = 0; i < numCircles; i++) {
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int radius = random.nextInt(2) + 5;
                    
            g.setColor(Color.WHITE);
            g.fillOval(x, y, radius * 2, radius * 2);
        }

        g.setColor(green);
        g.fillRect(0, 670, 2000, 300);

        g.setColor(Color.WHITE);
        g.fillOval(1300, 100, 150, 150);

        g.setColor(blancogris);
        g.fillOval(1325, 110, 50, 50);
        g.fillOval(1305, 180, 50, 50);
        g.fillOval(1380, 190, 50, 50);

        g.setColor(gray);
        g.fillOval(1360, 80, 150, 150);

        g.setColor(nubes);
        g.fillOval(100, 80, 100, 100);
        g.fillOval(160, 60, 100, 100);
        g.fillOval(230, 80, 100, 100);
        g.fillOval(500, 110, 100, 100);
        g.fillOval(560, 140, 100, 100);
        g.fillOval(620, 110, 100, 100);
        g.fillOval(900, 80, 100, 100);
        g.fillOval(970, 60, 100, 100);
        g.fillOval(1040, 80, 100, 100);
        g.fillOval(1250, 190, 100, 100);
        g.fillOval(1310, 205, 100, 100);

        g.setColor(cafe);
        g.fillRect(30, 420, 80, 300);
        g.fillRect(170, 390, 80, 300);
        g.fillRect(310, 440, 80, 300);
        g.fillRect(450, 390, 80, 300);
        g.fillRect(590, 420, 80, 300);
        g.fillRect(730, 390, 80, 300);
        g.fillRect(870, 420, 80, 300);
        g.fillRect(1010, 390, 80, 300);
        g.fillRect(1150, 420, 80, 300);
        g.fillRect(1290, 390, 80, 300);
        g.fillRect(1430, 420, 80, 300);

        g.setColor(green);
        g.fillOval(27, 700, 45, 50);
        g.fillOval(70, 700, 45, 50);
        g.fillOval(167, 670, 45, 50);
        g.fillOval(212, 670, 45, 50);
        g.fillOval(307, 720, 45, 50);
        g.fillOval(350, 720, 45, 50);
        g.fillOval(447, 670, 45, 50);
        g.fillOval(492, 670, 45, 50);
        g.fillOval(587, 700, 45, 50);
        g.fillOval(632, 700, 45, 50);
        g.fillOval(727, 670, 45, 50);
        g.fillOval(772, 670, 45, 50);
        g.fillOval(867, 700, 45, 50);
        g.fillOval(912, 700, 45, 50);
        g.fillOval(1007, 670, 45, 50);
        g.fillOval(1052, 670, 45, 50);
        g.fillOval(1147, 700, 45, 50);
        g.fillOval(1192, 700, 45, 50);
        g.fillOval(1287, 670, 45, 50);
        g.fillOval(1332, 670, 45, 50);
        g.fillOval(1427, 700, 45, 50);
        g.fillOval(1472, 700, 45, 50);

        g.setColor(tras);
        g.fillOval(-50, 420, 140, 140);
        g.fillOval(60, 420, 140, 140);
        g.fillOval(180, 420, 140, 140);
        g.fillOval(300, 420, 140, 140);
        g.fillOval(420, 420, 140, 140);
        g.fillOval(540, 420, 140, 140);
        g.fillOval(660, 420, 140, 140);
        g.fillOval(780, 420, 140, 140);
        g.fillOval(900, 420, 140, 140);
        g.fillOval(1020, 420, 140, 140);
        g.fillOval(1140, 420, 140, 140);
        g.fillOval(1260, 420, 140, 140);
        g.fillOval(1380, 420, 140, 140);
        g.fillOval(1500, 420, 140, 140);

        g.setColor(hojas);
        g.fillRect(0, 390, 2000, 130);

        g.setColor(tras);
        g.fillOval(-50, 350, 140, 140);
        g.fillOval(60, 350, 140, 140);
        g.fillOval(180, 350, 140, 140);
        g.fillOval(300, 350, 140, 140);
        g.fillOval(420, 350, 140, 140);
        g.fillOval(540, 350, 140, 140);
        g.fillOval(660, 350, 140, 140);
        g.fillOval(780, 350, 140, 140);
        g.fillOval(900, 350, 140, 140);
        g.fillOval(1020, 350, 140, 140);
        g.fillOval(1140, 350, 140, 140);
        g.fillOval(1260, 350, 140, 140);
        g.fillOval(1380, 350, 140, 140);
        g.fillOval(1500, 350, 140, 140);

        g.setColor(hojas);
        g.fillOval(0, 350, 140, 140);
        g.fillOval(120, 350, 140, 140);
        g.fillOval(240, 350, 140, 140);
        g.fillOval(360, 350, 140, 140);
        g.fillOval(480, 350, 140, 140);
        g.fillOval(600, 350, 140, 140);
        g.fillOval(720, 350, 140, 140);
        g.fillOval(840, 350, 140, 140);
        g.fillOval(960, 350, 140, 140);
        g.fillOval(1080, 350, 140, 140);
        g.fillOval(1200, 350, 140, 140);
        g.fillOval(1320, 350, 140, 140);
        g.fillOval(1440, 350, 140, 140);

        g.setColor(hojas);
        g.fillOval(0, 420, 140, 140);
        g.fillOval(120, 420, 140, 140);
        g.fillOval(240, 420, 140, 140);
        g.fillOval(360, 420, 140, 140);
        g.fillOval(480, 420, 140, 140);
        g.fillOval(600, 420, 140, 140);
        g.fillOval(720, 420, 140, 140);
        g.fillOval(840, 420, 140, 140);
        g.fillOval(960, 420, 140, 140);
        g.fillOval(1080, 420, 140, 140);
        g.fillOval(1200, 420, 140, 140);
        g.fillOval(1320, 420, 140, 140);
        g.fillOval(1440, 420, 140, 140);
        
        g.setColor(cas1);
        g.fillRect(550, 480, 400, 380);

        int[] xPoints = {500, 740, 1000};
        int[] yPoints = {500, 350, 500};
        g.setColor(cas2);
        g.fillPolygon(xPoints, yPoints, 3);

        g.setColor(cas3);
        g.fillRect(700, 660, 100, 200);
        g.setColor(cas5);
        g.fillRect(710, 670, 35, 75);
        g.fillRect(755, 670, 35, 75);
        g.fillRect(710, 755, 35, 98);
        g.fillRect(755, 755, 35, 98);

        g.setColor(cas3);
        g.fillOval(705, 710, 90, 90);
        g.setColor(cas5);
        g.fillOval(713, 718, 75, 75);

        g.setColor(Color.DARK_GRAY);
        g.fillOval(780, 743, 25, 25);

        g.setColor(cas3);
        g.fillOval(575, 520, 120, 120);
        g.setColor(cas4);
        g.fillOval(585, 528, 100, 100);
        g.setColor(cas3);
        g.fillRect(577, 565, 110, 25);
        g.fillRect(623, 525, 25, 110);

        g.setColor(cas3);
        g.fillOval(800, 520, 120, 120);
        g.setColor(cas4);
        g.fillOval(810, 528, 100, 100);
        g.setColor(cas3);
        g.fillRect(805, 565, 110, 25);
        g.fillRect(847, 525, 25, 110);

        g.setColor(cas3);
        g.fillOval(695, 380, 100, 100);
        g.setColor(cas4);
        g.fillOval(705, 390, 80, 80);
        g.setColor(cas3);
        g.fillRect(705, 416, 80, 25);
        g.fillRect(733, 390, 25, 80);

        g.setColor(green);
        g.fillOval(540, 820, 85, 60);
        g.fillOval(580, 790, 80, 60);
        g.fillOval(620, 820, 80, 60);
        g.fillOval(800, 820, 85, 60);
        g.fillOval(840, 790, 80, 60);
        g.fillOval(870, 820, 80, 60);
    }

    private void initPantalla(){

        setTitle("Casita");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Casita();
    }
}
