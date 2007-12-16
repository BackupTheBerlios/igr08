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

}


GLfloat normaliza(GLfloat num){

        if (num<0) {
                return num+360;
                }
        else{
                return num;
                }
}

GLfloat normaliza2(GLfloat ang){
        if (ang > 180) {
                ang -= 360;
        }
        else {
                if (ang < -180) {
                        ang_X += 360;
                }
        }

}

GLdouble calculaApertura(GLdouble ai, GLdouble af, GLdouble ao){
        GLdouble aPn;

         if ((ao<ai & ao>af) | (ao>ai & ao<af)){
                aPn = max (ai, af)  - min (af, ai);
         }
         else {
               aPn = 360 - (ai - af);
             }
         return aPn;
}


bool girarDerecha (GLdouble otro, GLdouble inicio, GLdouble final){
        GLdouble so = sin (g2r(otro));
        GLdouble si = sin (g2r(inicio));
        GLdouble sf = sin (g2r(final));
        if (so > max(si, sf))
                return true;
        else return false; 

}


