//---------------------------------------------------------------------------
#ifndef EscenaH
#define EscenaH

#include "DibujoLineas.h"
#include "Punto2f.h"
//---------------------------------------------------------------------------

class Escena {

   public:
      Escena();

      void Resize();
      void Pinta();

      void inserta(Punto2f * p);

      void setNombre(AnsiString cadena) { nombre = cadena; }
      void setModificada(bool valor) { estado = valor; }

      AnsiString getNombre() { return nombre; }
      bool Modificada() { return estado; }
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}
      void Zoom(float factor);
      void ZoomProgresivo(float factor, int nPasos);

   private:
      int xR, xL, yT, yB;
      Lista<DibujoLineas> * listaDibujos;
      AnsiString nombre;
      bool estado;
};

#endif
