//---------------------------------------------------------------------------
#ifndef DibujoLineasH
#define DibujoLineasH

#include "Lista.h"
#include "Segmento.h"
//---------------------------------------------------------------------------

class DibujoLineas {

   public:
      DibujoLineas();
      ~DibujoLineas(){ delete listaSegmentos;}
      Lista<Segmento> * getSegmentos() {return listaSegmentos;}
      void Pinta();
      void inserta(Segmento *);
      void Seleccionar(Punto2f*);

      void setOperacion(int o) {tipoOperacion = o; }
      int getOperacion() { return tipoOperacion; }
      String toString();
      String toString2();

   private:
      Lista<Segmento> * listaSegmentos;
      int tipoOperacion;

};

#endif
