package practica4.Modelo;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica4.util.Lapiz;

public class Toro extends Malla {

    /**
     * Ecuaciones parametricas de una elipse
     *	x = a � cos (t)
     *	y = b � sin (t)
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
	circunferencia_1 = l.poligonoR2(new PuntoVector3D(), rad1, ladosPoligono);
	circunferencia_2 = l.poligonoR2(new PuntoVector3D(), rad2, ladosToro);
    }

    public void dibuja(GL gl) {
	gl.glBegin(gl.GL_LINES);

	for (int i = 0; i < circunferencia_1.size(); i++) {
	    PuntoVector3D unPunto = circunferencia_1.get(i);
	    gl.glVertex3d(unPunto.getX(), unPunto.getY(), unPunto.getZ());
	}
	gl.glEnd();
    }
}
