//---------------------------------------------------------------------------
#include "PV.h"
#include <GL/gl.h>
#include <list.h>
#include "Lapiz.h"
#ifndef PelotaH
#define PelotaH
//---------------------------------------------------------------------------
class Pelota{

    public:
        Pelota();
        Pelota(PV * c, GLfloat r );
        ~Pelota();
        void Pinta();
        void avanza(GLdouble);
//        bool DetectarObstaculo();
//        void rebota();
        void rebota(PV* n);
        void stop(){delete direccion; direccion = new PV (0,0);}
        PV * getDireccion(){return direccion;}
        PV getPuntoTangente(PV* );
        PV * getCentro (){return centro;}
        GLdouble getRadio(){return radio;}

    private:
        GLdouble radio;
        PV * centro;
        PV * direccion;
        PV** vertices;
        int nVertices;        

};

#endif


