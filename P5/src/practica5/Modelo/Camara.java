package practica5.Modelo;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import practica5.util.Conversiones;

public class Camara {
    
    private PuntoVector3D eye, look, up;
    private PuntoVector3D u, v, n;
    private float l, r, b, t, N, F;
    private float[] m= new float[16];
    
    private GL gl;
    private GLU glu;
    
    
    
    public Camara(GL gl) {
        this.gl = gl;
        this.glu = new GLU();
        
        eye = new PuntoVector3D(0,0,0);
        look = new PuntoVector3D(0,0,-1);
        up = new PuntoVector3D(0,1,0);
    }
    
    private void setView(PuntoVector3D eye, PuntoVector3D look, PuntoVector3D up){
        this.eye = eye;
        this.look = look;
        this.up = up;
        //n = eye-look    normalizar
        //u = up x n    normalizar
        //v = n x u;
        n = eye.menos(look);
        n = n.normaliza();
        
        u = up.cross(n);
        u = u.normaliza();
        
        v = n.cross(u);
        
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        glu.gluLookAt(eye.getX(),eye.getY(),eye.getZ(),look.getX(),look.getY(),look.getZ(),up.getX(),up.getY(),up.getZ());
        
    }
    
    private void setProjection(){
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        //gl.glOrtho(l,r,b,t,-1,1);
        double anguloVision = 10;
        double proporcion = 1;
        glu.gluPerspective(anguloVision, proporcion, N, F);
    }
    
    private void setModelViewMatrix(){
        
    }
    
    public void roll(double angulo){
        double cs = Math.cos(Conversiones.g2r(angulo));
        double sn = Math.sin(Conversiones.g2r(angulo));
        PuntoVector3D t = u.clonar();
        u = new PuntoVector3D(cs*t.getX()-sn*v.getX(),cs*t.getY()-sn*v.getY(),cs*t.getZ()-sn*v.getZ());
        v = new PuntoVector3D(sn*t.getX()+cs*v.getX(),sn*t.getY()+cs*v.getY(),sn*t.getZ()+cs*v.getZ());
        setModelViewMatrix();
    }
    
    public void desliza(PuntoVector3D del){
        //******
        
        setModelViewMatrix();
    }
}
