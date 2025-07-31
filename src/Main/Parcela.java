package Main;
import Objetos.Planta;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Parcela {
    public BufferedImage imagen;
    public boolean colision = false;//Cambiar a true en la misma clase especifica si quieres colisiones
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,72,144);//Valor defualt del area de un tile completo}
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    public boolean activa = false;
    public int accion;

    Planta planta;
    public boolean estaOcupada() {
        return planta != null;
    }
    public void actualizar() {
        if (planta != null) {
            planta.actualizarEstado();
        }
    }
    public Planta getPlanta() {
        return planta;
    }
}

