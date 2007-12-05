//---------------------------------------------------------------------------

#ifndef OutCodeH
#define OutCodeH
//---------------------------------------------------------------------------

class OutCode
{
    private:
    bool arriba;
    bool abajo;
    bool derecha;
    bool izquierda;


    public:

    OutCode(){arriba = false; abajo = false; derecha = false; izquierda = false;}
    OutCode(bool ar, bool ab, bool de, bool iz){  arriba = ar; abajo= ab; derecha= de; izquierda = iz;}
    setArriba(bool a){arriba = a;}
    setAbajo(bool a){abajo = a;}
    setDerecha(bool a){derecha = a;}
    setIzquierda(bool a){izquierda = a;}

};



#endif
