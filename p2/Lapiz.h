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
      void poligonoR1 (GLfloat lado, int nlados, DibujoLineas* dl);
      void poligonoR2 (Punto2f * centro, GLfloat radio, int nlados, DibujoLineas* dl);
      void arco(Punto2f * inicio, Punto2f * fin, Punto2f *otro, int nlados, DibujoLineas* dl);


      void Lapiz::arcoR2 (Punto2f * centro, GLfloat radio, int nlados, GLdouble angInicial, GLdouble angFinal, DibujoLineas* dl);
      void Bezier(Punto2f** ,int , int, DibujoLineas*);
      void Casteljau(float , Punto2f** , Punto2f* , int );

      //void B_Splines(Punto2f** ,int ,int ,int ,DibujoLineas*);
      void B_Splines(Punto2f** ,int ,int,DibujoLineas*); 

   private:
      Punto2f * pos;
      GLdouble ang;  // Radianes

      // Funciones Auxiliares
      //double Base(float, int, int, int);
      //float Nudo(int, int, int);
      double Base(float, int);
      void arco(Punto2f * centro, GLdouble radio, GLdouble angInicial, GLdouble angFinal, int nlados, DibujoLineas*);
};

#endif
