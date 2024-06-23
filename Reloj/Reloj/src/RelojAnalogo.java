import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class RelojAnalogo extends JPanel {
    private int horas;
    private int minutos;
    private int segundos;
    private Image fondo;
    private int anchoImagen;
    private int altoImagen;
    int valorH = 0; //de 0 a 12 mañana, de 13 a 19 tarde y de 20 a 24 noche

    public RelojAnalogo() {
        this.anchoImagen = 1545;
        this.altoImagen = 865;
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        hora += valorH;
        try {
            if (hora >= 0 && hora < 13){
                fondo = ImageIO.read(new File("\\Users\\Dark6\\OneDrive\\Imágenes\\imagens\\mañana.jpg"));
                fondo = fondo.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH);
            } else if (hora >= 13 && hora < 20){
                fondo = ImageIO.read(new File("\\Users\\Dark6\\OneDrive\\Imágenes\\imagens\\tarde.jpg"));
                fondo = fondo.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH);
            } else {
                fondo = ImageIO.read(new File("\\Users\\Dark6\\OneDrive\\Imágenes\\imagens\\noche.jpg"));
                fondo = fondo.getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(1000, e -> {
            actualizarHora();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, this);
        }
        dibujarReloj(g);
    }

    private void dibujarReloj(Graphics g) {    
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        hora += valorH;
        if (hora >= 0 && hora < 13){
            int centroX = fondo.getWidth(this) / 2;
            int centroY = fondo.getHeight(this) / 2;

            Color moradoO = new Color(80, 36, 89); //morado oscuro
            Color morado = new Color(129, 99, 139); //morado claro
            Color moradoC = new Color(182, 149, 192); //morado muy claro 
            Color cafeC = new Color(168, 111, 79); //cafe claro
            Color cafeO = new Color(105, 69, 49); //cafe oscuro
            Color cafeCC = new Color(217, 143, 102); //cafe clarocalro
            Color cafeCT = new Color(148, 98, 70); //cafe un poco mas oscuro
            Color rosa = new Color(252, 143, 155); //rosadito
            Color rojo = new Color(0, 0, 0); //oscuro

            int radioCirculo2 = Math.min(centroX, centroY) - 180;
            g.setColor(moradoO);
            g.fillOval(centroX - radioCirculo2, centroY - radioCirculo2, radioCirculo2 * 2, radioCirculo2 * 2);
            int radioCirculo = Math.min(centroX, centroY) - 200;
            g.setColor(morado);
            g.fillOval(centroX - radioCirculo, centroY - radioCirculo, radioCirculo * 2, radioCirculo * 2);

            int radio = Math.min(centroX, centroY) - 200;
            int radio2 = Math.min(centroX, centroY) - 180;

            CirculoPuntoMedio(g, centroX, centroY, radio, rojo);
            CirculoPuntoMedio(g, centroX, centroY, radio2, rojo);
            
            double anguloHoras = Math.toRadians((horas % 12) * 30 - 90);
            double anguloMinutos = Math.toRadians(minutos * 6 - 90);
            double anguloSegundos = Math.toRadians(segundos * 6 - 90);

            int xHoras = (int) (centroX + radio * 0.5 * Math.cos(anguloHoras));
            int yHoras = (int) (centroY + radio * 0.5 * Math.sin(anguloHoras));
            int xMinutos = (int) (centroX + radio * 0.6 * Math.cos(anguloMinutos));
            int yMinutos = (int) (centroY + radio * 0.6 * Math.sin(anguloMinutos));
            int xSegundos = (int) (centroX + radio * 0.6 * Math.cos(anguloSegundos));
            int ySegundos = (int) (centroY + radio * 0.6 * Math.sin(anguloSegundos));

            //oreja izquierda
            int[] orejraI1x = {700, 740, centroX + 25};
            int[] orejraI1y = {340, 300, 400};
            int[] orejraI2x = {700, 670, 740};
            int[] orejraI2y = {340, 260, 300};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraI1x, orejraI1y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraI1x, orejraI1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraI2x, orejraI2y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraI2x, orejraI2y, 3);
            Linea(g, 700, 340, 740, 300, cafeC, 1);

            //oreja interna izquierda
            int[] orejraIn1x = {707, 735, centroX + 25};
            int[] orejraIn1y = {334, 306, 400};
            int[] orejraIn2x = {707, 674, 735};
            int[] orejraIn2y = {334, 265, 306};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIn1x, orejraIn1y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraIn1x, orejraIn1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIn2x, orejraIn2y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraIn2x, orejraIn2y, 3);
            Linea(g, 707, 334, 735, 306, cafeO, 1);

            //pelaje de oreja izquierda
            int[] orejraIp1x = {725, 735, centroX + 25};
            int[] orejraIp1y = {345, 306, 400};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIp1x, orejraIp1y, 3);
            g.setColor(moradoC);
            g.fillPolygon(orejraIp1x, orejraIp1y, 3);

            //oreja derecha
            int[] orejraD1x = {840, 800, centroX - 25};
            int[] orejraD1y = {340, 300, 400};
            int[] orejraD2x = {870, 800, 840};
            int[] orejraD2y = {260, 300, 340};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraD1x, orejraD1y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraD1x, orejraD1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraD2x, orejraD2y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraD2x, orejraD2y, 3);
            Linea(g, 800, 300, 840, 340, cafeC, 1);

            //oreja interna derecha
            int[] orejraDn1x = {835, 807, centroX - 25};
            int[] orejraDn1y = {334, 306, 400};
            int[] orejraDn2x = {866, 807, 835};
            int[] orejraDn2y = {265, 306, 334};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDn1x, orejraDn1y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraDn1x, orejraDn1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDn2x, orejraDn2y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraDn2x, orejraDn2y, 3);
            Linea(g, 807, 306, 835, 334, cafeO, 1);

            //pelaje de oreja derecha
            int[] orejraDp1x = {818, 807, centroX - 25};
            int[] orejraDp1y = {345, 306, 400};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDp1x, orejraDp1y, 3);
            g.setColor(moradoC);
            g.fillPolygon(orejraDp1x, orejraDp1y, 3);

            //Cola
            Elipse(g, centroX + 65, centroY + 65, 85, 30, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 38 / 2, centroY + 70 / 2, 169, 60);

            int[] orejraCx = {885, 885, 950};
            int[] orejraCy = {centroY + 91, centroY + 39, centroY + 65};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraCx, orejraCy, 3);
            g.setColor(moradoC);
            g.fillPolygon(orejraCx, orejraCy, 3);
            
            //cuerpo
            Elipse(g, centroX, centroY, 60, 90, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 120 / 2, centroY - 180 / 2, 120, 180);

            //Patas
            Elipse(g, centroX - 45, centroY + 85, 35, 55, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 80, centroY + 31, 70, 109);
            Elipse(g, centroX + 45, centroY + 85, 35, 55, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX + 10, centroY + 31, 70, 109);

            //Patas interna
            Elipse(g, centroX - 45, centroY + 100, 20, 25, Color.BLACK);
            g.setColor(cafeCC);
            g.fillOval(centroX - 65, centroY + 75, 40, 50);
            Elipse(g, centroX + 45, centroY + 100, 20, 25, Color.BLACK);
            g.setColor(cafeCC);
            g.fillOval(centroX + 26, centroY + 75, 40, 50);

            //garras
            Linea(g, 800, 470, 805, 485, Color.BLACK, 2);
            Linea(g, 817, 464, 817, 485, Color.BLACK, 2);
            Linea(g, 835, 470, 830, 485, Color.BLACK, 2);
            Linea(g, 710, 470, 715, 485, Color.BLACK, 2);
            Linea(g, 727, 464, 727, 485, Color.BLACK, 2);
            Linea(g, 745, 470, 740, 485, Color.BLACK, 2);

            //Pelaje
            Elipse(g, centroX, 395, 55, 45, Color.BLACK);
            g.setColor(moradoC);
            g.fillOval(centroX - 110 / 2, 440 - 180 / 2, 110, 90);

            //Cabeza
            Elipse(g, centroX, 370, 55, 45, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 110 / 2, 415 - 180 / 2, 110, 90);

            //Boca
            Elipse(g, centroX, 388, 22, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(centroX - 45 / 2, centroY - 119 / 2, 45, 30);
            Elipse(g, centroX, 395, 12, 5, Color.BLACK);
            g.setColor(Color.RED);
            g.fillOval(centroX - 25 / 2, centroY - 85 / 2, 25, 10);
            g.setColor(cafeC);
            g.fillRect(750, 370, 45, 15);

            //Nariz
            Elipse(g, centroX, 370, 8, 4, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(centroX - 17 / 2, centroY - 132 / 2, 18, 8);

            //Ojos
            Elipse(g, centroX - 25, 355, 10, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(737, 341, 20, 30);
            g.setColor(moradoC);
            g.fillOval(745, 342, 10, 20);
            Elipse(g, centroX + 25, 355, 10, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(787, 341, 20, 30);
            g.setColor(moradoC);
            g.fillOval(795, 342, 10, 20);

            Font fuenteGruesa = new Font("NombreDeLaFuente", Font.BOLD, 20);
            g.setFont(fuenteGruesa);

            for (int i = 1; i <= 12; i++) {
                double angulo = Math.toRadians(i * 30 - 90);
                int xNumero = (int) (centroX + radio * 0.8 * Math.cos(angulo));
                int yNumero = (int) (centroY + radio * 0.8 * Math.sin(angulo));
                String numero = String.valueOf(i);
                FontMetrics fm = g.getFontMetrics();
                int anchoTexto = fm.stringWidth(numero);
                int altoTexto = fm.getAscent();
                g.setColor(rojo);
                g.drawString(numero, xNumero - anchoTexto / 2, yNumero + altoTexto / 2);
            }

            for (int i = 1; i <= 12; i++) {
                double angulo = Math.toRadians(i * 30 - 90);
                int x1 = (int) (centroX + radio * 0.98 * Math.cos(angulo));
                int y1 = (int) (centroY + radio * 0.98 * Math.sin(angulo));
                int x2 = (int) (centroX + radio * 0.88 * Math.cos(angulo));
                int y2 = (int) (centroY + radio * 0.88 * Math.sin(angulo));
                Linea(g, x1, y1, x2, y2, rojo, 3);
        
                for (int j = 1; j <= 4; j++) {
                    double anguloPequeno = Math.toRadians(i * 30 - 90 + j * 6);
                    int x3 = (int) (centroX + radio * 0.96 * Math.cos(anguloPequeno));
                    int y3 = (int) (centroY + radio * 0.96 * Math.sin(anguloPequeno));
                    int x4 = (int) (centroX + radio * 0.91 * Math.cos(anguloPequeno));
                    int y4 = (int) (centroY + radio * 0.91 * Math.sin(anguloPequeno));
                    Linea(g, x3, y3, x4, y4, rojo, 3);
                }
            }
            
            Linea(g, centroX, centroY, xHoras, yHoras, cafeCT, 30); //horas
            Linea(g, centroX, centroY, xMinutos, yMinutos, cafeCT, 30); //minutos
            Linea(g, centroX, centroY, xSegundos, ySegundos, Color.RED, 15); //segundos

            //fruta
            Elipse(g, centroX, centroY, 25, 25, Color.BLACK);
            g.setColor(rosa);
            g.fillOval(centroX - 25, centroY - 25, 50, 50);

        } else if (hora >= 13 && hora < 20) {
            int centroX = fondo.getWidth(this) / 2;
            int centroY = fondo.getHeight(this) / 2;

            Color naranja = new Color(255, 130, 67); //naranja
            Color antiguo2 = new Color(204, 198, 185); //blanco antiguo tonalidad diferente
            Color naranjaO = new Color(226, 106, 44); //naranja mas oscuro
            Color cafeC = new Color(168, 111, 79); //cafe claro
            Color cafeO = new Color(105, 69, 49); //cafe oscuro
            Color cafeCC = new Color(217, 143, 102); //cafe clarocalro
            Color cafeCT = new Color(148, 98, 70); //cafe un poco mas oscuro
            Color rosa = new Color(252, 143, 155); //rosadito

            int radioCirculo2 = Math.min(centroX, centroY) - 180;
            g.setColor(naranjaO);
            g.fillOval(centroX - radioCirculo2, centroY - radioCirculo2, radioCirculo2 * 2, radioCirculo2 * 2);
            int radioCirculo = Math.min(centroX, centroY) - 200;
            g.setColor(naranja);
            g.fillOval(centroX - radioCirculo, centroY - radioCirculo, radioCirculo * 2, radioCirculo * 2);

            int radio = Math.min(centroX, centroY) - 200;
            int radio2 = Math.min(centroX, centroY) - 180;

            CirculoPuntoMedio(g, centroX, centroY, radio, Color.BLACK);
            CirculoPuntoMedio(g, centroX, centroY, radio2, Color.BLACK);
            
            double anguloHoras = Math.toRadians((horas % 12) * 30 - 90);
            double anguloMinutos = Math.toRadians(minutos * 6 - 90);
            double anguloSegundos = Math.toRadians(segundos * 6 - 90);

            int xHoras = (int) (centroX + radio * 0.5 * Math.cos(anguloHoras));
            int yHoras = (int) (centroY + radio * 0.5 * Math.sin(anguloHoras));
            int xMinutos = (int) (centroX + radio * 0.6 * Math.cos(anguloMinutos));
            int yMinutos = (int) (centroY + radio * 0.6 * Math.sin(anguloMinutos));
            int xSegundos = (int) (centroX + radio * 0.6 * Math.cos(anguloSegundos));
            int ySegundos = (int) (centroY + radio * 0.6 * Math.sin(anguloSegundos));

            //oreja izquierda
            int[] orejraI1x = {700, 740, centroX + 25};
            int[] orejraI1y = {340, 300, 400};
            int[] orejraI2x = {700, 670, 740};
            int[] orejraI2y = {340, 260, 300};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraI1x, orejraI1y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraI1x, orejraI1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraI2x, orejraI2y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraI2x, orejraI2y, 3);
            Linea(g, 700, 340, 740, 300, cafeC, 1);

            //oreja interna izquierda
            int[] orejraIn1x = {707, 735, centroX + 25};
            int[] orejraIn1y = {334, 306, 400};
            int[] orejraIn2x = {707, 674, 735};
            int[] orejraIn2y = {334, 265, 306};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIn1x, orejraIn1y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraIn1x, orejraIn1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIn2x, orejraIn2y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraIn2x, orejraIn2y, 3);
            Linea(g, 707, 334, 735, 306, cafeO, 1);

            //pelaje de oreja izquierda
            int[] orejraIp1x = {725, 735, centroX + 25};
            int[] orejraIp1y = {345, 306, 400};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIp1x, orejraIp1y, 3);
            g.setColor(antiguo2);
            g.fillPolygon(orejraIp1x, orejraIp1y, 3);

            //oreja derecha
            int[] orejraD1x = {840, 800, centroX - 25};
            int[] orejraD1y = {340, 300, 400};
            int[] orejraD2x = {870, 800, 840};
            int[] orejraD2y = {260, 300, 340};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraD1x, orejraD1y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraD1x, orejraD1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraD2x, orejraD2y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraD2x, orejraD2y, 3);
            Linea(g, 800, 300, 840, 340, cafeC, 1);

            //oreja interna derecha
            int[] orejraDn1x = {835, 807, centroX - 25};
            int[] orejraDn1y = {334, 306, 400};
            int[] orejraDn2x = {866, 807, 835};
            int[] orejraDn2y = {265, 306, 334};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDn1x, orejraDn1y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraDn1x, orejraDn1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDn2x, orejraDn2y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraDn2x, orejraDn2y, 3);
            Linea(g, 807, 306, 835, 334, cafeO, 1);

            //pelaje de oreja derecha
            int[] orejraDp1x = {818, 807, centroX - 25};
            int[] orejraDp1y = {345, 306, 400};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDp1x, orejraDp1y, 3);
            g.setColor(antiguo2);
            g.fillPolygon(orejraDp1x, orejraDp1y, 3);

            //Cola
            Elipse(g, centroX + 65, centroY + 65, 85, 30, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 38 / 2, centroY + 70 / 2, 169, 60);

            int[] orejraCx = {885, 885, 950};
            int[] orejraCy = {centroY + 91, centroY + 39, centroY + 65};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraCx, orejraCy, 3);
            g.setColor(antiguo2);
            g.fillPolygon(orejraCx, orejraCy, 3);
            
            //cuerpo
            Elipse(g, centroX, centroY, 60, 90, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 120 / 2, centroY - 180 / 2, 120, 180);

            //Patas
            Elipse(g, centroX - 45, centroY + 85, 35, 55, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 80, centroY + 31, 70, 109);
            Elipse(g, centroX + 45, centroY + 85, 35, 55, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX + 10, centroY + 31, 70, 109);

            //Patas interna
            Elipse(g, centroX - 45, centroY + 100, 20, 25, Color.BLACK);
            g.setColor(cafeCC);
            g.fillOval(centroX - 65, centroY + 75, 40, 50);
            Elipse(g, centroX + 45, centroY + 100, 20, 25, Color.BLACK);
            g.setColor(cafeCC);
            g.fillOval(centroX + 26, centroY + 75, 40, 50);

            //garras
            Linea(g, 800, 470, 805, 485, Color.BLACK, 2);
            Linea(g, 817, 464, 817, 485, Color.BLACK, 2);
            Linea(g, 835, 470, 830, 485, Color.BLACK, 2);
            Linea(g, 710, 470, 715, 485, Color.BLACK, 2);
            Linea(g, 727, 464, 727, 485, Color.BLACK, 2);
            Linea(g, 745, 470, 740, 485, Color.BLACK, 2);

            //Pelaje
            Elipse(g, centroX, 395, 55, 45, Color.BLACK);
            g.setColor(antiguo2);
            g.fillOval(centroX - 110 / 2, 440 - 180 / 2, 110, 90);

            //Cabeza
            Elipse(g, centroX, 370, 55, 45, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 110 / 2, 415 - 180 / 2, 110, 90);

            //Boca
            Elipse(g, centroX, 388, 22, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(centroX - 45 / 2, centroY - 119 / 2, 45, 30);
            Elipse(g, centroX, 395, 12, 5, Color.BLACK);
            g.setColor(Color.RED);
            g.fillOval(centroX - 25 / 2, centroY - 85 / 2, 25, 10);
            g.setColor(cafeC);
            g.fillRect(750, 370, 45, 15);

            //Nariz
            Elipse(g, centroX, 370, 8, 4, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(centroX - 17 / 2, centroY - 132 / 2, 18, 8);

            //Ojos
            Elipse(g, centroX - 25, 355, 10, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(737, 341, 20, 30);
            g.setColor(Color.WHITE);
            g.fillOval(745, 347, 10, 20);
            Elipse(g, centroX + 25, 355, 10, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(787, 341, 20, 30);
            g.setColor(Color.WHITE);
            g.fillOval(789, 347, 10, 20);

            Font fuenteGruesa = new Font("NombreDeLaFuente", Font.BOLD, 20);
            g.setFont(fuenteGruesa);

            for (int i = 1; i <= 12; i++) {
                double angulo = Math.toRadians(i * 30 - 90);
                int xNumero = (int) (centroX + radio * 0.8 * Math.cos(angulo));
                int yNumero = (int) (centroY + radio * 0.8 * Math.sin(angulo));
                String numero = String.valueOf(i);
                FontMetrics fm = g.getFontMetrics();
                int anchoTexto = fm.stringWidth(numero);
                int altoTexto = fm.getAscent();
                g.setColor(Color.BLACK);
                g.drawString(numero, xNumero - anchoTexto / 2, yNumero + altoTexto / 2);
            }

            for (int i = 1; i <= 12; i++) {
                double angulo = Math.toRadians(i * 30 - 90);
                int x1 = (int) (centroX + radio * 0.98 * Math.cos(angulo));
                int y1 = (int) (centroY + radio * 0.98 * Math.sin(angulo));
                int x2 = (int) (centroX + radio * 0.88 * Math.cos(angulo));
                int y2 = (int) (centroY + radio * 0.88 * Math.sin(angulo));
                Linea(g, x1, y1, x2, y2, Color.BLACK, 3);
        
                for (int j = 1; j <= 4; j++) {
                    double anguloPequeno = Math.toRadians(i * 30 - 90 + j * 6);
                    int x3 = (int) (centroX + radio * 0.96 * Math.cos(anguloPequeno));
                    int y3 = (int) (centroY + radio * 0.96 * Math.sin(anguloPequeno));
                    int x4 = (int) (centroX + radio * 0.91 * Math.cos(anguloPequeno));
                    int y4 = (int) (centroY + radio * 0.91 * Math.sin(anguloPequeno));
                    Linea(g, x3, y3, x4, y4, Color.BLACK, 3);
                }
            }
            
            Linea(g, centroX, centroY, xHoras, yHoras, cafeCT, 30); //horas
            Linea(g, centroX, centroY, xMinutos, yMinutos, cafeCT, 30); //minutos
            Linea(g, centroX, centroY, xSegundos, ySegundos, Color.RED, 15); //segundos

            //fruta
            Elipse(g, centroX, centroY, 25, 25, Color.BLACK);
            g.setColor(rosa);
            g.fillOval(centroX - 25, centroY - 25, 50, 50);

        } else {
            int centroX = fondo.getWidth(this) / 2;
            int centroY = fondo.getHeight(this) / 2;

            Color Amarillo = new Color(255, 245, 45); //Amarillo
            Color Gris = new Color(33, 33, 33); //Gris
            Color cafeC = new Color(168, 111, 79); //cafe claro
            Color cafeO = new Color(105, 69, 49); //cafe oscuro
            Color cafeCC = new Color(217, 143, 102); //cafe clarocalro
            Color cafeCT = new Color(148, 98, 70); //cafe un poco mas oscuro
            Color rosa = new Color(252, 143, 155); //rosadito

            int radioCirculo2 = Math.min(centroX, centroY) - 180;
            g.setColor(Color.BLACK);
            g.fillOval(centroX - radioCirculo2, centroY - radioCirculo2, radioCirculo2 * 2, radioCirculo2 * 2);
            int radioCirculo = Math.min(centroX, centroY) - 200;
            g.setColor(Gris);
            g.fillOval(centroX - radioCirculo, centroY - radioCirculo, radioCirculo * 2, radioCirculo * 2);

            int radio = Math.min(centroX, centroY) - 200;
            int radio2 = Math.min(centroX, centroY) - 180;

            CirculoPuntoMedio(g, centroX, centroY, radio, Amarillo);
            CirculoPuntoMedio(g, centroX, centroY, radio2, Amarillo);
            
            double anguloHoras = Math.toRadians((horas % 12) * 30 - 90);
            double anguloMinutos = Math.toRadians(minutos * 6 - 90);
            double anguloSegundos = Math.toRadians(segundos * 6 - 90);

            int xHoras = (int) (centroX + radio * 0.5 * Math.cos(anguloHoras));
            int yHoras = (int) (centroY + radio * 0.5 * Math.sin(anguloHoras));
            int xMinutos = (int) (centroX + radio * 0.6 * Math.cos(anguloMinutos));
            int yMinutos = (int) (centroY + radio * 0.6 * Math.sin(anguloMinutos));
            int xSegundos = (int) (centroX + radio * 0.6 * Math.cos(anguloSegundos));
            int ySegundos = (int) (centroY + radio * 0.6 * Math.sin(anguloSegundos));

            //oreja izquierda
            int[] orejraI1x = {700, 740, centroX + 25};
            int[] orejraI1y = {340, 300, 400};
            int[] orejraI2x = {700, 670, 740};
            int[] orejraI2y = {340, 260, 300};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraI1x, orejraI1y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraI1x, orejraI1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraI2x, orejraI2y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraI2x, orejraI2y, 3);
            Linea(g, 700, 340, 740, 300, cafeC, 1);

            //oreja interna izquierda
            int[] orejraIn1x = {707, 735, centroX + 25};
            int[] orejraIn1y = {334, 306, 400};
            int[] orejraIn2x = {707, 674, 735};
            int[] orejraIn2y = {334, 265, 306};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIn1x, orejraIn1y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraIn1x, orejraIn1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIn2x, orejraIn2y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraIn2x, orejraIn2y, 3);
            Linea(g, 707, 334, 735, 306, cafeO, 1);

            //pelaje de oreja izquierda
            int[] orejraIp1x = {725, 735, centroX + 25};
            int[] orejraIp1y = {345, 306, 400};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraIp1x, orejraIp1y, 3);
            g.setColor(Amarillo);
            g.fillPolygon(orejraIp1x, orejraIp1y, 3);

            //oreja derecha
            int[] orejraD1x = {840, 800, centroX - 25};
            int[] orejraD1y = {340, 300, 400};
            int[] orejraD2x = {870, 800, 840};
            int[] orejraD2y = {260, 300, 340};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraD1x, orejraD1y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraD1x, orejraD1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraD2x, orejraD2y, 3);
            g.setColor(cafeC);
            g.fillPolygon(orejraD2x, orejraD2y, 3);
            Linea(g, 800, 300, 840, 340, cafeC, 1);

            //oreja interna derecha
            int[] orejraDn1x = {835, 807, centroX - 25};
            int[] orejraDn1y = {334, 306, 400};
            int[] orejraDn2x = {866, 807, 835};
            int[] orejraDn2y = {265, 306, 334};
            
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDn1x, orejraDn1y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraDn1x, orejraDn1y, 3);
            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDn2x, orejraDn2y, 3);
            g.setColor(cafeO);
            g.fillPolygon(orejraDn2x, orejraDn2y, 3);
            Linea(g, 807, 306, 835, 334, cafeO, 1);

            //pelaje de oreja derecha
            int[] orejraDp1x = {818, 807, centroX - 25};
            int[] orejraDp1y = {345, 306, 400};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraDp1x, orejraDp1y, 3);
            g.setColor(Amarillo);
            g.fillPolygon(orejraDp1x, orejraDp1y, 3);

            //Cola
            Elipse(g, centroX + 65, centroY + 65, 85, 30, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 38 / 2, centroY + 70 / 2, 169, 60);

            int[] orejraCx = {885, 885, 950};
            int[] orejraCy = {centroY + 91, centroY + 39, centroY + 65};

            g.setColor(Color.BLACK);
            g.drawPolygon(orejraCx, orejraCy, 3);
            g.setColor(Amarillo);
            g.fillPolygon(orejraCx, orejraCy, 3);
            
            //cuerpo
            Elipse(g, centroX, centroY, 60, 90, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 120 / 2, centroY - 180 / 2, 120, 180);

            //Patas
            Elipse(g, centroX - 45, centroY + 85, 35, 55, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 80, centroY + 31, 70, 109);
            Elipse(g, centroX + 45, centroY + 85, 35, 55, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX + 10, centroY + 31, 70, 109);

            //Patas interna
            Elipse(g, centroX - 45, centroY + 100, 20, 25, Color.BLACK);
            g.setColor(cafeCC);
            g.fillOval(centroX - 65, centroY + 75, 40, 50);
            Elipse(g, centroX + 45, centroY + 100, 20, 25, Color.BLACK);
            g.setColor(cafeCC);
            g.fillOval(centroX + 26, centroY + 75, 40, 50);

            //garras
            Linea(g, 800, 470, 805, 485, Color.BLACK, 2);
            Linea(g, 817, 464, 817, 485, Color.BLACK, 2);
            Linea(g, 835, 470, 830, 485, Color.BLACK, 2);
            Linea(g, 710, 470, 715, 485, Color.BLACK, 2);
            Linea(g, 727, 464, 727, 485, Color.BLACK, 2);
            Linea(g, 745, 470, 740, 485, Color.BLACK, 2);

            //Pelaje
            Elipse(g, centroX, 395, 55, 45, Color.BLACK);
            g.setColor(Amarillo);
            g.fillOval(centroX - 110 / 2, 440 - 180 / 2, 110, 90);

            //Cabeza
            Elipse(g, centroX, 370, 55, 45, Color.BLACK);
            g.setColor(cafeC);
            g.fillOval(centroX - 110 / 2, 415 - 180 / 2, 110, 90);

            //Boca
            Elipse(g, centroX, 388, 22, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(centroX - 45 / 2, centroY - 119 / 2, 45, 30);
            Elipse(g, centroX, 395, 12, 5, Color.BLACK);
            g.setColor(Color.RED);
            g.fillOval(centroX - 25 / 2, centroY - 85 / 2, 25, 10);
            g.setColor(cafeC);
            g.fillRect(750, 370, 45, 15);

            //Nariz
            Elipse(g, centroX, 370, 8, 4, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(centroX - 17 / 2, centroY - 132 / 2, 18, 8);

            //Ojos
            Elipse(g, centroX - 25, 355, 10, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(737, 341, 20, 30);
            g.setColor(Amarillo);
            g.fillOval(739, 342, 10, 20);
            Elipse(g, centroX + 25, 355, 10, 15, Color.BLACK);
            g.setColor(Color.BLACK);
            g.fillOval(787, 341, 20, 30);
            g.setColor(Amarillo);
            g.fillOval(789, 342, 10, 20);

            Font fuenteGruesa = new Font("NombreDeLaFuente", Font.BOLD, 20);
            g.setFont(fuenteGruesa);

            for (int i = 1; i <= 12; i++) {
                double angulo = Math.toRadians(i * 30 - 90);
                int xNumero = (int) (centroX + radio * 0.8 * Math.cos(angulo));
                int yNumero = (int) (centroY + radio * 0.8 * Math.sin(angulo));
                String numero = String.valueOf(i);
                FontMetrics fm = g.getFontMetrics();
                int anchoTexto = fm.stringWidth(numero);
                int altoTexto = fm.getAscent();
                g.setColor(Amarillo);
                g.drawString(numero, xNumero - anchoTexto / 2, yNumero + altoTexto / 2);
            }

            for (int i = 1; i <= 12; i++) {
                double angulo = Math.toRadians(i * 30 - 90);
                int x1 = (int) (centroX + radio * 0.98 * Math.cos(angulo));
                int y1 = (int) (centroY + radio * 0.98 * Math.sin(angulo));
                int x2 = (int) (centroX + radio * 0.88 * Math.cos(angulo));
                int y2 = (int) (centroY + radio * 0.88 * Math.sin(angulo));
                Linea(g, x1, y1, x2, y2, Amarillo, 3);
        
                for (int j = 1; j <= 4; j++) {
                    double anguloPequeno = Math.toRadians(i * 30 - 90 + j * 6);
                    int x3 = (int) (centroX + radio * 0.96 * Math.cos(anguloPequeno));
                    int y3 = (int) (centroY + radio * 0.96 * Math.sin(anguloPequeno));
                    int x4 = (int) (centroX + radio * 0.91 * Math.cos(anguloPequeno));
                    int y4 = (int) (centroY + radio * 0.91 * Math.sin(anguloPequeno));
                    Linea(g, x3, y3, x4, y4, Amarillo, 3);
                }
            }
            
            Linea(g, centroX, centroY, xHoras, yHoras, cafeCT, 30); //horas
            Linea(g, centroX, centroY, xMinutos, yMinutos, cafeCT, 30); //minutos
            Linea(g, centroX, centroY, xSegundos, ySegundos, Color.RED, 15); //segundos

            //fruta
            Elipse(g, centroX, centroY, 25, 25, Color.BLACK);
            g.setColor(rosa);
            g.fillOval(centroX - 25, centroY - 25, 50, 50);
        }
    }

    private void pix(Graphics g, int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }

    private void Linea(Graphics g, int x1, int y1, int x2, int y2, Color c, int grosor) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(grosor));
    
        g2d.setColor(c);
        g2d.drawLine(x1, y1, x2, y2);
    
        g2d.setStroke(oldStroke);
    }

    private void CirculoPuntoMedio(Graphics g, int xc, int yc, int r, Color c) {
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

    public void ElipseRotada(Graphics g, int xc, int yc, int a, int b, double angulo, Color c) {
        double radianes = Math.toRadians(angulo);
        double cosA = Math.cos(radianes);
        double sinA = Math.sin(radianes);
    
        int a2 = a * a;
        int b2 = b * b;
        int twoa2 = 2 * a2;
        int twob2 = 2 * b2;
        int p;
        int x = 0;
        int y = b;
        int px = 0;
        int py = twoa2 * y;
    
        int xRotado = (int) (x * cosA - y * sinA) + xc;
        int yRotado = (int) (x * sinA + y * cosA) + yc;
        pix(g, xRotado, yRotado, c);
    
        xRotado = (int) (x * cosA + y * sinA) + xc;
        yRotado = (int) (-x * sinA + y * cosA) + yc;
        pix(g, xRotado, yRotado, c);
    
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
    
            xRotado = (int) (x * cosA - y * sinA) + xc;
            yRotado = (int) (x * sinA + y * cosA) + yc;
            pix(g, xRotado, yRotado, c);
    
            xRotado = (int) (-x * cosA - y * sinA) + xc;
            yRotado = (int) (-x * sinA + y * cosA) + yc;
            pix(g, xRotado, yRotado, c);
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
    
            xRotado = (int) (x * cosA - y * sinA) + xc;
            yRotado = (int) (x * sinA + y * cosA) + yc;
            pix(g, xRotado, yRotado, c);
    
            xRotado = (int) (-x * cosA - y * sinA) + xc;
            yRotado = (int) (-x * sinA + y * cosA) + yc;
            pix(g, xRotado, yRotado, c);
        }
    }
    

    private void actualizarHora() {
        Calendar calendario = Calendar.getInstance();
        horas = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reloj Analógico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        RelojAnalogo reloj = new RelojAnalogo();
        frame.add(reloj);
        frame.setVisible(true);
    }
}
