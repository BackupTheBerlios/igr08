//---------------------------------------------------------------------------
#pragma hdrstop
#pragma package(smart_init)

#include <vcl.h>
#include "Escena.h"
//---------------------------------------------------------------------------

// Constructora por defecto
Escena::Escena(int distancia, int CW, int CH){
        listaDibujos = new Lista <DibujoLineas>();
        listaDibujos -> inicia();
        ratioViewPort = 1.0;
        xRight =  distancia;
        xLeft  = -xRight;
        yTop   =  xRight;
        yBot   = -yTop;
        ClientWidth = CW;
        ClientHeight = CH;
}

Escena::~Escena(){
        delete listaDibujos;
}


void Escena::Resize(int CW, int CH) {
        ClientWidth = CW;
        ClientHeight = CH;


    // Establecemos el nuevo Puerto de Vista
    glViewport(0, 0, CW, CH);

    // Se actualiza el volumen de vista
    // para que su radio coincida con ratioViewPort
    GLfloat RatioVolVista= xRight/yTop;

    if (RatioVolVista >= ratioViewPort){
       //Aumentamos yTop-yBot
       yTop = xRight / ratioViewPort;
       yBot = -yTop;
    }
    else{
       //Aumentamos xRight-xLeft
       xRight= ratioViewPort*yTop;
       xLeft = -xRight;
    }

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(xLeft,xRight,yBot,yTop);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}

void Escena::Pinta(){
//    glClear(GL_COLOR_BUFFER_BIT);
    listaDibujos ->inicia();
    while(!listaDibujos->final()){
         listaDibujos->getActual()->Pinta();
         listaDibujos->avanza();
   }


 // Ejecutar lista de comandos en espera
 glFlush();       
 // Habilitado uso del doble buffer
 SwapBuffers(hdc);
}

void Escena::inserta(DibujoLineas * dl) {
        listaDibujos->inserta(dl);
}

void Escena::teclado(WORD& Key) {

  // Factor de mov de Traslaccion
  GLfloat T = 0.01;

  // Trasladamos hacia arriba
  if (Key == VK_UP) {
    GLfloat ar = (yTop - yBot)* T;
    yBot += ar;
    yTop += ar;
  }

  // Trasladamos hacia abajo
  if (Key == VK_DOWN) {
    GLfloat ab = (yTop - yBot)* T;
    yBot -= ab;
    yTop -= ab;
  }

  // Trasladamos hacia derecha
  if (Key == VK_RIGHT) {
    GLfloat der = (xRight - xLeft)* T;
    xRight += der;
    xLeft += der;
  }

  // Trasladamos hacia izquierda
  if (Key == VK_LEFT) {
    GLfloat izq = (xRight - xLeft)* T;
    xRight -= izq;
    xLeft -= izq;
  }

  // Tecla A -> Acercamos
  if (Key == 'a' | Key == 'A') {
  Zoom(105);
  }

  // Tecla S -> Alejamos
  if (Key == 's' | Key == 'S') {
  Zoom(95);
  }
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluOrtho2D(xLeft,xRight,yBot,yTop);

  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
}

void Escena::transformarXY(Punto2f * p, int ancho, int alto) {
        GLfloat x_Aux;
        GLfloat y_Aux;

        x_Aux = convertirX(p->getX(), ancho);
        y_Aux = convertirY(p->getY(), alto);

        p->setX(x_Aux);
        p->setY(-y_Aux);
}


void Escena::Zoom(float F){
     F=F/100.0;

     // Calculamos las nuevas coordenadas del AVE
     GLdouble xRn = (xRight + xLeft) / (GLdouble) 2;
     xRn += (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble xLn = (xRight + xLeft) / (GLdouble) 2;
     xLn -= (xRight - xLeft) * (GLdouble) 0.5 * (1/F);

     GLdouble yTn = (yTop+yBot) / (GLdouble) 2;
     yTn += (yTop - yBot) * (GLdouble) 0.5 * (1/F);

     GLdouble yBn = (yTop+yBot) / (GLdouble) 2;
     yBn -= (yTop - yBot) * (GLdouble) 0.5 * (1/F);

     // Actualizamos las variables del AVE
     xLeft = xLn;
     xRight =xRn;

     yBot = yBn;
     yTop = yTn;
     glMatrixMode(GL_PROJECTION);
     glLoadIdentity();
     gluOrtho2D(xLeft,xRight,yBot,yTop);
}
bool Escena::ZoomProgresivo(float factor, int nPasos){
   factor=factor/100.0;
   if (factor>0){
        int xRp, xLp, yTp, yBp;
        GLdouble cx = (xRight + xLeft) / 2.0;
        GLdouble cy = (yBot + yTop) / 2.0;
        GLdouble incrF = (factor - 1) / (GLdouble) nPasos;
        glMatrixMode (GL_PROJECTION);
        for (int i =0; i<= nPasos; i++){
                xRp = cx + 0.5 * ((xRight-xLeft)/(1+ incrF * i));
                xLp = cx - 0.5 * ((xRight-xLeft)/(1+incrF * i));
                yTp = cy + 0.5 * ((yTop-yBot)/(1+incrF * i));
                yBp = cy - 0.5 * ((yTop-yBot)/(1+incrF * i));
                glClear(GL_COLOR_BUFFER_BIT);
                glLoadIdentity();
                gluOrtho2D(xLp,xRp, yBp, yTp);
                Pinta();
                Sleep(deltaT);
        }
   xRight = xRp; xLeft = xLp;
   yTop = yTp; yBot = yBp;
   return true;
   }
   else return false;
}

// Recorte de la escena usando el algorimo de cohen sutherland
Escena * recorte(Punto2f * NE, Punto2f * SO){
/*procedure CohenSutherlandLineClipAndDraw(
       x0,y0,x1,y1,xmin,xmax,ymin,ymax : real ; value: integer);
// Cohen-Sutherland clipping algorithm for line P0=(x0,y0) to P1=(x1,y1 and clip rectangle with diagonal from (xmin,ymin) to (xmax,ymax).}
type
    edge = (LEFT,RIGHT,BOTTOM,TOP);
    outcode = set of edge;*/
bool     accept,done;
OutCode   outcode0,outcode1,outcodeOut;
    //Outcodes for P0,P1, and whichever point lies outside the clip rectangle}
    GLdouble x,y;
 
begin
    accept = false;  done = false;
    CompOutCode (x0,y0,outcode0); CompOutCode (x1,y1,outcode1);
    repeat
      if(outcode0=[]) and (outcode1=[]) then {Trivial accept and exit}
        begin accept := true; done:=true end
      else if (outcode0*outcode1) <> [] then
        done := true {Logical intersection is true,
                      so trivial reject and exit.}
      else
        {Failed both tests, so calculate the line segment to clip;
        from an outside point to an intersection with clip edge.}
        begin
          {At least one endpoint is outside the clip rectangle; pick it.}
          if outcode0 <> [] then
            outcodeOut := outcode0 else outcodeOut := outcode1;
          {Now find intersection point;
          use formulas y=y0+slope*(x-x0),x=x0+(1/slope)*(y-y0).}
 
          if TOP in outcodeOut then
            begin     {Divide line at top of clip rectangle}
              x := x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);
              y := ymax
            end
          else if BOTTOM in outcodeOut then
            begin     {Divide line at bottom of clip rectangle}
              x := x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);
              y := ymin
            end
 
          if RIGHT in outcodeOut then
            begin     {Divide line at right edge of clip rectangle}
              y := y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);
              x := xmax
            end
          else if LEFT in outcodeOut then
            begin     {Divide line at left edge of clip rectangle}
              y := y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);
              x := xmin
            end;
 
          {Now we move outside point to intersection point to clip,
          and get ready for next pass.}
          if (outcodeOut = outcode0) then
            begin
              x0 := x; y0 := y; CompOutCode(x0,y0,outcode0)
            end
          else
            begin
              x1 := x; y1 := y; CompOutCode(x1,y1,outcode1);
            end
        end   {subdivide}
    until done;
    if accept then MidpointLineReal(x0,y0,x1,y1,value) //Version for real coordinates
end; //CohenSutherlandLineClipAndDraw


}

/////////////////////////////////////////////////////
//                 MÉTODOS PRIVADOS                //
/////////////////////////////////////////////////////

// Escala la coordenada X desde el puerto de vista hasta
// el area visible de la escena
GLdouble Escena::convertirX(int x, int ancho) {
    GLdouble escalaAncho = (GLdouble)ancho/(xRight-xLeft);
   // GLdouble centro =   (xLeft-xRight)/2.0;
   if (xLeft <0)
        return (x / escalaAncho + xLeft);
   else
        return (x / escalaAncho -  xLeft);

}

// Escala la coordenada Y desde el puerto de vista hasta
// el area visible de la escena
GLdouble Escena::convertirY(int y, int alto) {
   GLdouble escalaAlto = (GLdouble)alto/(yTop-yBot);
   // GLdouble centro =   (yBot-yTop)/2.0;
   if (yTop<0)
        return (y / escalaAlto +  yTop);
   else
        return (y / escalaAlto - yTop);

}



// Método  privado para el recorte de líneas

    void CompOutCode(GLdouble x,GLdouble y OutCode code &);
//    Compute outcode for the point (x,y)
{
      if      (y > ymax) {code. = [TOP];}
      else if (y < ymin) {code = [BOTTOM];}

      if      (x > xmax) {code = code + [RIGHT];}
      else if (x < xmin) {code = code + [LEFT];}
}
