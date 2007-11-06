//---------------------------------------------------------------------------
#ifndef UFPH
#define UFPH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include <Menus.hpp>
#include "CGRID.h"
#include <ExtCtrls.hpp>

#include <gl\gl.h>
#include <gl\glu.h>

#include "Punto.h"
//---------------------------------------------------------------------------
class TGLForm2D : public TForm
{
__published:	// IDE-managed Components
        TMainMenu *MainMenu1;
        TMenuItem *Menu1;
        TMenuItem *Comandos1;
        TMenuItem *Salir1;
        TMenuItem *Creditos1;
        TMenuItem *Comandos2;
        TMenuItem *Autores1;
        TCColorGrid *CColorGrid1;
        TMenuItem *GrosorPunto1;
        TPanel *Panel1;
        TScrollBar *ScrollBar1;
        TLabel *Label1;
        TLabel *Label3;
        TLabel *Label4;
        TLabel *Label5;
        TScrollBar *ScrollBar2;
        TScrollBar *ScrollBar3;
        TScrollBar *ScrollBar4;
        TPanel *Panel2;
        TRadioButton *RadioButton2;
        TRadioButton *RadioButton1;
        TRadioButton *RadioButton3;
        TRadioButton *RadioButton4;
        TRadioButton *RadioButton5;
        TRadioButton *RadioButton6;
  void __fastcall FormCreate(TObject *Sender);
  void __fastcall FormResize(TObject *Sender);
  void __fastcall FormPaint(TObject *Sender);
  void __fastcall FormDestroy(TObject *Sender);
  void __fastcall FormKeyPress(TObject *Sender, char &Key);
        void __fastcall FormKeyDown(TObject *Sender, WORD &Key,
          TShiftState Shift);
        void __fastcall Comandos1Click(TObject *Sender);
        void __fastcall Comandos2Click(TObject *Sender);
        void __fastcall Autores1Click(TObject *Sender);
        void __fastcall Salir1Click(TObject *Sender);
        void __fastcall CColorGrid1Change(TObject *Sender);
        void __fastcall GrosorPunto1Click(TObject *Sender);
        void __fastcall ScrollBar1Change(TObject *Sender);
        void __fastcall ScrollBar2Change(TObject *Sender);
        void __fastcall ScrollBar3Change(TObject *Sender);
        void __fastcall ScrollBar4Change(TObject *Sender);
        void __fastcall RadioButton2KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton1KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton3KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton4KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton5KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton6KeyPress(TObject *Sender, char &Key);

private:	// User declarations
 HDC hdc;
 HGLRC hrc;

 // Ddefinen el tama�o del volumen de vista
 GLfloat xLeft,xRight,yTop,yBot;

 // Guarda el radio del puerto de vista
 GLfloat RatioViewPort;

 // Punto central y limite
 Punto P, Q;

 // Puntos del rectangulo
 Punto A, B, C, D;

 // Punto auxiliar R
 Punto R;

 // Distancia de los vertices con respecto al punto central P
 GLint longX, longY;

 // Coordenadas del punto limte
 GLint Qx,Qy;

 // Contador de rectangulos
 GLint cont;

 // Opci�n de centrado
 GLint centrado;

 // Grosor del Punto origen P
 GLint grosorP;

  // Grosor del Punto l�mite Q
 GLint grosorQ;

 // Grosor de l�neas de rect�ngulos
 GLint grosorLinRect;

  // Grosor de l�neas de diagonales
 GLint grosorLinDiag;

 // Color del punto de Origen
 GLfloat colorP[3];

 // Color del punto de L�mite
 GLfloat colorQ[3];

 // Color de l�neas de rect�ngulo inicial
 GLfloat colorLinRect[3];

 // Color de l�neas de diagonales
 GLfloat colorLinDiag[3];

 // Color de l�neas actual
 GLfloat colorLinAct[50][3];

 // m�todos privados
 void __fastcall SetPixelFormatDescriptor();
 void __fastcall GLScene();

 public:		// User declarations
   __fastcall TGLForm2D(TComponent* Owner);



};
//---------------------------------------------------------------------------
extern PACKAGE TGLForm2D *GLForm2D;
//---------------------------------------------------------------------------
#endif
