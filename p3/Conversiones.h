#ifndef conversionesH
#define conversionesH

#include <gl\gl.h>
#include <gl\glu.h>

#include "math.h"
#include <stdio.h>
//---------------------------------------------------------------------------
GLdouble r2g(GLfloat);
GLdouble g2r(GLfloat);

GLdouble normaliza(GLdouble);
GLdouble normaliza2(GLdouble ang);
GLdouble calculaApertura(GLdouble ai, GLdouble af, GLdouble ao);
bool girarDerecha (GLdouble otro, GLdouble inicio, GLdouble final);

String redondea(GLfloat);
void parte(char *, GLdouble &, GLdouble &);

#endif
