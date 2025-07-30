package Main;

import java.awt.*;
import java.util.ArrayList;

public class Inventario{
    PanelJuego panel;
    boolean activo = false;

    public Inventario(PanelJuego panel) {
        this.panel = panel;
    }

    public void dibujar(Graphics2D g2) {
        if (!activo) return;

        int x = panel.tileSize;
        int y = panel.tileSize+20;
        int w = panel.screenWidth - panel.tileSize * 25;
        int h = panel.tileSize * 15;

        // Fondo
        g2.setColor(new Color(0, 0, 0));
        g2.fillRoundRect(x, y, w, h, 25, 25);

        g2.setColor(Color.white);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g2.drawString("Inventario", x + 20, y + 30);

        // Lista de ítems
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        ArrayList<String> inventario = panel.jugador1.inventario;
        for (int i = 0; i < inventario.size(); i++) {
            g2.drawString("- " + inventario.get(i), x + 30, y + 60 + i * 25);
        }
    }
    public void alternar() {
        activo = !activo;
    }
}

