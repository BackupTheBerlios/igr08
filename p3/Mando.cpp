//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Mando.h"
//---------------------------------------------------------------------------
// Constructora
Mando::Mando() : Convexo() {

}

Mando::Mando(PV** v, PV * pos, int vel) : Convexo() {

   vertices = v;
   posicion = pos;
   velocidad = vel;
   nVertices = 4;


}
// Destructora
Mando::~Mando() {
   delete vertices;
}

//Pintar el mando
void Mando::Pinta() {
        glBegin(GL_POLYGON);
  //          list<PV>::iterator it;
            for (int i = 0; i<nVertices; i++){
//            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();
}
void Mando::Mueve (PV mov) {
  //      list<PV>::iterator it;
//        for( it = vertices->begin(); it != vertices->end(); it++ ) {

        for (int i = 0; i<nVertices; i++){
                vertices[i]->setX(vertices[i]->getX()+mov.getX());
                vertices[i]->setY(vertices[i]->getY()+mov.getY());
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

