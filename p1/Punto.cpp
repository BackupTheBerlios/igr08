//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Punto.h"
#include <gl\gl.h>
//---------------------------------------------------------------------------
// Metodos publicos

// Creamos un nuevo punto
void Punto::nuevo(GLint a, GLint b) {
   _x = a;
   _y = b;
}

// Pon coordenada x
void Punto::x(GLint a) {
   _x = a;
}

// Pon coordenada y
void Punto::y(GLint b) {
   _y = b;
}

// Dame coordenada x
int Punto::x() {
   return _x;
}

// Dame coordenada y
int Punto::y() {
   return _y;
}

#pragma package(smart_init)
 