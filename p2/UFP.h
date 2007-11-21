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

//---------------------------------------------------------------------------
class TGLForm2D : public TForm
{
__published:	// IDE-managed Components
        TSaveDialog *SaveDialog1;
        TOpenDialog *OpenDialog1;
        TMainMenu *MainMenu1;
        TMenuItem *Archivo1;
        TMenuItem *Abrir1;
        TMenuItem *Guardar1;
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

private:	// User declarations
 HDC hdc;
 HGLRC hrc;
 // Definen el tamaño del volumen de vista
 GLfloat xLeft,xRight,yTop,yBot;
 // Guarda el radio del puerto de vista
 GLfloat RatioViewPort;
 // Guarda un puntero a la escena
 Escena * scene;
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
