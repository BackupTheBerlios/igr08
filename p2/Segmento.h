//---------------------------------------------------------------------------
#define"punto2f.h"

#ifndef SegmentoH
#define SegmentoH
//---------------------------------------------------------------------------

class Segmento{

private:
Punto2f * inicio;
Punto2f * final;

public:
Segmento() {
inicio = new Punto2f(0.0, 0.0);
final = new Puntof(0.0, 0.0)
}

Segmento(Punto2f i, Punto2f f) {}
   inicio = i.clon();
   final = f.clon();
}


~Segmento(){
   delete inicio;
   delete final;
}

#endif
