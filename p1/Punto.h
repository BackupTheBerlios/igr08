//---------------------------------------------------------------------------
#ifndef PuntoH
#define PuntoH

#include <gl\gl.h>
//---------------------------------------------------------------------------
class Punto {

  public:
     void nuevo(GLfloat,GLfloat);
     void x(GLfloat);
     void y(GLfloat);
     GLfloat x();
     GLfloat y();

  private:
     GLfloat _x;
     GLfloat _y;
};

#endif
 