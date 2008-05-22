package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Cuadro extends ObjetoCompuesto3D {
        
    // Atributos privados
    private Tablero tablero;
    
    public Cuadro(Texture[] texturas, PuntoVector3D pos) {
        
        // Cuadro
        tablero = new Tablero(120, 80, 5, 3, 3, 3);
        tablero.setId(Objeto3D.CUADRO);
        tablero.setTextura(texturas[6]);
        tablero.getMatriz().trasladar(pos.getX()+120, pos.getY()+100, pos.getZ()-340);
        tablero.setColor(color.gris);
        this.addHijos(tablero); 
    }
    
}
