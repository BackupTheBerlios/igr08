package practica5.Modelo.Basic;

import javax.media.opengl.GL;

public class TAfin {
    
    // Atributos privados
    private GL gl;
    private  double[] matriz;
    
    private static double EJE_SEL = 1.0;
    
    // Constructora
    public TAfin() {
        // Trabajamos con el formato de matriz equivalente a openGL
        matriz = new double[16];
    }
    
    // Transformaciones Afines
    public void rotar(double ang, double ejeX, double ejeY, double ejeZ){
        gl.glMatrixMode(gl.GL_MODELVIEW);
        //gl.glLoadIdentity();  //??
        gl.glRotated(ang, ejeX, ejeY, ejeZ);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void trasladar(double X, double Y, double Z){
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity(); //??
        gl.glTranslated(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void escalar(double X, double Y, double Z){
        gl.glMatrixMode(gl.GL_MODELVIEW);
     //   gl.glLoadIdentity();  //??
        gl.glScaled(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
    
    public void setGL(GL gl){
        this.gl = gl;
    }
    
    // Getters & Setters
    public double[] getMatriz() {
        return matriz;
    }
    
    public void setMatriz(double[] m) {
        this.matriz = m;
    }
    
    public void setMatrizComponent(int indice, double valor){
        matriz[indice] = valor;
    }
    
    public void setIdentity(){
        for (int i=0; i<16; i++){
            this.matriz[i]=0;
        }
        matriz[0]=1;
        matriz[5]=1;
        matriz[10]=1;
        matriz[15]=1;
    }
}

