//---------------------------------------------------------------------------
#include "PV.h"
#ifndef ObstaculoH
#define ObstaculoH
//---------------------------------------------------------------------------
class Obstaculo {

    public:
        Obstaculo();
        ~Obstaculo();

        virtual void Pinta();
        bool getEsVisible(){return esVisible;}
        void setEsVisible(bool eV) {esVisible = eV;}

        PV getPosicion() { return posicion; }
        void setPosicion(PV punto) { posicion = punto; }

        int getVelocidad() { return velocidad; }
        void setVelocidad(int v) { velocidad = v;}

    private:
        bool esVisible;
        PV** vertices;
        PV** normales;
        PV posicion;
        int velocidad;

};

#endif
