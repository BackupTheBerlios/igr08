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

    int maximo = -1000;

    if (cont > 1) {
      for (int j=0; j<cont-1; j++) {
         if (puntuaciones[j] >= maximo)
            maximo = puntuaciones[j];
      }
    }
    else maximo = puntuaciones[0];

    ShowMessage(maximo);

    if (puntos > maximo)
          return true;

    return false;

}

void TForm1::registrarPuntuacion(String nombre, int puntitos) {

     //fstream salida;
     salida.open("records.txt",ios::app);
     //strcat(cadena,nombre);
     //strcat(cadena," ");

     //fichero << cadena << endl;
     salida << puntitos << " " << puntitos << endl;

     salida.close();

}

void __fastcall TForm1::FormCreate(TObject *Sender)
{
        char * linea = new char();
        cadena = new char();

        ifstream fichero;
        fichero.open("records.txt", ios::in);
        int i=0;

        puntuaciones = new int[100];
        cont = 0;

        if (fichero.is_open()) {

            fichero > linea;
            while (!fichero.eof()) {

                fichero >> linea;
                strcat(cadena,linea);
                if (i%2==1)
                  strcat(cadena,"\n");
                else
                  strcat(cadena," ");

                if (i%2==1) {
                    puntuaciones[cont]=StrToInt(linea);
                    cont++;
                }

                i++;
            }

        Label2->SetTextBuf(cadena);
        }

        fichero.close();
}
//---------------------------------------------------------------------------

void __fastcall TForm1::FormDestroy(TObject *Sender)
{
 //fichero.close();
}
//---------------------------------------------------------------------------

