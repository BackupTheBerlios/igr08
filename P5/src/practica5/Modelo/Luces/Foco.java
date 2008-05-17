package practica5.Modelo.Luces;

import practica5.Modelo.Basic.PuntoVector3D;

public class Foco {
    
    // Atributos privados
    private PuntoVector3D pos;
    private double angulo;
    private double alpha;
    private boolean on;
    
    // Constructora por defecto
    public Foco() {        
        this.pos = new PuntoVector3D();
        this.angulo = 90;
        this.alpha = 45;
        this.on = true;
    }
    
    // Constructora con parámetros
    public Foco(PuntoVector3D p, double ang, double alfa, boolean encendido) {        
        this.pos = p;
        this.angulo = ang;
        this.alpha = alfa;
        this.on = encendido;
    }
    
    
}
