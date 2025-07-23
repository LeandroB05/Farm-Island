package Entidades;

import Main.CollisionChecker;
import Main.PanelJuego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entidad {

    PanelJuego panel;
    public int worldX, worldY; //ejes para el movimiento
    public int speed;  //velocidad
    boolean idle; //Comprobar si esta quieto o no
    String lastHorizontalDirection = "derecha";  // Comprobar ultimo sentido en que se direcciono el personaje

    public BufferedImage left1,left2,left3,left4, right1,right2,right3,right4,idleR1, idleR2, idleR3, idleR4, idleL1, idleL2, idleL3, idleL4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea= new Rectangle(0,0,48,48);  //hitbox del personaje
    public boolean collisionOn = false;

    public void dibujar(Graphics g2, PanelJuego panel){
        BufferedImage imagen = null;
        int screenX = worldX - panel.jugador1.worldX + panel.jugador1.screenX;
        int screenY = worldY - panel.jugador1.worldY + panel.jugador1.screenY;
        //Animacion
        spriteCounter++;
        int velocidadAnimacion = idle ? 20 : 10;
        if (spriteCounter > velocidadAnimacion) {
            spriteNum++;
            if (spriteNum > 4) spriteNum = 1;
            spriteCounter = 0;
        }

        if (idle) {
            if (spriteNum == 1) imagen = idleL1;
            else if (spriteNum == 2) imagen = idleL2;
            else if (spriteNum == 3) imagen = idleL3;
            else if (spriteNum == 4) imagen = idleL4;

        }

        g2.drawImage(imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);
    }

    public Entidad(PanelJuego panel) {
        this.panel=panel;
    }
}
