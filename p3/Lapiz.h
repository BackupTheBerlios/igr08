//---------------------------------------------------------------------------
#ifndef LapizH
#define LapizH

//#include "Punto2f.h"
//#include "Segmento.h"
//#include "DibujoLineas.h"
#include "PV.h"
#include <list.h>
#include "conversiones.h"
//---------------------------------------------------------------------------

class Lapiz {

   public:
      Lapiz();
      Lapiz(PV * pos, GLfloat ang);
/*      Lapiz (const Lapiz&);
      Lapiz * clon() const;*/
      ~Lapiz(){delete pos;}

      void lineTo (PV *, bool);
      void gira (GLdouble incrAng);
      void avanza (GLfloat longitud, bool esVisible, PV*& nuevoPunto/*, Segmento*&*/);
      void poligonoR1 (GLfloat lado, int nlados, list<PV>*  /*, DibujoLineas* dl*/ );
      void poligonoR2 (PV * centro, GLfloat radio, int nlados/* , DibujoLineas* dl*/);
   

   private:
      PV * pos;
      GLdouble ang;  // Radianes
};

#endif
