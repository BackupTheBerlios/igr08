package practica5.util;

import java.util.ArrayList;
import practica5.Modelo.PuntoVector3D;

public class Lapiz {

    private PuntoVector3D pos;
    private double ang;  // Radianes

// Constructora por defecto
    public Lapiz() {
	ang = 0.0;
	pos = new PuntoVector3D(0, 0, 0);
    }

// Constructora por par�metros
    public Lapiz(PuntoVector3D punto, float angulo) {
	ang = angulo;
	pos = punto;
    }

// Dibuja un segmento desde la posicion relativa
    void lineTo(PuntoVector3D destino) {
	pos = destino.clonar();
    }

// Variamos el angulo actual de dibujo
    void gira(double incrAng) {

	double sum = incrAng + Conversiones.r2g(ang);
	double parte_Decimal = Conversiones.parteDecimal(sum);
	int parte_Entera = (int) sum;
	parte_Entera = parte_Entera % 360;
	double grados = Conversiones.g2r(parte_Entera);
	grados += Conversiones.g2r(parte_Decimal);
	ang = grados;
    }

// Avanzamos al siguiente punto relativo
    public PuntoVector3D avanza(double longitud, PuntoVector3D eje) {

	float  xD = (float) pos.getX() + (float) longitud * (float) Math.cos(ang);
	float  yD = (float) pos.getY() + (float) longitud * (float) Math.sin(ang);
	PuntoVector3D p = new PuntoVector3D();
	if (eje.getX() == 0) {
	    p = new PuntoVector3D(0,  xD,  yD);
	} else if (eje.getY() == 0) {
	    p = new PuntoVector3D(xD, 0, yD);
	} else if (eje.getZ() == 0) {
	    p = new PuntoVector3D(xD, yD, 0);
	} 
	lineTo(p);
	return p;
    }

    public ArrayList<PuntoVector3D> poligonoR1(double lado, int nlados, PuntoVector3D eje) {
	ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
	double alfa = 360.0 / (double) nlados;
	double beta = (180.0 - alfa) / 2.0;
	double gamma = 180 - 2 * beta;
	// retVal.add(this.pos); // el primer punto es equivalente al ultimo
	for (int i = 0; i < nlados; i++) {
	    PuntoVector3D unPunto = this.avanza(lado, eje);
	    retVal.add(unPunto);
	    this.gira(gamma);
	}
	return retVal;
    }

    public ArrayList<PuntoVector3D> poligonoR2(PuntoVector3D centro, float radio, int nlados, PuntoVector3D eje) {
	ArrayList<PuntoVector3D> retVal;
	double alfa = 360.0 / (double) nlados;
	double beta = (180.0 - alfa) / 2.0;
	double theta = 180 - 2 * beta;
	this.pos.setX(centro.getX() + (Math.cos(Conversiones.g2r(beta)) * radio));
	this.pos.setY(centro.getY() - (Math.sin(Conversiones.g2r(beta)) * radio));
	this.ang = Conversiones.g2r(theta);

	double lado = Math.cos(Conversiones.g2r(beta)) * radio * 2;
	retVal = poligonoR1(lado, nlados, eje);
	return retVal;
    }
}
