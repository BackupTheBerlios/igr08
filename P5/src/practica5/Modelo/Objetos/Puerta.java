package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Puerta extends ObjetoCompuesto3D {
    
    private Tablero tablero;
    private Esfera esfera;
    
    public Puerta(GL gl, Texture[] texturas) {
        
        // Puerta
        tablero = new Tablero(110, 240, 10, 3, 3, 3, gl);
        tablero.setId(Objeto3D.PUERTA);
        tablero.setTextura(texturas[1]);
        tablero.getMatriz().rotar(-45, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(100, 0, 0);
        tablero.setColor(color.marron);
        this.addHijos(tablero);
        
        // Picaporte 1
        esfera = new Esfera(5, 10, 10);
        esfera.setId(Objeto3D.PUERTA);
        esfera.setGL(gl);
        esfera.setTextura(texturas[5]);
        esfera.getMatriz().trasladarM(135, 75, 50);
        esfera.setColor(color.gris);
        this.addHijos(esfera);
        
        // Picaporte 2
        esfera = new Esfera(5, 10, 10);
        esfera.setId(Objeto3D.PUERTA);
        esfera.setGL(gl);
        esfera.setTextura(texturas[5]);
        esfera.getMatriz().trasladarM(145, 75, 40);
        esfera.setColor(color.gris);
        this.addHijos(esfera);
        
    }
    
}
