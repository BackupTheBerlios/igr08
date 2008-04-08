package practica5.Modelo;

public class VerticeNormal {

    // Atributos privados
    private int indiceVertice;
    private int indiceNormal;

    // Constructora por defecto
    public VerticeNormal() {}
    
    // Constructora por par�metros
    public VerticeNormal(int indiceV, int indiceN) {
	this.indiceVertice = indiceV;
        this.indiceNormal = indiceN;	
    }
   
    // Getters & Setters 
    public int getIndiceVertice() {
	return indiceVertice;
    }

    public void setIndiceVertice(int i) {
	this.indiceVertice = i;
    }
    
    public int getIndiceNormal() {
	return indiceNormal;
    }

    public void setIndiceNormal(int i) {
	this.indiceNormal = i;
    }
}
