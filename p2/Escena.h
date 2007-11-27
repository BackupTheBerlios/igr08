//---------------------------------------------------------------------------
#ifndef EscenaH
#define EscenaH

#include "DibujoLineas.h"
#include "Punto2f.h"
//---------------------------------------------------------------------------

class Escena {

   public:
      Escena(int);
      ~Escena();
      void Resize(int CW, int CH);
      void Pinta();

      void inserta(DibujoLineas * p);
      void transformarXY(Punto2f *, int, int);

      void setNombre(AnsiString cadena) { nombre = cadena; }
      void setModificada(bool valor) { modificado = valor; }

      AnsiString getNombre() { return nombre; }
      bool Modificada() { return modificado; }
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}
      void Zoom(float factor);
      void ZoomProgresivo(float factor, int nPasos);

   private:
      int xRight, xLeft;
      int yTop, yBot;
      float ratioViewPort;
      bool modificado;
      AnsiString nombre;
      Lista<DibujoLineas> * listaDibujos;

      GLdouble convertirX(int,int);
      GLdouble convertirY(int,int);
};

#endif
