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
    
}
