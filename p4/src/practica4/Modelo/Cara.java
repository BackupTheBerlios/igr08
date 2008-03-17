package practica4.Modelo;

import java.util.ArrayList;

public class Cara {

    private ArrayList<Integer> indicesVertices; 
    private ArrayList<Integer> indicesVerticesNormales;
    private int numVertices;

    public Cara() {
        indicesVertices = new ArrayList<Integer>();
	indicesVerticesNormales = new ArrayList<Integer>();
        numVertices = 0;
    }

    public int getNumVertices() {
	return indicesVertices.size();
    }

    public int getIndiceVertice(int i) {
	return indicesVerticesNormales.get(i);
    }

    public int getIndiceNormal(int i) {
	return indicesVerticesNormales.get(i);
    }

    public void setIndiceVerticeNormal(int indice) {
	this.indicesVerticesNormales.add(indice);
    }
    
    public void setIndiceVertice(int indice) {
	this.indicesVertices.add(indice);
    }
}
