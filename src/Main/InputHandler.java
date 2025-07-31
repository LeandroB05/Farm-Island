package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {  //implements para que pueda leer la accion del jugador
    PanelJuego panel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, pPressed; //booleano para comprobar que se aprieta la tecla
    public InputHandler(PanelJuego panel) {
        this.panel = panel;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //retorna el valor de la tecla presionada

        if (!panel.pausado && !panel.mostrarDialogo && !panel.mostrarDormir&&!(panel.interacciones.accion==0)) {
            if (key == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (key == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (key == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (key == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (key == KeyEvent.VK_P) {
                pPressed = true;
                panel.sonido.stop();
                panel.pausado = true;
            }
            if (key == KeyEvent.VK_E) {
                ePressed = true;
            }
            if (key == KeyEvent.VK_I) {
                panel.inventario.alternar();
            }

        } else if (panel.pausado) {
            if (key == KeyEvent.VK_P) {
                pPressed = true;
                panel.sonido.play();
                panel.pausado = false;
            }

        }
        if (panel.tiendaComprar.activa || panel.tiendaVender.activa || panel.mostrarAccionParcela) {
            if (panel.mostrarTiendaComprar) {
                if (key == KeyEvent.VK_UP) panel.tiendaComprar.moverSeleccion(-1);
                if (key == KeyEvent.VK_DOWN) panel.tiendaComprar.moverSeleccion(1);
                if (key == KeyEvent.VK_ENTER) panel.tiendaComprar.comprarSeleccionada();
            }
            if (panel.mostrarTiendaVender) {
                if (key == KeyEvent.VK_UP) panel.tiendaVender.moverSeleccion(-1);
                if (key == KeyEvent.VK_DOWN) panel.tiendaVender.moverSeleccion(1);
                if (key == KeyEvent.VK_ENTER) panel.tiendaVender.venderSeleccionada();
            }
            if (panel.mostrarAccionParcela&&panel.interacciones.accion!=0) {
                if (key == KeyEvent.VK_UP) panel.ventanas.moverSeleccionAccion(-1);
                if (key == KeyEvent.VK_DOWN) panel.ventanas.moverSeleccionAccion(1);
                if (key == KeyEvent.VK_ENTER) {
                    panel.interacciones.accion = panel.ventanas.seleccionAccion;
                }
            }else{
                if (key == KeyEvent.VK_UP) panel.inventario.moverSeleccionInventario(-1);
                if (key == KeyEvent.VK_DOWN) panel.inventario.moverSeleccionInventario(1);
                if (key == KeyEvent.VK_ESCAPE) {
                    panel.interacciones.accion=-1;
                }
            }
                if (key == KeyEvent.VK_ESCAPE) {
                    panel.mostrarDialogo = false;
                    panel.tiendaComprar.desactivar();
                    panel.tiendaVender.desactivar();
                    panel.mostrarAccionParcela = false;
                    panel.pausado = false;
                    panel.interacciones.interaccionActiva = false;
                }
            }
            if (panel.mostrarDormir) {
                if (key == KeyEvent.VK_ESCAPE) {
                    panel.mostrarDormir = false;
                } else if (key == KeyEvent.VK_ENTER) {
                    panel.tiempo.pasarDia();
                    panel.mostrarDormir = false;
                }
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_P) {
            pPressed = false;
        }
        if (key == KeyEvent.VK_E) {
            ePressed = false;
        }
    }
}

