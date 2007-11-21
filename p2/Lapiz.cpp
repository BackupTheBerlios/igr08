//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Lapiz.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Lapiz::Lapiz() {
    ang = 0.0;
    pos = new Punto2f(0,0);
}

// Constructora por parámetros
Lapiz::Lapiz(Punto2f * pos, GLfloat a){
    ang = a;
    pos = pos -> clon();
}

// Constructora de copia
Lapiz::Lapiz (const Lapiz& l){
    ang = l.ang;
    pos = l.pos -> clon();
}

// Método Clon
Lapiz * Lapiz::clon() const{
    Lapiz * l = new Lapiz();
    *l = *this;
    return l;
}

// Dibuja un segmento desde la posicion relativa
void Lapiz::lineTo (Punto2f * destino, bool esVisible){

    if ( esVisible){
        glBegin(GL_LINES);
                glVertex2f(pos -> getX(), pos -> getY());
                glVertex2f(destino -> getX(), destino -> getY());
        glEnd();
    }

    pos = destino->clon();
}

// Variamos el angulo actual de dibujo
void Lapiz::gira (GLdouble incrAng){
        ang += incrAng;
        GLdouble grados = r2g(ang);

        double  p_E;
        double parte_Decimal = modf(grados, &p_E);
        int  parte_Entera = p_E;
        parte_Entera = parte_Entera % 360;
        grados = g2r(parte_Entera);
        grados += g2r(parte_Decimal);
}

// Avanzamos al siguiente punto relativo
void Lapiz::avanza (GLfloat longitud, bool esVisible){
        GLfloat xD = pos -> getX() + longitud * cos (ang);
        GLfloat yD = pos -> getX() + longitud * sin (ang);
        Punto2f  * p = new Punto2f(xD,yD);
        lineTo(p,esVisible);
        delete p;
}

//---------------------------------------------------------------------------

void Lapiz::drawMotivo(Lapiz *l){

}

//---------------------------------------------------------------------------

void Lapiz::drawTotal (Punto2f * posInicial, GLfloat angInicial){

}

//---------------------------------------------------------------------------

// Realiza los calculos para dibujar una poliEspiral
void Lapiz::poliEspiral ( Punto2f * posInicial, GLfloat incrAng,
                          GLfloat angInicial, GLfloat incrLong,
                          GLfloat longInicial, int nPasos){

        Lapiz * l = new Lapiz (posInicial, angInicial);
        for (int i = 0; i<nPasos; i++){
                 l -> avanza(longInicial, true);
                 longInicial += incrLong;
                 l -> gira(incrAng);
        }
        delete l;
}

//---------------------------------------------------------------------------

void Lapiz::poligonoR1 (Lapiz * l, GLfloat lado, int nlados){

}

//---------------------------------------------------------------------------

void Lapiz::poligonoR2 (Punto2f * centro, GLfloat radio, int nlados){

}

//---------------------------------------------------------------------------

void Lapiz::arco(Punto2f * inicio, Punto2f * fin, Punto2f *otro){

}
