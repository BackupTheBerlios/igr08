#include <vcl.h>
#include "UFP.h"

#pragma hdrstop
#pragma package(smart_init)
#pragma resource "*.dfm"
//---------------------------------------------------------------------------
TGLForm2D *GLForm2D;
//---------------------------------------------------------------------------
__fastcall TGLForm2D::TGLForm2D(TComponent* Owner)
        : TForm(Owner)
{}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::FormCreate(TObject *Sender)
{
    hdc = GetDC(Handle);
    SetPixelFormatDescriptor();
    hrc = wglCreateContext(hdc);
    if(hrc == NULL)
    	ShowMessage(":-)~ hrc == NULL");
    if(wglMakeCurrent(hdc, hrc) == false)
    	ShowMessage("Could not MakeCurrent");
    //Color de fondo de la ventana
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

    estado = 0;
    play = false;
    scene = NULL;


    // Inicializar mando etc.
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::SetPixelFormatDescriptor()
{
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
void __fastcall TGLForm2D::FormResize(TObject *Sender)
{
  // Se actualiza puerto de vista y su radio
  if ((ClientWidth<=1)||(ClientHeight<=1)){
     ClientWidth=600;
     ClientHeight=600;
     RatioViewPort=1.0;
     }
  else RatioViewPort= (float)ClientWidth/(float)ClientHeight;

  // Llamamos a la función Resize de la Escena
  if (scene !=NULL)
    scene->Resize(ClientWidth,ClientHeight);

  // Refrescamos
  GLScene();
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::GLScene()
{
glClear(GL_COLOR_BUFFER_BIT);

// Comandos para dibujar la escena
if (scene != NULL)
  scene->Dibuja();

glFlush();
SwapBuffers(hdc);
}

//---------------------------------------------------------------------------
void __fastcall TGLForm2D::FormPaint(TObject *Sender) {
  GLScene();
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::FormDestroy(TObject *Sender)
{
    ReleaseDC(Handle,hdc);
    wglMakeCurrent(NULL, NULL);
    wglDeleteContext(hrc);

    // Eliminar objetos creados
   if (scene != NULL){
      delete scene;
      scene = NULL;
   }
}
//---------------------------------------------------------------------------
// Control del teclado
void __fastcall TGLForm2D::FormKeyDown(TObject *Sender, WORD &Key,
      TShiftState Shift)
{
    if (play)
      scene->Teclado(Key);
    GLScene();
}
//---------------------------------------------------------------------------
// Comienza la partida previa al juego
// Timer para la animación
// Inicia la configuración de la partida
void __fastcall TGLForm2D::Nueva1Click(TObject *Sender)
{
   if (scene != NULL){
      delete scene;
      scene = NULL;
   }
  play = false;
  estado = 1;
  scene = new Escena(ClientWidth,ClientHeight);
  GLScene();
        Form1->Visible= true;
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::Iniciar1Click(TObject *Sender)
{
play = true;
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::Convexo1Click(TObject *Sender)
{
  if (estado == 1) {
  AnsiString dato = "10";

  if (InputQuery("Solicitando datos","Numero de Lados:",dato)) {
    nLados = StrToInt(dato);
    if (InputQuery("Solicitando datos","Logitud del Lado:",dato)) {
        longLado = StrToInt(dato);
        Application->MessageBox("Elige el centro","Poligono Convexo",MB_OK);
        estado = 2;
     }
   }
  }
  play = true;
}
//---------------------------------------------------------------------------
// Seleccion de posision e inserccion de obstaculos
void __fastcall TGLForm2D::FormMouseDown(TObject *Sender,
      TMouseButton Button, TShiftState Shift, int X, int Y)
{
 PV * p;
 p = NULL;
 PV * pos_actual;

 switch (estado) {
        case 2: { // Poligonos convexos
                  p = new PV(X,Y);
                  scene->TransformarXY(p);
                  if (pos_actual== NULL)
                     pos_actual = p->clon();
                  break;
                }

        case 3: { // Poligonos convexos
                  p = new PV(X,Y);
                  scene->TransformarXY(p);
                  if (pos_actual== NULL)
                     pos_actual = p->clon();
                  break;
                }

        default: break;
 }


 if (p != NULL) {
    delete p;
    p = NULL;
 }
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::Timer1Timer(TObject *Sender)
{
Timer1->Enabled = false;

if (play){
    scene->avanza();
    if (!scene->getJuego()) {
      /* String numero = IntToStr(scene->getNumVidas());
       ShowMessage("Te quedan "+ numero + " vidas");*/
       Form1->setVidas(scene->getNumVidas());
       if (scene->getNumVidas() > 0)
          scene->setJuego();
       else {
         ShowMessage("Fin del juego");
         play = false;
       }
    }
    GLScene();
    }
Timer1->Enabled = true;
}
//---------------------------------------------------------------------------

