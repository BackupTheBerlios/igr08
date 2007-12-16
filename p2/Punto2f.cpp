//---------------------------------------------------------------------------
#include <vcl.h>
#include "Punto2f.h"
#pragma hdrstop
#include <stdio.h>
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


String Punto2f::toString2(){
   String retVal = String((int)x) + ";";
   retVal += String ((int)y)+";\n";
   return retVal;
}

Punto2f * Punto2f::puntoMedio(Punto2f *otro){
    GLfloat x = this->getX()+otro->getX();
    GLfloat y = this->getY()+otro->getY();
    Punto2f *retVal = new Punto2f(x/2.0,y/2.0);
    return retVal;
}

Punto2f *  Punto2f::operator+( Punto2f otro) {

    GLfloat x = this->getX() + otro.getX();
    GLfloat y = this->getY() + otro.getY();
    Punto2f * retVal = new Punto2f(x,y);
    return retVal;
  }

Punto2f *  Punto2f::operator-( Punto2f otro) {

    GLfloat x = this->getX() - otro.getX();
    GLfloat y = this->getY() - otro.getY();
    Punto2f * retVal = new Punto2f(x,y);
    return retVal;
  }

Punto2f *  Punto2f::multiplicar(GLdouble k){
GLfloat x =  this->getX()*k;
GLfloat y =  this->getY()* k;
     return (new Punto2f(x ,y));
}

Punto2f *  Punto2f::dividir(GLdouble k){
        GLfloat x =  this->getX() / k;
        GLfloat y =  this->getY() / k;
        return (new Punto2f(x ,y));
}

GLdouble Punto2f::dot(Punto2f *otro){
        return (this->getX()* otro->getX()) + (this->getY()* otro->getY());
}

Punto2f * Punto2f::perpendicular(){
        return new Punto2f(-this->getY(),this->getX());
}


GLdouble Punto2f::distancia(Punto2f *otro){
        Punto2f * vectorDistancia = *otro - *this;
        GLdouble x = vectorDistancia->getX();
        GLdouble y = vectorDistancia->getY();
        delete vectorDistancia;
        return (sqrt(x*x+y*y));
}

GLdouble Punto2f::modulo(){
        return (sqrt(x*x+y*y));
}


GLdouble Punto2f::angulo2(Punto2f *otro){
            Punto2f * div = *otro-*this;
            GLdouble retVal;
            GLdouble tg = div->getX() / (GLdouble) div->getY();
            retVal = atan(tg);
            retVal = r2g(retVal);
            if (otro->getX()< this->getX())
            {
                if (otro->getY()< this->getY()){
                        retVal = 90+ (retVal*-1);
                }
                else {
                        retVal = 90+ (retVal*-1);
                }
            }



            delete div;
            return retVal;
}

/*GLdouble Punto2f::angulo3(Punto2f *otro){
            Punto2f * div = *this-*otro;
            Punto2f * horizontal = new Punto2f(1000,0);
            GLdouble dividendo = div->dot(horizontal);
            GLdouble mod1, mod2;
            mod1 = horizontal->modulo();
            mod2 = otro->modulo();
            GLdouble divisor = mod1 * mod2;

            GLdouble retVal = r2g(acos(dividendo / divisor));
            if ((div->getY()<0) & (div->getX()<0)){
                retVal = retVal - 90;
            }
            if ((div->getY()>0) & (div->getX()<0)){
                retVal = retVal + 90;
            }

            if ((div->getY()<0) & (div->getX()>0)){
              //  retVal = retVal + 90;
            }

            if ((div->getY()>0) & (div->getX()>0)){
                retVal = retVal +275;
            }
            delete div;
            delete horizontal;
            return retVal;
}     */
