//---------------------------------------------------------------------------
 #include <vcl.h>
#pragma hdrstop
#include "Pelota.h"
//---------------------------------------------------------------------------
// Constructora
Pelota::Pelota() {
        Lapiz * l = new Lapiz();
        list<PV>* listaVertices = new list<PV>();
        l->poligonoR2(new PV(0,0), 10, 30, listaVertices);
        vertices = listaVertices;
        delete l;
        centro = new PV(0,0);
        radio = 10;
}


Pelota::Pelota(PV * c, GLfloat r ) {
        Lapiz * l = new Lapiz();
        list<PV>* listaVertices = new list<PV>();
        l->poligonoR2(c , r, 30, listaVertices);
        delete vertices;
        vertices = listaVertices;
        delete l;
        centro = c->clon();
        radio = r;
}

// Destructora
Pelota::~Pelota() {

}

// Metodo que pinta la pelota
void Pelota::Pinta() {
        glBegin(GL_POLYGON);
            list<PV>::iterator it;
            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}

void Pelota::avanza(GLdouble t){

}
#pragma package(smart_init)
