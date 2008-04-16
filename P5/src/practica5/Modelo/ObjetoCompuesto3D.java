package practica5.Modelo;

import java.util.ArrayList;

public class ObjetoCompuesto3D extends Objeto3D{
    
    // Atributos protegigos
    protected ArrayList<Objeto3D> hijos;
    
    // Constructora
    public ObjetoCompuesto3D() {
        hijos = new ArrayList<Objeto3D>();
    }
    
    // Operaciones 
    void rotar(double ang, double ejeX, double ejeY, double ejeZ) {

    }
    
    void trasladar(double X, double Y, double Z) {
    
    }
    
    void escalar(double X, double Y, double Z) {
    
    }
    
    // Getters & Setters
    public ArrayList<Objeto3D> getHijos() {
        return hijos;
    }
    
    public void setHijos(Objeto3D objeto3d) {
        hijos.add(objeto3d);
    }
    
}
