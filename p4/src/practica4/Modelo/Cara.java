package practica4.Modelo;

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
}
