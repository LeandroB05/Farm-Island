package Main;

public class Interacciones {
    //Panel
    PanelJuego panel;
    public boolean interaccionActiva = false;

    public Interacciones(PanelJuego panel) {
        this.panel = panel;
    }
    //Interaccion con npcs

    public void interaccionObjeto(int indice){
    }

    public void interaccionNPC(int indice) {
        if (indice != 999 && !panel.mostrarDialogo) {//Solo se muestra si se cumplen los dos
            switch (indice) {//El case se llena dependiendo el npc
                case 0://NPC Comerciante (Vende semillas)
                    panel.tiendaComprar.activar();
                    panel.mostrarDialogo = true;
                    panel.mostrarTiendaComprar = true;
                    panel.repaint();
                    break;
                    case 1://NPC Comerciante (Compra plantas)
                        panel.tiendaVender.activar();
                        panel.mostrarDialogo = true;
                        panel.mostrarTiendaVender = true;
                        panel.repaint();
                        break;
            }
        }
    }

}

