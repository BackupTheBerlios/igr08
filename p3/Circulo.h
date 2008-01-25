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
        ~Circulo();

        bool Corte(Pelota* pelota, GLdouble &tIn, PV* &normal);
        void Pinta();

    private:
        PV* centro;
        GLfloat radio;
        PV * unaNormal;


};
#endif
