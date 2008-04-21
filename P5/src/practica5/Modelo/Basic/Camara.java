package practica5.Modelo.Basic;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import practica5.Controlador.GL3D;
import practica5.util.Conversiones;

public class Camara {

    // Atributos privados
    private PuntoVector3D eye,  look,  up;
    private PuntoVector3D u,  v,  n;
    private float l,  r,  b,  t,  N,  F;
    private double[] m = new double[16];
    private GL gl;
    private GLU glu;

    public Camara(GL gl) {
	this.gl = gl;
	this.glu = new GLU();

	eye = new PuntoVector3D(0, 0, 0);
	look = new PuntoVector3D(0, 0, -1);
	up = new PuntoVector3D(0, 1, 0);

	setView(eye, look, up);
	setModelViewMatrix();
	setProjection(GL3D.PROY_ORTOGONAL);
    }
    
    public Camara(PuntoVector3D eye, PuntoVector3D look, PuntoVector3D up, GL gl){
	this.gl = gl;
	this.glu = new GLU();
	
	setView(eye, look, up);
	setModelViewMatrix();
	setProjection(GL3D.PROY_ORTOGONAL);
    }

    private void setView(PuntoVector3D eye, PuntoVector3D look, PuntoVector3D up) {
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
	glu.gluLookAt(eye.getX(), eye.getY(), eye.getZ(), look.getX(), look.getY(), look.getZ(), up.getX(), up.getY(), up.getZ());
    }

    private void setProjection(int tipoProyeccion) {
	gl.glMatrixMode(gl.GL_PROJECTION);
	gl.glLoadIdentity();

	switch (tipoProyeccion) {
	    case GL3D.PROY_ORTOGONAL:
		gl.glOrtho(l, r, b, t, -1, 1);
		break;
	    case GL3D.PROY_PERSPECTIVA:
		double anguloVision = 10;
		double proporcion = 1;
		glu.gluPerspective(anguloVision, proporcion, N, F);
		break;
	    case GL3D.PROY_OBLICUA:
		// Completar
		break;
	    default:
		gl.glOrtho(l, r, b, t, -1, 1);
		break;
	}
    }

    public void setModelViewMatrix() {
	m[0] = u.x;
	m[1] = v.x;
	m[2] = n.x;
	m[3] = 0;
	m[4] = u.y;
	m[5] = v.y;
	m[6] = n.y;
	m[7] = 0;
	m[8] = u.z;
	m[9] = v.z;
	m[10] = n.z;
	m[11] = 0;
	m[12] = -eye.prodEsc(u);
	m[13] = -eye.prodEsc(v);
	m[14] = -eye.prodEsc(n);
	m[15] = 1;

	this.gl.glMatrixMode(gl.GL_MODELVIEW);
	this.gl.glLoadMatrixd(m, 0);
    }

    public void roll(double angulo) {
	double cs = Math.cos(Conversiones.g2r(angulo));
	double sn = Math.sin(Conversiones.g2r(angulo));
	PuntoVector3D t = u.clonar();
	u = new PuntoVector3D(
		cs * t.getX() - sn * v.getX(),
		cs * t.getY() - sn * v.getY(),
		cs * t.getZ() - sn * v.getZ());
	v = new PuntoVector3D(
		sn * t.getX() + cs * v.getX(),
		sn * t.getY() + cs * v.getY(),
		sn * t.getZ() + cs * v.getZ());
	setModelViewMatrix();
    }

    public void pitch(double angulo) {
	double cs = Math.cos(Conversiones.g2r(angulo));
	double sn = Math.sin(Conversiones.g2r(angulo));
	PuntoVector3D t = n.clonar();
	n = new PuntoVector3D(
		cs * t.getX() - sn * v.getX(),
		cs * t.getY() - sn * v.getY(),
		cs * t.getZ() - sn * v.getZ());
	v = new PuntoVector3D(
		sn * t.getX() + cs * v.getX(),
		sn * t.getY() + cs * v.getY(),
		sn * t.getZ() + cs * v.getZ());
	setModelViewMatrix();
    }

    public void yaw(double angulo) {
	double cs = Math.cos(Conversiones.g2r(angulo));
	double sn = Math.sin(Conversiones.g2r(angulo));
	PuntoVector3D t = u.clonar();
	u = new PuntoVector3D(
		cs * t.getX() - sn * n.getX(),
		cs * t.getY() - sn * n.getY(),
		cs * t.getZ() - sn * n.getZ());
	n = new PuntoVector3D(
		sn * t.getX() + cs * n.getX(),
		sn * t.getY() + cs * n.getY(),
		sn * t.getZ() + cs * n.getZ());

	setModelViewMatrix();
    }

    public void desliza(PuntoVector3D des) {
	eye.x += des.x;
	eye.y += des.y;
	eye.z += des.z;

	setModelViewMatrix();
    }
}
