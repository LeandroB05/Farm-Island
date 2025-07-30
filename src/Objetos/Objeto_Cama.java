package Objetos;

import Main.PanelJuego;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Objeto_Cama extends SuperObjetos{
    PanelJuego panel;
    public Objeto_Cama(){
        nombre="Cama";
        this.colision = true;
        try {
            imagen = ImageIO.read(getClass().getResource("../sprites/objetos/Cama.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 40; // ancho de la cama
        solidArea.height = 40; // alto de la cama
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
