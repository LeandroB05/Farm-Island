package Main;

import Objetos.SuperObjetos;

import java.awt.*;
import java.util.ArrayList;

public class Inventario {
    PanelJuego panel;
    boolean activo = false;

    public Inventario(PanelJuego panel) {
        this.panel = panel;
    }

    public void draw(Graphics2D g2) {
        if (!activo) return;

        int x = panel.tileSize;
        int y = panel.tileSize + 20;
        int w = panel.screenWidth - panel.tileSize * 25;
        int h = panel.tileSize * 15;

        // Fondo
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRoundRect(x, y, w, h, 25, 25);

        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
        g2.drawString("Inventario", x + 20, y + 30);
        //Mensajes
        if (panel.jugador1.inventario.isEmpty()) {
            g2.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
            g2.drawString("(Inventario vacio)", x + 30, y+100);
        }
        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
        g2.drawString("Q: anterior  E: siguiente", x + 30, y+680);
        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
        g2.drawString("I: cerrar inventario", x + 30, y+700);

        // Lista de ítems
        ArrayList<SuperObjetos> inventario = panel.jugador1.inventario;
        for (int i = 0; i < inventario.size(); i++) {
            SuperObjetos obj = inventario.get(i);
            String texto = "- " + obj.nombre;
            if (obj.stackeable && obj.cantidad > 1) {
                texto += " x" + obj.cantidad;
            }
            if (i == panel.jugador1.indiceItemSeleccionado) {
                int textoY = y + 60 + i * 25;
                g2.setColor(Color.yellow);
                g2.fillRect(x + 20, textoY - 18, 300, 22); // fondo resaltado
                g2.setColor(Color.black);
            } else {
                g2.setColor(Color.white);
            }
            g2.drawString(texto, x + 30, y + 60 + i * 25);
        }
    }

    public void alternar() {
        activo = !activo;
    }
}
