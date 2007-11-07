//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Punto.h"
#include <gl\gl.h>
//---------------------------------------------------------------------------
// Metodos publicos

// Creamos un nuevo punto
void Punto::nuevo(GLdouble a, GLdouble b) {
   _x = a;
   _y = b;
}

// Pon coordenada x
void Punto::x(GLdouble a) {
   _x = a;
}

// Pon coordenada y
void Punto::y(GLdouble b) {
   _y = b;
}

// Dame coordenada x
GLdouble Punto::x() {
   return _x;
}

// Dame coordenada y
GLdouble Punto::y() {
   return _y;
}

#pragma package(smart_init)
 
