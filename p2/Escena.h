//---------------------------------------------------------------------------
#ifndef EscenaH
#define EscenaH

#include "DibujoLineas.h"
#include "Punto2f.h"
//---------------------------------------------------------------------------

class Escena {

   public:
      Escena();
      ~Escena();
      void Resize(int CW, int CH);
      void Pinta();

      void inserta(DibujoLineas * p);

      void setNombre(AnsiString cadena) { nombre = cadena; }
      void setModificada(bool valor) { modificado = valor; }

      AnsiString getNombre() { return nombre; }
      bool Modificada() { return modificado; }
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}
      void Zoom(float factor);
      void ZoomProgresivo(float factor, int nPasos);

   private:
      int xR, xL, yT, yB;
      Lista<DibujoLineas> * listaDibujos;
      AnsiString nombre;
      bool modificado;
};

#endif
