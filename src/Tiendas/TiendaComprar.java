package Tiendas;

import Main.PanelJuego;
import Objetos.Semillas;
import Objetos.SuperObjetos;

import java.awt.*;
import java.util.ArrayList;

public class TiendaComprar {
    PanelJuego panel;
    ArrayList<Semillas> semillas;
    int seleccion = 0;
    public boolean activa = false;
    public boolean fondosInsuficientes = false;

    public TiendaComprar(PanelJuego panel) {
        this.panel = panel;
        semillas = new ArrayList<>();

        // Lista de semillas con nombre y precio
        semillas.add(new Semillas("Semilla de Zanahoria", 10));
        semillas.add(new Semillas("Semilla de Rabano", 15));
        semillas.add(new Semillas("Semilla de Papa", 20));
        semillas.add(new Semillas("Semilla de Calabaza", 25));
        semillas.add(new Semillas("Semilla de Coliflor", 30));
    }

    public void dibujar(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;

        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 40));
        g2.drawString("Tienda de Semillas", x + 20, y -150);

        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));
        for (int i = 0; i < semillas.size(); i++) {
            Semillas semilla = semillas.get(i);
            int textoY = y + 60 + i * panel.tileSize;

            if (i == seleccion) {
                g2.setColor(Color.yellow);
                g2.drawRect(x + 10, textoY - 20, panel.screenWidth - (panel.tileSize * 20) - 40, panel.tileSize);
            }

            g2.setColor(Color.white);
            g2.drawString(semilla.getNombre() + " - $" + semilla.getPrecio(), x + 20, textoY);
        }

        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 30));
        g2.drawString("Dinero actual: " + panel.jugador1.dinero, x + 800, y - 180);

        if (fondosInsuficientes) {
            g2.drawString("Fondos insuficientes", panel.tileSize * 2 + 800, panel.tileSize * 7 - 150);
        }

        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));
        g2.drawString("¡Bienvenido a mi tienda!", x + 30, y - 100);
    }

    public void moverSeleccion(int direccion) {
        seleccion += direccion;
        if (seleccion < 0) seleccion = semillas.size() - 1;
        if (seleccion >= semillas.size()) seleccion = 0;
    }

    public void dibujarInformacion(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7 + 40;

        g2.setColor(Color.white);
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 20));

        switch (seleccion) {
            case 0:
                g2.drawString("Zanahoria", x + 650, y + 40);
                g2.drawString("Tiempo de crecimiento: 2 Días", x + 650, y + 80);
                g2.drawString("Valor de venta: 20$", x + 650, y + 120);
                g2.drawString("Vegetal diurético, bronceado natural.", x + 650, y + 160);
                break;
            case 1:
                g2.drawString("Rábano", x + 650, y + 40);
                g2.drawString("Tiempo de crecimiento: 2 Días", x + 650, y + 80);
                g2.drawString("Valor de venta: 30$", x + 650, y + 120);
                g2.drawString("Apoya función renal y elimina toxinas.", x + 650, y + 160);
                break;
            case 2:
                g2.drawString("Papa", x + 650, y + 40);
                g2.drawString("Tiempo de crecimiento: 4 Días", x + 650, y + 80);
                g2.drawString("Valor de venta: 40$", x + 650, y + 120);
                g2.drawString("Buena fuente de calorías y proteínas.", x + 650, y + 160);
                break;
            case 3:
                g2.drawString("Calabaza", x + 650, y + 40);
                g2.drawString("Tiempo de crecimiento: 5 Días", x + 650, y + 80);
                g2.drawString("Valor de venta: 55$", x + 650, y + 120);
                g2.drawString("Baja en calorías, rica en fibra y beta-caroteno.", x + 650, y + 160);
                break;
            case 4:
                g2.drawString("Coliflor", x + 650, y + 40);
                g2.drawString("Tiempo de crecimiento: 6 Días", x + 650, y + 80);
                g2.drawString("Valor de venta: 70$", x + 650, y + 120);
                g2.drawString("Alta en vitamina C, vitaminas B, K y potasio.", x + 650, y + 160);
                break;
        }
    }

    public void comprarSeleccionada() {
        Semillas semillaSeleccionada = semillas.get(seleccion);
        if (panel.jugador1.dinero >= semillaSeleccionada.getPrecio()) {
            panel.jugador1.dinero -= semillaSeleccionada.getPrecio();

            // Crear copia con nombre válido
            Semillas copia = new Semillas(semillaSeleccionada.nombre, semillaSeleccionada.getPrecio());
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
    }

    public void desactivar() {
        activa = false;
    }
}
