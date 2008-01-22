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
        PV** listaVertices = new PV*();
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
            list<PV>::iterator it;

//            for( it = vertices->begin(); it != vertices->end(); it++ ) {
        for (int i = 0; i<nVertices; i++){
                glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}


#pragma package(smart_init)
