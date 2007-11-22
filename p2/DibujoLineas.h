//---------------------------------------------------------------------------
#ifndef DibujoLineasH
#define DibujoLineasH

#include "Lista.h"
#include "Segmento.h"
//---------------------------------------------------------------------------

class DibujoLineas {

   public:
      DibujoLineas();
      Lista<Segmento> * getDibujoLineas() {return listaSegmentos;}

   private:
      Lista<Segmento> * listaSegmentos;

};

#endif
