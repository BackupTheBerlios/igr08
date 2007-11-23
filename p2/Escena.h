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

      void setNombre(AnsiString cadena) { nombre = cadena; }
      void setModificada(bool valor) { estado = valor; }

      AnsiString getNombre() { return nombre; }
      bool Modificada() { return estado; }
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}

   private:
      int xR, xL, yT, yB;
      Lista<DibujoLineas> * listaDibujos;
      AnsiString nombre;
      bool estado;
};

#endif
