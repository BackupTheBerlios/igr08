package practica4.Modelo;

import java.util.ArrayList;

public class MallaPorRevolucion extends Malla {
    
    //private ArrayList<PuntoVector3D> perfil;
    private ArrayList<ArrayList<PuntoVector3D>> aros;
    
    public MallaPorRevolucion() {
        //perfil = new ArrayList<PuntoVector3D>();
    }
    
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil) {
        //perfil = (ArrayList<PuntoVector3D>) p.clone();
        for (int i = 0; i < perfil.size()-1; i++) {
            
            ArrayList<PuntoVector3D> aroActual = perfil.get(i).calculaPuntos(perfil.get(i), (float)0.5);
            ArrayList<PuntoVector3D> aroSiguiente = perfil.get(i).calculaPuntos(perfil.get(i+1), (float)0.5);
            
            for (int j=0; j<aroActual.size(); j++) {
                
            }
            
            aros.add(perfil.get(i).calculaPuntos(perfil.get(i), (float)0.5));
        }
    }
    
}
