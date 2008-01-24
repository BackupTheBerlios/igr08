//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#pragma package(smart_init)

#include "PV.h"
//---------------------------------------------------------------------------
// Constructora de copia
PV::PV (const PV& p){
     x = p.x;
     y = p.y;
}

PV::PV (PV  inicio, PV  fin){
 x = (inicio - fin)->getX();
 y = (inicio - fin)->getY();
}

// Método clon
PV* PV::clon() const {
     PV* p1 = new PV();
     *p1 = *this;
     return p1;
}

// Dibuja un punto
void PV::Pinta(){
      glBegin(GL_POINTS);
      glVertex2f(x,y);
      glEnd();
}

// Calcula el punto medio de dos puntos
PV * PV::puntoMedio(PV *otro){
    GLdouble x = this->getX() + otro->getX();
    GLdouble y = this->getY() + otro->getY();
    PV *retVal = new PV(x/2.0,y/2.0);
    return retVal;
}

// Producto escalar
PV * PV::escalar(GLdouble num) {
     GLdouble x = this->getX() * num;
     GLdouble y = this->getY() * num;
     PV * escalar = new PV(x,y);
     return escalar;
}

// Calcula el prodcuto escalar de dos puntos
GLdouble PV::dot(PV *otro){
     return (this->getX()* otro->getX()) + (this->getY()* otro->getY());
}

// Devuelve la perpendicular de un punto dado
PV PV::perpendicular(){
     return PV(-this->getY(),this->getX());
}

// Calcula la distancia a otro punto
GLdouble PV::distancia(PV *otro){
     PV * vectorDistancia = *otro - *this;
     GLdouble x = vectorDistancia->getX();
     GLdouble y = vectorDistancia->getY();
     delete vectorDistancia;
     return (sqrt(x*x+y*y));
}

// Operación Suma
PV * PV::operator+(PV otro) {
     GLdouble x = this->getX() + otro.getX();
     GLdouble y = this->getY() + otro.getY();
     PV * retVal = new PV(x,y);
     return retVal;
  }

// Operación Resta
PV * PV::operator-(PV otro) {
     GLdouble x = this->getX() - otro.getX();
     GLdouble y = this->getY() - otro.getY();
     PV * retVal = new PV(x,y);
     return retVal;
  }

// Operación Producto
PV * PV::multiplicar(GLdouble k){
     GLdouble x =  this->getX()*k;
     GLdouble y =  this->getY()* k;
     return (new PV(x ,y));
}

// Operación División
PV * PV::dividir(GLdouble k){
     GLdouble x =  this->getX() / k;
     GLdouble y =  this->getY() / k;
     return (new PV(x ,y));
}

// Modulo de un punto
GLdouble PV::modulo(){
     return (sqrt(x*x+y*y));
}

// Normaliza un PV
void PV::normaliza() {
    GLdouble x = this->getX() / sqrt(this->getX() *
                 this->getX() + this->getY() *
                 this->getY());
    
    GLdouble y = this->getY() / sqrt(this->getX() *
                 this->getX() + this->getY() *
                 this->getY());
    this->setX(x);
    this->setY(y);
}

// Angulo con respecto a otro punto
GLdouble PV::angulo(PV *otro){
      PV * div = *otro-*this;
      GLdouble retVal;
      GLdouble tg = div->getX() / (GLdouble) div->getY();
      retVal = atan(tg);
      retVal = r2g(retVal);
      if (otro->getX()< this->getX()) {
        if (otro->getY()< this->getY()){
           retVal = 180+ (90 - retVal);
        }
        else {
           retVal = 90+ (retVal*-1);
        }
       }
      else {
        if (otro->getY()< this->getY()) {
           retVal = (90+ retVal)*-1;
        }
        else {
           retVal = 90-retVal;
        }
      }

      delete div;
      return retVal;
}


        PV * PV::unitario(){
         GLdouble modulo = this->modulo();
         PV * retVal = new PV(x/modulo, y/modulo);
         return retVal;
        }


      PV * PV::inversa(){
         PV * retVal = new PV(-x, -y);
         return retVal;
      }
