//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop
#include "Punto2f.h"


//---------------------------------------------------------------------------

#pragma package(smart_init)

Punto2f * Punto2f::clon() const {
        Punto2f* p = new Punto2f();
        *p = *this;
        return p;
}

