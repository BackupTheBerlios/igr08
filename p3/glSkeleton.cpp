//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop
USERES("glSkeleton.res");
USEFORM("UFP.cpp", GLForm2D);
USEUNIT("PV.cpp");
USEUNIT("Conversiones.cpp");
USEUNIT("Obstaculo.cpp");
USEUNIT("Convexo.cpp");
USEUNIT("Circulo.cpp");
USEUNIT("Pelota.cpp");
USEUNIT("Rectangulo.cpp");
USEUNIT("Escena.cpp");
USEUNIT("Mando.cpp");
USEUNIT("Lapiz.cpp");
USEFORM("resultados.cpp", Form1);
//---------------------------------------------------------------------------
WINAPI WinMain(HINSTANCE, HINSTANCE, LPSTR, int)
{
        try
        {
                 Application->Initialize();
                 Application->CreateForm(__classid(TGLForm2D), &GLForm2D);
                 Application->CreateForm(__classid(TForm1), &Form1);
                 Application->Run();
        }
        catch (Exception &exception)
        {
                 Application->ShowException(&exception);
        }
        return 0;
}
//---------------------------------------------------------------------------
