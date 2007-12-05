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
        if (this->arriba != OutCode->arriba){
        return false;
        }
        if (this->abajo != OutCode->abajo){
        return false;
        }

        if (this->derecha != OutCode->derecha){
        return false;
        }

        if (this->izquierda != OutCode->izquierda){
        return false;
        }
        return true;
    }

#pragma package(smart_init)
