package practica4;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class Malla {

    private int numVertices,  numNormales,  numCaras;

    private ArrayList<PuntoVector3D> vertices;  // ??
    private ArrayList<PuntoVector3D> normales;  // ??
    private ArrayList<Cara> caras;
    
    
    public Malla() {
	vertices = new ArrayList<PuntoVector3D>();
	normales = new ArrayList<PuntoVector3D>();
	caras = new ArrayList<Cara>();
    }

    public void defineMalla() {

	int nU = 0;
	int nV = 0;
	double uMin = 0, uMax = 0;
	double vMin = 0, vMax = 0;
	numVertices = nU * nV;
	numNormales = numVertices;
	PuntoVector3D pt;
	PuntoVector3D norm;
	numCaras = (nU - 1) * (nV - 1);
	// cara  = -.....
	double incrU = (uMax - uMin) / (nU - 1);
	double incrV = (vMax - vMin) / (nV - 1);
	double u = uMin;
	double v = vMin;
	for (int i = 0; i < nU; i++) {
	    for (int j = 0; j < nV; j++) {
		int indVertice = i * nV + j;
		//pt[indVertice] = new PuntoVector3D();


		v += incrV;
	    }
	    u += incrU;
	}
    }

    public void dibuja(GL gl) {
	for (int i = 0; i<numCaras; i++){
	    gl.glBegin(GL.GL_POLYGON);
	    for (int j = 0; j < caras.get(i).getNumVertices(); j++) {
		int iV = caras.get(i).getIndiceVertice(j);
		int iN = caras.get(i).getIndiceNormal(j);
		gl.glVertex3d(vertices.get(iV).getX(), vertices.get(iV).getY(), vertices.get(iV).getZ());
		gl.glVertex3d(normales.get(iV).getX(), normales.get(iV).getY(), normales.get(iV).getZ());
	    }
	    gl.glEnd();
	}
    }
    
    
    public void SetNormales() {
	// Metodo de Newel
	double nx = 0, ny = 0, nz = 0;
	int N = vertices.size();
	for (int i = 0; i < N; i++) {
	    nx = vertices.get(i).getY() - (vertices.get(i + 1 % N).getY());
	    nx = nx * vertices.get(i).getZ() + (vertices.get(i + 1 % N).getZ());

	    ny = vertices.get(i).getZ() - (vertices.get(i + 1 % N).getZ());
	    ny = ny * vertices.get(i).getX() + (vertices.get(i + 1 % N).getX());

	    nz = vertices.get(i).getX() - (vertices.get(i + 1 % N).getX());
	    nz = nz * vertices.get(i).getY() + (vertices.get(i + 1 % N).getY());
	}
	PuntoVector3D tmp = new PuntoVector3D(nx, ny, nz);
	normales.add(tmp.normaliza());
    }
}
