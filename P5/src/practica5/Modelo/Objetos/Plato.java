package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Plato extends ObjetoCompuesto3D {

    // Atributos privados
    private Malla plato;
    
    
    public Plato(PuntoVector3D pos, double ori, GL gl, Texture[] texturas) {
        
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(25, 15, 0));
        perfil.add(new PuntoVector3D(15, 5, 0));
        perfil.add(new PuntoVector3D(0, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        
        // Plato
        plato = new MallaPorRevolucion(perfil, 10, 0.3 , gl);
        plato.setId(Objeto3D.PLATO);
        plato.setTextura(texturas[6]);
        plato.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        plato.setColor(color.blanco);
        this.addHijos(plato);  
    
    }
    
}
