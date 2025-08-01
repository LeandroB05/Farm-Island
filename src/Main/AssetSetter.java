package Main;

import Entidades.NPC_Comerciante;
import Entidades.NPC_Comerciante2;
import Objetos.Objeto_Cama;
import Objetos.Objeto_Carta;
import Objetos.Semillas;
import Objetos.SuperObjetos;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetSetter {
    PanelJuego panel;

    public AssetSetter(PanelJuego panel) {
        this.panel = panel;
    }

    public void setObjeto() {
        // Objetos del juego (cama y carta)
        panel.objeto[0] = new Objeto_Carta();
        panel.objeto[0].worldX = panel.tileSize * 25;
        panel.objeto[0].worldY = panel.tileSize * 25;

        panel.objeto[1] = new Objeto_Cama();
        panel.objeto[1].worldX = panel.tileSize * 38;
        panel.objeto[1].worldY = panel.tileSize * 19;
    }

    public void setNPC() {
        // NPCs comerciantes
        panel.npc[0] = new NPC_Comerciante(panel);
        panel.npc[0].worldX = panel.tileSize * 21;
        panel.npc[0].worldY = panel.tileSize * 18;

        panel.npc[1] = new NPC_Comerciante2(panel);
        panel.npc[1].worldX = panel.tileSize * 26;
        panel.npc[1].worldY = panel.tileSize * 18;
    }

    public void setSemillas() {
        // Calabaza
        panel.semillas[0] = crearSemillaConProducto(
                "Semilla de Calabaza",
                25,
                5,
                "Calabaza",
                50,
                "/Sprites/Plantas/calabaza",
                "Cultivo de 5 días"
        );

        // Papa
        panel.semillas[1] = crearSemillaConProducto(
                "Semilla de Papa",
                15,
                4,
                "Papa",
                30,
                "/Sprites/Plantas/papa",
                "Tubérculo versátil"
        );

        // Rábano
        panel.semillas[2] = crearSemillaConProducto(
                "Semilla de Rábano",
                10,
                3,
                "Rábano",
                20,
                "/Sprites/Plantas/rabano",
                "Crecimiento rápido"
        );

        // Zanahoria
        panel.semillas[3] = crearSemillaConProducto(
                "Semilla de Zanahoria",
                12,
                4,
                "Zanahoria",
                25,
                "/Sprites/Plantas/zanahoria",
                "Rica en vitaminas"
        );

        // Coliflor
        panel.semillas[4] = crearSemillaConProducto(
                "Semilla de Coliflor",
                30,
                6,
                "Coliflor",
                60,
                "/Sprites/Plantas/coliflor",
                "Cultivo premium"
        );
    }

    public Semillas crearSemillaConProducto(String nombreSemilla, int precioSemilla, int diasCrecer, String nombreProducto, int precioProducto, String rutaBase, String descripcion) {
        try {
            // 1. Crear producto cosechado con todos los atributos REQUERIDOS
            SuperObjetos producto = new SuperObjetos();
            producto.nombre = nombreProducto;
            producto.precio = precioProducto;
            producto.stackeable = true;
            producto.cantidad = 1;
            producto.imagen = cargarImagen(rutaBase + "5.png");

            // 2. Cargar imágenes de crecimiento
            BufferedImage[] etapas = new BufferedImage[4];
            for(int i = 0; i < 4; i++) {
                etapas[i] = cargarImagen(rutaBase + (i+1) + ".png");
            }

            // 3. Crear la semilla
            Semillas semilla = new Semillas(
                    nombreSemilla,
                    precioSemilla,
                    diasCrecer,
                    producto,
                    etapas,
                    descripcion
            );

            // 4. Asignar imagen de la semilla
            semilla.imagen = cargarImagen(rutaBase + "0.png");
            semilla.stackeable = true;
            semilla.cantidad = 1;

            System.out.printf("""
            Semilla configurada:
            Nombre: %s
            Precio: %d
            Producto: %s (Precio: %d)
            Ruta base: %s
            %n""",
                    nombreSemilla, precioSemilla, nombreProducto, precioProducto, rutaBase);

            return semilla;

        } catch (IOException e) {
            System.err.println("ERROR CRÍTICO: No se pudo cargar " + nombreSemilla);
            e.printStackTrace();

            // Fallback seguro
            SuperObjetos productoFallback = new SuperObjetos();
            productoFallback.nombre = nombreProducto;
            productoFallback.precio = precioProducto;
            productoFallback.stackeable = true;

            return new Semillas(
                    nombreSemilla,
                    precioSemilla,
                    diasCrecer,
                    productoFallback,
                    new BufferedImage[4],
                    descripcion
            );
        }
    }

    private BufferedImage cargarImagen(String ruta) throws IOException {
        BufferedImage imagen = ImageIO.read(getClass().getResourceAsStream(ruta));
        if (imagen == null) {
            throw new IOException("Imagen no encontrada: " + ruta);
        }
        return imagen;
    }
}