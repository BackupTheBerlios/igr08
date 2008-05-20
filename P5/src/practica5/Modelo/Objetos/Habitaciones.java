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
    
    // Constructora
    public Habitaciones(GL gl, Texture[] texturas) {

        Cilindro cilindro;
        Tablero tablero;
        
        // Habitacion Trasera: Pared central
        tablero = new Tablero(350, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().trasladar(0, 0, -350);
        tablero.setColor(color.rojoClaro);
        this.addHijos(tablero); 
        
        
        // Habitacion Trasera: Suelo
        tablero = new Tablero(350, 350, 10, 3, 3, 3, gl);
        tablero.setId(Objeto3D.SUELO);
        tablero.setTextura(texturas[0]);
        tablero.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -350);
        tablero.setColor(color.amarilloClaro);
        this.addHijos(tablero);  
        
        
        // Habitacion Trasera: Pared Derecha
        tablero = new Tablero(100, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -350);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(200, 102, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -250);
        tablero.getMatriz().trasladarM(0, 147, 0);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(200, 80, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -250);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(100, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, -90);
        tablero.setColor(color.azulClaro);
        this.addHijos(tablero); 
        
        // Habitaci�n Frontal: Pared Central
        tablero = new Tablero(150, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);
        
        tablero = new Tablero(130, 90, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setGL(gl);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().trasladar(100, 160, 0);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);    
        
        tablero = new Tablero(120, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().trasladar(230, 0, 0);
        tablero.setColor(color.verdeClaro);   
        this.addHijos(tablero);
 
        
        // Habitacion Frontal: Pared Derecha
        tablero = new Tablero(160, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(50, 100, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, 160);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        tablero = new Tablero(50, 100, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 150, 160);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero);         
       
        tablero = new Tablero(140, 250, 10, 3, 3, 3, gl);
        tablero.setId(0);
        tablero.setTextura(texturas[2]);
        tablero.getMatriz().rotar(-90, 0.0, 1.0, 0.0);
        tablero.getMatriz().trasladarM(0, 0, 210);
        tablero.setColor(color.turquesaClaro);
        this.addHijos(tablero); 
        
        
        // Habitacion Frontal: Suelo
        tablero = new Tablero(350, 350, 10, 3, 3, 3, gl);
        tablero.setId(Objeto3D.SUELO);
        tablero.setTextura(texturas[0]);
        tablero.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tablero.setColor(color.turquesaOscuro);
        this.addHijos(tablero);

        // Puerta
        puerta = new Puerta(gl, texturas);
        puerta.setId(Objeto3D.PUERTA);
        for (int i=0; i<puerta.getHijos().size(); i++)
            this.addHijos(puerta.getHijos().get(i));
        
        // Cuadro
        cuadro = new Cuadro(gl, texturas);
        cuadro.setId(Objeto3D.CUADRO);
        for (int i=0; i<cuadro.getHijos().size(); i++)
            this.addHijos(cuadro.getHijos().get(i));
        
        // Muebles
        muebles = new Muebles(new PuntoVector3D(100, 50, -150), 45, gl, texturas);
        muebles.setId(Objeto3D.MUEBLES);
        muebles.setTextura(texturas[1]);
        this.addHijos(muebles);
        
        // Estanteria
        estante = new Estanteria(new PuntoVector3D(10, 50, -30), 0, gl, texturas);
        estante.setId(Objeto3D.ESTANTERIA);
        for (int i=0; i<estante.getHijos().size(); i++)
            this.addHijos(estante.getHijos().get(i));
        
        // Lampara
        lampara = new Lampara(new PuntoVector3D(150, 250, -150), 0, gl);
        lampara.setId(Objeto3D.LAMPARA);
        this.addHijos(lampara);
     
        // Perchero
        percha = new Perchero(new PuntoVector3D(30, 0, 30), 0, gl);
        percha.setId(Objeto3D.PERCHERO);
        this.addHijos(percha);
        
        // Mesa 2
        camilla = new Camilla(new PuntoVector3D(200, 0, 200), 0, gl, texturas);
        for (int i=0; i<camilla.getHijos().size(); i++)
            this.addHijos(camilla.getHijos().get(i));
        
        // Sofa
        sofa = new Sofa(new PuntoVector3D(10, 0, 200), 0, gl, texturas);
        sofa.setId(Objeto3D.SOFA);
        for (int i=0; i<sofa.getHijos().size(); i++)
            this.addHijos(sofa.getHijos().get(i));
        
        // Jarron
        estatua = new Jarron(new PuntoVector3D(300, 0, 50), 0, gl, texturas);
        estatua.setId(Objeto3D.ESTATUA);
        for (int i=0; i<estatua.getHijos().size(); i++)
            this.addHijos(estatua.getHijos().get(i));
        
        // Persiana
        persiana = new Persiana(new PuntoVector3D(-5, 140, -265), 0, gl, texturas);
        persiana.setId(Objeto3D.PERSIANA);
        for (int i=0; i<persiana.getHijos().size(); i++)
            this.addHijos(persiana.getHijos().get(i));
        
        // Persona
        persona = new Persona(new PuntoVector3D(280, 0, 280), 0, gl);
        persona.setId(Objeto3D.PERSONA);
        this.addHijos(persona);
    
        // Luces
        Foco luzLampara = new Foco(gl);
        luzLampara.setId(20);
        luzLampara.setMatriz(lampara.getMatriz());
        //luzLampara.setLuzAmbiente(gl); 
        luzLampara.enciende();
        luzLampara.setLuzDifusa(gl);
        luzLampara.dibuja2(gl);

        //configurarLuzDireccional(gl);
        ponerLuzAmbiente(gl);

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
        float[] intensidad = {-10.0f , -10.0f, -10.0f, 0.0f};
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, intensidad, 1);
    }
    
    public void ponerLuzAmbiente(GL gl) {
        float intensidad[]={0.2f,0.2f,0.2f,1f};
        gl.glLightModelfv(gl.GL_LIGHT_MODEL_AMBIENT, intensidad, 1);
    }
    

}
