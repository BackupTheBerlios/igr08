package practica5.Modelo.Basic;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import practica5.util.Conversiones;

public class Camara {
    
    private PuntoVector3D eye, look, up;
    private PuntoVector3D u, v, n;
    private float l, r, b, t, N, F;
    private double[] m= new double[16];
    
    private GL gl;
    private GLU glu;
    
    
    public Camara(GL gl) {
        this.gl = gl;
        this.glu = new GLU();
        
        eye = new PuntoVector3D(0,0,0);
        look = new PuntoVector3D(0,0,-1);
        up = new PuntoVector3D(0,1,0);
    }
    
    public Camara(GL gl, PuntoVector3D eye, PuntoVector3D look, PuntoVector3D up) {
        this.gl = gl;
        this.glu = new GLU();
        
        this.eye = eye;
        this.look = look;
        this.up = up;
    }
    
    private void setView(PuntoVector3D eye, PuntoVector3D look, PuntoVector3D up){
        this.eye = eye;
        this.look = look;
        this.up = up;
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
        double x , y , z;
        x = -eye.prodEsc(u);
        y = -eye.prodEsc(v);
        z = -eye.prodEsc(n);
        PuntoVector3D d = new PuntoVector3D(x, y, z);
        // 1ª Fila
        m[0] = u.x;
        m[1] = u.y;
        m[2] = u.z;
        m[3] = d.x;
        // 2ª Fila
        m[4] = v.x;
        m[5] = v.y;
        m[6] = v.z;
        m[7] = d.y;
        // 3ª Fila
        m[8] = n.x;
        m[9] = n.y;
        m[10] = n.z;
        m[11] = d.z;
        // 4ª Fila
        m[12] = 0;
        m[13] = 0;
        m[14] = 0;
        m[15] = 1;
    }
    
    public void roll(double angulo){
        double cs = Math.cos(Conversiones.g2r(angulo));
        double sn = Math.sin(Conversiones.g2r(angulo));
        PuntoVector3D t = u.clonar();
        u = new PuntoVector3D(
                cs*t.getX()-sn*v.getX(),
                cs*t.getY()-sn*v.getY(),
                cs*t.getZ()-sn*v.getZ());
        v = new PuntoVector3D(
                sn*t.getX()+cs*v.getX(),
                sn*t.getY()+cs*v.getY(),
                sn*t.getZ()+cs*v.getZ());
        setModelViewMatrix();
    }
    
    public void pitch(double angulo){
        double cs = Math.cos(Conversiones.g2r(angulo));
        double sn = Math.sin(Conversiones.g2r(angulo));
        PuntoVector3D t = n.clonar();
        n = new PuntoVector3D(
                cs*t.getX()-sn*v.getX(),
                cs*t.getY()-sn*v.getY(),
                cs*t.getZ()-sn*v.getZ());
        v = new PuntoVector3D(
                sn*t.getX()+cs*v.getX(),
                sn*t.getY()+cs*v.getY(),
                sn*t.getZ()+cs*v.getZ());
        setModelViewMatrix();
    }
    
    public void yaw(double angulo){
        double cs = Math.cos(Conversiones.g2r(angulo));
        double sn = Math.sin(Conversiones.g2r(angulo));
        PuntoVector3D t = u.clonar();
        u = new PuntoVector3D(
                cs*t.getX()-sn*v.getX(),
                cs*t.getY()-sn*v.getY(),
                cs*t.getZ()-sn*v.getZ());
        n = new PuntoVector3D(
                sn*t.getX()+cs*v.getX(),
                sn*t.getY()+cs*v.getY(),
                sn*t.getZ()+cs*v.getZ());
        setModelViewMatrix();
    }
    
    public void desliza(PuntoVector3D des){
        eye.x+= des.x+des.y+des.z;
        eye.y+= des.x+des.y+des.z;
        eye.z+= des.x+des.y+des.z;
        
        setModelViewMatrix();
    }
}
