package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Persiana extends ObjetoCompuesto3D {
    
    // Atributos
    Tablero tablero;
    
    // Cosntructora
    public Persiana(PuntoVector3D pos, double ori, Texture[] texturas)  {
        
//       super.setGL(gl);
       
       tablero= new Tablero(2, 100, 180, 3, 3, 3);
       tablero.setId(Objeto3D.PERSIANA);
       tablero.setTextura(texturas[1]);
       tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ() + 15);
       tablero.setColor(Color.amarilloClaro);
       this.addHijos(tablero); 
    }
    
}
