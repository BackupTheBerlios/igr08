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
        bool Corte(Pelota* , GLdouble &, PV* &); 

        void Pinta();
        void Mueve(PV);
        PV* getPosicion();

    private:
        int velocidad;


};
#endif
