package practica5.Modelo.Basic;

public class Color {

//    public static final Color rojo = new Color(1.0f , 0.0f, 0.0f);
//    public static final Color rosa = new Color(2.0f, 1.5f, 1.5f);
//    public static final Color marron = new Color(1.0f, 0.0f, 0.0f);
//    public static final Color verdeClaro = new Color(0.0f, 2.5f, 0.0f);
//    public static final Color verdeOscuro = new Color(0.0f, 1.0f, 0.0f);
//    public static final Color azulClaro = new Color(2.5f, 2.5f, 2.0f);
//    public static final Color azulOscuro = new Color(0.0f, 0.0f, 1.0f);
//    public static final Color amarilloClaro = new Color(1.0f, 1.0f, 0.0f);
//    public static final Color amarilloOscuro = new Color(1.0f, 1.0f, 0.0f);
//    public static final Color turquesaClaro = new Color(0.0f, 1.0f, 1.0f);
//    public static final Color turquesaOscuro = new Color(0.0f, 1.0f, 1.0f);
//    public static final Color violetaClaro = new Color(1.0f, 0.0f, 1.0f);
//    public static final Color violetaOscuro = new Color(1.0f, 0.0f, 1.0f);  
//    public static final Color blanco = new Color(2.5f, 2.5f, 2.5f);
//    public static final Color gris = new Color(1.5f, 2.0f, 1.5f);
//    public static final Color negro = new Color(0.0f, 0.0f, 0.0f);
    
     public static final Color rojo = new Color(1.0f , 0.0f, 0.0f);
    public static final Color rosa = new Color(1.0f, 0.5f, 0.5f);
    public static final Color marron = new Color(1.0f, 0.0f, 0.0f);
    public static final Color verdeClaro = new Color(0.0f, 0.7f, 0.0f);
    public static final Color verdeOscuro = new Color(0.0f, 0.5f, 0.0f);
    public static final Color azulClaro = new Color(0.8f, 0.8f, 0.75f);
    public static final Color azulOscuro = new Color(0.0f, 0.0f, 0.7f);
    public static final Color amarilloClaro = new Color(1.0f, 1.0f, 0.0f);
    public static final Color amarilloOscuro = new Color(1.0f, 1.0f, 0.0f);
    public static final Color turquesaClaro = new Color(0.0f, 0.8f, 0.8f);
    public static final Color turquesaOscuro = new Color(0.0f, 0.5f, 0.5f);
    public static final Color violetaClaro = new Color(1.0f, 0.0f, 1.0f);
    public static final Color violetaOscuro = new Color(0.8f, 0.0f, 0.8f);  
    public static final Color blanco = new Color(1.0f, 1.0f, 1.0f);
    public static final Color gris = new Color(0.75f, 1.0f, 0.75f);
    public static final Color negro = new Color(0.0f, 0.0f, 0.0f);
    
    // Atributos privados
    private float Red;
    private float Green;
    private float Blue;

    // Constructora
    public Color(float Red, float Green, float Blue) {
	this.Red = Red;
	this.Green = Green;
	this.Blue = Blue;
    }

    // Getters & Setters
    public float getRed() {
	return Red;
    }

    public void setRed(float Red) {
	this.Red = Red;
    }

    public float getGreen() {
	return Green;
    }

    public void setGreen(float Green) {
	this.Green = Green;
    }

    public float getBlue() {
	return Blue;
    }

    public void setBlue(float Blue) {
	this.Blue = Blue;
    }
}
