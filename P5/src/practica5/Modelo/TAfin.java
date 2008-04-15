package practica5.Modelo;

import javax.media.opengl.GL;

public class TAfin {
    
    private  double[] matriz;
    private GL gl;
    
    public TAfin() {
        matriz = new double[16];
    }
    
    public void gira(double ang, double ejeX, double ejeY, double ejeZ){
        gl.glLoadIdentity();  //??
        gl.glRotated(ang, ejeX, ejeY, ejeZ);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void translada(double X, double Y, double Z){
        gl.glLoadIdentity();  //??
        gl.glTranslated(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void escala(double X, double Y, double Z){
        gl.glLoadIdentity();  //??
        gl.glScaled(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
}

