//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Convexo.h"

//---------------------------------------------------------------------------
// Constructora
Convexo::Convexo() : Obstaculo() {}
Convexo::Convexo(list<PV>* listaVertices): Obstaculo(listaVertices) {calculaNormales();}

Convexo::Convexo(PV* centro, GLfloat radio, int nlados) : Obstaculo(){
        Lapiz * l = new Lapiz();
        list<PV>* listaVertices = new list<PV>();
        l->poligonoR2(centro, radio, nlados, listaVertices);
        delete vertices;
        vertices = listaVertices;
        delete l;
        calculaNormales();
}

// Destructora
Convexo::~Convexo() {

}

// Metodo que pinta el poligono convexo
void Convexo::Pinta() {
        glBegin(GL_POLYGON);
            list<PV>::iterator it;
            for( it = vertices->begin(); it != vertices->end(); it++ ) {
                glVertex2d(it->getX(), it->getY());
                }
        glEnd();
}

void Convexo::calculaNormales(){
   list<PV>::iterator it;
   PV p0, p1, p2, vectorArista, normal;
      bool vertice0 = true;
      for( it = vertices->begin(); it != vertices->end(); it++ ) {
         if (vertice0){
            p1 = PV(it->getX(), it->getY());
            p0 = p1;
            vertice0 = false;
         }
         else{ // vertices sucesivos
            p2 = PV(it->getX(), it->getY());
            vectorArista = PV(p1, p2);
            p1 = p2;
            // calcular normal
            normal = vectorArista.perpendicular();
            // falta añadir normal a la lista.. ademas cambiar el sentido??
   PV vn;
   vn = PV(normal.getX(), normal.getY());
   normales->push_front(vn);

         }
      }

   vectorArista = PV(p0, p2);
   // calcular normal
   normal = vectorArista.perpendicular();
   //mismo que arriba
   //   normales->push_front(normal);
 /*  PV * vn;
   vn = new PV(normal.getX(), normal.getY());
   normales->push_front(*vn);*/
}
#pragma package(smart_init)
