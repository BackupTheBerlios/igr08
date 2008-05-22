package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Muebles extends ObjetoCompuesto3D {
    
    // Atributos Privados
    private Mesa mesa;
    private Silla silla;
    private Plato plato;
    private Donut donut;
    private Copa copa;
    private PuntoVector3D p;
    
    public Muebles(Texture[] texturas, PuntoVector3D pos, double ori) {
           
//        super.setGL(gl);
        
        // Mesa
        mesa = new Mesa(pos, ori, texturas);
        this.addHijos(mesa);
        
        // Silla 1
        p = new PuntoVector3D(pos.getX()+5, pos.getY()-25, pos.getZ()+85);
        silla = new Silla(p, ori + 90, texturas);
        this.addHijos(silla);
        
        // Silla 2
        p = new PuntoVector3D(pos.getX()+160, pos.getY()-25, pos.getZ()-135);
        silla = new Silla(p, -ori, texturas);
        this.addHijos(silla);
        
        // Plato
        p = new PuntoVector3D(pos.getX()+75, pos.getY(), pos.getZ()-25);
        plato = new Plato(p, 0, texturas);
        this.addHijos(plato);
        
        // Donut
        p = new PuntoVector3D(pos.getX()+75, pos.getY()+13, pos.getZ()-25);
        donut = new Donut(p, 0, texturas);
        this.addHijos(donut);
        
        // Copa 1
        p = new PuntoVector3D(pos.getX()+45, pos.getY(), pos.getZ()+15);
        copa = new Copa(p, 0, texturas);
        this.addHijos(copa);        

        // Copa 2
        p = new PuntoVector3D(pos.getX()+115, pos.getY(), pos.getZ()-65);
        copa = new Copa(p, 0, texturas);
        this.addHijos(copa);    
        
    }
    
    
}
