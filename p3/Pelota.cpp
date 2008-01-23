//---------------------------------------------------------------------------
 #include <vcl.h>
#pragma hdrstop
#include "Pelota.h"
//---------------------------------------------------------------------------
// Constructora
Pelota::Pelota() {
        Lapiz * l = new Lapiz();
        int numVert = 30;
        PV** listaVertices = new PV*[numVert];
        l->poligonoR2(new PV(100,0), 10, numVert, listaVertices);
        vertices = listaVertices;
        delete l;
        centro = new PV(100,0);
        radio = 10;
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

#pragma package(smart_init)
