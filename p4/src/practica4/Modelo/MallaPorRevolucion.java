package practica4.Modelo;

public class MallaPorRevolucion extends Malla {

    private PuntoVector3D perfil;

    public MallaPorRevolucion() {
    perfil = new PuntoVector3D();
    }

    public MallaPorRevolucion(PuntoVector3D p) {
	perfil = p;
    }

}
