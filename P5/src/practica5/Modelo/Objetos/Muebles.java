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
        silla = new Silla(new PuntoVector3D(pos.getX() + 5, pos.getY() - 25, pos.getZ() + 85), ori + 90, gl, texturas);
        this.addHijos(silla);
        
        // Silla 2
        silla = new Silla(new PuntoVector3D(pos.getX() + 160, pos.getY() - 25, pos.getZ() - 135), -ori, gl, texturas);
        this.addHijos(silla);
        
        // Plato
        plato = new Plato(new PuntoVector3D(pos.getX() + 75, pos.getY(), pos.getZ() - 25), 0, gl, texturas);
        this.addHijos(plato);
        
        // Donut
        donut = new Donut(new PuntoVector3D(pos.getX() + 75, pos.getY() + 13, pos.getZ() - 25), 0, gl, texturas);
        this.addHijos(donut);
        
        // Copa 1
        copa = new Copa(new PuntoVector3D(pos.getX() + 45, pos.getY(), pos.getZ()+ 15), 0, gl, texturas);
        this.addHijos(copa);        

        // Copa 2
        copa = new Copa(new PuntoVector3D(pos.getX() + 115, pos.getY(), pos.getZ() - 65), 0, gl, texturas);
        this.addHijos(copa);    
        
    }
    
    
}
