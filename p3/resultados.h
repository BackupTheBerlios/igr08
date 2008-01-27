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
private:	// User declarations
public:		// User declarations
        __fastcall TForm1(TComponent* Owner);
        void setPuntos(int p);
        void setVidas(int v);
};
//---------------------------------------------------------------------------
extern PACKAGE TForm1 *Form1;
//---------------------------------------------------------------------------
#endif
