//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "DibujoLineas.h"
//---------------------------------------------------------------------------

// Constructora por defecto
DibujoLineas::DibujoLineas() {
  listaSegmentos = new Lista <Segmento>();
  listaSegmentos->inicia();
  tipoOperacion = 1;
}
void DibujoLineas::Pinta(){
   // Dibujado de lista de segmentos
   if (tipoOperacion == 1) {
      glColor3f(1.0,1.0,1.0);
      glLineWidth(2);
      glPointSize(3);
   }
   else { // Seleccionado de listes de segmentos
          glColor3f(1.0,0.0,1.0);
          glLineWidth(3);
          glPointSize(5);
       }
   listaSegmentos ->inicia();
   while(!listaSegmentos->final()){
      listaSegmentos->getActual()->Pinta();
      if (tipoOperacion == 2){
        listaSegmentos->getActual()->Pinta2();
      }
      listaSegmentos->avanza();
   }
}

void DibujoLineas::inserta(Segmento * s) {
    listaSegmentos->inserta(s->clon());
}

void DibujoLineas::Seleccionar(Punto2f* p) {
   bool enc = false;
   listaSegmentos ->inicia();
   while(!listaSegmentos->final()&&!enc){
     if (listaSegmentos->getActual()->contiene(p)) {
       this->setOperacion(2);
       enc = true;
     }
     else {
       this->setOperacion(1);
     }
     listaSegmentos->avanza();
   }
}

String DibujoLineas::toString(){
String retVal="";
        listaSegmentos->inicia();
        while(!listaSegmentos->final()){
                Segmento * seg = listaSegmentos->getActual();
                if (seg!=NULL){
                retVal+= seg->toString()+"\n";
                }


                listaSegmentos->avanza();
        }

retVal+="----------------\n";
return retVal;
}



String DibujoLineas::toString2(){
String retVal="";
        listaSegmentos->inicia();
        while(!listaSegmentos->final()){
                Segmento * seg = listaSegmentos->getActual();
                if (seg!=NULL){
                retVal+= seg->toString2();
        }
                listaSegmentos->avanza();
        }
retVal+="x\n";
return retVal;
}
