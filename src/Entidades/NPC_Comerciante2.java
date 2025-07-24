package Entidades;

import Main.PanelJuego;

import javax.imageio.ImageIO;
import java.io.IOException;

public class NPC_Comerciante2 extends Entidad {//IMPORTANTE: EL NPC NO TIENE COLISION PORQUE LO VOY A ENCERRAR EN UNA TIENDA

    public NPC_Comerciante2(PanelJuego panel) {
        super(panel);
        worldX = panel.tileSize * 22;
        worldY = panel.tileSize * 17;
        speed=0;

        getComercianteImage();
        update();

    }
    public void getComercianteImage(){ //Obtener la ruta de las imagenes
        try{
            idleL1 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/Comerciante2.1.png"));
            idleL2 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/Comerciante2.2.png"));
            idleL3 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/Comerciante2.3.png"));
            idleL4 = ImageIO.read(getClass().getResourceAsStream("../sprites/comerciante/Comerciante2.4.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        idle = true;//El npc va estar quieto
        // Animación
        if (panel.pausado){
            spriteCounter = 0;
        }else{
            spriteCounter++;
            int velocidadAnimacion = idle ? 20 : 10;
            if (spriteCounter > velocidadAnimacion) {
                spriteNum++;
                if (spriteNum > 4) spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
}
