package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class MallaPorRevolucion extends Malla {
    
    // Atributos
    private ArrayList<ArrayList<PuntoVector3D>> aros;
    private final int nVCara = 4;
    
    // Constructora por defecto
    public MallaPorRevolucion() {}
    /*
    // Constructora por parámetros
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil) {
    ArrayList<PuntoVector3D> aroActual;
    for (int i = 0; i < perfil.size(); i++) {
    aroActual = perfil.get(i).calculaPuntos(perfil.get(i), (float)0.5);
    for (int j = 1; j <= aroActual.size(); j++)
    addVertice(aroActual.get(j));
    }
    }
     */
    
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil) {
        
        PuntoVector3D actualI, actualI1, giraI, giraI1;
        double ang = 0.5;
        Cara c;
        
        //actualI = (PuntoVector3D) perfil.get(0);
        //actualI1 = (PuntoVector3D) perfil.get(1);
        //this.vertices.add(actualI);
        //this.vertices.add(actualI1);
        
        
        
        
        //ArrayList<PuntoVector3D> PerfilGirado = new ArrayList<PuntoVector3D>();
        actualI = (PuntoVector3D) perfil.get(0);
        giraI = actualI.giraPunto(ang);
        //actualI1 = (PuntoVector3D) perfil.get(1);
        this.vertices.add(actualI); ///
        this.vertices.add(giraI);  ///
        int nlados = (int) Math.floor(2 * Math.PI / ang);
        
        for (int i = 0; i < nlados; i++) {
            for (int j = 0; j < perfil.size() - 1; j++) {
                actualI1 = (PuntoVector3D) perfil.get(j + 1);
                //giraI = actualI.giraPunto((i + 1) * ang);
                giraI1 = actualI1.giraPunto(ang);
                
                this.vertices.add(actualI1);
                this.vertices.add(giraI1);
                
                // unir
                c = new Cara();
                c.setIndiceVertice(j + 1);
                c.setIndiceVertice(j + 2);
                c.setIndiceVertice(j + 4);  //<----
                c.setIndiceVertice(j + 3);
                
                this.caras.add(c);
                actualI = actualI1;
                giraI = giraI1;
            }
        }
    }
    
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil, float anguloRad) {
        int indCara ;
        Cara unaCara;
        int numVerticesAro = new PuntoVector3D().getNumVerticesDeRevolucion(anguloRad);
        for(int i = 0; i<1500; i++) {
            vertices.add(new PuntoVector3D());
        }
        
        for (int i=0; i<perfil.size(); i++) {
            for (int j=0; j<numVerticesAro; j++) {
                //vertices.add(i*perfil.size() + j, perfil.get(j).giraPunto(anguloRad));
                vertices.set(i*perfil.size() + j, perfil.get(j).giraPunto(anguloRad));
            }
        }
        for (int i=0; i<numVerticesAro; i++) {
            for (int j=1; j<perfil.size(); j++) {
                indCara = i * (perfil.size() - 1) + j - 1;
                unaCara = new Cara();
                unaCara.setIndiceVerticeNormal(new VerticeNormal(i*perfil.size()+ j, indCara));
                unaCara.setIndiceVerticeNormal(new VerticeNormal(i*perfil.size()+ j - 1, indCara));
                unaCara.setIndiceVerticeNormal(new VerticeNormal(((i+1)%numVerticesAro)*perfil.size()+ j - 1, indCara));
                unaCara.setIndiceVerticeNormal(new VerticeNormal(((i+1)%numVerticesAro)*perfil.size()+ j, indCara));
                caras.add(unaCara);
            }
        }
    }
    
    /********************************************************************
     * MallaPorRevolucion::MallaPorRevolucion(GLint numeroVerticesPerfil, PuntoVector3D** perfil, GLdouble radianes)
     * {
     * // Para empezar es necesario saber cuantas veces moveremos el perfil alrededor
     * // del eje y. Lo calculamos dividiendo 2*PI entre la distancia en radianes
     * // dada como parámetro y redondeando a la baja.
     * int copiasDelPerfil = floor(2 * M_PI / radianes);
     * // Ahora sabemos que tendremos tantos VERTICES como tenga el perfil
     * // multiplicado por el número de veces que vaya a aparecer:
     * numeroVertices = numeroVerticesPerfil * copiasDelPerfil;
     * // A continuación, el número de caras (que coincidirá con el número de
     * // normales al usar el método de Newell) será el número de vertices del
     * // perfil menos uno por el número de veces que aparecerá el perfil.
     * numeroCaras = (numeroVerticesPerfil - 1) * copiasDelPerfil;
     * numeroNormales = numeroCaras;
     * // Ya sabemos las dimensiones que tendrán los arrays dinámicos. Los creamos.
     * vertice = new PuntoVector3D*[numeroVertices];
     * normal = new PuntoVector3D*[numeroNormales];
     * cara = new Cara*[numeroCaras];
     * // Llega el momento de empezar a crear los objetos. Primero los vértices.
     * GLdouble angulo, x, y, z;
     * for (int i=0; i<copiasDelPerfil; i++) {
     * for (int j=0; j<numeroVerticesPerfil; j++) {
     * angulo = (double)i * radianes;
     * x = perfil[j]->getX() * cos(angulo);
     * y = perfil[j]->getY();
     * z = perfil[j]->getX() * sin(angulo);
     * vertice[i*numeroVerticesPerfil + j] = new PuntoVector3D(x, y, z);
     * }
     * }
     * // Creamos a continuación las caras.
     * for (int i=0; i<copiasDelPerfil; i++) {
     * for (int j=1; j<numeroVerticesPerfil; j++) {
     * // vertices implicados en la cara:
     * //        vertice[i*numeroVerticesPerfil + j]
     * //        vertice[i*numeroVerticesPerfil + j - 1]
     * //        vertice[((i+1)%copiasDelPerfil)*numeroVerticesPerfil + j - 1]
     * //        vertice[((i+1)%copiasDelPerfil)*numeroVerticesPerfil + j]
     * int indiceCara = i * (numeroVerticesPerfil - 1) + j - 1;
     * int numeroVerticesCara = 4;
     * VerticeNormal** vn = new VerticeNormal*[numeroVerticesCara];
     * vn[0] = new VerticeNormal(i*numeroVerticesPerfil + j, indiceCara);
     * vn[1] = new VerticeNormal(i*numeroVerticesPerfil + j - 1, indiceCara);
     * vn[2] = new VerticeNormal(((i+1)%copiasDelPerfil)*numeroVerticesPerfil + j - 1, indiceCara);
     * vn[3] = new VerticeNormal(((i+1)%copiasDelPerfil)*numeroVerticesPerfil + j, indiceCara);
     * cara[indiceCara] = new Cara(numeroVerticesCara, vn);
     * normal[indiceCara] = aplicarNewell(indiceCara);
     * }
     * }
     * // Por último y para terminar, borramos el perfil, que ya no nos hace falta.
     * for (int i=0; i<numeroVerticesPerfil; i++)
     * delete perfil[i];
     * delete[] perfil;
     * }
     */
    
    public void dibujaMallaPorRevolucion(GL gl) {
        this.dibuja(gl);
    }
}

