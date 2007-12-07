//---------------------------------------------------------------------------
#ifndef UFPH
#define UFPH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include <Dialogs.hpp>
#include <Menus.hpp>

#include <gl\gl.h>
#include <gl\glu.h>

#include "Escena.h"
#include "Lapiz.h"
#include "Punto2f.h"
#include "Segmento.h"

//---------------------------------------------------------------------------
const int MAX = 100;

class TGLForm2D : public TForm
{
__published:	// IDE-managed Components
        TSaveDialog *SaveDialog1;
        TOpenDialog *OpenDialog1;
        TMainMenu *MainMenu1;
        TMenuItem *Archivo1;
        TMenuItem *Abrir1;
        TMenuItem *Guardar1;
        TMenuItem *Nuevo1;
        TMenuItem *Salir1;
        TMenuItem *Dibujar1;
        TMenuItem *Lineas1;
        TMenuItem *Arcos1;
        TMenuItem *Espirales1;
        TMenuItem *Escena1;
        TMenuItem *Zoom1;
        TMenuItem *ZoomProgresivo1;
        TMenuItem *Editar1;
        TMenuItem *Seleccionar1;
        TMenuItem *Borrar1;
        TMenuItem *Bezier1;
        TMenuItem *Recorte1;
        TMenuItem *CurvaBSplines1;
        void __fastcall FormCreate(TObject *Sender);
        void __fastcall FormResize(TObject *Sender);
        void __fastcall FormPaint(TObject *Sender);
        void __fastcall FormDestroy(TObject *Sender);
        void __fastcall FormKeyDown(TObject *Sender, WORD &Key,
          TShiftState Shift);
        void __fastcall Abrir1Click(TObject *Sender);
        void __fastcall Guardar1Click(TObject *Sender);
        void __fastcall FormMouseDown(TObject *Sender, TMouseButton Button,
          TShiftState Shift, int X, int Y);
        void __fastcall Nuevo1Click(TObject *Sender);
        void __fastcall Salir1Click(TObject *Sender);
        void __fastcall Lineas1Click(TObject *Sender);
        void __fastcall Arcos1Click(TObject *Sender);
        void __fastcall Espirales1Click(TObject *Sender);
        void __fastcall Zoom1Click(TObject *Sender);
        void __fastcall ZoomProgresivo1Click(TObject *Sender);
        void __fastcall Seleccionar1Click(TObject *Sender);
        void __fastcall Borrar1Click(TObject *Sender);
        void __fastcall Bezier1Click(TObject *Sender);
        void __fastcall Recorte1Click(TObject *Sender);
        void __fastcall FormMouseWheelDown(TObject *Sender,
          TShiftState Shift, TPoint &MousePos, bool &Handled);
        void __fastcall FormMouseWheelUp(TObject *Sender,
          TShiftState Shift, TPoint &MousePos, bool &Handled);
        void __fastcall CurvaBSplines1Click(TObject *Sender);
        void __fastcall FormMouseUp(TObject *Sender, TMouseButton Button,
          TShiftState Shift, int X, int Y);
        void __fastcall FormMouseMove(TObject *Sender, TShiftState Shift,
          int X, int Y);

private:	// User declarations
 HDC hdc;
 HGLRC hrc;
 // Definen el tamaño del volumen de vista
 GLfloat xLeft,xRight,yTop,yBot;
 // Guarda el radio del puerto de vista
 GLfloat RatioViewPort;
 // Guarda un puntero a la escena
 Escena * scene;
 // Distancia equidistante del punto central
 int distancia;
 // Posicion Actual
 Punto2f * pos_actual;
 // Estado
 int estado;
 // Segmento y dibujo de lineas auxiliares
 Segmento * s;
 DibujoLineas * dl;
 Punto2f * p1,* p2; // Puntos para el recorte


 // Variables necesarios
 Punto2f* puntos[MAX];
 int cont;
 int total_puntos;
 int nPasos;
 int grado;

 // Variables necesarias para la Espiral
 GLfloat longInicial;
 GLfloat incrLong;
 GLfloat incrAng;

 // Métodos privados
 void __fastcall SetPixelFormatDescriptor();
 void __fastcall GLScene();

 public:		// User declarations
   __fastcall TGLForm2D(TComponent* Owner);
};
//---------------------------------------------------------------------------
extern PACKAGE TGLForm2D *GLForm2D;
//---------------------------------------------------------------------------
#endif
