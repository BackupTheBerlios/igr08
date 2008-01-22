//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Circulo.h"
//---------------------------------------------------------------------------
// Constructora
Circulo::Circulo() : Obstaculo() {

}

Circulo::Circulo(PV* c, GLfloat r) : Obstaculo(){
        Lapiz * l = new Lapiz();
        PV** listaVertices = new PV*();
        l->poligonoR2(c, r, 30, listaVertices);
        delete vertices;
        vertices = listaVertices;
        delete l;
        centro = c->clon();
        radio = r;
}

// Destructora
Circulo::~Circulo() {
        
}

// Metodo que pinta circulo
void Circulo::Pinta() {
        glBegin(GL_POLYGON);
//            list<PV>::iterator it;
            for (int i = 0 ; i< nVertices; i++){
//            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();
}

bool Circulo::corte(){
}
#pragma package(smart_init)

