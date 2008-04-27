package practica5.util;

import java.util.ArrayList;
import practica5.Modelo.Basic.PuntoVector3D;

public class Calculos {
    
    /** Creates a new instance of Calculos */
    public Calculos() {
        
    }
    
    // M�todo Auxiliar que calcula los casos Base de la funci�n BSplines
    public double BaseN2(double t) {
        
        double res = (double) 0.0;
        
        if (0 <= t && t <= 1)
            res = (double) (t * t * (1/2.0));
        else if (1 < t && t <= 2)
            res = (double) ((3/4.0) - (t - 3/2.0) * (t - 3/2.0));
        else if (2 < t && t <= 3)
            res = (double) ((1/2.0) * (3.0 - t) * (3.0 - t));
        
        return res;
    }
    
    public double BaseN(double t) {
        
        if (t >= 0 && t <= 1)
            return 0.5 * t * t;
        else if (t > 1 && t <= 2)
            return 0.75 - (t - 1.5) * (t - 1.5);
        else if (t > 2 && t <= 3)
            return 0.5 * (3.0 - t) * (3.0 - t);
        else
            return 0.0;
    }
    
    // Metodo B-Splines
    public ArrayList<PuntoVector3D> calculaPuntosBSplines(ArrayList<PuntoVector3D> perfil, int numPuntosControl) {
        
        ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
        int numPuntosIniciales = perfil.size();
        
        int ind;
        double ti, tj;
        double xd, yd, zd;
        double v, incr;
        
        ti = (double) 2.0;
        tj = numPuntosIniciales + 1;
        incr = (double) ((tj - 2) / numPuntosControl);
        
        while (ti<tj) {
            
            xd = 0;
            yd = 0;
            zd = 0;
            
            for (int i=0; i<numPuntosIniciales; i++) {
                v = (double) BaseN(ti-i);
                xd += perfil.get(i).getX() * v;
                yd += perfil.get(i).getY() * v;
                zd += perfil.get(i).getZ() * v;
            }
            
            ti += incr;
            
            retVal.add(new PuntoVector3D(xd, yd, zd, 1));
        }
        
        return retVal;
    }
    
}
