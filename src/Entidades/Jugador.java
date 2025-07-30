package Entidades;

import Main.InputHandler;
import Main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

public class Jugador extends Entidad {

    InputHandler inputH;
    public int dinero = 1000;

    public final int screenX;
    public final int screenY;

    public Jugador(PanelJuego panel, InputHandler inputH) {
        super(panel);
        this.inputH = inputH;

        screenX = panel.screenWidth/2 - (panel.tileSize/2);
        screenY = panel.screenHeight/2- (panel.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 27;
        solidArea.height = 24;

        setDefaultValues(); //valores base
        getPlayerImage(); //imagenes
    }



    public void setDefaultValues(){
        worldX = panel.tileSize * 20;
        worldY = panel.tileSize * 25;
        speed = 7;
        direction = "derecha";
        idle = !(inputH.upPressed || inputH.downPressed || inputH.leftPressed || inputH.rightPressed);
    }
    public void getPlayerImage(){ //Obtener la ruta de las imagenes
        try{
            left1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left4.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right4.png"));

            idleR1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle1.png"));
            idleR2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle2.png"));
            idleR3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle3.png"));
            idleR4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle4.png"));

            idleL1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle1.png"));
            idleL2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle2.png"));
            idleL3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle3.png"));
            idleL4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle4.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void update() {
        double dx = 0;
        double dy = 0;

        idle = true; // reiniciar estado

        if (inputH.upPressed) {
            dy -= 1;
            direction = "arriba";
            idle = false;
        }
        if (inputH.downPressed) {
            dy += 1;
            direction = "abajo";
            idle = false;
        }
        if (inputH.leftPressed) {
            dx -= 1;
            lastHorizontalDirection = "izquierda";
            direction = "izquierda";
            idle = false;
        }
        if (inputH.rightPressed) {
            dx += 1;
            lastHorizontalDirection = "derecha";
            direction = "derecha";
            idle = false;
        }

        //normalizar para no ir más rápido en diagonal
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx = (dx / length) * speed;
            dy = (dy / length) * speed;
        }

        if (dx != 0) {
            direction = dx < 0 ? "izquierda" : "derecha"; //actualizar direction
            collisionOn = false;
            panel.hitbox.checkCollision(this);
            if (!collisionOn) {  //moverse solo si no hay colision
                worldX += dx;
            }
        }

        if (dy != 0) {
            direction = dy < 0 ? "arriba" : "abajo";
            collisionOn = false;
            panel.hitbox.checkCollision(this);
            if (!collisionOn) { //moverse solo si no hay colision
                worldY += dy;
            }
        }

        //Verifica si esta tocando algo
        //Objeto
        int indiceObjeto = panel.hitbox.verificarObjeto(this, true);
        //NPC
        int indiceNPC = panel.hitbox.verificarEntidad(this, panel.npc);
        if(indiceNPC!=999){
            panel.tiendaComprar.activar();
        }

        if (indiceNPC!=999){
            if (collisionOn) {  //No puede moverse si entra en contacto con un npc
                worldX -= dx;
                worldY -= dy;
            }
            panel.interacciones.interaccionNPC(indiceNPC);
        }

        // Animación
        spriteCounter++;
        int velocidadAnimacion = idle ? 20 : 10;
        if (spriteCounter > velocidadAnimacion) {
            spriteNum++;
            if (spriteNum > 4) spriteNum = 1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage imagen = null;

        if (idle) {
            if (lastHorizontalDirection.equals("derecha")) {
                if (spriteNum == 1) imagen = idleR1;
                else if (spriteNum == 2) imagen = idleR2;
                else if (spriteNum == 3) imagen = idleR3;
                else if (spriteNum == 4) imagen = idleR4;
            } else if (lastHorizontalDirection.equals("izquierda")) {
                if (spriteNum == 1) imagen = idleL1;
                else if (spriteNum == 2) imagen = idleL2;
                else if (spriteNum == 3) imagen = idleL3;
                else if (spriteNum == 4) imagen = idleL4;
            }
        } else {
            if (direction.equals("derecha") || (direction.equals("abajo") && lastHorizontalDirection.equals("derecha")) || (direction.equals("arriba") && lastHorizontalDirection.equals("derecha"))) {
                if (spriteNum == 1) imagen = right1;
                else if (spriteNum == 2) imagen = right2;
                else if (spriteNum == 3) imagen = right3;
                else if (spriteNum == 4) imagen = right4;
            } else if (direction.equals("izquierda") || (direction.equals("abajo") && lastHorizontalDirection.equals("izquierda")) || (direction.equals("arriba") && lastHorizontalDirection.equals("izquierda"))) {
                if (spriteNum == 1) imagen = left1;
                else if (spriteNum == 2) imagen = left2;
                else if (spriteNum == 3) imagen = left3;
                else if (spriteNum == 4) imagen = left4;
            }
        }

        g2.drawImage(imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);
    }



}
