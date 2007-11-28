//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "DibujoLineas.h"
//---------------------------------------------------------------------------

// Constructora por defecto
DibujoLineas::DibujoLineas() {
  listaSegmentos = new Lista <Segmento>();
  listaSegmentos->inicia();
}
void DibujoLineas::Pinta(){
   listaSegmentos ->inicia();
   while(!listaSegmentos->final()){
        listaSegmentos->getActual()->Pinta();
        listaSegmentos->avanza();
   }
}

void DibujoLineas::inserta(Segmento * s) {
    listaSegmentos->inserta(s);
}
