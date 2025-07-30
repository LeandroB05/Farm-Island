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

        if (!panel.pausado && !panel.mostrarDialogo) {
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
        } else if (panel.pausado) {
            if (key == KeyEvent.VK_P) {
                pPressed = true;
                panel.sonido.play();
                panel.pausado = false;
            }
        } if (panel.mostrarDialogo) {
            if (key == KeyEvent.VK_ENTER) {
                panel.mostrarDialogo = false;
                panel.mostrarTienda = false;
                panel.interacciones.interaccionActiva=false;
                panel.tiendaComprar.activa=false;
            }
        }
        if (panel.tiendaComprar.activa) {
            if (key == KeyEvent.VK_UP) panel.tiendaComprar.moverSeleccion(-1);
            if (key == KeyEvent.VK_DOWN) panel.tiendaComprar.moverSeleccion(1);
            if (key == KeyEvent.VK_ENTER) panel.tiendaComprar.comprarSeleccionada(panel.jugador1);
            if (key == KeyEvent.VK_ESCAPE) panel.tiendaComprar.desactivar();
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

