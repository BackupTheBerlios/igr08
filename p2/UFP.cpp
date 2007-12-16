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
    scene = new Escena(distancia, this-> ClientWidth, this->ClientHeight);
    scene->setHDC(hdc);
    
////////////////// Lab 1 pto 01 /////////////////
    //scene->Pinta();
    //GLScene();
/////////////////////////////////////////////////



//////////////////////////////////

  /*      puntos[0] = new Punto2f(35.0,10.0); // Punto inicial
        puntos[1] = new Punto2f(95.0,10.0); // punto final
        puntos[2] = new Punto2f(65.0,20.0); // Punto otro
        //calculo Punto Medio A
        Punto2f *A = puntos[0]->puntoMedio(puntos[2]);
        //calculo Punto Medio B
        Punto2f *B = puntos[1]->puntoMedio(puntos[2]);

        Punto2f *BA, *Va, *Vap, *Vb, *Vbp, *P0P1, *P1P2; // son vectores

        Vap = *puntos[2] - *puntos[0];
        Va = Vap->perpendicular();
        Vbp = *puntos[1] - *puntos[2];
        Vb = Vbp->perpendicular();
        GLdouble k1, k2;
        BA = *A - *B;

        k2=BA->dot(Vap);
        GLdouble  aux = Vb->dot(Vap);
        k2=k2/aux;

        Punto2f * BmenosA = *B-*A;
        Punto2f * aux2 = Vb->multiplicar(k2);
        aux2= *BmenosA + *aux2;
        k1 = aux2->getX() / Va->getX();
        aux2 = Va->multiplicar(k1);
        Punto2f * centro = *A + *aux2; */
//////////////////////////////////
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
      glLineWidth(2);
      glPointSize(3);

// Comandos para dibujar la escena
if (scene !=NULL) {
   scene->Pinta();
   }

// Puntos provisionales del Arco
if (estado == 2 || estado == 5 || estado == 6)
   for (int i=0; i<cont; i++)
      if (puntos[i]!=NULL)
         puntos[i]->Pinta();

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

        for (int i=0; i<cont; i++){
                delete puntos[i];
                puntos[i]= NULL;
        }

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
        char * linea = new char();
        char * path = OpenDialog1 -> FileName.c_str();
        distancia = scene->getDistancia();
        delete scene;
        //distancia = scene->getDistancia();
        scene = new Escena(200, ClientWidth, ClientHeight);
        int i;
        AnsiString cad;
        AnsiString c1, c2;
        GLfloat x,y;
        //Segmento *s;
        //Punto2f *p1, *p2;

        ifstream filein;
        filein.open(path, ios::in);
        filein.getline(linea, 25);
        cad = linea;
        while (cad.SubString(1,1)!="="){
           dl = new DibujoLineas();
           while (cad.SubString(1,1)!="x"){
               // Obtenemos 1º Punto
               i = 1;
               c1 = "";
               c2 = "";
               while (cad.SubString(i,1)!=";") {
                 c1 += cad.SubString(i,1);
                 i++;
               }
               i++;
               while (cad.SubString(i,1)!=";") {
                 c2 += cad.SubString(i,1);
                 i++;
               }

               x = c1.ToInt();
               y = c2.ToInt();
               p1 = new Punto2f(x,y);

               // Obtenemos 2º punto
               filein.getline(linea, 25);
               cad = linea;
               i = 1;
               c1 = "";
               c2 = "";
               while (cad.SubString(i,1)!=";") {
                 c1 += cad.SubString(i,1);
                 i++;
               }
               i++;
               while (cad.SubString(i,1)!=";") {
                 c2 += cad.SubString(i,1);
                 i++;
               }

               x = c1.ToInt();
               y = c2.ToInt();
               p2 = new Punto2f(x,y);

               // Insertamos segmento en la lista
               s = new Segmento(p1,p2);
               dl->inserta(s);

               filein.getline(linea, 25);
               cad = linea;
           }
            scene->inserta(dl);
            filein.getline(linea, 25);
            cad = linea;
        }
        filein.close();
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

        // se crea un flujo de salida y se asocia con un fichero
        ofstream fileout;
        fileout.open(path, ios::out);
        String cadena = scene->toString2();
        char * ff=  cadena.c_str();
        // se escribe el mismo texto y el nuevo número
        fileout << ff << endl;
        fileout.close();
        }
else{
        ShowMessage("El usuario canceló la operación");
        }
}
//---------------------------------------------------------------------------
// Control del raton
void __fastcall TGLForm2D::FormMouseDown(TObject *Sender,
      TMouseButton Button, TShiftState Shift, int x, int y) {

Punto2f * p;
p=NULL;

switch (estado) {
        case 1: { // Polilíneas
                  p = new Punto2f(x,y);
                  scene->transformarXY(p);//,ClientWidth,ClientHeight);
                  if ( pos_actual== NULL)
                     pos_actual = p->clon();

                  s = new Segmento(pos_actual, p);
                  dl->inserta(s);
                  delete pos_actual;
                  delete s;

                  pos_actual = p->clon();

                  GLScene();

                  break;
                }
        case 2: { // Arcos
                  if (cont < 3) {
                    puntos[cont] = new Punto2f(x,y);
                    scene->transformarXY(puntos[cont]);
                    GLScene();
                    cont++;
                  }

                  if (cont==3) {
                        AnsiString dato = 200;
                        InputQuery("Solicitando datos",
                                "Numero de iteraciones",
                                dato);
                        nPasos = StrToInt(dato);

                        Lapiz * lap = new Lapiz();
                        lap->arco(puntos[0], puntos[1], puntos[2], nPasos, dl);
                        scene->inserta(dl);
                        delete lap;
                        for (int i=0; i<cont; i++){
                                delete puntos[i];
                                puntos[i]= NULL;
                        }
                        estado = 0;
                        cont = 0;

                        
                     /*   puntos[0] = new Punto2f(35.0,10.0); // Punto inicial
                        puntos[1] = new Punto2f(95.0,10.0); // punto final
                        puntos[2] = new Punto2f(65.0,20.0); // Punto otro
                        //calculo Punto Medio A
                        Punto2f *A = puntos[0]->puntoMedio(puntos[2]);
                        //calculo Punto Medio B
                        Punto2f *B = puntos[1]->puntoMedio(puntos[2]);

                        Punto2f *BA, *Va, *Vap, *Vb, *Vbp, *P0P1, *P1P2; // son vectores

                        Vap = *puntos[2] - *puntos[0];
                        Va = Vap->perpendicular();
                        Vbp = *puntos[1] - *puntos[2];
                        Vb = Vbp->perpendicular();
                        GLdouble k1, k2;
                        BA = *A - *B;

                        k2=BA->dot(Vap);
                        GLdouble  aux = Vb->dot(Vap);
                        k2=k2/aux;

                        Punto2f * BmenosA = *B-*A;
                        Punto2f * aux2 = Vb->multiplicar(k2);
                        aux2= *BmenosA + *aux2;
                        k1 = aux2->getX() / Va->getX();
                        aux2 = Va->multiplicar(k1);
                        Punto2f * centro = *A + *aux2;


                    delete A; //???
                    delete B; //???
                    */
                  }

                  // Si cont = 3 dibujamos arco
                  // e inicializamos cont a 0.
                  // en otro caso cont++

                  break;
                }
        case 3: { // Espirales
                  GLfloat ang = 0.0;  // Angulo inicial 0.0

                  dl = new DibujoLineas();
                  scene -> inserta(dl);

                  p = new Punto2f(x,y);
                  scene->transformarXY(p);//,ClientWidth,ClientHeight);


                  Lapiz * l = new Lapiz (p, ang);
                  l->poliEspiral(p,incrAng,ang,incrLong,longInicial,nPasos,dl);
                  delete l;
                  p=NULL;

                  GLScene();

                  break;
                }
        case 4: { // Seleccionar
                  p = new Punto2f(x,y);
                  scene->transformarXY(p);//,ClientWidth,ClientHeight);

                  scene->Seleccionar(p);

                  GLScene();

                  break;
                }

        case 5: { // Curva Bezier
                  if (cont < total_puntos) {
                      puntos[cont] = new Punto2f(x,y);
                      scene->transformarXY(puntos[cont]);//,ClientWidth,ClientHeight);

                      GLScene();

                      cont++;
                    }

                  if (cont == total_puntos) {
                    Lapiz * l = new Lapiz();
                    l->Bezier(puntos,total_puntos, nPasos,dl);
                    delete l;
                    estado = 0;
                    p=NULL;
                  }

                  GLScene();

                  break;
                 }

        case 6: { // Curva B-Splines
                  if (cont < total_puntos) {
                      puntos[cont] = new Punto2f(x,y);
                      scene->transformarXY(puntos[cont]);//,ClientWidth,ClientHeight);

                      GLScene();

                      cont++;
                    }

                  if (cont == total_puntos) {
                    Lapiz * l = new Lapiz();
                    l->B_Splines(puntos,total_puntos,nPasos,dl);
                    delete l;
                    estado = 0;
                    p=NULL;
                  }

                  GLScene();

                  break;
                 }
        case 7:
        p1 = new Punto2f(x,y);
        scene->transformarXY(p1);//,ClientWidth, ClientHeight);
        break;
                }


        if (p != NULL) {
           delete p;
           p=NULL;
        }

}

//---------------------------------------------------------------------------
// Si hemos dibujado algo preguntamos si queremos guardar y empezamos
// una nueva escena
void __fastcall TGLForm2D::Nuevo1Click(TObject *Sender) {
  // bool ok;
   int ret;
   if (!(scene->getEscena()->vacia())) {
      ret = Application->MessageBox("¿ Desea guardar la escena actual ?",
                                        "Escena Actual", MB_YESNO);
      if (ret==IDYES)
          //ok =
          GLForm2D->Guardar1Click(this);
   }

   if (/*ok |*/ret==IDNO){
   delete scene;
   scene = new Escena(distancia, this->ClientWidth, this->ClientHeight);
   GLScene();
   estado = 0;
   }
}
//---------------------------------------------------------------------------
// Si hemos dibujado algo preguntamos si queremos guardar y salimos !!!!
void __fastcall TGLForm2D::Salir1Click(TObject *Sender)
{
//   bool ok;
   if (!(scene->getEscena()->vacia())) {
      int ret = Application->MessageBox("El archivo ha sido modificado\n\n¿Desea guardar la escena?",
                                        "Advertencia", MB_YESNO);
      if (ret==IDYES)
         //ok =
         GLForm2D->Guardar1Click(this);
   }
        Application->Terminate();
/*   if (ok)   {

   }*/
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


 total_puntos = 3;
 cont = 0;

 for (int i=0; i<total_puntos; i++)
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
                 if (!scene->ZoomProgresivo(StrToFloat(S_porcentaje), StrToInt(S_iteraciones))){
                ShowMessage("El factor debe ser un número mayor que 0"); 
                 }
        }
 }
}
//---------------------------------------------------------------------------
// Selecciona un dibujo de lineas
void __fastcall TGLForm2D::Seleccionar1Click(TObject *Sender)
{
 estado = 4;
}
//---------------------------------------------------------------------------
// Borrado dibujo de lineas seleccionado
void __fastcall TGLForm2D::Borrar1Click(TObject *Sender)
{
 scene->BorrarSeleccionado();
 GLScene();
}
//---------------------------------------------------------------------------
// Dibujado de una curva Bezier
void __fastcall TGLForm2D::Bezier1Click(TObject *Sender)
{
 AnsiString Num_Puntos="4";
 AnsiString Num_Lados="25";
 if (InputQuery("Solicitando datos","Numero de puntos:",Num_Puntos)) {
    total_puntos = StrToInt(Num_Puntos);
    if (InputQuery("Solicitando datos","Numero de Lados:", Num_Lados)) {
       estado = 5;
       cont = 0;
       nPasos = StrToInt(Num_Lados);
       dl = new DibujoLineas();
       scene -> inserta(dl);

       for (int i=0; i<total_puntos; i++)
         puntos[i] = NULL;
    }
  }
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::Recorte1Click(TObject *Sender)
{
estado = 7;
}
//---------------------------------------------------------------------------




void __fastcall TGLForm2D::FormMouseUp(TObject *Sender,
      TMouseButton Button, TShiftState Shift, int X, int Y)
{
        if ( estado ==7){
                p2 = new Punto2f(X,Y);
                scene->transformarXY(p2);
                String str1 = scene->toString();
                scene->recorte(p1,p2);
                String str2 = scene->toString();
                scene->Pinta();
                estado=0;
                delete p1;
                delete p2;
                p1 = NULL;
                p2 = NULL;
                }
/////////////////////// Lab 1 pto 1 /////////////////////////
                //    scene->Pinta();
/////////////////////////////////////////////////////////////

}
//---------------------------------------------------------------------------



void __fastcall TGLForm2D::FormMouseWheelDown(TObject *Sender,
      TShiftState Shift, TPoint &MousePos, bool &Handled)
{
        scene->Zoom(95);
        scene->Pinta();
        GLScene();
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::FormMouseWheelUp(TObject *Sender,
      TShiftState Shift, TPoint &MousePos, bool &Handled)
{
        scene->Zoom(105);
        scene->Pinta();
        GLScene();
}
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::CurvaBSplines1Click(TObject *Sender)
{
 AnsiString Num_Puntos = "10";
 AnsiString Num_Lados = "30";
 AnsiString Grado_Suavidad = "3";

 if (InputQuery("Solicitando datos","Numero de puntos:",Num_Puntos)) {
    total_puntos = StrToInt(Num_Puntos);
    if (InputQuery("Solicitando datos","Numero de Lados:", Num_Lados)) {
       nPasos = StrToInt(Num_Lados);
       if (InputQuery("Solicitando datos","Grado de Suavidad:", Grado_Suavidad)) {
       estado = 6;
       cont = 0;
       grado = StrToInt(Grado_Suavidad);
       dl = new DibujoLineas();
       scene -> inserta(dl);

       for (int i=0; i<total_puntos; i++)
         puntos[i] = NULL;
       }
   }
 }
}
//---------------------------------------------------------------------------


void __fastcall TGLForm2D::FormMouseMove(TObject *Sender,
      TShiftState Shift, int X, int Y)
{

  if (estado == 7){
        if (p1!=NULL){
        p2 = new Punto2f(X,Y);
        glClear(GL_COLOR_BUFFER_BIT);
        scene->Pinta();
        scene->transformarXY(p2);//,ClientWidth, ClientHeight);
        glEnable(GL_LINE_STIPPLE);
        glLineStipple(1, 0xCCCC);
        glColor3f(1.0, 1.0, 0.0);
        glBegin(GL_LINE_LOOP);
                glVertex2f(p1->getX(),p1->getY());
                glVertex2f(p2->getX(),p1->getY());
                glVertex2f(p2->getX(),p2->getY());
                glVertex2f(p1->getX(),p2->getY());
        glEnd();

        glColor3f(0.0, 1.0, 1.0);
        glBegin(GL_POINTS);
                glVertex2f(p1->getX(),p1->getY());
                glVertex2f(p2->getX(),p2->getY());
        glEnd();

        glFlush();
        SwapBuffers(hdc);

        glDisable(GL_LINE_STIPPLE);
        glColor3f(1.0, 1.0, 1.0);
        delete p2;
        }
  }
}
//---------------------------------------------------------------------------
/*
void __fastcall TGLForm2D::Poligono1Click(TObject *Sender)
{
DibujoLineas * dib = new DibujoLineas();
Punto2f * pi = new Punto2f(10.0,20.0);
Lapiz * lap = new Lapiz(pi, 0);
lap->poligonoR1(90,8,dib);
scene->inserta(dib);
delete lap;
}
*/
//---------------------------------------------------------------------------

void __fastcall TGLForm2D::lapiz1Click(TObject *Sender)
{

/*DibujoLineas * dib = new DibujoLineas();
Punto2f * pi = new Punto2f(0.0,0.0);
Lapiz * lap = new Lapiz(pi, 0);
lap->poligonoR1(90,4,dib);
scene->inserta(dib);
delete lap;


dib = new DibujoLineas();
pi = new Punto2f(0.0,0.0);
lap = new Lapiz(pi, 0);
pi = new Punto2f(0.0,0.0);
lap->poligonoR2(pi, 80,5,dib);
scene->inserta(dib);
delete lap;
delete pi;*/

/*
DibujoLineas * dib = new DibujoLineas();
Punto2f * centro = new Punto2f(0.0,0.0);
Punto2f * inicio = new Punto2f(0.0,0.0);
Punto2f * final = new Punto2f(100.0,0.0);
Punto2f * otro = new Punto2f(50.0,20.0);
Lapiz * lap = new Lapiz(inicio, 0);
inicio = new Punto2f(0.0,0.0);
//lap->arcoR2 (centro, 60, 20, 160, 380,dib);
lap->arco(inicio, final, otro, 2, dib);
scene->inserta(dib);
delete lap;   */
Punto2f * centro = new Punto2f(0.0,0.0);
Punto2f * inicio = new Punto2f(30.0,40.0);
ShowMessage (centro->angulo2(inicio));
delete inicio; delete centro;

 centro = new Punto2f(0.0,0.0);
 inicio = new Punto2f(-30.0,40.0);
ShowMessage (centro->angulo2(inicio));
delete inicio; delete centro;

 centro = new Punto2f(0.0,0.0);
inicio = new Punto2f(30.0,-40.0);
ShowMessage (centro->angulo2(inicio));
delete inicio; delete centro;

 centro = new Punto2f(0.0,0.0);
inicio = new Punto2f(-30.0,-40.0);
ShowMessage (centro->angulo2(inicio));
delete inicio; delete centro;
}
//---------------------------------------------------------------------------


