//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Punto.h"
#include <gl\gl.h>
//---------------------------------------------------------------------------
// Metodos publicos

// Creamos un nuevo punto
void Punto::nuevo(GLfloat a, GLfloat b) {
   _x = a;
   _y = b;
}

// Pon coordenada x
void Punto::x(GLfloat a) {
   _x = a;
}

// Pon coordenada y
void Punto::y(GLfloat b) {
   _y = b;
}

// Dame coordenada x
GLfloat Punto::x() {
   return _x;
}

// Dame coordenada y
GLfloat Punto::y() {
   return _y;
}

#pragma package(smart_init)
 