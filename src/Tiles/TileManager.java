package Tiles;

import Main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    PanelJuego panel;
    public Tile[] tile;
    public int[][] mapaTileNumber;    // Terreno base
    public int[][] objetoTileNumber;  // Objetos encima del terreno

    public TileManager(PanelJuego panel) {
        this.panel = panel;
        tile = new Tile[1000];
        mapaTileNumber = new int[panel.maxWorldCol][panel.maxWorldRow];
        objetoTileNumber = new int[panel.maxWorldCol][panel.maxWorldRow];

        getTileImage();
        loadMap("../Sprites/Mapas/mundo01.txt", mapaTileNumber);
        loadMap("../Sprites/Mapas/objetos01.txt", objetoTileNumber);
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Fondo/grass2.png"));
            tile[1] = new Tile();
            tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirt1.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/water1.png"));
            tile[2].collision = true;  // para anadirle colision a cualquier tile que querramos
            tile[3] = new Tile();
            tile[3].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/wall1.png"));
            tile[4] = new Tile();
            tile[4].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/path.png"));
            tile[5] = new Tile();
            tile[5].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grass-end-left.png"));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grass-end-right.png"));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/uppath1.png"));
            tile[8] = new Tile();
            tile[8].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/uppath2.png"));
            tile[9] = new Tile();
            tile[9].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirt-to-grass1.png"));
            tile[10] = new Tile();
            tile[10].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirt-to-grass2.png"));
            tile[11] = new Tile();
            tile[11].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/diagonal-dirt1.png"));
            tile[11].collision = true;
            tile[12] = new Tile();
            tile[12].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/diagonal-dirt2.png"));
            tile[12].collision = true;
            tile[13] = new Tile();
            tile[13].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassL.png"));
            tile[14] = new Tile();
            tile[14].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassR.png"));
            tile[15] = new Tile();
            tile[15].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassT.png"));
            tile[16] = new Tile();
            tile[16].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassB.png"));
            tile[17] = new Tile();
            tile[17].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassB2.png"));
            tile[18] = new Tile();
            tile[18].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/pathTL.png"));
            tile[19] = new Tile();
            tile[19].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/pathTR.png"));
            tile[20] = new Tile();
            tile[20].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/pathBL.png"));
            tile[21] = new Tile();
            tile[21].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/pathBR.png"));
            tile[22] = new Tile();
            tile[22].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/path1.png"));
            tile[22].setCultivable(true);
            tile[23] = new Tile();
            tile[23].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/sand1.png"));
            tile[24] = new Tile();
            tile[24].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirtL.png"));
            tile[24].collision = true;
            tile[25] = new Tile();
            tile[25].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirtR.png"));
            tile[25].collision = true;
            tile[26] = new Tile();
            tile[26].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassTL.png"));
            tile[26].collision = true;
            tile[27] = new Tile();
            tile[27].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassTR.png"));
            tile[27].collision = true;
            tile[28] = new Tile();
            tile[28].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/bush1.png"));
            tile[28].collision = true;
            tile[29] = new Tile();
            tile[29].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/flower1.png"));
            tile[30] = new Tile();
            tile[30].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/puenteL.png"));
            tile[31] = new Tile();
            tile[31].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/puenteR.png"));
            tile[32] = new Tile();
            tile[32].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/puenteT.png"));
            tile[33] = new Tile();
            tile[33].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/puenteB.png"));
            //34-36 y los carteles son parte de la tienda
            tile[34] = new Tile();
            tile[34].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Tienda_superiorL.png"));
            tile[34].collision = true;
            tile[35] = new Tile();
            tile[35].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Tienda_inferiorL.png"));
            tile[35].collision = true;
            tile[36] = new Tile();
            tile[36].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Tienda_inferiorR.png"));
            tile[36].collision = true;
            tile[37] = new Tile();
            tile[37].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Cartel_semillas.png"));
            tile[37].collision = true;
            tile[38] = new Tile();
            tile[38].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Cartel_cosechas.png"));
            tile[38].collision = true;
            tile[39] = new Tile();
            tile[39].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Caja.png"));
            tile[39].collision = true;
            tile[40] = new Tile();
            tile[40].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Caja2.png"));
            tile[40].collision = true;
            tile[41] = new Tile();
            tile[41].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Cofre.png"));//Nose como clasificarlo
            tile[41].collision = true;
            tile[42] = new Tile();
            tile[42].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Fondo/grass2.png"));
            tile[42].collision = true;
            //Casas
            tile[43] = new Tile();
            tile[43].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaPequeA.png"));
            tile[43].collision = true;
            tile[44] = new Tile();
            tile[44].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaPequeA2.png"));
            tile[44].collision = true;
            tile[45] = new Tile();
            tile[45].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaPequeb.png"));
            tile[45].collision = true;
            tile[46] = new Tile();
            tile[46].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaPequeB2.png"));
            tile[46].collision = true;
            tile[47] = new Tile();
            tile[47].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaA.png"));
            tile[47].collision = true;
            tile[48] = new Tile();
            tile[48].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaA2.png"));
            tile[48].collision = true;
            tile[49] = new Tile();
            tile[49].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaA3.png"));
            tile[49].collision = true;
            tile[50] = new Tile();
            tile[50].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaA4.png"));
            tile[50].collision = true;
            tile[51] = new Tile();
            tile[51].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaB.png"));
            tile[51].collision = true;
            tile[52] = new Tile();
            tile[52].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaB2.png"));
            tile[52].collision = true;
            tile[53] = new Tile();
            tile[53].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaB3.png"));
            tile[53].collision = true;
            tile[54] = new Tile();
            tile[54].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/GranjaB4.png"));
            tile[54].collision = true;
            tile[55] = new Tile();
            tile[55].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulA.png"));
            tile[55].collision = true;
            tile[56] = new Tile();
            tile[56].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulA2.png"));
            tile[56].collision = true;
            tile[57] = new Tile();
            tile[57].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulA3.png"));
            tile[57].collision = true;
            tile[58] = new Tile();
            tile[58].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulA4.png"));
            tile[58].collision = true;
            tile[59] = new Tile();
            tile[59].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulA5.png"));
            tile[59].collision = true;
            tile[60] = new Tile();
            tile[60].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulA6.png"));
            tile[60].collision = true;
            tile[61] = new Tile();
            tile[61].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulB.png"));
            tile[61].collision = true;
            tile[62] = new Tile();
            tile[62].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulB2.png"));
            tile[62].collision = true;
            tile[63] = new Tile();
            tile[63].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulB3.png"));
            tile[63].collision = true;
            tile[64] = new Tile();
            tile[64].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulB4.png"));
            tile[64].collision = true;
            tile[65] = new Tile();
            tile[65].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulB5.png"));
            tile[65].collision = true;
            tile[66] = new Tile();
            tile[66].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/CasaAzulB6.png"));
            tile[66].collision = true;
            tile[67] = new Tile();
            // Estructuras aparte de casas
            tile[67].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/FuenteA.png"));
            tile[67].collision = true;
            tile[68] = new Tile();
            tile[68].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/FuenteA2.png"));
            tile[68].collision = true;
            tile[69] = new Tile();
            tile[69].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/FuenteA3.png"));
            tile[69].collision = false;
            tile[70] = new Tile();
            tile[70].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/FuenteB.png"));
            tile[70].collision = true;
            tile[71] = new Tile();
            tile[71].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/FuenteB2.png"));
            tile[71].collision = true;
            tile[72] = new Tile();
            tile[72].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/FuenteB3.png"));
            tile[72].collision = false;
            //Vegetación
            tile[73] = new Tile();
            tile[73].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolA.png"));
            tile[73].collision = true;
            tile[74] = new Tile();
            tile[74].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolA2.png"));
            tile[74].collision = true;
            tile[75] = new Tile();
            tile[75].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolA3.png"));
            tile[75].collision = false;
            tile[76] = new Tile();
            tile[76].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolB.png"));
            tile[76].collision = true;
            tile[77] = new Tile();
            tile[77].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolB2.png"));
            tile[77].collision = true;
            tile[78] = new Tile();
            tile[78].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolB3.png"));
            tile[78].collision = false;
            tile[79] = new Tile();
            tile[79].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolilloA.png"));
            tile[79].collision = true;
            tile[80] = new Tile();
            tile[80].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolilloA2.png"));
            tile[80].collision = false;
            tile[81] = new Tile();
            tile[81].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolilloB.png"));
            tile[81].collision = true;
            tile[82] = new Tile();
            tile[82].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolilloB2.png"));
            tile[82].collision = false;
            tile[83] = new Tile();
            tile[83].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Arbolillo2A.png"));
            tile[83].collision = true;
            tile[84] = new Tile();
            tile[84].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Arbolillo2A2.png"));
            tile[84].collision = false;
            tile[85] = new Tile();
            tile[85].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Arbolillo2B.png"));
            tile[85].collision = true;
            tile[86] = new Tile();
            tile[86].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Arbolillo2B2.png"));
            tile[86].collision = false;
            tile[87] = new Tile();
            tile[87].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Flor1.png"));
            tile[87].collision = false;
            tile[88] = new Tile();
            tile[88].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Flor2.png"));
            tile[88].collision = false;
            tile[89] = new Tile();
            tile[89].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Flor3.png"));
            tile[89].collision = false;
            tile[90] = new Tile();
            tile[90].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Arbusto.png"));
            tile[90].collision = true;
            tile[91] = new Tile();
            tile[91].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/Arbusto2.png"));
            tile[91].collision = false;
            tile[92] = new Tile();
            tile[92].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbustoB.png"));
            tile[92].collision = true;
            tile[93] = new Tile();
            tile[93].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbustoB2.png"));
            tile[93].collision = false;
            tile[94] = new Tile();
            tile[94].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Vegeta/ArbolCortado.png"));
            tile[94].collision = true;
            //extras
            tile[95] = new Tile();
            tile[95].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/Banquillo.png"));
            tile[95].collision = false;
            tile[96] = new Tile();
            tile[96].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/Banquillo2.png"));
            tile[96].collision = false;
            tile[97] = new Tile();
            tile[97].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/BanquilloB.png"));
            tile[97].collision = true;
            tile[98] = new Tile();
            tile[98].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/BanquilloB2.png"));
            tile[98].collision = true;
            tile[99] = new Tile();
            tile[99].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/MesaA.png"));
            tile[99].collision = true;
            tile[100] = new Tile();
            tile[100].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/MesaA2.png"));
            tile[100].collision = true;
            tile[101] = new Tile();
            tile[101].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/MesaB.png"));
            tile[101].collision = true;
            tile[102] = new Tile();
            tile[102].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/MesaB2.png"));
            tile[102].collision = true;
            tile[103] = new Tile();
            tile[103].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/techoo.png"));
            tile[103].collision = true;
            tile[104] = new Tile();
            tile[104].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/CasaUpper.png"));
            tile[104].collision = true;
            tile[105] = new Tile();
            tile[105].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/CasaUpper2.png"));
            tile[105].collision = true;
            tile[106] = new Tile();
            tile[106].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/Puerta.png"));
            tile[106].collision = false;
            tile[107] = new Tile();
            tile[107].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/Puerta2c.png"));
            tile[107].collision = false;
            tile[108] = new Tile();
            tile[108].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/techoo2.png"));
            tile[108].collision = true;
            tile[109] = new Tile();
            tile[109].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/Pared.png"));
            tile[109].collision = true;
            tile[110] = new Tile();
            tile[110].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/Soporte.png"));
            tile[110].collision = true;
            tile[111] = new Tile();
            tile[111].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/estructuras/Granja/Mueble.png"));
            tile[111].collision = true;
            tile[112] = new Tile();
            tile[112].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/Alfombra.png"));
            tile[112].collision = false;
            tile[113] = new Tile();
            tile[113].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/Alfombra2.png"));
            tile[113].collision = false;
            tile[114] = new Tile();
            tile[114].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/AlfombraB.png"));
            tile[114].collision = false;
            tile[115] = new Tile();
            tile[115].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Extras/AlfombraB2.png"));
            tile[115].collision = false;
            tile[116] = new Tile();
            tile[116].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Objetos/Cama2.png"));
            tile[116].collision = false;
            tile[117] = new Tile();
            tile[117].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/paredB.png"));
            tile[117].collision = true;
            tile[118] = new Tile();
            tile[118].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/paredB2.png"));
            tile[118].collision = true;
            tile[119] = new Tile();
            tile[119].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/Estructuras/Granja/Suelo.png"));
            tile[119].collision = false;

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean esTierraCultivable(int worldCol, int worldRow) {
        // 1. Verificar que las coordenadas estén dentro del mapa
        if (worldCol < 0 || worldRow < 0 || worldCol >= panel.maxWorldCol || worldRow >= panel.maxWorldRow) {
            return false;
        }

        // 2. Verificar el tile base (suelo)
        int tileBaseNum = mapaTileNumber[worldCol][worldRow];

        // Si el tile base no existe o no es cultivable
        if (tileBaseNum < 0 || tileBaseNum >= tile.length || tile[tileBaseNum] == null || !tile[tileBaseNum].cultivable) {
            return false;
        }

        // 3. Verificar objetos superpuestos (capa de objetos)
        int tileObjetoNum = objetoTileNumber[worldCol][worldRow];

        // Si hay un objeto y es collisionable o no cultivable
        if (tileObjetoNum != 0) {
            if (tileObjetoNum < 0 || tileObjetoNum >= tile.length || tile[tileObjetoNum] == null) {
                return false;
            }
            if (tile[tileObjetoNum].collision || !tile[tileObjetoNum].cultivable) {
                return false;
            }
        }

        // 5. Si pasó todas las validaciones, es cultivable
        return true;
    }

    public void loadMap(String filePath, int[][] targetArray){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < panel.maxWorldCol && row < panel.maxWorldRow){
                String line = reader.readLine();
                while(col < panel.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    targetArray[col][row] = num;
                    col++;
                }
                if(col == panel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            reader.close();
        }catch(Exception e){
            // Si el archivo no existe o está vacío, deja la capa en 0 (sin objetos)
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < panel.maxWorldCol && worldRow < panel.maxWorldRow){
            int tileNum =  mapaTileNumber[worldCol][worldRow];
            int objetoNum = objetoTileNumber[worldCol][worldRow];

            int worldX = worldCol * panel.tileSize;
            int worldY = worldRow * panel.tileSize;
            int screenX = worldX - panel.jugador1.worldX + panel.jugador1.screenX;
            int screenY = worldY - panel.jugador1.worldY + panel.jugador1.screenY;

            // Dibuja primero el terreno
            g2.drawImage(tile[tileNum].imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);

            // Dibuja el objeto si hay (por ejemplo, si objetoNum != 0)
            if(objetoNum != 0){
                g2.drawImage(tile[objetoNum].imagen, screenX, screenY, panel.tileSize, panel.tileSize, null);
            }

            worldCol++;
            if(worldCol == panel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
