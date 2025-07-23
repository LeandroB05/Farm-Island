package Entidades;

import Main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_Comerciante extends Entidad {//IMPORTANTE: EL NPC NO TIENE COLISION PORQUE LO VOY A ENCERRAR EN UNA TIENDA

    public NPC_Comerciante(PanelJuego panel) {
        super(panel);
        worldX = panel.tileSize * 22;
        worldY = panel.tileSize * 17;
        speed=0;

        getComercianteImage();
        update();

    }
    public void getComercianteImage(){ //Obtener la ruta de las imagenes
        try{
            idleL1 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/comerciante1.png"));
            idleL2 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/comerciante2.png"));
            idleL3 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/comerciante3.png"));
            idleL4 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/comerciante4.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        double dx = 0;
        double dy = 0;
        idle = true;//El npc va estar quieto
        // Animación
        spriteCounter++;
        int velocidadAnimacion = idle ? 20 : 10;
        if (spriteCounter > velocidadAnimacion) {
            spriteNum++;
            if (spriteNum > 4) spriteNum = 1;
            spriteCounter = 0;
        }
    }
}
