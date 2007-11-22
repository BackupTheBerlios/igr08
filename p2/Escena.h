//---------------------------------------------------------------------------
#ifndef EscenaH
#define EscenaH

#include "DibujoLineas.h"
//---------------------------------------------------------------------------

class Escena {

   public:
      Escena();
      void Resize();
      void Pinta();
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}

   private:
      Lista<DibujoLineas> * listaDibujos;

};

#endif
