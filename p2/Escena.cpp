//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Escena.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Escena::Escena(int distancia){
      listaDibujos = new Lista <DibujoLineas>();
      listaDibujos -> inicia();
      ratioViewPort = 1.0;
      xRight =  distancia;
      xLeft  = -xRight;
      yTop   =  xRight;
      yBot   = -yTop;

}

Escena::~Escena(){
        delete listaDibujos;
}


void Escena::Resize(int CW, int CH) {

    // Establecemos el nuevo Puerto de Vista
    glViewport(0, 0, CW, CH);

    // Se actualiza el volumen de vista
    // para que su radio coincida con ratioViewPort
    GLfloat RatioVolVista= xRight/yTop;

    if (RatioVolVista >= ratioViewPort){
       //Aumentamos yTop-yBot
       yTop = xRight / ratioViewPort;
       yBot =-yTop;
    }
    else{
       //Aumentamos xRight-xLeft
       xRight= ratioViewPort*yTop;
       xLeft =-xRight;
    }

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(xLeft,xRight, yBot,yTop);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    return;
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

void Escena::transformarXY(Punto2f * p, int ancho, int alto) {
        GLdouble x_Aux;
        GLdouble y_Aux;

        x_Aux = convertirX(p->getX(), ancho);
        y_Aux = convertirY(p->getX(), alto);

        p->setX(x_Aux);
        p->setY(y_Aux);

}


void Escena::Zoom(float factor){

}
void Escena::ZoomProgresivo(float factor, int nPasos){

// controlar f > 0
//
        int xRp, xLp, yTp, yBp;
        GLdouble cx = (xRight + xLeft) / 2.0;
        GLdouble cy = (yBot + yTop) / 2.0;
        GLdouble incrF = (factor - 1) / (GLdouble) nPasos;
        glMatrixMode (GL_PROJECTION);
        for (int i =0; i<= nPasos; i++){
                xRp = cx +0.5 * ((xRight-xLeft)/(1+incrF * i));
                xLp = cx +0.5 * ((xRight-xLeft)/(1+incrF * i));
                yTp = cy +0.5 * ((yTop-yBot)/(1+incrF * i));
                yBp = cy +0.5 * ((yTop-yBot)/(1+incrF * i));
                glLoadIdentity();
                gluOrtho2D(xLp,xRp, yBp, yTp);
                Pinta();
                xRight = xRp; xLeft = xLp;
                yTop = yTp; yBot = yBp;
        }
}

/////////////////////////////////////////////////////
//                 MÉTODOS PRIVADOS                //
/////////////////////////////////////////////////////

// Escala la coordenada X desde el puerto de vista hasta
// el area visible de la escena
GLdouble Escena::convertirX(int x, int ancho) {
    GLdouble escalaAncho = (GLdouble)ancho/(xRight-xLeft);
    return (x / escalaAncho + xLeft);
}

// Escala la coordenada Y desde el puerto de vista hasta
// el area visible de la escena
GLdouble Escena::convertirY(int y, int alto) {
   GLdouble escalaAlto = (GLdouble)alto/(yTop-yBot);
   return (y / escalaAlto + yBot);
}




