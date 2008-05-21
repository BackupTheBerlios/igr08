package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;

public class Toro extends Malla {
    
    /**
     * Ecuaciones parametricas de una circunferencia
     *	x = r * cos (t)
     *	y = r * sin (t)
     */
    private int nP,  nQ; // nP: numero de lados del poligono de la seccion del toro
    // nQ = numero de capas del toro
    private float r1,  r2; // r1: radio del toro; r2: radio de la seccion del toro
    private double[] matrizFrenet = new double[16];
    
    /**
     * <p> Construye un toroide cuyos parametros son:</p>
     * @param nP: Numero de lados que aproximan la secci�n del toroide
     * @param nQ: Numero de capas del toroide
     * @param r1: Radio del toro. Radio "grande"
     * @param r2: Radio de la secci�n del toro. Radio "pequeño"
     */
    public Toro(int nP, int nQ, float r1, float r2, GL gl) {
        
        super();
        super.setGL(gl);
        
        this.nP = nP;
        this.nQ = nQ;
        this.r1 = r1;
        this.r2 = r2;
        PuntoVector3D centro = new PuntoVector3D();
        
        for (int i = 0; i < nQ; i++) { // para cada capa...
            double t = (2.0 * Math.PI / (double) nQ) * (double) i;
            creaMarcoFrenet(t);
            
            ArrayList<PuntoVector3D> unAro = creaPoligonoRegular(nP, r2, centro);
            cambiaCoordenadas(unAro);
            
            //Guardamos los vertices de la malla.
            for (int j = 0; j < nP; j++) {
                this.vertices.add(unAro.get(j));
            }
        }
        
        //Creamos las normales de las caras
        for (int i = 0; i < nQ; i++) {  // Para cada capa...
            for (int j = 0; j < nP; j++) {    // Para cada punto...
                
                ArrayList<PuntoVector3D> verticesCara = new ArrayList<PuntoVector3D>();
                verticesCara.add(vertices.get((i * nP) + j));
                
                int sigVert = (j + 1) % nP;
                int sigCapa = (i + 1) % nQ;
                
                verticesCara.add(vertices.get((i * nP) + sigVert));
                
                verticesCara.add(vertices.get((sigCapa * nP) + sigVert));
                
                verticesCara.add(vertices.get((sigCapa * nP) + j));
                
                PuntoVector3D normal = this.metodoNewell(verticesCara);
                normales.add(normal);
                
                Cara unaCara = new Cara();
                VerticeNormal VN0 = new VerticeNormal((i * nP) + j, i * nP + j);
                VerticeNormal VN1 = new VerticeNormal((i * nP) + sigVert, i * nP + j);
                VerticeNormal VN2 = new VerticeNormal((sigCapa * nP) + sigVert, i * nP + j);
                VerticeNormal VN3 = new VerticeNormal((sigCapa * nP) + j, i * nP + j);
                
                unaCara.setIndiceVerticeNormal(VN0);
                unaCara.setIndiceVerticeNormal(VN1);
                unaCara.setIndiceVerticeNormal(VN2);
                unaCara.setIndiceVerticeNormal(VN3);
                
                this.caras.add(unaCara);
            }
        }
    }
    
    private void creaMarcoFrenet(double t) {
        /**
         * x = r1 * cos (t)
         * y = r1 * sin (t)
         **/
        //El vector trayectoria
        PuntoVector3D ct = new PuntoVector3D(r1 * Math.cos(t),
                r1 * Math.sin(t),
                0,
                1);
        
        //El vector derivada de la trayectoria
        PuntoVector3D tt = new PuntoVector3D(-r1 * Math.sin(t),
                r1 * Math.cos(t),
                0,
                0);
        
        // El vector binormal
        PuntoVector3D bt = new PuntoVector3D(0,
                0,
                -1,
                0);
        
        //El producto vectorial de bt y tt
        PuntoVector3D nt = new PuntoVector3D(-r1 * (Math.cos(t)),
                -r1 * (Math.sin(t)),
                0,
                0);
        
        tt = tt.normaliza();
        nt = nt.normaliza();
        
        //Creamos la matriz de cambio de coordenadas locales en globales.
        
        //1era fila
        matrizFrenet[0] = nt.getX();
        matrizFrenet[1] = bt.getX();
        matrizFrenet[2] = tt.getX();
        matrizFrenet[3] = ct.getX();
        
        //2a fila
        matrizFrenet[4] = nt.getY();
        matrizFrenet[5] = bt.getY();
        matrizFrenet[6] = tt.getY();
        matrizFrenet[7] = ct.getY();
        
        //3a fila
        matrizFrenet[8] = nt.getZ();
        matrizFrenet[9] = bt.getZ();
        matrizFrenet[10] = tt.getZ();
        matrizFrenet[11] = ct.getZ();
        
        //4a fila
        matrizFrenet[12] = nt.getPV();
        matrizFrenet[13] = bt.getPV();
        matrizFrenet[14] = tt.getPV();
        matrizFrenet[15] = ct.getPV();
    }
    
    ArrayList<PuntoVector3D> creaPoligonoRegular(int numLados, float radio, PuntoVector3D centro) {
        ArrayList<PuntoVector3D> poligono = new ArrayList<PuntoVector3D>();
        //angulos en radianes
        double anguloAlfa = 2 * Math.PI / numLados;
        double coordenadaZ = centro.getZ();
        
        for (int i = 1; i <= numLados; i++) {
            PuntoVector3D puntoNuevo = new PuntoVector3D(centro.getX() + radio * Math.cos(anguloAlfa * i), centro.getY() + radio * Math.sin(anguloAlfa * i), coordenadaZ, 1);
            poligono.add(puntoNuevo);
        }
        return poligono;
    }
    
    private void cambiaCoordenadas(ArrayList<PuntoVector3D> poligono) {
        for (int i = 0; i < nP; i++) {
            
            double X = (poligono.get(i).getX() * matrizFrenet[0] +
                    poligono.get(i).getY() * matrizFrenet[1] +
                    poligono.get(i).getZ() * matrizFrenet[2] +
                    +matrizFrenet[3]);
            
            double Y = (poligono.get(i).getX() * matrizFrenet[4] +
                    poligono.get(i).getY() * matrizFrenet[5] +
                    poligono.get(i).getZ() * matrizFrenet[6] +
                    matrizFrenet[7]);
            
            double Z = (poligono.get(i).getX() * matrizFrenet[8] +
                    poligono.get(i).getY() * matrizFrenet[9] +
                    poligono.get(i).getZ() * matrizFrenet[10] +
                    matrizFrenet[11]);
            
            //Se cambian los valores con el nuevo marco
            PuntoVector3D p = new PuntoVector3D(X, Y, Z, 1);
            poligono.set(i, p);
        }
    }
}
