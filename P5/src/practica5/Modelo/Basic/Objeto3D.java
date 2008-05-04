/** Clase Objeto3D
 * De cada objeto almacenaremos los siguientes campos:
 *    - Id: Identifica al objeto en cuestion que queremos modificar
 *    - Color: Color del objeto en cuestion.
 *    - matriz: Matriz de traformacion afin aplicado al objeto.
 */
package practica5.Modelo.Basic;

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
    
    public static final int ESCENA = 0;
    public static final int MUEBLES = 1;
    public static final int LAMPARA = 2;
    public static final int ESTANTERIA = 3;
    public static final int PUERTA = 4;
    public static final int CUADRO = 5;
    public static final int SILLA = 6;
    public static final int MESA = 7;
    public static final int PERCHERO = 8;
           
        
    // Constructora
    public Objeto3D() {
        color = new Color(0, 1, 0);
        tipoMalla = Malla.GL_POLYGON;
        normalesEnabled = false;
        matriz = new TAfin();
        modificado = false;
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
}
