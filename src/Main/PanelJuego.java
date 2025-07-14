package Main;

import javax.swing.*;
import java.awt.*;

public class PanelJuego extends JPanel implements Runnable {

    //ajustes de pantala
    final int originalTileSize = 16; //tamano de 16x16 de cada cuadro de entidad
    final int scale = 3; //escalado de tamano

    final int tileSize = originalTileSize * scale; //tamano despues del escalado
    final int maxScreenCol = 32; //numero de columnas
    final int maxScreenRow = 24; //numero de filas
    final int screenWidth = tileSize * maxScreenCol; //ancho de pantalla: 1536 pixeles
    final int screenHeight = tileSize * maxScreenRow; //largo de pantalla: 1152 pixeles

    Thread gameThread; //crea un "reloj" para el juego, no para de contar hasta que tu lo cierras


    public PanelJuego() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //setear el tamano de la clase con el ancho y el largo
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);  //existen otras opciones pero esta es mas simple

    }

    public void startGameThread() {
        gameThread = new Thread(this); //estamos pasandole la clase PanelJuego a este objeto, instanciando el hilo(thread)
        gameThread.start();

    }

    @Override
    public void run() {  //Cuando empezamos el objeto gameThread llama a este metodo automaticamente



    }
}
