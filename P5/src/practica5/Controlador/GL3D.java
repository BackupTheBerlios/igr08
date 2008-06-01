package practica5.Controlador;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import practica5.Modelo.Basic.Color;

import practica5.Modelo.Basic.*;
import practica5.Modelo.Luces.*;
import practica5.Modelo.Objetos.Habitaciones;

public class GL3D implements GLEventListener {
    
    // Constantes
    public static final int PROY_ORTOGONAL = 0;
    public static final int PROY_PERSPECTIVA = 1;
    public static final int PROY_OBLICUA = 2;
    public static final int CAMARA_PERSONA = 3;
    public static final int VISTA_FRONTAL = 1;
    public static final int VISTA_LATERAL = 2;
    public static final int VISTA_CENITAL = 3;
    public static final int VISTA_POR_DEFECTO = 4;
    
    private static final int difuseAmbientLight1Number = GL.GL_LIGHT0;
    private static final int difuseAmbientLight2Number = GL.GL_LIGHT1;
    private static final int spotLightNumber = GL.GL_LIGHT2;
    
    // Atributos privados
    private GL gl;
    private GLContext context;
    private double xLeft,  xRight;
    private double yTop,  yBot;
    private double xCentro,  yCentro;
    private int anchura;
    private int altura;
    private Camara camaraActual;
    private Camara camaraSecundaria;
    private double RatioViewPort;
    
    private ObjetoCompuesto3D hab;
    private int perspectiva = PROY_ORTOGONAL;
    private int vista;
    private Texture[] texturas;
    public double desplPersonaX = 0;
    public double desplPersonaY = 0;
    public double desplPersonaZ = 0;
    private boolean luzAmbiente1 = true;
    private boolean luzAmbiente2 = true;
    private boolean luzLampara = true;
    private float[] PosicionLuz0 = new float[4];
                
    public GL3D(int anchura, int altura) {
        //this.glu = new GLU();
        
        this.anchura = anchura;
        this.altura = altura;
        this.xRight = anchura / 2.0;
        this.xLeft = -xRight;
        this.yTop = altura / 2.0;
        this.yBot = -xRight;
        this.xCentro = (xRight + xLeft) / 2.0;
        this.yCentro = (yTop + yBot) / 2.0;
        this.RatioViewPort = 1.0;
    }
    
    public void display(GLAutoDrawable drw) {
        
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        
        switch (vista) {
            case VISTA_FRONTAL:
                hab.getMatriz().setIdentity();
                camaraActual.reset(new PuntoVector3D(400, 0, 0), new PuntoVector3D(0, 0, 0));
                break;
            case VISTA_LATERAL:
                hab.getMatriz().setIdentity();
                camaraActual.reset(new PuntoVector3D(0, 0, 400), new PuntoVector3D(0, 0, 0));
                break;
            case VISTA_CENITAL:
                hab.getMatriz().setIdentity();
                camaraActual.reset(new PuntoVector3D(0, 800, 0.001), new PuntoVector3D(0, 0, 0));
                break;
            case VISTA_POR_DEFECTO:
                hab.getMatriz().setIdentity();
                camaraActual.reset(new PuntoVector3D(500, 500, -250), new PuntoVector3D(0, 0, 0));
                break;
        }
        vista = 0;
        // Tipo de proyeccion
        switch (perspectiva) {
            case PROY_ORTOGONAL:
                camaraActual.setOrtogonal();
                break;
            case PROY_OBLICUA:
                camaraActual.setOblicua(new PuntoVector3D(1, 2, 3));
                break;
            case PROY_PERSPECTIVA:
                camaraActual.setPerspective();
                break;
            case CAMARA_PERSONA:
                camaraActual.setPersona();
                break;
        }
        perspectiva = -1;
        
        camaraActual.setModelViewMatrix();
        
        if (luzAmbiente1) {
            gl.glEnable(difuseAmbientLight1Number);
        } else {
            gl.glDisable(difuseAmbientLight1Number);
        }
	
	if (luzAmbiente2) {
            gl.glEnable(difuseAmbientLight2Number);
        } else {
            gl.glDisable(difuseAmbientLight2Number);
        }
	
	if (luzLampara){
	    gl.glDisable(spotLightNumber);
	}
	else{
	    gl.glEnable(spotLightNumber);
	}
        
        hab.dibuja(gl);
        
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable drw, boolean arg1, boolean arg2) {
    }
    
    public void init(GLAutoDrawable drw) {
        //context = drw.getContext();
        //context.makeCurrent();
        //gl = context.getGL();
        gl = drw.getGL();
        //glu = new GLU();
        
        // this.camaraActual = new Camara(gl, glu);
        this.camaraActual = new Camara(new PuntoVector3D(500, 500, -250), new PuntoVector3D(0, 0, 0), new PuntoVector3D(0, 1, 0), gl);
        this.camaraSecundaria = new Camara(new PuntoVector3D(300, 300, 300), new PuntoVector3D(0, 0, 0), new PuntoVector3D(0, 1, 0), gl);
        this.camaraActual.setOrtogonal();
        
        activarLuces(gl);
        activarOpcionesOpenGL(gl);
        
        texturas = new Texture[Malla.nombreTexturas.length];
        for (int i = 0; i < Malla.nombreTexturas.length; i++) {
            texturas[i] = TextureIO.newTexture(Cargar_Imagen("practica5/Images/" + Malla.nombreTexturas[i]), true);
        }
        
        PuntoVector3D pos = new PuntoVector3D(0, 0, 0, 1);
        
        hab = new Habitaciones(pos, texturas, spotLightNumber);
        hab.setId(Objeto3D.ESCENA);
        hab.setColor(Color.verdeClaro);
        
//        activarLuces(gl);
//        activarOpcionesOpenGL(gl);
        
        gl.glClearColor(0, 0, 0, 1);
        drw.setAutoSwapBufferMode(true);
        
    }
    
    public void reshape(GLAutoDrawable drw, int x, int y, int width, int height) {
        RatioViewPort = (double) width / (double) height;
        double RatioVolVista = (xRight - xLeft) / (yTop - yBot);
        
        if (RatioVolVista >= RatioViewPort) {
            //Aumentamos yTop-yBot
            double altoNew = (xRight - xLeft) / RatioViewPort;
            yTop = yCentro + altoNew / 2.0;
            yBot = yCentro - altoNew / 2.0;
        } else {
            //Aumentamos xRight-xLeft
            double anchoNew = RatioViewPort * (yTop - yBot);
            xRight = xCentro + anchoNew / 2.0;
            xLeft = xCentro - anchoNew / 2.0;
        }
        
        //GL gl = drw.getGL();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        //gl.glOrtho(xLeft, xRight, yBot, yTop, -1000.0f, 1000.0f);
        camaraActual.setOrtogonal();
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        display(drw);
    }
    
//    public GLContext getContext() {
//        return context;
//    }
    
    // Activamos las luces del entorno
    public void activarLuces(GL gl) {
       
        // Activamos Luz en OpenGL
        gl.glEnable(gl.GL_LIGHTING);

        // Luz0: Luz Ambiente
        gl.glEnable(difuseAmbientLight1Number);
             
        float[] LuzDifusa1={1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(difuseAmbientLight1Number, gl.GL_DIFFUSE, LuzDifusa1, 0);
        
        float[] LuzAmbiente1={0.3f, 0.3f, 0.3f, 1.0f};
        gl.glLightfv(difuseAmbientLight1Number, gl.GL_AMBIENT, LuzAmbiente1, 0);
        
        PosicionLuz0[0]=0.0f; PosicionLuz0[1]= 500.0f;
        PosicionLuz0[2]=200.0f; PosicionLuz0[3]= 1.0f;
        gl.glLightfv(difuseAmbientLight1Number, gl.GL_POSITION, PosicionLuz0, 0);
        
	
	// Luz1: Luz Ambiente
        gl.glEnable(difuseAmbientLight2Number);
             
        float[] LuzDifusa2={1.0f, 1.0f, 1.0f, 1.0f};
        gl.glLightfv(difuseAmbientLight2Number, gl.GL_DIFFUSE, LuzDifusa2, 0);
        
        float[] LuzAmbiente2={0.3f, 0.3f, 0.3f, 1.0f};
        gl.glLightfv(difuseAmbientLight2Number, gl.GL_AMBIENT, LuzAmbiente2, 0);
        
        PosicionLuz0[0]=0.0f; PosicionLuz0[1]= -200.0f;
        PosicionLuz0[2]=200.0f; PosicionLuz0[3]= 1.0f;
        gl.glLightfv(difuseAmbientLight2Number, gl.GL_POSITION, PosicionLuz0, 0);
        
        // luz Focal
	gl.glEnable(spotLightNumber);

    }
    
    // Activamos opciones internas de OpenGL
    public void activarOpcionesOpenGL(GL gl) {
        
        gl.glEnable(gl.GL_COLOR_MATERIAL);
        gl.glColorMaterial(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT_AND_DIFFUSE);
        gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, 0.1f);
	
        /*
        gl.glEnable(gl.GL_COLOR_MATERIAL);
        //gl.glMaterialf(gl.GL_FRONT_AND_BACK, gl.GL_SHININESS, 0.1f);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_NORMALIZE); // Normalizar las normales, no seria necesario?
        gl.glFrontFace(gl.GL_CCW);
        //gl.glFrontFace(gl.GL_CW);
        gl.glEnable(gl.GL_FRONT_FACE);
        //gl.glEnable(gl.GL_CULL_FACE);
        gl.glShadeModel(gl.GL_SMOOTH);   // Modelo de sombreado suave
         **/

        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_NORMALIZE);
        gl.glShadeModel(gl.GL_SMOOTH); // Modelo de sombreado suave
    }
    
    public ObjetoCompuesto3D getObjeto3D() {
        return hab;
    }
    
    public Camara getCamara() {
        return this.camaraActual;
    }
    
    public void cambiaCamara() {
        Camara aux = camaraActual;
        camaraActual = camaraSecundaria;
        camaraSecundaria = aux;
    }
    
    public void setVista(int a) {
        vista = a;
    }
    
    public void setPerspectiva(int tipo) {
        
        this.perspectiva = tipo;
        
        switch (perspectiva) {
            
//            case PROY_ORTOGONAL: camaraActual.setPuntoMira(new PuntoVector3D(100, 100, 100));
//                                break;
//
//            case PROY_OBLICUA:   camaraActual.setPuntoMira(new PuntoVector3D(100, 120, 0));
//                                 break;
//
//            case PROY_PERSPECTIVA: camaraActual.setPuntoMira(new PuntoVector3D(100, 120, 300));
//                                   break;
//
            case CAMARA_PERSONA:
                camaraActual.setPuntoMira(new PuntoVector3D(250 + desplPersonaX, 120 + desplPersonaY, 300 + desplPersonaZ));
                //camaraActual.reset(new PuntoVector3D(250+desplPersonaX, 120+desplPersonaY, 300+desplPersonaZ), new PuntoVector3D(0,0,0));
                break;
        }
    }
    
    public void cambiaLuzLampara() {
        this.luzLampara = !luzLampara;
    }
    
    public void cambiaLuzAmbiente1(){
        this.luzAmbiente1 = !luzAmbiente1;
    }
    
    public void cambiaLuzAmbiente2(){
	this.luzAmbiente2 = !luzAmbiente2;
    }
    
    public BufferedImage Cargar_Imagen(String nombre) {
        
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource(nombre);
            
            return ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen " + nombre + " de " + url);
            System.out.println("El error fue : " + e.getClass().getName() + " " + e.getMessage());
            System.exit(0);
            return null;
        }
    }
}
