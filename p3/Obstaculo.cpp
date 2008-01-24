//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Obstaculo.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Obstaculo::Obstaculo() {
   vertices = new PV*();
   this->esVisible = true;
//   vertices = new list<PV>();
}

// Constructora con parametros
Obstaculo::Obstaculo(PV** lv) {
        vertices = lv;
}

// Destructora
Obstaculo::~Obstaculo() {
        delete vertices;
}

void Obstaculo::Pinta(){

}

void Obstaculo::calculaNormales(){
  normales = new PV*[nVertices];
  for (int i = 0; i < nVertices; i++) {
    PV* unPunto = vertices[i];
    PV* otroPunto = vertices[(i+1)%nVertices];
    PV* normal = new PV(unPunto->getY() - otroPunto->getY(), otroPunto->getX() - unPunto->getX());
    normal->normaliza();
    normales[i] = normal;
  }
}
#pragma package(smart_init)





