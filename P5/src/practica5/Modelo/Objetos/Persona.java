package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Persona extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Esfera cabeza;
    private Tablero articulacion;
    
    // Constructora
    public Persona(PuntoVector3D pos, double ori, GL gl) {
        
        super.setGL(gl);
        
        // Cabeza
        cabeza = new Esfera(10, 10, 10);
        cabeza.setId(Objeto3D.PERSONA);
        cabeza.setGL(gl);
        cabeza.getMatriz().trasladarM(pos.getX() + 25, pos.getY() + 100, pos.getZ());
        cabeza.setColor(color.naranja);
        this.addHijos(cabeza);
        
        // Tronco
        articulacion = new Tablero(50, 60, 5, 5, 5, 5);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.setGL(gl);
        articulacion.getMatriz().trasladarM(pos.getX(), pos.getY() + 50, pos.getZ());
        articulacion.setColor(color.blanco);
        this.addHijos(articulacion);
        
        
    }
    
}
