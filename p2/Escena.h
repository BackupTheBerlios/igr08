//---------------------------------------------------------------------------
#ifndef EscenaH
#define EscenaH

#include "DibujoLineas.h"
#include "Punto2f.h"
#include "OutCode.h"

#define deltaT 100
//---------------------------------------------------------------------------

class Escena {

   public:
      Escena(int,int, int);
      ~Escena();
      void Resize(int CW, int CH);
      void Pinta();

      void inserta(DibujoLineas * p);
      void transformarXY(Punto2f *, int, int);

      void teclado(WORD&);

      void setNombre(AnsiString cadena) { nombre = cadena; }
      void setModificada(bool valor) { modificado = valor; }
      void setHDC(HDC h){ hdc=h; }

      AnsiString getNombre() { return nombre; }
      bool Modificada() { return modificado; }
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}
      void Zoom(float factor);
      bool ZoomProgresivo(float factor, int nPasos);
      Escena * recorte(Punto2f *, Punto2f *);


   private:
      GLfloat xRight, xLeft;
      GLfloat yTop, yBot;
      int ClientWidth, ClientHeight; // Dimensiones de la ventana donde esta la escena
      float ratioViewPort;
      bool modificado;
      AnsiString nombre;
      Lista<DibujoLineas> * listaDibujos;

      GLdouble convertirX(int,int);
      GLdouble convertirY(int,int);
      void CompOutCode(GLdouble x, GLdouble y, OutCode &code);

      HDC hdc;
};

#endif
