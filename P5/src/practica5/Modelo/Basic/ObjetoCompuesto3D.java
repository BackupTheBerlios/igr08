package practica5.Modelo.Basic;

import com.sun.opengl.util.texture.TextureCoords;
import java.util.ArrayList;
import javax.media.opengl.GL;

public class ObjetoCompuesto3D extends Objeto3D{
    
    // Atributos protegigos
    protected ArrayList<Objeto3D> hijos;
    private int ang;
    
    // Constructora
    public ObjetoCompuesto3D() {
        hijos = new ArrayList<Objeto3D>();
        ang = 1;
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
                   
            if (modificado)
                gl.glMultMatrixd(matriz.getMatriz(), 0);
        
            if (gira) {
                
                if (sentido)
                    ang++;
                else
                    ang--;
                            
                switch(ejeGiro) { 
                    
                    case 0: gl.glRotated(ang, 1.0, 0.0, 0.0); break;
                    case 1: gl.glRotated(ang, 0.0, 1.0, 0.0); break;
                    case 2: gl.glRotated(ang, 0.0, 0.0, 0.0); break;
            
                }
                gira = false;
            }
        
            if ((subir) && (getId() == Objeto3D.PERSIANA)) {
               glScaled(0.0, 50.0, 0.0);
            }
        
            if ((bajar) && (getId() == Objeto3D.PERSIANA)) {
                gl.glScaled(0.0, -50.0, 0.0);
            }
        
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
