package Main;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Interacciones {
    //Panel
    PanelJuego panel;
    public boolean interaccionActiva = false;

    public Interacciones(PanelJuego panel) {
        this.panel = panel;
    }
    //Interaccion con npcs

    public void interaccionObjeto(int indice){
        if (indice != 999 && !panel.mostrarDialogo) {
            switch (indice) {
                case 1:
                    System.out.println("Interaccion cama");
                    panel.mostrarDormir = true;
                    break;
            }
        }
    }

    public void interaccionNPC(int indice) {
        if (indice != 999 && !panel.mostrarDialogo) {//Solo se muestra si se cumplen los dos
            System.out.println("Interaccion npc");
            switch (indice) {//El case se llena dependiendo el npc
                case 0://NPC Comerciante (Vender)
                    panel.tiendaComprar.activar();
                    panel.mostrarDialogo = true;
                    panel.mostrarTienda = true;
                    panel.repaint();
                    break;
                    case 1:
                        System.out.println("Interaccion npc2");
                        break;//NPC Comerciante 2 (Comprador)
            }
        }
    }

}

