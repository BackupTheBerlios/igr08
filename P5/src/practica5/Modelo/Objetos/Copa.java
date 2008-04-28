package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Copa extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla copa;
    
    // Constructora
    public Copa(PuntoVector3D pos, double ori, GL gl) {
        
        // Percha
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(5, 30, 0));
        perfil.add(new PuntoVector3D(3, 20, 0));
        perfil.add(new PuntoVector3D(1, 10, 0));
        perfil.add(new PuntoVector3D(1, 5, 0));
        perfil.add(new PuntoVector3D(5, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        copa = new MallaPorRevolucion(perfil, 4, 0.7 , gl);
        copa.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        copa.setColor(color.turquesaClaro);
        this.addHijos(copa);
    }
}