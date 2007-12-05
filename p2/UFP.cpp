//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "UFP.h"
#include <fstream.h>
#pragma package(smart_init)
#pragma resource "*.dfm"
//--------0-------------------------------------------------------------------
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
    scene = new Escena(distancia, this-> ClientWidth, this->ClientHeight);
    scene->setHDC(hdc);
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

  // Llamamos a la función Resize de la Escena
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

glColor3f(1.0,1.0,1.0);
glLineWidth(2);
glPointSize(3);

// Comandos para dibujar la escena
if (scene !=NULL) {
   scene->Pinta();
   }

// Puntos provisionales del Arco
if (estado == 2)
   for (int i=0; i<=cont; i++)
      if (puntos[i]!=NULL)
         puntos[i]->Pinta();
/*
Lapiz * l = new Lapiz();
l->poliEspiral(new Punto2f(10,10),90,0,25,10,25);
delete l; */

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
    scene->teclado(Key);
    GLScene();
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
        // se lee hasta el carácter (;)
        filein.getline(text, 200);
        // se cierra el fichero
        filein.close();

//         delete text;  ?????
        }
else{
        ShowMessage("El usuario canceló la operación");
        }
}
//---------------------------------------------------------------------------
// Guardar la escena actual
void __fastcall TGLForm2D::Guardar1Click(TObject *Sender)
{
if (SaveDialog1 ->Execute()){
        char *  path = SaveDialog1 -> FileName.c_str();
        ShowMessage (path);


        // se crea un flujo de salida y se asocia con un fichero
        ofstream fileout;
        fileout.open(path, ios::out);
        // se escribe el mismo texto y el nuevo número
        fileout << "aaaaaa" << "; " << endl;
        fileout.close();

        }
else{
        ShowMessage("El usuario canceló la operación");
        }
}
//---------------------------------------------------------------------------
// Control del raton
void __fastcall TGLForm2D::FormMouseDown(TObject *Sender,
      TMouseButton Button, TShiftState Shift, int x, int y)
{

Punto2f * p;
p=NULL;
switch(estado){
        case 1:   // Polilíneas
                  p = new Punto2f(x,y);
                  scene->transformarXY(p,ClientWidth,ClientHeight);
                  if ( pos_actual== NULL){
                     pos_actual = p->clon();
                     }
                  s = new Segmento(pos_actual, p);
                  dl->inserta(s);
                  delete pos_actual;
                  delete s;
                  pos_actual = p->clon();
                  GLScene();
                  break;

        case 2:  // Arcos
                if (cont < 3) {

                    puntos[cont] = new Punto2f(x,y);
                    scene->transformarXY(puntos[cont],ClientWidth,ClientHeight);

                    GLScene();

                    cont++;
                }

                if (cont==3) {
                   AnsiString dato = 10;
                   InputQuery("Solicitando datos",
                              "Numero de iteraciones",
                              dato);
                   nPasos = StrToInt(dato);
                }

                 // Si cont = 3 dibujamos arco
                 // e inicializamos cont a 0.
                 // en otro caso cont++

                 break;

        case 3: // Espirales

            
                GLfloat ang = 0.0;  // Angulo inicial 0.0

                p = new Punto2f(x,y);
                scene->transformarXY(p,ClientWidth,ClientHeight);


                Lapiz * l = new Lapiz();
                l->poliEspiral(p,incrAng,ang,incrLong,longInicial,nPasos,dl);
                delete l;
                p=NULL;
                GLScene();

                break;
        }

        if (p != NULL)
           {
           delete p;
           p=NULL;
           }



}

//---------------------------------------------------------------------------
// Si hemos dibujado algo preguntamos si queremos guardar y empezamos
// una nueva escena
void __fastcall TGLForm2D::Nuevo1Click(TObject *Sender) {

   if (!(scene->getEscena()->vacia())) {
      int ret = Application->MessageBox("¿ Desea guardar la escena actual ?",
                                        "Escena Actual", MB_YESNO);
      if (ret==6)
          GLForm2D->Guardar1Click(0);
   }

   delete scene;
   scene = new Escena(distancia, this->ClientWidth, this->ClientHeight);
   GLScene();
}
//---------------------------------------------------------------------------
// Si hemos dibujado algo preguntamos si queremos guardar y salimos !!!!
void __fastcall TGLForm2D::Salir1Click(TObject *Sender)
{
   if (!(scene->getEscena()->vacia())) {
      int ret = Application->MessageBox("El archivo ha sido modificado\n\n¿Desea guardar la escena?",
                                        "Advertencia", MB_YESNO);
      if (ret==6)
          GLForm2D->Guardar1Click(0);
   }

   Application->Terminate();
}
//---------------------------------------------------------------------------
// Dibujamos Polilíneas
void __fastcall TGLForm2D::Lineas1Click(TObject *Sender)
{
 estado = 1;
 pos_actual = NULL;
 dl = new DibujoLineas();
 scene -> inserta(dl);
}
//---------------------------------------------------------------------------
// Dibujamos Arcos
void __fastcall TGLForm2D::Arcos1Click(TObject *Sender)
{
 estado = 2;
 dl = new DibujoLineas();
 scene -> inserta(dl);

 cont = 0;

 for (int i=0; i<3; i++)
    puntos[i] = NULL;

 Application->MessageBox("Señala 3 puntos: Inicial, Final y otro",
                         "Dibujo del arco", MB_OK);

}
//---------------------------------------------------------------------------
// Dibujamos espirales
 void __fastcall TGLForm2D::Espirales1Click(TObject *Sender)
{

 AnsiString dato = "10";

if(InputQuery("Solicitando datos","Numero de iteraciones:",dato))
{
    nPasos = StrToInt(dato);
    if(InputQuery("Solicitando datos","Longitud inicial del lado:",dato))
    {
    longInicial = StrToInt(dato);

    if (InputQuery("Solicitando datos","Incremento del lado:",dato))
        {
        incrLong = StrToInt(dato);

        if (InputQuery("Solicitando datos","AnguloGiro:",dato))
            {
            incrAng = StrToInt(dato);
            Application->MessageBox("Elige el centro","Espiral",MB_OK);
             estado = 3;
             dl = new DibujoLineas();
             scene -> inserta(dl);
            }
        }
    }
 }

}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::Zoom1Click(TObject *Sender)
{
AnsiString S_porcentaje="100";
if (InputQuery("Solicitando datos","Porcentaje:", S_porcentaje))
        {
        scene->Zoom(StrToFloat(S_porcentaje));
        }
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::ZoomProgresivo1Click(TObject *Sender)
{
AnsiString S_porcentaje="100";
AnsiString S_iteraciones="3";
if (InputQuery("Solicitando datos","Porcentaje:",S_porcentaje))
 {      if (InputQuery("Solicitando datos","Numero de iteraciones:", S_iteraciones))
        {
                 scene->ZoomProgresivo(StrToFloat(S_porcentaje), StrToInt(S_iteraciones));
        }
 }
}
//---------------------------------------------------------------------------




