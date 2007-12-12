//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "conversiones.h"


//---------------------------------------------------------------------------

#pragma package(smart_init)


GLfloat r2g(GLfloat radianes){
        return radianes * 180.0 / M_PI;
}


GLfloat g2r(GLfloat grados){
        return grados *  M_PI / 180.0;
}


String redondea(GLfloat num){

  String aux = String (num);
  int pos = strcspn( aux.c_str(), "," );
  String retVal = aux.SubString(1, pos+3);
  return retVal;
}
