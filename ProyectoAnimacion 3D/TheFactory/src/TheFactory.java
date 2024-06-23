import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TheFactory extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("The Factory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        TheFactory fabrica = new TheFactory();
        frame.add(fabrica);
        frame.setVisible(true);
    }

    Escalacion3D esc = new Escalacion3D();
    Rotacion3D rot = new Rotacion3D();
    Relleno3D rel = new Relleno3D();
    Metodos meto = new Metodos();
    Colores color = new Colores();
    private BufferedImage capa, capa2, capa3, capa4;
    private int xPosition = 0;
    private int currentScene = 0;
    private Timer timer = new Timer();
    private int targetx = -350;
    private int targetX = 5200;
    private int targetx2 = -350;

    public TheFactory() {
        esca();
        rota();
        scheduleSceneChange();
        capa = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        cajas(capa.getGraphics());
        capa2 = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        cajas2(capa2.getGraphics());
        capa3 = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        cajas3(capa3.getGraphics());
        capa4 = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        cajas4(capa4.getGraphics());
        iniciarAnimacion();
    }

    public void iniciarAnimacion() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moverCapa();
                moverCapa2();
                moverCapa3();
                moverCapa4();
                repaint();
            }
        }, 0, 35);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentScene == 0) {
            FondoFabricaC(g);
            luz(g);
            carteles(g);
            apagon(g);
        } else if (currentScene == 1){
            FondoFabricaC(g);
            apagon(g);
        } else if (currentScene == 2){
            FondoFabricaC(g);
            luz(g);
            carteles(g);
            dibujarCapa4(g);
        } else if (currentScene == 3){
            FondoFabricaC(g);
            apagon(g);
        } else if (currentScene == 4){
            FondoFabricaC(g);
            luz(g);
            carteles(g);
            dibujarCapa4(g);
        } else if (currentScene == 5){
            FondoFabricaC(g);
            apagon(g);
        } else if (currentScene == 6){
            FondoFabricaC(g);
            luz(g);
            carteles(g);
            dibujarCapa3(g);
        } else if (currentScene == 7){
            FondoFabricaC(g);
            apagon(g);
        } else if (currentScene == 8){
            FondoFabricaC(g);
            luz(g);
            carteles(g);
            dibujarCapa3(g);
        } else if (currentScene == 9){
            FondoFabricaC(g);
            apagon(g);
        } else if (currentScene == 10){
            FondoFabrica(g);
            luz(g);
            carteles2(g);
            dibujarCapa2(g);
        } else if (currentScene == 11){
            FondoFabrica(g);
            apagon(g);
        } else if (currentScene == 12){
            FondoFabrica(g);
            luz(g);
            carteles2(g);
            dibujarCapa2(g);
        } else if (currentScene == 13){
            FondoFabrica(g);
            apagon(g);
        } else if (currentScene == 14){
            FondoFabrica(g);
            luz(g);
            carteles3(g);
            dibujarCapa(g);
        } else if (currentScene == 15){
            FondoFabrica(g);
            apagon(g);
        } else if (currentScene == 16){
            FondoFabrica(g);
            luz(g);
            carteles3(g);
            dibujarCapa(g);
        } else if (currentScene == 17){
            FondoFabrica(g);
            apagon(g);
        }
    }

    private void scheduleSceneChange() {
        int delay = 0;
    
        if (currentScene == 0) {
            currentScene = 1;
            delay = 1000;
        } else if (currentScene == 1){
            currentScene = 2;
            delay = 1000;
        } else if (currentScene == 2){
            currentScene = 3;
            delay = 1000;
        } else if (currentScene == 3){
            currentScene = 4;
            delay = 35000;//cajas1 
        } else if (currentScene == 4){
            currentScene = 5;
            delay = 1000; 
        } else if (currentScene == 5){
            currentScene = 6;
            delay = 1000;
        } else if (currentScene == 6){
            currentScene = 7;
            delay = 1000;
        } else if (currentScene == 7){
            currentScene = 8;
            delay = 34000;//cajas2
        } else if  (currentScene == 8){
            currentScene = 9;
            delay = 1000;
        } else if (currentScene == 9){
            currentScene = 10;
            delay = 1000;
        } else if (currentScene == 10){
            currentScene = 11;
            delay = 1000;
        } else if (currentScene == 11){
            currentScene = 12;
            delay = 34000;//cajas3
        } else if  (currentScene == 12){
            currentScene = 13;
            delay = 1000;
        } else if (currentScene == 13){
            currentScene = 14;
            delay = 1000;
        } else if (currentScene == 14){
            currentScene = 15;
            delay = 1000;
        } else if (currentScene == 15){
            currentScene = 16;
            delay = 34000;//cajas4
        } else if (currentScene == 16){
            currentScene = 17;
            delay = 1000;
        }
    
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                scheduleSceneChange();
                repaint();
            }
        }, delay);
    }

    private void rota(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (targetx < targetX) {
                    rot.mov();
                    rot.Traslacion();
                    repaint();
                } else if (targetX > targetx2){
                    rot.mov2();
                    rot.Traslacion();
                    repaint();
                }else{
                    targetx = -350;
                    targetX = 5200;
                    targetx2 = -350;
                }
            }
        }, 5);
    }

    private void esca(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (esc.puntoP[0] < targetX) {
                    esc.mov();
                    esc.Traslacion();
                    repaint();
                }
            }
        }, 5);
    }

    private void moverCapa() {
        xPosition -= 1;

        if (xPosition <= -1540) {
            xPosition = 0;
        }
    }

    public void dibujarCapa(Graphics g) {
        for (int i = xPosition; i < getWidth(); i += 1540) {
            g.drawImage(capa, i, 0, null);
        }
    }

    private void moverCapa2() {
        xPosition -= 1;

        if (xPosition <= -1540) {
            xPosition = 0;
        }
    }

    public void dibujarCapa2(Graphics g) {
        for (int i = xPosition; i < getWidth(); i += 1540) {
            g.drawImage(capa2, i, 0, null);
        }
    }

    private void moverCapa3() {
        xPosition -= 1;

        if (xPosition <= -1540) {
            xPosition = 0;
        }
    }

    public void dibujarCapa3(Graphics g) {
        for (int i = xPosition; i < getWidth(); i += 1540) {
            g.drawImage(capa3, i, 0, null);
        }
    }

    private void moverCapa4() {
        xPosition -= 1;

        if (xPosition <= -1540) {
            xPosition = 0;
        }
    }

    public void dibujarCapa4(Graphics g) {
        for (int i = xPosition; i < getWidth(); i += 1540) {
            g.drawImage(capa4, i, 0, null);
        }
    }

    public void apagon(Graphics g){
        meto.RectanguloRelleno(g, 0, 0, 1570, 870, Color.black);
    }

    public void FondoFabrica(Graphics g){
        rel.fillCube(g, color.GrisParedF);
        meto.RellenoTriangulo(g, -200, 870, 22, 70, 450, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 22, 870, 400, 50, 700, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 22, 50, 250, 870, 400, 50, color.GrisParedFL);
        meto.RellenoTriangulo(g, 250, 870, 580, 60, 1200, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 250, 870, 950, 50, 1250, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 580, 60, 500, 870, 940, 50, color.GrisParedFL);
        meto.RellenoTriangulo(g, 850, 870, 1125, 60, 1600, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 850, 870, 1500, 50, 1800, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 1125, 60, 1300, 870, 1500, 50, color.GrisParedFL);
        meto.RectanguloRelleno(g, 0, 790, 1570, 870, color.GrisParedF);
        rel.Cube(g, Color.black);
        meto.RectanguloRelleno(g, 620, 480, 680, 870, color.GrisParedF);
        meto.RectanguloRelleno(g, 640, 480, 700, 870, color.GrisParedF);
        meto.Rectangulo(g, 620, 480, 680, 870, Color.black);
        meto.Rectangulo(g, 640, 480, 700, 870, Color.black);
        rot.fillCube(g, color.GrisParedF);
        rot.Cube(g, Color.black);
        meto.RectanguloRelleno(g, 0, 600, 1600, 700, color.OscuroParedFL);
        meto.RectanguloRelleno(g, 0, 700, 1600, 720, color.CafeRelleno);
        meto.Linea(g, 0, 600, 1600, 600, Color.black);
        meto.Linea(g, 0, 700, 1600, 700, Color.black);
        meto.RectanguloRelleno(g, 80, 720, 120, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 180, 720, 220, 870, color.CafeRelleno);
        meto.Linea(g, 100, 720, 100, 870, Color.black);
        meto.Linea(g, 80, 720, 80, 870, Color.black);
        meto.Linea(g, 120, 720, 120, 870, Color.black);
        meto.Linea(g, 200, 720, 200, 870, Color.black);
        meto.Linea(g, 180, 720, 180, 870, Color.black);
        meto.Linea(g, 220, 720, 220, 870, Color.black);
        meto.RectanguloRelleno(g, 480, 720, 520, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 580, 720, 620, 870, color.CafeRelleno);
        meto.Linea(g, 500, 720, 500, 870, Color.black);
        meto.Linea(g, 480, 720, 480, 870, Color.black);
        meto.Linea(g, 520, 720, 520, 870, Color.black);
        meto.Linea(g, 600, 720, 600, 870, Color.black);
        meto.Linea(g, 580, 720, 580, 870, Color.black);
        meto.Linea(g, 620, 720, 620, 870, Color.black);
        meto.RectanguloRelleno(g, 880, 720, 920, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 980, 720, 1020, 870, color.CafeRelleno);
        meto.Linea(g, 900, 720, 900,  870, Color.black);
        meto.Linea(g, 880, 720, 880, 870, Color.black);
        meto.Linea(g, 920, 720, 920,  870, Color.black);
        meto.Linea(g, 1000, 720,1000, 870, Color.black);
        meto.Linea(g, 980, 720, 980,  870, Color.black);
        meto.Linea(g, 1020, 720,1020, 870, Color.black);
        meto.RectanguloRelleno(g, 1280, 720, 1320, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 1380, 720, 1420, 870, color.CafeRelleno);
        meto.Linea(g, 1300, 720, 1300, 870, Color.black);
        meto.Linea(g, 1280, 720, 1280, 870, Color.black);
        meto.Linea(g, 1320, 720, 1320, 870, Color.black);
        meto.Linea(g, 1400, 720, 1400, 870, Color.black);
        meto.Linea(g, 1380, 720, 1380, 870, Color.black);
        meto.Linea(g, 1420, 720, 1420, 870, Color.black);
        meto.Linea(g, 0, 720, 1600, 720, Color.black);
        luz(g);
    }

    public void FondoFabricaC(Graphics g){
        rel.fillCube(g, color.GrisParedF);
        meto.RellenoTriangulo(g, -200, 870, 22, 70, 450, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 22, 870, 400, 50, 700, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 22, 50, 250, 870, 400, 50, color.GrisParedFL);
        meto.RellenoTriangulo(g, 250, 870, 580, 60, 1200, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 250, 870, 950, 50, 1250, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 580, 60, 500, 870, 940, 50, color.GrisParedFL);
        meto.RellenoTriangulo(g, 850, 870, 1125, 60, 1600, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 850, 870, 1500, 50, 1800, 870, color.GrisParedFL);
        meto.RellenoTriangulo(g, 1125, 60, 1300, 870, 1500, 50, color.GrisParedFL);
        meto.RectanguloRelleno(g, 0, 790, 1570, 870, color.GrisParedF);
        rel.Cube(g, Color.black);
        meto.RectanguloRelleno(g, 620, 480, 680, 870, color.GrisParedF);
        meto.RectanguloRelleno(g, 640, 480, 700, 870, color.GrisParedF);
        meto.Rectangulo(g, 620, 480, 680, 870, Color.black);
        meto.Rectangulo(g, 640, 480, 700, 870, Color.black);
        rot.fillCube(g, color.GrisParedF);
        rot.Cube(g, Color.black);
        esc.fillCube(g, color.CafeRellenoO);
        esc.Cube(g, Color.black);
        meto.RectanguloRelleno(g, 0, 600, 1600, 700, color.OscuroParedFL);
        meto.RectanguloRelleno(g, 0, 700, 1600, 720, color.CafeRelleno);
        meto.Linea(g, 0, 600, 1600, 600, Color.black);
        meto.Linea(g, 0, 700, 1600, 700, Color.black);
        meto.RectanguloRelleno(g, 80, 720, 120, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 180, 720, 220, 870, color.CafeRelleno);
        meto.Linea(g, 100, 720, 100, 870, Color.black);
        meto.Linea(g, 80, 720, 80, 870, Color.black);
        meto.Linea(g, 120, 720, 120, 870, Color.black);
        meto.Linea(g, 200, 720, 200, 870, Color.black);
        meto.Linea(g, 180, 720, 180, 870, Color.black);
        meto.Linea(g, 220, 720, 220, 870, Color.black);
        meto.RectanguloRelleno(g, 480, 720, 520, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 580, 720, 620, 870, color.CafeRelleno);
        meto.Linea(g, 500, 720, 500, 870, Color.black);
        meto.Linea(g, 480, 720, 480, 870, Color.black);
        meto.Linea(g, 520, 720, 520, 870, Color.black);
        meto.Linea(g, 600, 720, 600, 870, Color.black);
        meto.Linea(g, 580, 720, 580, 870, Color.black);
        meto.Linea(g, 620, 720, 620, 870, Color.black);
        meto.RectanguloRelleno(g, 880, 720, 920, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 980, 720, 1020, 870, color.CafeRelleno);
        meto.Linea(g, 900, 720, 900,  870, Color.black);
        meto.Linea(g, 880, 720, 880, 870, Color.black);
        meto.Linea(g, 920, 720, 920,  870, Color.black);
        meto.Linea(g, 1000, 720,1000, 870, Color.black);
        meto.Linea(g, 980, 720, 980,  870, Color.black);
        meto.Linea(g, 1020, 720,1020, 870, Color.black);
        meto.RectanguloRelleno(g, 1280, 720, 1320, 870, color.CafeRelleno);
        meto.RectanguloRelleno(g, 1380, 720, 1420, 870, color.CafeRelleno);
        meto.Linea(g, 1300, 720, 1300, 870, Color.black);
        meto.Linea(g, 1280, 720, 1280, 870, Color.black);
        meto.Linea(g, 1320, 720, 1320, 870, Color.black);
        meto.Linea(g, 1400, 720, 1400, 870, Color.black);
        meto.Linea(g, 1380, 720, 1380, 870, Color.black);
        meto.Linea(g, 1420, 720, 1420, 870, Color.black);
        meto.Linea(g, 0, 720, 1600, 720, Color.black);
    }

    public void cajas(Graphics g) {
        BufferedImage columnaLayer = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = columnaLayer.createGraphics();

        meto.LineaBresenham(g2, 50, 600, 0, 700, Color.black);
        meto.LineaBresenham(g2, 150, 600, 100, 700, Color.black);
        meto.LineaBresenham(g2, 250, 600, 200, 700, Color.black);
        meto.LineaBresenham(g2, 350, 600, 300, 700, Color.black);
        meto.LineaBresenham(g2, 450, 600, 400, 700, Color.black);
        meto.LineaBresenham(g2, 550, 600, 500, 700, Color.black);
        meto.LineaBresenham(g2, 650, 600, 600, 700, Color.black);
        meto.LineaBresenham(g2, 750, 600, 700, 700, Color.black);
        meto.LineaBresenham(g2, 850, 600, 800, 700, Color.black);
        meto.LineaBresenham(g2, 950, 600, 900, 700, Color.black);
        meto.LineaBresenham(g2, 1050, 600, 1000, 700, Color.black);
        meto.LineaBresenham(g2, 1150, 600, 1100, 700, Color.black);
        meto.LineaBresenham(g2, 1250, 600, 1200, 700, Color.black);
        meto.LineaBresenham(g2, 1350, 600, 1300, 700, Color.black);
        meto.LineaBresenham(g2, 1450, 600, 1400, 700, Color.black);
        meto.RectanguloRelleno(g2, 50, 620, 150, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 80, 580, 180, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 50, 620, 90, 690, 80, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 150, 690, 150, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 80, 580, 180, 650, Color.black);
        meto.Linea(g2, 80, 580, 80, 650, color.CafeRellenoO);
        meto.Linea(g2, 80, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 50, 620, 150, 690, Color.black);
        meto.Linea(g2, 50, 620, 80, 580, Color.black);
        meto.Linea(g2, 150, 620, 180, 580, Color.black);
        meto.Linea(g2, 150, 690, 180, 650, Color.black);
        meto.RectanguloRelleno(g2, 250, 620, 350, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 280, 580, 380, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 250, 620, 290, 690, 280, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 350, 690, 350, 650, 380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 280, 580, 380, 650, Color.black);
        meto.Linea(g2, 280, 580, 280, 650, color.CafeRellenoO);
        meto.Linea(g2, 280, 650, 380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 250, 620, 350, 690, Color.black);
        meto.Linea(g2, 250, 620, 280, 580, Color.black);
        meto.Linea(g2, 350, 620, 380, 580, Color.black);
        meto.Linea(g2, 350, 690, 380, 650, Color.black);
        meto.RectanguloRelleno(g2, 450, 620, 550, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 480, 580, 580, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 450, 620, 490, 690, 480, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 550, 690, 550, 650, 580, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 480, 580, 580, 650, Color.black);
        meto.Linea(g2, 480, 580, 480, 650, color.CafeRellenoO);
        meto.Linea(g2, 480, 650, 580, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 450, 620, 550, 690, Color.black);
        meto.Linea(g2, 450, 620, 480, 580, Color.black);
        meto.Linea(g2, 550, 620, 580, 580, Color.black);
        meto.Linea(g2, 550, 690, 580, 650, Color.black);
        meto.RectanguloRelleno(g2, 650, 620, 750, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 680, 580, 780, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 650, 620, 690, 690, 680, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 690, 750, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 680, 580, 780, 650, Color.black);
        meto.Linea(g2, 680, 580, 680, 650, color.CafeRellenoO);
        meto.Linea(g2, 680, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 650, 620, 750, 690, Color.black);
        meto.Linea(g2, 650, 620, 680, 580, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 750, 690, 780, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 620, 850, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 580, 880, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 620, 790, 690, 780, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 690, 850, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 580, 880, 650, Color.black);
        meto.Linea(g2, 780, 580, 780, 650, color.CafeRellenoO);
        meto.Linea(g2, 780, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 620, 850, 690, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.Linea(g2, 850, 690, 880, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 550, 850, 620, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 510, 880, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 550, 790, 620, 780, 510, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 620, 850, 620, 880, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 510, 880, 580, Color.black);
        meto.Linea(g2, 780, 510, 780, 580, color.CafeRellenoO);
        meto.Linea(g2, 780, 580, 880, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 550, 850, 620, Color.black);
        meto.Linea(g2, 750, 550, 780, 510, Color.black);
        meto.Linea(g2, 850, 550, 880, 510, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.RectanguloRelleno(g2, 850, 620, 950, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 880, 580, 980, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 620, 890, 690, 880, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 950, 690, 950, 650, 980, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 880, 580, 980, 650, Color.black);
        meto.Linea(g2, 880, 580, 880, 650, color.CafeRellenoO);
        meto.Linea(g2, 880, 650, 980, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 850, 620, 950, 690, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.Linea(g2, 950, 620, 980, 580, Color.black);
        meto.Linea(g2, 950, 690, 980, 650, Color.black);
        meto.RectanguloRelleno(g2, 850, 550, 950, 620, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 880, 510, 980, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 550, 890, 620, 880, 510, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 950, 620, 950, 620, 980, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 880, 510, 980, 580, Color.black);
        meto.Linea(g2, 880, 510, 880, 580, color.CafeRellenoO);
        meto.Linea(g2, 880, 580, 980, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 850, 550, 950, 620, Color.black);
        meto.Linea(g2, 850, 550, 880, 510, Color.black);
        meto.Linea(g2, 950, 550, 980, 510, Color.black);
        meto.Linea(g2, 950, 620, 980, 580, Color.black);
        meto.RectanguloRelleno(g2, 1050, 620, 1150, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1080, 580, 1180, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1050, 620, 1090, 690, 1080, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1150, 690, 1150, 650, 1180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1080, 580, 1180, 650, Color.black);
        meto.Linea(g2, 1080, 580, 1080, 650, color.CafeRellenoO);
        meto.Linea(g2, 1080, 650, 1180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1050, 620, 1150, 690, Color.black);
        meto.Linea(g2, 1050, 620, 1080, 580, Color.black);
        meto.Linea(g2, 1150, 620, 1180, 580, Color.black);
        meto.Linea(g2, 1150, 690, 1180, 650, Color.black);
        meto.RectanguloRelleno(g2, 1150, 620, 1250, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1180, 580, 1280, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1150, 620, 1190, 690, 1180, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1250, 690, 1250, 650, 1280, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1180, 580, 1280, 650, Color.black);
        meto.Linea(g2, 1180, 580, 1180, 650, color.CafeRellenoO);
        meto.Linea(g2, 1180, 650, 1280, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1150, 620, 1250, 690, Color.black);
        meto.Linea(g2, 1150, 620, 1180, 580, Color.black);
        meto.Linea(g2, 1250, 620, 1280, 580, Color.black);
        meto.Linea(g2, 1250, 690, 1280, 650, Color.black);
        meto.RectanguloRelleno(g2, 1250, 620, 1350, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1280, 580, 1380, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1250, 620, 1290, 690, 1280, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1350, 690, 1350, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1280, 580, 1380, 650, Color.black);
        meto.Linea(g2, 1280, 580, 1280, 650, color.CafeRellenoO);
        meto.Linea(g2, 1280, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1350, 690, 1250, 620, Color.black);
        meto.Linea(g2, 1250, 620, 1280, 580, Color.black);
        meto.Linea(g2, 1350, 620, 1380, 580, Color.black);
        meto.Linea(g2, 1350, 690, 1380, 650, Color.black);

        g.drawImage(columnaLayer, xPosition, 0, null);
    }

    public void cajas2(Graphics g) {
        BufferedImage columnaLayer = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = columnaLayer.createGraphics();

        meto.LineaBresenham(g2, 50, 600, 0, 700, Color.black);
        meto.LineaBresenham(g2, 150, 600, 100, 700, Color.black);
        meto.LineaBresenham(g2, 250, 600, 200, 700, Color.black);
        meto.LineaBresenham(g2, 350, 600, 300, 700, Color.black);
        meto.LineaBresenham(g2, 450, 600, 400, 700, Color.black);
        meto.LineaBresenham(g2, 550, 600, 500, 700, Color.black);
        meto.LineaBresenham(g2, 650, 600, 600, 700, Color.black);
        meto.LineaBresenham(g2, 750, 600, 700, 700, Color.black);
        meto.LineaBresenham(g2, 850, 600, 800, 700, Color.black);
        meto.LineaBresenham(g2, 950, 600, 900, 700, Color.black);
        meto.LineaBresenham(g2, 1050, 600, 1000, 700, Color.black);
        meto.LineaBresenham(g2, 1150, 600, 1100, 700, Color.black);
        meto.LineaBresenham(g2, 1250, 600, 1200, 700, Color.black);
        meto.LineaBresenham(g2, 1350, 600, 1300, 700, Color.black);
        meto.LineaBresenham(g2, 1450, 600, 1400, 700, Color.black);
        meto.RectanguloRelleno(g2, 50, 620, 150, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 80, 580, 180, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 50, 620, 90, 690, 80, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 150, 690, 150, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 80, 580, 180, 650, Color.black);
        meto.Linea(g2, 80, 580, 80, 650, color.CafeRellenoO);
        meto.Linea(g2, 80, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 50, 620, 150, 690, Color.black);
        meto.Linea(g2, 50, 620, 80, 580, Color.black);
        meto.Linea(g2, 150, 620, 180, 580, Color.black);
        meto.Linea(g2, 150, 690, 180, 650, Color.black);
        meto.RectanguloRelleno(g2, 650, 620, 750, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 680, 580, 780, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 650, 620, 690, 690, 680, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 690, 750, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 680, 580, 780, 650, Color.black);
        meto.Linea(g2, 680, 580, 680, 650, color.CafeRellenoO);
        meto.Linea(g2, 680, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 650, 620, 750, 690, Color.black);
        meto.Linea(g2, 650, 620, 680, 580, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 750, 690, 780, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 620, 850, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 580, 880, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 620, 790, 690, 780, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 690, 850, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 580, 880, 650, Color.black);
        meto.Linea(g2, 780, 580, 780, 650, color.CafeRellenoO);
        meto.Linea(g2, 780, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 620, 850, 690, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.Linea(g2, 850, 690, 880, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 550, 850, 620, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 510, 880, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 550, 790, 620, 780, 510, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 620, 850, 620, 880, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 510, 880, 580, Color.black);
        meto.Linea(g2, 780, 510, 780, 580, color.CafeRellenoO);
        meto.Linea(g2, 780, 580, 880, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 550, 850, 620, Color.black);
        meto.Linea(g2, 750, 550, 780, 510, Color.black);
        meto.Linea(g2, 850, 550, 880, 510, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.RectanguloRelleno(g2, 850, 620, 950, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 880, 580, 980, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 620, 890, 690, 880, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 950, 690, 950, 650, 980, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 880, 580, 980, 650, Color.black);
        meto.Linea(g2, 880, 580, 880, 650, color.CafeRellenoO);
        meto.Linea(g2, 880, 650, 980, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 850, 620, 950, 690, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.Linea(g2, 950, 620, 980, 580, Color.black);
        meto.Linea(g2, 950, 690, 980, 650, Color.black);
        meto.RectanguloRelleno(g2, 850, 550, 950, 620, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 880, 510, 980, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 550, 890, 620, 880, 510, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 950, 620, 950, 620, 980, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 880, 510, 980, 580, Color.black);
        meto.Linea(g2, 880, 510, 880, 580, color.CafeRellenoO);
        meto.Linea(g2, 880, 580, 980, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 850, 550, 950, 620, Color.black);
        meto.Linea(g2, 850, 550, 880, 510, Color.black);
        meto.Linea(g2, 950, 550, 980, 510, Color.black);
        meto.Linea(g2, 950, 620, 980, 580, Color.black);
        meto.RectanguloRelleno(g2, 1150, 620, 1250, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1180, 580, 1280, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1150, 620, 1190, 690, 1180, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1250, 690, 1250, 650, 1280, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1180, 580, 1280, 650, Color.black);
        meto.Linea(g2, 1180, 580, 1180, 650, color.CafeRellenoO);
        meto.Linea(g2, 1180, 650, 1280, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1150, 620, 1250, 690, Color.black);
        meto.Linea(g2, 1150, 620, 1180, 580, Color.black);
        meto.Linea(g2, 1250, 620, 1280, 580, Color.black);
        meto.Linea(g2, 1250, 690, 1280, 650, Color.black);
        meto.RectanguloRelleno(g2, 1250, 620, 1350, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1280, 580, 1380, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1250, 620, 1290, 690, 1280, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1350, 690, 1350, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1280, 580, 1380, 650, Color.black);
        meto.Linea(g2, 1280, 580, 1280, 650, color.CafeRellenoO);
        meto.Linea(g2, 1280, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1350, 690, 1250, 620, Color.black);
        meto.Linea(g2, 1250, 620, 1280, 580, Color.black);
        meto.Linea(g2, 1350, 620, 1380, 580, Color.black);
        meto.Linea(g2, 1350, 690, 1380, 650, Color.black);


        g.drawImage(columnaLayer, xPosition, 0, null);
    }

    public void cajas3(Graphics g) {
        BufferedImage columnaLayer = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = columnaLayer.createGraphics();

        meto.LineaBresenham(g2, 50, 600, 0, 700, Color.black);
        meto.LineaBresenham(g2, 150, 600, 100, 700, Color.black);
        meto.LineaBresenham(g2, 250, 600, 200, 700, Color.black);
        meto.LineaBresenham(g2, 350, 600, 300, 700, Color.black);
        meto.LineaBresenham(g2, 450, 600, 400, 700, Color.black);
        meto.LineaBresenham(g2, 550, 600, 500, 700, Color.black);
        meto.LineaBresenham(g2, 650, 600, 600, 700, Color.black);
        meto.LineaBresenham(g2, 750, 600, 700, 700, Color.black);
        meto.LineaBresenham(g2, 850, 600, 800, 700, Color.black);
        meto.LineaBresenham(g2, 950, 600, 900, 700, Color.black);
        meto.LineaBresenham(g2, 1050, 600, 1000, 700, Color.black);
        meto.LineaBresenham(g2, 1150, 600, 1100, 700, Color.black);
        meto.LineaBresenham(g2, 1250, 600, 1200, 700, Color.black);
        meto.LineaBresenham(g2, 1350, 600, 1300, 700, Color.black);
        meto.LineaBresenham(g2, 1450, 600, 1400, 700, Color.black);
        meto.RectanguloRelleno(g2, 50, 620, 150, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 80, 580, 180, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 50, 620, 90, 690, 80, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 150, 690, 150, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 80, 580, 180, 650, Color.black);
        meto.Linea(g2, 80, 580, 80, 650, color.CafeRellenoO);
        meto.Linea(g2, 80, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 50, 620, 150, 690, Color.black);
        meto.Linea(g2, 50, 620, 80, 580, Color.black);
        meto.Linea(g2, 150, 620, 180, 580, Color.black);
        meto.Linea(g2, 150, 690, 180, 650, Color.black);
        meto.RectanguloRelleno(g2, 650, 620, 750, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 680, 580, 780, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 650, 620, 690, 690, 680, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 690, 750, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 680, 580, 780, 650, Color.black);
        meto.Linea(g2, 680, 580, 680, 650, color.CafeRellenoO);
        meto.Linea(g2, 680, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 650, 620, 750, 690, Color.black);
        meto.Linea(g2, 650, 620, 680, 580, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 750, 690, 780, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 620, 850, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 580, 880, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 620, 790, 690, 780, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 690, 850, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 580, 880, 650, Color.black);
        meto.Linea(g2, 780, 580, 780, 650, color.CafeRellenoO);
        meto.Linea(g2, 780, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 620, 850, 690, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.Linea(g2, 850, 690, 880, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 550, 850, 620, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 510, 880, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 550, 790, 620, 780, 510, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 620, 850, 620, 880, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 510, 880, 580, Color.black);
        meto.Linea(g2, 780, 510, 780, 580, color.CafeRellenoO);
        meto.Linea(g2, 780, 580, 880, 580, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 550, 850, 620, Color.black);
        meto.Linea(g2, 750, 550, 780, 510, Color.black);
        meto.Linea(g2, 850, 550, 880, 510, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.RectanguloRelleno(g2, 1250, 620, 1350, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1280, 580, 1380, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1250, 620, 1290, 690, 1280, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1350, 690, 1350, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1280, 580, 1380, 650, Color.black);
        meto.Linea(g2, 1280, 580, 1280, 650, color.CafeRellenoO);
        meto.Linea(g2, 1280, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1350, 690, 1250, 620, Color.black);
        meto.Linea(g2, 1250, 620, 1280, 580, Color.black);
        meto.Linea(g2, 1350, 620, 1380, 580, Color.black);
        meto.Linea(g2, 1350, 690, 1380, 650, Color.black);

        g.drawImage(columnaLayer, xPosition, 0, null);
    }

    public void cajas4(Graphics g) {
        BufferedImage columnaLayer = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = columnaLayer.createGraphics();

        meto.LineaBresenham(g2, 50, 600, 0, 700, Color.black);
        meto.LineaBresenham(g2, 150, 600, 100, 700, Color.black);
        meto.LineaBresenham(g2, 250, 600, 200, 700, Color.black);
        meto.LineaBresenham(g2, 350, 600, 300, 700, Color.black);
        meto.LineaBresenham(g2, 450, 600, 400, 700, Color.black);
        meto.LineaBresenham(g2, 550, 600, 500, 700, Color.black);
        meto.LineaBresenham(g2, 650, 600, 600, 700, Color.black);
        meto.LineaBresenham(g2, 750, 600, 700, 700, Color.black);
        meto.LineaBresenham(g2, 850, 600, 800, 700, Color.black);
        meto.LineaBresenham(g2, 950, 600, 900, 700, Color.black);
        meto.LineaBresenham(g2, 1050, 600, 1000, 700, Color.black);
        meto.LineaBresenham(g2, 1150, 600, 1100, 700, Color.black);
        meto.LineaBresenham(g2, 1250, 600, 1200, 700, Color.black);
        meto.LineaBresenham(g2, 1350, 600, 1300, 700, Color.black);
        meto.LineaBresenham(g2, 1450, 600, 1400, 700, Color.black);
        meto.RectanguloRelleno(g2, 50, 620, 150, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 80, 580, 180, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 50, 620, 90, 690, 80, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 150, 690, 150, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 80, 580, 180, 650, Color.black);
        meto.Linea(g2, 80, 580, 80, 650, color.CafeRellenoO);
        meto.Linea(g2, 80, 650, 180, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 50, 620, 150, 690, Color.black);
        meto.Linea(g2, 50, 620, 80, 580, Color.black);
        meto.Linea(g2, 150, 620, 180, 580, Color.black);
        meto.Linea(g2, 150, 690, 180, 650, Color.black);
        meto.RectanguloRelleno(g2, 650, 620, 750, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 680, 580, 780, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 650, 620, 690, 690, 680, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 690, 750, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 680, 580, 780, 650, Color.black);
        meto.Linea(g2, 680, 580, 680, 650, color.CafeRellenoO);
        meto.Linea(g2, 680, 650, 780, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 650, 620, 750, 690, Color.black);
        meto.Linea(g2, 650, 620, 680, 580, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 750, 690, 780, 650, Color.black);
        meto.RectanguloRelleno(g2, 750, 620, 850, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 780, 580, 880, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 750, 620, 790, 690, 780, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 850, 690, 850, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 780, 580, 880, 650, Color.black);
        meto.Linea(g2, 780, 580, 780, 650, color.CafeRellenoO);
        meto.Linea(g2, 780, 650, 880, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 750, 620, 850, 690, Color.black);
        meto.Linea(g2, 750, 620, 780, 580, Color.black);
        meto.Linea(g2, 850, 620, 880, 580, Color.black);
        meto.Linea(g2, 850, 690, 880, 650, Color.black);
        meto.RectanguloRelleno(g2, 1250, 620, 1350, 690, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1280, 580, 1380, 650, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1250, 620, 1290, 690, 1280, 580, color.CafeRellenoO);
        meto.RellenoTriangulo(g2, 1350, 690, 1350, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1280, 580, 1380, 650, Color.black);
        meto.Linea(g2, 1280, 580, 1280, 650, color.CafeRellenoO);
        meto.Linea(g2, 1280, 650, 1380, 650, color.CafeRellenoO);
        meto.Rectangulo(g2, 1350, 690, 1250, 620, Color.black);
        meto.Linea(g2, 1250, 620, 1280, 580, Color.black);
        meto.Linea(g2, 1350, 620, 1380, 580, Color.black);
        meto.Linea(g2, 1350, 690, 1380, 650, Color.black);

        g.drawImage(columnaLayer, xPosition, 0, null);
    }

    public void luz(Graphics g){
        meto.RellenoTriangulo(g, 400, 60, 400, 20, 425, 60, Color.gray);
        meto.RellenoTriangulo(g, 20, 70, 40, 110, 40, 60, Color.gray);
        meto.RectanguloRelleno(g, 20, 20, 400, 70, Color.gray);
        meto.RectanguloRelleno(g, 40, 60, 425, 110, Color.gray);
        meto.LineaBresenham(g, 20, 20, 40, 60, Color.black);
        meto.LineaBresenham(g, 400, 20, 425, 60, Color.black);
        meto.LineaBresenham(g, 20, 70, 40, 110, Color.black);
        meto.LineaBresenham(g, 400, 70, 425, 110, Color.black);
        meto.Rectangulo(g, 20, 20, 400, 70, Color.black);
        meto.Rectangulo(g, 40, 60, 425, 110, Color.black);
        meto.RectanguloRelleno(g, 30, 75, 400, 85, color.AmarilloAntorcha);
        meto.RectanguloRelleno(g, 40, 90, 410, 100, color.AmarilloAntorcha);
        meto.RellenoTriangulo(g, 950, 60, 950, 20, 975, 60, Color.gray);
        meto.RellenoTriangulo(g, 575, 70, 600, 110, 600, 60, Color.gray);
        meto.RectanguloRelleno(g,575, 20, 950, 70, Color.gray);
        meto.RectanguloRelleno(g, 600, 60, 975, 110, Color.gray);
        meto.LineaBresenham(g, 575, 20, 600, 60, Color.black);
        meto.LineaBresenham(g, 950, 20, 975, 60, Color.black);
        meto.LineaBresenham(g, 575, 70, 600, 110, Color.black);
        meto.LineaBresenham(g, 950, 70, 975, 110, Color.black);
        meto.Rectangulo(g, 575, 20, 950, 70, Color.black);
        meto.Rectangulo(g, 600, 60, 975, 110, Color.black);
        meto.RectanguloRelleno(g, 590, 75, 950, 85, color.AmarilloAntorcha);
        meto.RectanguloRelleno(g, 600, 90, 960, 100, color.AmarilloAntorcha);
        meto.RellenoTriangulo(g, 1500, 60, 1500, 20, 1525, 60, Color.gray);
        meto.RellenoTriangulo(g, 1120, 70, 1140, 110, 1140, 60, Color.gray);
        meto.RectanguloRelleno(g,1120, 20, 1500, 70, Color.gray);
        meto.RectanguloRelleno(g, 1140, 60, 1525, 110, Color.gray);
        meto.LineaBresenham(g, 1120, 20, 1140, 60, Color.black);
        meto.LineaBresenham(g, 1500, 20, 1525, 60, Color.black);
        meto.LineaBresenham(g, 1120, 70, 1140, 110, Color.black);
        meto.LineaBresenham(g, 1500, 70, 1525, 110, Color.black);
        meto.Rectangulo(g, 1120, 20, 1500, 70, Color.black);
        meto.Rectangulo(g, 1140, 60, 1525, 110, Color.black);
        meto.RectanguloRelleno(g, 1132, 75, 1499, 85, color.AmarilloAntorcha);
        meto.RectanguloRelleno(g, 1140, 90, 1510, 100, color.AmarilloAntorcha);
    }

    public void carteles(Graphics g){
        meto.RectanguloRelleno(g, 100, 200, 300, 500, color.AmarilloAntiguo);
        meto.RectanguloRelleno(g, 120, 220, 280, 300, Color.black);
        meto.Rectangulo(g, 100, 200, 300, 500, Color.black);
        meto.Rectangulo(g, 120, 220, 280, 300, Color.black);
        meto.Linea(g, 120, 325, 280, 325, Color.black);
        meto.Linea(g, 120, 350, 280, 350, Color.black);
        meto.Linea(g, 120, 375, 280, 375, Color.black);
        meto.Linea(g, 120, 400, 280, 400, Color.black);
        meto.Linea(g, 120, 425, 280, 425, Color.black);
        meto.Linea(g, 120, 450, 280, 450, Color.black);
        meto.Linea(g, 120, 475, 280, 475, Color.black);
        meto.RectanguloRelleno(g, 550, 200, 650, 350, color.AmarilloAntiguo);
        meto.Rectangulo(g, 550, 200, 650, 350, Color.black);
        meto.RectanguloRelleno(g, 650, 150, 750, 300, color.AmarilloAntiguo);
        meto.Rectangulo(g, 650, 150, 750, 300, Color.black);
        meto.RectanguloRelleno(g, 750, 250, 850, 400, color.AmarilloAntiguo);
        meto.Rectangulo(g, 750, 250, 850, 400, Color.black);
        meto.RectanguloRelleno(g, 730, 140, 830, 290, color.AmarilloAntiguo);
        meto.Rectangulo(g, 730, 140, 830, 290, Color.black);
        meto.RectanguloRelleno(g, 850, 200, 950, 350, color.AmarilloAntiguo);
        meto.Rectangulo(g, 850, 200, 950, 350, Color.black);
    }

    public void carteles2(Graphics g){
        meto.RectanguloRelleno(g, 100, 200, 300, 500, Color.gray);
        meto.RectanguloRelleno(g, 120, 220, 280, 300, Color.black);
        meto.Rectangulo(g, 100, 200, 300, 500, Color.black);
        meto.Rectangulo(g, 120, 220, 280, 300, Color.black);
        meto.Linea(g, 120, 325, 280, 325, Color.black);
        meto.Linea(g, 120, 350, 280, 350, Color.black);
        meto.Linea(g, 120, 375, 280, 375, Color.black);
        meto.Linea(g, 120, 400, 280, 400, Color.black);
        meto.Linea(g, 120, 425, 280, 425, Color.black);
        meto.Linea(g, 120, 450, 280, 450, Color.black);
        meto.Linea(g, 120, 475, 280, 475, Color.black);
        meto.RectanguloRelleno(g, 550, 200, 650, 350, color.AmarilloAntiguo);
        meto.Rectangulo(g, 550, 200, 650, 350, Color.black);
        meto.RectanguloRelleno(g, 650, 150, 750, 300, color.AmarilloAntiguo);
        meto.Rectangulo(g, 650, 150, 750, 300, Color.black);
        meto.RectanguloRelleno(g, 750, 250, 850, 400, color.AmarilloAntiguo);
        meto.Rectangulo(g, 750, 250, 850, 400, Color.black);
        meto.RectanguloRelleno(g, 730, 140, 830, 290, color.AmarilloAntiguo);
        meto.Rectangulo(g, 730, 140, 830, 290, Color.black);
        meto.RectanguloRelleno(g, 850, 200, 950, 350, color.AmarilloAntiguo);
        meto.Rectangulo(g, 850, 200, 950, 350, Color.black);
        meto.CirculoPuntoMedioRelleno(g, 1400, 400, 100, Color.black);
    }

    public void carteles3(Graphics g){
        meto.RectanguloRelleno(g, 100, 200, 300, 500, color.AmarilloAntiguo);
        meto.RectanguloRelleno(g, 120, 220, 280, 300, Color.black);
        meto.Rectangulo(g, 100, 200, 300, 500, Color.black);
        meto.Rectangulo(g, 120, 220, 280, 300, Color.black);
        meto.CirculoPuntoMedioRelleno(g, 160, 260, 20, Color.red);
        meto.CirculoPuntoMedioRelleno(g, 240, 260, 20, Color.red);
        meto.Linea(g, 120, 325, 280, 325, Color.black);
        meto.Linea(g, 120, 350, 280, 350, Color.black);
        meto.Linea(g, 120, 375, 280, 375, Color.black);
        meto.Linea(g, 120, 400, 280, 400, Color.black);
        meto.Linea(g, 120, 425, 280, 425, Color.black);
        meto.Linea(g, 120, 450, 280, 450, Color.black);
        meto.Linea(g, 120, 475, 280, 475, Color.black);
        meto.RectanguloRelleno(g, 550, 200, 650, 350, color.AmarilloAntiguo);
        meto.Rectangulo(g, 550, 200, 650, 350, Color.black);
        meto.RectanguloRelleno(g, 650, 150, 750, 300, color.AmarilloAntiguo);
        meto.Rectangulo(g, 650, 150, 750, 300, Color.black);
        meto.RectanguloRelleno(g, 750, 250, 850, 400, color.AmarilloAntiguo);
        meto.Rectangulo(g, 750, 250, 850, 400, Color.black);
        meto.RectanguloRelleno(g, 730, 140, 830, 290, color.AmarilloAntiguo);
        meto.Rectangulo(g, 730, 140, 830, 290, Color.black);
        meto.RectanguloRelleno(g, 850, 200, 950, 350, color.AmarilloAntiguo);
        meto.Rectangulo(g, 850, 200, 950, 350, Color.black);
        meto.CirculoPuntoMedioRelleno(g, 1400, 400, 100, Color.black);
        meto.CirculoPuntoMedioRelleno(g, 1360, 360, 20, Color.red);
        meto.CirculoPuntoMedioRelleno(g, 1440, 360, 20, Color.red);
        meto.Triangulo(g, 1330, 410, 1375, 470, 1395, 420, Color.white);
        meto.Triangulo(g, 1380, 470, 1400, 420, 1420, 470, Color.white);
        meto.Triangulo(g, 1405, 420, 1425, 470, 1470, 410, Color.white);
    }
}