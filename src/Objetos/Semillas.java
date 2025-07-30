package Objetos;

public class Semillas extends SuperObjetos {
    int precio;

    public Semillas(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.stackeable = true;
    }

    public String getNombre() {return nombre;}

    public int getPrecio() {
        return precio;
    }
}
