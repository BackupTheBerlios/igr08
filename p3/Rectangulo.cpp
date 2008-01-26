//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Rectangulo.h"
//---------------------------------------------------------------------------
// Constructora
Rectangulo::Rectangulo() : Obstaculo() { calculaNormales(); }

Rectangulo::Rectangulo(PV** v, int size, PV * pos) : Obstaculo() {
   for (int i= 0; i<size; i++){
      vertices[i] = v[i];
   }
   //   vertices = v;
   nVertices = size;
   posicion = pos;
   calculaNormales();
}



Rectangulo::Rectangulo(int ancho, int alto, int size, PV * esqSupIzq) : Obstaculo() {
        PV NO, NE, SO, SE;
        NO = PV(esqSupIzq->getX(), esqSupIzq->getY());
        NE = PV(esqSupIzq->getX()+ancho, esqSupIzq->getY());
        SE = PV(esqSupIzq->getX()+ancho, esqSupIzq->getY()-alto);
        SO = PV(esqSupIzq->getX(), esqSupIzq->getY()-alto);

        vertices = new PV*[4];
        vertices[0] = new PV(esqSupIzq->getX(), esqSupIzq->getY());
        vertices[1] = new PV(esqSupIzq->getX()+ancho, esqSupIzq->getY());
        vertices[2] = new PV(esqSupIzq->getX()+ancho, esqSupIzq->getY()-alto);
        vertices[3] = new PV(esqSupIzq->getX(), esqSupIzq->getY()-alto);
        posicion = esqSupIzq;

        nVertices = size;
        calculaNormales();
}

// Destructora
Rectangulo::~Rectangulo() {
}

// Metodo que pinta las paredes
void Rectangulo::Pinta() {
        glBegin(GL_POLYGON);
                for (int i = 0; i<nVertices; i++){
                        glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();

///////////////////////////////////
  PV** puntosMedios = new PV*[nVertices];
  for (int i = 0; i < nVertices; i++) {
    PV* unPunto = vertices[i];
    PV* otroPunto = vertices[(i+1)%nVertices];
    PV* puntoMedio = new PV((unPunto->getX() + otroPunto->getX())/2, (unPunto->getY() + otroPunto->getY())/2);
    puntosMedios[i] = puntoMedio;
  }
       glColor3f(1.0, 1.0, 1.0);
        glBegin(GL_LINES);
        for (int i = 0; i<nVertices; i++){
                glVertex2d(puntosMedios[i]->getX(), puntosMedios[i]->getY());
                glVertex2d(puntosMedios[i]->getX()+(normales[i]->getX()*20), puntosMedios[i]->getY()+(normales[i]->getY()*20));
                }
        glEnd();
}

bool Rectangulo::Corte(Pelota* pelota, GLdouble &tIn, PV* &normal) {

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


#pragma package(smart_init)

