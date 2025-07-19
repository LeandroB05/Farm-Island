package Entidades;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entidad {

    public int worldX, worldY; //ejes para el movimiento
    public int speed;  //velocidad
    boolean idle; //Comprobar si esta quieto o no
    String lastHorizontalDirection = "derecha";  // Comprobar ultimo sentido en que se direcciono el personaje

    public BufferedImage left1,left2,left3,left4, right1,right2,right3,right4,idleR1, idleR2, idleR3, idleR4, idleL1, idleL2, idleL3, idleL4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;  //hitbox del personaje
    public boolean collisionOn = false;
}
