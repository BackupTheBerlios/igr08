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
        SE = PV(esqSupIzq->getX()+ancho, esqSupIzq->getY()-alto);
        SO = PV(esqSupIzq->getX(), esqSupIzq->getY()-alto);

        vertices->push_front(NO);
        vertices->push_front(NE);
        vertices->push_front(SE);
        vertices->push_front(SO);
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

bool Rectangulo::Corte(Pelota* pelota, GLdouble &tIn, PV* &normal) {

  tIn = 0;
  GLdouble tOut = 1;
  GLdouble tHit, num, den;
  PV* n;
  int i = -1;
  bool acabado = false;
  while (i < nVertices - 1 && !acabado) {
    i++;
    n = normales[i];
    num = vertices[i]->menos(pelota->getPuntoTangente(n)).productoEscalar(*n);
    den = n->productoEscalar(*pelota->getSentido());
    if (fabs(den) > 0.000001f) { // hay tHit
      tHit = num / den;
      if (den > 0) {
        if (tHit < tOut) tOut = tHit;
      } else {
        if (tHit >= tIn) {
          tIn = tHit;
          normal = n;
        }
      }
      acabado = tIn > tOut; // si se han cruzado, no hay intersección
    } else { // paralelismo
      if (num <= 0) acabado = true;
    }
  }
  return !acabado && !((tIn == 0) && (tIn <= tOut) && (tOut < 0.000001f));
}


}
#pragma package(smart_init)
