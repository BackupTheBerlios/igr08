package practica4.Controlador;

import java.nio.FloatBuffer;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import java.util.ArrayList;
import practica4.Modelo.Malla;
import practica4.Modelo.MallaPorRevolucion;
import practica4.Modelo.PuntoVector3D;
import practica4.Modelo.Espiral;

public class GL3D implements GLEventListener {

    private double xLeft,  xRight,  yTop,  yBot,  xCentro,  yCentro;
    private double RatioViewPort;
    private GLU glu;
    private GLContext context;
    private int anchura;
    private int altura;
    private ArrayList<PuntoVector3D> perfil;
    private boolean generado;
    private int tipo;
    private MallaPorRevolucion mallaPorRevolucion;
    private Espiral mallaPorExtrusion;
    private Malla mallaActual;
    double eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ; // cámara
    float[] PosicionLuz0 = new float[4];

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

	this.tipo = 0;
	this.generado = false;
	this.mallaPorRevolucion = null;
    }

    public void display(GLAutoDrawable drw) {
	GL gl = drw.getGL();

	gl.glClear(GL.GL_COLOR_BUFFER_BIT);

	if (generado) // Tipo de Escena a dibujar
	{
	    switch (tipo) {
		case 0:
		    dibujaMallaPorRevolucion(gl);
		    break;
		case 1:
		    dibujaMallaPorExtrusion(gl);
		    break;
		case 2:
		    dibujaMallaPorRevolucion(gl);
		    break;
		case 3:
		    mallaActual.dibuja(gl, 0);
		    break;
	    }
	} else {
	    dibujarPuntos(gl);
	}

	gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
    }

    public void init(GLAutoDrawable drw) {
	GL gl = drw.getGL();
	GLU glu = new GLU();
	/**
	 * 
	 */
	gl.glClearColor(0.6f, 0.7f, 0.8f, 1.0f);
	gl.glEnable(gl.GL_LIGHTING);
	gl.glEnable(gl.GL_LIGHT0);
	// luz0
	gl.glEnable(gl.GL_LIGHT0);
	float LuzDifusa[] = {1.0f, 1.0f, 1.0f, 1.0f};
	FloatBuffer LuzDifusa1 = FloatBuffer.wrap(LuzDifusa);

	gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, LuzDifusa1);


	float LuzAmbiente[] = {0.5f, 0.5f, 0.5f, 1.0f};
	FloatBuffer LuzAmbiente1 = FloatBuffer.wrap(LuzAmbiente);
	gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, LuzAmbiente1);
	PosicionLuz0[0] = 25.0f;
	PosicionLuz0[1] = 25.0f;
	PosicionLuz0[2] = 0.0f;
	PosicionLuz0[3] = 1.0f;

	FloatBuffer PosicionLuz01 = FloatBuffer.wrap(PosicionLuz0);
	gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, PosicionLuz01);

	gl.glEnable(gl.GL_COLOR_MATERIAL);
	gl.glMaterialf(gl.GL_FRONT, gl.GL_SHININESS, 0.1f);
	//gl.glEnable(gl.GL_DEPTH_TEST);
	gl.glEnable(gl.GL_NORMALIZE);
	gl.glShadeModel(gl.GL_SMOOTH);   //defecto

	// cámara
	eyeX = 2.0;
	eyeY = 2.0;
	eyeZ = 2.0;
	lookX = 0.0;
	lookY = 0.0;
	lookZ = 0.0;
	upX = 0;
	upY = 1;
	upZ = 0;
	gl.glMatrixMode(gl.GL_MODELVIEW);
	gl.glLoadIdentity();
	glu.gluLookAt(eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

	/**
	 * 
	 */
	gl.glClearColor(0, 0, 0, 0);

	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();

	//glu.gluOrtho2D(xLeft, xRight, yBot, yTop);
	//glu.gluPerspective(45.0, xRight, 1.0, 20.0);
	gl.glOrtho(xLeft, xRight, yBot, yTop, -1.0f, 10.0f);


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

    public void zoom(GLAutoDrawable drw, double factor) {
	double newAncho = (xRight - xLeft) * factor;
	double newAlto = (yTop - yBot) * factor;
	xRight = xCentro + newAncho / 2.0;
	xLeft = xCentro - newAncho / 2.0;
	yTop = yCentro + newAlto / 2.0;
	yBot = yCentro - newAlto / 2.0;

	GL gl = drw.getGL();

	gl.glMatrixMode(GL.GL_PROJECTION);
	gl.glLoadIdentity();
	glu.gluOrtho2D(xLeft, xRight, yBot, yTop);

    }

    // Método que dibuja los puntos de control del perfil
    public void dibujarPuntos(GL gl) {

	gl.glColor3f(1.0f, 1.0f, 1.0f);
	gl.glPointSize(2.0f);

	gl.glBegin(GL.GL_POINTS);

	for (int i = 0; i < perfil.size(); i++) {
	    gl.glVertex3f((float) perfil.get(i).getX(), (float) perfil.get(i).getY(), (float) perfil.get(i).getZ());
	}

	gl.glEnd();
    }

    // Método que dibuja Malla por Revolucion
    public void dibujaMallaPorRevolucion(GL gl) {

	mallaPorRevolucion.dibujaMallaPorRevolucion(gl);
    /*
    //gl.glTranslatef(-1.5f, 0.0f, -6.0f);
    gl.glBegin(GL.GL_TRIANGLES);		    
    gl.glColor3f(1.0f, 0.0f, 0.0f);   
    gl.glVertex3f(0.0f, 50.0f, 0.0f);  
    gl.glColor3f(0.0f, 1.0f, 0.0f);   
    gl.glVertex3f(-50.0f, -50.0f, 0.0f);
    gl.glColor3f(0.0f, 0.0f, 1.0f);    
    gl.glVertex3f(50.0f, -50.0f, 0.0f); 
    gl.glEnd();
     */
    }

    // Método que dibuja Malla por Extrusión
    public void dibujaMallaPorExtrusion(GL gl) {

	gl.glColor3f(0.5f, 0.5f, 1.0f);
	//gl.glTranslatef(3.0f, 0.0f, 0.0f);

	this.mallaPorExtrusion.dibuja(gl, 0);
    /*gl.glBegin(GL.GL_QUADS);
    gl.glVertex3f(-50.0f, 50.0f, 0.0f);  
    gl.glVertex3f(50.0f, 50.0f, 0.0f);  
    gl.glVertex3f(50.0f, -50.0f, 0.0f);  
    gl.glVertex3f(-50.0f, -50.0f, 0.0f); 
    gl.glEnd();       */
    }

    // Getters & Stters
    public int getTipo() {
	return tipo;
    }

    public void setTipo(int t) {
	this.tipo = t;
    }

    public ArrayList<PuntoVector3D> getPerfil() {
	return perfil;
    }

    public void setPerfil(ArrayList<PuntoVector3D> puntosPerfil) {
	this.perfil = (ArrayList<PuntoVector3D>) puntosPerfil.clone();
    }

    public boolean getGenerado() {
	return generado;
    }

    public void setGenerado(boolean bool) {
	generado = bool;
    }

    // Métodos que actualiza datos de la escena
    public void actualizarPerfil(int tipoMalla, ArrayList<PuntoVector3D> puntosPerfil) {
	this.tipo = tipoMalla;
	this.perfil = (ArrayList<PuntoVector3D>) puntosPerfil.clone();
    }

    public void actualizarMallaRev(int tipoMalla, MallaPorRevolucion mallaPorRevolucion) {
	this.tipo = tipoMalla;
	this.mallaPorRevolucion = mallaPorRevolucion;
    }

    public void actualizarMallaExt(int tipoMalla, Espiral mallaPorExtrusion) {
	this.tipo = tipoMalla;
	this.mallaPorExtrusion = mallaPorExtrusion;
    }

    public void actualizarMalla(int tipoMalla, Malla laMalla) {
	this.tipo = tipoMalla;
	this.mallaActual = laMalla;
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

    // Método que convierte las coordenadas del perfil a coordenadas del Area Visible de la Escena
    public ArrayList<PuntoVector3D> transformarPerfil(ArrayList<PuntoVector3D> perfilDado) {

	ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();

	for (int i = 0; i < perfilDado.size(); i++) {
	    retVal.add(convertirPuntoToPixel(perfilDado.get(i)));
	}

	return retVal;
    }
}
