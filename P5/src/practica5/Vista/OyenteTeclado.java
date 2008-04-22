package practica5.Vista;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GLJPanel;
import practica5.Controlador.GL3D;
import practica5.Modelo.Basic.Camara;
import practica5.Modelo.Basic.PuntoVector3D;

public class OyenteTeclado implements KeyListener {

    private GL3D escena;
    private GLJPanel canvas;

    public OyenteTeclado(GL3D escena, GLJPanel canvas) {
	this.escena = escena;
	this.canvas = canvas;
    }

    public void keyPressed(KeyEvent e) {

	Camara camara = escena.getCamara();
	switch (e.getKeyCode()) {
	    // Giros	   
	    case KeyEvent.VK_Q:
		camara.pitch(5);
		escena.getObjeto3D().setColor(Principal.color2);
		break;
	    case KeyEvent.VK_W:
		camara.pitch(-5);
		break;
	    case KeyEvent.VK_E:
		camara.roll(5);
		escena.getObjeto3D().setColor(Principal.color1);
		break;
	    case KeyEvent.VK_R:
		camara.roll(-5);
		break;
	    case KeyEvent.VK_T:
		camara.yaw(5);
		escena.getObjeto3D().setColor(Principal.color3);
		break;
	    case KeyEvent.VK_Y:
		camara.yaw(-5);
		escena.getObjeto3D().setColor(Principal.color3);
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
	System.out.println("tecla: " + e.getKeyChar());
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

