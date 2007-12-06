//---------------------------------------------------------------------------
#ifndef LapizH
#define LapizH

#include "Punto2f.h"
#include "Segmento.h"
#include "DibujoLineas.h"
#include "conversiones.h"
//---------------------------------------------------------------------------

class Lapiz {

   public:
      Lapiz();
      Lapiz(Punto2f * pos, GLfloat ang);
      Lapiz (const Lapiz&);
      Lapiz * clon() const;
      ~Lapiz(){delete pos;}

      void lineTo (Punto2f *, bool);
      void gira (GLdouble incrAng);
      void avanza (GLfloat longitud, bool esVisible, Segmento*&);
      void drawMotivo(Lapiz *l);
      void drawTotal (Punto2f * posInicial, GLfloat angInicial);
      void poliEspiral ( Punto2f * posInicial, GLfloat incrAng,
                   GLfloat angInicial, GLfloat incrLong,
                   GLfloat longInicial, int nPasos, DibujoLineas*);
      void poligonoR1 (Lapiz * l, GLfloat lado, int nlados);
      void poligonoR2 (Punto2f * centro, GLfloat radio, int nlados);
      void arco(Punto2f * inicio, Punto2f * fin, Punto2f *otro);
      void Bezier(Punto2f** ,int , Punto2f**);
      Punto2f* calcularBezier(Punto2f**,float);

   private:
      Punto2f * pos;
      GLdouble ang;  // Radianes
};

#endif
