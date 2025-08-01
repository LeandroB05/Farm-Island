package Tiendas;

import Main.PanelJuego;
import Objetos.Semillas;
import java.awt.*;
import java.util.ArrayList;

public class TiendaComprar {
    PanelJuego panel;
    public ArrayList<Semillas> semillasDisponibles;
    public int seleccion = 0;
    public boolean activa = false;
    public boolean fondosInsuficientes = false;

    public TiendaComprar(PanelJuego panel) {
        this.panel = panel;
        this.semillasDisponibles = new ArrayList<>();
    }

    public void inicializarTienda() {
        semillasDisponibles.clear();

        // Asegurarse que las semillas están cargadas
        if (panel.semillas != null) {
            for (int i = 0; i < panel.semillas.length; i++) {
                if (panel.semillas[i] != null) {
                    // Crear una copia para la tienda
                    Semillas semillaOriginal = panel.semillas[i];
                    Semillas copia = new Semillas(
                            semillaOriginal.nombre,
                            semillaOriginal.getPrecio(),
                            semillaOriginal.getDiasParaCrecer(),
                            semillaOriginal.getProducto().copiar(),
                            semillaOriginal.getImagenesEtapas(),
                            semillaOriginal.getDescripcion()
                    );
                    copia.imagen = semillaOriginal.imagen;
                    copia.stackeable = true;
                    semillasDisponibles.add(copia);
                }
            }
        }

        if (semillasDisponibles.isEmpty()) {
            System.out.println("Advertencia: No hay semillas disponibles en la tienda");
        }
    }
    public void dibujar(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;

        // Dibujar interfaz de tienda
        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 40));
        g2.drawString("Tienda de Semillas", x + 20, y -150);

        // Lista de semillas
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));
        for (int i = 0; i < semillasDisponibles.size(); i++) {
            Semillas semilla = semillasDisponibles.get(i);
            int textoY = y + 60 + i * panel.tileSize;

            // Resaltar selección
            if (i == seleccion) {
                g2.setColor(Color.yellow);
                g2.drawRect(x + 10, textoY - 20, panel.screenWidth - (panel.tileSize * 20) - 40, panel.tileSize);
            }

            g2.setColor(Color.white);
            g2.drawString(semilla.getNombre() + " - $" + semilla.getPrecio(), x + 20, textoY);
        }

        // Información de dinero
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
        g2.drawString("Dinero actual: " + panel.jugador1.dinero, x + 800, y - 180);

        // Mensaje de fondos insuficientes
        if (fondosInsuficientes) {
            g2.setColor(Color.red);
            g2.drawString("Fondos insuficientes", x + 800, y - 150);
        }
    }

    public void dibujarInformacion(Graphics2D g2) {
        if (!activa || seleccion < 0 || seleccion >= semillasDisponibles.size()) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7 + 40;
        Semillas semilla = semillasDisponibles.get(seleccion);

        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));

        // Mostrar información dinámica basada en la semilla seleccionada
        g2.drawString(semilla.getNombre().replace("Semilla de ", ""), x + 650, y + 40);
        g2.drawString("Tiempo crecimiento: " + semilla.getDiasParaCrecer() + " días", x + 650, y + 80);
        g2.drawString("Valor cosecha: $" + semilla.getPrecio()*2, x + 650, y + 120);
        g2.drawString(semilla.getDescripcion(), x + 650, y + 160);
    }

    public void moverSeleccion(int direccion) {
        seleccion += direccion;
        if (seleccion < 0) seleccion = semillasDisponibles.size() - 1;
        if (seleccion >= semillasDisponibles.size()) seleccion = 0;
        fondosInsuficientes = false; // Resetear mensaje al moverse
    }

    public void comprarSeleccionada() {
        if (seleccion < 0 || seleccion >= semillasDisponibles.size()) return;

        Semillas semilla = semillasDisponibles.get(seleccion);
        if (panel.jugador1.dinero >= semilla.getPrecio()) {
            panel.jugador1.dinero -= semilla.getPrecio();

            // Crear copia para el inventario
            Semillas copia = semilla.copiar();
            copia.stackeable = true;
            copia.cantidad = 1;

            panel.jugador1.agregarItem(copia);
            fondosInsuficientes = false;
        } else {
            fondosInsuficientes = true;
        }
    }

    public void activar() {
        activa = true;
        seleccion = 0;
        fondosInsuficientes = false;
    }

    public void desactivar() {
        activa = false;
    }
}