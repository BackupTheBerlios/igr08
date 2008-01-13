//---------------------------------------------------------------------------
#include "Obstaculo.h"
#include <list.h>
//#include "Lista.h"
#include "Pelota.h"
#include "Mando.h"
#include "Rectangulo.h"
#include "Lapiz.h"
#include "Convexo.h"
#ifndef EscenaH
#define EscenaH
//---------------------------------------------------------------------------
class Escena {

   public:
      Escena(int,int);
      ~Escena();

      void Resize(int CW, int CH);
      void Dibuja();

      void InsertaObstaculo(Obstaculo *);
      void TransformarXY(PV *);
      void Teclado(WORD&);

   private:
      GLfloat xRight, xLeft;
      GLfloat yTop, yBot;
      int ClientWidth, ClientHeight;
      float ratioViewPort;
      list <Obstaculo*> * listaDeObstaculos;
      bool estado;

      Mando * mando;
      Pelota * pelota;
      Rectangulo * paredes;

      GLdouble convertirX(int,int);
      GLdouble convertirY(int,int);

      HDC hdc;
};
#endif
