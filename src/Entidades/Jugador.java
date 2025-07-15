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
            left = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left1.png"));
            right = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {  //En Java el(0,0) esta arriba a la derecha, la X aumenta hacia la derecha y la Y aumenta hacia abajo

        if(inputH.upPressed == true) {
            direction = "arriba";
            y -= speed;                               //cambiar de posicion en cada direccion
        } else if(inputH.downPressed == true) {
            direction = "abajo";
            y += speed;
        }else if(inputH.leftPressed == true) {
            direction = "izquierda";
            x -= speed;
        }else if(inputH.rightPressed == true) {
            direction = "derecha";
            x += speed;
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage imagen = null;  //Cambiar el sprite del personaje basado en la direccion
        switch (direction) {
            case "arriba":
                imagen = left;
                break;
            case "abajo":
                imagen = right;
                break;
            case "izquierda":
                imagen = left;
                break;
            case "derecha":
                imagen = right;
                break;
        }

        g2.drawImage(imagen, x, y, panel.tileSize, panel.tileSize, null); //Observador de imagenes


    }

}
