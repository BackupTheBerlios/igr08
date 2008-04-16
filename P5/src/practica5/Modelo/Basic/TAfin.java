package practica5.Modelo.Basic;

import javax.media.opengl.GL;

public class TAfin {
    
    // Atributos privados
    private GL gl;
    private  double[] matriz;
    
    // Constructora
    public TAfin() {
        // Trabajamos con el formato de matriz equivalente a openGL 
        matriz = new double[16];
    }
    
    // Transformaciones Afines
    public void rotar(double ang, double ejeX, double ejeY, double ejeZ){
        gl.glLoadIdentity();  //??
        gl.glRotated(ang, ejeX, ejeY, ejeZ);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void trasladar(double X, double Y, double Z){
        gl.glLoadIdentity();  //??
        gl.glTranslated(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void escalar(double X, double Y, double Z){
        gl.glLoadIdentity();  //??
        gl.glScaled(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
}

