package Main;

import Entidades.Entidad;
import Entidades.Jugador;
import Objetos.Semillas;
import Objetos.SuperObjetos;
import Tiendas.TiendaComprar;
import Tiendas.TiendaVender;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable {
    //Estadel juego
    public boolean pausado=false;
    public boolean mostrarDialogo = false;
    public boolean mostrarTiendaComprar = false;
    public boolean mostrarTiendaVender = false;
    public boolean inventarioAbierto = false;
    public boolean accion = false;
    //ajustes de pantalla
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
    InputHandler inputH = new InputHandler(this); //clase para manejar las acciones del usuario
    Sound sonido = new Sound(); //instanciar la clase sonido
    public CollisionChecker hitbox = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this); //instanciar para crear objetos
    Thread gameThread; //crea un "reloj" para el juego, no para de contar hasta que tu lo cierras
    //TIENDAS
    public TiendaComprar tiendaComprar = new TiendaComprar(this);
    public TiendaVender tiendaVender = new TiendaVender(this);
    //OBJETOS
    public Semillas semillas[]= new Semillas[50];
    public SuperObjetos objeto[] = new SuperObjetos[50];//Cantidad de objetos que se pueden monstrar a la vez
    //ENTIDADES
    public Jugador jugador1 = new Jugador(this, inputH);  //instanciar clase Jugador
    public Entidad npc[] = new Entidad[10];
    //INVENTARIO
    public Inventario inventario= new Inventario(this);
    //DIALOGOS
    public Ventanas ventanas = new Ventanas(this);
    public Interacciones interacciones  = new Interacciones(this);
    //TIEMPO
    public Tiempo tiempo = new Tiempo();


    public PanelJuego() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setear el tamano de la clase con el ancho y el largo
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);  //existen otras opciones pero esta es mas simple
        this.addKeyListener(inputH); //para que pueda reconocer los inputs
        this.setFocusable(true); //para que haga focus en este panel

        //setupGame();
    }

    public void setupGame() {
        //inicializar el juego
        playMusic(0); //llamada a la funcion que reproduce la musica de fondo
        assetSetter.setObjeto();
        assetSetter.setNPC();
        pausado=false;

    }

    public void startGameThread() {
        setupGame(); //llamada a la funcion que inicializa el juego
        gameThread = new Thread(this); //estamos pasandole la clase PanelJuego a este objeto, instanciando el hilo(thread)
        gameThread.start();

    }

    @Override
    public void run() {  //Cuando empezamos el objeto gameThread llama a este metodo automaticamente

        double intervaloDibujo = 1000000000 / FPS; //cantidad de nanosegundos en un segundos entre los fps(60)
        double delta = 0;
        long lastTime = System.nanoTime(); //tiempo actual en segundos
        long lastTimeTimer = lastTime; //para el tiempo dentro del juego
        long tiempoActual;

        while (gameThread != null) { //mientras el hilo este activo siempre va a repetir este proceso

            tiempoActual = System.nanoTime();  //revisamos el tiempo actual
            delta += (tiempoActual - lastTime) / intervaloDibujo;  //actualiza el tiempo de dibujo y se lo anade a delta
            lastTime = tiempoActual; //ultimo tiempo se convierte en el actual para crear el ciclo

            if (delta >= 1) {
                //Calcular tiempo real transcurrido antes de actualizar lastTime
                //Esto asegura que el temporizador avance correctamente
                long tiempoTranscurrido = tiempoActual - lastTimeTimer;

                //Actualizar tiempo del juego con el tiempo transcurrido real
                tiempo.actualizar(tiempoTranscurrido);
                //Actu1: Actualizar info como la posicion del personaje
                update(); //llamada a update

                //Actu2: Actualizar la pantalla cuando nos movamos
                repaint(); //llamada a paint

                delta--;  //resetea el delta
                lastTimeTimer = tiempoActual;
            }

        }

    }

    public void update() {  //En Java el(0,0) esta arriba a la derecha, la X aumenta hacia la derecha y la Y aumenta hacia abajo
        //Actualiza jugador
        if (pausado) {
            //Hace nada ta pausado :P
        }else{
            sonido.play();
            jugador1.update();
        }
        //Actualiza npcs
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
            }
        }
    }

    public void paintComponent(Graphics g) {  //cada vez que nos movamos, esta funcion "pinta" al personaje otra vez
        super.paintComponent(g);  //el super es para la clase padre de esta(Jpanel)
        Graphics2D g2 = (Graphics2D) g; //estamos cambiando una clase por otra, las 2da permite mejor control de geometria, posicion etc

        setBackground(new Color(36, 172, 228));
        //Dibujar el mapa
        tileM.draw(g2);
        //Dibujar objetos
        for (int i = 0; i < objeto.length; i++) {//Verifica el arreglo de los objetos que se creo arriba
            if (objeto[i] != null) {//Evita un null pointer error
                objeto[i].dibujar(g2, this);
            }
        }
        //Dibujar NPC
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
             npc[i].dibujar(g2, this);
            }
        }
        //Dibujar el jugador
        jugador1.draw(g2);
        //Dibujar ventanas
        if (mostrarDialogo) {
            ventanas.dibujarPantallaDialogo(g2);
            if (interacciones.interaccionActiva){
                mostrarDialogo = true;
            }
        }
        if (mostrarTiendaComprar) {
            ventanas.dibujarVentanaTienda(g2);
            tiendaComprar.dibujar(g2);
            tiendaComprar.dibujarInformacion(g2);
        }
        if (mostrarTiendaVender){
            ventanas.dibujarVentanaTienda(g2);
            tiendaVender.dibujar(g2);
        }
        if (pausado) {
            ventanas.dibujarPausaVentana(g2);
        }
        //Dibujar inventario
        if (inventario.activo) {
            inventario.dibujar(g2);
        }
        //Dibujar tiempo y dia en pantalla
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.WHITE);

        String tiempoTexto = String.format("Tiempo: %02d:%02d:%02d", tiempo.getHoras(), tiempo.getMinutos(), tiempo.getSegundos());
        g2.drawString(tiempoTexto, 20, 30);

        String diaTexto = "Día: " + tiempo.getDiaActual();
        g2.drawString(diaTexto, 20, 55);
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
