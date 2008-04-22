package practica5.Modelo.Basic;

import java.util.ArrayList;


public class Tablero extends Malla {
    
    // Atributos privados
    private double X, Y, Z;
    private int divX, divY, divZ;
    
    // Constructora por defecto
    public Tablero() { }
    
    /* El tablero está formado por una serie de cubos o planos paralelos entré sí
     * Por ello, vamos a irlo creando en orden...
     *      1º Calculamos el primer plano
     *      2º Calulamos los planos intermedios
     *      3º Calculamos el último plano
     * Y con esto tenemos definido el tablero
     */
    
    // Constructora con parámetros
    public Tablero(double X, double Y, double Z,
            int divX, int divY, int divZ) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.divX = divX;
        this.divY = divY;
        this.divZ = divZ;
        
        Cara cara;
        int numPlanos = 0;
        ArrayList<PuntoVector3D> plano;
        double incX, incY, incZ;
        
        // Cargamos la matriz de coordenadas inicial
        this.matriz = new TAfin();
        
        // Definimos un color inicial
        this.color = new practica5.Modelo.Basic.Color(1.0, 0.0, 0.0);
        
        // Calculamos la distancia que debe haber entre los puntos intermedios
        incX = (double) (X / divX);
        incY = (double) (Y / divY);
        incZ = (double) (Z / divZ);
        
        // Creamos el primer plano ( x = constante, (Y, Z) = variables )
        double x = 0;
        plano = generarPlano(x, incY, incZ, divY, divZ);
        numPlanos++;
        
        // Insertarmos los vertices
        for (int j=0; j<divY; j++)
            for (int k=0; k<divZ; k++)
                addVertice(plano.get(k+j*divY));
        
        // Insertamos las caras y las normales del 1º plano
        calcularCarasYnormalesYZ(divY, divZ, numPlanos);
        
        // Pasamos al siguiente plano
        x += incX;
        
        // Creamos los planos intermedios
        for (int i=1; i<divX-1; i++) {
            plano = generarPlano(x, incY, incZ, divY, divZ);
            numPlanos++;
            
            for (int j=0; j<divY; j++)
                for (int k=0; k<divZ; k++)
                    addVertice(plano.get(k+j*divY));
            
            
            // Insertamos las caras y las normales de los planos intermedios
            calcularCarasYnormalesXZ(divX, divY, divZ, numPlanos);
            calcularCarasYnormalesXY(divX, divY, divZ, numPlanos);
            
            // Siguiente plano a calcular
            x += incX;
        }
        
        // Creamos el último plano
        plano = generarPlano(x, incY, incZ, divY, divZ);
        numPlanos++;
        
        // Insertarmos los vertices
        for (int j=0; j<divY; j++)
            for (int k=0; k<divZ; k++)
                this.addVertice(plano.get(k+j*divY));
        
        // Insertamos las caras y las normales del último plano
        calcularCarasYnormalesYZ(divY, divZ, numPlanos);
        calcularCarasYnormalesXZ(divX, divY, divZ, numPlanos);
        calcularCarasYnormalesXY(divX, divY, divZ, numPlanos);
    }
    
    // Método que genera el plano
    public ArrayList<PuntoVector3D> generarPlano(double x,double incY, double incZ,
            int divY,int divZ) {
        double z, y;
        PuntoVector3D punto;
        ArrayList<PuntoVector3D> superficie;
        
        superficie = new ArrayList<PuntoVector3D>();
        for (int i=0; i<divY; i++)
            for (int k=0; k<divZ; k++) 
                superficie.add(new PuntoVector3D());
            
        
        //superficie.ensureCapacity((divY+1)*(divZ+1));
        
        y = 0;
        for (int j=0; j<divY; j++) {
            z = 0;
            for (int k=0; k<divZ; k++) {
                superficie.get(k+j*divZ).setXYZ(x, y, z);
                z += incZ;
            }
            y += incY;
        }
        
        return superficie;
    }
    
    // Método que calcula las normales entre cada par de ejes
    public void calcularCarasYnormalesYZ(int divY, int divZ, int numPlanos) {
        
        // índices de vértices
        int ind1, ind2, ind3, ind4;
        
        for (int j=0; j<divY-1; j++) {
            for (int k=0; k<divZ-1; k++) {
                
                if (numPlanos==1) { // Caso del 1º Plano
                    ind1 = (k) + (j) * divZ + divY * divZ * (numPlanos-1);
                    ind2 = (k+1) +(j) * divZ + divY * divZ * (numPlanos-1);
                    ind3 = (k+1) +(j+1) * divZ + divY * divZ * (numPlanos-1);
                    ind4 = (k) + (j+1) * divZ + divY * divZ * (numPlanos-1);
                } else { // Caso del último plano
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
        
        // índices de vértices
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
        
        // índices de vértices
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
    
    // Método que genera una cara a partir de 4 indices de puntos
    public Cara generarCara(int ind1,int ind2,int ind3,int ind4) {
       
        Cara cara = new Cara();
        
        cara.setIndiceVerticeNormal(new VerticeNormal(ind1, 0));
        cara.setIndiceVerticeNormal(new VerticeNormal(ind2, 0));
        cara.setIndiceVerticeNormal(new VerticeNormal(ind3, 0));
        cara.setIndiceVerticeNormal(new VerticeNormal(ind4, 0));
       
        return cara;       
    }
    
}
