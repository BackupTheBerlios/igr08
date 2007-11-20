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

void Lapiz::avanza (GLfloat longitud, bool esVisible){
GLfloat xD = pos -> getX() + longitud * cos (ang);
GLfloat yD = pos -> getX() + longitud * sin (ang);
Punto2f  * p = new Punto2f(xD,yD);
lineTo(p,esVisible);
delete p;
}

void Lapiz::drawMotivo(Lapiz *l){

}
void Lapiz::drawTotal (Punto2f * posInicial, GLfloat angInicial){

}
void Lapiz::poliEspiral ( Punto2f * posInicial, GLfloat incrAng,
                   GLfloat angInicial, GLfloat incrLong,
                   GLfloat longInicial, int nPasos){

                   }
void Lapiz::poligonoR1 (Lapiz * l, GLfloat lado, int nlados){

}
void Lapiz::poligonoR2 (Punto2f * centro, GLfloat radio, int nlados){

}
void Lapiz::arco(Punto2f * inicio, Punto2f * fin, Punto2f *otro){

}
