package practica4.util;

import java.util.ArrayList;
import practica4.Modelo.PuntoVector3D;

public class Calculos {
    
    /** Creates a new instance of Calculos */
    public Calculos() {
        
    }
    
    
    
    // Método Auxiliar que calcula los casos Base de la función BSplines
    public float BaseN(float t) {
        
        float res = (float) 0.0;
        
        if (0 <= t && t <= 1)
            res = (float) (t * t * (1/2.0));
        else if (1 < t && t <= 2)
            res = (float) ((3/4.0) - (t - 3/2.0) * (t - 3/2.0));
        else if (2 < t && t <= 3)
            res = (float) ((1/2.0) * (3.0 - t) * (3.0 - t));
        
        return res;
    }
    
    // Metodo B-Splines
    public ArrayList<PuntoVector3D> calculaPuntosBSplines(ArrayList<PuntoVector3D> perfil, int numPuntosControl) {
        
        ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
        int numPuntosIniciales = perfil.size();
        
        int ind;
        float ti, tj;
        float xd, yd, zd;
        float v, incr;
        
        ti = (float) 2.0;
        tj = numPuntosIniciales + 1;
        incr = (float) ((tj - 2) / numPuntosControl);
        
        while (ti<tj) {
            
            xd = 0;
            yd = 0;
            zd = 0;
            
            for (int i=0; i<numPuntosIniciales; i++) {
                v = (float) BaseN(ti-i);
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
