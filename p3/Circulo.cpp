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
        unaNormal = new PV(0,1);

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


//---------------------------------------------------------------------------
bool Circulo::Corte(Pelota* pelota, GLdouble &tIn, PV* &normal) {
  PV * pc =  new PV(*centro->operator -(*pelota->getCentro()));
  PV *pc_borrar = new PV(centro->getX() - pelota->getCentro()->getX(), centro->getY() - pelota->getCentro()->getY());
  PV *s = pelota->getDireccion();
  PV *sT = new PV(- s->getY(), s->getX());
  GLdouble a = pc->dot(s) / s->dot(s);
  GLdouble b = pc->dot(sT) / s->dot(s);
  GLdouble d0 = fabs(b) * sT->modulo();
  delete pc;
  delete sT;
  if (d0 < (pelota->getRadio() + radio)) {
    GLdouble d2 = sqrt(pow(pelota->getRadio() + radio, 2) - pow(d0, 2));
    GLdouble d1 = fabs(a) * s->modulo() - d2;
    tIn = d1 / s->modulo();
    PV *puntoContacto = new PV(pelota->getCentro()->getX() + tIn * pelota->getDireccion()->getX(),pelota->getCentro()->getY() + tIn * pelota->getDireccion()->getY());
//    PV unaNormalTemp = puntoContacto->menos(*centro);
    PV * unaNormalTemp = *puntoContacto-*centro;
    GLdouble mod = unaNormalTemp->modulo();
   // falta definir la variable unaNormal ...
    if (unaNormal != NULL)
      delete unaNormal;
    unaNormal = new PV(unaNormalTemp->getX() / mod, unaNormalTemp->getY() / mod);
    normal = unaNormal;
    delete puntoContacto;
    return (a > 0) && (s->modulo() > d1);
  } else
    return false;
}
//---------------------------------------------------------------------------

#pragma package(smart_init)
