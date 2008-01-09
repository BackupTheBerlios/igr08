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
    //Cor de fondo de la ventana
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

    estado = false;
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
    delete scene;
    scene = NULL;
}
//---------------------------------------------------------------------------
// Control del teclado
void __fastcall TGLForm2D::FormKeyDown(TObject *Sender, WORD &Key,
      TShiftState Shift)
{
    if (estado)
      scene->Teclado(Key);
}
//---------------------------------------------------------------------------
// Comienza la partida previa al juego
// Timer para la animacion
// Inicia la configuracion de la partida
void __fastcall TGLForm2D::Nueva1Click(TObject *Sender)
{
   if (scene != NULL){
      delete scene;
      scene = NULL;
   }
  scene = new Escena(ClientWidth,ClientHeight);
  GLScene();
}
//---------------------------------------------------------------------------



