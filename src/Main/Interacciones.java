package Main;

import java.awt.*;

public class Interacciones {
    //Panel
    PanelJuego panel;
    public boolean interaccionActiva = false;

    public Interacciones(PanelJuego panel) {
        this.panel = panel;
    }
    //Interaccion con npcs
    public void interaccionNPC(int indice) {
        if (indice != 999 && !panel.mostrarDialogo) {//Solo se muestra si se cumplen los dos
            panel.mostrarDialogo = true;
            System.out.println("Interaccion npc");
            switch (indice) {//El case se llena dependiendo el npc
                case 0:
                    panel.repaint();
                    break;
            }
        }
    }

}

