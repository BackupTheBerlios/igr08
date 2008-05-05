package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Jarron extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla piedra;
    
    // Constructora
    public Jarron(PuntoVector3D pos, double ori, GL gl) {
        
        super.setGL(gl);
        
        // Perchero
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(30, 250, 0));
        perfil.add(new PuntoVector3D(1, 50, 0));
        perfil.add(new PuntoVector3D(45, 30, 0));
        perfil.add(new PuntoVector3D(15, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        piedra = new MallaPorRevolucion(perfil, 4, 0.1 , gl);
        piedra.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        piedra.setColor(color.gris);
        piedra.setId(8);
        this.addHijos(piedra);
    }
    
}
