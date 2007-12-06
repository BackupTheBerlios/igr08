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

// M�todo clon
Punto2f * Punto2f::clon() const {
        Punto2f* p1 = new Punto2f();
        *p1 = *this;
        return p1;
}

// Dibuja un punto
void Punto2f::Pinta(){
        glBegin(GL_POINTS);
          glVertex2f(x,y);
        glEnd();
      }


