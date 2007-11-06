#include <vcl.h>
#pragma hdrstop
#include "UFP.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma link "CGRID"
#pragma resource "*.dfm"
TGLForm2D *GLForm2D;
const GLfloat RAZON_AUREA = 1.61803389;
bool automatico;
//---------------------------------------------------------------------------
__fastcall TGLForm2D::TGLForm2D(TComponent* Owner)
        : TForm(Owner)
{}
//---------------------------------------------------------------------------
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

    // Coordenadas del punto limite
    Qx = 82;
    Qy = -50;

    // Establecemos las distancias con respecto a P
    longX = 180;
    longY = 110;

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
}
//---------------------------------------------------------------------------
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
void __fastcall TGLForm2D::FormResize(TObject *Sender) {

  // Actualización del puerto de vista y su radio
  if ((ClientWidth<=1)||(ClientHeight<=1)){
     ClientWidth=400;
     ClientHeight=400;
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
  gluOrtho2D(xLeft,xRight, yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  GLScene();

}
//---------------------------------------------------------------------------
// Función que dibuja el punto central
void dibujarPuntoCentral(Punto P, GLint grosorP, GLfloat colorP[3]) {
  // Selecionamos el color de dibujo
  glColor3f(colorP[0],colorP[1],colorP[2]);

  // Seleccionamos el gorsor del punto central
  glPointSize(grosorP);

  // Dibujaos el punto central
  glBegin(GL_POINTS);
    glVertex2i(P.x(),P.y());
  glEnd();
}

// Dibuja el Rectangulo inicial
void dibujarRectanguloInicial(Punto A,Punto B,Punto C,Punto D,GLint grosorLinRect,GLfloat colorLinRect[3]) {
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

// Dibuja las diagonales
void dibujarDiagonales(Punto A,Punto B,Punto C,Punto D, Punto R, GLint grosorLinDiag,GLfloat colorLinDiag[3]) {
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
  //R.nuevo(D.x() + (A.y() - D.y()), D.y());
  R.nuevo((GLint) C.x() - ((A.y() - D.y())/RAZON_AUREA), D.y());

  // Dibujamos la diagonal B-R
  glBegin(GL_LINES);
    glVertex2i(B.x(),B.y());
    glVertex2i(R.x(),R.y());
  glEnd();

  // Deshabilitamos la opción de discontinuas
  glDisable(GL_LINE_STIPPLE);
}

// Función que dibuja el punto limite
void dibujarPuntoLimite(Punto P, GLint Qx, GLint Qy, GLint grosorQ,GLfloat colorQ[3]) {
  // Selecionamos el color de dibujo
  glColor3f(colorQ[0],colorQ[1],colorQ[2]);

  // Seleccionamos el gorsor del punto central
  glPointSize(grosorQ);

  // Calculo el punto Q con respecto a P
  Punto Q;
  Q.nuevo(P.x() + Qx, P.y() + Qy);

  // Dibujamos el punto limite
  glBegin(GL_POINTS);
    glVertex2i(Q.x(),Q.y());
  glEnd();
}

// Dibuja los rectangulos anidados
void dibujarRectangulosAnidados(Punto A,Punto B,Punto C,Punto D,GLint cont,
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
         /*  Aux.nuevo(A.x(),A.y());
           A.nuevo(B.x(),B.y());
           B.nuevo(C.x(),C.y());
           C.nuevo(Aux.x()+(A.y()-B.y()),B.y());
           D.nuevo(Aux.x()+(A.y()-B.y()),A.y());       */

           Aux.nuevo(B.x(),B.y());
           A.nuevo(B.x(),B.y());
           B.nuevo(C.x(),C.y());
           C.nuevo( (GLint) C.x() - ((A.y()-B.y())/RAZON_AUREA), B.y());
           D.nuevo( (GLint) A.x() - ((A.y()-B.y())/RAZON_AUREA), A.y());
           break;

      case 1: // Caso B
        /* Aux.nuevo(A.x(),A.y());
           A.nuevo(D.x(),D.y()-(Aux.x()-D.x())+5);
           C.nuevo(B.x(),B.y());
           B.nuevo(C.x(),D.y() - (Aux.x()-D.x())+5);
           D.nuevo(C.x() - (B.x()-A.x()),C.y());            */

           Aux.nuevo(A.x(),A.y());
           A.nuevo(D.x(), (GLint) (C.y() + ((Aux.x()-D.x())/RAZON_AUREA)));
           C.nuevo(B.x(),B.y());
           B.nuevo(C.x(), (GLint) (C.y() + ((Aux.x()-D.x())/RAZON_AUREA)));
           D.nuevo(C.x() - (B.x()-A.x()),C.y());
           break;

      case 2: // Caso C
         /*Aux.nuevo(A.x(),A.y());
           A.nuevo(B.x()-(B.y()-C.y()), Aux.y());
           B.nuevo(C.x()-(B.y()-C.y()), D.y());
           C.nuevo(D.x(),D.y());
           D.nuevo(Aux.x(),Aux.y());    */
           Aux.nuevo(A.x(),A.y());
           A.nuevo( (GLint) A.x()+((B.y()-C.y())/RAZON_AUREA), Aux.y());
           B.nuevo( (GLint) D.x()+((B.y()-C.y())/RAZON_AUREA), D.y());
           C.nuevo(D.x(),D.y());
           D.nuevo(Aux.x(),Aux.y());
           break;

      case 3: // Caso D
     /*    Aux.nuevo(B.x(),B.y());
           B.nuevo(A.x(),A.y());
           C.nuevo(Aux.x(), Aux.y()+(A.x()-D.x())-3);
           A.nuevo(D.x(),D.y());
           D.nuevo(A.x(), Aux.y() + (B.x()-A.x())-3);     */
           Aux.nuevo(B.x(),B.y());
           B.nuevo(A.x(),A.y());
           C.nuevo(Aux.x(), (GLint) A.y() - ((A.x()-D.x())/RAZON_AUREA));
           A.nuevo(D.x(),D.y());
           D.nuevo(A.x(), (GLint) A.y() - ((B.x()-A.x())/RAZON_AUREA));
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

void __fastcall TGLForm2D::GLScene() {

 // Aplicamos el fondo de ventana establecido
 glClear(GL_COLOR_BUFFER_BIT);

 // Restablecemos puntos iniciales
 A.nuevo(P.x()-longX, P.y()+longY);
 B.nuevo(P.x()+longX, P.y()+longY);
 C.nuevo(P.x()+longX, P.y()-longY);
 D.nuevo(P.x()-longX, P.y()-longY);

 // Dibujamos el Rectangulo inicial
 dibujarRectanguloInicial(A,B,C,D,grosorLinRect,colorLinRect);

 // Dibujamos diagonales
 dibujarDiagonales(A,B,C,D,R,grosorLinDiag,colorLinDiag);

  // Dibujamos el punto central
 dibujarPuntoCentral(P,grosorP,colorP);

 // Dibujamos el punto Limite
 dibujarPuntoLimite(P,Qx,Qy,grosorQ,colorQ);

 // Dibujamos el resto de rectangulos
 dibujarRectangulosAnidados(A,B,C,D,cont,grosorLinRect,colorLinAct);

 // Ejecutar lista de comandos en espera
 glFlush();

 // Habilitado uso del doble buffer
 SwapBuffers(hdc);
}

//---------------------------------------------------------------------------
void __fastcall TGLForm2D::FormPaint(TObject *Sender)
{
  GLScene();
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::FormDestroy(TObject *Sender)
{
    ReleaseDC(Handle,hdc);
    wglMakeCurrent(NULL, NULL);
    wglDeleteContext(hrc);
    // eliminar objetos creados
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::FormKeyPress(TObject *Sender, char &Key)
{
  // Tecla A -> Acercamos
  if (Key == 'a' | Key == 'A') {
     xLeft += 5;
     xRight -=5;
     yBot += 5;
     yTop -=5;
   }

  // Tecla S -> Alejamos
  if (Key == 's' | Key == 'S') {
     xLeft -= 5;
     xRight+= 5;
     yBot -= 5;
     yTop += 5;
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
void __fastcall TGLForm2D::FormKeyDown(TObject *Sender, WORD &Key,
      TShiftState Shift)
{
  // Trasladamos hacia arriba
  if (Key == VK_UP) {
     P.y(P.y() + 10);
  }

  // Trasladamos hacia abajo
  if (Key == VK_DOWN) {
    P.y(P.y() - 10);
  }

  // Trasladamos hacia derecha
  if (Key == VK_RIGHT) {
    P.x(P.x() + 10);
  }

  // Trasladamos hacia izquierda
  if (Key == VK_LEFT) {
    P.x(P.x() - 10);
  }

  // Cambiamos origen de coordenadas
  if (Key == VK_SPACE) {
     // Cambiar a limite
     if (centrado == 1) {
       P.nuevo(-Qx,-Qy);
       Q.nuevo(xRight + xLeft, yTop + yBot);
       centrado = 2;
     }
     // Cambiar a origen
     else {
       Q.nuevo(-P.x(),-P.y());
       P.nuevo(xRight + xLeft, yTop + yBot);
       centrado = 1;
     }
    }

  // Salimos de la aplicacion
  if (Key == VK_ESCAPE) {
    Application->Terminate();
  }

  // Modificamos las matrices
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  GLScene();
}
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
void __fastcall TGLForm2D::Comandos2Click(TObject *Sender)
{
  ShowMessage(" COMANDOS BÁSICOS \n\n  "
              "     A = Acercar \n  "
              "     S = Alejar \n  "
              "     Z = Anidar \n  "
              "     X = Desanidar \n\n "
              "     C = Color \n "
              "     G = Grosor \n\n "
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
             ColorAux[0] = 0.2f; ColorAux[1] = 0.2f; ColorAux[2] = 0.2f;
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
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::RadioButton6KeyPress(TObject *Sender, char &Key)
{
 CColorGrid1->Visible = false;
 Panel2->Visible = false;
 automatico = false;
}
//---------------------------------------------------------------------------

