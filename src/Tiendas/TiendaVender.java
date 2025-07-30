package Tiendas;

import Main.PanelJuego;
import java.awt.*;

public class TiendaVender {
    PanelJuego panel;
    int seleccion = 0;
    public boolean activa = false;
    public TiendaVender(PanelJuego panel) {
        this.panel = panel;
    }
    public void dibujar(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;

        //Dibuja la letra de la tienda (nombre)
        g2.setColor(new Color(0, 0, 0, 200));
        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 40));
        g2.drawString("Verduleria el John", x + 20, y -150);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));
        g2.drawString("Tienes algo para vender?", x + 30, y -100);


        //Dinero del jugador
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
        g2.setColor(Color.white);
        g2.drawString("Dinero actual: "+panel.jugador1.dinero, x + 800, y-180);


    }
    //Estados de la tienda
    public void activar() {
        activa = true;
        seleccion = 0;
    }
    public void desactivar() {
        activa = false;
    }
    public boolean estaActiva() {
        return activa;
    }
}
