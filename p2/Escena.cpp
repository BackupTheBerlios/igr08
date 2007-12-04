//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Escena.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Escena::Escena(int distancia, int CW, int CH){
        listaDibujos = new Lista <DibujoLineas>();
        listaDibujos -> inicia();
        ratioViewPort = 1.0;
        xRight =  distancia;
        xLeft  = -xRight;
        yTop   =  xRight;
        yBot   = -yTop;
        ClientWidth = CW;
        ClientHeight = CH;
}

Escena::~Escena(){
        delete listaDibujos;
}


void Escena::Resize(int CW, int CH) {
        ClientWidth = CW;
        ClientHeight = CH;


    // Establecemos el nuevo Puerto de Vista
    glViewport(0, 0, CW, CH);

    // Se actualiza el volumen de vista
    // para que su radio coincida con ratioViewPort
    GLfloat RatioVolVista= xRight/yTop;

    if (RatioVolVista >= ratioViewPort){
       //Aumentamos yTop-yBot
       yTop = xRight / ratioViewPort;
       yBot = -yTop;
    }
    else{
       //Aumentamos xRight-xLeft
       xRight= ratioViewPort*yTop;
       xLeft = -xRight;
    }

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(xLeft,xRight,yBot,yTop);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}

void Escena::Pinta(){
//    glClear(GL_COLOR_BUFFER_BIT);
    listaDibujos ->inicia();
    while(!listaDibujos->final()){
         listaDibujos->getActual()->Pinta();
         listaDibujos->avanza();
   }


 // Ejecutar lista de comandos en espera
 glFlush();       
 // Habilitado uso del doble buffer
 SwapBuffers(hdc);
}

void Escena::inserta(DibujoLineas * dl) {
        listaDibujos->inserta(dl);
}

void Escena::teclado(WORD& Key) {

  // Factor de mov de Traslaccion
  GLfloat T = 0.01;

  // Trasladamos hacia arriba
  if (Key == VK_UP) {
    GLfloat ar = (yTop - yBot)* T;
    yBot += ar;
    yTop += ar;
  }

  // Trasladamos hacia abajo
  if (Key == VK_DOWN) {
    GLfloat ab = (yTop - yBot)* T;
    yBot -= ab;
    yTop -= ab;
  }

  // Trasladamos hacia derecha
  if (Key == VK_RIGHT) {
    GLfloat der = (xRight - xLeft)* T;
    xRight += der;
    xLeft += der;
  }

  // Trasladamos hacia izquierda
  if (Key == VK_LEFT) {
    GLfloat izq = (xRight - xLeft)* T;
    xRight -= izq;
    xLeft -= izq;
  }

  // Tecla A -> Acercamos
  if (Key == 'a' | Key == 'A') {
  Zoom(105);
  }

  // Tecla S -> Alejamos
  if (Key == 's' | Key == 'S') {
  Zoom(95);
  }
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
}

void Escena::transformarXY(Punto2f * p, int ancho, int alto) {
        GLfloat x_Aux;
        GLfloat y_Aux;

        x_Aux = convertirX(p->getX(), ancho);
        y_Aux = convertirY(p->getY(), alto);

        p->setX(x_Aux);
        p->setY(-y_Aux);
}


void Escena::Zoom(float F){
     F=F/100.0;

     // Calculamos las nuevas coordenadas del AVE
     GLdouble xRn = (xRight + xLeft) / (GLdouble) 2;
     xRn += (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble xLn = (xRight + xLeft) / (GLdouble) 2;
     xLn -= (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble yTn = (yTop+yBot) / (GLdouble) 2;
     yTn += (yTop - yBot) * (GLdouble) 0.5 * (1/F);

     GLdouble yBn = (yTop+yBot) / (GLdouble) 2;
     yBn -= (yTop - yBot) * (GLdouble) 0.5 * (1/F);

     // Actualizamos las variables del AVE
     xLeft = xLn;
     xRight =xRn;

     yBot = yBn;
     yTop = yTn;
     glMatrixMode(GL_PROJECTION);
     glLoadIdentity();
     gluOrtho2D(xLeft,xRight,yBot,yTop);
}
bool Escena::ZoomProgresivo(float factor, int nPasos){
   factor=factor/100.0;
   if (factor>0){
        int xRp, xLp, yTp, yBp;
        GLdouble cx = (xRight + xLeft) / 2.0;
        GLdouble cy = (yBot + yTop) / 2.0;
        GLdouble incrF = (factor - 1) / (GLdouble) nPasos;
        glMatrixMode (GL_PROJECTION);
        for (int i =0; i<= nPasos; i++){
                xRp = cx + 0.5 * ((xRight-xLeft)/(1+ incrF * i));
                xLp = cx - 0.5 * ((xRight-xLeft)/(1+incrF * i));
                yTp = cy + 0.5 * ((yTop-yBot)/(1+incrF * i));
                yBp = cy - 0.5 * ((yTop-yBot)/(1+incrF * i));
                glClear(GL_COLOR_BUFFER_BIT);
                glLoadIdentity();
                gluOrtho2D(xLp,xRp, yBp, yTp);
                Pinta();
                Sleep(deltaT);
        }
   xRight = xRp; xLeft = xLp;
   yTop = yTp; yBot = yBp;
   return true;
   }
   else return false;
}


/////////////////////////////////////////////////////
//                 MÉTODOS PRIVADOS                //
/////////////////////////////////////////////////////

// Escala la coordenada X desde el puerto de vista hasta
// el area visible de la escena
GLdouble Escena::convertirX(int x, int ancho) {
    GLdouble escalaAncho = (GLdouble)ancho/(xRight-xLeft);
   // GLdouble centro =   (xLeft-xRight)/2.0;
   if (xLeft <0)
        return (x / escalaAncho + xLeft);
   else
        return (x / escalaAncho -  xLeft);

}

// Escala la coordenada Y desde el puerto de vista hasta
// el area visible de la escena
GLdouble Escena::convertirY(int y, int alto) {
   GLdouble escalaAlto = (GLdouble)alto/(yTop-yBot);
   // GLdouble centro =   (yBot-yTop)/2.0;
   if (yTop<0)
        return (y / escalaAlto +  yTop);
   else
        return (y / escalaAlto - yTop);

}




