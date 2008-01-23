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
                //list<PV>::iterator it;
                for (int i = 0; i<nVertices; i++){
//                for( it = vertices->begin(); it != vertices->end(); it++ ) {
                        glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();
}

bool Rectangulo::Corte(Pelota* pelota, GLdouble &tIn, PV* &normal) {

GLfloat epsilon = 0.000001f;

  tIn = 0;
  GLdouble tOut = 1;

  GLdouble tHit, num, den;
  PV * n;
  int i = -1;
  bool acabado = false;
  
 while ((i < nVertices - 1) && !acabado) {
   i++;
   n = normales[i];
   PV * verticeI = vertices[i];
   //PV  ptang = pelota->getPuntoTangente(n);
   //   PV  * ptang = pelota->getCentro();
   //PV * tmpVector = *vertices[i] - *pelota->getCentro();
   //num = tmpVector->dot(n);

   num = (*vertices[i] - *pelota->getCentro())->dot(n);
   den = n->dot(pelota->getDireccion());
   if (fabs(den) > epsilon) { // hay tHit
      tHit = num / den;
      if (den > 0) {  // Salida
         tOut = min (tOut, tHit);
         }
      else{
         tIn = max (tIn, tHit);
         acabado = tIn > tOut; // si se han cruzado, no hay intersección
         }
   }
   else { // paralelismo
      if (num <= 0) acabado = true;
         }
}
  return !acabado && !((tIn == 0) && (tIn <= tOut) && (tOut < epsilon));
}



#pragma package(smart_init)

