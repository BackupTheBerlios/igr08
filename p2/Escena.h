//---------------------------------------------------------------------------
#ifndef EscenaH
#define EscenaH

#include "DibujoLineas.h"
#include "Punto2f.h"
#include "OutCode.h"

#define deltaT 50
//---------------------------------------------------------------------------

class Escena {

   public:
      Escena(int,int, int);
      ~Escena();
      void Resize(int CW, int CH);
      void Pinta();

      void inserta(DibujoLineas * p);
      void transformarXY(Punto2f *);//, int, int);
      void Seleccionar(Punto2f*);
      void Deseleccionar();
      void BorrarSeleccionado();

      void teclado(WORD&);

      void setNombre(AnsiString cadena) { nombre = cadena; }
      void setModificada(bool valor) { modificado = valor; }
      void setHDC(HDC h){ hdc=h; }

      AnsiString getNombre() { return nombre; }
      bool Modificada() { return modificado; }
      Lista<DibujoLineas> * getEscena() {return listaDibujos;}
      void Zoom(float factor);
      bool ZoomProgresivo(float factor, int nPasos);
      void recorte(Punto2f * , Punto2f *);

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
      void Escena::CompOutCode(GLdouble x, GLdouble y, GLdouble xmin, GLdouble xmax, GLdouble ymin, GLdouble ymax, OutCode &code);
      void recorteLinea(Punto2f * , Punto2f *, Punto2f *, Punto2f *);
      HDC hdc;
};

#endif
