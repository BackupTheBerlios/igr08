//---------------------------------------------------------------------------
#ifndef PVH
#define PVH

#include <math.h>
#include "conversiones.h"
#include <gl\gl.h>
#include <gl\glu.h>
//---------------------------------------------------------------------------

class PV {

    public:
        PV () : x(0.0), y(0.0) {}
        PV (GLdouble X, GLdouble Y) : x(X), y(Y) {}
        PV (PV , PV );
        PV (const PV&);
        PV * clon() const;

        void Pinta();

        GLdouble getX() {return x;}
        GLdouble getY() {return y;}

        void setX(GLdouble X){x=X;}
        void setY(GLdouble Y){y=Y;}

        PV * puntoMedio(PV *);
        GLdouble dot(PV *);
        PV perpendicular();
        GLdouble distancia(PV *);
        PV * operator+(PV);
        PV * operator-(PV);
        PV *  multiplicar(GLdouble);
        PV *  dividir(GLdouble);
        GLdouble angulo(PV *otro);
        GLdouble modulo();

        String toString();

    private:
        GLdouble x;
        GLdouble y;
};

#endif





