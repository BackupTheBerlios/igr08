package practica4.Modelo;

import java.util.ArrayList;

public class MallaPorRevolucion extends Malla {
    
    // Atributos
    private ArrayList<ArrayList<PuntoVector3D>> aros;
    
    // Constructora por defecto
    public MallaPorRevolucion() {}
    
    // Constructora por parámetros
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil) {
        
        ArrayList<PuntoVector3D> aroActual;
        
        for (int i = 0; i < perfil.size(); i++) {
            
            aroActual = perfil.get(i).calculaPuntos(perfil.get(i), (float)0.5);
            
            for (int j = 1; j <= aroActual.size(); j++)
               addVertice(aroActual.get(j));
        }

    }
    
}
