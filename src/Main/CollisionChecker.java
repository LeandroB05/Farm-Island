package Main;


import Entidades.Entidad;

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

}
