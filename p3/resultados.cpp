//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "resultados.h"
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma resource "*.dfm"
TForm1 *Form1;
//---------------------------------------------------------------------------
__fastcall TForm1::TForm1(TComponent* Owner)
        : TForm(Owner)
{
}
//---------------------------------------------------------------------------
void TForm1::setPuntos(int p){
        Puntos->Text = p;
}

void TForm1::setVidas(int v){
        Vidas->Text = v;
}
