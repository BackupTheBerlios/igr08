package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class Malla {

    protected int numVertices,  numNormales,  numCaras;

    protected ArrayList<PuntoVector3D> vertices;  // ??
    protected ArrayList<PuntoVector3D> normales;  // ??
    protected ArrayList<Cara> caras;
    
    
    public Malla() {
	vertices = new ArrayList<PuntoVector3D>();
	normales = new ArrayList<PuntoVector3D>();
	caras = new ArrayList<Cara>();
    }

    public void defineMalla() {

	int nU = vertices.size();
	int nV = normales.size();
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
        for (int i=0; i<caras.size(); i++) {
            gl.glBegin(gl.GL_LINE_LOOP);
                for (int j=0; j<caras.get(i).getNumVertices(); j++) {
                     
                    int iN=caras.get(i).getIndiceNormal(j);
                    int iV=caras.get(i).getIndiceVertice(j);
                     
                    gl.glNormal3f(normales.get(iN).getX(),
                                  normales.get(iN).getY(),
                                  normales.get(iN).getZ());
                       
                    gl.glVertex3f(vertices.get(iV).getX(),
                                  vertices.get(iV).getY(),
                                  vertices.get(iV).getZ());
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
	PuntoVector3D tmp = new PuntoVector3D((float)nx, (float)ny, (float)nz, 1);
	normales.add(tmp.normaliza());
    }
    
    // Añade un nuevo vertice 
    public void addVertice(PuntoVector3D punto) {
        vertices.add(punto);
    }
}
