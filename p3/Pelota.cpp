//---------------------------------------------------------------------------
 #include <vcl.h>
#pragma hdrstop
#include "Pelota.h"
//---------------------------------------------------------------------------
// Constructora
Pelota::Pelota() {
        Lapiz * l = new Lapiz();
        radio = 10;
        int numVert = 30;
        PV** listaVertices = new PV*[numVert];
        l->poligonoR2(new PV(100,0), radio, numVert, listaVertices);
        vertices = listaVertices;
        delete l;
        centro = new PV(100,0);
        direccion = new PV (4,2);
        nVertices = numVert;
}


/*Pelota::Pelota(PV * c, GLfloat r ) {
        Lapiz * l = new Lapiz();
        list<PV>* listaVertices = new list<PV>();
        l->poligonoR2(c , r, 30, listaVertices);
        delete vertices;
        vertices = listaVertices;
        delete l;
        centro = c->clon();
        radio = r;
}   */

// Destructora
Pelota::~Pelota() {
        delete centro;
        delete direccion;
}

// Metodo que pinta la pelota
void Pelota::Pinta() {
        glBegin(GL_POLYGON);
                for (int i = 0; i<nVertices; i++){
                        glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();
}

void Pelota::avanza(GLdouble t){

centro->setX(centro->getX() + t * direccion->getX());
centro->setY(centro->getY() + t * direccion->getY());


for (int i = 0; i<nVertices; i++){
     vertices[i]->setX(vertices[i]->getX() + t * direccion->getX());
     vertices[i]->setY(vertices[i]->getY() + t * direccion->getY());
}

}

PV Pelota::getPuntoTangente(PV* normal)
{
 GLdouble coef = radio / normal->modulo();
 return PV(centro->getX() - coef * normal->getX(), centro->getY() - coef * normal->getY()); 
}

  /*
void Pelota::rebota(PV* n){

}
 direccion->setX(-direccion->getX());
direccion->setY(-direccion->getY());
  */
void Pelota::rebota(PV* n){
     // GLdouble factor = (-2)* direccion->dot(n);
      //PV * aux = n->multiplicar(factor);
     // direccion = *direccion+*aux;
     // delete aux;
     //PV* aux  = *n - *n->escalar(2 * n->dot(n)/n->dot(n));

     direccion = *centro - *n->escalar(2 * centro->dot(n)/n->dot(n));
     direccion = direccion->unitario();
     int col = 1;
     //direccion->setX(direccion->getX() + n->getX());
     //direccion->setY(direccion->getY() + n->getY());

    // u.resta( n.escalar( 2 * u.dot(n) / n.dot(n) ) );
    // n = aux;
    // delete aux;
}
#pragma package(smart_init)

