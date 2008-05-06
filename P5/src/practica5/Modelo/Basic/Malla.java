package practica5.Modelo.Basic;

import com.sun.opengl.util.texture.TextureCoords;
import java.util.ArrayList;
import javax.media.opengl.GL;

public class Malla extends Objeto3D {
    
    
    // texturas
    public static final String[] nombreTexturas = { "suelo.jpg", "madera.jpg", "cristalino.jpg",  
                                                    "tela.jpg", "pared.jpg", "pared2.jpg",
                                                    "cristalino.jpg", "tela.jpg", "suelo.jpg",  
                                                    "madera.jpg", "tela.jpg", "suelo.jpg" };
    
    // Constantes
    public static final int GL_POINTS = 0;
    public static final int GL_LINES = 1;
    public static final int GL_POLYGON = 2;
    
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
    
    // Metodo que permite dibujar la malla
    public void dibuja(GL gl) {
        //super.dibuja(gl);
        
        // Seleccionamos el color del objeto
        gl.glColor3d(this.getColor().getRed(), this.getColor().getGreen(), this.getColor().getBlue());
        
        // Guardamos el estado de la matriz de Modelado
        gl.glPushMatrix();
        
        // Situamos el objeto en la escena
        gl.glMultMatrixd(matriz.getMatriz(), 0);
        
        if (texturizado) {
               texturaAct.enable();
               texturaAct.bind();
               gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_REPLACE);
               TextureCoords coords = texturaAct.getImageTexCoords();   
            }
        else {
          texturaAct.disable(); 
        }
        
        // Seleccionamos el tipo de representación
        for (int i = 0; i < caras.size(); i++) {
            switch (this.tipoMalla) {
                case GL_POINTS:
                    gl.glBegin(gl.GL_POINTS);
                    break;
                case GL_LINES:
                    gl.glBegin(gl.GL_LINE_LOOP);
                    break;
                case GL_POLYGON:
                    gl.glBegin(gl.GL_POLYGON);
                    break;
            }
            

            // Comenzamos a dibujar el objeto
            for (int j = 0; j < caras.get(i).getIndiceVerticeNormal().size(); j++) {
                
                if ((baldosas) && (getId() == Objeto3D.SUELO))
                    if (j%2 == 0)
                        gl.glColor3d(255, 255, 255);
                    else
                        gl.glColor3d(0, 0, 0);
                
                int iN = caras.get(i).getIndiceNormal(j);
                int iV = caras.get(i).getIndiceVertice(j);
                
                if (this.normalesEnabled) {
                    gl.glNormal3d(normales.get(iN).getX(),
                            normales.get(iN).getY(),
                            normales.get(iN).getZ());
                }
                
                if (texturizado)
                    gl.glTexCoord3d(vertices.get(iV).getX()/300,
                                    vertices.get(iV).getY()/300,
                                    vertices.get(iV).getZ()/300); 
                
                gl.glVertex3d(vertices.get(iV).getX(),
                        vertices.get(iV).getY(),
                        vertices.get(iV).getZ());
            }
            gl.glEnd();
        }
        
        // Volvemos al estado anterior de la Matriz de Modelado
        gl.glPopMatrix();
    }
    
    public PuntoVector3D metodoNewell(ArrayList<PuntoVector3D> v) {
        PuntoVector3D normalPlano;
        double normX = 0;
        double normY = 0;
        double normZ = 0;
        
        for (int i = 0; i < v.size(); i++) {
            int posSig = (i + 1) % v.size();
            normX = normX + (v.get(i).getY() - v.get(posSig).getY()) *
                    (v.get(i).getZ() + v.get(posSig).getZ());
            
            normY = normY + (v.get(i).getZ() - v.get(posSig).getZ()) *
                    (v.get(i).getX() + v.get(posSig).getX());
            
            normZ = normZ + (v.get(i).getX() - v.get(posSig).getX()) *
                    (v.get(i).getY() + v.get(posSig).getY());
        }
        
        normalPlano = new PuntoVector3D(normX, normY, normZ, 0);
        normalPlano = normalPlano.normaliza();
        return normalPlano;
    }
    
    // Añade un nuevo vertice
    public void addVertice(PuntoVector3D punto) {
        vertices.add(punto);
    }
    
    // Añade una nueva normal
    public void addNormal(PuntoVector3D normal) {
        normales.add(normal);
    }
    
    // Añade una nueva cara
    public void addCara(Cara cara) {
        caras.add(cara);
    }
    
    // Calcula normal dados unos puntos
    public PuntoVector3D calculaNormal(int ind1, int ind2, int ind3, int ind4) {
        
        double x = 0;
        x += (vertices.get(ind1).getY() - vertices.get(ind2).getY())*
                (vertices.get(ind1).getZ() + vertices.get(ind2).getZ());
        
        x += (vertices.get(ind2).getY() - vertices.get(ind3).getY())*
                (vertices.get(ind2).getZ() + vertices.get(ind3).getZ());
        
        x += (vertices.get(ind3).getY() - vertices.get(ind4).getY())*
                (vertices.get(ind3).getZ() + vertices.get(ind4).getZ());
        
        x += (vertices.get(ind4).getY() - vertices.get(ind1).getY())*
                (vertices.get(ind4).getZ() + vertices.get(ind1).getZ());
        
        
        double y = 0;
        y += (vertices.get(ind1).getZ() - vertices.get(ind2).getZ())*
                (vertices.get(ind1).getX() + vertices.get(ind2).getX());
        
        y += (vertices.get(ind2).getZ() - vertices.get(ind3).getZ())*
                (vertices.get(ind2).getX() + vertices.get(ind3).getX());
        
        y += (vertices.get(ind3).getZ() - vertices.get(ind4).getZ())*
                (vertices.get(ind3).getX() + vertices.get(ind4).getX());
        
        y += (vertices.get(ind4).getZ() - vertices.get(ind1).getZ())*
                (vertices.get(ind4).getX() + vertices.get(ind1).getX());
        
        
        double z = 0;
        z += (vertices.get(ind1).getX() - vertices.get(ind2).getX())*
                (vertices.get(ind1).getY() + vertices.get(ind2).getY());
        
        z += (vertices.get(ind2).getX() - vertices.get(ind3).getX())*
                (vertices.get(ind2).getY() + vertices.get(ind3).getY());
        
        z += (vertices.get(ind3).getX() - vertices.get(ind4).getX())*
                (vertices.get(ind3).getY() + vertices.get(ind4).getY());
        
        z += (vertices.get(ind4).getX() - vertices.get(ind1).getX())*
                (vertices.get(ind4).getY() + vertices.get(ind1).getY());
        
        
        return (new PuntoVector3D(x, y, z, 0).normaliza());
    }
    
}
