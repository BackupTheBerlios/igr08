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
    bool igual(bool a, bool b) {return a==b;}

    public:

    OutCode(){arriba = false; abajo = false; derecha = false; izquierda = false;}
    OutCode(bool ar, bool ab, bool de, bool iz){  arriba = ar; abajo= ab; derecha= de; izquierda = iz;}

    void setArriba(bool a){arriba = a;}
    void setAbajo(bool a){abajo = a;}
    void setDerecha(bool a){derecha = a;}
    void setIzquierda(bool a){izquierda = a;}

    bool getArriba() {return arriba;}
    bool getAbajo() {return abajo;}
    bool getDerecha() {return derecha;}
    bool getIzquierda() {return izquierda;}

    bool esTodoTrue() { return (arriba  & abajo & derecha & izquierda);}
    bool esTodoFalse(){return (!arriba  & !abajo & !derecha & !izquierda);}
    bool tieneAlgunaCoordenadaTrueIgualQue(OutCode);

    bool operator== (OutCode) const;
};



#endif
