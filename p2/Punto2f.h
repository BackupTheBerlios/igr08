//---------------------------------------------------------------------------
#ifndef Punto2fH
#define Punto2fH

#include <math.h>

#include <gl\gl.h>
#include <gl\glu.h>

#include "conversiones.h"
//---------------------------------------------------------------------------

class Punto2f {

    public:
        Punto2f () : x(0.0), y(0.0) {}
        Punto2f (GLfloat X, GLfloat Y) : x(X), y(Y) {}
        Punto2f (const Punto2f&);
        Punto2f * clon() const;

        void Pinta();

        GLfloat getX() {return x;}
        GLfloat getY() {return y;}

        void setX(GLfloat X){x=X;}
        void setY(GLfloat Y){y=Y;}


        Punto2f * puntoMedio(Punto2f *);
        GLdouble dot(Punto2f *);
        Punto2f * perpendicular();
        GLdouble distancia(Punto2f *);
        Punto2f *  operator+(Punto2f);
        Punto2f *  operator-(Punto2f);
        Punto2f *  multiplicar(GLdouble);
        Punto2f *  dividir(GLdouble);
        GLdouble angulo2( Punto2f *otro);
        GLdouble modulo();

        String toString();
        String toString2();


    private:
        GLfloat x;
        GLfloat y;
};

#endif
