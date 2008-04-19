package practica5.Modelo.Basic;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class Malla extends Objeto3D {

    public static final int GL_POINTS = 0;
    public static final int GL_LINES = 1;
    public static final int GL_POLYGON = 2;
    // Atributos protegidos
    protected ArrayList<PuntoVector3D> vertices;
    protected ArrayList<PuntoVector3D> normales;
    protected ArrayList<Cara> caras;
    private boolean normalesActivadas;

    // Constructora por defecto
    public Malla() {
	vertices = new ArrayList<PuntoVector3D>();
	normales = new ArrayList<PuntoVector3D>();
	caras = new ArrayList<Cara>();
	normalesActivadas = false;
    }

    // Método que permite dibujar la malla
    public void dibuja(GL gl) {
	super.dibuja(gl);
	for (int i = 0; i < caras.size(); i++) {
	    switch (tipoMalla) {
		case GL_POINTS:
		    gl.glBegin(gl.GL_POINTS);
		    break;
		case GL_LINES:
		    gl.glBegin(gl.GL_LINE_LOOP);
		    break;
		case GL_POLYGON:
		    gl.glBegin(gl.GL_POLYGON);
		    break;
	    }

	    for (int j = 0; j < caras.get(i).getIndiceVerticeNormal().size(); j++) {

		int iN = caras.get(i).getIndiceNormal(j);
		int iV = caras.get(i).getIndiceVertice(j);

		if (normalesActivadas) {
		    gl.glNormal3d(normales.get(iN).getX(),
			    normales.get(iN).getY(),
			    normales.get(iN).getZ());
		}

		gl.glVertex3d(vertices.get(iV).getX(),
			vertices.get(iV).getY(),
			vertices.get(iV).getZ());
	    }
	    gl.glEnd();
	}
    }

    public PuntoVector3D metodoNewell(ArrayList<PuntoVector3D> v) {
	PuntoVector3D normalPlano;
	double normX = 0;
	double normY = 0;
	double normZ = 0;

	for (int i = 0; i < v.size(); i++) {
	    int posSig = (i + 1) % v.size();
	    normX = normX + (v.get(i).getY() - v.get(posSig).getY()) *
		    (v.get(i).getZ() + v.get(posSig).getZ());

	    normY = normY + (v.get(i).getZ() - v.get(posSig).getZ()) *
		    (v.get(i).getX() + v.get(posSig).getX());

	    normZ = normZ + (v.get(i).getX() - v.get(posSig).getX()) *
		    (v.get(i).getY() + v.get(posSig).getY());
	}

	normalPlano = new PuntoVector3D(normX, normY, normZ, 0);
	normalPlano = normalPlano.normaliza();
	return normalPlano;
    }

    // Añade un nuevo vertice
    public void addVertice(PuntoVector3D punto) {
	vertices.add(punto);
    }

    public void setNormales(boolean valor) {
	normalesActivadas = valor;
    }
}
