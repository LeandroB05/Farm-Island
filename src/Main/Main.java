package Main;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); //crear nueva ventana
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //para que aparezca el boton de cerrar
        window.setResizable(false); //no cambiar el tamano de la ventana
        window.setTitle("ProyectoGranja"); //titulo de la ventana


        PanelJuego panel = new PanelJuego();
        window.add(panel);

        window.pack(); // setea la ventana al tamano preferido

        window.setLocationRelativeTo(null); //ponerlo en null significa que va a ir en el centro
        window.setVisible(true); //para poder ver la ventana

        panel.startGameThread();




    }
}