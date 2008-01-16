//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Mando.h"
//---------------------------------------------------------------------------
// Constructora
Mando::Mando() : Convexo() {

}

Mando::Mando(list<PV>* v, PV * pos, int vel) : Convexo() {

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
        glBegin(GL_POLYGON);
            list<PV>::iterator it;
            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}
void Mando::Mueve (PV mov) {
        list<PV>::iterator it;
        for( it = vertices->begin(); it != vertices->end(); it++ ) {
                it->setX(it->getX()+mov.getX());
                it->setY(it->getY()+mov.getY());
        }
        posicion->setX(posicion->getX() + mov.getX());
}

bool Mando:: corte(){
// Implemntar Cyrus - Beck

}

PV* Mando::getPosicion(){
   return posicion;
}
#pragma package(smart_init)

