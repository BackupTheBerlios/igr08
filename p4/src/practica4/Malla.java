package practica4;

public class Malla {
    
    /** Creates a new instance of Malla */
    public Malla() {
    }
    
    public void defineMalla(){
        
        int nU = 0;
        int nV = 0;
        double uMin = 0, uMax = 0;
        double vMin = 0, vMax = 0;
        int numVert = nU* nV;
        int numNorm = numVert;
        PuntoVector3D  pt;
        PuntoVector3D  norm;
        int numCaras = (nU-1) * (nV-1);
        // cara  = -.....
        double incrU = (uMax- uMin) / (nU - 1);
        double incrV = (vMax- vMin) / (nV - 1);
        double u= uMin;
        double v= vMin;
        for (int i = 0; i < nU;  i++) {
            for (int j = 0;  j < nV; j++) {
                int indVertice = i* nV+j;
                //pt[indVertice] = new PuntoVector3D();
                
                
                v += incrV;
            }
            u+= incrU;
        }
        
        
        
        
    }
    
}
