//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop
#include <fstream.h>
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
        Puntos->Caption = p;
}

void TForm1::setVidas(int v){
        Vidas->Caption = v;
}


void __fastcall TForm1::FormCreate(TObject *Sender)
{
        char * linea = new char();
        char * cadena = new char();

        ifstream entrada;
        entrada.open("records.txt", ios::in);
        int i=0;

        if (entrada.is_open()) {

            entrada > linea;
            while (!entrada.eof()) {

                  entrada >> linea;
                  strcat(cadena,linea);
                  strcat(cadena,"\n\n");
            }

        Label2->SetTextBuf(cadena);
        }

        entrada.close();
}
//---------------------------------------------------------------------------

