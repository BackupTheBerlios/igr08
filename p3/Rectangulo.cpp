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

// Destructora
Rectangulo::~Rectangulo() {
delete vertices;
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
