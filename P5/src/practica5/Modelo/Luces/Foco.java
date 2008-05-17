package practica5.Modelo.Luces;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.Objeto3D;
import practica5.Modelo.Basic.PuntoVector3D;

public class Foco extends Objeto3D {
    
    // Atributos privados
    private PuntoVector3D pos;
    private PuntoVector3D angulo;
    private float alpha;
    private boolean on;
    
    // Constructora por defecto
    public Foco() {
        this.pos = new PuntoVector3D();
        this.angulo = new PuntoVector3D();
        this.alpha = 45;
        this.on = true;
    }
    
    // Constructora con parámetros
    public Foco(PuntoVector3D p, PuntoVector3D ang, float alfa, boolean encendido, GL gl) {
        
        super.setGL(gl);
        
        this.pos = p;
        this.angulo = ang;
        this.alpha = alfa;
        this.on = encendido;
        
        float[] posLuz1 = { (float)pos.getX(), (float)pos.getY(), (float)pos.getZ(), 1 };
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posLuz1, 0);
        gl.glLightf(gl.GL_LIGHT1, gl.GL_SPOT_CUTOFF, alpha);
        
        float[] angLuz1 = { (float)ang.getX(), (float)ang.getY(), (float)ang.getZ(), 0};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPOT_DIRECTION, angLuz1, 0);
    }
    
    // Métodos funcionales
    public void mueveFoco() {
        
        gl.glMatrixMode(gl.GL_MODELVIEW);
        
        gl.glPushMatrix();
        
        gl.glMultMatrixd(this.getMatriz().getMatriz(), 0);
        
        float posLuz1[] = { (float)pos.getX(), (float)pos.getY(), (float)pos.getZ(), 1};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posLuz1, 1);
        gl.glLightf(gl.GL_LIGHT1, gl.GL_SPOT_CUTOFF, alpha);
        
        float angLuz1[] = { (float)angulo.getX(), (float)angulo.getY(), (float)angulo.getZ(), 0};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPOT_DIRECTION, angLuz1, 0);
        
        gl.glPopMatrix();
    }
   
    // Metodo que dibuja
    public void dibuja(){
        
        if (on) {
            
            float LuzAmbiente[] = { (float)0.4, (float)0.4, (float)0.4, (float)1.0};
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, LuzAmbiente, 1);
            
            float LuzDifusa[] = { (float)0.5, (float)0.5, (float)0.5, (float)1.0};
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, LuzDifusa, 0);
        
        } else {
            
            float LuzAmbiente[] = { (float)0.0, (float)0.0, (float)0.0, (float)1.0};
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, LuzAmbiente, 1);
            
            float LuzDifusa[] = { (float)0.0, (float)0.0, (float)0.0, (float)1.0};
            gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, LuzDifusa, 0);
            
        }
        
        gl.glMultMatrixd(this.getMatriz().getMatriz(), 0);
        
        float posLuz1[] = { (float)pos.getX(), (float)pos.getY(), (float)pos.getZ(), 1};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_POSITION, posLuz1, 1);
        
        float angLuz1[]={ (float)angulo.getX(), (float) angulo.getY(), (float)angulo.getZ(), 0};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_SPOT_DIRECTION, angLuz1, 0) ;
        gl.glLightf(gl.GL_LIGHT1, gl.GL_SPOT_CUTOFF, alpha);
    }
    
    // Tipos de luces
    public void setLuzDifusa() {
        float LuzDifusa[] = { (float)0.5, (float)0.5, (float)0.5, (float)1.0};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_DIFFUSE, LuzDifusa, 1);
    }

    public void setLuzAmbiente() {
        float LuzAmbiente[] = { (float)0.4, (float)0.4, (float)0.4, (float)1.0};
        gl.glLightfv(gl.GL_LIGHT1, gl.GL_AMBIENT, LuzAmbiente, 1);
    }

    public void setIntensidad(float factor) {
        gl.glLightf(gl.GL_LIGHT1, gl.GL_SPOT_EXPONENT, factor);
    }

    public void setAlpha(float a) {
        this.alpha = a;
    }
    
    public void apaga() {
        on = false;
    }
    
    public void enciende() {
        on = true;
    }
    
}
