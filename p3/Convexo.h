//---------------------------------------------------------------------------
#include "Obstaculo.h"
#ifndef ConvexoH
#define ConvexoH
//---------------------------------------------------------------------------
class Convexo : public Obstaculo{

    public:
        Convexo();
        Convexo(list<PV>*);
        ~Convexo();
        void Pinta();

    private:

};
#endif
