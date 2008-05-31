package practica5.Modelo.Luces;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Luz extends Objeto3D {
    
    // Atributos privados
    private int idLuz;
    private float[] posicion;
    private float[] direccion;
    private float[] color;
    private float angulo;
    private float exponente;
    private boolean encendida;
    
    // Constructora por defecto
    public Luz() {
        
        this.idLuz = 0;
        this.posicion = new float[4];
        this.direccion = new float[4];
        this.color = new float[4];
        this.angulo = 0;
        this.exponente = 0;
        this.encendida = false;
    
    }
    
    // Constructora con parámetros
    public Luz(int id, PuntoVector3D pos, PuntoVector3D dir, Color color, float ang, float exp) {
        
        this.idLuz = id;
        
        this.posicion = new float[4];
        this.posicion[0] = (float) pos.getX();
        this.posicion[1] = (float) pos.getY();
        this.posicion[2] = (float) pos.getZ();
        this.posicion[3] = 1.0f;
        
        this.direccion = new float[4];
        this.direccion[0] = (float) dir.getX();
        this.direccion[1] = (float) dir.getY();
        this.direccion[2] = (float) dir.getZ();
        this.direccion[3] = 1.0f;

        this.color = new float[4];
        this.color[0] = color.getRed();
        this.color[1] = color.getGreen();
        this.color[2] = color.getBlue();
        this.color[3] = 1.0f;
        
        this.angulo = ang;
        this.exponente = exp;
        
        this.encendida = true;
    }
    
    public void dibujar(GL gl) {
         
        if (encendida) {
            gl.glPushMatrix();
                gl.glDisable(idLuz);
                gl.glLightfv(idLuz, gl.GL_POSITION, posicion, 1);
                gl.glLightfv(idLuz, gl.GL_SPOT_DIRECTION, direccion, 1);
                gl.glLightf(idLuz, gl.GL_SPOT_CUTOFF, angulo);
                gl.glLightf(idLuz, gl.GL_SPOT_EXPONENT, exponente);
                gl.glLightfv(idLuz, gl.GL_AMBIENT, color, 1);
                gl.glLightfv(idLuz, gl.GL_DIFFUSE, color, 1);
                gl.glEnable(idLuz);
            gl.glPopMatrix();
         }
    }
    
    public void interruptor() {
        
        if (encendida)
            encendida = false;
        else
            encendida = true;
    }
    
}
