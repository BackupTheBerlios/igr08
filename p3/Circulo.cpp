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



//---------------------------------------------------------------------------
/*bool Circulo::corte(Pelota* pelota, GLdouble &tIn, PV* &normal)
{
  PV *pc = new PV(centro->getX() - pelota->getCentro()->getX(), centro->getY() - pelota->getCentro()->getY());
  PV *s = pelota->getDireccion();
  PV *sT = new PV(- s->getY(), s->getX());
  GLdouble a = pc->dot(*s) / s->dot(*s);
  GLdouble b = pc->productoEscalar(*sT) / s->productoEscalar(*s);
  GLdouble d0 = fabs(b) * sT->getModulo();
  delete pc;
  delete sT;
  if (d0 < (pelota->getRadio() + radio)) {
    GLdouble d2 = sqrt(pow(pelota->getRadio() + radio, 2) - pow(d0, 2));
    GLdouble d1 = fabs(a) * s->getModulo() - d2;
    tIn = d1 / s->getModulo();
    PV *puntoContacto = new PV(pelota->getCentro()->getX() + tIn * pelota->getSentido()->getX(),pelota->getCentro()->getY() + tIn * pelota->getSentido()->getY());
    PV unaNormalTemp = puntoContacto->menos(*centro);
    GLdouble mod = unaNormalTemp.getModulo();
    if (unaNormal != NULL)
      delete unaNormal;
    unaNormal = new PV(unaNormalTemp.getX() / mod, unaNormalTemp.getY() / mod);
    normal = unaNormal;
    delete puntoContacto;
    return (a > 0) && (s->getModulo() > d1);
  } else
    return false;
}       */
//---------------------------------------------------------------------------
