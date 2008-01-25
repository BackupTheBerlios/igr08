//---------------------------------------------------------------------------
#include "Obstaculo.h"
#include "Lapiz.h"
#ifndef ConvexoH
#define ConvexoH
//---------------------------------------------------------------------------
class Convexo : public Obstaculo{

    public:
        Convexo();
        Convexo(PV**);
        Convexo(PV* centro, GLfloat radio, int nlados);
        bool Corte(Pelota* pelota, GLdouble &tIn, PV* &normal);
        
        ~Convexo();
        void Pinta();
    private:
            //PV** normales;




};
#endif
