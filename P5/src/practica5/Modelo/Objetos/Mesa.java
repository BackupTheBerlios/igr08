package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Mesa extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Tablero tabla;
    private Malla pataMesa;
    private Cilindro pata;
    
    // Constructora
    public Mesa(PuntoVector3D pos, double ori, GL gl) {
        
        // Tabla de la mesa
        tabla = new Tablero(150, 80, 10, 3, 3, 3);
        tabla.setId(Objeto3D.MUEBLES);
        tabla.setGL(gl);
        tabla.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tabla.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        tabla.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        this.addHijos(tabla);  
        
        
        // Pata
        ArrayList<PuntoVector3D> perfil = new ArrayList<PuntoVector3D>();
        perfil.add(new PuntoVector3D(10, 50, 0));
        perfil.add(new PuntoVector3D(10, 40, 0));
        perfil.add(new PuntoVector3D(10, 30, 0));
        perfil.add(new PuntoVector3D(10, 20, 0));
        perfil.add(new PuntoVector3D(10, 10, 0));
        perfil.add(new PuntoVector3D(10, 0, 0));

        perfil = new Calculos().calculaPuntosBSplines(perfil, 20);
        
        pataMesa = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pataMesa.getMatriz().trasladarM(pos.getX() + 50, pos.getY() - 50, pos.getZ() - 20);
        pataMesa.setColor(color.violetaOscuro);
        this.addHijos(pataMesa);
                
        
        // Pata por Cilindro
        pata = new Cilindro(10, 10, 50, 20, 30);
        pata.setId(Objeto3D.MESA);
        pata.setGL(gl);
        pata.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        pata.getMatriz().trasladarM(pos.getX() + 50, pos.getY(), pos.getZ() - 20);
        pata.setColor(color.violetaOscuro);
        //this.addHijos(pata);  
        

        
        
    }
    
}
