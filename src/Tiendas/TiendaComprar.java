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
    public boolean fondosInsuficientes = false;

    public TiendaComprar(PanelJuego panel) {
        this.panel = panel;
        semillas = new ArrayList<>();
        //Lista de semillas
        semillas.add(new Semillas("Zanahoria", 10));
        semillas.add(new Semillas("Rabano", 15));
        semillas.add(new Semillas("Papa", 20));
        semillas.add(new Semillas("Calabaza", 25));
        semillas.add(new Semillas("Coliflor", 30));
    }

    public void dibujar(Graphics2D g2) {
        if (!activa) return;

        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;

        //Dibuja la letra de la tienda (nombre)
        g2.setColor(new Color(0, 0, 0, 200));
        g2.setColor(Color.white);
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        g2.drawString("Tienda de Semillas", x + 20, y -150);

        //Lista de semillas
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        for (int i = 0; i < semillas.size(); i++) {
            Semillas semilla = semillas.get(i);
            int textoY = y + 60 + i * panel.tileSize;
            //Contorno de seleccion
            if (i == seleccion) {
                g2.setColor(Color.yellow);
                g2.drawRect(x + 10, textoY - 20, panel.screenWidth - (panel.tileSize * 20)-40, panel.tileSize);
            }
            g2.setColor(Color.white);
            g2.drawString(semilla.getNombre() + " - $" + semilla.getPrecio(), x + 20, textoY);
        }
        //Dinero del jugador
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        g2.setColor(Color.white);
        g2.drawString("Dinero actual: "+panel.jugador1.dinero, x + 800, y-180);
        if (fondosInsuficientes) {
            g2.drawString("Fondos insuficientes", panel.tileSize * 2 + 800, panel.tileSize * 7-150);
        }

    }
    //Seleccion de la tienda
    public void moverSeleccion(int direccion) {
        seleccion += direccion;
        if (seleccion < 0) seleccion = semillas.size() - 1;
        if (seleccion >= semillas.size()) seleccion = 0;
    }

    //Imprimir informacion al seleccionar
    public void dibujarInformacion(Graphics2D g2) {
        if (!activa) return;
        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7+40;
        g2.setColor(Color.white);
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        switch (seleccion) {
            case 0:
                //Incrementos de 40 en Y para que este uno abajo del otro
                g2.drawString("Zanahoria", x + 650, y+40);
                g2.drawString("Tiempo de crecimiento: 2 Dias", x + 650, y+80);
                g2.drawString("Valor de venta: 20$", x + 650, y+120);
                g2.drawString("Es un vegetal diurético que evita la ", x + 650, y+160);
                g2.drawString("retención de líquidos. No puede", x + 650, y+200);
                g2.drawString("faltar en verano, ya que facilita el", x + 650, y+240);
                g2.drawString(" bronceado de manera saludable", x + 650, y+280);
                g2.drawString(" y totalmente natural.", x + 650, y+320);
                break;
                case 1:
                    //Incrementos de 40 en Y para que este uno abajo del otro
                    g2.drawString("Rabano", x + 650, y+40);
                    g2.drawString("Tiempo de crecimiento: 2 Dias", x + 650, y+80);
                    g2.drawString("Valor de venta: 30$", x + 650, y+120);
                    g2.drawString("El rábano blanco apoya la función renal ", x + 650, y+160);
                    g2.drawString(" con sus propiedades diuréticas y ayuda", x + 650, y+200);
                    g2.drawString(" a eliminar las toxinas del cuerpo.", x + 650, y+240);
                    break;
                    case 2:
                        g2.drawString("Papa", x + 650, y+40);
                        g2.drawString("Tiempo de crecimiento: 4 Dias", x + 650, y+80);
                        g2.drawString("Valor de venta: 40$", x + 650, y+120);
                        g2.drawString("La papa es una buena fuente de calorías y ", x + 650, y+160);
                        g2.drawString("también tiene algunos micronutrientes, así ", x + 650, y+200);
                        g2.drawString("como un gran contenido de proteínas en ", x + 650, y+240);
                        g2.drawString("comparación con otras raíces y tubérculos.", x + 650, y+280);
                        break;
                        case 3:

                            g2.drawString("Calabaza", x + 650, y+40);
                            g2.drawString("Tiempo de crecimiento: 5 dias", x + 650, y+80);
                            g2.drawString("Valor de venta: 55$", x + 650, y+120);
                            g2.drawString("La calabaza es baja en calorías ", x + 650, y+160);
                            g2.drawString("y rica en beta‑caroteno y fibra,", x + 650, y+200);
                            g2.drawString("con contenido moderado de vitamina C.", x + 650, y+240);
                        break;
                            case 4:
                                g2.drawString("Coliflor", x + 650, y+40);
                                g2.drawString("Tiempo de crecimiento: 6 dias", x + 650, y+80);
                                g2.drawString("Valor de venta: 70$", x + 650, y+120);
                                g2.drawString("La coliflor aporta solo 25kcal por cada 100g,", x + 650, y+160);
                                g2.drawString("es alta en vitamina C con vitaminas B, K", x + 650, y+200);
                                g2.drawString("y potasio.", x + 650, y+240);
                            break;



        }
    }

    public void comprarSeleccionada() {
        Semillas s = semillas.get(seleccion);
        if (panel.jugador1.dinero - s.getPrecio()>=0) {
            panel.jugador1.dinero -= s.getPrecio();
            fondosInsuficientes=false;
        }else if ((panel.jugador1.dinero - s.getPrecio())<0) {
            fondosInsuficientes=true;
        }
        //AGREGAR FUNCIONES DE INVENTARIO AQUI (la clase todavia no esta hecha)
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

