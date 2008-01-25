//---------------------------------------------------------------------------
#include "Pelota.h"
#include "Mando.h"
#include "Rectangulo.h"
#include "Lapiz.h"
#include "Obstaculo.h"
#include "Convexo.h"
#include "Circulo.h"
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
      void TransformarXY(PV *){}
      void Teclado(WORD&);
      void avanza();

      bool getJuego();
      void setJuego();
      int getNumVidas();

   private:
      GLfloat xRight, xLeft;
      GLfloat yTop, yBot;
      int ClientWidth, ClientHeight;
      float ratioViewPort;
      Obstaculo** listaDeObstaculos;
      bool estado;
      int numVidas;
      int numObstaculos;

      Mando * mando;
      Pelota * pelota;
      Rectangulo * paredIzq;
      Rectangulo * paredDcha;
      Rectangulo * paredArriba;
      Rectangulo * paredPierde;

      GLdouble convertirX(int,int);
      GLdouble convertirY(int,int);

      HDC hdc;
};
#endif
