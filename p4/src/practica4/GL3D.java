package practica4;

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
    
    public GL3D(){
        glu= new GLU();
        xRight=100.0; xLeft=-xRight;
        yTop=100.0; yBot=-xRight;
        xCentro= (xRight+xLeft)/2.0; //=0;
        yCentro= (yTop+yBot)/2.0;    //=0;
        RatioViewPort= 1.0;
    }
    
    public void display(GLAutoDrawable drw) {
        GL gl = drw.getGL();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL.GL_TRIANGLES);
        
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(25f, 25f);
        
        gl.glColor3f(0, 1, 0);
        gl.glVertex2f(5f, 25f);
        
        gl.glColor3f(0, 0, 1);
        gl.glVertex2f(25f, 5f);
        
        gl.glEnd();
        gl.glFlush();
    }
    
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        // TODO Auto-generated method stub
        
    }
    
    public void init(GLAutoDrawable drw) {
        GL gl = drw.getGL();
        
        gl.glClearColor(0, 0, 0, 0);
        
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(xLeft,xRight, yBot,yTop);
        
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        //Animator anim= new Animator(drw);
        //anim.start();
        
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
    
}
