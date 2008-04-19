package practica5.Modelo.Basic;

import javax.media.opengl.glu.*;
import javax.media.opengl.GL;

public class Esfera extends ObjetoCuadrico{
    
    // Atributos privados
    private double radio;
    private int numRayasHor;
    private int numRayasVer;
    
    private GLU glu;
    
    // Constructora 1
    public Esfera(double r, int nrh, int nrv) {
        
        this.glu = new GLU();
        
        this.radio = r;
        this.numRayasHor = nrh;
        this.numRayasVer = nrv;
        
        this.glu.gluQuadricDrawStyle(glu.gluNewQuadric(), glu.GLU_LINE);
  
        this.setColor3d(1.0, 1.0, 1.0);
    }
 
    // Constructora 2
    public Esfera(double r, int nrh, int nrv, Color c) {
        
        this.glu = new GLU();
        
        this.radio = r;
        this.numRayasHor = nrh;
        this.numRayasVer = nrv;
        
        this.glu.gluQuadricDrawStyle(glu.gluNewQuadric(), glu.GLU_LINE);
  
        this.setColor(c);
    }
    
    // M�todo Dibuja
    public void dibuja(GL gl) {
        
        // Seleccionamos color de dibujo
        gl.glColor3d(this.getColor().getRed(),this.getColor().getGreen(),this.getColor().getBlue());
        
        // Guardamos el estado de la matriz
        gl.glPushMatrix();
    
             // Situamos el objeto en al escena
             gl.glMultMatrixd(this.getMatriz().getMatriz(), 0);
             glu.gluSphere(glu.gluNewQuadric(), this.radio, this.numRayasVer, this.numRayasHor);
  
        gl.glPopMatrix();
        // Volvemos al estado anterior de la matriz
    }
    
}
