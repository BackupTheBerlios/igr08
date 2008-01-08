//---------------------------------------------------------------------------
#include "PV.h"
#include <GL/gl.h>
#ifndef PelotaH
#define PelotaH
//---------------------------------------------------------------------------
class Pelota{

    public:
        Pelota();
        ~Pelota();
        void Pinta();
        void avanza();
        bool DetectarObstaculo();
        void rebota();

    private:
        GLdouble radio;
        PV * centro;
        PV * direccion;

};

#endif


