package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Cuadro extends ObjetoCompuesto3D {
        
    // Atributos privados
    private Tablero tablero;
    
    public Cuadro(GL gl) {
        
        // Cuadro
        tablero = new Tablero(160, 120, 10, 3, 3, 3);
        tablero.setId(Objeto3D.CUADRO);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(80, 100, -260);
        tablero.setColor(color.gris);
        this.addHijos(tablero); 
    }
    
}
