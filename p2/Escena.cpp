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
    glClear(GL_COLOR_BUFFER_BIT);
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

void Escena::Seleccionar(Punto2f* p) {
    listaDibujos ->inicia();
    while(!listaDibujos->final()){
         listaDibujos->getActual()->Seleccionar(p);
         listaDibujos->avanza();
    }
}

void Escena::Deseleccionar(){
    listaDibujos ->inicia();
    while(!listaDibujos->final()){
         listaDibujos->getActual()->setOperacion(1);
         listaDibujos->avanza();
   }
}

void Escena::BorrarSeleccionado() {
    bool enc = false;
    listaDibujos ->inicia();
    while(!listaDibujos->final()&&!enc){
         if (listaDibujos->getActual()->getOperacion()==2) {
            listaDibujos->eliminaActual();
            enc = true;
         }
         listaDibujos->avanza();
   }
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

  // Tecla Supr -> Elimina dibujo seleccionado
  if (Key == 46) {
    this->BorrarSeleccionado();
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

        x_Aux = convertirX(p->getX(), this->ClientWidth);
        y_Aux = convertirY(p->getY(), this->ClientHeight);

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

void Escena::recorte(Punto2f * NE, Punto2f * SO){
        DibujoLineas * dl;
        Segmento * s;
    listaDibujos ->inicia();
    while(!listaDibujos->final()){
        dl = listaDibujos->getActual(); //->getDibujoLineas();
        dl->getSegmentos()->inicia();
        while(!dl->getSegmentos()->final()) {
                s = dl->getSegmentos()->getActual();
                recorteLinea(NE, SO, s->getInicio(), s->getFinal());
                dl->getSegmentos()->avanza();
        }
        listaDibujos->avanza();
   }

}

// Recorte de la escena usando el algorimo de cohen sutherland
void Escena::recorteLinea(Punto2f * esquina1, Punto2f * esquina2, Punto2f * inicio, Punto2f * final) {
    GLdouble x0, y0, x1, y1, xmin, xmax, ymin, ymax;
    int value;
    bool accept, done;
    OutCode outcode0, outcode1, outcodeOut;
    //Outcodes for P0,P1, and whichever point lies outside the clip rectangle
    GLdouble x,y;
    accept = false;
    done = false;

    x0 = inicio -> getX();
    y0 = inicio -> getY();

    x1 = final -> getX();
    y1 = final -> getY();

    xmin = min (esquina1->getX(), esquina2->getX());
    xmax = max (esquina1->getX(), esquina2->getX());
    ymin = min (esquina1->getY(), esquina2->getY());
    ymax = max (esquina1->getY(), esquina2->getY());

    CompOutCode(x0, y0, xmin, xmax, ymin, ymax, outcode0);
    CompOutCode(x1, y1, xmin, xmax, ymin, ymax, outcode1);
    do
      {
      if(outcode0.esTodoFalse() & outcode1.esTodoFalse() )  //Trivial accept and exit
        {accept = true; done=true; }
      else if (outcode0.tieneAlgunaCoordenadaTrueIgualQue(outcode1)) {
        done = true; //Logical intersection is true, so trivial reject and exit.
        }
      else // Failed both tests, so calculate the line segment to clip;
           // from an outside point to an intersection with clip edge.
        {
          //At least one endpoint is outside the clip rectangle; pick it.
          if (!outcode0.esTodoFalse() ){
            outcodeOut = outcode0;
          }
          else outcodeOut = outcode1;
          //Now find intersection point;
          //use formulas y=y0+slope*(x-x0),x=x0+(1/slope)*(y-y0).

          if (outcodeOut.getArriba())
            {     //Divide line at top of clip rectangle
              x = x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);
              y = ymax;
            }
          else if (outcodeOut.getAbajo())
            {     //Divide line at bottom of clip rectangle
              x = x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);
              y = ymin;
            }

          if (outcodeOut.getDerecha())
            {     //Divide line at right edge of clip rectangle
              y = y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);
              x = xmax;
            }
          else if (outcodeOut.getIzquierda())
            { //Divide line at left edge of clip rectangle
              y = y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);
              x = xmin;
            }
 
          // Now we move outside point to intersection point to clip,
          // and get ready for next pass.}
          if (outcodeOut == outcode0)
            {
              x0 = x; y0 = y; CompOutCode(x0,y0,xmin, xmax, ymin, ymax,outcode0);
            }
          else
            {
              x1 = x; y1 = y; CompOutCode(x1,y1,xmin, xmax, ymin, ymax,outcode1);
            }
        }   //subdivide
      } while (!done);
    if (accept ){
//    MidpointLineReal(x0,y0,x1,y1,value); //Version for real coordinates
    }
         ///////////
}  //CohenSutherlandLineClipAndDraw

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



// Método  privado para el recorte de líneas
    void Escena::CompOutCode(GLdouble x, GLdouble y, GLdouble xmin, GLdouble xmax, GLdouble ymin, GLdouble ymax, OutCode &code)
//    Compute outcode for the point (x,y)
{
      if      (y > ymax) {code.setArriba(true);}
      else if (y < ymin) {code.setAbajo(true);}

      if      (x > xmax) {code.setDerecha(true);}
      else if (x < xmin) {code.setIzquierda(true);}
}




