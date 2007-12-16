//---------------------------------------------------------------------------

#include <gl\gl.h>
#include <gl\glu.h>

#include "math.h"
#include <stdio.h>

#ifndef conversionesH
#define conversionesH
//---------------------------------------------------------------------------
#endif

GLfloat r2g(GLfloat);
GLfloat g2r(GLfloat);

GLfloat normaliza(GLfloat);
GLfloat normaliza2(GLfloat ang);
GLdouble calculaApertura(GLdouble ai, GLdouble af, GLdouble ao);
bool girarDerecha (GLdouble otro, GLdouble inicio, GLdouble final);

String redondea(GLfloat);
void parte(char *, GLfloat &, GLfloat &);
