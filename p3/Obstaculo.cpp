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
Obstaculo::Obstaculo(PV* pos,PV** vertices,int v) {
     posicion = pos;
     velocidad = v;
}

// Destructora
Obstaculo::~Obstaculo() {

}

// Metodo que pinta la pelota
void Obstaculo::Pinta() {

}
#pragma package(smart_init)





