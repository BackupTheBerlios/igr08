package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Estanteria extends ObjetoCompuesto3D{
    
    // Atributos privados
    private Tablero tablero;
    
    public Estanteria(PuntoVector3D pos, double ori, GL gl) {
        
        // Estante 1
        tablero = new Tablero(120, 10, 50, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        tablero.setColor(Color.marron);   
        this.addHijos(tablero);
        
        // Estante 2
        tablero = new Tablero(120, 10, 50, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX(), pos.getY() + 30, pos.getZ());
        tablero.setColor(Color.marron);   
        this.addHijos(tablero);
        
        // Estante 3
        tablero = new Tablero(120, 10, 50, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX(), pos.getY() + 60, pos.getZ());
        tablero.setColor(Color.marron);   
        this.addHijos(tablero);
        
        // Libros del estante 1
        tablero = new Tablero(5, 30, 30, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX() + 30, pos.getY() + 5, pos.getZ());
        tablero.setColor(Color.amarilloOscuro);   
        this.addHijos(tablero);
        
        tablero = new Tablero(5, 30, 30, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX() + 70, pos.getY() + 5, pos.getZ());
        tablero.setColor(Color.turquesaOscuro);   
        this.addHijos(tablero);
        
        // Libros del estante 3
        tablero = new Tablero(5, 30, 50, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX() + 20, pos.getY() + 65, pos.getZ());
        tablero.setColor(Color.blanco);   
        this.addHijos(tablero);
        
        tablero = new Tablero(30, 10, 40, 3, 3, 3);
        tablero.setId(Objeto3D.ESTANTERIA);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(ori, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(pos.getX() + 50, pos.getY() + 65, pos.getZ());
        tablero.setColor(Color.rojoClaro);   
        this.addHijos(tablero);
    }
    
}
