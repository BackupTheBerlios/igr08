package practica4.Modelo;

import java.util.ArrayList;
import practica4.util.Lapiz;

public class Espiral extends Malla {

    /**
     * Ecuaciones parametricas de una circunferencia
     *	x = r · cos (t)
     *	y = r · sin (t)
     */
    public static int MAX = 1000;
    private int nP,  nQ; // nP: nº de lados del polígono de la seccion del toro
    // nQ = nº de capas del toro
    private float r1,  r2; // r1: radio del toro; r2: radio de la seccion del toro
    private double[] matrizFrenet = new double[16];
    //---------------------------------------
    float radio;  //radio = radio del tubo

    public Espiral(float rad1, float rad2, int ladosToro, int ladosPoligono) {
	r1 = rad1;
	r2 = rad2;
	nP = ladosPoligono;
	nQ = ladosToro;

	Lapiz l = new Lapiz();
//	PuntoVector3D ejeXY = new PuntoVector3D(1.0f, 1.0f, 0.0f);
//	PuntoVector3D ejeYZ = new PuntoVector3D(0.0f, 1.0f, 1.0f);
//	circunferencia_1 = l.poligonoR2(new PuntoVector3D(), rad1, ladosPoligono, ejeXY);
//	circunferencia_2 = l.poligonoR2(new PuntoVector3D(), rad2, ladosToro, ejeYZ);
    }

    public Espiral(int nP, float radio, int nQ) {
	super();
	this.nP = nP;
	this.radio = radio;
	this.nQ = nQ;
	ArrayList<PuntoVector3D> poligonoConcreto;
	PuntoVector3D origenComunLocal = new PuntoVector3D(0, 1, 0, 1);
	int t = 0;


	for (t = 0; t < (nQ * 6) + 0; t = t + 6) {
	    crearMarcoFrenet(t);

	    poligonoConcreto = crearPoligonoRegular(nP, radio, origenComunLocal);
	    traduceVertices(poligonoConcreto); //matrizFrenet no se le pasa por parámetro porque es global

	    //Se guardan en malla los vértices, luego ya se crearán normales y caras.
	    for (int i = 0; i < nP; i++) {
		this.vertices.add(poligonoConcreto.get(i));
		int a = 0;
	    }
	}


	//Creamos las normales de las caras
	for (int k = 0; k < (nQ - 1); k++) {   //Rango hasta nQ-1, pues se consulta en el bucle (k+1)
	    for (int i = 0; i < nP; i++) {
		PuntoVector3D normal;
		int sig;
		ArrayList<PuntoVector3D> verticesCara = new ArrayList<PuntoVector3D>();

		verticesCara.add(vertices.get((k * nP) + i));

		sig = (i + 1) % nP;

		verticesCara.add(vertices.get((k * nP) + sig));

		verticesCara.add(vertices.get(((k + 1) * nP) + sig));

		verticesCara.add(vertices.get(((k + 1) * nP) + i));

		normal = this.metodoNewell(verticesCara);
		// añadir la normal
		normales.add(normal);

		Cara unaCara = new Cara();
		VerticeNormal VN0 = new VerticeNormal((k * nP) + i, k * nP + i);
		VerticeNormal VN1 = new VerticeNormal((k * nP) + sig, k * nP + i);
		VerticeNormal VN2 = new VerticeNormal(((k + 1) * nP) + sig, k * nP + i);
		VerticeNormal VN3 = new VerticeNormal(((k + 1) * nP) + i, k * nP + i);

		unaCara.setIndiceVerticeNormal(VN0);
		unaCara.setIndiceVerticeNormal(VN1);
		unaCara.setIndiceVerticeNormal(VN2);
		unaCara.setIndiceVerticeNormal(VN3);

		this.caras.add(unaCara);

	    }
	}

    }

    private void crearMarcoFrenet(int t) {
	/**
	 * son los vectores para una espiral. Cambiarlos por los de una circunferencia
	 */
	//Crear vectores adecuados y normalizarlos.
	//El vector de la trayectoria
	PuntoVector3D ct = new PuntoVector3D(Math.cos(t) + t * Math.sin(t),
		0,
		Math.sin(t) - t * Math.cos(t),
		1);

	//El vector derivada de la trayectoria
	PuntoVector3D tt = new PuntoVector3D(t * Math.cos(t),
		0,
		t * Math.sin(t),
		0);
	// El vector binormal
	PuntoVector3D bt = new PuntoVector3D(0,
		-(1),
		0,
		0);

	//El producto vectorial de bt y tt
	PuntoVector3D nt = new PuntoVector3D(-t * (Math.sin(t)),
		0,
		t * (Math.cos(t)),
		0);

	tt = tt.normaliza();
	nt = nt.normaliza();

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

    ArrayList<PuntoVector3D> crearPoligonoRegular(int numLados, float radio, PuntoVector3D centro) {
	ArrayList<PuntoVector3D> poligono = new ArrayList<PuntoVector3D>();
	//Ángulos en radianes
	double anguloAlfa = 2 * Math.PI / numLados;
	double coordenadaZ = centro.getZ();

	/*PuntoVector3D punto0 = new PuntoVector3D(centro.getX() + radio, centro.getY(), coordenadaZ, 1);
	poligono.add(punto0);*/

	PuntoVector3D puntoNuevo;

	for (int i = 1; i <= numLados; i++) {
	    puntoNuevo = new PuntoVector3D(centro.getX() + radio * Math.cos(anguloAlfa * i), centro.getY() + radio * Math.sin(anguloAlfa * i), coordenadaZ, 1);
	    poligono.add(puntoNuevo);
	}
	return poligono;
    }

    private void traduceVertices(ArrayList<PuntoVector3D> poligono) {
	for (int i = 0; i < nP; i++) {

	    double auxX = (poligono.get(i).getX() * matrizFrenet[0] +
		    poligono.get(i).getY() * matrizFrenet[1] +
		    poligono.get(i).getZ() * matrizFrenet[2] +
		    +matrizFrenet[3]);

	    double auxY = (poligono.get(i).getX() * matrizFrenet[4] +
		    poligono.get(i).getY() * matrizFrenet[5] +
		    poligono.get(i).getZ() * matrizFrenet[6] +
		    matrizFrenet[7]);

	    double auxZ = (poligono.get(i).getX() * matrizFrenet[8] +
		    poligono.get(i).getY() * matrizFrenet[9] +
		    poligono.get(i).getZ() * matrizFrenet[10] +
		    matrizFrenet[11]);

	    int Punto_Vector = 1;

	    //Se cambian los valores con el nuevo marco
	    PuntoVector3D p = new PuntoVector3D(auxX, auxY, auxZ, Punto_Vector);
	    poligono.set(i, p);
	}

    }
}
