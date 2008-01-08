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
//---------------------------------------------------------------------------
class TGLForm2D : public TForm  {

__published:	// IDE-managed Components
    TTimer *Timer1;
        void __fastcall FormCreate(TObject *Sender);
        void __fastcall FormResize(TObject *Sender);
        void __fastcall FormPaint(TObject *Sender);
        void __fastcall FormDestroy(TObject *Sender);
        void __fastcall FormKeyDown(TObject *Sender, WORD &Key,
          TShiftState Shift);

private:	// User declarations
 HDC hdc;
 HGLRC hrc;
 // Definen el tamaño del volumen de vista
 GLfloat xLeft,xRight,yTop,yBot;
 // Guarda el radio del puerto de vista
 GLfloat RatioViewPort;

 // Distancia equidistante del punto central
 int distancia;

 // Estado
 int estado;

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

