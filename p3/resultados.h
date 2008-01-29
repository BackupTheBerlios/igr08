//---------------------------------------------------------------------------

#ifndef resultadosH
#define resultadosH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <fstream.h>
#include <iostream>
using namespace std;
#include <Forms.hpp>
//---------------------------------------------------------------------------
class TForm1 : public TForm
{
__published:	// IDE-managed Components
        TLabel *LabelPuntos;
        TLabel *LabelVidas;
        TLabel *Puntos;
        TLabel *Vidas;
        TLabel *Label1;
        TLabel *Label2;
        void __fastcall FormCreate(TObject *Sender);
        void __fastcall FormDestroy(TObject *Sender);

private:	// User declarations

        int * puntuaciones;
        int cont;
        char * cadena;
        //fstream fichero;

public:		// User declarations
        __fastcall TForm1(TComponent* Owner);
        void setPuntos(int p);
        void setVidas(int v);
        bool superaPuntuaciones(int);
        void registrarPuntuacion(String, int);
};
//---------------------------------------------------------------------------
extern PACKAGE TForm1 *Form1;
//---------------------------------------------------------------------------
#endif
