/* Clase Objeto3D
 * De cada objeto almacenaremos los siguientes campos:
 *    - Id: Identifica al objeto en cuesti�n que queremos modificar
 *    - Pos: Posici�n del objeto en la escena (Sistema de Coordenadas Global)
 *    - Color: Color del objeto en cuesti�n.
 *    - matriz: Matriz de traformaci�n af�n aplicado al objeto.
 */

package practica5.Modelo;

public class Objeto3D {
    
    // Atributos protegidos
    private int id;
    private Color color;
    private TAfin matriz;
    private PuntoVector3D pos;
    
    // Constructora
    public Objeto3D() {}
    
    // M�todo virtual
    public void dibuja() {};
    
    // Getters & Setters
    public PuntoVector3D getPos() {
        return pos;
    }

    public void setPos(PuntoVector3D pos) {
        this.pos = pos;
    }

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
