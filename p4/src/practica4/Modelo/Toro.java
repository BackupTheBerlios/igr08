package practica4.Modelo;

import java.util.ArrayList;

public class Toro extends Malla {

    /**
     * Ecuaciones parametricas de una circunferencia
     *	x = r · cos (t)
     *	y = r · sin (t)
     */
    public static int MAX = 1000;
    private int nP,  nQ; // nP: nº de lados del polígono de la seccion del toro
    // nQ = nº de capas del toro
    private float r1,  r2; // r1: radio del toro; r2: radio de la seccion del toro
    private double[] matrizFrenet = new double[16];

    public Toro(int nP, int nQ, float r1, float r2) {
        super();
        this.nP = nP;
        this.nQ = nQ;
        this.r1 = r1;
        this.r2 = r2;
        ArrayList<PuntoVector3D> poligonoConcreto;
        PuntoVector3D origenComunLocal = new PuntoVector3D(0, 1, 0, 1);

        for (int t = 0; t < (nQ * 6) + 0; t = t + 6) {
            crearMarcoFrenet(t);

            poligonoConcreto = crearPoligonoRegular(nP, r2, origenComunLocal);
            cambiaCoordenadas(poligonoConcreto); //matrizFrenet no se le pasa por parámetro porque es global

            //Guardamos los vertices de la malla.
            for (int i = 0; i < nP; i++) {
                this.vertices.add(poligonoConcreto.get(i));
            }
        }

        //Creamos las normales de las caras
        for (int i = 0; i < (nQ - 1); i++) {  // Para "cada" capa...
            for (int j = 0; j < nP; j++) {    // Para cada arista...

                ArrayList<PuntoVector3D> verticesCara = new ArrayList<PuntoVector3D>();

                verticesCara.add(vertices.get((i * nP) + j));

                int sig = (j + 1) % nP;

                verticesCara.add(vertices.get((i * nP) + sig));

                verticesCara.add(vertices.get(((i + 1) * nP) + sig));

                verticesCara.add(vertices.get(((i + 1) * nP) + j));

                PuntoVector3D normal = this.metodoNewell(verticesCara, 4);
                normales.add(normal);

                Cara unaCara = new Cara();
                VerticeNormal VN0 = new VerticeNormal((i * nP) + j, i * nP + j);
                VerticeNormal VN1 = new VerticeNormal((i * nP) + sig, i * nP + j);
                VerticeNormal VN2 = new VerticeNormal(((i + 1) * nP) + sig, i * nP + j);
                VerticeNormal VN3 = new VerticeNormal(((i + 1) * nP) + j, i * nP + j);

                unaCara.setIndiceVerticeNormal(VN0);
                unaCara.setIndiceVerticeNormal(VN1);
                unaCara.setIndiceVerticeNormal(VN2);
                unaCara.setIndiceVerticeNormal(VN3);

                this.caras.add(unaCara);
            }
        }
    }

    private void crearMarcoFrenet(int t) {
        /**
         * x = r1 · cos (t)
         * y = r1 · sin (t)
         **/
        //El vector trayectoria
        PuntoVector3D ct = new PuntoVector3D(r1 * Math.cos(t),
                r1 * Math.sin(t),
                0,
                1);
        /*PuntoVector3D ct = new PuntoVector3D(r1 * Math.cos(t),
        0,
        r1 * Math.sin(t),
        1);*/

        //El vector derivada de la trayectoria
        PuntoVector3D tt = new PuntoVector3D(-r1 * Math.sin(t),
                r1 * Math.cos(t),
                0,
                0);
        /*PuntoVector3D tt = new PuntoVector3D(-r1 * Math.sin(t),
        0,
        r1 * Math.cos(t),
        0);*/
        // El vector binormal
        PuntoVector3D bt = new PuntoVector3D(0,
                0,
                -1,
                0);///???
	/*PuntoVector3D bt = new PuntoVector3D(0,
        -(1),
        0,
        0);///???*/

        //El producto vectorial de bt y tt
        PuntoVector3D nt = new PuntoVector3D(-r1 * (Math.cos(t)),
                -r1 * (Math.sin(t)),
                0,
                0);
        /*PuntoVector3D nt = new PuntoVector3D(-r1 * (Math.cos(t)),
        0,
        -r1 * (Math.sin(t)),
        0);*/

        tt = tt.normaliza();
        nt = nt.normaliza();

        //Creamos la matriz de cambio de coordenadas locales en globales.

        //1ª fila
        matrizFrenet[0] = nt.getX();
        matrizFrenet[1] = bt.getX();
        matrizFrenet[2] = tt.getX();
        matrizFrenet[3] = ct.getX();

        //2ª fila
        matrizFrenet[4] = nt.getY();
        matrizFrenet[5] = bt.getY();
        matrizFrenet[6] = tt.getY();
        matrizFrenet[7] = ct.getY();

        //3ª fila
        matrizFrenet[8] = nt.getZ();
        matrizFrenet[9] = bt.getZ();
        matrizFrenet[10] = tt.getZ();
        matrizFrenet[11] = ct.getZ();

        //4ª fila
        matrizFrenet[12] = nt.getPV();
        matrizFrenet[13] = bt.getPV();
        matrizFrenet[14] = tt.getPV();
        matrizFrenet[15] = ct.getPV();
    }

    ArrayList<PuntoVector3D> crearPoligonoRegular(int numLados, float radio, PuntoVector3D centro) {
        ArrayList<PuntoVector3D> poligono = new ArrayList<PuntoVector3D>();
        //Ángulos en radianes
        double anguloAlfa = 2 * Math.PI / numLados;
        double coordenadaZ = centro.getZ();

        for (int i = 1; i <= numLados; i++) {
            PuntoVector3D puntoNuevo = new PuntoVector3D(centro.getX() + radio * Math.cos(anguloAlfa * i), centro.getY() + radio * Math.sin(anguloAlfa * i), coordenadaZ, 1);
            poligono.add(puntoNuevo);
        }
        return poligono;
    }

    private void cambiaCoordenadas(ArrayList<PuntoVector3D> poligono) {
        for (int i = 0; i < nP; i++) {

            double X = (poligono.get(i).getX() * matrizFrenet[0] +
                    poligono.get(i).getY() * matrizFrenet[1] +
                    poligono.get(i).getZ() * matrizFrenet[2] +
                    +matrizFrenet[3]);

            double Y = (poligono.get(i).getX() * matrizFrenet[4] +
                    poligono.get(i).getY() * matrizFrenet[5] +
                    poligono.get(i).getZ() * matrizFrenet[6] +
                    matrizFrenet[7]);

            double Z = (poligono.get(i).getX() * matrizFrenet[8] +
                    poligono.get(i).getY() * matrizFrenet[9] +
                    poligono.get(i).getZ() * matrizFrenet[10] +
                    matrizFrenet[11]);

            //Se cambian los valores con el nuevo marco
            PuntoVector3D p = new PuntoVector3D(X, Y, Z, 1);
            poligono.set(i, p);
        }
    }
}
