#include <vcl.h>
#include <math.h>
#include "UFP.h"

#pragma hdrstop
#pragma link "CGRID"
#pragma package(smart_init)
#pragma resource "*.dfm"
TGLForm2D *GLForm2D;


//---------------------------------------------------------------------------
//             MÉTODOS PUBLICOS
//---------------------------------------------------------------------------

// Creación del componente OpenGL sobre la Aplicación
_fastcall TGLForm2D::TGLForm2D(TComponent* Owner)
        : TForm(Owner) {}

//---------------------------------------------------------------------------

// Constructora de la Interface
void __fastcall TGLForm2D::FormCreate(TObject *Sender) {

    // Identificador de la tarjeta grafica
    hdc = GetDC(Handle);
    // Formato de video
    SetPixelFormatDescriptor();
    // Manejador windows-OpenGL
    hrc = wglCreateContext(hdc);
    if(hrc == NULL)
    	ShowMessage(":-)~ hrc == NULL");
    if(wglMakeCurrent(hdc, hrc) == false)
    	ShowMessage("Could not MakeCurrent");

    // Razón Aurea
    RAZON_AUREA = (1 + sqrt(5)) / 2;

    // Factor para el movimiento de Traslacion
    T = 0.01;
    // Factor Multiplo para el ZOOM
    F = 1.05;
    // Factor Divisor para el ZOOM
    f = 0.95;

    // Color de fondo de la ventana
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

    // Inicialización del volumen de vista
    xRight = 200.0;  // Derecha
    xLeft = -xRight; // Izquierda
    yTop = xRight;   // Arriba
    yBot = -yTop;    // Abajo

    // Radio del volumen de vista == 1
    RatioViewPort = 1.0;

    // Coordenadas del punto central
    P.nuevo(xRight + xLeft, yTop + yBot);

    // Establecemos las distancias con respecto a P
    // base / altura = Razon Aurea
    longX = 360;
    longY = longX / RAZON_AUREA;

    // Contador de rectangulos
    cont = 0;

    // Grosor del Punto origen P
    grosorP = 3;

    // Grosor del Punto límite Q
    grosorQ = 3;

    // Grosor de líneas de rectángulos
    grosorLinRect = 1;

    // Grosor de líneas de diagonales
    grosorLinDiag = 1;

    // Color del punto de Origen
    colorP[0] = 1.0f;
    colorP[1] = 0.0f;
    colorP[2] = 1.0f;

    // Color del punto de Límite
    colorQ[0] = 0.0f;
    colorQ[1] = 1.0f;
    colorQ[2] = 0.0f;

    // Color de líneas de rectángulos
    colorLinRect[0] = 1.0f;
    colorLinRect[1] = 0.0f;
    colorLinRect[2] = 0.0f;

    // Color de líneas de diagonales
    colorLinDiag[0] = 1.0f;
    colorLinDiag[1] = 1.0f;
    colorLinDiag[2] = 0.0f;

    // Color de líneas actual y guarda los colores de los rectangulos
    colorLinAct[0][0] = 1.0f;
    colorLinAct[0][1] = 0.0f;
    colorLinAct[0][2] = 1.0f;

    // Centrado: 1 -> Origen; 2 -> Límite
    centrado = 1;

    // Coloreado automatico
    automatico = true;

    // Embaldosado
    embaldosado = false;

    // Numero de filas y columnas iniciales
    N = 1;
}

//---------------------------------------------------------------------------

// Seleccionamos los parámetetros del formato de video
void __fastcall TGLForm2D::SetPixelFormatDescriptor() {

    PIXELFORMATDESCRIPTOR pfd = {
    	sizeof(PIXELFORMATDESCRIPTOR),
        1,
        PFD_DRAW_TO_WINDOW | PFD_SUPPORT_OPENGL | PFD_DOUBLEBUFFER,
        PFD_TYPE_RGBA,
        32,
        0,0,0,0,0,0,
        0,0,
        0,0,0,0,0,
        32,
        0,
        0,
        PFD_MAIN_PLANE,
        0,
        0,0,0
    };
    int PixelFormat = ChoosePixelFormat(hdc, &pfd);
    SetPixelFormat(hdc, PixelFormat, &pfd);
}

//---------------------------------------------------------------------

// Redimensiona el Area visual de la Escena al dimensionar la ventana
void __fastcall TGLForm2D::FormResize(TObject *Sender) {

  // Actualización del puerto de vista y su radio
  if ((ClientWidth<=1)||(ClientHeight<=1)){
     ClientWidth=400/N;
     ClientHeight=400/N;
     RatioViewPort=1.0;
     }
  else RatioViewPort = (float)ClientWidth/(float)ClientHeight;

  glViewport(0,0,ClientWidth,ClientHeight);

  // Se actualiza el volumen de vista
  // para que su radio coincida con ratioViewPort
  GLfloat RatioVolVista = (GLfloat) xRight/yTop;

  if (RatioVolVista>=RatioViewPort){
     //Aumentamos yTop-yBot
     yTop= xRight/RatioViewPort;
     yBot=-yTop;
     }
  else{
     //Aumentamos xRight-xLeft
     xRight=RatioViewPort*yTop;
     xLeft=-xRight;
     }

  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  GLScene();

}

//---------------------------------------------------------------------------

// Función que Repinta en la ventana
void __fastcall TGLForm2D::FormPaint(TObject *Sender)
{
  GLScene();
}

//---------------------------------------------------------------------------

// Destructora de la interface
void __fastcall TGLForm2D::FormDestroy(TObject *Sender)
{
    ReleaseDC(Handle,hdc);
    wglMakeCurrent(NULL, NULL);
    wglDeleteContext(hrc);
    // eliminar objetos creados
}


//---------------------------------------------------------------------------
//             MÉTODOS PRIVADOS
//---------------------------------------------------------------------------

// Dibuja el Rectangulo inicial
void TGLForm2D::dibujarRectanguloInicial(Punto A,Punto B,Punto C,Punto D,GLint grosorLinRect,GLfloat colorLinRect[3]) {
  // Selecionamos el color de dibujo
  glColor3f(colorLinRect[0],colorLinRect[1],colorLinRect[2]);

  // Seleccionamos el grosor de linea
  glLineWidth(grosorLinRect);

  // Dibujaos el punto central
  glBegin(GL_LINE_LOOP);
    glVertex2i(A.x(),A.y());
    glVertex2i(B.x(),B.y());
    glVertex2i(C.x(),C.y());
    glVertex2i(D.x(),D.y());
  glEnd();
}

//---------------------------------------------------------------------------

// Dibuja las diagonales
void TGLForm2D::dibujarDiagonales(Punto A,Punto B,Punto C,Punto D,Punto & R,
                       GLint grosorLinDiag,GLfloat colorLinDiag[3]) {
  // Selecionamos el color de las diagonales
  glColor3f(colorLinDiag[0],colorLinDiag[1],colorLinDiag[2]);

  // Seleccionamos el grosor de linea
  glLineWidth(grosorLinDiag);

  // Habilitamos opcion a lineas discontinuas
  glEnable(GL_LINE_STIPPLE);

  // Establecemos factor y patron
  glLineStipple(2,0x3333);

  // Dibujamos la diagonal A-C
  glBegin(GL_LINES);
    glVertex2i(A.x(),A.y());
    glVertex2i(C.x(),C.y());
  glEnd();

  // Calculamos un punto auxiliar R
  R.nuevo(D.x() + (A.y() - D.y()), D.y());

  // Dibujamos la diagonal B-R
  glBegin(GL_LINES);
    glVertex2i(B.x(),B.y());
    glVertex2i(R.x(),R.y());
  glEnd();

  // Deshabilitamos la opción de discontinuas
  glDisable(GL_LINE_STIPPLE);
}

//---------------------------------------------------------------------------

// Función que dibuja el punto central P
void TGLForm2D::dibujarPuntoCentral(Punto P, GLint grosorP, GLfloat colorP[3]) {
  // Selecionamos el color de dibujo
  glColor3f(colorP[0],colorP[1],colorP[2]);

  // Seleccionamos el gorsor del punto central
  glPointSize(grosorP);

  // Dibujaos el punto central
  glBegin(GL_POINTS);
    glVertex2f(P.x(),P.y());
  glEnd();
}

//-------------------------------------------------------------------------

// Función que dibuja el punto limite
void TGLForm2D::dibujarPuntoLimite(Punto A, Punto B, Punto C, Punto R, Punto & Q,
                        GLint grosorQ, GLfloat colorQ[3]) {
  // Selecionamos el color de dibujo
  glColor3f(colorQ[0],colorQ[1],colorQ[2]);

  // Seleccionamos el gorsor del punto central
  glPointSize(grosorQ);

  // Calculos de la pendiente y del corte con el eje de ordenadas
  GLdouble m_AC =(A.y()-C.y())/(GLfloat)(A.x()-C.x());
  GLdouble b_AC = (m_AC * A.x())-A.y();

  GLdouble m_BR = (B.y()-R.y())/(GLfloat)(B.x()-R.x());
  GLdouble b_BR = (m_BR * B.x())-B.y();

  GLdouble x =(b_BR - b_AC) / (m_AC - m_BR);
  GLdouble y = (m_AC*x+b_AC);

  // Asignamos las coordenadas calculadas previamente
  Q.nuevo(-x, -y);

  // Dibujamos el punto limite
  glBegin(GL_POINTS);
    glVertex2i(Q.x(),Q.y());
  glEnd();
}

//---------------------------------------------------------------------------

// Dibuja los rectangulos anidados
void TGLForm2D::dibujarRectangulosAnidados(Punto A,Punto B,Punto C,Punto D,GLint cont,
                                GLint grosorLinRect,GLfloat colorLinAct[50][3]) {
  // Seleccionamos el grosor de linea
  glLineWidth(grosorLinRect);

  // Contador actual
  GLint c = 0;
  GLint d = 0;

  // Selecionamos el color de dibujo
  glColor3f(colorLinAct[c][0],colorLinAct[c][1],colorLinAct[c][2]);

  // Punto auxiliar
  Punto Aux;

  while ( c < cont ) {

    switch (d) {

      case 0: // Caso A
           // Restando el lado
           Aux.nuevo(A.x(),A.y());
           A.nuevo(B.x(),B.y());
           B.nuevo(C.x(),C.y());
           C.nuevo(Aux.x()+(A.y()-B.y()),B.y());
           D.nuevo(Aux.x()+(A.y()-B.y()),A.y());
           break;

      case 1: // Caso B
           // Restando el lado
           Aux.nuevo(A.x(),A.y());
           A.nuevo(D.x(),D.y()-(Aux.x()-D.x()));
           C.nuevo(B.x(),B.y());
           B.nuevo(C.x(),D.y() - (Aux.x()-D.x()));
           D.nuevo(C.x() - (B.x()-A.x()),C.y());
           break;

      case 2: // Caso C
           // Restando el lado
           Aux.nuevo(A.x(),A.y());
           A.nuevo(B.x()-(B.y()-C.y()), Aux.y());
           B.nuevo(C.x()-(B.y()-C.y()), D.y());
           C.nuevo(D.x(),D.y());
           D.nuevo(Aux.x(),Aux.y());
           break;

      case 3: // Caso D
           // Restando el lado
           Aux.nuevo(B.x(),B.y());
           B.nuevo(A.x(),A.y());
           C.nuevo(Aux.x(), Aux.y()+(A.x()-D.x()));
           A.nuevo(D.x(),D.y());
           D.nuevo(A.x(), Aux.y() + (B.x()-A.x()));
           break;
      }

    // Apuntamos al siguiente rectangulo a dibujar
    if (d >= 3)
      d = 0;
    else
      d++;

    // Dibujamos el rectangulo siguiente
    glBegin(GL_LINE_LOOP);
      glVertex2i(A.x(),A.y());
      glVertex2i(B.x(),B.y());
      glVertex2i(C.x(),C.y());
      glVertex2i(D.x(),D.y());
    glEnd();

    c++;

    // Modificamos el color automaticamente
    if (automatico) {
      if (colorLinAct[c][0] < 1.0)
        colorLinAct[c][0] += 0.3;
      else {
        colorLinAct[c][0] = 0.2f;
        if (colorLinAct[c][1] < 1.0)
          colorLinAct[c][1] += 0.5;
        else {
          colorLinAct[c][1] = 0.2f;
          if (colorLinAct[c][2] < 1.0)
            colorLinAct[c][2] += 0.7;
          else
            colorLinAct[c][2] = 0.2f;
        }
      }
    }

    // Selecionamos el color de dibujo
    glColor3f(colorLinAct[c][0],colorLinAct[c][1],colorLinAct[c][2]);
  }
}

//---------------------------------------------------------------------------

// Dibuja los objetos de la escena
void TGLForm2D::dibujarEscena() {
 // Restablecemos puntos iniciales
 A.nuevo(P.x()-longX/2, P.y()+longY/2);
 B.nuevo(P.x()+longX/2, P.y()+longY/2);
 C.nuevo(P.x()+longX/2, P.y()-longY/2);
 D.nuevo(P.x()-longX/2, P.y()-longY/2);

 // Dibujamos el Rectangulo inicial
 dibujarRectanguloInicial(A,B,C,D,grosorLinRect,colorLinRect);

 // Dibujamos diagonales
 dibujarDiagonales(A,B,C,D,R,grosorLinDiag,colorLinDiag);

  // Dibujamos el punto central
 dibujarPuntoCentral(P,grosorP,colorP);

 // Dibujamos el punto Limite
 dibujarPuntoLimite(A,B,C,R,Q,grosorQ,colorQ);

 // Dibujamos el resto de rectangulos
 dibujarRectangulosAnidados(A,B,C,D,cont,grosorLinRect,colorLinAct);
 }

//---------------------------------------------------------------------------

// Dibuja la escena
void __fastcall TGLForm2D::GLScene() {

 // Aplicamos el fondo de ventana establecido
 glClear(GL_COLOR_BUFFER_BIT);

 // Si esta activado embaldosado ...
 if (embaldosado) {
   GLfloat pos_x = 0;
   GLfloat pos_y = 0;
   GLfloat baldosa_x = ClientWidth / N;
   GLfloat baldosa_y = ClientHeight / N;
   for (int i=0; i<=N; i++) {
     for (int j=0; j<=N; j++) {
        glViewport(pos_x,pos_y,baldosa_x,baldosa_y);
        dibujarEscena();
        pos_y = baldosa_y * j;
     }
     pos_x = baldosa_x * i;
   }
 }

 // Sino dibujamso a escala normal
 else {
     dibujarEscena();
 }

 // Ejecutar lista de comandos en espera
 glFlush();

 // Habilitado uso del doble buffer
 SwapBuffers(hdc);

}


//---------------------------------------------------------------------------
//             EVENTOS CON TECLADO
//---------------------------------------------------------------------------

// Funciones con teclas alfanuméricas
void __fastcall TGLForm2D::FormKeyPress(TObject *Sender, char &Key)
{
  // Tecla A -> Acercamos
  if (Key == 'a' | Key == 'A') {

     // Calculamos las nuevas coordenadas del AVE
     GLdouble xRn = (xRight + xLeft) / (GLdouble) 2;
     xRn += (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble xLn = (xRight + xLeft) / (GLdouble) 2;
     xLn -= (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble yTn = (yTop+yBot) / (GLdouble) 2;
     yTn += (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble yBn = (yTop+yBot) / (GLdouble) 2;
     yBn -= (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     // Actualizamos las variables del AVE
     xLeft = xLn;
     xRight =xRn;

     yBot = yBn;
     yTop = yTn;
   }

  // Tecla S -> Alejamos
  if (Key == 's' | Key == 'S') {

     // Calculamos las nuevas coordenadas del AVE
     GLdouble xRn = (xRight + xLeft) / (GLdouble) 2;
     xRn += (xRight - xLeft) * (GLdouble) 0.5 * (1/f);

     GLdouble xLn = (xRight + xLeft) / (GLdouble) 2;
     xLn -= (xRight - xLeft) * (GLdouble) 0.5 * (1/f);

     GLdouble yTn = (yTop+yBot) / (GLdouble) 2;
     yTn += (xRight - xLeft) * (GLdouble) 0.5 * (1/f);

     GLdouble yBn = (yTop+yBot) / (GLdouble) 2;
     yBn -= (xRight - xLeft) * (GLdouble) 0.5 * (1/f);

     // Actualizamos las variables del AVE
     xLeft = xLn;
     xRight =xRn;

     yBot = yBn;
     yTop = yTn;
   }

  // Tecla Z -> Anidamos
  if (Key == 'z' | Key == 'Z') {
     cont++;
   }

  // Tecla X -> Desanidamos
  if (Key == 'x' | Key == 'X') {
     if (cont > 0)
       cont--;
   }

  // Tecla C -> Activamos/Desactivamos opción de color
  if (Key == 'c' | Key == 'C') {
    if (!CColorGrid1->Visible) {
      CColorGrid1->Visible = true;
      Panel2->Visible = true;
      }
    else {
      CColorGrid1->Visible = false;
      Panel2->Visible = false;
      }
  }

  // Tecla G -> Activamos/Desactivamos opción de grosor
  if (Key == 'g' | Key == 'G') {
    if (!Panel1->Visible)
      Panel1->Visible = true;
    else
      Panel1->Visible = false;
  }

  // Tecla E -> Embaldosamos/Desembaldosamos
  if (Key == 'e' | Key == 'E') {
     if (embaldosado) {
       embaldosado = false;
       N = 1;
       glViewport(0,0,ClientWidth,ClientHeight);
       Embaldosar1->Caption = "&Embaldosar";
     }
     else {
        AnsiString a;
        a = InputBox("Embaldosado","Inserta el número de filas/columnas","");
        if (a == "")
           Application->MessageBox("Dato incorrecto o nulo","Error", MB_OK | MB_ICONHAND);
        else {
           N = StrToInt(a);
           embaldosado = true;
           Embaldosar1->Caption = "&Desembaldosar";
        }
     }
  }

  // Modificamos la matriz de Proyecccion
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  // Redibujamos con los nuevos parámetros
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  GLScene();
}

//---------------------------------------------------------------------------

// Funciones con teclas de movimiento, espaciado y escape
void __fastcall TGLForm2D::FormKeyDown(TObject *Sender, WORD &Key,
      TShiftState Shift)
{
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

  // Cambiamos origen de coordenadas
  if (Key == VK_SPACE) {
     GLdouble despX = (xRight + xLeft) / (GLdouble) 2;
     GLdouble despY = (yTop + yBot) / (GLdouble) 2;
     // Cambiar a limite
     if (centrado == 1) {
        xRight += Q.x() - despX;
        xLeft  += Q.x() - despX;
        yTop   += Q.y() - despY;
        yBot   += Q.y() - despY;
        centrado = 2;
     }
     // Cambiar a origen
     else {
        xRight -= Q.x();
        xLeft  -= Q.x();
        yTop   -= Q.y();
        yBot   -= Q.y();
        centrado = 1;
     }
    }

  // Salimos de la aplicacion
  if (Key == VK_ESCAPE) {
    Application->Terminate();
  }
 
  // Modificamos la matriz de Proyecccion
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  // Redibujamos con los nuevos parámetros
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  GLScene();
}


//---------------------------------------------------------------------------
//             INTERACCIÓN CON EL MENU
//---------------------------------------------------------------------------

// Activamos Opciones de coloreado
void __fastcall TGLForm2D::Comandos1Click(TObject *Sender)
{
    if (!CColorGrid1->Visible) {
      CColorGrid1->Visible = true;
      Panel2->Visible = true;
      }
    else {
      CColorGrid1->Visible = false;
      Panel2->Visible = false;
      }
}

//---------------------------------------------------------------------------

// Muestra ayuda sobre los comandos
void __fastcall TGLForm2D::Comandos2Click(TObject *Sender)
{
  ShowMessage(" COMANDOS BÁSICOS \n\n  "
              "     A = Acercar \n  "
              "     S = Alejar \n  "
              "     Z = Anidar \n  "
              "     X = Desanidar \n\n "
              "     C = Color \n "
              "     G = Grosor \n "
              "     E = Embaldosar \n\n "
              "Espacio = Origen/Límite \n "
              "Flechas = Traslación \n\n  "
              "      ESC = Salir");
}

//---------------------------------------------------------------------------

// Autores de la práctica
void __fastcall TGLForm2D::Autores1Click(TObject *Sender)
{
  ShowMessage(" Iván Romero \n"
              " Pedro Sánchez");
}

//---------------------------------------------------------------------------

// Terminar aplicación
void __fastcall TGLForm2D::Salir1Click(TObject *Sender)
{
 Application->Terminate();
}

//---------------------------------------------------------------------------

// Cambiar colores
void __fastcall TGLForm2D::CColorGrid1Change(TObject *Sender)
{
 GLfloat ColorAux[3];

 switch (CColorGrid1->ForegroundIndex) {

     case 0: // Negro
             ColorAux[0] = 0.0f; ColorAux[1] = 0.0f; ColorAux[2] = 0.0f;
             break;

     case 1: // Marrón
             ColorAux[0] = 0.4f; ColorAux[1] = 0.0f; ColorAux[2] = 0.0f;
             break;

     case 2: // Verde oscuro
             ColorAux[0] = 0.0f; ColorAux[1] = 0.5f; ColorAux[2] = 0.0f;
             break;

     case 3: // Añil
             ColorAux[0] = 0.5f; ColorAux[1] = 0.5f; ColorAux[2] = 0.0f;
             break;

     case 4: // Azul oscuro
             ColorAux[0] = 0.0f; ColorAux[1] = 0.0f; ColorAux[2] = 0.4f;
             break;

     case 5: // Violeta
             ColorAux[0] = 0.4f; ColorAux[1] = 0.0f; ColorAux[2] = 0.4f;
             break;

     case 6: // Azul Turquesa
             ColorAux[0] = 0.0f; ColorAux[1] = 0.5f; ColorAux[2] = 0.5f;
             break;

     case 7: // Gris claro
             ColorAux[0] = 0.8f; ColorAux[1] = 0.8f; ColorAux[2] = 0.8f;
             break;

     case 8: // Gris oscuro
             ColorAux[0] = 0.4f; ColorAux[1] = 0.4f; ColorAux[2] = 0.4f;
             break;

     case 9: // Rojo
             ColorAux[0] = 1.0f; ColorAux[1] = 0.0f; ColorAux[2] = 0.0f;
             break;

     case 10: // Verde claro
             ColorAux[0] = 0.0f; ColorAux[1] = 1.0f; ColorAux[2] = 0.0f;
             break;

     case 11: // Amarillo
             ColorAux[0] = 1.0f; ColorAux[1] = 1.0f; ColorAux[2] = 0.0f;
             break;

     case 12: // Azul neutro
             ColorAux[0] = 0.0f; ColorAux[1] = 0.0f; ColorAux[2] = 0.6f;
             break;

     case 13: // Rosa
             ColorAux[0] = 1.0f; ColorAux[1] = 0.0f; ColorAux[2] = 1.0f;
             break;

     case 14: // Cyan
             ColorAux[0] = 0.0f; ColorAux[1] = 1.0f; ColorAux[2] = 1.0f;
             break;

     case 15: // Blanco
             ColorAux[0] = 1.0f; ColorAux[1] = 1.0f; ColorAux[2] = 1.0f;
             break;

     default:break;
   }

   // Seleccionado Punto de Origen
   if (RadioButton1->Checked) {
      colorP[0] = ColorAux[0];
      colorP[1] = ColorAux[1];
      colorP[2] = ColorAux[2];
   }

   // Seleccionado Punto Limite
   if (RadioButton2->Checked) {
      colorQ[0] = ColorAux[0];
      colorQ[1] = ColorAux[1];
      colorQ[2] = ColorAux[2];
   }

   // Seleccionado Rectángulo
   if (RadioButton3->Checked) {
      colorLinRect[0] = ColorAux[0];
      colorLinRect[1] = ColorAux[1];
      colorLinRect[2] = ColorAux[2];
   }

   // Seleccionado Diagonales
   if (RadioButton4->Checked) {
      colorLinDiag[0] = ColorAux[0];
      colorLinDiag[1] = ColorAux[1];
      colorLinDiag[2] = ColorAux[2];
   }

   // Seleccionado Color actual
   if (RadioButton6->Checked) {
      colorLinAct[cont][0] = ColorAux[0];
      colorLinAct[cont][1] = ColorAux[1];
      colorLinAct[cont][2] = ColorAux[2];
   }

   // Seleccionado Fondo
   if (RadioButton5->Checked) {
      glClearColor(ColorAux[0],ColorAux[1],ColorAux[2],1.0f);
   }

   // Redibujamos con los cambios aplicados
   GLScene();

}

//---------------------------------------------------------------------------

// Opciones de grosor de lineas y puntos
void __fastcall TGLForm2D::GrosorPunto1Click(TObject *Sender)
{
  if (!Panel1->Visible)
    Panel1->Visible = true;
 else
    Panel1->Visible = false;
}

//---------------------------------------------------------------------------

// Variamos el valor de grosor del punto origen P
void __fastcall TGLForm2D::ScrollBar1Change(TObject *Sender)
{
 grosorP = ScrollBar1->Position;
 GLScene();
}

//---------------------------------------------------------------------------

// Variamos el valor de grosor del punto limite Q
void __fastcall TGLForm2D::ScrollBar2Change(TObject *Sender)
{
 grosorQ = ScrollBar2->Position;
 GLScene();
}

//---------------------------------------------------------------------------

// Variamos el valor de grosor de las lineas de los rectangulos
void __fastcall TGLForm2D::ScrollBar3Change(TObject *Sender)
{
 grosorLinRect = ScrollBar3->Position;
 GLScene();
}

//---------------------------------------------------------------------------

// Variamos el valor de grosor de las lineas de las diagonales
void __fastcall TGLForm2D::ScrollBar4Change(TObject *Sender)
{
 grosorLinDiag = ScrollBar4->Position;
 GLScene();
}

//---------------------------------------------------------------------------

// Desabilitar opciones de color por teclado
void __fastcall TGLForm2D::RadioButton2KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = true;
}
void __fastcall TGLForm2D::RadioButton1KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = true;
}
void __fastcall TGLForm2D::RadioButton3KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = true;
}
void __fastcall TGLForm2D::RadioButton4KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = true;
}
void __fastcall TGLForm2D::RadioButton5KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = true;
}
void __fastcall TGLForm2D::RadioButton6KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = false;
}

//---------------------------------------------------------------------------

// Activar la opción de embaldosado
void __fastcall TGLForm2D::Embaldosar1Click(TObject *Sender)
{
 if (embaldosado) {
   embaldosado = false;
   N = 1;
   glViewport(0,0,ClientWidth,ClientHeight);
   Embaldosar1->Caption = "&Embaldosar";
 }
 else {
     AnsiString a;
     a = InputBox("Embaldosado","Inserta el número de filas/columnas","");
     if (a == "")
       Application->MessageBox("Dato incorrecto o nulo","Error", MB_OK | MB_ICONHAND);
     else {
         N = StrToInt(a);
         embaldosado = true;
         Embaldosar1->Caption = "&Desembaldosar";
     }
 }
  GLScene();
}

//---------------------------------------------------------------------------



