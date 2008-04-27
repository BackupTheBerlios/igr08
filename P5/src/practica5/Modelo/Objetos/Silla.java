package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Silla extends ObjetoCompuesto3D{
    
    private Tablero tabla;
    private Cilindro pata;
    
    public Silla(PuntoVector3D pos, double ori, GL gl) {
        
        // Asiento
        tabla = new Tablero(50, 50, 10, 3, 3, 3);
        tabla.setId(Objeto3D.SILLA);
        tabla.setGL(gl);
        tabla.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tabla.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        tabla.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        this.addHijos(tabla);  
        
        // Respaldo
        tabla = new Tablero(50, 50, 10, 3, 3, 3);
        tabla.setId(Objeto3D.SILLA);
        tabla.setGL(gl);
        tabla.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        tabla.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        this.addHijos(tabla);  
    }
    
}
