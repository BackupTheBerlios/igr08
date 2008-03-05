package practica4;

import java.util.ArrayList;

public class Cara {

    private ArrayList<PuntoVector3D> vertices;
    private ArrayList<PuntoVector3D> normales;

    public Cara() {
	vertices = new ArrayList<PuntoVector3D>();
	normales = new ArrayList<PuntoVector3D>();
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

    public int getNumVertices() {
	return vertices.size();
    }
    
    public int getIndiceVertice(int i){
	return -1; // terminar
    }
    
    public int getIndiceNormal(int i){
	return -1; // terminar
    }
}
