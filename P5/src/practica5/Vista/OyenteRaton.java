package practica5.Vista;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.media.opengl.GLJPanel;
import practica5.Controlador.GL3D;

public class OyenteRaton implements MouseListener {

    private GL3D escena;
    private GLJPanel canvas;

    public OyenteRaton(GL3D escena, GLJPanel canvas) {
	this.escena = escena;
	this.canvas = canvas;
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("Clicked");
    }

    public void mousePressed(MouseEvent e) {
	System.out.println("pressed");
    }

    public void mouseReleased(MouseEvent e) {
	System.out.println("Released");
    }

    public void mouseEntered(MouseEvent e) {
	System.out.println("Entered");
    }

    public void mouseExited(MouseEvent e) {
	System.out.println("Exitend");
    }
}
