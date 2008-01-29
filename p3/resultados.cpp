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

bool TForm1::superaPuntuaciones(int puntos){
 /*
     char * lineaInt = new char();
     puntuaciones = new int[100];
     cont = 0;

     ifstream ent;
     ent.open("records.txt", ios::in);
     int i=0;

     if (ent.is_open()) {

        ent > lineaInt;
        while (!ent.eof()) {

           ent >> lineaInt;
           if (i%2==1 && i>1) {
              puntuaciones[cont]=StrToInt(lineaInt);
              cont++;
           }
           i++;
       }
    }
    ent.close();

                  */

    int maximo;

    if (cont > 1) {
      for (int j=1; j<cont; j++) {
         if (puntuaciones[j] >= puntuaciones[j-1])
            maximo = puntuaciones[j];
         else
            maximo = puntuaciones[j-1];
      }
    }
    else maximo = puntuaciones[0];

    if (puntos > maximo)
          return true;

    return false;

}


void __fastcall TForm1::FormCreate(TObject *Sender)
{
        char * linea = new char();
        char * cadena = new char();

        ifstream entrada;
        entrada.open("records.txt", ios::in);
        int i=0;

        puntuaciones = new int[100];
        cont = 0;

        if (entrada.is_open()) {

            entrada > linea;
            while (!entrada.eof()) {

                entrada >> linea;
                strcat(cadena,linea);
                if (i%2==1)
                  strcat(cadena,"\n");
                else
                  strcat(cadena," ");

                if (i%2==1 && i>1) {
                    puntuaciones[cont]=StrToInt(linea);
                    cont++;
                }

                i++;
            }

        Label2->SetTextBuf(cadena);
        }

        entrada.close();
}
//---------------------------------------------------------------------------

