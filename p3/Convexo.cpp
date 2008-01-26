//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Convexo.h"

//---------------------------------------------------------------------------
// Constructora
Convexo::Convexo() : Obstaculo() {}
Convexo::Convexo(PV** listaVertices): Obstaculo(listaVertices) {calculaNormales();}

Convexo::Convexo(PV* centro, GLfloat radio, int nlados) : Obstaculo(){
        Lapiz * l = new Lapiz();
        nVertices = nlados;
        PV** listaVertices = new PV*[nVertices];
//        PV** listaVertices = new PV*();
        l->poligonoR2(centro, radio, nlados, listaVertices);

        delete vertices;
        vertices = listaVertices;
        delete l;
        calculaNormales();
        for (int i = 0; i < nVertices; i++) {
            normales[i] = normales[i]->inversa();
        }
        int borrar = 0;
}

// Destructora
Convexo::~Convexo() {

}

// Metodo que pinta el poligono convexo
void Convexo::Pinta() {
   glColor3f(0.0, 0.0, 1.0);
        glBegin(GL_POLYGON);
        for (int i = 0; i<nVertices; i++){
                glVertex2d(this->vertices[i]->getX(), this->vertices[i]->getY());
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

bool Convexo::Corte(Pelota* pelota, GLdouble &tIn, PV* &normal) {

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
