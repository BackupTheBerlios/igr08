package practica5.Modelo.Basic;

import practica5.util.Conversiones;
import practica5.util.Matrix;
public class TAfin {
    public double[] matriz;
    
    // Constructora
    public TAfin() {
        matriz = new double[16]; //formato de matriz equivalente a openGL
	setIdentity();
    }
    
    public void trasladar(double X, double Y, double Z) {
        
        double[] m = new double[16];
        
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
       
        m[12]=X;
        m[13]=Y;
        m[14]=Z;
        
        matriz = Matrix.multiplica(matriz,m);   
    }
   
    public void rotaX(double ang){
        double[] m = new double[16];
        double c = Math.cos(Conversiones.g2r(ang));
        double s = Math.sin(Conversiones.g2r(ang));
        
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
              
        m[5]=c;
        m[6]=s;
        m[9]=-s;
        m[10]=c;

        matriz = Matrix.multiplica(matriz,m);
    }
    
    public void rotaY(double ang){
        double[] m = new double[16];
        double c = Math.cos(Conversiones.g2r(ang));
        double s = Math.sin(Conversiones.g2r(ang));
        
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;

        m[0]=c;
        m[2]=-s;
        m[8]=s;
        m[10]=c;

        matriz = Matrix.multiplica(matriz,m);
    }

    public void rotaZ(double ang){
        double[] m = new double[16];
        double c = Math.cos(Conversiones.g2r(ang));
        double s = Math.sin(Conversiones.g2r(ang));
        
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
   
        m[0]=c;
        m[1]=s;
        m[4]=-s;
        m[5]=c;

        matriz = Matrix.multiplica(matriz,m);
    }
    
    public void escalaX(double s) {
        double[] m = new double[16];
        
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        m[0]=s;
        
        matriz = Matrix.multiplica(matriz,m);
    }
    
    public void escalaY(double s) {
        double[] m = new double[16];
        
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        m[5]=s;
        
        matriz = Matrix.multiplica(matriz,m);
    }
    
    public void escalaZ(double s) {
        double[] m = new double[16];
       
        m[0]=1;
        m[5]=1;
        m[10]=1;
        m[15]=1;
        
        m[10]=s;
        
        matriz = Matrix.multiplica(matriz,m);
    }
    
    public void escalaXYZ(double s) {
        escalaX(s);
        escalaY(s);
        escalaZ(s);
    }
    
//    private void escala_X(double s) {
//        this.setMatrizComponent(0, s);
//    }
//    
//    private void escala_Y(double s) {
//        this.setMatrizComponent(5, s);
//    }
//    
//    private void escala_Z(double s) {
//        this.setMatrizComponent(10, s);
//    }
//    public void setGL(GL gl) {
//        this.gl = gl;
//        
//        gl.glMatrixMode(GL.GL_MODELVIEW);
//        gl.glPushMatrix();
//        gl.glLoadIdentity();
//        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
//        gl.glPopMatrix();
//    }
    
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
    
    /**
     * @param left, right Specify the coordinates for the left and right vertical clipping planes.
     * @param bottom, top Specify the coordinates for the bottom and top horizontal clipping planes.
     * @param near, far Specify the distances to the nearer and farther depth clipping planes. These distances are negative if the plane is to be behind the viewer. 
     * @return 
     */    
    public double[] getOrthogonalMatrix(double left, double right, double bottom, double top, double near, double far){
     
        double tx = (right + left)/(right - left);
        double ty = (top + bottom)/(top - bottom);
        double tz = (far + near)/(far - near);
        
        setZeroMatrix();
        
        setMatrizComponent(0, 2/(right- left));
        setMatrizComponent(5, 2/(top-bottom));
        setMatrizComponent(10, -2/(far-near));
        setMatrizComponent(12, tx);
        setMatrizComponent(13, ty);
        setMatrizComponent(14, tz);
        setMatrizComponent(15, 1);
        return matriz;
    
    }
    
    public String toString() {
        String cad = "";
        for (int i = 0; i < matriz.length; i++) {
            cad += matriz[i]+"**";
        }
        return cad;
    }
}

