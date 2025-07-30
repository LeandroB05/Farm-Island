package Tiendas;

import Entidades.Jugador;
import Main.PanelJuego;
import Objetos.Semillas;

import java.awt.*;
import java.util.ArrayList;

public class TiendaComprar {
    PanelJuego panel;
    ArrayList<Semillas> semillas;
    int seleccion = 0;
    public boolean activa = false;

    public TiendaComprar(PanelJuego panel) {
        this.panel = panel;
        semillas = new ArrayList<>();
        semillas.add(new Semillas("Zanahoria", 10));
        semillas.add(new Semillas("Papa", 15));
        semillas.add(new Semillas("Rabano", 20));
    }

    public void dibujar(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 6;
        int w = panel.screenWidth - panel.tileSize * 4;
        int h = panel.tileSize * semillas.size() + 40;

        // Fondo
        g2.setColor(new Color(0, 0, 0, 200));
        //g2.fillRoundRect(x, y, w, h, 30, 30);
        //g2.drawRoundRect(x+5, y+5, w-10, h-10, 40, 40);
        g2.setColor(Color.white);
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        g2.drawString("Tienda de Semillas", x + 20, y -150);

        // Lista de semillas
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        for (int i = 0; i < semillas.size(); i++) {
            Semillas semilla = semillas.get(i);
            int textoY = y + 60 + i * panel.tileSize;

            if (i == seleccion) {
                g2.setColor(Color.yellow);
                g2.drawRect(x + 10, textoY - 20, w - 20, panel.tileSize);
            }

            g2.setColor(Color.white);
            g2.drawString(semilla.getNombre() + " - $" + semilla.getPrecio(), x + 20, textoY);
        }
    }
    //Seleccion de la tienda
    public void moverSeleccion(int direccion) {
        seleccion += direccion;
        if (seleccion < 0) seleccion = semillas.size() - 1;
        if (seleccion >= semillas.size()) seleccion = 0;
    }

    public void comprarSeleccionada(Jugador jugador) {
        Semillas s = semillas.get(seleccion);
        if (jugador.dinero >= panel.jugador1.dinero) {
            jugador.dinero -= s.getPrecio();
            //AGREGAR FUNCIONES DE INVENTARIO AQUI (la clase todavia no esta hecha)
        }
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

