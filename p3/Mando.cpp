//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Mando.h"
//---------------------------------------------------------------------------
// Constructora
Mando::Mando() : Obstaculo() {

}

Mando::Mando(PV** v, PV * pos, int vel) : Obstaculo() {
       GLdouble x, y;

   for(int i=0; i<=3; i++){
      x= v[i]-> getX();
      y= v[i]-> getY();
      vertices[i] = new PV(x,y);
   }
   posicion = pos;
   velocidad = vel;


}
// Destructora
Mando::~Mando() {
   for(int i=0; i<=3; i++){
      delete vertices[i];
   }
}

// Metodo que pinta la pelota
void Mando::Pinta() {
glBegin(GL_POLYGON);
   for(int i=0; i<=3; i++){
      glVertex2d(vertices[i]->getX(),vertices[i]->getY());
   }
glEnd();


}
#pragma package(smart_init)
 
