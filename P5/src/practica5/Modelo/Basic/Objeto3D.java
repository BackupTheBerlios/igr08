/* Clase Objeto3D
 * De cada objeto almacenaremos los siguientes campos:
 *    - Id: Identifica al objeto en cuestión que queremos modificar
 *    - Color: Color del objeto en cuestión.
 *    - matriz: Matriz de traformación afín aplicado al objeto.
 */

package practica5.Modelo.Basic;

public class Objeto3D {
    
    // Atributos privados
    private int id;
    private Color color;
    private TAfin matriz;
    
    // Constructora
    public Objeto3D() {}
    
    // Método virtual
    public void dibuja() {};
    
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
    
    public void setColor(double Red, double Green, double Blue) {
        this.color = new Color(Red, Green, Blue);    
    }

    public TAfin getMatriz() {
        return matriz;
    }

    public void setMatriz(TAfin matriz) {
        this.matriz = matriz;
    }

}
