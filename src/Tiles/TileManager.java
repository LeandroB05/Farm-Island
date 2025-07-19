package Tiles;

import Main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {

    PanelJuego panel;
    public Tile[] tile;
    public int[][] mapaTileNumber;    // Terreno base
    public int[][] objetoTileNumber;  // Objetos encima del terreno

    public TileManager(PanelJuego panel) {
        this.panel = panel;
        tile = new Tile[50];
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
            tile[6] = new Tile();
            tile[6].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grass-end-right.png"));
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
            tile[12] = new Tile();
            tile[12].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/diagonal-dirt2.png"));
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
            tile[23] = new Tile();
            tile[23].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/sand1.png"));
            tile[24] = new Tile();
            tile[24].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirtL.png"));
            tile[25] = new Tile();
            tile[25].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/dirtR.png"));
            tile[26] = new Tile();
            tile[26].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassTL.png"));
            tile[27] = new Tile();
            tile[27].imagen = ImageIO.read(getClass().getResourceAsStream("../Sprites/fondo/grassTR.png"));




        }catch(IOException e){
            e.printStackTrace();
        }
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
