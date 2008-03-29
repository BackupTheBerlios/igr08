package practica4.util;

import java.util.ArrayList;
import practica4.Modelo.PuntoVector3D;

public class Lapiz {

    private PuntoVector3D pos;
    private double ang;  // Radianes

// Constructora por defecto
    public Lapiz() {
	ang = 0.0;
	pos = new PuntoVector3D(0, 0, 0);
    }

// Constructora por parámetros
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
    public PuntoVector3D avanza(double longitud) {

	double xD = pos.getX() + longitud * Math.cos(ang);
	double yD = (float) pos.getY() + longitud * Math.sin(ang);
	PuntoVector3D p = new PuntoVector3D((float) xD, (float) yD, 0);
//	s = new Segmento(pos, p);
	lineTo(p);
	return p;
    }

    public ArrayList<PuntoVector3D> poligonoR1(double lado, int nlados) {
	ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
	double alfa = 360.0 / (double) nlados;
	double beta = (180.0 - alfa) / 2.0;
	double gamma = 180 - 2 * beta;
	retVal.add(this.pos);
	for (int i = 0; i < nlados; i++) {
	    //Segmento s = new Segmento();
	    PuntoVector3D unPunto = this.avanza(lado);
	    retVal.add(unPunto);
	    this.gira(gamma);
	    
	}
	return retVal;
    }

    public ArrayList<PuntoVector3D> poligonoR2(PuntoVector3D centro, float radio, int nlados) {
	ArrayList<PuntoVector3D> retVal;
	double alfa = 360.0 / (double) nlados;
	double beta = (180.0 - alfa) / 2.0;
	//   GLdouble gamma = 180 - 2*beta;
	double theta = 180 - 2 * beta;
//        GLfloat cose =   cos(g2r(beta));
	this.pos.setX(centro.getX() + (Math.cos(Conversiones.g2r(beta)) * radio));
	this.pos.setY(centro.getY() - (Math.sin(Conversiones.g2r(beta)) * radio));
	this.ang = Conversiones.g2r(theta);

	double lado = Math.cos(Conversiones.g2r(beta)) * radio * 2;
	retVal = poligonoR1(lado, nlados);
	return retVal;
    }
}
