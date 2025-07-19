package Entidades;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Objeto extends Entidad {
    
    public BufferedImage imagen;
    public String nombre;
    public boolean colisionOn;
    
    public Objeto() {
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
    }
    
    public void setImage(String imagePath) {
        try {
            imagen = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2, int screenX, int screenY, int tileSize) {
        if (imagen != null) {
            g2.drawImage(imagen, screenX, screenY, tileSize, tileSize, null);
        }
    }
}