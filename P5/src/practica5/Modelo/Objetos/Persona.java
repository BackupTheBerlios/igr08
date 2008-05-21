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
        cabeza.getMatriz().trasladar(pos.getX() + 20, pos.getY() + 107, pos.getZ());
        cabeza.setColor(color.amarilloOscuro);
        this.addHijos(cabeza);
        
        // Tronco
        articulacion = new Tablero(45, 55, 10, 5, 5, 5, gl);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX(), pos.getY() + 50, pos.getZ());
        articulacion.setColor(color.blanco);
        this.addHijos(articulacion);
        
        // Pierna 1
        articulacion = new Tablero(17, 63, 10, 5, 5, 5, gl);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() + 20, pos.getY(), pos.getZ());
        articulacion.setColor(color.azulOscuro);
        this.addHijos(articulacion);
        
        // Pierna 2
        articulacion = new Tablero(17, 63, 10, 5, 5, 5, gl);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() + 2, pos.getY(), pos.getZ());
        articulacion.setColor(color.azulOscuro);
        this.addHijos(articulacion);
        
        // Brazo 1
        articulacion = new Tablero(15, 40, 10, 5, 5, 5, gl);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() + 40, pos.getY() + 55, pos.getZ());
        articulacion.setColor(color.marron);
        this.addHijos(articulacion);
        
        // Brazo 2
        articulacion = new Tablero(15, 40, 10, 5, 5, 5, gl);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() - 15, pos.getY() + 55, pos.getZ());
        articulacion.setColor(color.marron);
        this.addHijos(articulacion);
        
    }
    
}
