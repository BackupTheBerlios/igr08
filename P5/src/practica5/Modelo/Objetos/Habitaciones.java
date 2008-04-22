package practica5.Modelo.Objetos;

import practica5.Modelo.Basic.*;

public class Habitaciones extends ObjetoCompuesto3D{
    
    // Atributos privados
    private Mesa mesa;
    private Muebles mueble;
    private Puerta puerta;
    private Silla[] sillas;
    private Estanteria estante;
    
    // Constructora
    public Habitaciones() {
        
        PuntoVector3D pos; 
        Cilindro cilindro;
        Tablero tablero;
        
        // Paredes
        tablero = new Tablero(150, 100, 50, 3, 3, 3);
        tablero.setId(0);
        tablero.getMatriz().trasladar(-0.65, 0.6, 0.35);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 0.0, 1.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0.7, 0.7, 0));        
        
        tablero = new Tablero(150, 600, 1200, 3, 3, 3);
        tablero.setId(1);
        tablero.getMatriz().trasladar(-0.95, 0.6, 0.35);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        
        tablero = new Tablero(150, 600, 300, 3, 3, 3);
        tablero.setId(2);
        tablero.getMatriz().trasladar(0, 0.6, 0);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 1.0, 0.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        
        tablero = new Tablero(150, 300, 300, 3, 3, 3);
        tablero.setId(3);
        tablero.getMatriz().trasladar(0.65, 0.6, 0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));
        
        tablero = new Tablero(150, 300, 300, 3, 3, 3);
        tablero.setId(4);
        tablero.getMatriz().trasladar(0.65, 1.94, 0);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 1.0, 0.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1));

        tablero = new Tablero(150, 600, 2100, 3, 3, 3);
        tablero.setId(5);
        tablero.getMatriz().trasladar(5.30, 0.6, 0);
        tablero.getMatriz().rotar(Math.PI/2, 0.0, 1.0, 0.0);
        tablero.setColor(new practica5.Modelo.Basic.Color(0, 1, 1)); 
    }
    
}
