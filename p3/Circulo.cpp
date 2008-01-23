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
        nVertices = 30;
        PV** listaVertices = new PV*[nVertices];
        l->poligonoR2(c, r, nVertices, listaVertices);
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
            for (int i = 0 ; i< nVertices; i++){
                glVertex2d(vertices[i]->getX(), vertices[i]->getY());
                }
        glEnd();
}

bool Circulo::corte(){
}
#pragma package(smart_init)

