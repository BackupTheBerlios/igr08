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
    
        public void MallaPorRevolucion1(ArrayList<PuntoVector3D> perfil) {
	    PuntoVector3D actualI, actualI1, giraI, giraI1;
	 /** inicializar..
	  * coger puntos
	  */
	    actualI = (PuntoVector3D) perfil.get(0);
	    actualI1 = (PuntoVector3D) perfil.get(1);
	    this.vertices.add(actualI);
	    this.vertices.add(actualI1);
	    
	    for (int i = 0; i < perfil.size()-1; i++) {
		actualI = (PuntoVector3D) perfil.get(i);
		actualI1 = (PuntoVector3D) perfil.get(i);
		giraI = actualI.giraPunto(0.5);
		giraI1 = actualI1.giraPunto(0.5);
		
		// unir
		this.vertices.add(actualI);
		this.vertices.add(actualI1);
		this.vertices.add(giraI);
		this.vertices.add(giraI1);
		Cara c = new Cara();
		// c.set...
		this.caras.add(c);
		
		
	    }

	}
    
}

