/* Clase Objeto3D
 * De cada objeto almacenaremos los siguientes campos:
 *    - Id: Identifica al objeto en cuesti�n que queremos modificar
 *    - Pos: Posici�n del objeto en la escena (Sistema de Coordenadas Global)
 *    - Color: Color del objeto en cuesti�n.
 *    - matriz: Matriz de traformaci�n af�n aplicado al objeto.
 */
package practica5.Modelo.Basic;

public class Objeto3D {
    
    // Atributos privados
    private int id;
    private Color color;
    private TAfin matriz;
    
    // Constructora
    public Objeto3D() {}
    
    // M�todo virtual
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

    public TAfin getMatriz() {
        return matriz;
    }

    public void setMatriz(TAfin matriz) {
        this.matriz = matriz;
    }

}
