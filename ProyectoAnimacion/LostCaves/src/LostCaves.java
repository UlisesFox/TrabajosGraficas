import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LostCaves extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lost Caves");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        LostCaves cuevas = new LostCaves();
        frame.add(cuevas);
        frame.setVisible(true);

        while (true) {
            cuevas.moverCuadrado();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Metodos meto = new Metodos();
    Colores color = new Colores();
    Recorte reco = new Recorte();
    Rotacion rota = new Rotacion();
    Escalacion esca = new Escalacion();
    private double x0, y0;
    private double ladox, ladoy;
    private double w0;
    private double[][] transformMatrix;
    private BufferedImage capa, capa2, capa3, capa4;
    private int xPosition = 0;
    private int currentScene = 0;
    private Timer timer = new Timer();

    public LostCaves() {
        x0 = 0.0;
        y0 = 0.0;
        ladox = 1600.0;
        ladoy = 870.0;
        w0 = 1.0;

        transformMatrix = new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        scheduleSceneChange();
        
        capa = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Columnas(capa.getGraphics());
        capa2 = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        hielo(capa2.getGraphics());
        capa3 = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        hierva(capa3.getGraphics());
        capa4 = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Profunda(capa4.getGraphics());
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
        }, 20000, 50);
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentScene == 0) {
            
        } else if (currentScene == 1) {
            reco.Recortes(g);
            cartel(g);
        } else if (currentScene == 2) {
            FondoCueva(g);
            Personaje(g);
            dibujarCapa(g);
            esca.escalacion(g);
        } else if(currentScene == 3){
            FondoCueva(g);
            Personaje(g);
            dibujarCapa(g);
        } else if (currentScene == 4){
            FondoCueva(g);
            Personaje(g);
            dibujarCapa(g);
            x0 = 1500;
        } else if (currentScene == 5){
            FondoCueva(g);
            Personaje(g);
            dibujarCapa(g);
            CuboBlack(g);
        } else if (currentScene == 6){
            tras(g);
            x0 = 0;
        } else if (currentScene == 7){
            FondoCuevaHielo(g);
            Personaje(g);
            dibujarCapa2(g);
            CuboBlack(g);
        } else if (currentScene == 8){
            FondoCuevaHielo(g);
            Personaje(g);
            dibujarCapa2(g);
            x0 = 1500;
        } else if (currentScene == 9){
            FondoCuevaHielo(g);
            Personaje(g);
            dibujarCapa2(g);
            CuboBlack(g);
        } else if (currentScene == 10){
            tras(g);
            x0 = 0;
        } else if (currentScene == 11){
            FondoCuevaHierba(g);
            Personaje(g);
            dibujarCapa3(g);
            CuboBlack(g);
        } else if (currentScene == 12){
            FondoCuevaHierba(g);
            Personaje(g);
            dibujarCapa3(g);
            x0 = 1500;
        } else if (currentScene == 13){
            FondoCuevaHierba(g);
            Personaje(g);
            dibujarCapa3(g);
            CuboBlack(g);
        } else if (currentScene == 14){
            tras(g);
            x0 = 0;
        } else if (currentScene == 15){
            FondoCuevaProfunda(g);
            Personaje(g);
            dibujarCapa4(g);
            CuboBlack(g);
        } else if (currentScene == 16){
            FondoCuevaProfunda(g);
            Personaje(g);
            dibujarCapa4(g);
            x0 = 1500;
        } else if (currentScene == 17){
            FondoCuevaProfunda(g);
            Personaje(g);
            dibujarCapa4(g);
            CuboBlack(g);
        } else if (currentScene == 18){
            reco.Recortes(g);
            cartel(g);
        }
    }

    private void scheduleSceneChange() {
        int delay = 0;
    
        if (currentScene == 0) {
            currentScene = 1;
            delay = 3000;
        } else if (currentScene == 1) {
            currentScene = 2;
            delay = 18000;
        } else if (currentScene == 2){
            currentScene = 3;
            delay = 2000;
        } else if (currentScene == 3) {
            currentScene = 4;
            delay = 17000; 
        } else if (currentScene == 4){
            currentScene = 5;
            delay = 13000; 
        } else if (currentScene == 5){
            currentScene = 6;
            delay = 1000;
        } else if (currentScene == 6){
            currentScene = 7;
            delay = 13000;
        } else if (currentScene == 7){
            currentScene = 8;
            delay = 17000;
        } else if  (currentScene == 8){
            currentScene = 9;
            delay = 13000;
        } else if (currentScene == 9){
            currentScene = 10;
            delay = 1000;
        } else if (currentScene == 10){
            currentScene = 11;
            delay = 13000;
        } else if (currentScene == 11){
            currentScene = 12;
            delay = 18000;
        } else if  (currentScene == 12){
            currentScene = 13;
            delay = 13000;
        } else if (currentScene == 13){
            currentScene = 14;
            delay = 1000;
        } else if  (currentScene == 14){
            currentScene = 15;
            delay = 13000;
        } else if (currentScene == 15){
            currentScene = 16;
            delay = 18000;
        } else if  (currentScene == 16){
            currentScene = 17;
            delay = 13000;
        } else if (currentScene == 17){
            currentScene = 18;
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

    public void FondoCueva(Graphics g){
        meto.RectanguloRelleno(g, 0, 0, 1540, 870, color.GrisPared);
        meto.RectanguloRelleno(g, 0, 435, 1540, 800, color.GrisParedF);
        meto.CirculoPuntoMedioRelleno(g, 365, 600, 400, color.GrisParedFL);
        brillos(g);
        meto.RectanguloRelleno(g, 0, 0, 1540, 435, color.GrisPared);
        meto.RectanguloRelleno(g, 0, 800, 1540, 870, color.GrisPared);
        meto.Rectangulo(g, 0, 435, 1540, 800, Color.black);
    }

    public void FondoCuevaHielo(Graphics g){
        meto.RectanguloRelleno(g, 0, 0, 1540, 870, color.AzulPared);
        meto.RectanguloRelleno(g, 0, 435, 1540, 800, color.AzulParedF);
        meto.CirculoPuntoMedioRelleno(g, 365, 600, 400, color.AzulParedFL);
        brillos(g);
        meto.RectanguloRelleno(g, 0, 0, 1540, 435, color.AzulPared);
        meto.RectanguloRelleno(g, 0, 800, 1540, 870, color.AzulPared);
        meto.Rectangulo(g, 0, 435, 1540, 800, Color.black);
    }

    public void FondoCuevaHierba(Graphics g){
        meto.RectanguloRelleno(g, 0, 0, 1540, 870, color.VerdePared);
        meto.RectanguloRelleno(g, 0, 435, 1540, 800, color.VerdeParedF);
        meto.CirculoPuntoMedioRelleno(g, 365, 600, 400, color.VerdeParedFL);
        brillos(g);
        meto.RectanguloRelleno(g, 0, 0, 1540, 435, color.VerdePared);
        meto.RectanguloRelleno(g, 0, 800, 1540, 870, color.VerdePared);
        meto.Rectangulo(g, 0, 435, 1540, 800, Color.black);
    }

    public void FondoCuevaProfunda(Graphics g){
        meto.RectanguloRelleno(g, 0, 0, 1540, 870, color.OscuroPared);
        meto.RectanguloRelleno(g, 0, 435, 1540, 800, color.OscuroParedF);
        meto.CirculoPuntoMedioRelleno(g, 365, 600, 400, color.OscuroParedFL);
        brillos(g);
        meto.RectanguloRelleno(g, 0, 0, 1540, 435, color.OscuroPared);
        meto.RectanguloRelleno(g, 0, 800, 1540, 870, color.OscuroPared);
        meto.Rectangulo(g, 0, 435, 1540, 800, Color.black);
    }

    public void Personaje(Graphics g){
        meto.Rectangulo(g, 350, 560, 380, 600, Color.black);
        meto.RectanguloRelleno(g, 351, 561, 379, 599, color.CafeAntorcha);
        meto.RectanguloRelleno(g, 351, 536, 379, 559, color.AmarilloAntorcha);
        rota.rotacion(g, 365, 550, color.AmarilloAntorcha);
        meto.Sol(g, 365, 550, 2, color.AmarilloAntorcha);
        meto.CirculoPuntoMedio(g, 250, 550, 50, Color.black);
        meto.CirculoPuntoMedioRelleno(g, 250, 550, 50, color.Piel);
        meto.Rectangulo(g, 150, 600, 300, 700, Color.black);
        meto.Rectangulo(g, 199, 699, 301, 801, Color.black);
        meto.Rectangulo(g, 300, 600, 400, 650, Color.black);
        meto.RectanguloRelleno(g, 151, 601, 300, 699, color.Camisa);
        meto.RectanguloRelleno(g, 301, 601, 399, 649, color.Camisa);
        meto.Rectangulo(g, 200, 600, 300, 700, Color.black);
        meto.RectanguloRelleno(g, 200, 700, 300, 800, color.Pantalon);
        meto.CirculoPuntoMedioRelleno(g, 230, 540, 10, Color.white);
        meto.CirculoPuntoMedioRelleno(g, 285, 540, 10, Color.white);
        meto.CirculoPuntoMedioRelleno(g, 235, 540, 3, Color.black);
        meto.CirculoPuntoMedioRelleno(g, 290, 540, 3, Color.black);
        meto.infinito(g, 250, 540, 49, Color.black);
        meto.Circulo(g, 230, 540, 10, Color.black);
        meto.Circulo(g, 285, 540, 10, Color.black);
        meto.LineaDDA(g, 250, 720, 250, 800, Color.black);
    }

    public void Profunda(Graphics g) {
        BufferedImage columnaLayer = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = columnaLayer.createGraphics();

        meto.RectanguloRelleno(g2, 400, 435, 600, 520, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 450, 520, 550, 800, color.CafeRellenoO);
        meto.Rectangulo(g2, 400, 435, 600, 520, color.CafeRelleno);
        meto.Rectangulo(g2, 450, 520, 550, 800, color.CafeRelleno);
        meto.Mallado(g2, 400, 435, 600, 520, 20, color.CafeRelleno);
        meto.LineaDDA(g2, 450, 520, 550, 560, color.CafeRelleno);
        meto.LineaDDA(g2, 450, 600, 550, 650, color.CafeRelleno);
        meto.LineaDDA(g2, 450, 700, 550, 750, color.CafeRelleno);

        meto.RectanguloRelleno(g2, 1200, 435, 1400, 520, color.CafeRellenoO);
        meto.RectanguloRelleno(g2, 1250, 520, 1350, 800, color.CafeRellenoO);
        meto.Rectangulo(g2, 1200, 435, 1400, 520, color.CafeRelleno);
        meto.Rectangulo(g2, 1250, 520, 1350, 800, color.CafeRelleno);
        meto.Mallado(g2, 1200, 435, 1400, 520, 20, color.CafeRelleno);
        meto.LineaDDA(g2, 1250, 520, 1350, 560, color.CafeRelleno);
        meto.LineaDDA(g2, 1250, 600, 1350, 650, color.CafeRelleno);
        meto.LineaDDA(g2, 1250, 700, 1350, 750, color.CafeRelleno);

        meto.Triangulo(g2, 0, 430, 50,500, 100, 430, color.OscuroPared);
        meto.Triangulo(g2, 500, 430, 550, 500, 600, 430, color.OscuroPared);
        meto.Triangulo(g2, 950, 430, 1000, 500, 1050, 430, color.OscuroPared);
        meto.Triangulo(g2, 1100, 430, 1150, 550, 1200, 430, color.OscuroPared);
        meto.Triangulo(g2, 1400, 430, 1450, 550, 1500, 430, color.OscuroPared);

        minerales(g2);

        g.drawImage(columnaLayer, xPosition, 0, null);
    }
    
    public void hierva(Graphics g){
        BufferedImage hielocolumna = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = hielocolumna.createGraphics();

        meto.RectanguloRelleno(g2, 400, 435, 600, 520, color.CafeRelleno);
        meto.RectanguloRelleno(g2, 450, 520, 550, 800, color.CafeRelleno);
        meto.Rectangulo(g2, 400, 435, 600, 520, color.CafeBorde);
        meto.Rectangulo(g2, 450, 520, 550, 800, color.CafeBorde);
        meto.Mallado(g2, 400, 435, 600, 520, 20, color.CafeBorde);
        meto.LineaDDA(g2, 450, 520, 550, 560, color.CafeBorde);
        meto.LineaDDA(g2, 450, 600, 550, 650, color.CafeBorde);
        meto.LineaDDA(g2, 450, 700, 550, 750, color.CafeBorde);

        meto.RectanguloRelleno(g2, 1200, 435, 1400, 520, color.CafeRelleno);
        meto.RectanguloRelleno(g2, 1250, 520, 1350, 800, color.CafeRelleno);
        meto.Rectangulo(g2, 1200, 435, 1400, 520, color.CafeBorde);
        meto.Rectangulo(g2, 1250, 520, 1350, 800, color.CafeBorde);
        meto.Mallado(g2, 1200, 435, 1400, 520, 20, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 520, 1350, 560, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 600, 1350, 650, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 700, 1350, 750, color.CafeBorde);

        meto.CirculoPuntoMedioRelleno(g2, 440, 520, 70, color.Flor);
        meto.CirculoPuntoMedioRelleno(g2, 550, 650, 50, color.Flor);
        meto.CirculoPuntoMedioRelleno(g2, 450, 740, 60, color.Flor);
        meto.Flor(g2, 440, 520, 40, color.FlorO);
        meto.Flor(g2, 550, 650, 25, color.FlorO);
        meto.Flor(g2, 450, 740, 30, color.FlorO);

        meto.CirculoPuntoMedioRelleno(g2, 1360, 520, 50, color.Flor);
        meto.CirculoPuntoMedioRelleno(g2, 1250, 650, 60, color.Flor);
        meto.CirculoPuntoMedioRelleno(g2, 1350, 740, 70, color.Flor);
        meto.Flor(g2, 1360, 520, 30, color.FlorO);
        meto.Flor(g2, 1250, 650, 35, color.FlorO);
        meto.Flor(g2, 1350, 740, 40, color.FlorO);
        
        minerales(g2);

        g.drawImage(hielocolumna, xPosition, 0, null);
    }

    public void hielo(Graphics g){
        BufferedImage hielocolumna = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = hielocolumna.createGraphics();

        meto.RectanguloRelleno(g2, 400, 435, 600, 520, color.CafeRelleno);
        meto.RectanguloRelleno(g2, 450, 520, 550, 800, color.CafeRelleno);
        meto.Rectangulo(g2, 400, 435, 600, 520, color.CafeBorde);
        meto.Rectangulo(g2, 450, 520, 550, 800, color.CafeBorde);
        meto.Mallado(g2, 400, 435, 600, 520, 20, color.CafeBorde);
        meto.LineaDDA(g2, 450, 520, 550, 560, color.CafeBorde);
        meto.LineaDDA(g2, 450, 600, 550, 650, color.CafeBorde);
        meto.LineaDDA(g2, 450, 700, 550, 750, color.CafeBorde);

        meto.RectanguloRelleno(g2, 1200, 435, 1400, 520, color.CafeRelleno);
        meto.RectanguloRelleno(g2, 1250, 520, 1350, 800, color.CafeRelleno);
        meto.Rectangulo(g2, 1200, 435, 1400, 520, color.CafeBorde);
        meto.Rectangulo(g2, 1250, 520, 1350, 800, color.CafeBorde);
        meto.Mallado(g2, 1200, 435, 1400, 520, 20, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 520, 1350, 560, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 600, 1350, 650, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 700, 1350, 750, color.CafeBorde);

        meto.Triangulo(g2, 0, 430, 50,500, 100, 430, color.AzulPared);
        meto.Triangulo(g2, 150, 430, 250, 500, 300, 430, color.AzulPared);
        meto.Triangulo(g2, 350, 430, 400, 550, 450, 430, color.AzulPared);
        meto.Triangulo(g2, 500, 430, 550, 500, 600, 430, color.AzulPared);
        meto.Triangulo(g2, 650, 430, 700, 550, 800, 430, color.AzulPared);
        meto.Triangulo(g2, 950, 430, 1000, 500, 1050, 430, color.AzulPared);
        meto.Triangulo(g2, 1100, 430, 1150, 550, 1200, 430, color.AzulPared);
        meto.Triangulo(g2, 1250, 430, 1300, 500, 1350, 430, color.AzulPared);
        meto.Triangulo(g2, 1400, 430, 1450, 550, 1500, 430, color.AzulPared);

        minerales(g2);

        g.drawImage(hielocolumna, xPosition, 0, null);
    }

    public void Columnas(Graphics g) {
        BufferedImage columnaLayer = new BufferedImage(1540, 870, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = columnaLayer.createGraphics();

        meto.RectanguloRelleno(g2, 400, 435, 600, 520, color.CafeRelleno);
        meto.RectanguloRelleno(g2, 450, 520, 550, 800, color.CafeRelleno);
        meto.Rectangulo(g2, 400, 435, 600, 520, color.CafeBorde);
        meto.Rectangulo(g2, 450, 520, 550, 800, color.CafeBorde);
        meto.Mallado(g2, 400, 435, 600, 520, 20, color.CafeBorde);
        meto.LineaDDA(g2, 450, 520, 550, 560, color.CafeBorde);
        meto.LineaDDA(g2, 450, 600, 550, 650, color.CafeBorde);
        meto.LineaDDA(g2, 450, 700, 550, 750, color.CafeBorde);

        meto.RectanguloRelleno(g2, 1200, 435, 1400, 520, color.CafeRelleno);
        meto.RectanguloRelleno(g2, 1250, 520, 1350, 800, color.CafeRelleno);
        meto.Rectangulo(g2, 1200, 435, 1400, 520, color.CafeBorde);
        meto.Rectangulo(g2, 1250, 520, 1350, 800, color.CafeBorde);
        meto.Mallado(g2, 1200, 435, 1400, 520, 20, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 520, 1350, 560, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 600, 1350, 650, color.CafeBorde);
        meto.LineaDDA(g2, 1250, 700, 1350, 750, color.CafeBorde);

        minerales(g2);

        g.drawImage(columnaLayer, xPosition, 0, null);
    }

    private void cartel(Graphics g){
        meto.RectanguloRelleno(g, 150, 150, 650, 550, color.Esmeralda);
        meto.RectanguloRelleno(g, 200, 200, 600, 500, color.EsmeraldaO);
        meto.Rombo(g, 750, 400, 500, Color.RED);
        meto.Rombo(g, 750, 400, 300, color.Redstone);
        meto.LineaMedia(g, 500, 400, 600, 400, color.Redstone);
        meto.LineaMedia(g, 900, 400, 1000, 400, color.Redstone);
        meto.LineaMedia(g, 750, 200, 750, 300, color.Redstone);
        meto.LineaMedia(g, 750, 500, 750, 600, color.Redstone);
        meto.Triangulo(g, 900, 450, 1100, 650, 1300, 450, color.Diamante);
        meto.Triangulo(g, 900, 450, 950, 400, 1000, 450, color.Diamante);
        meto.Triangulo(g, 950, 400, 1000, 450, 1050, 400, color.Diamante);
        meto.Triangulo(g, 1000, 450, 1050, 400, 1100, 450, color.Diamante);
        meto.Triangulo(g, 1050, 400, 1100, 450, 1150, 400, color.Diamante);
        meto.Triangulo(g, 1100, 450, 1150, 400, 1200, 450, color.Diamante);
        meto.Triangulo(g, 1150, 400, 1200, 450, 1250, 400, color.Diamante);
        meto.Triangulo(g, 1200, 450, 1250, 400, 1300, 450, color.Diamante);
        meto.Triangulonot(g, 900, 450, 1100, 650, 1300, 450, color.DiamanteO);
        meto.Triangulonot(g, 900, 450, 950, 400, 1000, 450, color.DiamanteO);
        meto.Triangulonot(g, 950, 400, 1000, 450, 1050, 400, color.DiamanteO);
        meto.Triangulonot(g, 1000, 450, 1050, 400, 1100, 450, color.DiamanteO);
        meto.Triangulonot(g, 1050, 400, 1100, 450, 1150, 400, color.DiamanteO);
        meto.Triangulonot(g, 1100, 450, 1150, 400, 1200, 450, color.DiamanteO);
        meto.Triangulonot(g, 1150, 400, 1200, 450, 1250, 400, color.DiamanteO);
        meto.Triangulonot(g, 1200, 450, 1250, 400, 1300, 450, color.DiamanteO);
        meto.Triangulo(g, 300, 250, 400, 320, 300, 400, Color.BLACK);
        meto.Triangulo(g, 900, 200, 520,240, 700, 200, Color.BLACK);
        meto.Triangulo(g, 1200, 400, 1100, 450, 1200, 230, Color.BLACK);
        meto.Triangulo(g, 500, 600, 900, 570, 800, 600, Color.BLACK);
        meto.RectanguloRelleno(g, 500, 0, 1000, 200, Color.black);
        meto.RectanguloRelleno(g, 0, 600, 1540, 870, Color.black);
        meto.RectanguloRelleno(g, 0, 0, 300, 870, Color.black);
        meto.RectanguloRelleno(g, 1200, 0, 1540, 870, Color.black);
        
    }

    public void minerales(Graphics g2){
        meto.RectanguloRelleno(g2, 1490, 100, 1540, 150, color.Esmeralda);
        meto.RectanguloRelleno(g2, 1500, 110, 1530, 140, color.EsmeraldaO);
        meto.LineaMedia(g2, 1490, 100, 1500, 110, color.EsmeraldaO);
        meto.LineaMedia(g2, 1540, 100, 1530, 110, color.EsmeraldaO);
        meto.LineaMedia(g2, 1490, 150, 1500, 140, color.EsmeraldaO);
        meto.LineaMedia(g2, 1540, 150, 1530, 140, color.EsmeraldaO);
        
        meto.RectanguloRelleno(g2, 150, 50, 200, 100, color.Esmeralda);
        meto.RectanguloRelleno(g2, 160, 60, 190, 90, color.EsmeraldaO);
        meto.LineaMedia(g2, 150, 50, 160, 60, color.EsmeraldaO);
        meto.LineaMedia(g2, 200, 50, 190, 60, color.EsmeraldaO);
        meto.LineaMedia(g2, 150, 100, 160, 90, color.EsmeraldaO);
        meto.LineaMedia(g2, 200, 100, 190, 90, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 130, 150, 170, 200, color.Esmeralda);
        meto.RectanguloRelleno(g2, 140, 160, 160, 190, color.EsmeraldaO);
        meto.LineaMedia(g2, 130, 150, 140, 160, color.EsmeraldaO);
        meto.LineaMedia(g2, 170, 150, 160, 160, color.EsmeraldaO);
        meto.LineaMedia(g2, 130, 200, 140, 190, color.EsmeraldaO);
        meto.LineaMedia(g2, 170, 200, 160, 190, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 550, 100, 600, 150, color.Esmeralda);
        meto.RectanguloRelleno(g2, 560, 110, 590, 140, color.EsmeraldaO);
        meto.LineaMedia(g2, 550, 100, 560, 110, color.EsmeraldaO);
        meto.LineaMedia(g2, 600, 100, 590, 110, color.EsmeraldaO);
        meto.LineaMedia(g2, 550, 150, 560, 140, color.EsmeraldaO);
        meto.LineaMedia(g2, 600, 150, 590, 140, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 750, 50, 800, 100, color.Esmeralda);
        meto.RectanguloRelleno(g2, 760, 60, 790, 90, color.EsmeraldaO);
        meto.LineaMedia(g2, 750, 50, 760, 60, color.EsmeraldaO);
        meto.LineaMedia(g2, 800, 50, 790, 60, color.EsmeraldaO);
        meto.LineaMedia(g2, 750, 100, 760, 90, color.EsmeraldaO);
        meto.LineaMedia(g2, 800, 100, 790, 90, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 1050, 100, 1100, 150, color.Esmeralda);
        meto.RectanguloRelleno(g2, 1060, 110, 1090, 140, color.EsmeraldaO);
        meto.LineaMedia(g2, 1050, 100, 1060, 110, color.EsmeraldaO);
        meto.LineaMedia(g2, 1100, 100, 1090, 110, color.EsmeraldaO);
        meto.LineaMedia(g2, 1050, 150, 1060, 140, color.EsmeraldaO);
        meto.LineaMedia(g2, 1100, 150, 1090, 140, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 950, 50, 1000, 100, color.Esmeralda);
        meto.RectanguloRelleno(g2, 960, 60, 990, 90, color.EsmeraldaO);
        meto.LineaMedia(g2, 950, 50, 960, 60, color.EsmeraldaO);
        meto.LineaMedia(g2, 1000, 50, 990, 60, color.EsmeraldaO);
        meto.LineaMedia(g2, 950, 100, 960, 90, color.EsmeraldaO);
        meto.LineaMedia(g2, 1000, 100, 990, 90, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 1130, 150, 1170, 200, color.Esmeralda);
        meto.RectanguloRelleno(g2, 1140, 160, 1160, 190, color.EsmeraldaO);
        meto.LineaMedia(g2, 1130, 150, 1140, 160, color.EsmeraldaO);
        meto.LineaMedia(g2, 1170, 150, 1160, 160, color.EsmeraldaO);
        meto.LineaMedia(g2, 1130, 200, 1140, 190, color.EsmeraldaO);
        meto.LineaMedia(g2, 1170, 200, 1160, 190, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 50, 200, 100, 250, color.Esmeralda);
        meto.RectanguloRelleno(g2, 60, 210, 90, 240, color.EsmeraldaO);
        meto.LineaMedia(g2, 50, 200, 60, 210, color.EsmeraldaO);
        meto.LineaMedia(g2, 100, 200, 90, 210, color.EsmeraldaO);
        meto.LineaMedia(g2, 50, 250, 60, 240, color.EsmeraldaO);
        meto.LineaMedia(g2, 100, 250, 90, 240, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 150, 250, 200, 300, color.Esmeralda);
        meto.RectanguloRelleno(g2, 160, 260, 190, 290, color.EsmeraldaO);
        meto.LineaMedia(g2, 150, 250, 160, 260, color.EsmeraldaO);
        meto.LineaMedia(g2, 200, 250, 190, 260, color.EsmeraldaO);
        meto.LineaMedia(g2, 150, 300, 160, 290, color.EsmeraldaO);
        meto.LineaMedia(g2, 200, 300, 190, 290, color.EsmeraldaO);
        meto.RectanguloRelleno(g2, 50, 320, 100, 370, color.Esmeralda);
        meto.RectanguloRelleno(g2, 60, 330, 90, 360, color.EsmeraldaO);
        meto.LineaMedia(g2, 50, 320, 60, 330, color.EsmeraldaO);
        meto.LineaMedia(g2, 100, 320, 90, 330, color.EsmeraldaO);
        meto.LineaMedia(g2, 50, 370, 60, 360, color.EsmeraldaO);
        meto.LineaMedia(g2, 100, 370, 90, 360, color.EsmeraldaO);
        meto.Triangulo(g2, 400, 200, 430, 250, 460, 200, color.Diamante);
        meto.Triangulo(g2, 400, 200, 410, 190, 420, 200, color.Diamante);
        meto.Triangulo(g2, 410, 190, 420, 200, 430, 190, color.Diamante);
        meto.Triangulo(g2, 420, 200, 430, 190, 440, 200, color.Diamante);
        meto.Triangulo(g2, 430, 190, 440, 200, 450, 190, color.Diamante);
        meto.Triangulo(g2, 440, 200, 450, 190, 460, 200, color.Diamante);
        meto.Triangulonot(g2, 400, 200, 430, 250, 460, 200, color.DiamanteO);
        meto.Triangulonot(g2, 400, 200, 410, 190, 420, 200, color.DiamanteO);
        meto.Triangulonot(g2, 410, 190, 420, 200, 430, 190, color.DiamanteO);
        meto.Triangulonot(g2, 420, 200, 430, 190, 440, 200, color.DiamanteO);
        meto.Triangulonot(g2, 430, 190, 440, 200, 450, 190, color.DiamanteO);
        meto.Triangulonot(g2, 440, 200, 450, 190, 460, 200, color.DiamanteO);

        meto.Triangulo(g2, 400, 100, 430, 150, 460, 100, color.Diamante);
        meto.Triangulo(g2, 400, 100, 410, 90, 420, 100, color.Diamante);
        meto.Triangulo(g2, 410, 90, 420, 100, 430, 90, color.Diamante);
        meto.Triangulo(g2, 420, 100, 430, 90, 440, 100, color.Diamante);
        meto.Triangulo(g2, 430, 90, 440, 100, 450, 90, color.Diamante);
        meto.Triangulo(g2, 440, 100, 450, 90, 460, 100, color.Diamante);
        meto.Triangulonot(g2, 400, 100, 430, 150, 460, 100, color.DiamanteO);
        meto.Triangulonot(g2, 400, 100, 410, 90, 420, 100, color.DiamanteO);
        meto.Triangulonot(g2, 410, 90, 420, 100, 430, 90,  color.DiamanteO);
        meto.Triangulonot(g2, 420, 100, 430, 90, 440, 100, color.DiamanteO);
        meto.Triangulonot(g2, 430, 90, 440, 100, 450, 90,  color.DiamanteO);
        meto.Triangulonot(g2, 440, 100, 450, 90, 460, 100, color.DiamanteO);

        meto.Triangulo(g2, 500, 300, 530, 350, 560, 300, color.Diamante);
        meto.Triangulo(g2, 500, 300, 510, 290, 520, 300, color.Diamante);
        meto.Triangulo(g2, 510, 290, 520, 300, 530, 290, color.Diamante);
        meto.Triangulo(g2, 520, 300, 530, 290, 540, 300, color.Diamante);
        meto.Triangulo(g2, 530, 290, 540, 300, 550, 290, color.Diamante);
        meto.Triangulo(g2, 540, 300, 550, 290, 560, 300, color.Diamante);
        meto.Triangulonot(g2, 500, 300, 530, 350, 560, 300, color.DiamanteO);
        meto.Triangulonot(g2, 500, 300, 510, 290, 520, 300, color.DiamanteO);
        meto.Triangulonot(g2, 510, 290, 520, 300, 530, 290, color.DiamanteO);
        meto.Triangulonot(g2, 520, 300, 530, 290, 540, 300, color.DiamanteO);
        meto.Triangulonot(g2, 530, 290, 540, 300, 550, 290, color.DiamanteO);
        meto.Triangulonot(g2, 540, 300, 550, 290, 560, 300, color.DiamanteO);
        meto.Rombo(g2, 750, 200, 60, Color.RED);
        meto.Rombo(g2, 750, 200, 40, color.Redstone);
        meto.LineaMedia(g2, 720, 200, 780, 200, color.Redstone);
        meto.LineaMedia(g2, 750, 170, 750, 230, color.Redstone);
        meto.Rombo(g2, 880, 150, 60, Color.RED);
        meto.Rombo(g2, 880, 150, 40, color.Redstone);
        meto.LineaMedia(g2, 850, 150, 910, 150, color.Redstone);
        meto.LineaMedia(g2, 880, 120, 880, 180, color.Redstone);
        meto.Rombo(g2, 980, 250, 60, Color.RED);
        meto.Rombo(g2, 980, 250, 40, color.Redstone);
        meto.LineaMedia(g2, 950, 250, 1010, 250, color.Redstone);
        meto.LineaMedia(g2, 980, 220, 980, 280, color.Redstone);
        meto.Triangulo(g2, 1200, 100, 1230, 150, 1260, 100, color.Diamante);
        meto.Triangulo(g2, 1200, 100, 1210, 90, 1220, 100, color.Diamante);
        meto.Triangulo(g2, 1210, 90, 1220, 100, 1230, 90, color.Diamante);
        meto.Triangulo(g2, 1220, 100, 1230, 90, 1240, 100, color.Diamante);
        meto.Triangulo(g2, 1230, 90, 1240, 100, 1250, 90, color.Diamante);
        meto.Triangulo(g2, 1240, 100, 1250, 90, 1260, 100, color.Diamante);
        meto.Triangulonot(g2, 1200, 100, 1230, 150, 1260, 100, color.DiamanteO);
        meto.Triangulonot(g2, 1200, 100, 1210, 90, 1220, 100, color.DiamanteO);
        meto.Triangulonot(g2, 1210, 90, 1220, 100, 1230, 90, color.DiamanteO);
        meto.Triangulonot(g2, 1220, 100, 1230, 90, 1240, 100, color.DiamanteO);
        meto.Triangulonot(g2, 1230, 90, 1240, 100, 1250, 90, color.DiamanteO);
        meto.Triangulonot(g2, 1240, 100, 1250, 90, 1260, 100, color.DiamanteO);
        meto.Triangulo(g2, 1300, 200, 1330, 250, 1360, 200, color.Diamante);
        meto.Triangulo(g2, 1300, 200, 1310, 190, 1320, 200, color.Diamante);
        meto.Triangulo(g2, 1310, 190, 1320, 200, 1330, 190, color.Diamante);
        meto.Triangulo(g2, 1320, 200, 1330, 190, 1340, 200, color.Diamante);
        meto.Triangulo(g2, 1330, 190, 1340, 200, 1350, 190, color.Diamante);
        meto.Triangulo(g2, 1340, 200, 1350, 190, 1360, 200, color.Diamante);
        meto.Triangulonot(g2, 1300, 200, 1330, 250, 1360, 200, color.DiamanteO);
        meto.Triangulonot(g2, 1300, 200, 1310, 190, 1320, 200, color.DiamanteO);
        meto.Triangulonot(g2, 1310, 190, 1320, 200, 1330, 190, color.DiamanteO);
        meto.Triangulonot(g2, 1320, 200, 1330, 190, 1340, 200, color.DiamanteO);
        meto.Triangulonot(g2, 1330, 190, 1340, 200, 1350, 190, color.DiamanteO);
        meto.Triangulonot(g2, 1340, 200, 1350, 190, 1360, 200, color.DiamanteO);
        meto.Triangulo(g2, 1100, 300, 1130, 350, 1160, 300, color.Diamante);
        meto.Triangulo(g2, 1100, 300, 1110, 290, 1120, 300, color.Diamante);
        meto.Triangulo(g2, 1110, 290, 1120, 300, 1130, 290, color.Diamante);
        meto.Triangulo(g2, 1120, 300, 1130, 290, 1140, 300, color.Diamante);
        meto.Triangulo(g2, 1130, 290, 1140, 300, 1150, 290, color.Diamante);
        meto.Triangulo(g2, 1140, 300, 1150, 290, 1160, 300, color.Diamante);
        meto.Triangulonot(g2, 1100, 300, 1130, 350, 1160, 300, color.DiamanteO);
        meto.Triangulonot(g2, 1100, 300, 1110, 290, 1120, 300, color.DiamanteO);
        meto.Triangulonot(g2, 1110, 290, 1120, 300, 1130, 290, color.DiamanteO);
        meto.Triangulonot(g2, 1120, 300, 1130, 290, 1140, 300, color.DiamanteO);
        meto.Triangulonot(g2, 1130, 290, 1140, 300, 1150, 290, color.DiamanteO);
        meto.Triangulonot(g2, 1140, 300, 1150, 290, 1160, 300, color.DiamanteO);
        meto.Triangulo(g2, 800, 300, 830, 350, 860, 300, color.Diamante);
        meto.Triangulo(g2, 800, 300, 810, 290, 820, 300, color.Diamante);
        meto.Triangulo(g2, 810, 290, 820, 300, 830, 290, color.Diamante);
        meto.Triangulo(g2, 820, 300, 830, 290, 840, 300, color.Diamante);
        meto.Triangulo(g2, 830, 290, 840, 300, 850, 290, color.Diamante);
        meto.Triangulo(g2, 840, 300, 850, 290, 860, 300, color.Diamante);
        meto.Triangulonot(g2, 800, 300, 830, 350, 860, 300, color.DiamanteO);
        meto.Triangulonot(g2, 800, 300, 810, 290, 820, 300, color.DiamanteO);
        meto.Triangulonot(g2, 810, 290, 820, 300, 830, 290, color.DiamanteO);
        meto.Triangulonot(g2, 820, 300, 830, 290, 840, 300, color.DiamanteO);
        meto.Triangulonot(g2, 830, 290, 840, 300, 850, 290, color.DiamanteO);
        meto.Triangulonot(g2, 840, 300, 850, 290, 860, 300, color.DiamanteO);
        meto.Rombo(g2, 1400, 350, 60, Color.RED);
        meto.Rombo(g2, 1400, 350, 40, color.Redstone);
        meto.LineaMedia(g2, 1370, 350, 1430, 350, color.Redstone);
        meto.LineaMedia(g2, 1400, 320, 1400, 380, color.Redstone);
        meto.Rombo(g2, 1400, 100, 60, Color.RED);
        meto.Rombo(g2, 1400, 100, 40, color.Redstone);
        meto.LineaMedia(g2, 1370, 100, 1430, 100, color.Redstone);
        meto.LineaMedia(g2, 1400, 70, 1400, 130, color.Redstone);
        meto.Rombo(g2, 1500, 250, 60, Color.RED);
        meto.Rombo(g2, 1500, 250, 40, color.Redstone);
        meto.LineaMedia(g2, 1470, 250, 1530, 250, color.Redstone);
        meto.LineaMedia(g2, 1500, 220, 1500, 270, color.Redstone);
        meto.Rombo(g2, 200, 350, 60, Color.RED);
        meto.Rombo(g2, 200, 350, 40, color.Redstone);
        meto.LineaMedia(g2, 170, 350, 230, 350, color.Redstone);
        meto.LineaMedia(g2, 200, 320, 200, 380, color.Redstone);
        meto.Rombo(g2, 300, 100, 60, Color.RED);
        meto.Rombo(g2, 300, 100, 40, color.Redstone);
        meto.LineaMedia(g2, 270, 100, 330, 100, color.Redstone);
        meto.LineaMedia(g2, 300, 70, 300, 130, color.Redstone);
        meto.Rombo(g2, 300, 250, 60, Color.RED);
        meto.Rombo(g2, 300, 250, 40, color.Redstone);
        meto.LineaMedia(g2, 270, 250, 330, 250, color.Redstone);
        meto.LineaMedia(g2, 300, 220, 300, 270, color.Redstone);
    }

    public void brillos(Graphics g){
        meto.Tornado(g, 100, 600, 100, 550, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 125, 550, 125, 500, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 150, 500, 150, 450, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 450, 500, 450, 450, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 475, 550, 475, 500, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 500, 600, 500, 550, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 100, 620, 100, 670, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 125, 670, 125, 720, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 150, 720, 150, 770, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 450, 720, 450, 770, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 475, 670, 475, 720, 100, 0, color.AmarilloAntorcha);
        meto.Tornado(g, 500, 620, 500, 670, 100, 0, color.AmarilloAntorcha);
    }

    public void CuboBlack(Graphics g){
        RectanguloRelleno(g, x0, y0, x0 + ladox, y0 + ladoy, Color.black);
    }

    public void tras(Graphics g){
        RectanguloRelleno(g, 0, 0, 1540, 870, Color.black);
    }

    public void pix(Graphics g, double x, double y, double w, Color c) {
        double[] transformed = transformPoint(x, y, w);
        int screenX = (int) transformed[0];
        int screenY = (int) transformed[1];

        g.setColor(c);
        g.fillRect(screenX, screenY, 1, 1);
    }

    public void LineaMedia(Graphics g, double x0, double y0, double x1, double y1, Color c) {
        int dx = Math.abs((int) x1 - (int) x0);
        int dy = Math.abs((int) y1 - (int) y0);
        int sx, sy;

        if ((int) x0 < (int) x1) {
            sx = 1;
        } else {
            sx = -1;
        }

        if ((int) y0 < (int) y1) {
            sy = 1;
        } else {
            sy = -1;
        }

        int err = dx - dy;
        int x = (int) x0;
        int y = (int) y0;

        while (true) {
            pix(g, x, y, w0, c);

            if (x == (int) x1 && y == (int) y1) {
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

    public void RectanguloRelleno(Graphics g, double x0, double y0, double x1, double y1, Color c) {
        for (int y = (int) y0; y <= (int) y1; y++) {
            LineaMedia(g, x1, y, x0, y, c);
        }
    }

    public void moverCuadrado() {
        x0 -= 1.0;
        repaint();
    }

    private double[] transformPoint(double x, double y, double w) {
        double[] result = new double[3];
        result[0] = transformMatrix[0][0] * x + transformMatrix[0][1] * y + transformMatrix[0][2] * w;
        result[1] = transformMatrix[1][0] * x + transformMatrix[1][1] * y + transformMatrix[1][2] * w;
        result[2] = transformMatrix[2][0] * x + transformMatrix[2][1] * y + transformMatrix[2][2] * w;
        return result;
    }
}
