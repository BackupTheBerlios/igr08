//---------------------------------------------------------------------------
#include "Obstaculo.h"
#ifndef MandoH
#define MandoH
//---------------------------------------------------------------------------
class Mando : public Obstaculo {

    public:
        Mando();
        ~Mando();
        Mando(list<PV>*, PV *, int);
//        Mando(PV**, PV *, int);
        void Pinta();

    private:


};
#endif
