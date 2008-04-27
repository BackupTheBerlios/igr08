package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Puerta extends ObjetoCompuesto3D {
    
    private Tablero tablero;
    private Esfera esfera;
    
    public Puerta(GL gl) {
        
        // Puerta
        tablero = new Tablero(110, 240, 10, 3, 3, 3);
        tablero.setId(1);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-45, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(100, 0, 0);
        tablero.setColor(color.marron);
        this.addHijos(tablero);
        
        // Picaporte 1
        esfera = new Esfera(5, 10, 10);
        esfera.setId(1);
        esfera.setGL(gl);
        esfera.getMatriz().trasladarM(135, 75, 50);
        esfera.setColor(color.gris);
        this.addHijos(esfera);
        
        // Picaporte 2
        esfera = new Esfera(5, 10, 10);
        esfera.setId(1);
        esfera.setGL(gl);
        esfera.getMatriz().trasladarM(145, 75, 40);
        esfera.setColor(color.gris);
        this.addHijos(esfera);
        
    }
    
}
