package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.Modelo.Luces.Foco;
import practica5.util.Calculos;
import com.sun.opengl.util.texture.Texture;

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
    private PuntoVector3D p;
    
    // Constructora
    public Habitaciones(GL gl, PuntoVector3D pos, Texture[] texturas) {

        Cilindro cilindro;
        Tablero tablero;
       
        // Habitacion Trasera: Pared central
        tablero = new Tablero(350, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ()-350);
        tablero.setColor(color.rojoClaro);
        this.addHijos(tablero); 
        
        // Habitacion Trasera: Suelo
        tablero = new Tablero(350, 350, 10, 3, 3, 3, gl);
        tablero.setId(Objeto3D.SUELO);
        tablero.setTextura(texturas[11]);
        tablero.getMatriz().rotaXM(90);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ()-350);
        tablero.setColor(color.azulOscuro);
        this.addHijos(tablero);  
        
        // Habitacion Trasera: Pared Derecha
        tablero = new Tablero(100, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ()-350);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(160, 102, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(90);
        tablero.getMatriz().trasladar(pos.getX()-10, pos.getY()+148, pos.getZ()-90);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(160, 80, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.getMatriz().trasladar(0, 0, -250);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(90, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ() -90);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        // Habitaciïón Frontal: Pared Central
        tablero = new Tablero(150, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);
        
        tablero = new Tablero(130, 90, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().trasladar(pos.getX()+100, pos.getY()+160, pos.getZ());
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);    
        
        tablero = new Tablero(120, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().trasladar(pos.getX()+230, pos.getY(), pos.getZ());
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);
 
        
        // Habitacion Frontal: Pared Derecha
        tablero = new Tablero(160, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(50, 100, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ()+160);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(50, 100, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY()+150, pos.getZ()+160);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero);         
       
        tablero = new Tablero(140, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotaYM(-90);
        tablero.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ()+210);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        
        // Habitacion Frontal: Suelo
        tablero = new Tablero(350, 350, 10, 3, 3, 3, gl);
        tablero.setId(Objeto3D.SUELO);
        tablero.setTextura(texturas[0]);
        tablero.getMatriz().rotaXM(90);
        tablero.setColor(color.turquesaOscuro);
        this.addHijos(tablero);

        // Puerta
        puerta = new Puerta(gl, texturas, pos, -60);
        puerta.setId(Objeto3D.PUERTA);
        this.addHijos(puerta);
        
        // Cuadro
        cuadro = new Cuadro(gl, texturas, pos);
        cuadro.setId(Objeto3D.CUADRO);
        this.addHijos(cuadro);
        
        // Muebles
        p = new PuntoVector3D(pos.getX()+100, pos.getY()+50, pos.getZ()-150);
        muebles = new Muebles(gl, texturas, p, 45);
        muebles.setId(Objeto3D.MUEBLES);
        muebles.setTextura(texturas[1]);
        this.addHijos(muebles);
        
        // Estanteria
        p = new PuntoVector3D(pos.getX()+10, pos.getY()+50, pos.getZ()-30);
        estante = new Estanteria(p, 0, gl, texturas);
        estante.setId(Objeto3D.ESTANTERIA);
        this.addHijos(estante);
        
        // Lampara
        p = new PuntoVector3D(pos.getX()+150, pos.getY()+250, pos.getZ()-150);
        lampara = new Lampara(p, 0, gl);
        lampara.setId(Objeto3D.LAMPARA);
        this.addHijos(lampara);
     
        // Perchero
        p = new PuntoVector3D(pos.getX()+30, pos.getY(), pos.getZ()+30);
        percha = new Perchero(p, 0, gl);
        percha.setId(Objeto3D.PERCHERO);
        this.addHijos(percha);
        
        // Mesa 2
        p = new PuntoVector3D(pos.getX()+200, pos.getY(), pos.getZ()+200);
        camilla = new Camilla(p, 0, gl, texturas);
        this.addHijos(camilla);
        
        // Sofa
        p = new PuntoVector3D(pos.getX()+10, pos.getY(), pos.getZ()+200);
        sofa = new Sofa(p, 0, gl, texturas);
        sofa.setId(Objeto3D.SOFA);
        this.addHijos(sofa);
        
        // Jarron
        p = new PuntoVector3D(pos.getX()+300, pos.getY(), pos.getZ()+50);
        estatua = new Jarron(p, 0, gl, texturas);
        estatua.setId(Objeto3D.ESTATUA);
        this.addHijos(estatua);
        
        // Persiana
        p = new PuntoVector3D(pos.getX()-5, pos.getY()+140, pos.getZ()-275);
        persiana = new Persiana(p, 0, gl, texturas);
        persiana.setId(Objeto3D.PERSIANA);
        this.addHijos(persiana);
        
        // Persona
        persona = new Persona(new PuntoVector3D(280, 0, 280), 0, gl);
        persona.setId(Objeto3D.PERSONA);
        this.addHijos(persona);
    
    }
    
    
    // Metodos para las luces
    public void configurarLuzDireccional(GL gl) {
   
        gl.glEnable(gl.GL_LIGHT2);
        
        float[] ambiente = { 0.2f, 0.2f, 0.2f, 1.0f};
        float[] difusa = { 0.7f, 0.7f, 0.7f, 1.0f};
  
        //float difusa[] = {0.4,0.4,0.4,2.0};
        //PV3d* posicionUno = new PV3d(1,-1,0,0);
        
        PuntoVector3D pos1 = new PuntoVector3D(-1,-1,0,0);
        pos1.normaliza();
        
        float[] pos = { (float)pos1.getX(), (float)pos1.getY(), (float)pos1.getZ(), (float)0.0f};
        gl.glLightfv(gl.GL_LIGHT2, gl.GL_AMBIENT, ambiente, 1);
        gl.glLightfv(gl.GL_LIGHT2, gl.GL_DIFFUSE, difusa, 1);
        gl.glLightfv(gl.GL_LIGHT2, gl.GL_POSITION, pos, 1);
    } 
    
    public void quitarLuzAmbiente(GL gl) {
        float[] intensidad = {0.0f , 0.0f, 0.0f, 0.0f};
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, intensidad, 1);
    }
    
    public void ponerLuzAmbiente(GL gl) {
        float intensidad[]={0.2f,0.2f,0.2f,1f};
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, intensidad, 1);
    }
    

}
