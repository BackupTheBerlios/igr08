package practica5.Controlador;

import java.nio.FloatBuffer;
import java.security.Principal;
import java.util.ArrayList;
import javax.media.opengl.*;
import javax.media.opengl.GLContext;
import javax.media.opengl.glu.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import practica5.Modelo.Basic.Color;

import practica5.Modelo.Basic.*;
import practica5.Modelo.Objetos.Habitaciones;
import practica5.Modelo.Objetos.Toro;
import practica5.util.Calculos;

public class GL3D implements GLEventListener {

    // Constantes
    public static final int PROY_ORTOGONAL = 0;
    public static final int PROY_PERSPECTIVA = 1;
    public static final int PROY_OBLICUA = 2;
    
    
    // Atributos privados
    private GL gl;
    private GLU glu;
    private GLContext context;
    private double xLeft,  xRight;
    private double yTop,  yBot;
    private double xCentro,  yCentro;
    private int anchura;
    private int altura;
    private Camara camaraActual;
    private Camara camaraSecundaria;
    private double RatioViewPort;
    private float[] PosicionLuz0 = new float[4];
    private ObjetoCompuesto3D hab;
    private int perspectiva = PROY_ORTOGONAL;


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
	double l = -504;
	double r = 504;
	double b = -400;
	double t = 300;
	double N = -1000;
	double F = 800;

	switch (perspectiva) {
	    case PROY_ORTOGONAL:
		gl.glMatrixMode(gl.GL_PROJECTION);
		gl.glLoadIdentity();   
		gl.glOrtho(l, r, b, t, N, F);
		break;
	    case PROY_OBLICUA:
		gl.glMatrixMode(gl.GL_PROJECTION);
		gl.glLoadIdentity();
		PuntoVector3D d = new PuntoVector3D(1, 2, 3);
		gl.glOrtho(l, r, b, t, N, F);
		if (d.getZ() != 0 & d != new PuntoVector3D(0, 0, 1)) {
		    TAfin matriz = new TAfin();
		    matriz.setIdentity();
		    matriz.setMatrizComponent(8, -d.getX() / d.getZ());
		    matriz.setMatrizComponent(9, -d.getY() / d.getZ());
		    gl.glMultMatrixd(matriz.getMatriz(), 0);
		}
		break;
	    case PROY_PERSPECTIVA:
				gl.glMatrixMode(gl.GL_PROJECTION);
		gl.glLoadIdentity();
		double anguloVision = 40;
		double proporcion = r - l / t - b;

		TAfin a = new TAfin();
		double[] m = a.getPerspectiveMatrix(anguloVision, proporcion, N, F);
		gl.glLoadMatrixd(m, 0);
		//glu.gluPerspective(anguloVision, proporcion, N, F);
		break;
	}






	//////////////////////////////////////////////////////////////////////

	gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);

	camaraActual.setModelViewMatrix();

	hab.dibuja(gl);

	gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drw, boolean arg1, boolean arg2) {
    }

    public void init(GLAutoDrawable drw) {
	context = drw.getContext();
	context.makeCurrent();
	gl = context.getGL();
	//gl = drw.getGL();
	glu = new GLU();

	this.camaraActual = new Camara(gl, glu);
	this.camaraSecundaria = new Camara(new PuntoVector3D(100, 100, 100), new PuntoVector3D(0, 0, 0), new PuntoVector3D(0, 1, 0), gl);
	this.activarLuces(gl);
	this.activarOpcionesOpenGL(gl);

	hab = new Habitaciones(gl);
	hab.setId(Objeto3D.ESCENA);
	hab.setGL(gl);
	//  hab.getMatriz().trasladar(-150.0, -500.0, 0.35);
	hab.setColor(Color.verdeClaro);

	activarLuces(gl);
	activarOpcionesOpenGL(gl);

	gl.glClearColor(0, 0, 0, 1);
	drw.setAutoSwapBufferMode(true);
    /*        
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    //glu.gluOrtho2D(xLeft, xRight, yBot, yTop);
    //glu.gluPerspective(90.0, xRight, 1.0, 20.0);
    //gl.glOrtho(xLeft, xRight, yBot, yTop, -100.0f, 100.0f);
    gl.glMatrixMode(GL.GL_MODELVIEW);
    gl.glLoadIdentity(); */
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
//	gl.glOrtho(xLeft, xRight, yBot, yTop, -1000.0f, 1000.0f);
	camaraActual.setProjection(GL3D.PROY_ORTOGONAL);

	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glLoadIdentity();
	display(drw);
    }

    public GLContext getContext() {
	return context;
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
	FloatBuffer PosicionLuz01 = FloatBuffer.wrap(PosicionLuz0);
	gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, PosicionLuz01);
    }

    // Activamos opciones internas de OpenGL
    public void activarOpcionesOpenGL(GL gl) {

	gl.glEnable(gl.GL_COLOR_MATERIAL);
	gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, 0.1f);
	gl.glEnable(gl.GL_DEPTH_TEST);
	gl.glEnable(gl.GL_NORMALIZE);
	//gl.glFrontFace(gl.GL_CCW);
	//gl.glFrontFace(gl.GL_CW);
	//gl.glEnable(gl.GL_FRONT_FACE);
	//gl.glEnable(gl.GL_CULL_FACE);
	gl.glShadeModel(gl.GL_SMOOTH);   //defecto
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
    
    public void setPerspectiva(int tipo){
	this.perspectiva = tipo;
    }
}
