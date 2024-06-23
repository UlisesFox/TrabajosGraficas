public class Inicio {
    public static void main(String[] args) {
        Thread hiloMusica = new Thread(() -> Musica.main(args));
        Thread hiloLostCaves = new Thread(() -> LostCaves.main(args));
        
        hiloMusica.start();
        hiloLostCaves.start();
    }
}