#ifndef UFPH
#define UFPH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include <Dialogs.hpp>
#include <Menus.hpp>
#include <ExtCtrls.hpp>

#include <gl\gl.h>
#include <gl\glu.h>
#include "Escena.h"
//---------------------------------------------------------------------------
class TGLForm2D : public TForm  {

__published:	// IDE-managed Components
    TTimer *Timer1;
        TMainMenu *MainMenu1;
        TMenuItem *Nuevo1;
        TMenuItem *Iniciar1;
        TMenuItem *Detalles1;
        TMenuItem *Convexo1;
        TMenuItem *Circulos1;
        TMenuItem *Salir1;
        TMenuItem *Nueva1;
        void __fastcall FormCreate(TObject *Sender);
        void __fastcall FormResize(TObject *Sender);
        void __fastcall FormPaint(TObject *Sender);
        void __fastcall FormDestroy(TObject *Sender);
        void __fastcall FormKeyDown(TObject *Sender, WORD &Key,
          TShiftState Shift);
        void __fastcall Nueva1Click(TObject *Sender);

private:	// User declarations
 HDC hdc;
 HGLRC hrc;
 // Definen el tamaño del volumen de vista
 GLfloat xLeft,xRight,yTop,yBot;
 // Guarda el radio del puerto de vista
 GLfloat RatioViewPort;

 // Estado
 bool estado;

 // Escena
 Escena* scene;

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

