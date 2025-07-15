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
    }
    public void getPlayerImage(){ //Obtener la ruta de las imagenes
        try{
            left1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left8.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right8.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if (inputH.upPressed) {
            direction = "arriba";
            y -= speed;
        } else if (inputH.downPressed) {
            direction = "abajo";
            y += speed;
        } else if (inputH.leftPressed) {
            direction = "izquierda";
            x -= speed;
        } else if (inputH.rightPressed) {
            direction = "derecha";
            x += speed;
        }

        // Animación solo si se está moviendo
        if (inputH.upPressed || inputH.downPressed || inputH.leftPressed || inputH.rightPressed) {
            spriteCounter++;

            if (spriteCounter > 10) { // Cambia cada 10 updates (ajustable)
                spriteNum++;
                if (spriteNum > 8) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage imagen = null;  //Cambiar el sprite del personaje basado en la direccion
        switch (direction) {
            case "arriba":
                if (spriteNum == 1) {
                    imagen = left1;
                } else if (spriteNum == 2) {
                    imagen = left2;
                } else if (spriteNum == 3) {
                    imagen = left3;
                } else if (spriteNum == 4) {
                    imagen = left4;
                } else if (spriteNum == 5) {
                    imagen = left5;
                } else if (spriteNum == 6) {
                    imagen = left6;
                } else if (spriteNum == 7) {
                    imagen = left7;
                } else if (spriteNum == 8) {
                    imagen = left8;
                }
                break;
            case "abajo":
                if (spriteNum == 1) {
                    imagen = right1;
                } else if (spriteNum == 2) {
                    imagen = right2;
                } else if (spriteNum == 3) {
                    imagen = right3;
                } else if (spriteNum == 4) {
                    imagen = right4;
                } else if (spriteNum == 5) {
                    imagen = right5;
                } else if (spriteNum == 6) {
                    imagen = right6;
                } else if (spriteNum == 7) {
                    imagen = right7;
                } else if (spriteNum == 8) {
                    imagen = right8;
                }
                break;
            case "izquierda":
                if (spriteNum == 1) {
                    imagen = left1;
                } else if (spriteNum == 2) {
                    imagen = left2;
                } else if (spriteNum == 3) {
                    imagen = left3;
                } else if (spriteNum == 4) {
                    imagen = left4;
                } else if (spriteNum == 5) {
                    imagen = left5;
                } else if (spriteNum == 6) {
                    imagen = left6;
                } else if (spriteNum == 7) {
                    imagen = left7;
                } else if (spriteNum == 8) {
                    imagen = left8;
                }
                break;
            case "derecha":
                if (spriteNum == 1) {
                    imagen = right1;
                } else if (spriteNum == 2) {
                    imagen = right2;
                } else if (spriteNum == 3) {
                    imagen = right3;
                } else if (spriteNum == 4) {
                    imagen = right4;
                } else if (spriteNum == 5) {
                    imagen = right5;
                } else if (spriteNum == 6) {
                    imagen = right6;
                } else if (spriteNum == 7) {
                    imagen = right7;
                } else if (spriteNum == 8) {
                    imagen = right8;
                }
                break;
        }


        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null); //Observador de imagenes


    }

}
