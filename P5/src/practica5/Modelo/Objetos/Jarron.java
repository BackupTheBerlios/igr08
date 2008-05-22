package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Jarron extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla piedra;
    
    // Constructora
    public Jarron(PuntoVector3D pos, double ori, Texture[] texturas) {
        
//        super.setGL(gl);
        
        // Perchero
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(30, 250, 0));
        perfil.add(new PuntoVector3D(1, 50, 0));
        perfil.add(new PuntoVector3D(45, 30, 0));
        perfil.add(new PuntoVector3D(15, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 30);
        
        piedra = new MallaPorRevolucion(perfil, 4, 0.1);
        piedra.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        piedra.setColor(color.gris);
        piedra.setTextura(texturas[10]);
        piedra.setId(Objeto3D.ESTATUA);
        this.addHijos(piedra);
    }
    
}
