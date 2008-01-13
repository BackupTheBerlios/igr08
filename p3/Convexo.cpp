//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Convexo.h"

//---------------------------------------------------------------------------
// Constructora
Convexo::Convexo() : Obstaculo() {}
Convexo::Convexo(list<PV>* listaVertices): Obstaculo(listaVertices) {}

Convexo::Convexo(PV* centro, GLfloat radio, int nlados) : Obstaculo(){
        Lapiz * l = new Lapiz();
        list<PV>* listaVertices = new list<PV>();
        l->poligonoR2(centro, radio, nlados, listaVertices);
        vertices = listaVertices;
}

// Destructora
Convexo::~Convexo() {

}

// Metodo que pinta el poligono convexo
void Convexo::Pinta() {
        glBegin(GL_POLYGON);
            list<PV>::iterator it;
            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}
#pragma package(smart_init)
