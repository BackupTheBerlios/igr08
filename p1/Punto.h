//---------------------------------------------------------------------------
#ifndef PuntoH
#define PuntoH

#include <gl\gl.h>
//---------------------------------------------------------------------------
class Punto {

  public:
     void nuevo(GLdouble,GLdouble);
     void x(GLdouble);
     void y(GLdouble);
     GLdouble x();
     GLdouble y();

  private:
     GLdouble _x;
     GLdouble _y;
};

#endif
  
