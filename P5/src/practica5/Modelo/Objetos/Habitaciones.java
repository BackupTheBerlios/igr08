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
    private Sofa sofa;
    private Jarron estatua;
    private Persiana persiana;
    private Persona persona;
    
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
        puerta.setId(Objeto3D.PUERTA);
        for (int i=0; i<puerta.getHijos().size(); i++)
            this.addHijos(puerta.getHijos().get(i));
        
        // Cuadro
        cuadro = new Cuadro(gl);
        cuadro.setId(Objeto3D.CUADRO);
        for (int i=0; i<cuadro.getHijos().size(); i++)
            this.addHijos(cuadro.getHijos().get(i));
        
        // Muebles
        muebles = new Muebles(new PuntoVector3D(50, 50, -150), 45, gl);
        muebles.setId(Objeto3D.MUEBLES);
        for (int i=0; i<muebles.getHijos().size(); i++)
            this.addHijos(muebles.getHijos().get(i));
        
        
        // Estanteria
        estante = new Estanteria(new PuntoVector3D(10, 50, -30), 0, gl);
        estante.setId(Objeto3D.ESTANTERIA);
        this.addHijos(estante);
        
        // Lampara
        lampara = new Lampara(new PuntoVector3D(140, 250, -140), 0, gl);
        lampara.setId(Objeto3D.LAMPARA);
        for (int i=0; i<lampara.getHijos().size(); i++)
            this.addHijos(lampara.getHijos().get(i));
     
        // Perchero
        percha = new Perchero(new PuntoVector3D(30, 0, 50), 0, gl);
        percha.setId(Objeto3D.PERCHERO);
        this.addHijos(percha);
        
        // Mesa 2
        camilla = new Camilla(new PuntoVector3D(150, 0, 150), 0, gl);
        camilla.setId(Objeto3D.MESA);
        for (int i=0; i<camilla.getHijos().size(); i++)
            this.addHijos(camilla.getHijos().get(i));
        
        // Sofa
        sofa = new Sofa(new PuntoVector3D(10, 0, 150), 0, gl);
        sofa.setId(Objeto3D.SOFA);
        for (int i=0; i<sofa.getHijos().size(); i++)
            this.addHijos(sofa.getHijos().get(i));
        
        // Jarron
        estatua = new Jarron(new PuntoVector3D(230, 0, 50), 0, gl);
        estatua.setId(Objeto3D.ESTATUA);
        for (int i=0; i<estatua.getHijos().size(); i++)
            this.addHijos(estatua.getHijos().get(i));
        
        // Persiana
        persiana = new Persiana(new PuntoVector3D(-5, 140, -215), 0, gl);
        persiana.setId(Objeto3D.PERSIANA);
        for (int i=0; i<persiana.getHijos().size(); i++)
            this.addHijos(persiana.getHijos().get(i));
        
        // Persona
        persona = new Persona(new PuntoVector3D(230, 0, 230), 0, gl);
        persona.setId(Objeto3D.PERSONA);
        this.addHijos(persona);
        
    }
        
}
