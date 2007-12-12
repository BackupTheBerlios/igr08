//---------------------------------------------------------------------------
#ifndef SegmentoH
#define SegmentoH

#include "Punto2f.h"
//---------------------------------------------------------------------------

class Segmento {

   public:
      Segmento();
      Segmento(Punto2f * i, Punto2f * f);
      ~Segmento();

      Punto2f * getInicio(){return inicio;}
      Punto2f * getFinal(){return final;}
      void setInicio(Punto2f * p){inicio = p;}
      void setFinal(Punto2f * p){final = p;}
      void setPuntosNull();
      void Pinta();
      void Pinta2();
      Segmento * clon() const;

      bool contiene(Punto2f*);
      String toString();
      String toString2();
   private:
      Punto2f * inicio;
      Punto2f * final;
};

#endif
