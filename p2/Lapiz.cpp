//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Lapiz.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Lapiz::Lapiz() {
    ang = 0.0;
    pos = new Punto2f(0,0);
}

// Constructora por par�metros
Lapiz::Lapiz(Punto2f * p, GLfloat a){
    ang = a;
    pos = p -> clon();
    delete p;
    p=NULL;
}

// Constructora de copia
Lapiz::Lapiz (const Lapiz& l){
    ang = l.ang;
    pos = l.pos -> clon();
}

// M�todo Clon
Lapiz * Lapiz::clon() const{
    Lapiz * l = new Lapiz();
    *l = *this;
    return l;
}

// Dibuja un segmento desde la posicion relativa
void Lapiz::lineTo (Punto2f * destino, bool esVisible){

    if ( esVisible){
        glBegin(GL_LINES);
                glVertex2f(pos -> getX(), pos -> getY());
                glVertex2f(destino -> getX(), destino -> getY());
        glEnd();
    }
    delete pos;
    pos = destino->clon();
}

// Variamos el angulo actual de dibujo
void Lapiz::gira (GLdouble incrAng){
/*        GLdouble radianes = g2r(incrAng);*/

//        ang += radianes;
//        GLdouble grados = r2g(ang);

/*        double  p_E;
        double parte_Decimal = modf(radianes, &p_E);
        int  parte_Entera = p_E;
        parte_Entera = parte_Entera % 360;
        GLdouble grados = r2g(parte_Entera);
        grados += r2g(parte_Decimal);
        ang+=g2r(grados);
  */
        ///////////////////////////
 GLdouble sum = incrAng + r2g(ang);
 double  p_E;
 double parte_Decimal = modf(sum, &p_E);
 int  parte_Entera = p_E;
 parte_Entera = parte_Entera % 360;
 GLdouble grados = g2r(parte_Entera);
 grados += g2r(parte_Decimal);
 ang = grados;


}

// Avanzamos al siguiente punto relativo
void Lapiz::avanza (GLfloat longitud, bool esVisible, Segmento*& s){
      //  GLfloat aa = longitud * cos(ang);
        GLfloat xD = pos -> getX() + longitud * cos (ang);
        GLfloat yD = pos -> getY() + longitud * sin (ang);
        Punto2f * p = new Punto2f(xD,yD);
        s = new Segmento(pos,p);
        lineTo(p,esVisible);
        delete p;
        p=NULL;
}

//---------------------------------------------------------------------------

void Lapiz::drawMotivo(Lapiz *l){

}

//---------------------------------------------------------------------------

void Lapiz::drawTotal (Punto2f * posInicial, GLfloat angInicial){

}

//---------------------------------------------------------------------------

// Realiza los calculos para dibujar una poliEspiral
void Lapiz::poliEspiral ( Punto2f * posInicial, GLfloat incrAng,
                          GLfloat angInicial, GLfloat incrLong,
                          GLfloat longInicial, int nPasos, DibujoLineas* dl){

//        Lapiz * l = new Lapiz (posInicial, angInicial);
        for (int i = 0; i<nPasos; i++){
                 Segmento* s;
                 this -> avanza(longInicial, true, s);
                 longInicial += incrLong;
                 this -> gira(incrAng);
                 dl->inserta(s);
                 delete s;
        }
//        delete l;
//        delete posInicial;
        posInicial=NULL;
}

//---------------------------------------------------------------------------

void Lapiz::poligonoR1 (GLfloat lado, int nlados, DibujoLineas* dl){
        GLdouble alfa = 360.0 / (GLdouble) nlados;
        GLdouble beta = (180.0 - alfa) / 2.0;
        GLdouble gamma = 180 - 2*beta;
        for (int i = 0; i<nlados; i++){
                Segmento* s;
                this->avanza(lado, true, s);
                this->gira(gamma);
                dl->inserta(s);
                delete s;
        }
}

//---------------------------------------------------------------------------

void Lapiz::poligonoR2 (Punto2f * centro, GLfloat radio, int nlados, DibujoLineas* dl){
        GLdouble alfa = 360.0 / (GLdouble) nlados;
        GLdouble beta = (180.0 - alfa) / 2.0;
     //   GLdouble gamma = 180 - 2*beta;
        GLdouble theta = 180 - 2*beta;
//        GLfloat cose =   cos(g2r(beta));
        this->pos->setX(centro->getX()+(cos(g2r(beta))*radio));
        this->pos->setY(centro->getY()-(sin(g2r(beta))*radio));
        this->ang= g2r(theta);

        GLfloat lado = cos(g2r(beta))*radio*2;
        poligonoR1(lado, nlados, dl);
}

//---------------------------------------------------------------------------
void Lapiz::arcoR2 (Punto2f * centro, GLfloat radio, int nlados, GLdouble angInicial, GLdouble angOtro, GLdouble angFinal, DibujoLineas* dl, bool righthanded){
        GLdouble apertura, aIn, aFn, aOn, aPn;
        aFn = normaliza (angFinal);
        aIn = normaliza(angInicial);
        aOn = normaliza(angOtro);
        aPn = calculaApertura (aIn, aFn, aOn);
        GLdouble alfa = aPn / (GLdouble) nlados;
        GLdouble beta = (180.0 - alfa) / 2.0;
        GLdouble theta = 180 - 2*beta;
       if (righthanded){
                this->pos->setX(centro->getX()+(cos(g2r(angFinal))*radio));
                this->pos->setY(centro->getY()+(sin(g2r(angFinal))*radio));
                this->ang = g2r(angFinal+90-(theta/2.0));
       }

       else {
                this->pos->setX(centro->getX()+(cos(g2r(angInicial))*radio));
                this->pos->setY(centro->getY()+(sin(g2r(angInicial))*radio));
                this->ang = g2r(angInicial+90+(theta/2.0));
       }
       /* if (aIn<aFn){
                if (aIn < aOn){
                        this->pos->setX(centro->getX()+(cos(g2r(angInicial))*radio));
                        this->pos->setY(centro->getY()+(sin(g2r(angInicial))*radio));
                        this->ang = g2r(angInicial+90+(theta/2.0));
                        }
                else{
                        this->pos->setX(centro->getX()+(cos(g2r(angFinal))*radio));
                        this->pos->setY(centro->getY()+(sin(g2r(angFinal))*radio));
                        this->ang = g2r(angFinal+90+(theta/2.0));
                    }
         }
        else {
                if (aIn>aOn){
                        this->pos->setX(centro->getX()+(cos(g2r(angFinal))*radio));
                        this->pos->setY(centro->getY()+(sin(g2r(angFinal))*radio));
                        this->ang = g2r(angFinal+90+(theta/2.0));
                }
                else {
                        this->pos->setX(centro->getX()+(cos(g2r(angInicial))*radio));
                        this->pos->setY(centro->getY()+(sin(g2r(angInicial))*radio));
                        this->ang = g2r(angInicial+90+(theta/2.0));
                }
        }
            */
        GLfloat lado = cos(g2r(beta))*radio*2;
        arcoR3(lado, nlados, theta, dl);
}
//---------------------------------------------------------------------------

void Lapiz::arcoR3 (GLdouble lado,  int nlados, GLdouble theta, DibujoLineas* dl){
    Segmento * s;
    for (int i = 0; i<nlados; i++){
        avanza(lado, true, s);
        gira (theta);
        dl->inserta(s);
        delete s;
    }
}
//---------------------------------------------------------------------------
void Lapiz::arco(Punto2f * inicio, Punto2f * final, Punto2f *otro, int nlados, DibujoLineas* dl){

        //calculo Punto Medio A
        Punto2f *A = inicio->puntoMedio(otro);
        //calculo Punto Medio B
        Punto2f *B = final->puntoMedio(otro);

        Punto2f *BA, *Va, *Vap, *Vb, *Vbp, *P0P1, *P1P2; // son vectores

        Vap = *otro - *inicio;
        Va = Vap->perpendicular();
        Vbp = *final - *otro;
        Vb = Vbp->perpendicular();
        GLdouble k1, k2;
        BA = *A - *B;

        k2=BA->dot(Vap);
        GLdouble  aux = Vb->dot(Vap);
        k2=k2/aux;

        Punto2f * BmenosA = *B-*A;
        Punto2f * aux2 = Vb->multiplicar(k2);
        Punto2f * del = aux2;
        aux2= *BmenosA + *aux2;
        k1 = aux2->getX() / Va->getX();
        Punto2f * aux3 = Va->multiplicar(k1);
        Punto2f * centro = *A + *aux3;
        GLfloat radio = centro->distancia(inicio);

        GLfloat angInicial, angFinal, angOtro;
/*        angInicial= acos((inicio->getX() - centro->getX())/radio);
        angFinal= asin((final->getY() -  centro->getY())/radio);
        angOtro= asin((otro->getY() -  centro->getY())/radio);*/

        angInicial = centro->angulo2(inicio);
        angFinal = centro->angulo2 (final);
        angOtro = centro -> angulo2 (otro);

        angInicial = centro->angulo2(inicio);
        angFinal = centro->angulo2 (final);
        angOtro = centro -> angulo2 (otro);

        bool  righthanded, lefthanded;
        GLdouble angOF, angIO, angX;
        angIO = atan2(otro->getY()-inicio->getY(), otro->getX()-inicio->getX());
        angOF = atan2(final->getY()-otro->getY(), final->getX()-otro->getX());
        angIO = normaliza2(r2g(angIO));
        angOF = normaliza2(r2g(angOF));
        angX = angOF - angIO;
        if (angX < 0.0) {
                righthanded = true;
                lefthanded = false;
                }
        if (angX > 0.0) {
                lefthanded = true;
                righthanded = false;
                }


        arcoR2(centro, radio, nlados, angInicial, angOtro, angFinal, dl, righthanded);
    //    this->poligonoR2(centro, radio, nlados, dl);
        delete A; //???
        delete B; //???
        delete Vap;
        delete Va;
        delete Vbp;
        delete Vb;
        delete aux2;
        delete aux3;
        delete del;
        delete BA;
        delete BmenosA;
        delete centro;
//calcular mediatrices
        // calcular puntos medios
/*            Punto2f * A = inicio ->puntoMedio(otro);
            Punto2f * B = otro ->puntoMedio(fin);
        // calcular vector normal
            GLdouble x = inicio->getX() - otro -> getX();
            GLdouble y = inicio->getY() - otro -> getY();
            Punto2f * v1 = new Punto2f(x,y); // es un vector
            Punto2f * v1p= v1->perpendicular();
  */
// calcular punto de corte de las mediatrices.
//calcular radio

}


void Lapiz::arco(Punto2f * centro, GLdouble radio, GLdouble angInicial, GLdouble angFinal, int nlados, DibujoLineas* dl){
        // calcular poner al lapiz con angulo correcto
        this->ang = angInicial + g2r(90);

        GLfloat dyi, dxi;
//        GLfloat ang = angFinal-angInicial;
        for (int i = 0; i<=nlados; i++){
                dxi = cos(angInicial+(i * ang / nlados));
                dyi = sin(angInicial+(i * ang / nlados));
                // calcular posicion correcta
                this->pos->setX(centro->getX()+dxi);
                this->pos->setX(centro->getY()+dyi);
                
        }


//        poligonoR2 (centro, radio, nlados, dl);
}

void Lapiz::Casteljau(float t, Punto2f** puntos, Punto2f* p, int n) {

    for (int j=1; j<n; j++) {
      for (int k=0; k<n-j; k++) {
         float xd = (1-t) * puntos[k]->getX() + t * puntos[k+1]->getX();
         puntos[k]->setX(xd);

         float yd = (1-t) * puntos[k]->getY() + t * puntos[k+1]->getY();
         puntos[k]->setY(yd);
      }
    }

    p->setX(puntos[0]->getX());
    p->setY(puntos[0]->getY());
}

void Lapiz::Bezier(Punto2f** puntos,int num_Puntos,int Num_Lados,DibujoLineas* dl) {

   float ti, tj, incr;
   Punto2f* pos_actual;
   Segmento* s;
   pos_actual = NULL;

   // Punto Inicial
   Punto2f* p = new Punto2f(puntos[0]->getX(),puntos[0]->getY());
   ti = 0.0;
   tj = 1.0;
   incr = (float) tj / Num_Lados;

   //for (int i=1; i<=Num_Lados; i++) {
   while (ti<=tj) {
      //t = (float) i/Num_Lados;
      Casteljau(ti,puntos,p,num_Puntos);

      ti += incr;
      if ( pos_actual== NULL)
         pos_actual = p->clon();

      s = new Segmento(pos_actual, p);
      dl->inserta(s);
      delete pos_actual;
      delete s;

      pos_actual = p->clon();

   }
}

/*

int Lapiz::fact(int n) {

  int num = 1;

  if (n!=0)
    for (int i=n; i>=1; i--)
      num = num * i;

  return num;
}

float Lapiz::elevado(float base, int exp) {

   float res = (float) base;

   for (int i=2; i<=exp; i++)
     res = (float) res * base;

   return res;
}

float Lapiz::BaseB(int n, int k, float t) {

    float factorial = (float) fact(n) / (fact(k) * fact(n-k));

    return (float) factorial * elevado(1-t,n-k) * elevado(t,n);

}

void Lapiz::Bezier(Punto2f** puntos,int Num_Puntos,int Num_Lados,DibujoLineas* dl) {

   float xd, yd;
   float ti, tj;
   float v, incr;
   Punto2f* pos_actual;
   Punto2f* p;
   Segmento* s;
   pos_actual = NULL;

   ti = 0.0;
   tj = 1.0;

   incr = (float) tj / Num_Lados;
   //xd = puntos[0]->getX();
   //yd = puntos[0]->getY();

   while (ti<=tj) {
     //t = (float) i / Num_Lados;
     //xd = puntos[0]->getX();
     //yd = puntos[0]->getY();
     xd = 0;
     yd = 0;

     for (int k=0; k<Num_Puntos; k++) {
        v = (float) BaseB(Num_Puntos,k,ti);
        xd += puntos[k]->getX() * v;
        //puntos[k]->setX(xd);
        yd += puntos[k]->getY() * v;
        //puntos[k]->setY(yd);
     }

     ti += incr;

     p = new Punto2f(xd,yd);

     if (pos_actual == NULL)
       pos_actual = p->clon();

     s = new Segmento(pos_actual, p);
     dl->inserta(s);
     delete pos_actual;
     delete s;

     pos_actual = p->clon();
   }
}
*/

float Lapiz::BaseN(float t) {

   float res = 0.0;

   if (0 <= t && t <= 1)
      res = (float) t * t * (1/2.0);
   else if (1 < t && t <= 2)
          res = (float) (3/4.0) - (t - 3/2.0) * (t - 3/2.0);
        else if (2 < t && t <= 3)
               res = (float) (1/2.0) * (3.0 - t) * (3.0 - t);

   return res;
}


void Lapiz::B_Splines(Punto2f** puntos,int num_Puntos,int Num_Lados,DibujoLineas* dl) {

     int ind;
     float ti, tj;
     float v, incr;
     float xd, yd;
     Punto2f* pos_actual;
     pos_actual = NULL;
     Segmento* s;
     Punto2f* p;

     ti = 2.0;
     tj = num_Puntos + 1;
     incr = (float) (tj - 2) / Num_Lados;

     // xd = puntos[0]->getX() * BaseN(ti-0);
     // yd = puntos[0]->getY() * BaseN(ti-0);

     //xd = 0;
     //yd = 0;

     while (ti<tj) {

       xd = 0;
       yd = 0;

       for (int k=0; k<num_Puntos; k++) {
          v = (float) BaseN(ti-k);
          xd += puntos[k]->getX() * v;
          yd += puntos[k]->getY() * v;
       }
       ti += incr;

       p = new Punto2f(xd,yd);

       if (pos_actual == NULL)
         pos_actual = p->clon();

       s = new Segmento(pos_actual, p);
       dl->inserta(s);
       delete pos_actual;
       delete s;

       pos_actual = p->clon();
      }
}
/*
double Lapiz::Base(float t, int i) {

    double base;

    switch (i) {

       case  1: base = (t * t * t)/6;
                break;
       case  0: base = (1 + 3*t + 3*t*t - 3*t*t*t)/6;
                break;
       case -1: base = (4 - 6*t*t + 3*t*t*t)/6;
                break;
       case -2: base = (1 - 3*t + 3*t*t - t*t*t)/6;
                break;
    }

    return base;
}

void Lapiz::B_Splines(Punto2f** puntos,int num_Puntos,int Num_Lados,DibujoLineas* dl) {

     int ind;
     float t, v;
     float xd, yd;
     Punto2f* pos_actual;
     pos_actual = NULL;
     Segmento* s;
     Punto2f* p;

     for (int i=0; i<Num_Lados; i++) {
        t = (float) i/Num_Lados;
        p = new Punto2f();
        xd = 0; yd = 0;

        for (int j=-2; j<=1; j++) {
            v = Base(t,j);
            ind = j + i;

            if (ind < 0) // Aproximaci�n desde el 1� punto
              ind = 0;
            else {       // Aproximaci�n desde el N� punto
              if (ind >= num_Puntos)
                 ind = num_Puntos - 1;
            }

            xd = p->getX() + puntos[ind]->getX() * v;
            yd = p->getY() + puntos[ind]->getY() * v;

            p->setX(xd);
            p->setY(yd);
        }

        // Siguiente segmento a agregar
        if (pos_actual == NULL)
           pos_actual = p->clon();

        s = new Segmento(pos_actual, p);
        dl->inserta(s);
        delete pos_actual;
        delete s;

        pos_actual = p->clon();
        delete p;

     }
  }
  */
/*
float Lapiz::Nudo(int i, int grado, int num_Puntos) {

     float n;

     if (i <= grado)
        n = 0;
     else {
        if (i > num_Puntos)
          n = num_Puntos - grado + 2;
        else
          n = i - grado + 1;
     }
     return n;
}

double Lapiz::Base(float t, int j, int g, int nP) {

    double n1, n2;
    double d1, d2;
    double c1, c2;
    double base;

    if (g == 1) {
      if ((t < Nudo(j+1,g,nP) && (Nudo(j,g,nP) <= t)))
         base = 1;
      else
         base = 0;
    }
    else {
         n1 = (t-j) * Base(t,j,g-1,nP);
         n2 = (Nudo(j+g,g,nP) - t) * Base(t,j+1,g-1,nP);
         d1 = Nudo(j+g-1,g,nP) - Nudo(j,g,nP);
         d2 = Nudo(j+g,g,nP) - Nudo(j+1,g,nP);

         if (d1 == 0)
           c1 = 0;
         else
           c1 = (double) n1/d1;

         if (d2 == 0)
           c2 = 0;
         else
           c2 = (double) n2/d2;

         base = c1 + c2;
      }
   return base;
}

void Lapiz::B_Splines(Punto2f** puntos,int num_Puntos,int nPasos,int grado,DibujoLineas* dl) {

     float t;
     double v;
     float xd, yd;
     Punto2f* pos_actual;
     pos_actual = NULL;
     Segmento* s;
     Punto2f* p;

     for (int i=1; i<=(nPasos * (num_Puntos - grado + 2)); i++) {
         t = (float) i/nPasos;
         p = new Punto2f();
         xd = 0; yd = 0;

         for (int j=0; j<num_Puntos; j++) {
             v = (double) Base(t,j,grado,num_Puntos);

             xd += puntos[j]->getX() * v;
             yd += puntos[j]->getY() * v;

             p->setX(xd);
             p->setY(yd);
         }

        // Siguiente segmento a agregar
        if (pos_actual == NULL)
           pos_actual = p->clon();

        s = new Segmento(pos_actual, p);
        dl->inserta(s);
        delete pos_actual;
        delete s;
        
        pos_actual = p->clon();
        delete p;
     }
}
 */
