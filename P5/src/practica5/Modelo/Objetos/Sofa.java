package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Sofa extends ObjetoCompuesto3D {
    
    // Atributos privados
    Tablero tablero;
    
    // Constructora
    public Sofa(PuntoVector3D pos, double ori, GL gl, Texture[] texturas) {
        
       super.setGL(gl);
       
       tablero= new Tablero(70, 40, 120, 3, 3, 3, gl);
       tablero.setId(Objeto3D.SOFA);
       tablero.setTextura(texturas[4]);
       tablero.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ() + 10);
       tablero.setColor(color.azulOscuro);
       this.addHijos(tablero); 
       
       tablero= new Tablero(10, 100, 140, 3, 3, 3, gl);
       tablero.setId(Objeto3D.SOFA);
       tablero.setTextura(texturas[4]);
       tablero.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
       tablero.setColor(color.verdeOscuro);
       this.addHijos(tablero); 
       
       tablero= new Tablero(70, 60, 10, 3, 3, 3, gl);
       tablero.setId(Objeto3D.SOFA);
       tablero.setTextura(texturas[4]);
       tablero.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
       tablero.setColor(color.marron);
       this.addHijos(tablero); 
       
       tablero= new Tablero(70, 60, 10, 3, 3, 3, gl);
       tablero.setId(Objeto3D.SOFA);
       tablero.setTextura(texturas[4]);
       tablero.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ() + 130);
       tablero.setColor(color.marron);
       this.addHijos(tablero); 
       
       
    }
    
}
