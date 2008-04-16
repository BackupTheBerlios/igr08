package practica5.Modelo.Basic;

import java.util.ArrayList;

public class Cara {

    // Atributos privados
    private ArrayList<VerticeNormal> indicesVerticesNormales;

    // Constructora por defecto
    public Cara() {
	indicesVerticesNormales = new ArrayList<VerticeNormal>();
    }

    // Getters & Setters
    public int getNumVertices() {
	return indicesVerticesNormales.size();
    }

    public int getIndiceVertice(int i) {
	return indicesVerticesNormales.get(i).getIndiceVertice();
    }

    public int getIndiceNormal(int i) {
	return indicesVerticesNormales.get(i).getIndiceNormal();
    }

    public void setIndiceVerticeNormal(VerticeNormal indiceVN) {
	this.indicesVerticesNormales.add(indiceVN);
    }

    public ArrayList<VerticeNormal> getIndiceVerticeNormal() {
	return indicesVerticesNormales;
    }

    void setIndiceVerticeNormal(ArrayList p) {
	indicesVerticesNormales = (ArrayList<VerticeNormal>) p.clone();
    }
    

    public PuntoVector3D SetNormal(ArrayList<PuntoVector3D> vertices, ArrayList<PuntoVector3D> normales) {
	PuntoVector3D normal = new PuntoVector3D();
	int indVertice = 0, indNormal = 0;
	ArrayList<PuntoVector3D> verticesCara = new ArrayList<PuntoVector3D>();
	ArrayList<PuntoVector3D> normalesCara = new ArrayList<PuntoVector3D>();
	for (int i = 0; i < this.indicesVerticesNormales.size(); i++) {
	    indVertice = this.indicesVerticesNormales.get(i).getIndiceVertice();
	    //indNormal = this.indicesVerticesNormales.get(i).getIndiceNormal();
	    verticesCara.add(vertices.get(indVertice));
	    //normalesCara.add(normales.get(indNormal));
	}
	// Metodo de Newel
	double nx = 0, ny = 0, nz = 0, nxi = 0, nyi = 0, nzi = 0;

	// vertices de la cara
	for (int i = 0; i < verticesCara.size(); i++) {
	    nxi = verticesCara.get(i).getY() - (verticesCara.get((i + 1) % verticesCara.size()).getY());
	    nxi = nx * verticesCara.get(i).getZ() + (verticesCara.get((i + 1) % verticesCara.size()).getZ());
	    nx += nxi;

	    nyi = verticesCara.get(i).getZ() - (verticesCara.get((i + 1) % verticesCara.size()).getZ());
	    nyi = ny * verticesCara.get(i).getX() + (verticesCara.get((i + 1) % verticesCara.size()).getX());
	    ny += nyi;

	    nzi = verticesCara.get(i).getX() - (verticesCara.get((i + 1) % verticesCara.size()).getX());
	    nzi = nz * verticesCara.get(i).getY() + (verticesCara.get((i + 1) % verticesCara.size()).getY());
	    nz += nzi;

	}
	normales.add(new PuntoVector3D((float) nx, (float) ny, (float) nz, 1).normaliza());
	// establecer el indice donde se a�ade la normal.
	indicesVerticesNormales.add(new VerticeNormal(indVertice, indVertice));
	return normal;
    }
}
