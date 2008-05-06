package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Persiana extends ObjetoCompuesto3D {
    
    // Atributos
    Tablero tablero;
    
    // Cosntructora
    public Persiana(PuntoVector3D pos, double ori, GL gl)  {
        
       super.setGL(gl);
       
       tablero= new Tablero(5, 50, 180, 3, 3, 3);
       tablero.setId(Objeto3D.PERSIANA);
       tablero.setGL(gl);
       tablero.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ() + 15);
       tablero.setColor(Color.amarilloClaro);
       this.addHijos(tablero); 
    }
    
}
