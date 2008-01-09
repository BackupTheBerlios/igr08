//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Escena.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Escena::Escena(int CW, int CH){
        listaDeObstaculos = new Lista <Obstaculo>();
        listaDeObstaculos -> inicia();
        ratioViewPort = 1.0;
        xRight =  CW / 2;
        xLeft  = -xRight;
        yTop   =  CH / 2;
        yBot   = -yTop;
        ClientWidth = CW;
        ClientHeight = CH;
        estado = false;

        paredes = new Rectangulo();

        PV * p_Mando = new PV(10,50);
        PV** vertices= new PV*();
/*        vertices[0]= new PV (400,400);
        vertices[1]= new PV (500,400);
        vertices[2]= new PV (500,450);
        vertices[3]= new PV (400,450);
  */
        vertices[0]= new PV (100,-120);
        vertices[1]= new PV (150,-120);
        vertices[2]= new PV (150,-150);
        vertices[3]= new PV (100,-150);

        mando = new Mando(vertices, p_Mando, 1);
        pelota = new Pelota();
}

// Destructora de clase
Escena::~Escena(){
        delete listaDeObstaculos;
}

// Metodo Resize de la Escena
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

// Dibuja todos los objetos fijos y moviles
void Escena::Dibuja() {
    glClear(GL_COLOR_BUFFER_BIT);


    // Dibujamos los obstaculos fijos/moviles
    glColor3f(1.0, 0.0, 1.0);
    listaDeObstaculos ->inicia();
    while(!listaDeObstaculos->final()){
       if (listaDeObstaculos->getActual()->getEsVisible()) {
         listaDeObstaculos->getActual()->Pinta();
         listaDeObstaculos->avanza();
       }
    }
    // Dibujamos el mando
    glColor3f(0.0, 1.0, 0.0);
    mando->Pinta();

    // Dibujamos las pareces
    glColor3f(1.0, 1.0, 0.0);
//    paredes->Pinta();   <========
/////////
    
    // Dibujamos la pelota
    glColor3f(1.0, 1.0, 1.0);
    if (!estado)
      pelota->Pinta();

    // Ejecutar lista de comandos en espera
    glFlush();

    // Habilitado uso del doble buffer
    SwapBuffers(hdc);
}

// Insertamos un obstaculo fijo/movil en la Escena
void Escena::InsertaObstaculo(Obstaculo * objeto) {
    listaDeObstaculos->inserta(objeto);
}

// Controles de movimiento
void Escena::Teclado(WORD& Key) {

  // Mueve mando hacia derecha
  if (Key == VK_RIGHT) {
     PV pos_Nueva = PV(mando->getPosicion()->getX() + mando->getVelocidad(), mando->getPosicion()->getY());
     mando->setPosicion(&pos_Nueva);
  }

  // Mueve mando hacia izquierda
  if (Key == VK_LEFT) {
     PV pos_Nueva = PV(mando->getPosicion()->getX() - mando->getVelocidad(), mando->getPosicion()->getY());
     mando->setPosicion(&pos_Nueva);
  }

  // Tecla A -> Acercamos
  if (Key == 'p' | Key == 'P') {
        // pausar el juego
  }

  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
}

// Transforma a corrdenadas de la Escena
void Escena::TransformarXY(PV * p){
        GLdouble x_Aux;
        GLdouble y_Aux;

        x_Aux = convertirX(p->getX(), this->ClientWidth);
        y_Aux = convertirY(p->getY(), this->ClientHeight);

        p->setX(x_Aux);
        p->setY(-y_Aux);
}

// Escala la coordenada X desde el P.Vista hasta el AVE
GLdouble Escena::convertirX(int x, int ancho) {
    GLdouble escalaAncho = (GLdouble)ancho/(xRight-xLeft);
   // GLdouble centro =   (xLeft-xRight)/2.0;
   if (xLeft <0)
        return (x / (GLfloat) escalaAncho + xLeft);
   else
        return (x / (GLfloat) escalaAncho -  xLeft);

}

// Escala la coordenada Y desde el P.Vista hasta el AVE
GLdouble Escena::convertirY(int y, int alto) {
   GLdouble escalaAlto = (GLdouble)alto/(yTop-yBot);
   // GLdouble centro =   (yBot-yTop)/2.0;
   if (yTop<0)
        return (y / (GLfloat) escalaAlto +  yTop);
   else
        return (y / (GLfloat) escalaAlto - yTop);

}
#pragma package(smart_init)
