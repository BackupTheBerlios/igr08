//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Lapiz.h"

//---------------------------------------------------------------------------

#pragma package(smart_init)

             Lapiz::Lapiz() : ang(0.0) {}

void Lapiz::lineTo (Punto2f * destino, bool esVisible){
        if ( esVisible){
        glBegin(GL_LINES);
                glVertex2f(pos -> getX(), pos -> getY());
                glVertex2f(destino -> getX(), destino -> getY());
        glEnd();
        }
        pos = destino; // clone;!!!!!
}


void Lapiz::gira (GLfloat incrAng){
ang += incrAng;
GLfloat grados = r2g(ang);

double  p_E;
double parte_Decimal = modf(grados, &p_E);
int  parte_Entera = p_E;
parte_Entera = parte_Entera % 360;
grados = g2r(parte_Entera);
grados += g2r(parte_Decimal);

}

