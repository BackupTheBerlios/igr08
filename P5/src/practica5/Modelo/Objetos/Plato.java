package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Plato extends ObjetoCompuesto3D {

    // Atributos privados
    private Cilindro plato;
    
    
    public Plato(PuntoVector3D pos, double ori, GL gl) {
        
        // Plato
        plato = new Cilindro(20, 0, 10, 20, 30);
        plato.setId(4);
        plato.setGL(gl);
        plato.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        plato.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        plato.setColor(color.blanco);
        this.addHijos(plato);  
    
    }
    
}
