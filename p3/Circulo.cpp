//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Circulo.h"
//---------------------------------------------------------------------------
// Constructora
Circulo::Circulo() : Obstaculo() {

}

Circulo::Circulo(PV* centro, GLfloat radio) : Obstaculo(){
        Lapiz * l = new Lapiz();
        list<PV>* listaVertices = new list<PV>();
        l->poligonoR2(centro, radio, 30, listaVertices);
        delete vertices;
        vertices = listaVertices;
        int a = 3;
        delete l;

}

// Destructora
Circulo::~Circulo() {
        delete vertices;        
}

// Metodo que pinta circulo
void Circulo::Pinta() {
        glBegin(GL_POLYGON);
            list<PV>::iterator it;
            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}
#pragma package(smart_init)

