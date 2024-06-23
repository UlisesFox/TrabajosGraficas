public class Inicio {
    public static void main(String[] args) {
        Thread hiloMusica = new Thread(() -> Musica.main(args));
        Thread hiloTheFactory = new Thread(() -> TheFactory.main(args));
        
        hiloMusica.start();
        hiloTheFactory.start();
    }
}