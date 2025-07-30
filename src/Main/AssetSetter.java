package Main;

import Entidades.NPC_Comerciante;
import Entidades.NPC_Comerciante2;
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

    }
    public void setNPC(){
        panel.npc[0] = new NPC_Comerciante(panel); //Spawnea npc y su posicion
        panel.npc[0].worldX = panel.tileSize*21;
        panel.npc[0].worldY = panel.tileSize*18;
        panel.npc[1] = new NPC_Comerciante2(panel);
        panel.npc[1].worldX = panel.tileSize*26;
        panel.npc[1].worldY = panel.tileSize*18;
    }
}
