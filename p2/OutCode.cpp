//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "OutCode.h"

//---------------------------------------------------------------------------

    bool OutCode::tieneAlgunaCoordenadaIgualQue(OutCode c){
        return (igual(this->arriba,arriba) |
                igual(this->abajo, abajo) |
                igual(this->derecha,derecha) |
                igual(this->izquierda,izquierda) );
    }

    bool OutCode::operator== (OutCode) const{
    bool retVal= true;
//    this->arriba;
    return retVal;
    }

#pragma package(smart_init)
