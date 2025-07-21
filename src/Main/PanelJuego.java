package Main;

import Entidades.Jugador;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable {

    //ajustes de pantala
    final int originalTileSize = 16; //tamano de 16x16 de cada cuadro de entidad
    final int scale = 3; //escalado de tamano

    public final int tileSize = originalTileSize * scale; //tamano despues del escalado
    public final int maxScreenCol = 32; //numero de columnas
    public final int maxScreenRow = 18; //numero de filas
    public final int screenWidth = tileSize * maxScreenCol; //ancho de pantalla: 1536 pixeles
    public final int screenHeight = tileSize * maxScreenRow; //largo de pantalla: 1152 pixeles

    //Ajustes del Mundo

    public final int maxWorldCol = 70;  //temporal     anteriormente era 42
    public final int maxWorldRow = 70;  //temporal     anteriormente era 44
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    int FPS = 60;

    //SISTEMA
    TileManager tileM = new TileManager(this);
    InputHandler inputH = new InputHandler(); //clase para manejar las acciones del usuario
    Sound sonido = new Sound(); //instanciar la clase sonido
    public CollisionChecker hitbox = new CollisionChecker(this);
    Thread gameThread; //crea un "reloj" para el juego, no para de contar hasta que tu lo cierras

    //ENTIDADES
    public Jugador jugador1 = new Jugador(this, inputH);  //instanciar clase Jugador


    public PanelJuego() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setear el tamano de la clase con el ancho y el largo
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);  //existen otras opciones pero esta es mas simple
        this.addKeyListener(inputH); //para que pueda reconocer los inputs
        this.setFocusable(true); //para que haga focus en este panel

        setupGame();
    }

    public void setupGame() { //inicializar el juego
        playMusic(0); //llamada a la funcion que reproduce la musica de fondo
    }

    public void startGameThread() {
        gameThread = new Thread(this); //estamos pasandole la clase PanelJuego a este objeto, instanciando el hilo(thread)
        gameThread.start();

    }

    @Override
    public void run() {  //Cuando empezamos el objeto gameThread llama a este metodo automaticamente

        double intervaloDibujo = 1000000000 / FPS; //cantidad de nanosegundos en un segundos entre los fps(60)
        double delta = 0;
        long lastTime = System.nanoTime(); //tiempo actual en segundos
        long tiempoActual;

        while (gameThread != null) { //mientras el hilo este activo siempre va a repetir este proceso

            tiempoActual = System.nanoTime();  //revisamos el tiempo actual
            delta += (tiempoActual - lastTime) / intervaloDibujo;  //actualiza el tiempo de dibujo y se lo anade a delta
            lastTime = tiempoActual; //ultimo tiempo se convierte en el actual para crear el ciclo

            if (delta >= 1) {
                //Actu1: Actualizar info como la posicion del personaje
                update(); //llamada a update

                //Actu2: Actualizar la pantalla cuando nos movamos
                repaint(); //llamada a paint

                delta--;  //resetea el delta
            }


        }

    }

    public void update() {  //En Java el(0,0) esta arriba a la derecha, la X aumenta hacia la derecha y la Y aumenta hacia abajo

        jugador1.update();
    }

    public void paintComponent(Graphics g) {  //cada vez que nos movamos, esta funcion "pinta" al personaje otra vez
        super.paintComponent(g);  //el super es para la clase padre de esta(Jpanel)
        Graphics2D g2 = (Graphics2D) g; //estamos cambiando una clase por otra, las 2da permite mejor control de geometria, posicion etc

        setBackground(new Color(36, 172, 228));
        tileM.draw(g2);
        jugador1.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        sonido.setFile(i);
        sonido.play();
        sonido.loop();
    }

    public void stopMusic() {
        sonido.stop();
    }

    public void playSoundEffect(int i) {
        sonido.setFile(i);
        sonido.play();
    }
}
