//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Segmento.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Segmento::Segmento() {
        inicio = new Punto2f (0.0, 0.0);
        final  = new Punto2f (0.0, 0.0);
}

// Constructora por parámetros
Segmento::Segmento(Punto2f * i, Punto2f * f) {
        inicio = i->clon();
        final = f->clon();
}

// Destructora
Segmento::~Segmento(){
         delete inicio;
         delete final;
}

// Dibuja segmento actual
void Segmento::Pinta(){
        if((inicio!=NULL) & (final!=NULL)){
                glBegin(GL_LINES);
                        glVertex2f(inicio->getX() , inicio->getY());
                        glVertex2f(final->getX() , final->getY());
                glEnd();
        }
      }

// Dibuja Puntos de control
void Segmento::Pinta2(){
        if((inicio!=NULL) & (final!=NULL)){
                glBegin(GL_POINTS);
                        glVertex2f(inicio->getX() , inicio->getY());
                        glVertex2f(final->getX() , final->getY());
                glEnd();
        }
      }

Segmento * Segmento::clon() const{
       Punto2f* i = new Punto2f(inicio->getX(), inicio->getY());
       Punto2f* f = new Punto2f(final->getX(), final->getY());
       Segmento * r = new Segmento(i,f);
       delete i;
       delete f;
       return r;

}

bool Segmento::contiene(Punto2f* p){

   if ((inicio->getX() < p->getX() + 10)&&(inicio->getX() > p->getX() - 10)&&
      (inicio->getY() < p->getY() + 10)&&(inicio->getY() > p->getY() - 10))
      return true;

   if ((final->getX() < p->getX() + 10)&&(final->getX() > p->getX() - 10)&&
      (final->getY() < p->getY() + 10)&&(final->getY() > p->getY()- 10))
      return true;

   return false;
}

void Segmento::setPuntosNull(){
        inicio=NULL;
        final=NULL;
}

String Segmento::toString(){
        if (inicio !=NULL & final !=NULL){
                String retVal = inicio->toString();
                retVal += "<>"+final->toString();
                return retVal;
        }
        return "";
}


String Segmento::toString2(){
        if (inicio !=NULL & final !=NULL){
                String retVal = inicio->toString2();
                retVal += final->toString2();
                return retVal;
        }
        return "";
}
