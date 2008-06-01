package practica5.Modelo.Luces;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Luz extends Objeto3D {
    
    // Atributos privados
    private int idLuz;
    private PuntoVector3D posicion;
    private PuntoVector3D direccion;
    private Color color;
    private float angulo;
    private float intensidad;
    
    // Constructora por defecto
    public Luz() {
        
        this.idLuz = 0;
        this.posicion = new PuntoVector3D(0, 0, 0, 1);
        this.direccion = new PuntoVector3D(0, -1, 0, 1);
        this.color = new Color(0.0f, 0.0f, 0.0f);
        this.angulo = 30;
        this.intensidad = 20;
    
    }
    
    // Constructora con parámetros
    public Luz(int id, PuntoVector3D pos, PuntoVector3D dir, Color color, float ang, float exp) {
        
        this.idLuz = id;
        
        this.posicion = pos;
        this.direccion = dir;
        this.color = color;
        
        this.angulo = ang;
        this.intensidad = exp;

    }
    
    public void dibujar(GL gl) {
         
            float[] luzDifusa={0.25f, 0.695f, 0.32f, 1.0f};
	    gl.glLightfv(idLuz, gl.GL_DIFFUSE, luzDifusa, 0);
	    
	    float[] luzAmbiente={0.43f, 0.43f, 0.43f, 1.0f};
	    gl.glLightfv(idLuz, gl.GL_AMBIENT,luzAmbiente, 0);
	    
	    float[] luzEspecular={0.5f, 0.5f, 0.5f, 1.0f};
	    gl.glLightfv(idLuz, gl.GL_SPECULAR, luzEspecular, 0);
	    
	    
	    float[] posicionLuz = new float[4];
	    posicionLuz[0] = (float) posicion.getX(); 
	    posicionLuz[1] = (float) posicion.getY();
	    posicionLuz[2] = (float) posicion.getZ(); 
            posicionLuz[3] =(float) posicion.getPV(); 
	    gl.glLightfv(idLuz, gl.GL_POSITION, posicionLuz, 0);
	   

	    gl.glLightf(idLuz, gl.GL_SPOT_CUTOFF, (float) angulo);
	  
	    float[] direccionLuz = new float[4];
	    direccionLuz[0] = (float) direccion.getX();
	    direccionLuz[1] = (float) direccion.getY();
	    direccionLuz[2] = (float) direccion.getZ();
	    direccionLuz[3] = (float) direccion.getPV();
	    gl.glLightfv(idLuz, gl.GL_SPOT_DIRECTION, direccionLuz, 0);
	    
	    
	    // atenuacion de la intensidad
	    gl.glLightf(idLuz, gl.GL_SPOT_EXPONENT, (float) intensidad);

    }
    
    public void interruptor(GL gl, boolean on) {
        
        if (on)
            gl.glEnable(idLuz);
        else
            gl.glDisable(idLuz);
    }
    
    public void setPosicion(PuntoVector3D p) {
        this.posicion = p;
    }
    
    public void setIntensidad(float exp) {
        this.intensidad = exp;
    }
    
    public void setAngulo(float ang) {
        this.angulo =ang;
    }
}
