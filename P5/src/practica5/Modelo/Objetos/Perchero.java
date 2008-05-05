package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Perchero extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla percha;
    
    // Constructora
    public Perchero(PuntoVector3D pos, double ori, GL gl) {
        
        super.setGL(gl);
        
        // Perchero
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(1, 250, 0));
        perfil.add(new PuntoVector3D(3, 50, 0));
        perfil.add(new PuntoVector3D(3, 30, 0));
        perfil.add(new PuntoVector3D(30, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        percha = new MallaPorRevolucion(perfil, 4, 0.7 , gl);
        percha.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        percha.setColor(color.negro);
        percha.setId(8);
        this.addHijos(percha);
    }
    
}
