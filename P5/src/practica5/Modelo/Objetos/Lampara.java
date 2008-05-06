package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Lampara extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Cilindro lampara;
    
    public Lampara(PuntoVector3D pos, double ori, GL gl) {
        
        super.setGL(gl);
        
        // Brazo
        lampara = new Cilindro(5, 5, 20, 20, 30);
        lampara.setId(Objeto3D.LAMPARA);
        lampara.setGL(gl);
        lampara.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        lampara.getMatriz().trasladarM(pos.getX(), pos.getY() + 15, pos.getZ());
        lampara.setColor(color.amarilloClaro);
        this.addHijos(lampara);  
        
        // Tulipa
        lampara = new Cilindro(10, 30, 40, 20, 30);
        lampara.setId(Objeto3D.LAMPARA);
        lampara.setGL(gl);
        lampara.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        lampara.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        lampara.setColor(color.violetaClaro);
        this.addHijos(lampara);  
    }
    
}
