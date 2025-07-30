package Objetos;
import Main.PanelJuego;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//Va ser la clase padre de todos los objetos
public class SuperObjetos{
    public BufferedImage imagen;
    public String nombre;
    public boolean colision = false;//Cambiar a true en la misma clase especifica si quieres colisiones
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);//Valor defualt del area de un tile completo}
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;

    public boolean stackeable = false;  // Por defecto no se agrupan
    public int cantidad = 1;            // Cantidad por defecto es 1


    public void dibujar(Graphics2D g2, PanelJuego panel){
        int screenX = worldX - panel.jugador1.worldX + panel.jugador1.screenX;
        int screenY = worldY - panel.jugador1.worldY + panel.jugador1.screenY;
        g2.drawImage(imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);
    }


}
