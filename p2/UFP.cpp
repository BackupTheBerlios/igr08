//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "UFP.h"
#include <fstream.h>
#pragma package(smart_init)
#pragma resource "*.dfm"
//---------------------------------------------------------------------------
TGLForm2D *GLForm2D;
//---------------------------------------------------------------------------
__fastcall TGLForm2D::TGLForm2D(TComponent* Owner)
        : TForm(Owner)
{
}
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

    // Distancia equidistante al centro
    distancia = 200;

    // Creamos una nueva escena
    scene = new Escena(distancia);

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
     ClientWidth=400;
     ClientHeight=400;
     RatioViewPort=1.0;
     }
  else RatioViewPort= (float)ClientWidth/(float)ClientHeight;

  // Llamamos a la funci�n Resize de la Escena
   if (scene !=NULL) {
     scene->Resize(ClientWidth,ClientHeight);
  }

  // Refrescamos
  GLScene();
}
//---------------------------------------------------------------------------
void __fastcall TGLForm2D::GLScene()
{
glClear(GL_COLOR_BUFFER_BIT);

glColor3f(1.0,0.0,1.0);
/*Punto2f * p1 = new Punto2f(0.0, 20.0);
Punto2f * p2 = new Punto2f(100.0, 20.0);
Punto2f * p3 = new Punto2f(200.0, 70.0);
Punto2f * p4 = new Punto2f(60.0, 120.0);
Punto2f * p5 = new Punto2f(180.0, 150.0);
Punto2f * p6 = new Punto2f(100.0, 200.0);
Segmento * s1 = new Segmento(p1,p2);
Segmento * s2 = new Segmento(p2,p3);
Segmento * s3 = new Segmento(p4,p5);
Segmento * s4 = new Segmento(p5,p6);

DibujoLineas * dl = new DibujoLineas();
dl->inserta(s1);
dl->inserta(s2);
dl->inserta(s3);
dl->inserta(s4);
scene-> inserta (dl);  

    */

// comandos para dibujar la escena
if (scene !=NULL) {
   scene->Pinta();
   }

glFlush();
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

       delete pos_actual;
       delete scene;
       scene = NULL;
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::FormKeyDown(TObject *Sender, WORD &Key,
      TShiftState Shift)
{
    Lapiz * l= new Lapiz();
    l->gira(3.0);
    ShowMessage("");
    delete l;
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::Abrir1Click(TObject *Sender)
{
if (OpenDialog1 ->Execute()){
        char * path = OpenDialog1 -> FileName.c_str();
        ShowMessage (path);
        char * text = new char;
        ifstream filein;
        filein.open(path, ios::in);
        // se lee hasta el car�cter (;)
        filein.getline(text, 200);
        // se cierra el fichero
        filein.close();

//         delete text;  ?????
        }
else{
        ShowMessage("El usuario cancel� la operaci�n");
        }
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::Guardar1Click(TObject *Sender)
{
if (SaveDialog1 ->Execute()){
        char *  path = SaveDialog1 -> FileName.c_str();
        ShowMessage (path);


        // se crea un flujo de salida y se asocia con un fichero
        ofstream fileout;
        fileout.open(path, ios::out);
        // se escribe el mismo texto y el nuevo n�mero
        fileout << "aaaaaa" << "; " << endl;
        fileout.close();

        }
else{
        ShowMessage("El usuario cancel� la operaci�n");
        }
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::FormMouseDown(TObject *Sender,
      TMouseButton Button, TShiftState Shift, int x, int y)
{

switch(estado){
        case 1:   // Polil�nea
                  p = new Punto2f(x,y);
                  scene->transformarXY(p,ClientWidth,ClientHeight);

                  if ( pos_actual== NULL)
                     pos_actual = p->clon();
                  s= new Segmento(pos_actual, p);
                  dl->inserta(s);
                  pos_actual = p->clon();
                  /*
                  if (primerClic){ // A�adir punto inicial
                          s = new Segmento();
                          s->setInicio(p);
                          primerClic = false;
                  }
                  else { // A�adir punto final
                          s->setFinal(p);
                          dl->inserta(s);
                          s = new Segmento();
                          s->setInicio(p->clon());
                       }
                   */
                  break;
        }
        //ShowMessage(s->getInicio()->getX());
        Edit1->Text = dl->getDibujoLineas()->getLongitud();
        Edit2->Text = s->getInicio()->getX();
        Edit3->Text = s->getFinal()->getX();

        //dl->getDibujoLineas()->getActual()->Pinta();

        Lista<Segmento> * ls =dl->getDibujoLineas();
      ls-> inicia();
        Segmento * s1 = ls->getActual();
        s1->Pinta();
        scene->Pinta();

}

//---------------------------------------------------------------------------
// Si hemos dibujado algo preguntamos si queremos guardar y empezamos
// una nueva escena
void __fastcall TGLForm2D::Nuevo1Click(TObject *Sender) {

   if (!(scene->getEscena()->vacia())) {
      int ret = Application->MessageBox("� Desea guardar la escena actual ?",
                                        "Escena Actual", MB_YESNO);
      if (ret==6)
          GLForm2D->Guardar1Click(0);
   }

   delete scene;
   scene = new Escena(distancia);
}
//---------------------------------------------------------------------------
// Si hemos dibujado algo preguntamos si queremos guardar y salimos !!!!
void __fastcall TGLForm2D::Salir1Click(TObject *Sender)
{
   if (!(scene->getEscena()->vacia())) {
      int ret = Application->MessageBox("El archivo ha sido modificado\n\n�Desea guardar la escena?",
                                        "Advertencia", MB_YESNO);
      if (ret==6)
          GLForm2D->Guardar1Click(0);
   }

   Application->Terminate();
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::Lineas1Click(TObject *Sender)
{
 estado = 1;
 primerClic = true;
 dl = new DibujoLineas();
 scene -> inserta(dl);
}
//---------------------------------------------------------------------------

