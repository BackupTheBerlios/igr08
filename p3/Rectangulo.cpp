//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Rectangulo.h"
//---------------------------------------------------------------------------
// Constructora
Rectangulo::Rectangulo() : Obstaculo() {

}

Rectangulo::Rectangulo(list<PV>* v, PV * pos) : Obstaculo() {
   vertices = v;
   posicion = pos;
}

Rectangulo::Rectangulo(int ancho, int alto, PV * esqSupIzq) : Obstaculo() {
        PV NO, NE, SO, SE;
        NO = PV(esqSupIzq->getX(), esqSupIzq->getY());
        NE = PV(esqSupIzq->getX()+ancho, esqSupIzq->getY());
        SO = PV(esqSupIzq->getX(), esqSupIzq->getY()-alto);
        SE = PV(esqSupIzq->getX()+ancho, esqSupIzq->getY()-alto);

        vertices->push_front(NO);
        vertices->push_front(NE);
        vertices->push_front(SO);
        vertices->push_front(SE);
        posicion = esqSupIzq;
}

// Destructora
Rectangulo::~Rectangulo() {
}

// Metodo que pinta las paredes
void Rectangulo::Pinta() {
        glBegin(GL_POLYGON);
                list<PV>::iterator it;
                for( it = vertices->begin(); it != vertices->end(); it++ ) {
                        glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}
#pragma package(smart_init)
