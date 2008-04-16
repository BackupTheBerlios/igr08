package practica5.Modelo.Basic;

public class Tablero extends Malla{
    private double X, Y, Z;
    private double divX, divY, divZ;
    
    public Tablero() {
    }
    
    public Tablero (double X, double Y, double Z,
                    double divX, double divY, double divZ) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.divX = divX;
        this.divY = divY;
        this.divZ = divZ;
    }
    
}
