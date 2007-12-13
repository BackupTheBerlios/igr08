//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop
USERES("glSkeleton.res");
USEFORM("UFP.cpp", GLForm2D);
USEUNIT("Lapiz.cpp");
USEUNIT("conversiones.cpp");
USEUNIT("Escena.cpp");
USEUNIT("DibujoLineas.cpp");
USEUNIT("Segmento.cpp");
USEUNIT("Lista.cpp");
USEUNIT("Nodo.cpp");
USEUNIT("OutCode.cpp");
USEUNIT("Punto2f.cpp");
USE("glSkeleton.todo", ToDo);
//---------------------------------------------------------------------------
WINAPI WinMain(HINSTANCE, HINSTANCE, LPSTR, int)
{
        try
        {
                 Application->Initialize();
                 Application->CreateForm(__classid(TGLForm2D), &GLForm2D);
                 Application->Run();
        }
        catch (Exception &exception)
        {
                 Application->ShowException(&exception);
        }
        return 0;
}
//---------------------------------------------------------------------------
