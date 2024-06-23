import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musica {
    public static void main(String[] args) {
        music();
    }

    public static void music(){
        try {
            Clip clip = AudioSystem.getClip();

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                Musica.class.getResourceAsStream("/Sonidos de Fabrica.wav"));

            clip.open(audioInputStream);

            clip.start();

            while (!clip.isRunning()) {
                Thread.sleep(20);
            }
            while (clip.isRunning()) {
                Thread.sleep(20);
            }
            
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
