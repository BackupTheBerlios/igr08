package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Donut extends ObjetoCompuesto3D {

    // Atributos privados
    private Toro donut;
    
    
    public Donut(PuntoVector3D pos, double ori, GL gl, Texture[] texturas) {
        
        // Donut
        donut = new Toro(20, 20, 10, 5);
        donut.setId(Objeto3D.DONUT);
        donut.setGL(gl);
        donut.setTextura(texturas[7]);
        donut.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        donut.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        donut.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        donut.setColor(color.verdeOscuro);
        this.addHijos(donut);  
    
    }
    
}
