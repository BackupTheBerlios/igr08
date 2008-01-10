//---------------------------------------------------------------------------
#include "PV.h"
#ifndef ObstaculoH
#define ObstaculoH
//---------------------------------------------------------------------------
class Obstaculo {

    public:
        Obstaculo();
        Obstaculo(PV*, PV**, int);
        ~Obstaculo();

        virtual void Pinta();
        bool getEsVisible(){return esVisible;}
        void setEsVisible(bool eV) {esVisible = eV;}

        PV* getPosicion() { return posicion; }
        void setPosicion(PV *punto) { posicion = punto->clon(); }

        int getVelocidad() { return velocidad; }
        void setVelocidad(int v) { velocidad = v;}

    protected:
        PV** vertices;
        PV * posicion;
        int velocidad;
    private:
        bool esVisible;

        PV** normales;

        void calculaNormales();

};

#endif
