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
Lapiz::Lapiz(Punto2f * p, GLfloat a){
    ang = a;
    pos = p -> clon();
    delete p;
    p=NULL;
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
    delete pos;
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
void Lapiz::avanza (GLfloat longitud, bool esVisible, Segmento*& s){
        GLfloat xD = pos -> getX() + longitud * cos (ang);
        GLfloat yD = pos -> getX() + longitud * sin (ang);
        Punto2f * p = new Punto2f(xD,yD);
        s = new Segmento(pos,p);
        lineTo(p,esVisible);
        delete p;
        p=NULL;
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
                          GLfloat longInicial, int nPasos, DibujoLineas* dl){

        Lapiz * l = new Lapiz (posInicial, angInicial);
        for (int i = 0; i<nPasos; i++){
                 Segmento* s;
                 l -> avanza(longInicial, true, s);
                 longInicial += incrLong;
                 l -> gira(incrAng);
                 dl->inserta(s);
                 delete s;
        }
        delete l;
//        delete posInicial;
        posInicial=NULL;
}

//---------------------------------------------------------------------------

void Lapiz::poligonoR1 (Lapiz * l, GLfloat lado, int nlados){

}

//---------------------------------------------------------------------------

void Lapiz::poligonoR2 (Punto2f * centro, GLfloat radio, int nlados){

}

//---------------------------------------------------------------------------

void Lapiz::arco(Punto2f * inicio, Punto2f * fin, Punto2f *otro){


//calcular mediatrices
        // calcular puntos medios
            Punto2f * A = inicio ->puntoMedio(otro);
            Punto2f * B = otro ->puntoMedio(fin);
        // calcular vector normal
            GLdouble x = inicio->getX() - otro -> getX();
            GLdouble y = inicio->getY() - otro -> getY();
            Punto2f * v1 = new Punto2f(x,y); // es un vector
            Punto2f * v1p= v1->perpendicular();

// calcular punto de corte de las mediatrices.
//calcular radio

}

Punto2f* Lapiz::calcularBezier(Punto2f** cp,float t) {

    float ax, bx, cx;
    float ay, by, cy;
    float ts, tc;
    Punto2f* punto;
 
    // Cálculamos los coeficientes polinomiales

    cx = 3.0 * (cp[1]->getX() - cp[0]->getX());
    bx = 3.0 * (cp[2]->getX() - cp[1]->getX()) - cx;
    ax = cp[3]->getX() - cp[0]->getX() - cx - bx;
 
    cy = 3.0 * (cp[1]->getY() - cp[0]->getY());
    by = 3.0 * (cp[2]->getY() - cp[1]->getY()) - cy;
    ay = cp[3]->getY() - cp[0]->getY() - cy - by;
 
    // Calculamos el punto de curva en el parametro t

    ts = t * t;
    tc = ts * t;
 
    punto->setX((ax * tc) + (bx * ts) + (cx * t) + cp[0]->getX());
    punto->setY((ay * tc) + (by * ts) + (cy * t) + cp[0]->getY());
 
    return punto;
}


void Lapiz::Bezier(Punto2f** puntosControl,int numeroDePuntos, Punto2f** curva) {
    float dt;
    int i;

    dt = 1.0 / (numeroDePuntos - 1);

    for (i=0; i<numeroDePuntos; i++)
        curva[i] = calcularBezier(puntosControl,i*dt);
}
