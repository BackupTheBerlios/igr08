//---------------------------------------------------------------------------
#include "PV.h"
#ifndef ObstaculoH
#define ObstaculoH
//---------------------------------------------------------------------------
class Obstaculo {

    public:
        Obstaculo();
        ~Obstaculo();
        void Pinta();
        bool getEsVisible(){return esVisible;}
        void setEsVisible(bool eV) {esVisible = eV;}
    private:
        bool esVisible;
        PV ** vertices;
        PV** normales;

};

#endif
