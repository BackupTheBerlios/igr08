//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Escena.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Escena::Escena(){
      listaDibujos = new Lista <DibujoLineas>();
      listaDibujos -> inicia();
}

void Escena::Resize(){

}


void Escena::Pinta(){

}

void Escena::inserta(Punto2f * p) {

}

