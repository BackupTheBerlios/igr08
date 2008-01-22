//---------------------------------------------------------------------------
#include "PV.h"
//#include <list.h>
#ifndef ObstaculoH
#define ObstaculoH
#include "Pelota.h"
//---------------------------------------------------------------------------
class Obstaculo {

    public:

        Obstaculo();
        Obstaculo(PV** lv);
        virtual ~Obstaculo();
        virtual bool corte() {}

        virtual void Pinta();
        bool getEsVisible(){return esVisible;}
        void setEsVisible(bool eV) {esVisible = eV;}

        PV* getPosicion() { return posicion; }
        void setPosicion(PV *punto) { posicion = punto->clon(); }

  //      int getVelocidad() { return velocidad; }
//        void setVelocidad(int v) { velocidad = v;}

    protected:
        PV** vertices;
        PV * posicion;
        bool esVisible;
        int nVertices;
        void calculaNormales();
        PV** normales;

    private:


};

#endif
