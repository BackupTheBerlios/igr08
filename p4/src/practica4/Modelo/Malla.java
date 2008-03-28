package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class Malla {
    
    // Atributos protegidos
    protected ArrayList<PuntoVector3D> vertices; 
    protected ArrayList<PuntoVector3D> normales; 
    protected ArrayList<Cara> caras;
    
    // Constructora por defecto
    public Malla() {
        vertices = new ArrayList<PuntoVector3D>();
        normales = new ArrayList<PuntoVector3D>();
        caras = new ArrayList<Cara>();
    }

    // Método que permite dibujar la malla
    public void dibuja(GL gl, int tipo) {
        
        for (int i=0; i<caras.size(); i++) {
 
            if (tipo==0)
              gl.glBegin(gl.GL_LINE_LOOP);
            else
              gl.glBegin(gl.GL_POINTS);
              
            for (int j=0; j<caras.get(i).getIndiceVerticeNormal().size(); j++) {
                
                int iN = caras.get(i).getIndiceNormal(j);
                int iV = caras.get(i).getIndiceVertice(j);
              
                gl.glNormal3f((float)normales.get(iN).getX(),
                              (float)normales.get(iN).getY(),
                              (float)normales.get(iN).getZ());
                
                gl.glVertex3f((float)vertices.get(iV).getX(),
                              (float)vertices.get(iV).getY(),
                              (float)vertices.get(iV).getZ());
            }
            gl.glEnd();
        
        }
    }
    
    public void SetNormales() {
        // Metodo de Newel
        double nx = 0, ny = 0, nz = 0;

        for (int i = 0; i < vertices.size(); i++) {
            nx = vertices.get(i).getY() - (vertices.get((i + 1) % vertices.size()).getY());
            nx = nx * vertices.get(i).getZ() + (vertices.get((i + 1) % vertices.size()).getZ());
            
            ny = vertices.get(i).getZ() - (vertices.get((i + 1) % vertices.size()).getZ());
            ny = ny * vertices.get(i).getX() + (vertices.get((i + 1) % vertices.size()).getX());
            
            nz = vertices.get(i).getX() - (vertices.get((i + 1) % vertices.size()).getX());
            nz = nz * vertices.get(i).getY() + (vertices.get((i + 1) % vertices.size()).getY());

        }
        normales.add(new PuntoVector3D((float)nx, (float)ny, (float)nz, 1).normaliza());
    }
    
    // Añade un nuevo vertice
    public void addVertice(PuntoVector3D punto) {
        vertices.add(punto);
    }
}
