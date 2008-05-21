package practica5.Modelo.Objetos;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Silla extends ObjetoCompuesto3D{
    
    private Tablero tabla;
    private Malla pata;
    
    public Silla(PuntoVector3D pos, double ori, GL gl, Texture[] texturas) {
        
        super.setGL(gl);
        int valorX, valorZ;
        int valorY = -25;
        
        // Asiento
        tabla = new Tablero(35, 35, 5, 3, 3, 3, gl);
        tabla.setId(Objeto3D.SILLA);
        tabla.setTextura(texturas[1]);
        tabla.getMatriz().rotaXM(90);
        tabla.getMatriz().rotaYM(ori);
        tabla.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        this.addHijos(tabla);  
        
        // Respaldo 
        tabla = new Tablero(35, 35, 5, 3, 3, 3, gl);
        tabla.setId(Objeto3D.SILLA);
        tabla.setTextura(texturas[1]);
        tabla.getMatriz().rotaYM(ori);
        tabla.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        this.addHijos(tabla);  
        
        // Pata 1
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(3, 30, 0));
        perfil.add(new PuntoVector3D(3, 20, 0));
        perfil.add(new PuntoVector3D(3, 10, 0));
        perfil.add(new PuntoVector3D(3, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 20);
        
        if (ori >= 0) {
            valorX = 10;
            valorZ = -20;
        }
        else {
            valorX= -10;
            valorZ = 20;
        }
        
        pata = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pata.getMatriz().rotaYM(ori);
        pata.setTextura(texturas[1]);
        pata.getMatriz().trasladar(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
        pata.setId(Objeto3D.SILLA);
        pata.setColor(color.violetaOscuro);
        this.addHijos(pata);
        
        if (ori >= 0) {
            valorX = 0;
            valorZ = -5;
        }
        else {
            valorX= -0;
            valorZ = 5;
        }        
        
        pata = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pata.getMatriz().rotaYM(ori);
        pata.getMatriz().trasladar(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
        pata.setId(Objeto3D.SILLA);
        pata.setTextura(texturas[1]);
        pata.setColor(color.violetaOscuro);
        this.addHijos(pata);

        if (ori >= 0) {
            valorX = 0;
            valorZ = -40;
        }
        else {
            valorX= -0;
            valorZ = 40;
        }     
        
        pata = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pata.getMatriz().rotaYM(ori);
        pata.getMatriz().trasladar(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
        pata.setId(Objeto3D.SILLA);
        pata.setTextura(texturas[1]);
        pata.setColor(color.violetaOscuro);
        this.addHijos(pata);
        
        if (ori >= 0) {
            valorX = -10;
            valorZ = -15;
        }
        else {
            valorX= 13;
            valorZ = 17;
        }     
        
        pata = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pata.getMatriz().rotaYM(ori);
        pata.getMatriz().trasladar(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
        pata.setId(Objeto3D.SILLA);
        pata.setTextura(texturas[1]);
        pata.setColor(color.violetaOscuro);
        this.addHijos(pata); 
    }
    
}
