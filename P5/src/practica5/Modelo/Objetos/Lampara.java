package practica5.Modelo.Objetos;

import practica5.Modelo.Basic.*;
import practica5.Modelo.Luces.Luz;

public class Lampara extends ObjetoCompuesto3D {
    
    // Atributos privados
    private Cilindro lampara;
    private Luz luz;
    
    public Lampara(PuntoVector3D pos, double ori, int lightNumber) {
        
        // Brazo
        lampara = new Cilindro(5, 5, 20, 20, 30);
        lampara.setId(Objeto3D.LAMPARA);
        lampara.getMatriz().rotaX(90);
        lampara.getMatriz().trasladar(pos.getX(), pos.getY() + 15, pos.getZ());
        lampara.setColor(Color.amarilloClaro);
        this.addHijos(lampara);  
        
        // Tulipa
        lampara = new Cilindro(10, 30, 40, 20, 30);
        lampara.setId(Objeto3D.LAMPARA);
        lampara.getMatriz().rotaX(90);
        lampara.getMatriz().trasladar(pos.getX(), pos.getY(), pos.getZ());
        lampara.setColor(Color.violetaClaro);
        this.addHijos(lampara);
	
	// Luz
	PuntoVector3D dir = new PuntoVector3D(0, -1, 0);
	Color c = new Color(1,1,1);
	luz = new Luz(lightNumber, pos, dir, c, 45, 5);
	this.addHijos(luz);
	
    }
    
}
