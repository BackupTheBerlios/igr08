//---------------------------------------------------------------------------
#include "Obstaculo.h"
#include "Lapiz.h"
#ifndef ConvexoH
#define ConvexoH
//---------------------------------------------------------------------------
class Convexo : public Obstaculo{

    public:
        Convexo();
        Convexo(list<PV>*);
        Convexo(PV* centro, GLfloat radio, int nlados);
        ~Convexo();
        void Pinta();
    private:
            PV** normales;

};
#endif
