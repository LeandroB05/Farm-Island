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

            // Dibujar selección
            if (i == seleccion) {
                g2.setColor(Color.yellow);
                g2.drawRect(x + 10, textoY - 20, 400, panel.tileSize);
            }

            // Dibujar nombre y cantidad
            g2.setColor(Color.white);
            g2.drawString(item.nombre + " x" + item.cantidad, x + 20, textoY);
        }

        // Mostrar precio de venta del ítem seleccionado
        var seleccionado = panel.jugador1.inventario.get(seleccion);
        int precioVenta;
        String nombre = seleccionado.nombre.toUpperCase();
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 33));
        if (nombre.startsWith("SEMILLA")) {
            precioVenta = 10;
            g2.drawString("Precio de venta: " + precioVenta, panel.tileSize * 2 + 620, y + 60);
        } else {
            precioVenta = seleccionado.precio;
            g2.drawString("Precio de venta: " + precioVenta, panel.tileSize * 2 + 620, y + 60);
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
        int precioVenta = item.precio / 2;

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

    public void activar() {
        activa = true;
        seleccion = 0;
    }

    public void desactivar() {
        activa = false;
    }
}
