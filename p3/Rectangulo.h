//---------------------------------------------------------------------------
#include "Obstaculo.h"
#ifndef RectanguloH
#define RectanguloH
//---------------------------------------------------------------------------
class Rectangulo : public Obstaculo{

    public:
        Rectangulo();
        Rectangulo(PV**, int , PV *);
        Rectangulo(int ancho, int alto, PV * esqSupIzq);
        bool Corte(Pelota* , GLdouble &, PV* &);
        ~Rectangulo();
        void Pinta();

    private:
        PV ** normales;
};
#endif
