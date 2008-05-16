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
    
    public Muebles(PuntoVector3D pos, double ori, GL gl, Texture[] texturas) {
           
        super.setGL(gl);
        
        // Mesa
        mesa = new Mesa(new PuntoVector3D(pos.getX(), pos.getY(), pos.getZ()), ori, gl, texturas);
        this.addHijos(mesa);
        
        // Silla 1
        silla = new Silla(new PuntoVector3D(pos.getX(), pos.getY() - 25, pos.getZ() + 60), ori + 90, gl, texturas);
        this.addHijos(silla);
        
        // Silla 2
        silla = new Silla(new PuntoVector3D(pos.getX() + 110, pos.getY() - 25, pos.getZ() - 90), -ori, gl, texturas);
        this.addHijos(silla);
        
        // Plato
        plato = new Plato(new PuntoVector3D(pos.getX() + 50, pos.getY(), pos.getZ() - 20), 0, gl, texturas);
        this.addHijos(plato);
        
        // Donut
        donut = new Donut(new PuntoVector3D(pos.getX() + 50, pos.getY() + 13, pos.getZ() - 20), 0, gl, texturas);
        this.addHijos(donut);
        
        // Copa 1
        copa = new Copa(new PuntoVector3D(pos.getX() + 30, pos.getY(), pos.getZ()), 0, gl, texturas);
        this.addHijos(copa);        

        // Copa 2
        copa = new Copa(new PuntoVector3D(pos.getX() + 80, pos.getY(), pos.getZ() - 50), 0, gl, texturas);
        this.addHijos(copa);    
        
    }
    
    
}
