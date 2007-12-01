//---------------------------------------------------------------------------
#ifndef DibujoLineasH
#define DibujoLineasH

#include "Lista.h"
#include "Segmento.h"
//---------------------------------------------------------------------------

class DibujoLineas {

   public:
      DibujoLineas();
      ~DibujoLineas(){ delete listaSegmentos;}
      Lista<Segmento> * getDibujoLineas() {return listaSegmentos;}
      void Pinta();
      void inserta(Segmento * s);

   private:
      Lista<Segmento> * listaSegmentos;

};

#endif