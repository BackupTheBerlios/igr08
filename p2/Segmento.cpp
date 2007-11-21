//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Segmento.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Segmento::Segmento() {
        inicio = new Punto2f (0.0, 0.0);
        final  = new Punto2f (0.0, 0.0);
}

// Constructora por parámetros
Segmento::Segmento(Punto2f * i, Punto2f * f) {
        inicio = i->clon();
        final = f->clon();
}

// Destructora
Segmento::~Segmento(){
         delete inicio;
         delete final;
}
