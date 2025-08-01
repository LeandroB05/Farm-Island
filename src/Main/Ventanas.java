package Main;

import java.awt.*;
//Parametros de ventas para diferentes usos
public class Ventanas {
    PanelJuego panel;
    public String dialogoActual="";
    public Ventanas(PanelJuego panel) {
        this.panel = panel;
    }

    public void dibujarPantallaDialogo(Graphics2D g2){
        // Tamaño de la ventana
        int x = panel.tileSize * 2;
        int y = panel.tileSize * 2;
        int w = panel.screenWidth - (panel.tileSize * 4);
        int h = panel.tileSize * 5;
        dibujarVentanilla(g2, x, y, w, h);
    }

    public void dibujarVentanilla(Graphics2D g2, int x, int y, int w, int h){
        Color c = new Color(0, 0, 0, 180);
        g2.setColor(c);
        g2.fillRoundRect(x, y, w, h, 40, 40);
        c= new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, w-10, h-10, 40, 40);
    }
    public void dibujarVentanaTienda(Graphics2D g2){
        if (!panel.tiendaComprar.activa&&!panel.tiendaVender.activa) return;
        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;
        int w = panel.screenWidth - (panel.tileSize * 20);
        int h = panel.tileSize * 10;
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        //Ventana izquierda (Seleccion)
        g2.fillRoundRect(x, y, w-10, h, 40, 40);
        //Ventana derecha (informacion)
        g2.fillRoundRect(x+600, y, w-10, h, 40, 40);
        c= new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        //Ventana izquierda (Seleccion)
        g2.drawRoundRect(x, y+5, w-10, h-10, 40, 40);
        //Ventana derecha (informacion)
        g2.drawRoundRect(x+600, y+5, w-10, h-10, 40, 40);

    }
    public void dibujarVentanaTienda2(Graphics2D g2){
        if (!panel.tiendaComprar.activa&&!panel.tiendaVender.activa) return;
        int x = panel.tileSize * 2;
        int y = panel.tileSize * 7;
        int w = panel.screenWidth - (panel.tileSize * 20);
        int h = panel.tileSize * 10;
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        //Ventana izquierda (Seleccion)
        g2.fillRoundRect(x, y, w-10, h, 40, 40);
        //Ventana derecha (informacion)
        g2.fillRoundRect(x+600, y, panel.screenWidth - (panel.tileSize * 25)-10, panel.tileSize * 2-10, 40, 40);
        c= new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        //Ventana izquierda (Seleccion)
        g2.drawRoundRect(x, y+5, w-10, h-10, 40, 40);
        //Ventana derecha (informacion)
        g2.drawRoundRect(x+600, y+5, panel.screenWidth - (panel.tileSize * 25)-10, panel.tileSize * 2-10, 40, 40);

    }
    public void dibujarPausaVentana(Graphics2D g2){
        int x = 572;
        int y = 460;
        int w = panel.screenWidth - (panel.tileSize * 20);
        int h = panel.tileSize * 5;
        //Parametros para la ventana
        Color c = new Color(0, 100, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x-100, y-140, w, h, 40, 40);
        c= new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x-100+5, y-140+5, w-10, h-10, 40, 40);
        //Parametro para el mensaje
        String text="PAUSADO";
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 80));
        g2.drawString(text, 572, 460); //Se puede sacar la posicion con fomulas exoticas pero no me complique
    }

    public void dibujarVentanaDormir(Graphics2D g2){
        int x = panel.tileSize * 4;
        int y = panel.tileSize * 4;
        int w = panel.screenWidth - (panel.tileSize * 8);
        int h = panel.tileSize * 10;
        //Parametros para la ventana
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, w, h, 40, 40);
        c= new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, w-10, h-10, 40, 40);
        //Parametro para el mensaje
        String text="Presione ENTER para pasar el dia";
        g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 40));
        g2.drawString(text, 480, 440);
    }
    public void dibujarVentanaCarta(Graphics2D g2){
        int x = panel.tileSize * 17;
        int y = panel.tileSize * 2;
        int w = panel.screenWidth - (panel.tileSize * 25);
        int h = panel.tileSize * 10;
        Color c = new Color(255, 255, 255, 180);
        g2.setColor(c);
        g2.fillRoundRect(x, y, w, h, 40, 40);
        c= new Color(0, 0, 0);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, w-10, h-10, 40, 40);
        g2.setFont(new Font("Pixelify Sans", Font.BOLD, 30));
        g2.drawString("Controles: ", x + 20, y + 40);
        g2.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
        g2.drawString("W A S D: Mover al jugador", x + 20, y + 70);
        g2.drawString(" ↑  ↓ : elegir en los menús", x + 20, y + 100);
        g2.drawString("I: Abrir/cerrar inventario", x + 20, y + 130);
        g2.drawString("P: Pausar el juego", x + 20, y + 160);
        g2.drawString("ESC: Cerrar ventanas", x + 20, y + 190);
        g2.drawString("     o cancelar acciones.", x + 20, y + 220);
        g2.drawString("ENTER: Confirmar acciones", x + 20, y + 250);
        g2.drawString("(comprar, vender, dormir, etc).", x + 20, y + 280);
        g2.drawString("Q / E: Cambiar objeto", x + 20, y + 310);

    }
}

