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
        void rebota();

    private:
        GLdouble radio;
        PV * centro;
        PV * direccion;
        list <PV> * vertices;        

};

#endif


