package Main;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); //crear nueva ventanassswwds
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que aparezca el boton de cerrar
        window.setResizable(false); //no cambiar el tamano de la ventana
        window.setTitle("Farm Island"); //titulo de la ventanaddsd

        // Creamos el cardLayout y el panel contenedor
        CardLayout cardLayout = new CardLayout();
        JPanel panelContenedor = new JPanel(cardLayout);
        PanelJuego panel = new PanelJuego();
        PantallaInicio pantallaInicio = new PantallaInicio(e -> {
            cardLayout.show(panelContenedor, "JUEGO");
            panel.requestFocusInWindow();
            panel.startGameThread();
        });
        panelContenedor.add(pantallaInicio, "MENU");
        panelContenedor.add(panel, "JUEGO");
        window.add(panelContenedor);
        window.pack(); // setea la ventana al tamano preferido

        window.setLocationRelativeTo(null); //ponerlo en null significa que va a ir en el centro
        window.setVisible(true); //para poder ver la ventana

        //panel.startGameThread();
    }
}