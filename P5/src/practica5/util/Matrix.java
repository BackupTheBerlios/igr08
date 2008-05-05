/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica5.util;

/**
 *
 * @author Iván
 */
public class Matrix {

    /* Matrix-multiply two arrays together.
     * The arrays MUST be rectangular.
     * @author Tom Christiansen & Nathan Torkington, Perl Cookbook version.
     */
    private static double[][] multiply(double[][] m1, double[][] m2) {
	int m1rows = m1.length;
	int m1cols = m1[0].length;
	int m2rows = m2.length;
	int m2cols = m2[0].length;
	if (m1cols != m2rows) {
	    throw new IllegalArgumentException("matrices don't match: " + m1cols + " != " + m2rows);
	}
	double[][] result = new double[m1rows][m2cols];

	// multiply
	for (int i = 0; i < m1rows; i++) {
	    for (int j = 0; j < m2cols; j++) {
		for (int k = 0; k < m1cols; k++) {
		    result[i][j] += m1[i][k] * m2[k][j];
		}
	    }
	}

	return result;
    }

    /** Matrix print.
     */
    public static void mprint(double[][] a) {
	int rows = a.length;
	int cols = a[0].length;
	System.out.println("array[" + rows + "][" + cols + "] = {");
	for (int i = 0; i < rows; i++) {
	    System.out.print("{");
	    for (int j = 0; j < cols; j++) {
		System.out.print(" " + a[i][j] + ",");
	    }
	    System.out.println("},");
	}
	System.out.println(":;");
    }

    public static double[] multiplica(double[] m11D, double[] m21D) {

	double[][] m1 = Matrix.unflatten(m11D);
	double[][] m2 = Matrix.unflatten(m21D);
	double[][] m3 = multiply(m1, m2);
	double[] res = Matrix.flatten(m3);
	return res;
    }

    public static void main(String[] argv) {
	//+
	double x[][] = {
	    {3, 2, 3},
	    {5, 9, 8},
                               };
    double y[][] = 
    {	    { 4, 7},
	    {9, 3},
	    {8, 1}};
	double z[][] = Matrix.multiply(x, y);
	Matrix.mprint(x);
	Matrix.mprint(y);
	Matrix.mprint(z);
    //-
    }

    private static double[] flatten(double[][] m12D) {
	double[] m1 = new double[16];
	int ind = 0;
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < 4; j++) {
		m1[ind] = m12D[i][j];
		ind++;
	    }
	}
	return m1;
    }

    private static double[][] unflatten(double[] m11D) {
	double[][] m1 = new double[4][4];
	int ind = 0;
	for (int i = 0; i < 4; i++) {
	    for (int j = 0; j < 4; j++) {
		m1[i][j] = m11D[ind];
		ind++;
	    }
	}
	return m1;
    }
}
