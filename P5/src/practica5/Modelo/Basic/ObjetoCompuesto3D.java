package practica5.Modelo.Basic;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class ObjetoCompuesto3D extends Objeto3D{
    
    // Atributos protegigos
    protected ArrayList<Objeto3D> hijos;
    
    // Constructora
    public ObjetoCompuesto3D() {
        hijos = new ArrayList<Objeto3D>();
    }
    
    // Operaciones 
    void rotar(double ang, double ejeX, double ejeY, double ejeZ) {
        this.getMatriz().rotar(ang, ejeX, ejeY, ejeZ);
    }
    
    void trasladar(double X, double Y, double Z) {
        this.getMatriz().trasladar(X, Y, Z);
    }
    
    void escalar(double X, double Y, double Z) {
        this.getMatriz().escalar(X, Y, Z);
    }
    
    // Getters & Setters
    public ArrayList<Objeto3D> getHijos() {
        return hijos;
    }
    
    public void addHijos(Objeto3D objeto3d) {
        hijos.add(objeto3d);
    }
    
    public void setColor(Color c) {
        this.setColor(c);
    }
    
    // Metodo dibujar
    public void dibuja(GL gl) {
        super.dibuja(gl);
        // Activamos la matriz de Modelado/Vista
        gl.glMatrixMode(gl.GL_MODELVIEW);
    
        // Guardamos el estado de la matriz
        gl.glPushMatrix();
    
        // Situamos el objeto compuesto en la escena
        gl.glMultMatrixd(matriz.getMatriz(), 0);
        
        // Dibujamos cada uno de los objetos
        for (int elem=0; elem<hijos.size(); elem++) 
            hijos.get(elem).dibuja(gl);
         
        // Volvemos al estado anterior
        gl.glPopMatrix();
    }
    
}
