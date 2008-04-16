package practica5.Modelo.Basic;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class Malla extends Objeto3D{

    // Atributos protegidos
    protected ArrayList<PuntoVector3D> vertices;
    protected ArrayList<PuntoVector3D> normales;
    protected ArrayList<Cara> caras;
    private int tipoMalla;
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

	for (int i = 0; i < caras.size(); i++) {

	    switch (tipoMalla) {

		case 0:
		    gl.glBegin(gl.GL_POINTS);
		    break;
		case 1:
		    gl.glBegin(gl.GL_LINE_LOOP);
		    break;
		case 2:
		    gl.glBegin(gl.GL_POLYGON);
		    break;
	    }

	    for (int j = 0; j < caras.get(i).getIndiceVerticeNormal().size(); j++) {

		int iN = caras.get(i).getIndiceNormal(j);
		int iV = caras.get(i).getIndiceVertice(j);

		if (normalesActivadas) {
		    gl.glNormal3f((float) normales.get(iN).getX(),
			         (float) normales.get(iN).getY(),
			         (float) normales.get(iN).getZ());
		}

		gl.glVertex3f((float) vertices.get(iV).getX(),
			(float) vertices.get(iV).getY(),
			(float) vertices.get(iV).getZ());
	    }
	    gl.glEnd();

	}

	//    }
	/////////////////////////////////////////////
	gl.glRotated(0.5, 0.5, 0.5, 0.5);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    /////////////////////////////////////////////
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

    // A�ade un nuevo vertice
    public void addVertice(PuntoVector3D punto) {
	vertices.add(punto);
    }

    public void setNormales(boolean valor) {
	normalesActivadas = valor;
    }

    public void setTipoMalla(int tipo) {
	tipoMalla = tipo;
    }
}
