package practica4.util;

public class Conversiones {

    public static double r2g(double radianes) {
	return radianes * 180.0 / Math.PI;
    }

    public static double g2r(double grados) {
	return grados * Math.PI / 180.0;
    }

    public static double parteDecimal(double number) {
	double parteDecimal = number - ((int) number);
	return parteDecimal;
    }
}
