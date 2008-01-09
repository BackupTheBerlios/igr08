//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Rectangulo.h"
//---------------------------------------------------------------------------
// Constructora
Rectangulo::Rectangulo() : Obstaculo() {

}

Rectangulo::Rectangulo(PV**vertices, PV * poscicion) : Obstaculo() {

}

// Destructora
Rectangulo::~Rectangulo() {

}

// Metodo que pinta las paredes
void Rectangulo::Pinta() {
glBegin(GL_LINES);
   for(int i=0; i<=3; i++){
      glVertex2d(vertices[i]->getX(),vertices[i]->getY());
//      glVertex2d(1.1, 20.321);
   }
glEnd();
}
#pragma package(smart_init)
