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

void parte(char * texto, GLfloat &n1, GLfloat &n2){
  String Sn1, Sn2;
  String aux = String (texto);
  int pos = strcspn( aux.c_str(), ";" );
  Sn1 = aux.SubString(1, pos);
  Sn2 = aux.SubString(pos+2, aux.Length());
  n1 =Sn1.ToDouble();
  n2 = Sn2.ToDouble();

//  char * g1 = Sn1.c_str();
//  char * g2 = Sn2.c_str();
//   n1 = strtod(g1);
//  n1 = atof(g1);
 // n2 = atof(g2);

}
