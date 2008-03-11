package practica4.Controlador;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import com.sun.opengl.util.Animator;

public class GL3D implements GLEventListener {
    
    private double xLeft, xRight, yTop, yBot, xCentro, yCentro;
    private double RatioViewPort;
    private GLU glu;
    private GLContext context;
    
    private int tipo;
    
    public GL3D(){
        glu= new GLU();
        
        xRight = 200; 
        xLeft =- xRight;
        yTop = 200; 
        yBot =- xRight;
        xCentro= (xRight+xLeft)/2.0; 
        yCentro= (yTop+yBot)/2.0;    
        
        RatioViewPort= 1.0;
        
        tipo = 0;
    }
    
    public void display(GLAutoDrawable drw) {
        GL gl = drw.getGL();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        // Tipo de Escena a dibujar
        switch(tipo) {
            case 0: dibujaMallaPorRevolucion(gl); break;
            case 1: dibujaMallaPorExtrusion(gl); break;
        }
        
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        // TODO Auto-generated method stub
        
    }
    
    public void init(GLAutoDrawable drw) {
        GL gl = drw.getGL();
        GLU glu = new GLU();
        
        gl.glClearColor(0, 0, 0, 0);
        
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        
        //glu.gluOrtho2D(xLeft, xRight, yBot, yTop);
        //glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glOrtho(xLeft, xRight, yBot,  yTop, 1.0f, 10.0f);  
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
                
    }
    
    public void reshape(GLAutoDrawable drw, int x, int y, int width, int height) {
        RatioViewPort= (double)width/(double)height;
        double RatioVolVista=(xRight-xLeft)/(yTop-yBot);
        
        if (RatioVolVista>=RatioViewPort){
            //Aumentamos yTop-yBot
            double altoNew= (xRight-xLeft)/RatioViewPort;
            yTop= yCentro + altoNew/2.0;
            yBot= yCentro - altoNew/2.0;
        } else{
            //Aumentamos xRight-xLeft
            double anchoNew= RatioViewPort*(yTop-yBot);
            xRight= xCentro + anchoNew/2.0;
            xLeft= xCentro - anchoNew/2.0;
        }
        
        GL gl = drw.getGL();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(xLeft,xRight, yBot,yTop);
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        display(drw);
    }
    
    public void zoom(GLAutoDrawable drw, double factor){
        double newAncho= (xRight-xLeft)*factor;
        double newAlto= (yTop-yBot)*factor;
        xRight= xCentro+newAncho/2.0;
        xLeft= xCentro-newAncho/2.0;
        yTop= yCentro+newAlto/2.0;
        yBot= yCentro-newAlto/2.0;
        
        GL gl = drw.getGL();
        
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(xLeft,xRight, yBot,yTop);
        
        //display(drw);
    }
    
    // Método que dibuja Malla por Revolucion
    public void dibujaMallaPorRevolucion(GL gl) {
		
        //gl.glTranslatef(-1.5f, 0.0f, -6.0f);

	gl.glBegin(GL.GL_TRIANGLES);		    
            gl.glColor3f(1.0f, 0.0f, 0.0f);   
            gl.glVertex3f(0.0f, 50.0f, 0.0f);  
            gl.glColor3f(0.0f, 1.0f, 0.0f);   
            gl.glVertex3f(-50.0f, -50.0f, 0.0f);
            gl.glColor3f(0.0f, 0.0f, 1.0f);    
            gl.glVertex3f(50.0f, -50.0f, 0.0f); 
	gl.glEnd();        
    }
    
    // Método que dibuja Malla por Extrusión
    public void dibujaMallaPorExtrusion(GL gl) {
 		
        gl.glColor3f(0.5f, 0.5f, 1.0f);    
        //gl.glTranslatef(3.0f, 0.0f, 0.0f);
	
        gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f(-50.0f, 50.0f, 0.0f);  
            gl.glVertex3f(50.0f, 50.0f, 0.0f);  
            gl.glVertex3f(50.0f, -50.0f, 0.0f);  
            gl.glVertex3f(-50.0f, -50.0f, 0.0f); 
	gl.glEnd();       
    }
    
    // Getters & Stters
    public int getTipo() {
        return tipo;
    }
    
    public void setTipo(int t) {
        this.tipo = t;
    }
}
