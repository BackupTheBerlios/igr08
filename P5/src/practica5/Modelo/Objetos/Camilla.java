package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Camilla extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Malla mesitaCamilla;
    
    // Coinstructora
    public Camilla(PuntoVector3D pos, double ori, Texture[] texturas) {
               
        // Perchero
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(-100, 101, 0));
	perfil.add(new PuntoVector3D(100, 100, 0));
        perfil.add(new PuntoVector3D(5, 90, 0));
        perfil.add(new PuntoVector3D(3, 30, 0));
        perfil.add(new PuntoVector3D(3, 0, 0));
        
        perfil = new Calculos().calculaPuntosBSplines(perfil, 50);
        
        mesitaCamilla = new MallaPorRevolucion(perfil, 4, 0.1);
        mesitaCamilla.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        mesitaCamilla.setColor(color.rosa);
        mesitaCamilla.setTextura(texturas[5]);
        mesitaCamilla.setId(Objeto3D.CAMILLA);
        this.addHijos(mesitaCamilla);
        
    }


}
