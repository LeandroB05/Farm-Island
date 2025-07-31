package Main;

public class AccionesParcelas {
    PanelJuego panel;
    public AccionesParcelas(PanelJuego panel) {
        this.panel = panel;
    }
    public void Acciones(int accion){
        switch(accion){
            //Plantar
            case 0:
                panel.inventario.activo = true;
                break;
                //Regar
                case 1:
                    break;
                    //Recoger
                    case 2:
                        break;
        }
    }
    public void AccionRealizada(){
        panel.mostrarAccionParcela=false;
        panel.inventario.activo = false;
    }
}
