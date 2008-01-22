//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Obstaculo.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Obstaculo::Obstaculo() {
   vertices = new PV*();
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


#pragma package(smart_init)





