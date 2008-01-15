//---------------------------------------------------------------------------
#include "Obstaculo.h"
#ifndef RectanguloH
#define RectanguloH
//---------------------------------------------------------------------------
class Rectangulo : public Obstaculo{

    public:
        Rectangulo();
        Rectangulo(list<PV>*, PV *);
        bool corte(){}
        ~Rectangulo();
        void Pinta();

    private:

};
#endif
