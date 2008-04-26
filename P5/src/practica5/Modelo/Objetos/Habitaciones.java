package practica5.Modelo.Objetos;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Habitaciones extends ObjetoCompuesto3D{
    
    // Atributos privados
    private Mesa mesa;
    private Muebles mueble;
    private Puerta puerta;
    private Silla[] sillas;
    private Estanteria estante;
    
    // Constructora
    public Habitaciones(GL gl) {
        
        PuntoVector3D pos; 
        Cilindro cilindro;
        Tablero tablero;
        
        // Paredes
        tablero = new Tablero(400, 400, 10, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(0, -200, 50);
        tablero.getMatriz().rotar(90, 0.0, 0.0, 1.0);
        tablero.setColor(this.getColor());   
        this.addHijos(tablero);
  
        tablero = new Tablero(250, 400, 10, 3, 3, 3);
        tablero.setId(1);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(0, 250, 100);
        tablero.setColor(color.violeta);
        this.addHijos(tablero); 
           /*  
        tablero = new Tablero(150, 600, 300, 3, 3, 3);
        tablero.setId(2);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(0, 0.6, 0);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 1.0, 0.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        this.addHijos(tablero);
        
        tablero = new Tablero(150, 300, 300, 3, 3, 3);
        tablero.setId(3);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(0.65, 0.6, 0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        this.addHijos(tablero);
        
        tablero = new Tablero(150, 300, 300, 3, 3, 3);
        tablero.setId(4);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(0.65, 1.94, 0);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 1.0, 0.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        this.addHijos(tablero);

        tablero = new Tablero(150, 600, 2100, 3, 3, 3);
        tablero.setId(5);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(5.30, 0.6, 0);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 1.0, 0.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        this.addHijos(tablero); */
    }
    
}
