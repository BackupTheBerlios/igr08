//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "OutCode.h"

//---------------------------------------------------------------------------

    bool OutCode::tieneAlgunaCoordenadaTrueIgualQue(OutCode c){
        bool ar = igual(this->arriba,c.arriba) & c.arriba;
        bool ab = igual(this->abajo, c.abajo) & c.abajo;
        bool dr = igual(this->derecha,c.derecha) & c.derecha;
        bool iz = igual(this->izquierda,c.izquierda) & c.izquierda;
        return (ar | ab | dr | iz);
        /*        return (igual(this->arriba,c.arriba) |
                igual(this->abajo, c.abajo) |
                igual(this->derecha,c.derecha) |
                igual(this->izquierda,c.izquierda) );*/
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
