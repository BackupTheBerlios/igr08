package practica4;

public class VerticeNormal {

    int indiceVertice;
    int indiceNormal;

    public VerticeNormal() {
    }
    
    public VerticeNormal(int indiceVert, int indiceNorm) {
	indiceNormal = indiceNorm;
	indiceVertice = indiceVert;	
    }

    public int getIndiceVertice() {
	return indiceVertice;
    }

    public int getIndiceNormal() {
	return indiceNormal;
    }

    public void setIndiceVertice(int i) {
	indiceVertice = i;
    }

    public void setIndiceNormal(int i) {
	indiceNormal = i;
    }
}
