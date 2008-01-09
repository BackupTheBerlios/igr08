//---------------------------------------------------------------------------
#include "Obstaculo.h"
#ifndef MandoH
#define MandoH
//---------------------------------------------------------------------------
class Mando : public Obstaculo {

    public:
        Mando();
        ~Mando();
        Mando(PV**, PV *, int);
        void Pinta();

    private:


};
#endif
