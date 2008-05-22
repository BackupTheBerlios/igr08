package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Donut extends ObjetoCompuesto3D {

    // Atributos privados
    private Toro donut;
    
    
    public Donut(PuntoVector3D pos, double ori, Texture[] texturas) {
        
//        super.setGL(gl);
        
        // Donut
        donut = new Toro(20, 20, 10, 5);
        donut.setId(Objeto3D.DONUT);
        donut.setTextura(texturas[7]);
        donut.getMatriz().rotaX(90);
        donut.getMatriz().rotaY(ori);
        donut.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        donut.setColor(color.verdeOscuro);
        this.addHijos(donut);  
    
    }
    
}
