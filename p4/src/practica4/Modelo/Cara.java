package practica4.Modelo;

import java.util.ArrayList;

public class Cara {

    private ArrayList<Integer> indicesVertices; 
    private ArrayList<VerticeNormal> indicesVerticesNormales;
    private int numVertices;

    public Cara() {
        indicesVertices = new ArrayList<Integer>();
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

    public void setIndiceVerticeNormal(VerticeNormal indiceVN) {
	this.indicesVerticesNormales.add(indiceVN);
    }
    
    public void setIndiceVertice(int indice) {
	this.indicesVertices.add(indice);
    }
}
