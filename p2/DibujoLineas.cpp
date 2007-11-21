//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "DibujoLineas.h"
//---------------------------------------------------------------------------

// Constructora por defecto
DibujoLineas::DibujoLineas() {
        listaSegmentos = new Lista <Segmento>();
        listaSegmentos->inicia();
}

