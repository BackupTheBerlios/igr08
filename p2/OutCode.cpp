//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "OutCode.h"

//---------------------------------------------------------------------------

    bool OutCode::tieneAlgunaCoordenadaIgualQue(OutCode c){
        return (igual(this->arriba,c.arriba) |
                igual(this->abajo, c.abajo) |
                igual(this->derecha,c.derecha) |
                igual(this->izquierda,c.izquierda) );
    }

    bool OutCode::operator== (OutCode otro) const{
        if (this->arriba != otro.arriba){
                return false;
        }
        if (this->abajo != otro.abajo){
                return false;
        }

        if (this->derecha != otro.derecha){
                return false;
        }

        if (this->izquierda != otro.izquierda){
                return false;
        }
        return true;
    }

#pragma package(smart_init)
