package Main;

import Entidades.NPC_Comerciante;
import Objetos.Objeto_Carta;

public class AssetSetter {//Instancia clases y los coloca en el mapa
    PanelJuego panel;
    public AssetSetter(PanelJuego panel) {
        this.panel = panel;
    }
    public void setObjeto(){
        panel.objeto[0] = new Objeto_Carta();
        panel.objeto[0].worldX = panel.tileSize*26;
        panel.objeto[0].worldY = panel.tileSize*19;

    }
    public void setNPC(){
        panel.npc[0] = new NPC_Comerciante(panel); //Spawnea npc y su posicion
        panel.npc[0].worldX = panel.tileSize*24;
        panel.npc[0].worldY = panel.tileSize*19;
    }
}
