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
}

// Destructora
Convexo::~Convexo() {

}

// Metodo que pinta el poligono convexo
void Convexo::Pinta() {
        glBegin(GL_POLYGON);
        for (int i = 0; i<nVertices; i++){
                glVertex2d(this->vertices[i]->getX(), this->vertices[i]->getY());
                }
        glEnd();
}


#pragma package(smart_init)
