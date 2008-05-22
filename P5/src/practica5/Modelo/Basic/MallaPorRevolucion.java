package practica5.Modelo.Basic;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class MallaPorRevolucion extends Malla {

    // Constructora por defecto
    public MallaPorRevolucion() {
    }
    
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil, int numVerticesCara, double anguloRad) {
        
        super();
//        super.setGL(gl);
        
        // 1�- Calculamos los v�rtices de la figura
        int numVerticesPorRev = new PuntoVector3D().getNumVerticesDeRevolucion(anguloRad);
        
        for (int i = 0; i < numVerticesPorRev; i++) {
            for (int j = 0; j < perfil.size(); j++) {
                this.vertices.add(perfil.get(j).giraPunto(anguloRad, i));
            }
        }
        
        
        // 2�- Calculamos las caras que forman los v�rtices
        Cara unaCara;
        int indCara = 0;
        ArrayList<PuntoVector3D> listaVertices;
        ArrayList<VerticeNormal> listaVerticesNormales;
        for (int i = 0; i < numVerticesPorRev; i++) {
            for (int j = 1; j < perfil.size(); j++) {
                listaVerticesNormales = new ArrayList<VerticeNormal>();
                
                if (numVerticesCara == 3) {
                    listaVerticesNormales.add(new VerticeNormal(i * perfil.size() + j, indCara));
                    listaVerticesNormales.add(new VerticeNormal(i * perfil.size() + j - 1, indCara));
                    listaVerticesNormales.add(new VerticeNormal(((i + 1) % numVerticesPorRev) * perfil.size() + j - 1, indCara));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int k = 0; k < listaVerticesNormales.size(); k++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(k).getIndiceVertice()));
                    }
                    
                    normales.add(this.metodoNewell(listaVertices));
                    
                    indCara++;
                    
                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    
                    listaVerticesNormales.add(new VerticeNormal(((i + 1) % numVerticesPorRev) * perfil.size() + j - 1, indCara));
                    listaVerticesNormales.add(new VerticeNormal(((i + 1) % numVerticesPorRev) * perfil.size() + j, indCara));
                    listaVerticesNormales.add(new VerticeNormal(i * perfil.size() + j, indCara));
                    
                    
                } else {
                    listaVerticesNormales.add(new VerticeNormal(i * perfil.size() + j, indCara));
                    listaVerticesNormales.add(new VerticeNormal(i * perfil.size() + j - 1, indCara));
                    listaVerticesNormales.add(new VerticeNormal(((i + 1) % numVerticesPorRev) * perfil.size() + j - 1, indCara));
                    listaVerticesNormales.add(new VerticeNormal(((i + 1) % numVerticesPorRev) * perfil.size() + j, indCara));
                    
                }
                
                unaCara = new Cara();
                unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                caras.add(unaCara);
                
                listaVertices = new ArrayList<PuntoVector3D>();
                for (int k = 0; k < listaVerticesNormales.size(); k++) {
                    listaVertices.add(vertices.get(listaVerticesNormales.get(k).getIndiceVertice()));
                }
                
                normales.add(this.metodoNewell(listaVertices));
                
                indCara++;
            }
        }
        
    }
}

