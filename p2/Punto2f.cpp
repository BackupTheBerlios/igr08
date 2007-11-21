//---------------------------------------------------------------------------
#include <vcl.h>
#include "Punto2f.h"
#pragma hdrstop
#pragma package(smart_init)
//---------------------------------------------------------------------------

// Constructora de copia
Punto2f::Punto2f (const Punto2f& p){
        x = p.x;
        y = p.y;
}

// Método clon
Punto2f * Punto2f::clon() const {
        Punto2f* p = new Punto2f();
        *p = *this;
        return p;
}

