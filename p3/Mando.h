//---------------------------------------------------------------------------
#include "Convexo.h"
#ifndef MandoH
#define MandoH
//---------------------------------------------------------------------------
class Mando : public Convexo {

    public:
        Mando();
        ~Mando();
        Mando(PV**, PV *, int);
        bool corte(Pelota* pelota, GLdouble &tIn, PV* &normal); 

        void Pinta();
        void Mueve(PV);
        PV* getPosicion();

    private:
        int velocidad;


};
#endif
