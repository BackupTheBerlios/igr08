package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Puerta extends ObjetoCompuesto3D {
    
    private Tablero tablero;
    private Esfera esfera;
    
    public Puerta(Texture[] texturas, PuntoVector3D pos, double angulo) {
        
        // Puerta
        tablero = new Tablero(80, 160, 5, 3, 3, 3);
        tablero.setId(Objeto3D.PUERTA);
        tablero.setTextura(texturas[1]);
        tablero.getMatriz().rotaY(angulo);
        tablero.getMatriz().trasladar(pos.getX()+150, pos.getY()+0,pos.getZ()+0);
        tablero.setColor(color.marron);
        this.addHijos(tablero);
        
        // Picaporte 1
        esfera = new Esfera(3, 10, 10);
        esfera.setId(Objeto3D.PUERTA);
      //  esfera.setGL(gl);
        esfera.setTextura(texturas[5]);
        esfera.getMatriz().trasladar(pos.getX()+175, pos.getY()+75, pos.getZ()+62);
        esfera.setColor(color.blanco);
        this.addHijos(esfera);
        
        // Picaporte 2
        esfera = new Esfera(3, 10, 10);
        esfera.setId(Objeto3D.PUERTA);
        //esfera.setGL(gl);
        esfera.setTextura(texturas[5]);
        esfera.getMatriz().trasladar(pos.getX()+185, pos.getY()+75, pos.getZ()+55);
        esfera.setColor(color.blanco);
        this.addHijos(esfera);
        
    }
    
}
