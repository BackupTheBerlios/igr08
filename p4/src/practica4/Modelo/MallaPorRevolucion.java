package practica4.Modelo;

import java.util.ArrayList;

public class MallaPorRevolucion extends Malla {
    
    private ArrayList<PuntoVector3D> perfil;
    private ArrayList<ArrayList<PuntoVector3D>> aros;
    
    public MallaPorRevolucion() {
        perfil = new ArrayList<PuntoVector3D>();
    }
    
    public MallaPorRevolucion(ArrayList<PuntoVector3D> p) {
        perfil = (ArrayList<PuntoVector3D>) p.clone();
        for (int i = 0; i < perfil.size(); i++) {
            aros.add(perfil.get(i).calculaPuntos(perfil.get(i),0.5));
        }
    }
    
}
