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
        
    // Getters & Setters
    public ArrayList<Objeto3D> getHijos() {
        return hijos;
    }
    
    public void addHijos(Objeto3D nuevo) {
        hijos.add(nuevo);
    }
    
    public void setColor(Color c) {
        super.setColor(c);
    }
    
    public GL getGL() {
        return super.getGL();
    }
    
    public void setGL(GL gl) {
        super.setGL(gl);
    }
    
    // Metodo dibujar
    public void dibuja(GL gl) {
        
        super.dibuja(gl);
        
        // Activamos la matriz de Modelado/Vista
        gl.glMatrixMode(gl.GL_MODELVIEW);
    
        // Guardamos el estado de la matriz
        gl.glPushMatrix();
    
       
            // Dibujamos cada uno de los objetos
            for (int elem=0; elem<hijos.size(); elem++) 
                hijos.get(elem).dibuja(gl);
         
        // Volvemos al estado anterior
        gl.glPopMatrix();
    }
    
    
    // Metodos generalizados
    public void setTipoMalla(int i) {
         for (int elem=0; elem<hijos.size(); elem++) 
                hijos.get(elem).setTipoMalla(i); 
    }
    
    public void activarNormales(boolean valor) {
        for (int elem=0; elem<hijos.size(); elem++) 
             hijos.get(elem).setNormalesEnabled(valor);

    }
    
    // Operaciones
    public void rotar(double ang, double ejeX, double ejeY, double ejeZ) {
        this.getMatriz().rotar(ang, ejeX, ejeY, ejeZ);
    }
    
    public void trasladar(double X, double Y, double Z) {
        this.getMatriz().trasladar(X, Y, Z);
    }
    
    public void escalar(double X, double Y, double Z) {
        this.getMatriz().escalar(X, Y, Z);
    }
    
}
