package Main;


import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); //crear nueva ventana
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que aparezca el boton de cerrar
        window.setResizable(false); //no cambiar el tamano de la ventana
        window.setTitle("Farm Island"); //título de la ventana

        // Creamos el cardLayout y el panel contenedor
        CardLayout cardLayout = new CardLayout();
        JPanel panelContenedor = new JPanel(cardLayout);
        PanelJuego panel = new PanelJuego();
        final PantallaInicio[] pantallaInicioRef = new PantallaInicio[1];  //creando una referencia para la pantalla de inicio

        PantallaInicio pantallaInicio = new PantallaInicio(e -> {
            pantallaInicioRef[0].detenerMusica(); // Detener la música del menú
            cardLayout.show(panelContenedor, "JUEGO"); // Cambiar a la pantalla del juego
            panel.requestFocusInWindow(); // Asegurarse de que el panel del juego tenga el foco para recibir eventos de teclado
            panel.startGameThread(); // Iniciar el hilo del juego
        });

        pantallaInicioRef[0] = pantallaInicio; // Asignar la referencia de pantallaIniciosa


        panelContenedor.add(pantallaInicio, "MENU");
        panelContenedor.add(panel, "JUEGO");
        window.add(panelContenedor);
        window.pack(); // setea la ventana al tamano preferido

        window.setLocationRelativeTo(null); //ponerlo en null significa que va a ir en el centro
        window.setVisible(true); //para poder ver la ventana

        //panel.startGameThread();
    }
}