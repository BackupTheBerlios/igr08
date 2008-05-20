package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Puerta extends ObjetoCompuesto3D {
    
    private Tablero tablero;
    private Esfera esfera;
    
    public Puerta(GL gl, Texture[] texturas) {
        
        // Puerta
        tablero = new Tablero(80, 160, 5, 3, 3, 3, gl);
        tablero.setId(Objeto3D.PUERTA);
        tablero.setTextura(texturas[1]);
        tablero.getMatriz().rotar(-60, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(150, 0, 0);
        tablero.setColor(color.marron);
        this.addHijos(tablero);
        
        // Picaporte 1
        esfera = new Esfera(3, 10, 10);
        esfera.setId(Objeto3D.PUERTA);
        esfera.setGL(gl);
        esfera.setTextura(texturas[5]);
        esfera.getMatriz().trasladarM(175, 75, 62);
        esfera.setColor(color.blanco);
        this.addHijos(esfera);
        
        // Picaporte 2
        esfera = new Esfera(3, 10, 10);
        esfera.setId(Objeto3D.PUERTA);
        esfera.setGL(gl);
        esfera.setTextura(texturas[5]);
        esfera.getMatriz().trasladarM(185, 75, 55);
        esfera.setColor(color.blanco);
        this.addHijos(esfera);
        
    }
    
}
