#include "PV.h"
#include <list.h>


#ifndef LapizH
#define LapizH

//#include "Punto2f.h"
//#include "Segmento.h"
//#include "DibujoLineas.h"

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
      void poligonoR1 (GLfloat lado, int nlados, PV**  /*, DibujoLineas* dl*/ );
      void poligonoR2 (PV * centro, GLfloat radio, int nlados, PV** listaVertices/* , DibujoLineas* dl*/);

      void poligonoR2 (PV * centro, GLfloat, int, PV**);

   private:
      PV * pos;
      GLdouble ang;  // Radianes
      // Convertir GRADOS a RADIANES
        GLdouble r2g(GLdouble radianes){ return radianes * 180.0 / M_PI;}

      // Convertir RADIANES a GRADOS
      GLdouble g2r(GLdouble grados){return grados *  M_PI / 180.0;}

};

#endif
