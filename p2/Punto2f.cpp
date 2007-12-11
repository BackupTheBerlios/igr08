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

String Punto2f::toString(){
String retVal = "x:"+  String (x)+", ";
retVal += "y:"+ String (y) +"; ";

return retVal;
}

Punto2f * Punto2f::puntoMedio(Punto2f *otro){
    GLfloat x = this->getX()+otro->getX();
    GLfloat y = this->getY()+otro->getY();
    Punto2f *retVal = new Punto2f(x,y);
}

Punto2f *  Punto2f::operator-( Punto2f * otro) {

    GLfloat x = this->getX() - otro->getX();
    GLfloat y = this->getY() - otro->getY();
    Punto2f * retVal = new Punto2f(x,y);
    return retVal;
  }
