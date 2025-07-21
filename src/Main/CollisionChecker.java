package Main;

import Entidades.Entidad;

public class  CollisionChecker {

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

        int tileNum1 = -1, tileNum2 = -1;
        int objetoNum1 = 0, objetoNum2 = 0;

        switch (entidad.direction) {
            case "arriba":
                entityTopRow = (entityTopWorldY - entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityRightCol][entityTopRow];

                objetoNum1 = panel.tileM.objetoTileNumber[entityLeftCol][entityTopRow];
                objetoNum2 = panel.tileM.objetoTileNumber[entityRightCol][entityTopRow];
                break;
            case "abajo":
                entityBottomRow = (entityBottomWorldY + entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityRightCol][entityBottomRow];

                objetoNum1 = panel.tileM.objetoTileNumber[entityLeftCol][entityBottomRow];
                objetoNum2 = panel.tileM.objetoTileNumber[entityRightCol][entityBottomRow];
                break;
            case "izquierda":
                entityLeftCol = (entityLeftWorldX - entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityLeftCol][entityBottomRow];

                objetoNum1 = panel.tileM.objetoTileNumber[entityLeftCol][entityTopRow];
                objetoNum2 = panel.tileM.objetoTileNumber[entityLeftCol][entityBottomRow];
                break;
            case "derecha":
                entityRightCol = (entityRightWorldX + entidad.speed) / panel.tileSize;
                tileNum1 = panel.tileM.mapaTileNumber[entityRightCol][entityTopRow];
                tileNum2 = panel.tileM.mapaTileNumber[entityRightCol][entityBottomRow];

                objetoNum1 = panel.tileM.objetoTileNumber[entityRightCol][entityTopRow];
                objetoNum2 = panel.tileM.objetoTileNumber[entityRightCol][entityBottomRow];
                break;
        }

        boolean colisionTerreno = false;
        boolean colisionObjeto = false;

        if (tileNum1 != -1 && panel.tileM.tile[tileNum1].collision) colisionTerreno = true;
        if (tileNum2 != -1 && panel.tileM.tile[tileNum2].collision) colisionTerreno = true;

        if (objetoNum1 != 0 && panel.tileM.tile[objetoNum1].collision) colisionObjeto = true;
        if (objetoNum2 != 0 && panel.tileM.tile[objetoNum2].collision) colisionObjeto = true;

        // Prioridad: si hay objetos, su colisión manda
        if (objetoNum1 != 0 || objetoNum2 != 0) {
            entidad.collisionOn = colisionObjeto;
        } else {
            entidad.collisionOn = colisionTerreno;
        }
    }
}
