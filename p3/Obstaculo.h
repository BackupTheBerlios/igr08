//---------------------------------------------------------------------------
#include "PV.h"
#include <list.h>
#ifndef ObstaculoH
#define ObstaculoH
//---------------------------------------------------------------------------
class Obstaculo {

    public:
        Obstaculo();
        Obstaculo(list<PV>*);
        virtual ~Obstaculo();

        virtual void Pinta();
        bool getEsVisible(){return esVisible;}
        void setEsVisible(bool eV) {esVisible = eV;}

        PV* getPosicion() { return posicion; }
        void setPosicion(PV *punto) { posicion = punto->clon(); }

  //      int getVelocidad() { return velocidad; }
//        void setVelocidad(int v) { velocidad = v;}

    protected:
        list <PV> * vertices;
      //  PV** vertices;
        PV * posicion;
//       int velocidad;
        bool esVisible;

    private:

        PV** normales;
        void calculaNormales();

};

#endif
