//---------------------------------------------------------------------------

#include <gl\gl.h>
#include <gl\glu.h>

#ifndef Punto2fH
#define Punto2fH
//---------------------------------------------------------------------------
#endif

class Punto2f {
private:
GLfloat x;
GLfloat y;


public:

Punto2f () : x(0.0), y(0.0) {}
Punto2f (GLfloat X, GLfloat Y) : x(X), y(Y) {}
Punto2f (const Punto2f&);
Punto2f * clon() const;
GLfloat getX() {return x;}
GLfloat getY() {return y;}

void setX(GLfloat X){x=X;}
void setY(GLfloat Y){y=Y;}

};