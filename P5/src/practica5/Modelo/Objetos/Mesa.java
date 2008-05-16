package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Mesa extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Tablero tabla;
    private Malla pataMesa;
    
    // Constructora
    public Mesa(PuntoVector3D pos, double ori, GL gl, Texture[] texturas) {
        
        
        // Tabla de la mesa
        tabla = new Tablero(150, 80, 10, 3, 3, 3, gl);
        tabla.setId(Objeto3D.MESA);
        tabla.setTextura(texturas[1]);
        tabla.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tabla.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        tabla.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        addHijos(tabla);  
        
        
        // Pata
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(10, 50, 0));
        perfil.add(new PuntoVector3D(10, 40, 0));
        perfil.add(new PuntoVector3D(10, 30, 0));
        perfil.add(new PuntoVector3D(10, 20, 0));
        perfil.add(new PuntoVector3D(10, 10, 0));
        perfil.add(new PuntoVector3D(10, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 20);
        
        pataMesa = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pataMesa.getMatriz().trasladarM(pos.getX() + 50, pos.getY() - 50, pos.getZ() - 20);
        pataMesa.setColor(color.violetaOscuro);
        pataMesa.setId(Objeto3D.MESA);
        pataMesa.setTextura(texturas[1]);
        addHijos(pataMesa);

    }
    
}
