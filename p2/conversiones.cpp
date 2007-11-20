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
