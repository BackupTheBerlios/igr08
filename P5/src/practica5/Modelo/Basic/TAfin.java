package practica5.Modelo.Basic;

import javax.media.opengl.GL;
//http://www.tecnun.es/asignaturas/grafcomp/presentaciones/transformaciones.ppt
import practica5.util.Conversiones;
import practica5.util.Matrix;
public class TAfin {

    // Atributos privados
    private GL gl;
    public double[] matriz;
    private static double EJE_SEL = 1.0;

    // Constructora
    public TAfin() {
	// Trabajamos con el formato de matriz equivalente a openGL
	matriz = new double[16];
    }

    public void trasladar1(double X, double Y, double Z) {
        double[] m = new double[16];
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        m[12]=X;
        m[13]=Y;
        m[14]=Z;
	/*this.setMatrizComponent(12, X);
	this.setMatrizComponent(13, Y);
	this.setMatrizComponent(14, Z);*/
       matriz = Matrix.multiplica(matriz,m);
       
    }
public void rotaXM(double ang){
        double[] m = new double[16];
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        double c = Math.cos(Conversiones.g2r(ang));
	double s = Math.sin(Conversiones.g2r(ang));
        m[5]=c;
        m[6]=s;
        m[9]=-s;
        m[10]=c;
//	this.setMatrizComponent(5, c);
//	this.setMatrizComponent(6, s);
//	this.setMatrizComponent(9, -s);
//	this.setMatrizComponent(10, c);
        matriz = Matrix.multiplica(matriz,m);
}

public void rotaYM(double ang){
        double[] m = new double[16];
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        double c = Math.cos(Conversiones.g2r(ang));
	double s = Math.sin(Conversiones.g2r(ang));
        m[0]=c;
        m[2]=-s;
        m[8]=s;
        m[10]=c;
//        this.setMatrizComponent(0, c);
//	this.setMatrizComponent(2, -s);
//	this.setMatrizComponent(8, s);
//	this.setMatrizComponent(10, c);
        matriz = Matrix.multiplica(matriz,m);
}



public void rotaZM(double ang){
        double[] m = new double[16];
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        double c = Math.cos(Conversiones.g2r(ang));
	double s = Math.sin(Conversiones.g2r(ang));
        m[0]=c;
        m[1]=s;
        m[4]=-s;
        m[5]=c;
//      this.setMatrizComponent(0, c);
//	this.setMatrizComponent(1, s);
//	this.setMatrizComponent(4, -s);
//	this.setMatrizComponent(5, c);
        matriz = Matrix.multiplica(matriz,m);
}
    public void escalaX(double s) {
	this.setMatrizComponent(0, s);
    }

    public void escalaY(double s) {
	this.setMatrizComponent(5, s);
    }

    public void escalaZ(double s) {
	this.setMatrizComponent(10, s);
    }

    public void escalaXYZ(double s) {
	escalaX(s);
	escalaY(s);
	escalaZ(s);
    }

    public void rotaX(double ang) {
        
        double a = ang * 2 * Math.PI / 360;
        
        double c = Math.cos(Conversiones.r2g(ang));
	double s = Math.sin(Conversiones.r2g(ang));
        
	//double c = Math.cos(Conversiones.r2g(ang));
	//double s = Math.sin(Conversiones.r2g(ang));

        this.setMatrizComponent(5, c);
	this.setMatrizComponent(6, s);
	this.setMatrizComponent(9, -s);
	this.setMatrizComponent(10, c);
        
	
    }

    public void rotaY(double ang) {
	double c = Math.cos(Conversiones.r2g(ang));
	double s = Math.sin(Conversiones.r2g(ang));
	this.setMatrizComponent(0, c);
	this.setMatrizComponent(2, -s);
	this.setMatrizComponent(8, s);
	this.setMatrizComponent(10, c);
    }

    public void rotaZ(double ang) {
	double c = Math.cos(Conversiones.r2g(ang));
	double s = Math.sin(Conversiones.r2g(ang));
	this.setMatrizComponent(0, c);
	this.setMatrizComponent(1, s);
	this.setMatrizComponent(4, -s);
	this.setMatrizComponent(5, c);
    }
/////////////////////////////////////////////////////
    // Transformaciones Afines
    public void rotar(double ang, double ejeX, double ejeY, double ejeZ) {
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glRotated(ang, ejeX, ejeY, ejeZ);
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
    }

    public void trasladar(double X, double Y, double Z) {
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glTranslated(X, Y, Z);
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
	for (int i = 0; i < matriz.length; i++) {
	    System.out.println(matriz[i]);
	}
    }

    public void escalar(double X, double Y, double Z) {
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glScaled(X, Y, Z);
	gl.glMultMatrixd(matriz, 0);
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
    }

    public void rotarM(double ang, double ejeX, double ejeY, double ejeZ) {
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glRotated(ang, ejeX, ejeY, ejeZ);
	gl.glMultMatrixd(matriz, 0);
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
    }
    
    public void trasladarM(double X, double Y, double Z) {
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glTranslated(X, Y, Z);
	gl.glMultMatrixd(matriz, 0);
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
    }

    public void escalarM(double X, double Y, double Z) {
	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glScaled(X, Y, Z);
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
    }

    /*
    public void rotarN(double ang, double ejeX, double ejeY, double ejeZ){
    gl.glMatrixMode(gl.GL_MODELVIEW);
    // gl.glLoadIdentity();  //??
    gl.glRotated(ang, ejeX, ejeY, ejeZ);
    gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    public void trasladarN(double X, double Y, double Z){
    gl.glMatrixMode(gl.GL_MODELVIEW);
    // gl.glLoadIdentity(); //??
    gl.glTranslated(X, Y, Z);
    gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    public void escalarN(double X, double Y, double Z){
    gl.glMatrixMode(gl.GL_MODELVIEW);
    //gl.glLoadIdentity();  //??
    gl.glScaled(X, Y, Z);
    gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
     */
    public void setGL(GL gl) {
	this.gl = gl;

	gl.glMatrixMode(GL.GL_MODELVIEW);
	gl.glPushMatrix();
	gl.glLoadIdentity();
	gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
	gl.glPopMatrix();
    }

    // Getters & Setters
    public double[] getMatriz() {
	return matriz;
    }

    public void setMatriz(double[] m) {
	this.matriz = m;
    }

    public void setMatrizComponent(int indice, double valor) {
	matriz[indice] = valor;
    }
    
    public void addtMatrizComponent(int indice, double valor) {
	matriz[indice] += valor;
    }

    public void setIdentity() {
	setZeroMatrix();
	
	matriz[0] = 1;
	matriz[5] = 1;
	matriz[10] = 1;
	matriz[15] = 1;
    }
    
    public void setZeroMatrix(){
    	for (int i = 0; i < 16; i++) {
	    this.matriz[i] = 0;
	}
    }
    /**
     * @param fovy Specifies the field of view angle, in degrees, in the y direction. 
     * @param aspect Specifies the aspect ratio that determines the field of view in the x direction. The aspect ratio is the ratio of x (width) to y (height). 
     * @param zNear Specifies the distance from the viewer to the near clipping plane (always positive). 
     * @param zFar Specifies the distance from the viewer to the far clipping plane (always positive). 
     * @return
     */
    public double[] getPerspectiveMatrix(double fovy,double aspect, double zNear, double zFar){
	// http://pyopengl.sourceforge.net/documentation/manual/gluPerspective.3G.html
	
	double f = Math.atan(fovy/2);
	
	setZeroMatrix();
	
	setMatrizComponent(0, f/aspect);
	setMatrizComponent(5, f);
	setMatrizComponent(10, (zFar+zNear)/(zNear-zFar));
	setMatrizComponent(11, -1);
	setMatrizComponent(14, (2*zFar*zNear)/(zNear-zFar));
	
	return matriz;    
    }

    public String imprime() {
	String cad = "";
	for (int i = 0; i < matriz.length; i++) {
	    cad += matriz[i]+"**";
	}

	return cad;
    }
}

