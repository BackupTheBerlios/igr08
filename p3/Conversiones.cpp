#include <vcl.h>
#pragma hdrstop
#pragma package(smart_init)

#include "conversiones.h"
//---------------------------------------------------------------------------


// Normaliza los numeros negativos
GLfloat normaliza(GLfloat num){
    if (num<0)
      return num+360;

    return num;
}

// Ajusta el angulo dado
GLfloat normaliza2(GLfloat ang){
    if (ang > 180) {
      ang -= 360;
    }
    else {
       if (ang < -180) {
          ang += 360;
       }
    }

    return ang;
}

// Aperturas para los arcos
GLdouble calculaApertura(GLdouble ai, GLdouble af, GLdouble ao){
    GLdouble aPn;
    ao = normaliza (ao);
    ai = normaliza (ai);
    af = normaliza (af);
    if ((ao<ai & ao>af) | (ao>ai & ao<af)){
       aPn = max (ai, af)  - min (af, ai);
    }
    else {
        aPn = 360 - (ai - af);
        }
        if (aPn >360){
          aPn = 360 -(aPn -360);
        }

    return aPn;
}

// Giro hacia una dirección
bool girarDerecha (GLdouble otro, GLdouble inicio, GLdouble final){
    GLdouble so = sin (g2r(otro));
    GLdouble si = sin (g2r(inicio));
    GLdouble sf = sin (g2r(final));
    if (so > max(si, sf))
       return true;
    else
       return false;
}
