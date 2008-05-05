package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Habitaciones extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Mesa mesa;
    private Muebles muebles;
    private Puerta puerta;
    private Cuadro cuadro;
    private Silla silla;
    private Lampara lampara;
    private Estanteria estante;
    private Perchero percha;
    private Camilla camilla;
    
    // Constructora
    public Habitaciones(GL gl) {

        Cilindro cilindro;
        Tablero tablero;
        
        // Habitación Trasera: Pared central
        tablero = new Tablero(400, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(0, 0, -270);
        tablero.setColor(color.rojoClaro);
        this.addHijos(tablero); 
        
        
        // Habitación Trasera: Suelo
        tablero = new Tablero(400, 400, 20, 3, 3, 3);
        tablero.setId(Objeto3D.SUELO);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -270);
        tablero.setColor(color.amarilloClaro);
        this.addHijos(tablero);  
        
        
        // Habitación Trasera: Pared Derecha
        tablero = new Tablero(100, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -270);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(200, 180, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -205);
        tablero.getMatriz().trasladarM(0, 147, 0);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(200, 130, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -205);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(120, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -80);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        // Habitación Frontal: Pared Central
        tablero = new Tablero(150, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);
        
        tablero = new Tablero(105, 160, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(100, 160, 0);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);    
        
        tablero = new Tablero(150, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().trasladar(170, 0, 0);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);
 
        
        // Habitación Frontal: Pared Derecha
        tablero = new Tablero(160, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(85, 150, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, 105);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(85, 175, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, 105);
        tablero.getMatriz().trasladarM(0, 150, 0);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero);         
       
        tablero = new Tablero(160, 400, 20, 3, 3, 3);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, 160);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        
        // Habitación Frontal: Suelo
        tablero = new Tablero(400, 400, 20, 3, 3, 3);
        tablero.setId(Objeto3D.SUELO);
        tablero.setGL(gl);
        tablero.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tablero.setColor(color.turquesaOscuro);
        this.addHijos(tablero);

        // Puerta
        puerta = new Puerta(gl);
        this.addHijos(puerta);
        
        // Cuadro
        cuadro = new Cuadro(gl);
        this.addHijos(cuadro);
        
        // Muebles
        muebles = new Muebles(new PuntoVector3D(50, 50, -150), 45, gl);
        muebles.setId(Objeto3D.MUEBLES);
        this.addHijos(muebles);
        
        // Estanteria
        estante = new Estanteria(new PuntoVector3D(10, 50, -30), 0, gl);
        estante.setId(Objeto3D.ESTANTERIA);
        this.addHijos(estante);
        
        // Lampara
        lampara = new Lampara(new PuntoVector3D(140, 250, -140), 0, gl);
        lampara.setId(Objeto3D.LAMPARA);
        this.addHijos(lampara);
     
        // Perchero
        percha = new Perchero(new PuntoVector3D(30, 0, 50), 0, gl);
        percha.setId(Objeto3D.PERCHERO);
        this.addHijos(percha);
        
        // Mesa 2
        camilla = new Camilla(new PuntoVector3D(150, 0, 150), 0, gl);
        camilla.setId(Objeto3D.PERCHERO);
        this.addHijos(camilla);
    }
        
}
