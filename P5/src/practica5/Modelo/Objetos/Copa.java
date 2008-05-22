package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Copa extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla copa;
    
    // Constructora
    public Copa(PuntoVector3D pos, double ori, Texture[] texturas) {
        
        // Percha
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(5, 30, 0));
        perfil.add(new PuntoVector3D(3, 20, 0));
        perfil.add(new PuntoVector3D(1, 10, 0));
        perfil.add(new PuntoVector3D(1, 5, 0));
        perfil.add(new PuntoVector3D(5, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        copa = new MallaPorRevolucion(perfil, 4, 0.7);
        copa.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        copa.setId(Objeto3D.COPA);
        copa.setTextura(texturas[6]);
        copa.setColor(color.turquesaClaro);
        this.addHijos(copa);
    }
}