package practica5.util;

import practica5.Modelo.PuntoVector3D;

public class Segmento {

    private PuntoVector3D _inicio;
    private PuntoVector3D _final;

    // Constructora por defecto
    Segmento() {
	_inicio = new PuntoVector3D(0.0, 0.0, 0.0);
	_final = new PuntoVector3D(0.0, 0.0, 0.0);
    }

// Constructora por par�metros
    public Segmento(PuntoVector3D i, PuntoVector3D f) {
	_inicio = i.clonar();
	_final = f.clonar();
    }

    void setPuntosNull() {
	_inicio = null;
	_final = null;
    }

    public String toString() {
	if (_inicio != null & _final != null) {
	    String retVal = _inicio.toString();
	    retVal += "<>" + _final.toString();
	    return retVal;
	}
	return "";
    }

    /*bool Segmento::contiene(Punto2f* p){
    if ((_inicio->getX() < p->getX() + 10)&&(_inicio->getX() > p->getX() - 10)&&
    (_inicio->getY() < p->getY() + 10)&&(_inicio->getY() > p->getY() - 10))
    return true;
    if ((_final.getX() < p.getX() + 10)&&(final->getX() > p->getX() - 10)&&
    (_final.getY() < p.getY() + 10)&&(final->getY() > p->getY()- 10))
    return true;
    return false;
    }*/
}


