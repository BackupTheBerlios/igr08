//---------------------------------------------------------------------------
#include "Obstaculo.h"
#include "Lapiz.h"
#ifndef CirculoH
#define CirculoH
//---------------------------------------------------------------------------
class Circulo : public Obstaculo{

    public:
        Circulo();
        Circulo(PV* centro, GLfloat radio);
        bool corte();
        ~Circulo();
        void Pinta();

    private:
        PV* centro;
        GLfloat radio;


};
#endif
