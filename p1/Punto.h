//---------------------------------------------------------------------------
#ifndef PuntoH
#define PuntoH

#include <gl\gl.h>
//---------------------------------------------------------------------------
class Punto {

  public:
     void nuevo(GLint,GLint);
     void x(GLint);
     void y(GLint);
     GLint x();
     GLint y();

  private:
     GLint _x;
     GLint _y;
};

#endif
 