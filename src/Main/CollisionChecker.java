package Main;


import Entidades.Entidad;
import Entidades.Objeto;

public class CollisionChecker {

    PanelJuego panel;

    public CollisionChecker(PanelJuego panel) {
    this.panel = panel;
    }

    public void checkCollision(Entidad entidad) {
        // Coordenadas del área sólida (hitbox) dentro del mundo
        int entityLeftWorldX = entidad.worldX + entidad.solidArea.x;
        int entityRightWorldX = entidad.worldX + entidad.solidArea.x + entidad.solidArea.width;
        int entityTopWorldY = entidad.worldY + entidad.solidArea.y;
        int entityBottomWorldY = entidad.worldY + entidad.solidArea.y + entidad.solidArea.height;

        int entityLeftCol = entityLeftWorldX / panel.tileSize;
        int entityRightCol = entityRightWorldX / panel.tileSize;
        int entityTopRow = entityTopWorldY / panel.tileSize;
        int entityBottomRow = entityBottomWorldY / panel.tileSize;

        int tileNum1, tileNum2;

        switch (entidad.direction) {
            case "arriba":
                entityTopRow = (entityTopWorldY - entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityRightCol][entityTopRow];
                break;
            case "abajo":
                entityBottomRow = (entityBottomWorldY + entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityRightCol][entityBottomRow];
                break;
            case "izquierda":
                entityLeftCol = (entityLeftWorldX - entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityLeftCol][entityBottomRow];
                break;
            case "derecha":
                entityRightCol = (entityRightWorldX + entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityRightCol][entityTopRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityRightCol][entityBottomRow];
                break;
            default:
                tileNum1 = tileNum2 = -1;
                break;
        }

        // Se verifica si alguno de los tiles tiene colisión activada
        if (tileNum1 != -1 && tileNum2 != -1) {
            if (panel.tileM.tile[tileNum1].collision || panel.tileM.tile[tileNum2].collision) {
                entidad.collisionOn = true;
            }
        }
    }

    public void checkObject(Entidad entidad, Objeto[] objetos) {
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null) {
                // Get entity's solid area position
                entidad.solidArea.x = entidad.worldX + entidad.solidArea.x;
                entidad.solidArea.y = entidad.worldY + entidad.solidArea.y;
                
                // Get object's solid area position
                objetos[i].solidArea.x = objetos[i].worldX + objetos[i].solidArea.x;
                objetos[i].solidArea.y = objetos[i].worldY + objetos[i].solidArea.y;
                
                // Check collision
                if (entidad.solidArea.intersects(objetos[i].solidArea)) {
                    entidad.collisionOn = true;
                }
                
                // Reset positions
                entidad.solidArea.x = entidad.solidArea.x - entidad.worldX;
                entidad.solidArea.y = entidad.solidArea.y - entidad.worldY;
                objetos[i].solidArea.x = objetos[i].solidArea.x - objetos[i].worldX;
                objetos[i].solidArea.y = objetos[i].solidArea.y - objetos[i].worldY;
            }
        }
    }

}
