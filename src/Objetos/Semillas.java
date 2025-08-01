package Objetos;

import java.awt.image.BufferedImage;

public class Semillas extends SuperObjetos {
    // Propiedades básicas
    public int precio;              // Precio de compra/venta
    public int cantidad;           // Cantidad en stack

    // Sistema de cultivo
    public int diasParaCrecer;     // Días necesarios para madurar
    public int etapaActual;        // Etapa actual (0-4)
    public boolean plantada;       // Si está plantada actualmente
    public int diaPlantada;        // Día cuando se plantó
    public int xPlantada, yPlantada; // Posición en el mapa

    // Producto y gráficos
    public SuperObjetos producto;  // Objeto obtenido al cosechar
    public BufferedImage[] imagenesEtapas; // Sprites para cada etapa
    public String descripcion;     // Descripción para la UI

    // Constructor principal
    public Semillas(String nombre, int precio, int diasParaCrecer,
                    SuperObjetos producto, BufferedImage[] imagenesEtapas,
                    String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.diasParaCrecer = diasParaCrecer;
        this.producto = producto != null ? producto : new SuperObjetos(); // Objeto por defecto
        this.imagenesEtapas = imagenesEtapas != null ? imagenesEtapas : new BufferedImage[4];
        this.descripcion = descripcion;
        this.stackeable = true;
        this.cantidad = 1; // Inicializar cantidad
    }
    public BufferedImage[] getImagenesEtapas() {
        return this.imagenesEtapas;
    }

    // --- Getters y Setters básicos ---
    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public String getDescripcion() { return descripcion; }
    public int getDiasParaCrecer() { return diasParaCrecer; }
    public int getEtapaActual() { return etapaActual; }
    public boolean isPlantada() { return plantada; }
    public SuperObjetos getProducto() { return producto; }

    // --- Métodos de crecimiento ---

    // Devuelve la imagen correspondiente a la etapa actual
    public BufferedImage getImagenActual() {
        if(!plantada) return imagen;         // Imagen de semilla
        if(etapaActual < 1) return imagenesEtapas[0]; // Etapa por defecto
        return imagenesEtapas[etapaActual-1]; // Imagen de la etapa
    }

    // Planta la semilla en una posición específica
    public void plantar(int diaActual, int x, int y) {
        if(plantada) return;                // Evitar plantar dos veces
        this.plantada = true;
        this.diaPlantada = diaActual;
        this.xPlantada = x;
        this.yPlantada = y;
        this.etapaActual = 1;               // Primera etapa
    }

    // Actualiza el estado de crecimiento
    public void actualizarCrecimiento(int diaActual) {
        if(!plantada || etapaActual >= 4) return; // No necesita actualización

        int diasTranscurridos = diaActual - diaPlantada;
        float progreso = (float)diasTranscurridos / diasParaCrecer;

        if(progreso >= 1.0f) etapaActual = 4;     // Etapa final
        else if(progreso >= 0.75f) etapaActual = 3;
        else if(progreso >= 0.5f) etapaActual = 2;
        // Etapa 1 se mantiene por defecto
    }

    // Verifica si está lista para cosechar
    public boolean estaListaParaCosechar() {
        return plantada && etapaActual >= 4; // Maduración completa
    }

    // Realiza la cosecha y devuelve el producto
    public SuperObjetos cosechar() {
        if (producto == null || producto.nombre == null) {
            System.err.println("ERROR: producto es null o inválido al cosechar " + nombre);
            return null;
        }


        // Crear copia INDEPENDIENTE del producto
        SuperObjetos productoACosechar = this.producto.copiar();
        productoACosechar.cantidad = 1;

        System.out.println("Cosechado: " + productoACosechar.nombre +
                " (Precio: " + productoACosechar.precio + ")");

        // Resetear cultivo
        this.plantada = false;
        this.etapaActual = 0;

        return productoACosechar;
    }

    // Obtiene la posición donde está plantada
    public int[] getPosicionPlantada() {
        return plantada ? new int[]{xPlantada, yPlantada} : null;
    }

    // Calcula el progreso como porcentaje (0-100)
    public int getProgresoCrecimiento(int diaActual) {
        if(!plantada) return 0;
        int diasTranscurridos = diaActual - diaPlantada;
        return Math.min(100, (diasTranscurridos * 100) / diasParaCrecer);
    }

    // Calcula días restantes para madurar
    public int getDiasRestantes(int diaActual) {
        if(!plantada) return diasParaCrecer;
        int diasTranscurridos = diaActual - diaPlantada;
        return Math.max(0, diasParaCrecer - diasTranscurridos);
    }

    public Semillas copiar() {
        // Copia profunda del producto
        SuperObjetos copiaProducto = new SuperObjetos();
        if (producto != null) {
            copiaProducto.nombre = producto.nombre;
            copiaProducto.precio = producto.precio;
            copiaProducto.stackeable = producto.stackeable;
            copiaProducto.imagen = producto.imagen;
            copiaProducto.cantidad = producto.cantidad;
        }

        Semillas copia = new Semillas(nombre, precio, diasParaCrecer,
                copiaProducto, imagenesEtapas, descripcion);
        copia.imagen = this.imagen;
        copia.stackeable = this.stackeable;
        copia.cantidad = this.cantidad;
        return copia;
    }


    // Genera información para mostrar en la UI
    public String getInfoCultivo(int diaActual) {
        if(!plantada) return nombre + "\n" + descripcion + "\nPrecio: " + precio;

        StringBuilder info = new StringBuilder();
        info.append(nombre).append(" (Etapa ").append(etapaActual).append("/4)\n");
        info.append("Progreso: ").append(getProgresoCrecimiento(diaActual)).append("%\n");
        info.append("Días restantes: ").append(getDiasRestantes(diaActual));
        if(estaListaParaCosechar()) info.append("\n¡Listo para cosechar!");
        return info.toString();
    }
}