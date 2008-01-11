//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Mando.h"
//---------------------------------------------------------------------------
// Constructora
Mando::Mando() : Obstaculo() {

}

Mando::Mando(list<PV>* v, PV * pos, int vel) : Obstaculo() {
       GLdouble x, y;

   vertices = v;
   posicion = pos;
   velocidad = vel;


}
// Destructora
Mando::~Mando() {
   delete vertices;
}

//Pintar el mando
void Mando::Pinta() {
int x, y;
glBegin(GL_POLYGON);
   list<PV>::iterator it;
    for( it = vertices->begin(); it != vertices->end(); it++ ) {
        x = it->getX();
        y = it->getY();
         glVertex2d(x, y);
    }
glEnd();


}
#pragma package(smart_init)
 
