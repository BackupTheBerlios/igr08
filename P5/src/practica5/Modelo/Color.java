package practica5.Modelo;

public class Color {
    
    private double Red;
    private double Green;
    private double Blue;
    
    public Color(double Red, double Green, double Blue ) {
        this.Red = Red;
        this.Green = Green;
        this.Blue = Blue;
    }

    public double getRed() {
        return Red;
    }

    public void setRed(double Red) {
        this.Red = Red;
    }

    public double getGreen() {
        return Green;
    }

    public void setGreen(double Green) {
        this.Green = Green;
    }

    public double getBlue() {
        return Blue;
    }

    public void setBlue(double Blue) {
        this.Blue = Blue;
    }
}
