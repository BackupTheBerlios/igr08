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
    void setArriba(bool a){arriba = a;}
    void setAbajo(bool a){abajo = a;}
    void setDerecha(bool a){derecha = a;}
    void setIzquierda(bool a){izquierda = a;}

};



#endif
