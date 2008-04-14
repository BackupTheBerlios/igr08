package practica5.Modelo;

public class Tablero extends Malla{
    private double largo, ancho, grueso;
    private double divLargo, divAncho, divGrueso;
    
    public Tablero() {
    }
    
    public Tablero (double largo, double ancho, double grueso,
                    double divLargo, double divAncho, double divGrueso) {
        this.largo = largo;
        this.ancho = ancho;
        this.grueso = grueso;
        this.divLargo = divLargo;
        this.divAncho = divAncho;
        this.divGrueso = divGrueso;   
    }
    
}
