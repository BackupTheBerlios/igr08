package practica5.Modelo.Basic;

import javax.media.opengl.glu.*;
import javax.media.opengl.GL;

public class Cilindro extends ObjetoCuadrico{

    // Atributos privados
    private double radioBaseA;
    private double radioBaseB;
    private double altura;
    private int numLados;
    private int numAros;
    
    private GLU glu;
    
    // Constructora 1
    public Cilindro(double radio1, double radio2, double h, int nLados, int nAros) {
        
        this.glu = new GLU();
        
        this.radioBaseA = radio1;
        this.radioBaseB = radio2;
        
        this.altura = h;
        this.numLados = nLados;
        this.numAros = nAros;
        
        this.glu.gluQuadricDrawStyle(glu.gluNewQuadric(), glu.GLU_FILL);
        
        this.setColor(1.0, 1.0, 1.0);
    }
    
    // Constructora 2
    public Cilindro(double radio1, double radio2, double h, int nLados, int nAros, Color c) {
        
        this.glu = new GLU();
        
        this.radioBaseA = radio1;
        this.radioBaseB = radio2;
        
        this.altura = h;
        this.numLados = nLados;
        this.numAros = nAros;
        
        glu.gluQuadricDrawStyle(glu.gluNewQuadric(), glu.GLU_FILL);
        
        this.setColor(c);
    }
    
    // Método Dibuja
    public void dibuja(GL gl) {
        
        // Seleccionamos color de dibujo
        gl.glColor3d(this.getColor().getRed(),this.getColor().getGreen(),this.getColor().getBlue());
        
        // Guardamos el estado de la matriz
        gl.glPushMatrix();
    
             // Situamos el objeto en al escena
             gl.glMultMatrixd(this.getMatriz().getMatriz(), 0);
             glu.gluCylinder(glu.gluNewQuadric(), this.radioBaseA, this.radioBaseB, this.altura,
                             this.numLados, this.numAros);
  
        gl.glPopMatrix();
        // Volvemos al estado anterior de la matriz
    }
    
}
