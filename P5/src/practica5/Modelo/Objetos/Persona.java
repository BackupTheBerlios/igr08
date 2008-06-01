package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Persona extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Esfera cabeza;
    private Tablero articulacion;
    
    // Constructora
    public Persona(PuntoVector3D pos, double ori) {
        
//        super.setGL(gl);
        
        // Cabeza
        cabeza = new Esfera(10, 10, 10);
        cabeza.setId(Objeto3D.PERSONA);
        cabeza.getMatriz().trasladar(pos.getX() + 22.5, pos.getY() + 107, pos.getZ());
        cabeza.setColor(color.amarilloOscuro);
        this.addHijos(cabeza);
        
        // Tronco
        articulacion = new Tablero(45, 55, 10, 5, 5, 5);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX(), pos.getY() + 50, pos.getZ());
        articulacion.setColor(color.blanco);
        this.addHijos(articulacion);
        
        // Pierna 1
        articulacion = new Tablero(17, 50, 10, 5, 5, 5);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() + 25, pos.getY(), pos.getZ());
        articulacion.setColor(color.azulOscuro);
        this.addHijos(articulacion);
        
        // Pierna 2
        articulacion = new Tablero(17, 50, 10, 5, 5, 5);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() + 2.5, pos.getY(), pos.getZ());
        articulacion.setColor(color.azulOscuro);
        this.addHijos(articulacion);
        
        // Brazo 1
        articulacion = new Tablero(15, 40, 10, 5, 5, 5);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() + 45, pos.getY() + 55, pos.getZ());
        articulacion.setColor(color.rojo);
        this.addHijos(articulacion);
        
        // Brazo 2
        articulacion = new Tablero(15, 40, 10, 5, 5, 5);
        articulacion.setId(Objeto3D.PERSONA);
        articulacion.getMatriz().trasladar(pos.getX() - 15, pos.getY() + 55, pos.getZ());
        articulacion.setColor(color.rojo);
        this.addHijos(articulacion);
        
    }
    
}
