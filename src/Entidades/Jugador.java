package Entidades;

import Main.InputHandler;
import Main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Jugador extends Entidad {

    PanelJuego panel;
    InputHandler inputH;

    public Jugador(PanelJuego panel, InputHandler inputH) {
        this.panel = panel;
        this.inputH = inputH;

        setDefaultValues(); //valores base
        getPlayerImage(); //imagenes
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "arriba";
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

        idle = true; // ← reinicia el estado al principio

        if (inputH.upPressed) {   // Recolectar entradas
            dy -= 1;
            idle = false;
        }
        if (inputH.downPressed) {
            dy += 1;
            idle = false;
        }
        if (inputH.leftPressed) {
            dx -= 1;
            lastHorizontalDirection = "izquierda";
            idle = false;
        }
        if (inputH.rightPressed) {
            dx += 1;
            lastHorizontalDirection = "derecha";
            idle = false;
        }

        double length = Math.sqrt(dx * dx + dy * dy);  // Normalizar velocidad en diagonal
        if (length != 0) {
            dx /= length;
            dy /= length;

            x += dx * speed;
            y += dy * speed;

            direction = lastHorizontalDirection;
        }


        spriteCounter++; // Animación: se actualiza SIEMPRE (incluso en idle)

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
            if (direction.equals("derecha")) {
                if (spriteNum == 1) imagen = idleR1;
                else if (spriteNum == 2) imagen = idleR2;
                else if (spriteNum == 3) imagen = idleR3;
                else if (spriteNum == 4) imagen = idleR4;
            } else if (direction.equals("izquierda")) {
                if (spriteNum == 1) imagen = idleL1;
                else if (spriteNum == 2) imagen = idleL2;
                else if (spriteNum == 3) imagen = idleL3;
                else if (spriteNum == 4) imagen = idleL4;
            }
        } else {
            if (direction.equals("derecha")) {
                if (spriteNum == 1) imagen = right1;
                else if (spriteNum == 2) imagen = right2;
                else if (spriteNum == 3) imagen = right3;
                else if (spriteNum == 4) imagen = right4;
            } else if (direction.equals("izquierda")) {
                if (spriteNum == 1) imagen = left1;
                else if (spriteNum == 2) imagen = left2;
                else if (spriteNum == 3) imagen = left3;
                else if (spriteNum == 4) imagen = left4;
            }
        }

        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null);
    }


}
