//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Escena.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Escena::Escena(int CW, int CH){
        listaDeObstaculos = new list <Obstaculo*>();
        ratioViewPort = 1.0;
        xRight =  CW / 2;
        xLeft  = -xRight;
        yTop   =  CH / 2;
        yBot   = -yTop;
        ClientWidth = CW;
        ClientHeight = CH;
        estado = false;


        // Cargamos las paredes que limitan el espacio del juego
/*        paredIzq = new Rectangulo(30, 580, new PV(-230,290));
        paredDcha= new Rectangulo(30, 580, new PV(200,290));
        paredArriba = new Rectangulo(450, 30, new PV(-230,+290));
        paredPierde = new Rectangulo(450, 30, new PV(-230,-260));
  */
        paredIzq = new Rectangulo(20, 580, 4, new PV(-240,290));
        paredDcha= new Rectangulo(20, 580, 4, new PV(220,290));
        paredArriba = new Rectangulo(460, 20, 4, new PV(-230,+290));
        paredPierde = new Rectangulo(460, 20, 4, new PV(-230,-270));


        // Cargamos el mando
        PV * pos_Mando = new PV(0,-200);
      //  TransformarXY(pos_Mando);

        PV** vert_Mando = new PV*[4];

        vert_Mando[0] = new PV(pos_Mando->getX() - 40, pos_Mando->getY() + 8);
        vert_Mando[1] = new PV(pos_Mando->getX() + 40, pos_Mando->getY() + 8);
        vert_Mando[2] = new PV(pos_Mando->getX() + 40, pos_Mando->getY() - 8);
        vert_Mando[3] = new PV(pos_Mando->getX() - 40, pos_Mando->getY() - 8);

        mando = new Mando(vert_Mando, pos_Mando, 10);
        /*
        // Cargamos los obstaculos
        PV * centroObj1 = new PV(100,100);
        PV * centroObj2 = new PV(50,50);
        Obstaculo * c1 = new Convexo(centroObj1, 50,5);
        Obstaculo * c2 = new Circulo(centroObj2, 50);
        listaDeObstaculos-> push_front(c1);
        listaDeObstaculos-> push_front(c2);
        delete centroObj1;
        delete centroObj2;
            */
        // Cargamos la pelota
        pelota = new Pelota();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluOrtho2D(xLeft,xRight,yBot,yTop);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
}

// Destructora de clase
Escena::~Escena(){

        list<Obstaculo*>::iterator it;
        Obstaculo * obsTmp;
        for( it = listaDeObstaculos->begin(); it != listaDeObstaculos->end(); it++ ) {
                obsTmp = *it;
                delete obsTmp;
        }
        delete listaDeObstaculos;
        delete mando;
        delete pelota;
        delete paredIzq;
        delete paredDcha;
        delete paredArriba;
        delete paredPierde;
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
    //glClear(GL_COLOR_BUFFER_BIT);

    // Dibujamos los obstaculos fijos/moviles
    glColor3f(0.0, 0.0, 1.0);
           /*
    list<Obstaculo*>::iterator it;
    Obstaculo * obsTmp;
    for( it = listaDeObstaculos->begin(); it != listaDeObstaculos->end(); it++ ) {
        obsTmp = *it;
        if (obsTmp->getEsVisible()){
                 obsTmp->Pinta();
         }
    }
*/
    // Dibujamos el mando
    glColor3f(0.0, 1.0, 0.0);
    mando->Pinta();

    // Dibujamos las paredes
    glColor3f(1.0, 1.0, 0.0);
    paredIzq->Pinta();
    paredDcha->Pinta();
    paredArriba->Pinta();
    paredPierde->Pinta();

    // Dibujamos la pelota
    glColor3f(1.0, 0.0, 0.0);
    if (!estado)
      pelota->Pinta();
     
/*    // Ejecutar lista de comandos en espera
    glFlush();

    // Habilitado uso del doble buffer
    SwapBuffers(hdc);   */
}

// Insertamos un obstaculo fijo/movil en la Escena
void Escena::InsertaObstaculo(Obstaculo * objeto) {
//    listaDeObstaculos->inserta(objeto);
    listaDeObstaculos->push_front(objeto);
}

// Controles de movimiento
void Escena::Teclado(WORD& Key) {

  // Mueve mando hacia derecha
  if (Key == VK_RIGHT) {
     if (mando->getPosicion()->getX() < xRight - 70)
        mando->Mueve(PV(10,0));
  }

  // Mueve mando hacia izquierda
  if (Key == VK_LEFT) {
    if (mando->getPosicion()->getX() > xLeft + 70)
       mando->Mueve(PV(-10,0));
  }

  // Tecla P -> Pausar el juego
  if (Key == 'p' | Key == 'P') {
        // pausar el juego
  }
  this->Dibuja();
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();

}
void Escena::avanza(){
        PV * normal;
        GLdouble tIN;
//        paredPierde
      if ( paredDcha ->Corte(this->pelota, tIN, normal)){
           pelota->avanza(tIN);
           ShowMessage("Hay corte");
           }
           else {
               pelota->avanza(1);
           }

}

/*
// Transforma a corrdenadas de la Escena
void Escena::TransformarXY(PV * p){
        GLdouble x_Aux;
        GLdouble y_Aux;

        x_Aux = convertirX(p->getX(), this->ClientWidth);
        y_Aux = convertirY(p->getY(), this->ClientHeight);

        p->setX(x_Aux);
        p->setY(-y_Aux);
}  */

// Escala la coordenada X desde el P.Vista hasta el AVE
/*GLdouble Escena::convertirX(int x, int ancho) {
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

}  */
#pragma package(smart_init)
