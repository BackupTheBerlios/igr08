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

   calculaNormales();
}
// Destructora
Mando::~Mando() {
   delete vertices;
}

//Pintar el mando
void Mando::Pinta() {
        glBegin(GL_POLYGON);
            for (int i = 0; i<nVertices; i++){
                glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();
}
void Mando::Mueve (PV mov) {
        for (int i = 0; i<nVertices; i++){
                vertices[i]->setX(vertices[i]->getX()+mov.getX());
                vertices[i]->setY(vertices[i]->getY()+mov.getY());
        }
        posicion->setX(posicion->getX() + mov.getX());
}

bool Mando::Corte(Pelota* pelota, GLdouble &tIn, PV* &normal) {

  GLfloat epsilon = 0.000001f;

  tIn = 0;
  GLdouble tOut = 1;
  GLdouble tHit;
  GLdouble num, den;

  PV * n;
  int i = -1;
  bool enc = false;


  while (i < nVertices - 1 && !enc) {
    i++;
    n = normales[i];

    PV ptang = pelota->getPuntoTangente(n);
    PV * tmpVector = *vertices[i] - pelota->getPuntoTangente(n);

    num = tmpVector->dot(n);
    den = n->dot(pelota->getDireccion());

    if (fabs(den) > epsilon) { // hay tHit
      tHit = num / den;
      if (den > 0) {
        if (tHit < tOut) tOut = tHit;
      } else {
        if (tHit >= tIn) {
          tIn = tHit;
          normal = n;
        }
      }
      enc = tIn > tOut; // si se han cruzado, no hay intersección
    } else { // paralelismo
      if (num <= 0) enc = true;
    }
  }
  return !enc && !((tIn == 0) && (tIn <= tOut) && (tOut < epsilon));
}

PV* Mando::getPosicion(){
   return posicion;
}
#pragma package(smart_init)

