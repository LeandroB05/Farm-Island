package Main;

import Entidades.NPC_Comerciante;
import Entidades.NPC_Comerciante2;
import Objetos.Objeto_Cama;
import Objetos.Objeto_Carta;

public class AssetSetter {//Instancia clases y los coloca en el mapa
    PanelJuego panel;
    public AssetSetter(PanelJuego panel) {
        this.panel = panel;
    }
    public void setObjeto(){
        panel.objeto[0] = new Objeto_Carta();
        panel.objeto[0].worldX = panel.tileSize*25;
        panel.objeto[0].worldY = panel.tileSize*25;
        panel.objeto[1] = new Objeto_Cama();
        panel.objeto[1].worldX = panel.tileSize*35;
        panel.objeto[1].worldY = panel.tileSize*20;
    }
    public void setNPC(){
        panel.npc[0] = new NPC_Comerciante(panel); //Spawnea npc y su posicion
        panel.npc[0].worldX = panel.tileSize*21;
        panel.npc[0].worldY = panel.tileSize*18;
        panel.npc[1] = new NPC_Comerciante2(panel);
        panel.npc[1].worldX = panel.tileSize*26;
        panel.npc[1].worldY = panel.tileSize*18;
    }
    public void setParcela(){
        panel.parcela[0] = new Parcela();
        panel.parcela[0].worldX = panel.tileSize*17;
        panel.parcela[0].worldY = panel.tileSize*42;
        panel.parcela[1] = new Parcela();
        panel.parcela[1].worldX = panel.tileSize*17;
        panel.parcela[1].worldY = panel.tileSize*46;
        panel.parcela[2] = new Parcela();
        panel.parcela[2].worldX = panel.tileSize*17;
        panel.parcela[2].worldY = panel.tileSize*50;
        panel.parcela[3] = new Parcela();
        panel.parcela[3].worldX = panel.tileSize*20;
        panel.parcela[3].worldY = panel.tileSize*42;
        panel.parcela[4] = new Parcela();
        panel.parcela[4].worldX = panel.tileSize*20;
        panel.parcela[4].worldY = panel.tileSize*46;
        panel.parcela[5] = new Parcela();
        panel.parcela[5].worldX = panel.tileSize*20;
        panel.parcela[5].worldY = panel.tileSize*50;
        panel.parcela[6] = new Parcela();
        panel.parcela[6].worldX = panel.tileSize*23;
        panel.parcela[6].worldY = panel.tileSize*42;
        panel.parcela[7] = new Parcela();
        panel.parcela[7].worldX = panel.tileSize*23;
        panel.parcela[7].worldY = panel.tileSize*46;
        panel.parcela[8] = new Parcela();
        panel.parcela[8].worldX = panel.tileSize*23;
        panel.parcela[8].worldY = panel.tileSize*50;

    }
}
