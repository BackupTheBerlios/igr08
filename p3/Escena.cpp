//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Escena.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Escena::Escena(int CW, int CH){

//        listaDeObstaculos = new Obstaculo*();
        listaDeObstaculos = new Obstaculo*[60];
        ratioViewPort = 1.0;
        xRight =  CW / 2;
        xLeft  = -xRight;
        yTop   =  CH / 2;
        yBot   = -yTop;
        ClientWidth = CW;
        ClientHeight = CH;
        estado = false;
        numVidas = 3;
        Form1->Vidas->Text = numVidas;
        puntos = 0;
        this->numObstaculos = 0;

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

        // Cargamos los obstaculos

  /*
        listaDeObstaculos[0] = new Circulo(new PV(-200,100), 20);
        listaDeObstaculos[1] = new Circulo(new PV(-200,150), 20);
        listaDeObstaculos[2] = new Circulo(new PV(-100,100), 20);
        listaDeObstaculos[3] = new Circulo(new PV(-100,150), 20);
        listaDeObstaculos[4] = new Circulo(new PV(0,100), 20);
        listaDeObstaculos[5] = new Circulo(new PV(0,150), 20);
        listaDeObstaculos[6] = new Circulo(new PV(100,100), 20);
        listaDeObstaculos[7] = new Circulo(new PV(100,150), 20);
        listaDeObstaculos[8] = new Circulo(new PV(200,100), 20);
        listaDeObstaculos[9] = new Circulo(new PV(200,150), 20);
*/
        listaDeObstaculos[0] = new Convexo(new PV(-150,100), 20,5);
        listaDeObstaculos[1] = new Convexo(new PV(-150,150), 20,5);
        listaDeObstaculos[2] = new Convexo(new PV(-50,100), 20,5);
        listaDeObstaculos[3] = new Convexo(new PV(-50,150), 20,5);
        listaDeObstaculos[4] = new Convexo(new PV(50,100), 20,5);
        listaDeObstaculos[5] = new Convexo(new PV(50,150), 20,5);
        listaDeObstaculos[6] = new Convexo(new PV(150,100), 20,5);
        listaDeObstaculos[7] = new Convexo(new PV(150,150), 20,5);

        this->numObstaculos = 8;
        this->numObstaculosVivos = numObstaculos;


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
        for (int i= 0; i<numObstaculos; i++){
                delete listaDeObstaculos[i];
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

        for (int i = 0; i < this->numObstaculos; i++){
                if (listaDeObstaculos[i]->getEsVisible()){
                        listaDeObstaculos[i]->Pinta();
                 }
        }
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
   // if (estado)
      pelota->Pinta();

/*    // Ejecutar lista de comandos en espera
    glFlush();

    // Habilitado uso del doble buffer
    SwapBuffers(hdc);   */
}

// Insertamos un obstaculo fijo/movil en la Escena
void Escena::InsertaObstaculo(Obstaculo * objeto) {
//    listaDeObstaculos->inserta(objeto);
//    listaDeObstaculos->push_front(objeto);
}

// Controles de movimiento
void Escena::Teclado(WORD& Key) {

  // Mueve mando hacia derecha
  if (Key == VK_RIGHT) {
     if (mando->getPosicion()->getX() < xRight - 70)
        mando->Mueve(PV(20,0));
  }

  // Mueve mando hacia izquierda
  if (Key == VK_LEFT) {
    if (mando->getPosicion()->getX() > xLeft + 70)
       mando->Mueve(PV(-20,0));
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

     estado = true;
     
      if (paredDcha->Corte(this->pelota, tIN, normal) ||
          paredIzq->Corte(this->pelota, tIN, normal) ||
          paredArriba->Corte(this->pelota, tIN, normal) ||
          mando->Corte(this->pelota, tIN, normal) ||
          haChocadoConObstaculos(tIN, normal)) {
            pelota->avanza(tIN);
            pelota->rebota(normal);
            if (numObstaculosVivos == 0){
                ShowMessage("Has ganado");
                pelota->stop();
            }
           }
      else if (paredPierde->Corte(this->pelota, tIN, normal)) {
            estado = false;
            numVidas--;
             }
           else {
            pelota->avanza(1);
           }
}

bool Escena::getJuego(){
   return estado;
}

void Escena::setJuego(){
   delete pelota;
   pelota = new Pelota();
   estado = true;
}

int Escena::getNumVidas() {
   return numVidas;
}


bool Escena::haChocadoConObstaculos(GLdouble &tIN, PV* &normal) {

   int i = 0;
   bool enc = false;
   // FALTA ARREGLAR ALGO, CREO Q FALLA EN CIRCULO
   while (i<numObstaculos && !enc) {
      if (listaDeObstaculos[i]->getEsVisible()) {
         if (listaDeObstaculos[i]->Corte(this->pelota, tIN, normal)) {
            puntos += 10;
            listaDeObstaculos[i]->setEsVisible(false);
            this->numObstaculosVivos--;
            Form1->setPuntos(puntos);
            enc = true;
         }
      }
      i++;
   }   
   return enc;
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
