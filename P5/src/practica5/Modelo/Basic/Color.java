package practica5.Modelo.Basic;

public class Color {
    
    public static final Color rojoClaro = new Color(255, 0, 0);
    public static final Color rosa = new Color(50, 1, 50);
    public static final Color marron = new Color(1, 0, 0);
    public static final Color verdeClaro = new Color(0, 255, 0);
    public static final Color verdeOscuro = new Color(0, 1, 0);
    public static final Color azulClaro = new Color(0, 0, 255);
    public static final Color azulOscuro = new Color(0, 0, 1);
    public static final Color amarilloClaro = new Color(255, 255, 0);
    public static final Color amarilloOscuro = new Color(1, 1, 0);
    public static final Color turquesaClaro = new Color(0, 255, 255);
    public static final Color turquesaOscuro = new Color(0, 1, 1);
    public static final Color violetaClaro = new Color(255, 0, 255);
    public static final Color violetaOscuro = new Color(1, 0, 1);  
    public static final Color blanco = new Color(255, 255, 255);
    public static final Color gris = new Color(1, 1, 1);
    public static final Color negro = new Color(0, 0, 0);
    
    // Atributos privados
    private int Red;
    private int Green;
    private int Blue;
    
    // Constructora
    public Color(int Red, int Green, int Blue) {
        this.Red = Red;
        this.Green = Green;
        this.Blue = Blue;
    }
    
    // Getters & Setters
    public int getRed() {
        return Red;
    }
    
    public void setRed(int Red) {
        this.Red = Red;
    }
    
    public int getGreen() {
        return Green;
    }
    
    public void setGreen(int Green) {
        this.Green = Green;
    }
    
    public int getBlue() {
        return Blue;
    }
    
    public void setBlue(int Blue) {
        this.Blue = Blue;
    }
}
