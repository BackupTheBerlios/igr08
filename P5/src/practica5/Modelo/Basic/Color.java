package practica5.Modelo.Basic;

public class Color {
    
    public static final Color color1 = new Color(1, 0, 0);
    public static final Color color2 = new Color(0, 1, 0);
    public static final Color color3 = new Color(0, 0, 1);
    public static final Color color4 = new Color(0, 0, 1);
    public static final Color color5 = new Color(1, 1, 0);
    public static final Color color6 = new Color(0, 1, 1);
    public static final Color color7 = new Color(1, 0, 1);
    public static final Color color8 = new Color(1, 1, 1);
    
    // Atributos privados
    private double Red;
    private double Green;
    private double Blue;
    
    // Constructora
    public Color(double Red, double Green, double Blue) {
        this.Red = Red;
        this.Green = Green;
        this.Blue = Blue;
    }
    
    // Getters & Setters
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
