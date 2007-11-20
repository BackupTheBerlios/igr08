//---------------------------------------------------------------------------
#include "Punto2f.h"
#include "conversiones.h"


#ifndef LapizH
#define LapizH
//---------------------------------------------------------------------------
#endif


class Lapiz {

private:
// Atributos
Punto2f * pos;
GLfloat ang; // en radianes

public:
Lapiz();
void lineTo (Punto2f *, bool);
void gira (GLfloat incrAng);

};