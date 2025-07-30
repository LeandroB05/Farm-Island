package Tiendas;

import Main.PanelJuego;
import java.awt.*;

public class TiendaVender {
    PanelJuego panel;
    int seleccion = 0;
    public boolean activa = false;
    public boolean sinObjetosParaVender = false;

    public TiendaVender(PanelJuego panel) {
        this.panel = panel;
    }

    public void dibujar(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;

        // Título
        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 40));
        g2.drawString("Vender Objetos", x + 20, y - 150);

        if (panel.jugador1.inventario.isEmpty()) {
            g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
            g2.drawString("No tienes objetos para vender.", x + 50, y - 30);
            sinObjetosParaVender = true;
            return;
        }

        sinObjetosParaVender = false;
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));

        for (int i = 0; i < panel.jugador1.inventario.size(); i++) {
            var item = panel.jugador1.inventario.get(i);
            int textoY = y + 60 + i * panel.tileSize;

            // Contorno de selección
            if (i == seleccion) {
                g2.setColor(Color.yellow);
                g2.drawRect(x + 10, textoY - 20, 400, panel.tileSize);
            }

            g2.setColor(Color.white);
            int precio = obtenerPrecioVenta(item.nombre);
            g2.drawString(item.nombre + " x" + item.cantidad + " - $" + precio + " c/u", x + 20, textoY);
        }

        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
        g2.drawString("Dinero actual: " + panel.jugador1.dinero, x + 800, y - 180);
    }

    public void moverSeleccion(int direccion) {
        if (panel.jugador1.inventario.isEmpty()) return;

        seleccion += direccion;
        if (seleccion < 0) seleccion = panel.jugador1.inventario.size() - 1;
        if (seleccion >= panel.jugador1.inventario.size()) seleccion = 0;
    }

    public void venderSeleccionada() {
        if (panel.jugador1.inventario.isEmpty()) return;

        var item = panel.jugador1.inventario.get(seleccion);
        int precioVenta = obtenerPrecioVenta(item.nombre);

        // Vender solo 1 unidad
        panel.jugador1.dinero += precioVenta;
        item.cantidad--;

        if (item.cantidad <= 0) {
            panel.jugador1.inventario.remove(seleccion);
        }

        if (seleccion >= panel.jugador1.inventario.size()) {
            seleccion = panel.jugador1.inventario.size() - 1;
        }
    }

    private int obtenerPrecioVenta(String nombreObjeto) {
        if (nombreObjeto == null) return 0; // o algún valor por defecto

        switch (nombreObjeto.toLowerCase()) {
            case "semilla de zanahoria": return 5;
            case "semilla de rabano": return 7;
            case "semilla de papa": return 10;
            case "semilla de calabaza": return 13;
            case "semilla de coliflor": return 15;
            default: return 5;
        }
    }


    public void activar() {
        activa = true;
        seleccion = 0;
    }

    public void desactivar() {
        activa = false;
    }
}
