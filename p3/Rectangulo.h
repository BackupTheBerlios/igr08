//---------------------------------------------------------------------------
#include "Obstaculo.h"
#ifndef RectanguloH
#define RectanguloH
//---------------------------------------------------------------------------
class Rectangulo : public Obstaculo{

    public:
        Rectangulo();
        Rectangulo(PV**vertices, PV * poscicion);
        Rectangulo(PV * poscicion, PV**vertices);
        ~Rectangulo();
        void Pinta();

    private:

};
#endif
