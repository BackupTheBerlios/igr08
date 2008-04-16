package practica5.Controlador;

import java.nio.FloatBuffer;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import practica5.Modelo.Basic.Camara;
import practica5.Modelo.Basic.Malla;
import practica5.Modelo.Objetos.Toro;
import practica5.Modelo.Basic.PuntoVector3D;

public class GL3D implements GLEventListener {
    
    // Variables Constantes
    public static int ORTOGONAL = 0;
    public static int PROYECCION = 1;
    
    // Atributos privado
    private GLU glu;
    private GLContext context;
    
    private double xLeft, xRight;
    private double yTop, yBot;
    private double xCentro, yCentro;
    
    private int anchura;
    private int altura;
    
    private Camara camara;
    private double RatioViewPort;
    private boolean generado;
    
    private static float[] POS_LUZ_0 = {25.0f, 25.0f, 0.0f, 1.0f};
    
    public GL3D(int anchura, int altura) {
        
        this.glu = new GLU();
        
        this.anchura = anchura;
        this.altura = altura;
        
        this.xRight = anchura / 2.0;
        this.xLeft = -xRight;
        this.yTop = altura / 2.0;
        this.yBot = -xRight;
        this.xCentro = (xRight + xLeft) / 2.0;
        this.yCentro = (yTop + yBot) / 2.0;
        
        this.RatioViewPort = 1.0;
        this.generado = false;
        
    }
    
    public void display(GLAutoDrawable drw) {
        GL gl = drw.getGL();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_COLOR_BUFFER_BIT);
        
        gl.glColor3d(100.0, 100.0, 250.0);
        pruebas(gl);
        
        if (generado) {
            //this.mallaActual.dibuja(gl);
        }
        
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {}
    
    public void init(GLAutoDrawable drw) {
        GL gl = drw.getGL();
        GLU glu = new GLU();
        
        //gl.glClearColor(0.6f, 0.7f, 0.8f, 1.0f);
        
        this.camara = new Camara(gl);
        this.activarLuces(gl);
        this.activarOpcionesOpenGL(gl);
        
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluLookAt(camara.getEye().getX(), camara.getEye().getY(), camara.getEye().getZ(),
                      camara.getLook().getX(), camara.getLook().getY(), camara.getLook().getZ(),
                      camara.getUp().getX(), camara.getUp().getY(), camara.getUp().getZ());
        
        
        gl.glClearColor(0, 0, 0, 0);
        
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        
        //glu.gluOrtho2D(xLeft, xRight, yBot, yTop);
        //glu.gluPerspective(45.0, xRight, 1.0, 20.0);
        gl.glOrtho(xLeft, xRight, yBot, yTop, -100.0f, 100.0f);
        
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
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
        
        GL gl = drw.getGL();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(xLeft, xRight, yBot, yTop, -1000.0f, 1000.0f);
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        display(drw);
    }
    
    public GLContext getContext() {
        return context;
    }
        
    // M�todo que dibuja los puntos de control del perfil
    public void dibujarPuntos(GL gl) {
        
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPointSize(2.0f);
        
        gl.glBegin(GL.GL_POINTS);
        
        gl.glEnd();
    }
    
    // Activamos las luces del entorno
    public void activarLuces(GL gl) {
        
        // Activamos Luz en OpenGL
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_LIGHT0);
        
        // Luz 0
        gl.glEnable(gl.GL_LIGHT0);
        float LuzDifusa[] = {1.0f, 1.0f, 1.0f, 1.0f};
        FloatBuffer LuzDifusa1 = FloatBuffer.wrap(LuzDifusa);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, LuzDifusa1);
        
        // Luz Ambiental
        float LuzAmbiente[] = {0.5f, 0.5f, 0.5f, 1.0f};
        FloatBuffer LuzAmbiente1 = FloatBuffer.wrap(LuzAmbiente);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, LuzAmbiente1);
        FloatBuffer PosicionLuz01 = FloatBuffer.wrap(POS_LUZ_0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, PosicionLuz01);
    }
    
    // Activamos opciones internas de OpenGL
    public void activarOpcionesOpenGL(GL gl) {
       
        gl.glEnable(gl.GL_COLOR_MATERIAL);
        gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, 0.1f);
        //gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(gl.GL_NORMALIZE);
        //gl.glFrontFace(gl.GL_CCW);
        //gl.glFrontFace(gl.GL_CW);
        //gl.glEnable(gl.GL_FRONT_FACE);
        //gl.glEnable(gl.GL_CULL_FACE);
        gl.glShadeModel(gl.GL_SMOOTH);   //defecto
    }
    
    // Getters & Setters
    public boolean getGenerado() {
        return generado;
    }
    
    public void setGenerado(boolean bool) {
        generado = bool;
    }
    
    public Camara getCamara() {
        return this.camara;
    }
    
    public void setCamara(Camara c) {
        this.camara = c;
    }
    
    
    // Escala un punto desde el puerto de vista hasta el area visible de la escena
    public PuntoVector3D convertirPuntoToPixel(PuntoVector3D punto) {
        
        PuntoVector3D pixel = new PuntoVector3D();
        
        double escalaAncho = (double) (anchura / (xRight - xLeft));
        if (xLeft < 0) {
            pixel.setX((double) (punto.getX() / escalaAncho + xLeft));
        } else {
            pixel.setX((double) (punto.getX() / escalaAncho - xLeft));
        }
        
        double escalaAlto = (double) (altura / (yTop - yBot));
        if (yTop < 0) {
            pixel.setY((double) (punto.getY() / escalaAlto + yTop));
        } else {
            pixel.setY((float) (punto.getY() / escalaAlto - yTop));
        }
        
        pixel.setZ(punto.getZ());
        pixel.setPV(punto.getPV());
        
        return pixel;
    }
    
    // M�todo que convierte las coordenadas del perfil a coordenadas del Area Visible de la Escena
    public ArrayList<PuntoVector3D> transformarPerfil(ArrayList<PuntoVector3D> perfilDado) {
        
        ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
        
        for (int i = 0; i < perfilDado.size(); i++) {
            retVal.add(convertirPuntoToPixel(perfilDado.get(i)));
        }
        
        return retVal;
    }
    
    // PRUEBA
    public void pruebas(GL gl) {
        Malla mallaToro = new Toro(25,36,180.5f,90.0f);
        mallaToro.dibuja(gl);
    }
    
}
