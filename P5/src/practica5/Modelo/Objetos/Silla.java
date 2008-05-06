package practica5.Modelo.Objetos;

import java.util.ArrayList;
import javax.media.opengl.GL;
import practica5.Modelo.Basic.*;
import practica5.util.Calculos;

public class Silla extends ObjetoCompuesto3D{
    
    private Tablero tabla;
    private Malla pata;
    
    public Silla(PuntoVector3D pos, double ori, GL gl) {
        
        super.setGL(gl);
        int valorX, valorZ;
        int valorY = -25;
        
        // Asiento
        tabla = new Tablero(50, 50, 10, 3, 3, 3);
        tabla.setId(Objeto3D.MUEBLES);
        tabla.setGL(gl);
        tabla.getMatriz().rotar(90, 1.0, 0.0, 0.0);
        tabla.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        tabla.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
        tabla.setColor(color.violetaOscuro);
        this.addHijos(tabla);  
        
        // Respaldo
        tabla = new Tablero(50, 50, 10, 3, 3, 3);
        tabla.setId(Objeto3D.MUEBLES);
        tabla.setGL(gl);
        tabla.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        tabla.getMatriz().trasladarM(pos.getX(), pos.getY(), pos.getZ());
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
        pata.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        pata.getMatriz().trasladarM(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
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
        pata.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        pata.getMatriz().trasladarM(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
        pata.setColor(color.violetaOscuro);
        this.addHijos(pata);

        if (ori >= 0) {
            valorX = 0;
            valorZ = -30;
        }
        else {
            valorX= -0;
            valorZ = 30;
        }     
        
        pata = new MallaPorRevolucion(perfil, 4, 1.57, gl);
        pata.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        pata.getMatriz().trasladarM(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
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
        pata.getMatriz().rotarM(ori, 0.0, 1.0, 0.0);
        pata.getMatriz().trasladarM(pos.getX() + valorX, pos.getY() + valorY, pos.getZ() + valorZ);
        pata.setColor(color.violetaOscuro);
        this.addHijos(pata); 
    }
    
}
