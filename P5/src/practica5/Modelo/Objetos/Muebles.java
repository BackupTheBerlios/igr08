package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Muebles extends ObjetoCompuesto3D {
    
    // Atributos Privados
    private Mesa mesa;
    private Silla silla;
    private Plato plato;
    private Donut donut;
    private Malla copa;
    
    public Muebles(PuntoVector3D pos, double ori, GL gl) {
        
        // Mesa
        mesa = new Mesa(new PuntoVector3D(pos.getX(), pos.getY(), pos.getZ()), ori, gl);
        this.addHijos(mesa);
        
        // Silla 1
        silla = new Silla(new PuntoVector3D(pos.getX(), pos.getY() - 25, pos.getZ() + 60), ori + 90, gl);
        this.addHijos(silla);
        
        // Silla 2
        silla = new Silla(new PuntoVector3D(pos.getX() + 110, pos.getY() - 25, pos.getZ() - 90), -ori, gl);
        this.addHijos(silla);
        
        // Plato
        plato = new Plato(new PuntoVector3D(pos.getX() + 50, pos.getY() + 10, pos.getZ() - 20), 0, gl);
        this.addHijos(plato);
        
        // Donut
        donut = new Donut(new PuntoVector3D(pos.getX() + 50, pos.getY() + 13, pos.getZ() - 20), 0, gl);
        this.addHijos(donut);
        
    }
    
    // Operaciones
    public void rotar(double ang, double ejeX, double ejeY, double ejeZ) {
        this.getMatriz().rotar(ang, ejeX, ejeY, ejeZ);
    }
    
    public void trasladar(double X, double Y, double Z) {
        this.getMatriz().trasladar(X, Y, Z);
    }
    
    public void escalar(double X, double Y, double Z) {
        this.getMatriz().escalar(X, Y, Z);
    }
    
}
