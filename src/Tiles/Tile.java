package Tiles;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage imagen;
    public boolean collision = false;
    public boolean cultivable = false; // Nueva propiedad

    public Tile() {}

    // Metodo para decir si es o no cultivable
    public void setCultivable(boolean cultivable) {
        this.cultivable = cultivable;
    }
}