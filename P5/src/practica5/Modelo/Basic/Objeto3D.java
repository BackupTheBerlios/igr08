/** Clase Objeto3D
 * De cada objeto almacenaremos los siguientes campos:
 *    - Id: Identifica al objeto en cuestion que queremos modificar
 *    - Color: Color del objeto en cuestion.
 *    - matriz: Matriz de traformacion afin aplicado al objeto.
 */
package practica5.Modelo.Basic;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;

public class Objeto3D {
    
    // Atributos privados
    protected int id;
    protected Color color;
    protected TAfin matriz;
    protected int tipoMalla;
    protected GL gl;
    protected boolean normalesEnabled;
    protected boolean modificado;
        
    protected boolean baldosas;
    protected boolean texturizado;
    
    protected Texture[] textura;
    protected Texture texturaAct;

    
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
    
    // Constructora
    public Objeto3D() {
        color = new Color(0, 1, 0);
        tipoMalla = Malla.GL_POLYGON;
        normalesEnabled = false;
        matriz = new TAfin();
        modificado = false;
                
        baldosas = false;
        texturizado = false;
        
        textura = new Texture[5];
        //for (int i=0; i<5; i++)
           // textura[i] = TextureIO.newTexture(Cargar_Imagen("practica5/Images/" + Malla.nombreTexturas[i]), true);
      
        texturaAct = textura[0];
        //textura = TextureIO.newTexture(Cargar_Imagen("practica5/Images/" + Malla.nombreTexturas[1]), true);
    }
    
    
    // Metodo virtual
    public void dibuja(GL gl) {
        //gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
    }
    
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
    
    public void setColor3d(double Red, double Green, double Blue) {
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
    
    public void setNormalesEnabled(boolean b){
        this.normalesEnabled = b;
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
    
    public BufferedImage Cargar_Imagen(String nombre) {
        
        URL url=null;
        try {
            url = getClass().getClassLoader().getResource(nombre);
            
            return ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
            System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
            System.exit(0);
            return null;
        }
    }
    
    public void setTexturizado() {
        if (texturizado)
            texturizado = false;
        else
            texturizado = true;
    }
    
    public void textSelec(int i) {
        texturaAct = textura[i];
    }
    
}
