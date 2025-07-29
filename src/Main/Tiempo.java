package Main;

public class Tiempo {
    private long acumuladorNano = 0;
    private int segundosTotales = 0;
    private int diaActual = 1;

    public void actualizar(long nanoSegundosTranscurridos) { //Suma el tiempo desde el ultimo ciclo en nanosegundos
        acumuladorNano += nanoSegundosTranscurridos;

        if (acumuladorNano >= 1000000000) { // 1 segundo = 1000000000 nanosegundos
            segundosTotales++;
            acumuladorNano -= 1000000000;
        }
    }

    public void pasarDia() {
        diaActual++;
        segundosTotales = 0;
        acumuladorNano = 0;
    }

    public int getDiaActual() {
        return diaActual;
    }

    public int getHoras() {
        return segundosTotales / 3600;
    }

    public int getMinutos() {
        return (segundosTotales % 3600) / 60;
    }

    public int getSegundos() {
        return segundosTotales % 60;
    }
}
