//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Mando.h"
//---------------------------------------------------------------------------
// Constructora
Mando::Mando() : Obstaculo() {

}

Mando::Mando(list<PV>* v, PV * pos, int vel) : Obstaculo() {

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
}
#pragma package(smart_init)
 
