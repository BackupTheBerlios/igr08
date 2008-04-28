package practica5.Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GLJPanel;
import practica5.Modelo.Basic.*;
import practica5.Modelo.Basic.Objeto3D;
import practica5.Modelo.Basic.Objeto3D;
import practica5.Modelo.Objetos.Muebles;
import practica5.Vista.*;

public class OyenteTeclado implements KeyListener {
    
    private int tipo;
    private GL3D escena;
    private GLJPanel canvas;
    
    public OyenteTeclado(GL3D escena, GLJPanel canvas) {
        this.escena = escena;
        this.canvas = canvas;
        this.tipo = Objeto3D.ESCENA;
    }
    
    public void keyPressed(KeyEvent e) {
        
        switch (tipo) {
            
            case Objeto3D.ESCENA: modificarEscena(e); break;
            case Objeto3D.MUEBLES: modificarMuebles(e); break;
            case Objeto3D.LAMPARA: modificarLampara(e); break;
            
        }
        
        System.out.println("tecla: " + e.getKeyChar());
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void seleccionaObjeto(int tipo) {
        this.tipo = tipo;
    }
    
    public void modificarEscena(KeyEvent e) {
        Camara camara = escena.getCamara();
        switch (e.getKeyCode()) {
            // Giros
            case KeyEvent.VK_Q:
                camara.pitch(5);
                break;
            case KeyEvent.VK_W:
                camara.pitch(-5);
                break;
            case KeyEvent.VK_E:
                camara.roll(5);
                break;
            case KeyEvent.VK_R:
                camara.roll(-5);
                break;
            case KeyEvent.VK_T:
                camara.yaw(5);
                break;
            case KeyEvent.VK_Y:
                camara.yaw(-5);
                break;
                // Traslaciones
            case KeyEvent.VK_RIGHT:
                camara.desliza(new PuntoVector3D(10, 0, 0, 1));
                break;
            case KeyEvent.VK_LEFT:
                camara.desliza(new PuntoVector3D(-10, 0, 0, 1));
                break;
            case KeyEvent.VK_UP:
                camara.desliza(new PuntoVector3D(0, 10, 0, 1));
                break;
            case KeyEvent.VK_DOWN:
                camara.desliza(new PuntoVector3D(0, -10, 0, 1));
                break;
            case KeyEvent.VK_A:
                camara.desliza(new PuntoVector3D(0, 0, 10, 1));
                break;
            case KeyEvent.VK_Z:
                camara.desliza(new PuntoVector3D(0, 0, -10, 1));
                break;
                // Extra
            case KeyEvent.VK_SHIFT:
                escena.cambiaCamara();
            default:
                System.out.print("Ignorada ");
                break;
        }
        canvas.repaint();
    }
    
    public void modificarMuebles(KeyEvent e) {

        boolean enc = false;
        int i = 0;
        while (i<escena.getObjeto3D().getHijos().size() && !enc) {
            if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.MUEBLES) {
                enc = true;
            }
            i++;
        }
        i--;
 
   
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(10, 0, 0);
                //escena.getObjeto3D().getHijos().get(i).trasladar(10, 0, 0);
                break;
            case KeyEvent.VK_LEFT:
                escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(-10, 0, 0);
                break;
            case KeyEvent.VK_UP:
                escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 10, 0);
                break;
            case KeyEvent.VK_DOWN:
                escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, -10, 0);
                break;
                
        }
         System.out.print(escena.getObjeto3D().getHijos().get(i).getMatriz().imprime());
        canvas.repaint();
    }
    
    public void modificarLampara(KeyEvent e) {
        Camara camara = escena.getCamara();
        switch (e.getKeyCode()) {
        }
    }
}    
