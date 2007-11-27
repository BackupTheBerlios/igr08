//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Escena.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Escena::Escena(){
      listaDibujos = new Lista <DibujoLineas>();
      listaDibujos -> inicia();
      xR, xL, yT, yB=0;
}

Escena::~Escena(){
        delete listaDibujos;
}


void Escena::Resize(int CW, int CH){

}


void Escena::Pinta(){
              while(!listaDibujos->final()){
                   listaDibujos-> getActual()->Pinta();
                   listaDibujos->avanza();
              }
}

void Escena::inserta(DibujoLineas * dl) {
        listaDibujos->inserta(dl);
}


void Escena::Zoom(float factor){

}
void Escena::ZoomProgresivo(float factor, int nPasos){

// controlar f > 0
//
        int xRp, xLp, yTp, yBp;
        GLdouble cx = (xR + xL) / 2.0;
        GLdouble cy = (yB + yT) / 2.0;
        GLdouble incrF = (factor - 1) / (GLdouble) nPasos;
        glMatrixMode (GL_PROJECTION);
        for (int i =0; i<= nPasos; i++){
                xRp = cx +0.5 * ((xR-xL)/(1+incrF * i));
                xLp = cx +0.5 * ((xR-xL)/(1+incrF * i));
                yTp = cy +0.5 * ((yT-yB)/(1+incrF * i));
                yBp = cy +0.5 * ((yT-yB)/(1+incrF * i));
                glLoadIdentity();
                gluOrtho2D(xLp,xRp, yBp, yTp);
                Pinta();
                xR=xRp; xL=xLp; yT=yTp; yB=yBp;
        }
}

