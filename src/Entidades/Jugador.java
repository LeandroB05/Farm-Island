package Entidades;

import Main.InputHandler;
import Main.PanelJuego;
import Objetos.SuperObjetos;
import Objetos.Semillas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Iterator;

public class Jugador extends Entidad {

    InputHandler inputH;
    //Dinero del jugador
    public int dinero = 1000;
    //Inventario del jugador
    public ArrayList<SuperObjetos> inventario = new ArrayList<>();

    public SuperObjetos itemSeleccionado;
    public int indiceItemSeleccionado = 0;

    public final int screenX;
    public final int screenY;

    // Control de tiempo para mostrar el ítem
    public long tiempoMostradoItem = 0;
    public final long TIEMPO_VISIBILIDAD_ITEM = 3000; // 3 segundos en milisegundos
    public boolean mostrarInfoItem = true; // Controla si se muestra la info

    public Jugador(PanelJuego panel, InputHandler inputH) {
        super(panel);
        this.inputH = inputH;

        screenX = panel.screenWidth/2 - (panel.tileSize/2);
        screenY = panel.screenHeight/2- (panel.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 27;
        solidArea.height = 24;

        setDefaultValues(); //valores base
        getPlayerImage(); //imagenes
    }

    public void agregarItem(SuperObjetos nuevo) {
        if(nuevo == null) {
            System.out.println("Intento de agregar item nulo");
            return;
        }

        // Verificar si el item es stackeable y ya existe en el inventario
        if(nuevo.stackeable) {
            for(SuperObjetos item : inventario) {
                if(item != null && item.nombre != null && item.nombre.equals(nuevo.nombre)) {
                    // Verificar si son del mismo tipo exacto
                    if(item.getClass() == nuevo.getClass()) {
                        item.cantidad += nuevo.cantidad;
                        System.out.println("Stackeado: " + item.nombre + " x" + item.cantidad);
                        return;
                    }
                }
            }
        }

        // Si no es stackeable o no encontró stack existente
        if(nuevo.cantidad <= 0) nuevo.cantidad = 1; // Asegurar cantidad mínima
        inventario.add(nuevo);
        System.out.println("Nuevo item agregado: " + nuevo.nombre + " x" + nuevo.cantidad);

        // Seleccionar automáticamente si es el primer item
        if(itemSeleccionado == null && !inventario.isEmpty()) {
            itemSeleccionado = inventario.get(0);
            indiceItemSeleccionado = 0;
        }
    }


    // Metodo para remover items del inventario
    public void removerItem(SuperObjetos item, int cantidad) {
        if(item == null) return;

        if(item.stackeable) {
            for(int i = 0; i < inventario.size(); i++) {
                SuperObjetos inventarioItem = inventario.get(i);
                if(inventarioItem.nombre.equals(item.nombre)) {
                    inventarioItem.cantidad -= cantidad;

                    if(inventarioItem.cantidad <= 0) {
                        inventario.remove(i);
                        // Ajustar el índice seleccionado si es necesario
                        if(indiceItemSeleccionado >= inventario.size()) {
                            indiceItemSeleccionado = Math.max(0, inventario.size() - 1);
                        }
                        // Actualizar item seleccionado
                        if(!inventario.isEmpty()) {
                            itemSeleccionado = inventario.get(indiceItemSeleccionado);
                        } else {
                            itemSeleccionado = null;
                        }
                    }
                    return;
                }
            }
        } else {
            inventario.remove(item);
            // Ajustar el índice seleccionado si es necesario
            if(indiceItemSeleccionado >= inventario.size()) {
                indiceItemSeleccionado = Math.max(0, inventario.size() - 1);
            }
            // Actualizar item seleccionado
            if(!inventario.isEmpty()) {
                itemSeleccionado = inventario.get(indiceItemSeleccionado);
            } else {
                itemSeleccionado = null;
            }
        }
    }

    // Metodo para cambiar lo seleccionado
    public void cambiarItemSeleccionado(int direccion) {
        if(inventario.isEmpty()) return;

        int intentos = 0;
        int maxIntentos = inventario.size(); // Para evitar bucles infinitos

        do {
            indiceItemSeleccionado += direccion;

            // Ajustar índices si se pasa de los límites
            if(indiceItemSeleccionado < 0) {
                indiceItemSeleccionado = inventario.size() - 1;
            } else if(indiceItemSeleccionado >= inventario.size()) {
                indiceItemSeleccionado = 0;
            }

            itemSeleccionado = inventario.get(indiceItemSeleccionado);
            intentos++;

            // Salir si encontramos un item con cantidad > 0 o si hemos revisado todos
        } while((itemSeleccionado.cantidad <= 0 || !itemSeleccionado.stackeable) && intentos < maxIntentos);

        // Si no hay items válidos, seleccionar null
        if(itemSeleccionado.cantidad <= 0 || !itemSeleccionado.stackeable) {
            itemSeleccionado = null;
        } else {
            tiempoMostradoItem = System.currentTimeMillis();
            mostrarInfoItem = true;
        }
    }

    // Metodo para interactuar con el terreno (Plantar/cosechar)
    public void interactuar() {
        if(panel == null || panel.cultivosActivos == null || panel.mapaCultivos == null) {
            System.out.println("Error: Panel o listas no inicializadas");
            return;
        }

        int tileX = (worldX + getFrenteX()) / panel.tileSize;
        int tileY = (worldY + getFrenteY()) / panel.tileSize;

        // 1. Verificar cosecha
        Iterator<Semillas> iterator = panel.cultivosActivos.iterator();
        while(iterator.hasNext()) {
            Semillas cultivo = iterator.next();
            if(cultivo != null) {
                int[] pos = cultivo.getPosicionPlantada();
                if(pos != null && pos.length == 2 && pos[0] == tileX && pos[1] == tileY) {
                    if(cultivo.estaListaParaCosechar()) {
                        SuperObjetos producto = cultivo.cosechar();
                        if(producto != null) {
                            producto.stackeable = true; // Asegurar que sea stackeable
                            producto.cantidad = 1; // Cantidad base
                            agregarItem(producto);
                            iterator.remove();
                            panel.mapaCultivos[tileX][tileY] = 0;
                            return;
                        }
                    }
                }
            }
        }

        // 2. Verificar si puede plantar
        if(itemSeleccionado != null && itemSeleccionado instanceof Semillas &&
                itemSeleccionado.cantidad > 0) {

            Semillas semilla = (Semillas) itemSeleccionado;
            if(panel.tileM != null && panel.tileM.esTierraCultivable(tileX, tileY) &&
                    panel.mapaCultivos[tileX][tileY] == 0) {

                Semillas nuevaPlanta = semilla.copiar();
                if(nuevaPlanta != null) {
                    nuevaPlanta.plantar(panel.tiempo.getDiaActual(), tileX, tileY);
                    panel.cultivosActivos.add(nuevaPlanta);
                    panel.mapaCultivos[tileX][tileY] = 1;
                    removerItem(semilla, 1);
                }
            }
        }
    }

    // Métodos auxiliares para determinar la posición frontal
    private int getFrenteX() {
        switch(direction) {
            case "izquierda": return -panel.tileSize;
            case "derecha": return panel.tileSize;
            default: return 0;
        }
    }

    private int getFrenteY() {
        switch(direction) {
            case "arriba": return -panel.tileSize;
            case "abajo": return panel.tileSize;
            default: return 0;
        }
    }


    public void setDefaultValues(){
        worldX = panel.tileSize * 20;
        worldY = panel.tileSize * 25;
        speed = 7;
        direction = "derecha";
        idle = !(inputH.upPressed || inputH.downPressed || inputH.leftPressed || inputH.rightPressed);
    }
    public void getPlayerImage(){ //Obtener la ruta de las imagenes
        try{
            left1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/left4.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/caminar/right4.png"));

            idleR1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle1.png"));
            idleR2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle2.png"));
            idleR3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle3.png"));
            idleR4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/r.idle4.png"));

            idleL1 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle1.png"));
            idleL2 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle2.png"));
            idleL3 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle3.png"));
            idleL4 = ImageIO.read(getClass().getResourceAsStream("../sprites/jugador/quieto/l.idle4.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void update() {
        double dx = 0;
        double dy = 0;

        idle = true; // reiniciar estado

        if (inputH.upPressed) {
            dy -= 1;
            direction = "arriba";
            idle = false;
        }
        if (inputH.downPressed) {
            dy += 1;
            direction = "abajo";
            idle = false;
        }
        if (inputH.leftPressed) {
            dx -= 1;
            lastHorizontalDirection = "izquierda";
            direction = "izquierda";
            idle = false;
        }
        if (inputH.rightPressed) {
            dx += 1;
            lastHorizontalDirection = "derecha";
            direction = "derecha";
            idle = false;
        }

        //normalizar para no ir más rápido en diagonal
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx = (dx / length) * speed;
            dy = (dy / length) * speed;
        }

        if (dx != 0) {
            direction = dx < 0 ? "izquierda" : "derecha"; //actualizar direction
            collisionOn = false;
            panel.hitbox.checkCollision(this);
            if (!collisionOn) {  //moverse solo si no hay colision
                worldX += dx;
            }
        }

        if (dy != 0) {
            direction = dy < 0 ? "arriba" : "abajo";
            collisionOn = false;
            panel.hitbox.checkCollision(this);
            if (!collisionOn) { //moverse solo si no hay colision
                worldY += dy;
            }
        }
        //Si abre el inventario se queda quieto
        //Verifica si esta tocando algo
        //Objeto
        int indiceObjeto = panel.hitbox.verificarObjeto(this, true);
        //NPC
        int indiceNPC = panel.hitbox.verificarEntidad(this, panel.npc);
        if (indiceNPC!=999){
            if (collisionOn) {  //Asegura la posicion del mundo
                worldX -= dx;
                worldY -= dy;
            }
            panel.interacciones.interaccionNPC(indiceNPC);
        }

        if (indiceObjeto!=999){ //Para objetos
            if (collisionOn) {
                worldX -= dx;
                worldY -= dy;
            }
            panel.interacciones.interaccionObjeto(indiceObjeto);
        }

        // Ocultar automáticamente después del tiempo definido
        if(mostrarInfoItem && System.currentTimeMillis() - tiempoMostradoItem > TIEMPO_VISIBILIDAD_ITEM) {
            mostrarInfoItem = false;
        }

        // Animación
        spriteCounter++;
        int velocidadAnimacion = idle ? 20 : 10;
        if (spriteCounter > velocidadAnimacion) {
            spriteNum++;
            if (spriteNum > 4) spriteNum = 1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage imagen = null;

        if (idle) {
            if (lastHorizontalDirection.equals("derecha")) {
                if (spriteNum == 1) imagen = idleR1;
                else if (spriteNum == 2) imagen = idleR2;
                else if (spriteNum == 3) imagen = idleR3;
                else if (spriteNum == 4) imagen = idleR4;
            } else if (lastHorizontalDirection.equals("izquierda")) {
                if (spriteNum == 1) imagen = idleL1;
                else if (spriteNum == 2) imagen = idleL2;
                else if (spriteNum == 3) imagen = idleL3;
                else if (spriteNum == 4) imagen = idleL4;
            }
        } else {
            if (direction.equals("derecha") || (direction.equals("abajo") && lastHorizontalDirection.equals("derecha")) || (direction.equals("arriba") && lastHorizontalDirection.equals("derecha"))) {
                if (spriteNum == 1) imagen = right1;
                else if (spriteNum == 2) imagen = right2;
                else if (spriteNum == 3) imagen = right3;
                else if (spriteNum == 4) imagen = right4;
            } else if (direction.equals("izquierda") || (direction.equals("abajo") && lastHorizontalDirection.equals("izquierda")) || (direction.equals("arriba") && lastHorizontalDirection.equals("izquierda"))) {
                if (spriteNum == 1) imagen = left1;
                else if (spriteNum == 2) imagen = left2;
                else if (spriteNum == 3) imagen = left3;
                else if (spriteNum == 4) imagen = left4;
            }
        }
        if(itemSeleccionado != null && mostrarInfoItem) {
            // Coordenadas y dimensiones del fondo
            int fondoX = screenX;
            int fondoY = screenY - 40;
            int fondoAncho = 200;
            int fondoAlto = 40;

            // Dibujar fondo
            g2.setColor(new Color(0, 0, 0, 100));
            g2.fillRect(fondoX, fondoY, fondoAncho, fondoAlto);

            // Dibujar borde
            g2.setColor(Color.WHITE);
            g2.drawRect(fondoX, fondoY, fondoAncho, fondoAlto);

            // Dibujar texto
            g2.setFont(new Font("Pixelify Sans", Font.PLAIN, 12));
            g2.drawString("Seleccionado: " + itemSeleccionado.nombre, screenX + 5, screenY - 25);

            if(itemSeleccionado instanceof Semillas) {
                Semillas semilla = (Semillas)itemSeleccionado;
                g2.drawString("Días crecimiento: " + semilla.getDiasParaCrecer(),
                        screenX + 5, screenY - 10);
            }
        }

        g2.drawImage(imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);
    }


}
