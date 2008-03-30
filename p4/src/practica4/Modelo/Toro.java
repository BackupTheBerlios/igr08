package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica4.util.Lapiz;

public class Toro extends Malla {

    /**
     * Ecuaciones parametricas de una elipse
     *	x = a · cos (t)
     *	y = b · sin (t)
     */
    private int nP,  nQ;
    private float r1,  r2;
    private ArrayList<PuntoVector3D> circunferencia_1 = new ArrayList<PuntoVector3D>();
    private ArrayList<PuntoVector3D> circunferencia_2 = new ArrayList<PuntoVector3D>();
    private double[] matrizFrenet = new double[16];

    public Toro(float rad1, float rad2, int ladosToro, int ladosPoligono) {
	r1 = rad1;
	r2 = rad2;
	nP = ladosPoligono;
	nQ = ladosToro;

	Lapiz l = new Lapiz();
	PuntoVector3D ejeXY = new PuntoVector3D(1.0f, 1.0f, 0.0f);
	PuntoVector3D ejeYZ = new PuntoVector3D(0.0f, 1.0f, 1.0f);
	circunferencia_1 = l.poligonoR2(new PuntoVector3D(), rad1, ladosPoligono, ejeXY);
	circunferencia_2 = l.poligonoR2(new PuntoVector3D(), rad2, ladosToro, ejeYZ);
    }

    private void crearMarcoFrenet(int t) {
	/**
	 * son los vectores para una espital. Cambiarlos para un circulo. elipse?
	 */
	//Crear vectores adecuados y normalizarlos.
	PuntoVector3D ct = new PuntoVector3D(Math.cos(t) + t * Math.sin(t),
		0,
		Math.sin(t) - t * Math.cos(t),
		1); //El vector de la trayectoria

	PuntoVector3D tt = new PuntoVector3D(t * Math.cos(t),
		0,
		t * Math.sin(t),
		0); //El vector derivada de la trayectoria
	PuntoVector3D bt = new PuntoVector3D(0,
		-(1),
		0,
		0); //El producto vectorial de
	PuntoVector3D nt = new PuntoVector3D(-t * (Math.sin(t)),
		0,
		t * (Math.cos(t)),
		0);

	tt.normaliza();
	nt.normaliza();

	//Creamos la matriz de cambio de coordenadas locales en globales.

	//1ª fila
	matrizFrenet[0] = nt.getX();
	matrizFrenet[1] = bt.getX();
	matrizFrenet[2] = tt.getX();
	matrizFrenet[3] = ct.getX();

	//2ª fila
	matrizFrenet[4] = nt.getY();
	matrizFrenet[5] = bt.getY();
	matrizFrenet[6] = tt.getY();
	matrizFrenet[7] = ct.getY();

	//3ª fila
	matrizFrenet[8] = nt.getZ();
	matrizFrenet[9] = bt.getZ();
	matrizFrenet[10] = tt.getZ();
	matrizFrenet[11] = ct.getZ();

	//4ª fila
	matrizFrenet[12] = nt.getPV();
	matrizFrenet[13] = bt.getPV();
	matrizFrenet[14] = tt.getPV();
	matrizFrenet[15] = ct.getPV();
    }

    PuntoVector3D[] crearPoligonoRegular(int numLados, float radio, PuntoVector3D centro) {
	
	//Ángulos en radianes
	double anguloAlfa = 2 * Math.PI / numLados;
	int numVertices = 0;
	double coordenadaZ = centro.getZ();

	PuntoVector3D[] poligono = new PuntoVector3D[numLados];

	PuntoVector3D puntoTemp = new PuntoVector3D(centro.getX() + radio, centro.getY(), coordenadaZ, 1);

	poligono[numVertices] = puntoTemp;
	numVertices++;

	PuntoVector3D puntoAnterior;

	PuntoVector3D puntoNuevo = puntoTemp;

	for (int i = 1; i <= numLados; i++) {
	    puntoAnterior = puntoNuevo;
	    puntoNuevo = new PuntoVector3D(centro.getX() + radio * Math.cos(anguloAlfa * i), centro.getY() + radio * Math.sin(anguloAlfa * i), coordenadaZ, 1);
	    poligono[numVertices] = puntoNuevo;
	    numVertices++;
	}
	return poligono;
    }

    public void dibuja(GL gl) {
	gl.glBegin(gl.GL_LINES);

	for (int i = 0; i < circunferencia_1.size(); i++) {
	    PuntoVector3D unPunto = circunferencia_1.get(i);
	    PuntoVector3D otroPunto = circunferencia_1.get((i + 1) % circunferencia_1.size());
	    gl.glVertex3d(unPunto.getX(), unPunto.getY(), unPunto.getZ());
	    gl.glVertex3d(otroPunto.getX(), otroPunto.getY(), otroPunto.getZ());
	}
	gl.glEnd();
    }
}
