//---------------------------------------------------------------------------
#ifndef UFPH
#define UFPH
//---------------------------------------------------------------------------
#include <Classes.hpp>
#include <Controls.hpp>
#include <StdCtrls.hpp>
#include <Forms.hpp>
#include <Menus.hpp>
#include "CGRID.h"
#include <ExtCtrls.hpp>

#include <gl\gl.h>
#include <gl\glu.h>

#include "Punto.h"
//---------------------------------------------------------------------------
class TGLForm2D : public TForm {

  __published:  // COMPONENTES DE INTERFACE //
        TMainMenu *MainMenu1;
        TMenuItem *Menu1;
        TMenuItem *Comandos1;
        TMenuItem *Salir1;
        TMenuItem *Creditos1;
        TMenuItem *Comandos2;
        TMenuItem *Autores1;
        TCColorGrid *CColorGrid1;
        TMenuItem *GrosorPunto1;
        TPanel *Panel1;
        TScrollBar *ScrollBar1;
        TLabel *Label1;
        TLabel *Label3;
        TLabel *Label4;
        TLabel *Label5;
        TScrollBar *ScrollBar2;
        TScrollBar *ScrollBar3;
        TScrollBar *ScrollBar4;
        TPanel *Panel2;
        TRadioButton *RadioButton2;
        TRadioButton *RadioButton1;
        TRadioButton *RadioButton3;
        TRadioButton *RadioButton4;
        TRadioButton *RadioButton5;
        TRadioButton *RadioButton6;
        TMenuItem *Embaldosar1;
        void __fastcall FormCreate(TObject *Sender);
        void __fastcall FormResize(TObject *Sender);
        void __fastcall FormPaint(TObject *Sender);
        void __fastcall FormDestroy(TObject *Sender);
        void __fastcall FormKeyPress(TObject *Sender, char &Key);
        void __fastcall FormKeyDown(TObject *Sender, WORD &Key,TShiftState Shift);
        void __fastcall Comandos1Click(TObject *Sender);
        void __fastcall Comandos2Click(TObject *Sender);
        void __fastcall Autores1Click(TObject *Sender);
        void __fastcall Salir1Click(TObject *Sender);
        void __fastcall CColorGrid1Change(TObject *Sender);
        void __fastcall GrosorPunto1Click(TObject *Sender);
        void __fastcall ScrollBar1Change(TObject *Sender);
        void __fastcall ScrollBar2Change(TObject *Sender);
        void __fastcall ScrollBar3Change(TObject *Sender);
        void __fastcall ScrollBar4Change(TObject *Sender);
        void __fastcall RadioButton2KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton1KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton3KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton4KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton5KeyPress(TObject *Sender, char &Key);
        void __fastcall RadioButton6KeyPress(TObject *Sender, char &Key);
        void __fastcall Embaldosar1Click(TObject *Sender);

  private:     // ATRIBUTOS PRIVADOS  //

     // Identificador y manejador de graficos
     HDC hdc;
     HGLRC hrc;

     // Definen el tamaño del volumen de vista
     GLfloat xLeft,xRight,yTop,yBot;

    // Guarda el radio del puerto de vista
    GLfloat RatioViewPort;

    // Punto central y limite
    Punto P, Q;

    // Puntos del rectangulo
    Punto A, B, C, D;

    // Punto auxiliar R
    Punto R;

    // Distancia de los vertices con respecto al punto central P
    GLfloat longX, longY;

    // Contador de rectangulos
    GLint cont;

    // Opción de centrado
    GLint centrado;

    // Grosor del Punto origen P
    GLint grosorP;

    // Grosor del Punto límite Q
    GLint grosorQ;

    // Grosor de líneas de rectángulos
    GLint grosorLinRect;

    // Grosor de líneas de diagonales
    GLint grosorLinDiag;

    // Color del punto de Origen
    GLfloat colorP[3];

    // Color del punto de Límite
    GLfloat colorQ[3];

    // Color de líneas de rectángulo inicial
    GLfloat colorLinRect[3];

    // Color de líneas de diagonales
    GLfloat colorLinDiag[3];

    // Color de líneas actual
    GLfloat colorLinAct[50][3];

    // MÉTODOS PRIVADOS //
    void __fastcall SetPixelFormatDescriptor();
    
    void __fastcall GLScene();
    void dibujarPuntoCentral(Punto P, GLint grosorP, GLfloat colorP[3]);
    void dibujarRectanguloInicial(Punto A,Punto B,Punto C,Punto D,GLint grosorLinRect,GLfloat colorLinRect[3]);
    void dibujarDiagonales(Punto A,Punto B,Punto C,Punto D,Punto & R, GLint grosorLinDiag,GLfloat colorLinDiag[3]);
    void TGLForm2D::dibujarPuntoLimite(Punto A, Punto B, Punto C, Punto R, Punto & Q, GLint grosorQ, GLfloat colorQ[3]);
    void TGLForm2D::dibujarRectangulosAnidados(Punto A,Punto B,Punto C,Punto D,GLint cont, GLint grosorLinRect,GLfloat colorLinAct[50][3]);
    void TGLForm2D::dibujarEscena();

 public:  // ATRIBUTOS PÚBLICOS  //

     // Puntero al componente de OpenGL
     TGLForm2D *GLForm2D;

     // Razón Aúrea
     GLdouble RAZON_AUREA;

     // Factor para el movimiento de Traslacion
     GLdouble T;

     // Factor Multiplo para el ZOOM
     GLdouble F;

     // Factor Divisor para el ZOOM
     GLdouble f;

     // Activar/Desactivar color automatizado
     bool automatico;

     // Activar/Desactivar embaldosado (TILING)
     bool embaldosado;

     // Numero de Filas y columnas a embaldosar
     GLint N;

     // Número máximo de rectangulos que se pueden anidar
     int LIM_RECTANGULOS;

     // MÉTODOS PÚBLICOS //
   __fastcall TGLForm2D(TComponent* Owner);

};
//---------------------------------------------------------------------------
extern PACKAGE TGLForm2D *GLForm2D;
//---------------------------------------------------------------------------
#endif


