package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Percha extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla percha;
    
    // Constructora
    public Percha(PuntoVector3D pos, double ori, GL gl) {
        
        // Percha
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(1, 250, 0));
        perfil.add(new PuntoVector3D(3, 50, 0));
        perfil.add(new PuntoVector3D(3, 30, 0));
        perfil.add(new PuntoVector3D(30, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        percha = new MallaPorRevolucion(perfil, 4, 0.7 , gl);
        percha.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        percha.setColor(color.negro);
        this.addHijos(percha);
    }
    
}
