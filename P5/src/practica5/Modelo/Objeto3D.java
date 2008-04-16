package practica5.Modelo;

public class Objeto3D {
    
    // Atributos protegidos
    private int id;
    private Color color;
    private TAfin matriz;
    private PuntoVector3D pos;
    
    // Constructora
    public Objeto3D() {}
    
    // Método virtual
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
