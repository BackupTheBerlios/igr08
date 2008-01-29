//---------------------------------------------------------------------------

#ifndef resultadosH
#define resultadosH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
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

private:	// User declarations

        int * puntuaciones;
        int cont;

public:		// User declarations
        __fastcall TForm1(TComponent* Owner);
        void setPuntos(int p);
        void setVidas(int v);
        bool superaPuntuaciones(int);
};
//---------------------------------------------------------------------------
extern PACKAGE TForm1 *Form1;
//---------------------------------------------------------------------------
#endif
