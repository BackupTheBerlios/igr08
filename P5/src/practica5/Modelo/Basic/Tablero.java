package practica5.Modelo.Basic;

import java.util.ArrayList;
import javax.media.opengl.GL;


public class Tablero extends Malla {
    
    // Atributos privados
    private double X, Y, Z;
    private int divX, divY, divZ;
    private int numTrozos;
    private int numCaras;
    private int numVertices;
    private int numNormales;
    
    // Constructora por defecto
    public Tablero() {
        
        super();
    }
    
    
    // Constructora con parametros
    public Tablero(double X, double Y, double Z,
            int divX, int divY, int divZ) {
        
        super();
//        super.setGL(gl);
        
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.divX = divX;
        this.divY = divY;
        this.divZ = divZ;
        
        this.numTrozos = divX * divY * divZ;
        this.numVertices = numTrozos * 8;
        this.numCaras = numTrozos * 6;
        this.numNormales = this.numCaras;
        
        dimensionarTablero();
    }
    
    // Metodo que calcula las normales entre cada par de ejes
    public void calcularCarasYnormalesYZ(int divY, int divZ, int numPlanos) {
        
        // �ndices de v�rtices
        int ind1, ind2, ind3, ind4;
        
        for (int j=0; j<divY-1; j++) {
            for (int k=0; k<divZ-1; k++) {
                
                if (numPlanos==1) { // Caso del 1� Plano
                    ind1 = (k) + (j) * divZ + divY * divZ * (numPlanos-1);
                    ind2 = (k+1) +(j) * divZ + divY * divZ * (numPlanos-1);
                    ind3 = (k+1) +(j+1) * divZ + divY * divZ * (numPlanos-1);
                    ind4 = (k) + (j+1) * divZ + divY * divZ * (numPlanos-1);
                } else { // Caso del �ltimo plano
                    ind1 = (k) + (j) * divZ + divY * divZ * (numPlanos-1);
                    ind2 = (k) + (j+1) * divZ + divY * divZ * (numPlanos-1);
                    ind3 = (k+1) + (j+1) * divZ + divY * divZ * (numPlanos-1);
                    ind4 = (k+1) + (j) * divZ + divY * divZ * (numPlanos-1);
                }
                
                // this.addNormal(new Malla().calculaNormal(ind1, ind2, ind3, ind4));
                this.addCara(generarCara(ind1,ind2,ind3,ind4));
            }
        }
    }
    
    public void calcularCarasYnormalesXZ(int divX,int divY,int divZ, int numPlanos) {
        
        // Indices de vertices
        int ind1, ind2, ind3, ind4;
        
        for (int i=0; i<divX-1; i++) {
            for (int k=0; k<divZ-1; k++) {
                
                // Cara inferior
                ind1 = (k) + divY * divZ * (numPlanos-2);
                ind2 = (k) + divY * divZ * (numPlanos-1);
                ind3 = (k+1) + divY * divZ * (numPlanos-1);
                ind4 = (k+1) + divY * divZ * (numPlanos-2);
                
                // this.addNormal(new Malla().calculaNormal(ind1, ind2, ind3, ind4));
                this.addCara(generarCara(ind1,ind2,ind3,ind4));
                
                
                // Cara superior
                ind1 = (k) + (divY-1) * divZ + divY * divZ * (numPlanos-2);
                ind2 = (k+1) + (divY-1) * divZ + divY * divZ * (numPlanos-2);
                ind3 = (k+1) + (divY-1) * divZ + divY * divZ * (numPlanos-1);
                ind4 = (k) + (divY-1) * divZ + divY * divZ * (numPlanos-1);
                
                // this.addNormal(new Malla().calculaNormal(ind1, ind2, ind3, ind4));
                this.addCara(generarCara(ind1,ind2,ind3,ind4));
            }
        }
    }
    
    public void calcularCarasYnormalesXY(int divX, int divY, int divZ, int numPlanos) {
        
        // Indices de vertices
        int ind1,ind2,ind3,ind4;
        
        for (int j=0; j<divY-1; j++) {
            
            // Plano z=0
            ind1 = (j) * divZ + divY * divZ * (numPlanos-2);
            ind2 = (j+1) * divZ + divY * divZ * (numPlanos-2);
            ind3 = (j+1) * divZ + divY * divZ * (numPlanos-1);
            ind4 = (j) * divZ + divY * divZ * (numPlanos-1);
            
            // this.addNormal(new Malla().calculaNormal(ind1, ind2, ind3, ind4));
            this.addCara(generarCara(ind1,ind2,ind3,ind4));
            
            // Plano z=Z
            ind1 = (divZ-1) + (j) * divZ + divY * divZ * (numPlanos-1);
            ind2 = (divZ-1) + (j) * divZ + divY * divZ * (numPlanos-2);
            ind3 = (divZ-1) + (j+1) * divZ + divY * divZ * (numPlanos-2);
            ind4 = (divZ-1) + (j+1) * divZ + divY * divZ * (numPlanos-1);
            
            // this.addNormal(new Malla().calculaNormal(ind1, ind2, ind3, ind4));
            this.addCara(generarCara(ind1,ind2,ind3,ind4));
        }
    }
    
    // Metodo que genera una cara a partir de 4 indices de puntos
    public Cara generarCara(int ind1,int ind2,int ind3,int ind4) {
        
        Cara cara = new Cara();
        
        cara.setIndiceVerticeNormal(new VerticeNormal(ind1, 0));
        cara.setIndiceVerticeNormal(new VerticeNormal(ind2, 0));
        cara.setIndiceVerticeNormal(new VerticeNormal(ind3, 0));
        cara.setIndiceVerticeNormal(new VerticeNormal(ind4, 0));
        
        
        return cara;
    }
    
    public void dimensionarTablero() {
        
        int nV = 0;
        int nC = 0;
        
        double ancho = this.X / this.divX;
        double alto =  this.Y/ this.divY;
        double grosor = this.Z / this.divX;
        
        Cara unaCara;
        VerticeNormal vn;
        ArrayList<PuntoVector3D> listaVertices;
        ArrayList<VerticeNormal> listaVerticesNormales;
                
        for (int i=0; i<divX; i++)
            for (int j=0; j<divY; j++)
                for (int k=0; k<divZ; k++) {
      
                    // Calculamos los vertices
                    vertices.add(new PuntoVector3D(i * ancho, j * alto, (k+1) * grosor));
                    vertices.add(new PuntoVector3D((i+1) * ancho,j * alto, (k+1) * grosor));
                    vertices.add(new PuntoVector3D(i * ancho, (j+1) * alto, (k+1) * grosor));
                    vertices.add(new PuntoVector3D((i+1) * ancho, (j+1) * alto, (k+1) * grosor));
                    vertices.add(new PuntoVector3D(i * ancho, j * alto, k * grosor));
                    vertices.add(new PuntoVector3D((i+1) * ancho, j * alto, k * grosor));
                    vertices.add(new PuntoVector3D(i * ancho, (j+1) * alto, k * grosor));
                    vertices.add(new PuntoVector3D((i+1) * ancho, (j+1) * alto, k * grosor));
            
                    // Calculamos las normales y las caras
                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    listaVerticesNormales.add(new VerticeNormal(nV, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 1, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 3, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 2, nC));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int l=0; l<listaVerticesNormales.size(); l++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(l).getIndiceVertice()));
                    }
                                        
                    normales.add(this.metodoNewell(listaVertices));
                    nC++;
                    
                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    listaVerticesNormales.add(new VerticeNormal(nV + 1, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 5, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 7, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 3, nC));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int l=0; l<listaVerticesNormales.size(); l++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(l).getIndiceVertice()));
                    }
                                        
                    normales.add(this.metodoNewell(listaVertices));
                    nC++;

                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    listaVerticesNormales.add(new VerticeNormal(nV + 5, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 4, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 6, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 7, nC));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int l=0; l<listaVerticesNormales.size(); l++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(l).getIndiceVertice()));
                    }
                                        
                    normales.add(this.metodoNewell(listaVertices));
                    nC++;

                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    listaVerticesNormales.add(new VerticeNormal(nV, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 2, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 6, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 4, nC));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int l=0; l<listaVerticesNormales.size(); l++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(l).getIndiceVertice()));
                    }
                                        
                    normales.add(this.metodoNewell(listaVertices));
                    nC++;
                    
                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    listaVerticesNormales.add(new VerticeNormal(nV + 2, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 3, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 7, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 6, nC));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int l=0; l<listaVerticesNormales.size(); l++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(l).getIndiceVertice()));
                    }
                                        
                    normales.add(this.metodoNewell(listaVertices));
                    nC++;
                    
                    listaVerticesNormales = new ArrayList<VerticeNormal>();
                    listaVerticesNormales.add(new VerticeNormal(nV, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 4, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 5, nC));
                    listaVerticesNormales.add(new VerticeNormal(nV + 1, nC));
                    
                    unaCara = new Cara();
                    unaCara.setIndiceVerticeNormal(listaVerticesNormales);
                    caras.add(unaCara);
                    
                    listaVertices = new ArrayList<PuntoVector3D>();
                    for (int l=0; l<listaVerticesNormales.size(); l++) {
                        listaVertices.add(vertices.get(listaVerticesNormales.get(l).getIndiceVertice()));
                    }
                                        
                    normales.add(this.metodoNewell(listaVertices));
                    nC++;
                    
                    nV += 8;
                }
    }
}
