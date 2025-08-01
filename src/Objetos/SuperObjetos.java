package Objetos;
import Main.PanelJuego;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjetos {
    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public boolean stackeable = false;
    public int cantidad = 1;
    public int precio;  // Nuevo campo para el precio

    // Constructor vacío
    public SuperObjetos() {
    }

    // Constructor con nombre y precio
    public SuperObjetos(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Constructor completo
    public SuperObjetos(String nombre, int precio, BufferedImage imagen, boolean stackeable) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.stackeable = stackeable;
    }

    public void dibujar(Graphics2D g2, PanelJuego panel) {
        int screenX = worldX - panel.jugador1.worldX + panel.jugador1.screenX;
        int screenY = worldY - panel.jugador1.worldY + panel.jugador1.screenY;

        // Solo dibujar si está dentro de la pantalla
        if(worldX + panel.tileSize > panel.jugador1.worldX - panel.jugador1.screenX &&
                worldX - panel.tileSize < panel.jugador1.worldX + panel.jugador1.screenX &&
                worldY + panel.tileSize > panel.jugador1.worldY - panel.jugador1.screenY &&
                worldY - panel.tileSize < panel.jugador1.worldY + panel.jugador1.screenY) {
            g2.drawImage(imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);
        }
    }

    // Método para crear copias (útil para semillas y productos)
    public SuperObjetos copiar() {
        SuperObjetos copia = new SuperObjetos();
        copia.nombre = this.nombre;
        copia.imagen = this.imagen;
        copia.colision = this.colision;
        copia.stackeable = this.stackeable;
        copia.cantidad = this.cantidad;
        copia.precio = this.precio;
        return copia;
    }
}