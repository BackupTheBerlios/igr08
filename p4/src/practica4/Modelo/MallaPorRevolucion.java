package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;

public class MallaPorRevolucion extends Malla {

    // Atributos
    private ArrayList<ArrayList<PuntoVector3D>> aros;

    // Constructora por defecto
    public MallaPorRevolucion() {
    }
    /*
    // Constructora por parámetros
    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil) {
    ArrayList<PuntoVector3D> aroActual;
    for (int i = 0; i < perfil.size(); i++) {
    aroActual = perfil.get(i).calculaPuntos(perfil.get(i), (float)0.5);
    for (int j = 1; j <= aroActual.size(); j++)
    addVertice(aroActual.get(j));
    }
    }
     */

    public MallaPorRevolucion(ArrayList<PuntoVector3D> perfil) {

	PuntoVector3D actualI, actualI1, giraI, giraI1;
	double ang = 0.5;
	Cara c;

	//actualI = (PuntoVector3D) perfil.get(0);
	//actualI1 = (PuntoVector3D) perfil.get(1);
	//this.vertices.add(actualI);
	//this.vertices.add(actualI1);




	//ArrayList<PuntoVector3D> PerfilGirado = new ArrayList<PuntoVector3D>();
	actualI = (PuntoVector3D) perfil.get(0);
	giraI = actualI.giraPunto(ang);
	//actualI1 = (PuntoVector3D) perfil.get(1);
	this.vertices.add(actualI); ///
	this.vertices.add(giraI);  ///
	int nlados = (int) Math.floor(2 * Math.PI / ang);

	for (int i = 0; i < nlados; i++) {
	    for (int j = 0; j < perfil.size() - 1; j++) {
		actualI1 = (PuntoVector3D) perfil.get(j + 1);
		//giraI = actualI.giraPunto((i + 1) * ang);
		giraI1 = actualI1.giraPunto(ang);

		this.vertices.add(actualI1);
		this.vertices.add(giraI1);

		// unir
		c = new Cara();
		c.setIndiceVertice(j + 1);
		c.setIndiceVertice(j + 2);
		c.setIndiceVertice(j + 4);  //<----
		c.setIndiceVertice(j + 3);

		this.caras.add(c);
		actualI = actualI1;
		giraI = giraI1;
	    }
	}
    }

    public void dibujaMallaPorRevolucion(GL gl) {
	this.dibuja(gl);
    }
}

