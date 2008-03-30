package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica4.util.Lapiz;

public class Toro extends Malla {

    /**
     * Ecuaciones parametricas de una elipse
     *	x = a · cos (t)
     *	y = b · sin (t)
     */
    private int nP,  nQ;
    private float r1,  r2;
    private ArrayList<PuntoVector3D> circunferencia_1 = new ArrayList<PuntoVector3D>();
    private ArrayList<PuntoVector3D> circunferencia_2 = new ArrayList<PuntoVector3D>();

    public Toro(float rad1, float rad2, int ladosToro, int ladosPoligono) {
	r1 = rad1;
	r2 = rad2;
	nP = ladosPoligono;
	nQ = ladosToro;

	Lapiz l = new Lapiz();
	PuntoVector3D ejeXY = new PuntoVector3D(1.0f,1.0f,0.0f);
	PuntoVector3D ejeYZ = new PuntoVector3D(0.0f,1.0f,1.0f);
	circunferencia_1 = l.poligonoR2(new PuntoVector3D(), rad1, ladosPoligono, ejeXY);
	circunferencia_2 = l.poligonoR2(new PuntoVector3D(), rad2, ladosToro, ejeYZ);
    }

    public void dibuja(GL gl) {
	gl.glBegin(gl.GL_LINES);

	for (int i = 0; i < circunferencia_1.size(); i++) {
	    PuntoVector3D unPunto = circunferencia_1.get(i);
	    PuntoVector3D otroPunto = circunferencia_1.get((i+1)%circunferencia_1.size());
	    gl.glVertex3d(unPunto.getX(), unPunto.getY(), unPunto.getZ());
	    gl.glVertex3d(otroPunto.getX(), otroPunto.getY(), otroPunto.getZ());
	}
	gl.glEnd();
    }
}
