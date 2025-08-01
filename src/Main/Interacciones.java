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
        if (indice != 999 && !panel.mostrarDialogo) {
            switch (indice) {
                case 0:
                    panel.tiendaVender.activa=false;
                    panel.inventario.activo = false;
                    panel.tiendaVender.desactivar();
                    panel.mostrarTiendaVender = false;
                    panel.mostrarCarta=true;
                    break;
                case 1:
                    panel.mostrarCarta=false;
                    panel.mostrarDormir = true;
                    break;
            }
        }
    }

    public void interaccionNPC(int indice) {
        if (indice != 999 && !panel.mostrarDialogo) {//Solo se muestra si se cumplen los dos
            switch (indice) {//El case se llena dependiendo el npc
                case 0://NPC Comerciante (Vende semillas)
                    panel.mostrarCarta=false;
                    panel.inventario.activo = false;
                    panel.tiendaVender.desactivar();
                    panel.mostrarTiendaVender = false;
                    panel.tiendaComprar.activar();
                    panel.mostrarDialogo = true;
                    panel.mostrarTiendaComprar = true;

                    break;
                    case 1://NPC Comerciante (Compra plantas)
                        panel.mostrarCarta=false;
                        panel.inventario.activo = false;
                        panel.tiendaComprar.desactivar();
                        panel.mostrarTiendaComprar = false;
                        panel.tiendaVender.activar();
                        panel.mostrarDialogo = true;
                        panel.mostrarTiendaVender = true;
                        break;
            }
        }
    }

}

