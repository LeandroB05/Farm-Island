package Objetos;

public class Semillas extends SuperObjetos {
    int precio;
    int diasParaCrecer;

    public Semillas(String nombre, int precio, int diasParaCrecer) {
        this.nombre = nombre;
        this.precio = precio;
        this.stackeable = true;
        this.diasParaCrecer = diasParaCrecer;
    }

    public String getNombre() {return nombre;}

    public int getPrecio() {
        return precio;
    }
}
