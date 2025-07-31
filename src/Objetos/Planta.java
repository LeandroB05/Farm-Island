package Objetos;

public class Planta {
    public String nombre;
    public int tiempoPlantada;
    public int tiempoCrecimiento;
    public boolean crecimientoCompleto;

    public Planta(String nombre, int tiempoCrecimiento) {
        this.nombre = nombre;
        this.tiempoCrecimiento = tiempoCrecimiento;
        this.tiempoPlantada = 0;
    }

    public void actualizarEstado() {
        if (tiempoPlantada == tiempoCrecimiento) {
            crecimientoCompleto = true;
        }
    }

    public boolean estaCrecido() {
        return crecimientoCompleto;
    }

    public String getNombre() {
        return nombre;
    }
}

