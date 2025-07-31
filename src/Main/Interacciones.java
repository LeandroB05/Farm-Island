package Main;

import java.awt.*;

public class Interacciones {
    //Panel
    PanelJuego panel;
    public boolean interaccionActiva = false;
    public int accion = -1;

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
            switch (indice) {//El case se llena dependiendo el npc
                case 0://NPC Comerciante (Vende semillas)
                    panel.inventario.activo = false;
                    panel.tiendaVender.desactivar();
                    panel.mostrarTiendaVender = false;
                    panel.tiendaComprar.activar();
                    panel.mostrarDialogo = true;
                    panel.mostrarTiendaComprar = true;

                    break;
                    case 1://NPC Comerciante (Compra plantas)
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
    public void interaccionParcela(int indice){
        if (indice != 999 && !panel.mostrarDialogo) {
            panel.tiendaComprar.desactivar();
            panel.tiendaVender.desactivar();
            panel.mostrarTiendaVender = false;
            panel.mostrarTiendaComprar = false;
            panel.mostrarAccionParcela = true;
            interaccionActiva = true;
            switch (indice) {
                case 0:
                    panel.accionesParcelas.Acciones(accion);
                    break;
                    case 1:
                        panel.accionesParcelas.Acciones(accion);
                        break;
                        case 2:
                            panel.accionesParcelas.Acciones(accion);
                            break;
                            case 3:
                                panel.accionesParcelas.Acciones(accion);
                                break;
                                case 4:
                                    panel.accionesParcelas.Acciones(accion);
                                    break;
                                    case 5:
                                        panel.accionesParcelas.Acciones(accion);
                                        break;
                                        case 6:
                                            panel.accionesParcelas.Acciones(accion);
                                            break;
                                            case 7:
                                                panel.accionesParcelas.Acciones(accion);
                                                break;
                                                case 8:
                                                    panel.accionesParcelas.Acciones(accion);
                                                    break;
                                                    case 9:
                                                        panel.accionesParcelas.Acciones(accion);
                                                        break;
            }
        }
    }

}

