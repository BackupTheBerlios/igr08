/** Clase Objeto3D
 * De cada objeto almacenaremos los siguientes campos:
 *    - Id: Identifica al objeto en cuestion que queremos modificar
 *    - Color: Color del objeto en cuestion.
 *    - matriz: Matriz de traformacion afin aplicado al objeto.
 */
package practica5.Modelo.Basic;

import javax.media.opengl.GL;

public class Objeto3D {

    // Atributos privados
    private int id;
    private Color color;
    private TAfin matriz;
    protected int tipoMalla;

    // Constructora
    public Objeto3D() {
	color = new Color(1, 1, 1);
	tipoMalla = Malla.GL_LINES;
    }

    // Metodo virtual
    public void dibuja(GL gl) {
	gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
    }

    // Getters & Setters
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public void setColor3d(double Red, double Green, double Blue) {
	this.color = new Color(Red, Green, Blue);
    }

    public void setTipoMalla(int tipo) {
	this.tipoMalla = tipo;
    }

    public TAfin getMatriz() {
	return matriz;
    }

    public void setMatriz(TAfin matriz) {
	this.matriz = matriz;
    }
}
