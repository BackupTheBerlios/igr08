package practica4.Modelo;

import java.util.ArrayList;

public class Cara {

    private ArrayList<PuntoVector3D> indicesVertices; 
    private ArrayList<VerticeNormal> indicesVerticesNormales;
    private int numVertices;

    public Cara() {
        indicesVertices = new ArrayList<PuntoVector3D>();
	indicesVerticesNormales = new ArrayList<VerticeNormal>();
        numVertices = 0;
    }

    public int getNumVertices() {
	return indicesVertices.size();
    }

    public int getIndiceVertice(int i) {
	return indicesVerticesNormales.get(i).getIndiceVertice();
    }

    public int getIndiceNormal(int i) {
	return indicesVerticesNormales.get(i).getIndiceNormal();
    }

    public void setIndiceVerticeNormal(int indiceVertice, int indiceNormal) {
	this.indicesVerticesNormales.add(new VerticeNormal(indiceVertice, indiceNormal));
    }
}
