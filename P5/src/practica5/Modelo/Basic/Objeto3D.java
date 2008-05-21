package practica5.Modelo.Basic;

import java.net.URL;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import com.sun.opengl.util.texture.Texture;

public class Objeto3D {
    
    // Atributos privados
    protected int id;
    protected Color color;
    protected TAfin matriz;
    protected int tipoMalla;
    protected GL gl;
    protected boolean normalizado;
    protected boolean modificado;
        
    protected boolean baldosas;
    protected boolean texturizado;
    
    protected Texture textura;
    
    public static final int ESCENA = 0;
    public static final int MUEBLES = 1;
    public static final int LAMPARA = 2;
    public static final int ESTANTERIA = 3;
    public static final int PUERTA = 4;
    public static final int CUADRO = 5;
    public static final int SILLA = 6;
    public static final int MESA = 7;
    public static final int PERCHERO = 8;
    public static final int SUELO = 9;
    public static final int SOFA = 10;
    public static final int ESTATUA = 11;
    public static final int PERSIANA = 12;
    public static final int PERSONA = 13;
    public static final int CAMILLA = 14;
    public static final int POMO = 15;
    public static final int LIBROS = 16;
    public static final int COPA = 17;
    public static final int DONUT = 18;
    public static final int PLATO = 19;
    
    // Constructora
    public Objeto3D() {
        color = Color.gris;
        tipoMalla = Malla.GL_POLYGON;
        normalizado = false;
        matriz = new TAfin();
        modificado = false;
                
        baldosas = false;
        texturizado = false;
        
        textura = null;
    }
    
    
    // Metodo virtual
    public void dibuja(GL gl) { }
    
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
    
    public void setColor3d(int Red, int Green, int Blue) {
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
    
    public void setGL(GL gl){
        matriz.setGL(gl);
    }
    
    public GL getGL() {
        return this.gl;
    }
    
    public void setModificado() {
        modificado = true;
    }
  
    public void setBaldosas() {
        if (baldosas)
            baldosas = false;
        else
            baldosas = true;
    }
 
    public void setTexturizado() {
        if (texturizado)
            texturizado = false;
        else
            texturizado = true; 
    }
    
    public void setNormalizado() {
        if (normalizado)
            normalizado = false;
        else
            normalizado = true; 
    }
    
    public void setTextura(Texture text) {
        this.textura = text;
    }
    
    public Texture getTextura() {
        return textura;
    }
    
    public boolean isTexturizado() {
        return texturizado;
    }
}
