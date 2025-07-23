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
    public int verificarObjeto(Entidad entidad, boolean jugador){//Revisa si se esta chocando con algo si es asi devuelve el indice del objeto
        int indice=999;                                             //El indice se usa despues para una interaccion entre objeto/npc jugador
        for(int i = 0; i< panel.objeto.length;i++){
            if (panel.objeto[i] != null){
                //Area solida de la entidad (jugador)
                entidad.solidArea.x = entidad.worldX + entidad.solidArea.x;
                entidad.solidArea.y = entidad.worldY + entidad.solidArea.y;
                //Area solida del objeto
                panel.objeto[i].solidArea.x = panel.objeto[i].worldX + panel.objeto[i].solidArea.x;
                panel.objeto[i].solidArea.y = panel.objeto[i].worldY + panel.objeto[i].solidArea.y;
                switch (entidad.direction) {
                    case "arriba":
                        entidad.solidArea.y = entidad.solidArea.y-entidad.speed;
                        if (entidad.solidArea.intersects(panel.objeto[i].solidArea)){
                            if(panel.objeto[i].colision){
                                entidad.collisionOn = true;
                            }
                            if(jugador){
                                indice = i;
                            }
                        }
                        break;
                        case "abajo":
                            entidad.solidArea.y = entidad.solidArea.y+entidad.speed;//Metodo intersects revisa si se intersectan (colisionan)
                            if (entidad.solidArea.intersects(panel.objeto[i].solidArea)){
                                if(panel.objeto[i].colision){
                                    entidad.collisionOn = true;
                                }
                                if(jugador){
                                    indice = i;
                                }
                            }
                            break;
                            case "izquierda":
                                entidad.solidArea.x = entidad.solidArea.x-entidad.speed;
                                if (entidad.solidArea.intersects(panel.objeto[i].solidArea)){
                                    if(panel.objeto[i].colision){
                                        entidad.collisionOn = true;
                                    }
                                    if(jugador){
                                        indice = i;
                                    }
                                }
                                break;
                                case "derecha":
                                    entidad.solidArea.x = entidad.solidArea.x+entidad.speed;
                                    if (entidad.solidArea.intersects(panel.objeto[i].solidArea)){
                                        if(panel.objeto[i].colision){
                                            entidad.collisionOn = true;
                                        }
                                        if(jugador){
                                            indice = i;
                                        }
                                    }
                                    break;
                }
                entidad.solidArea.x = entidad.solidAreaDefaultX;
                entidad.solidArea.y = entidad.solidAreaDefaultY;
                panel.objeto[i].solidArea.x = panel.objeto[i].solidAreaDefaultX;
                panel.objeto[i].solidArea.y = panel.objeto[i].solidAreaDefaultY;
            }
        }
        return indice;
    }

    public int verificarEntidad(Entidad entidad, Entidad[] objetivo) {
        int indice=999;                                             //El indice se usa despues para una interaccion entre objeto/npc jugador
        for(int i = 0; i< objetivo.length;i++){
            if (objetivo[i] != null){
                //Area solida de la entidad (jugador)
                entidad.solidArea.x = entidad.worldX + entidad.solidArea.x;
                entidad.solidArea.y = entidad.worldY + entidad.solidArea.y;
                //Area solida del objeto
                objetivo[i].solidArea.x = objetivo[i].worldX + objetivo[i].solidArea.x;
                objetivo[i].solidArea.y = objetivo[i].worldY + objetivo[i].solidArea.y;
                switch (entidad.direction) {
                    case "arriba":
                        entidad.solidArea.y = entidad.solidArea.y-entidad.speed;
                        if (entidad.solidArea.intersects(objetivo[i].solidArea)){
                            entidad.collisionOn = true;
                                indice = i;
                        }
                        break;
                    case "abajo":
                        entidad.solidArea.y = entidad.solidArea.y+entidad.speed;//Metodo intersects revisa si se intersectan (colisionan)
                        if (entidad.solidArea.intersects(objetivo[i].solidArea)){
                            entidad.collisionOn = true;
                                indice = i;
                        }
                        break;
                    case "izquierda":
                        entidad.solidArea.x = entidad.solidArea.x-entidad.speed;
                        if (entidad.solidArea.intersects(objetivo[i].solidArea)){
                            entidad.collisionOn = true;
                                indice = i;
                        }
                        break;
                    case "derecha":
                        entidad.solidArea.x = entidad.solidArea.x+entidad.speed;
                        if (entidad.solidArea.intersects(objetivo[i].solidArea)){
                            entidad.collisionOn = true;
                                indice = i;
                        }
                        break;
                }
                entidad.solidArea.x = entidad.solidAreaDefaultX;
                entidad.solidArea.y = entidad.solidAreaDefaultY;
                objetivo[i].solidArea.x = objetivo[i].solidAreaDefaultX;
                objetivo[i].solidArea.y = objetivo[i].solidAreaDefaultY;
            }
        }
        return indice;
    }
}
