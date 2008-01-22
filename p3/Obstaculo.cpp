//---------------------------------------------------------------------------
#include <vcl.h>
#pragma hdrstop
#include "Obstaculo.h"
//---------------------------------------------------------------------------
// Constructora por defecto
Obstaculo::Obstaculo() {
   vertices = new PV*();
//   vertices = new list<PV>();
}

// Constructora con parametros
Obstaculo::Obstaculo(PV** lv) {
        vertices = lv;
}

// Destructora
Obstaculo::~Obstaculo() {
        delete vertices;
}

void Obstaculo::Pinta(){

}

void Obstaculo::calculaNormales(){
//   list<PV>::iterator it;
   normales = new PV*[nVertices];
   PV p0, p1, p2, vectorArista, normal;
   int i = 0;
      bool vertice0 = true;
      for( i; i<nVertices; i++) {
         if (vertice0){
            p1 = PV(vertices[i]->getX(), vertices[i]->getY());
            p0 = p1;
            vertice0 = false;
         }
         else{ // vertices sucesivos
            p2 = PV(vertices[i]->getX(), vertices[i]->getY());
            vectorArista = PV(p1, p2);
            p1 = p2;
            // calcular normal
            normal = vectorArista.perpendicular();
            // falta añadir normal a la lista.. ademas cambiar el sentido?? y hacerlo unitario
            PV vn;
            vn = PV(normal.getX(), normal.getY());
            normales[i-1] = new PV(normal.getX(), normal.getY());
         }
      }

   vectorArista = PV(p0, p2);
   // calcular normal
   normal = vectorArista.perpendicular();
   normales[i-1] = new PV(normal.getX(), normal.getY());
   //mismo que arriba
   //   normales->push_front(normal);
 /*  PV * vn;
   vn = new PV(normal.getX(), normal.getY());
   normales->push_front(*vn);*/
}
#pragma package(smart_init)





