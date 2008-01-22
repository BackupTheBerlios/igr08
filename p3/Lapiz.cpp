//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Lapiz.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Lapiz::Lapiz() {
    ang = 0.0;
    pos = new PV(0,0);
}

// Constructora por parámetros
Lapiz::Lapiz(PV * p, GLfloat a){
    ang = a;
    pos = p -> clon();
    delete p;
    p=NULL;
}



// Dibuja un segmento desde la posicion relativa
void Lapiz::lineTo (PV * destino, bool esVisible){
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
        GLdouble sum = incrAng + r2g(ang);
        double  p_E;
        double parte_Decimal = modf(sum, &p_E);
        int  parte_Entera = p_E;
        parte_Entera = parte_Entera % 360;
        GLdouble grados = g2r(parte_Entera);
        grados += g2r(parte_Decimal);
        ang = grados;
}

// Avanzamos al siguiente punto relativo
void Lapiz::avanza (GLfloat longitud, bool esVisible, PV*& nuevoPunto/*, Segmento*& s*/){
        GLfloat xD = pos -> getX() + longitud * cos (ang);
        GLfloat yD = pos -> getY() + longitud * sin (ang);
//      PV * p = new PV(xD,yD);
//      s = new Segmento(pos,p);
        nuevoPunto = new PV(xD,yD);
        lineTo(nuevoPunto,esVisible);
/*        delete p;
        p=NULL;*/
}



//---------------------------------------------------------------------------

//---------------------------------------------------------------------------

void Lapiz::poligonoR1 (GLfloat lado, int nlados, PV** listaVertices /*, DibujoLineas* dl*/){
        GLdouble alfa = 360.0 / (GLdouble) nlados;
        GLdouble beta = (180.0 - alfa) / 2.0;
        GLdouble gamma = 180 - 2*beta;
        for (int i = 0; i<nlados; i++){
                PV * p;
                this->avanza(lado, true, p);
                this->gira(gamma);
                // hacer New para listaVertices
                listaVertices[i] = p;
                int borrar = 32;
//                delete p;
/*                Segmento* s;
                this->avanza(lado, true, s);
                this->gira(gamma);
                dl->inserta(s);
                delete s;      */
        }
}

//---------------------------------------------------------------------------

void Lapiz::poligonoR2 (PV * centro, GLfloat radio, int nlados, PV** listaVertices/*, DibujoLineas* dl*/){
        GLdouble alfa = 360.0 / (GLdouble) nlados;
        GLdouble beta = (180.0 - alfa) / 2.0;
     //   GLdouble gamma = 180 - 2*beta;
        GLdouble theta = 180 - 2*beta;

        this->pos->setX(centro->getX()+(cos(g2r(beta))*radio));
        this->pos->setY(centro->getY()-(sin(g2r(beta))*radio));
        this->ang= g2r(theta);

        GLfloat lado = cos(g2r(beta))*radio*2;
        poligonoR1(lado, nlados, listaVertices /*, dl*/);
}

//---------------------------------------------------------------------------

/*// Constructora de copia
Lapiz::Lapiz (const Lapiz& l){
    ang = l.ang;
    pos = l.pos -> clon();
}

// Método Clon
Lapiz * Lapiz::clon() const{
    Lapiz * l = new Lapiz();
    *l = *this;
    return l;
}  */
