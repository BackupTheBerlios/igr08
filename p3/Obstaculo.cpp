//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Obstaculo.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Obstaculo::Obstaculo() {
//   vertices = new PV*();
   vertices = new list<PV>();
}

// Constructora con parametros
Obstaculo::Obstaculo(list<PV>* lv) {
        vertices = lv;
}

// Destructora
Obstaculo::~Obstaculo() {

}

void Obstaculo::Pinta(){

}
#pragma package(smart_init)





