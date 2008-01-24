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


        bool corte(Pelota* pelota, GLdouble &tIn, PV* &normal);
 

    private:
        PV* centro;
        GLfloat radio;
/* ??????  */
        PV * unaNormal;


};
#endif
