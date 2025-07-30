package Objetos;

import javax.imageio.ImageIO;
import java.io.File;

public class Objeto_Carta extends SuperObjetos{//Esto va ser como una hoja de tutorial //Lo termino alfinal cuanto tengamos gameplay y mensajes
    public Objeto_Carta(){
        nombre="Carta";
        solidAreaDefaultX=0;
        solidAreaDefaultY=0;
        solidArea.width = 27;
        solidArea.height = 24;
        try {
            imagen = ImageIO.read(getClass().getResource("../sprites/objetos/carta.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
