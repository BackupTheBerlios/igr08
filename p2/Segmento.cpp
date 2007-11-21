//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Segmento.h"

//---------------------------------------------------------------------------

#pragma package(smart_init)
    Segmento::Segmento() {
         inicio = new Punto2f (0.0, 0.0);
         final  = new Punto2f (0.0, 0.0);
      }

      Segmento::Segmento(Punto2f i, Punto2f f) {
         inicio = i.clon();
         final = f.clon();
      }


   Segmento::~Segmento(){
         delete inicio;
         delete final;
   }
