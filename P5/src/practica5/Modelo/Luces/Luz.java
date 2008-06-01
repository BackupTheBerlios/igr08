package practica5.Modelo.Luces;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Luz extends Objeto3D {

    // Atributos privados
    private int glLightNumber;
    private PuntoVector3D posicion;
    private PuntoVector3D direccion;
    private float angulo;
    private float intensidad;

    // Constructora con parámetros
    public Luz(int LightNumber, PuntoVector3D pos, PuntoVector3D dir, Color color, float ang, float exp) {

	this.glLightNumber = LightNumber;

	this.posicion = pos;
	this.direccion = dir;
	this.color = color;

	this.angulo = ang;
	this.intensidad = exp;

    }

    @Override
    public void dibuja(GL gl) {

	//float[] luzDifusa={0.25f, 0.695f, 0.32f, 1.0f};
	float[] luzDifusa = {1.0f, 1.0f, 1.0f, 1.0f};
	gl.glLightfv(glLightNumber, gl.GL_DIFFUSE, luzDifusa, 0);

//	    float[] luzAmbiente={0.43f, 0.43f, 0.43f, 1.0f};
	float[] luzAmbiente = {1.0f, 1.0f, 1.0f, 1.0f};
	gl.glLightfv(glLightNumber, gl.GL_AMBIENT, luzAmbiente, 0);

	//float[] luzEspecular={0.5f, 0.5f, 0.5f, 1.0f};
	float[] luzEspecular = {1.0f, 1.0f, 1.0f, 1.0f};
	gl.glLightfv(glLightNumber, gl.GL_SPECULAR, luzEspecular, 0);


	float[] posicionLuz = new float[4];
	posicionLuz[0] = (float) posicion.getX();
	posicionLuz[1] = (float) posicion.getY();
	posicionLuz[2] = (float) posicion.getZ();
	posicionLuz[3] = (float) posicion.getPV();
	gl.glLightfv(glLightNumber, gl.GL_POSITION, posicionLuz, 0);


	gl.glLightf(glLightNumber, gl.GL_SPOT_CUTOFF, (float) angulo);

	float[] direccionLuz = new float[4];
	direccionLuz[0] = (float) direccion.getX();
	direccionLuz[1] = (float) direccion.getY();
	direccionLuz[2] = (float) direccion.getZ();
	direccionLuz[3] = (float) direccion.getPV();
	gl.glLightfv(glLightNumber, gl.GL_SPOT_DIRECTION, direccionLuz, 0);


	// atenuacion de la intensidad
	gl.glLightf(glLightNumber, gl.GL_SPOT_EXPONENT, (float) intensidad);


    }

    public void interruptor(GL gl, boolean on) {

	if (on) {
	    gl.glEnable(glLightNumber);
	} else {
	    gl.glDisable(glLightNumber);
	}
    }

    public void setPosicion(PuntoVector3D p) {
	this.posicion = p;
    }

    public void setIntensidad(float exp) {
	this.intensidad = exp;
    }

    public void setAngulo(float ang) {
	this.angulo = ang;
    }
}
