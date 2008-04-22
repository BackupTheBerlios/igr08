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
    /*
    // Transformaciones Afines
    public void rotar(double ang, double ejeX, double ejeY, double ejeZ){
        gl.glLoadIdentity();  //??
        gl.glRotated(ang, ejeX, ejeY, ejeZ);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
     
    public void trasladar(double X, double Y, double Z){
        gl.glLoadIdentity(); //??
        gl.glTranslated(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    }
     
    public void escalar(double X, double Y, double Z){
        gl.glLoadIdentity();  //??
        gl.glScaled(X, Y, Z);
        gl.glGetDoublev(gl.GL_MODELVIEW_MATRIX, matriz, 0);
    } 
    */
    public void escalar(double X, double Y, double Z) {
        
        double[] m = new double[16];
        
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                
                if (i==j) {
                    if (i==0) {
                        m[i+j*4] = X;
                    } else if (i==1) {
                        m[i+j*4] = Y;
                    } else if (i==2) {
                        m[i+j*4] = Z;
                    } else {
                        m[i+j*4] = 1.0;
                    }
                } else {
                    m[i+j*4] = 0;
                }
            }
        }
        postMultiplicar(m);
    }
    
    public void trasladar(double X, double Y, double Z) {
        
        double[] m = new double[16];
        
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (i==j) {
                    m[i+j*4] = 1.0;
                } else if (j==4-1) {
                    if (i==0) {
                        m[i+j*4] = X;
                    } else if (i==1) {
                        m[i+j*4] = Y;
                    } else if (i==2) {
                        m[i+j*4] = Z;
                    }
                } else {
                    m[i+j*4] = 0;
                }
            }
        }
        postMultiplicar(m);
    }
    
    public void rotar(double angulo, double X, double Y, double Z) {
        
        if (X == EJE_SEL)
            rotarX(angulo);
        
        if (Y == EJE_SEL)
            rotarY(angulo);
        
        if (Z == EJE_SEL)
            rotarZ(angulo);
        
    }
    
    // Métodos auxiliares
    public void rotarX(double angulo) {
        
        double[] m = new double[16];
        
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (i==j) {
                    if (i==1 || i==2) {
                        m[i+j*4] = Math.cos(angulo);
                    } else {
                        m[i+j*4] = 1.0;
                    }
                } else if (i==1 && j==2) {
                    m[i+j*4] = Math.sin(angulo);
                } else if (i==2 && j==1) {
                    m[i+j*4] = -Math.sin(angulo);
                } else {
                    m[i+j*4] = 0;
                }
            }
        }
        postMultiplicar(m);
    }
    
    
    public void rotarY(double angulo) {
        
        double[] m = new double[16];
        
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (i==j) {
                    if (i==0 || i==2) {
                        m[i+j*4] = Math.cos(angulo);
                    } else {
                        m[i+j*4] = 1.0;
                    }
                } else if (i==0 && j==2) {
                    m[i+j*4] = -Math.sin(angulo);
                } else if (i==2 && j==0) {
                    m[i+j*4] = Math.sin(angulo);
                } else {
                    m[i+j*4] = 0;
                }
            }
        }
        postMultiplicar(m);
    }
    
    public void rotarZ(double angulo) {
        
        double[] m = new double[16];
        
        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                if (i==j) {
                    if (i==0 || i==1) {
                        m[i+j*4] = Math.cos(angulo);
                    } else {
                        m[i+j*4] = 1.0;
                    }
                } else if (i==0 && j==1) {
                    m[i+j*4] = Math.sin(angulo);
                } else if (i==1 && j==0) {
                    m[i+j*4] = -Math.sin(angulo);
                } else {
                    m[i+j*4] = 0;
                }
            }
        }
        postMultiplicar(m);
    }
    
    
    // Posmultiplicacion
    public void postMultiplicar(double[] m) {
        
        double[] aux = new double[16];
        
        for (int i=0;i<4;i++)
            for (int j=0;j<4;j++)
                aux[i+j*4] = matriz[i+j*4];
        
        
        // Postmultiplicamos m * t
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                matriz[i+j*4]=0;
                for (int k=0; k<4; k++) {
                    matriz[i+j*4] += aux[i+k*4] * m[k+j*4];
                }
            }
        }
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

