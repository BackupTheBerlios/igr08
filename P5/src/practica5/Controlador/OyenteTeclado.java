package practica5.Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.GLJPanel;
import practica5.Modelo.Basic.*;
import practica5.Modelo.Basic.Objeto3D;
import practica5.Modelo.Basic.Objeto3D;
import practica5.Vista.*;

public class OyenteTeclado implements KeyListener {

    private int tipo;
    private GL3D escena;
    private GLJPanel canvas;
    private boolean[] seleccionados;
    private int veces;

    public OyenteTeclado(GL3D escena, GLJPanel canvas) {
	this.escena = escena;
	this.canvas = canvas;
	this.tipo = Objeto3D.ESCENA;

	this.veces = 11;
    }

    public void keyPressed(KeyEvent e) {

	switch (tipo) {

	    case Objeto3D.ESCENA:
		modificarEscena(e);
		break;

	    case Objeto3D.MUEBLES:
		modificarMuebles(e);
		break;

	    case Objeto3D.LAMPARA:
		modificarLampara(e);
		break;

	    case Objeto3D.PERSIANA:
		modificarPersiana(e);
		break;

	    case Objeto3D.PERSONA:
		modificarPersona(e);
		break;
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
	    // Zoom
	    case KeyEvent.VK_X:
		camara.zoomIn();
		break;
	    case KeyEvent.VK_C:
		camara.zoomOut();
		break;
	    case KeyEvent.VK_1:
		escena.setVista(GL3D.VISTA_FRONTAL);
		break;
	    case KeyEvent.VK_2:
		escena.setVista(GL3D.VISTA_LATERAL);
		break;
	    case KeyEvent.VK_3:
		escena.setVista(GL3D.VISTA_CENITAL);
		break;
	    case KeyEvent.VK_4:
		escena.setVista(GL3D.VISTA_POR_DEFECTO);
		break;
	    case KeyEvent.VK_7:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().rotaX(1);
		break;
	    case KeyEvent.VK_8:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().rotaX(-1);
		break;
	    case KeyEvent.VK_0:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().rotaY(1);
		break;
	    case KeyEvent.VK_9:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().rotaY(-1);
		break;
	    case KeyEvent.VK_5:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().rotaZ(1);
		break;
	    case KeyEvent.VK_6:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().rotaZ(-1);
		break;
	    case KeyEvent.VK_F:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaX(1.2);
		break;
	    case KeyEvent.VK_V:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaX(0.8);
		break;
	    case KeyEvent.VK_G:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaY(1.2);
		break;
	    case KeyEvent.VK_B:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaY(0.8);
		break;
	    case KeyEvent.VK_H:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaZ(1.2);
		break;
	    case KeyEvent.VK_N:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaZ(0.8);
		break;
	    case KeyEvent.VK_J:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaXYZ(1.2);
		break;
	    case KeyEvent.VK_M:
		escena.getObjeto3D().setModificado();
		escena.getObjeto3D().getMatriz().escalaXYZ(0.8);
		break;
	    case KeyEvent.VK_K:
		escena.cambiaLuzAmbiente();
		break;
	    case KeyEvent.VK_L:
		escena.cambiaLuzDifusa();
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

	seleccionados = new boolean[escena.getObjeto3D().getHijos().size()];
	for (int i = 0; i < escena.getObjeto3D().getHijos().size(); i++) {
	    if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.MUEBLES) {
		seleccionados[i] = true;
	    }
	}

	teclado(e);
	canvas.repaint();

    }

    public void modificarLampara(KeyEvent e) {

	seleccionados = new boolean[escena.getObjeto3D().getHijos().size()];
	for (int i = 0; i < escena.getObjeto3D().getHijos().size(); i++) {
	    if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.LAMPARA) {
		seleccionados[i] = true;
	    }
	}

	teclado(e);
	canvas.repaint();
    }

    public void modificarPersiana(KeyEvent e) {

	boolean enc = false;
	int i = -1;
	while (i < escena.getObjeto3D().getHijos().size() && !enc) {
	    i++;
	    if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.PERSIANA) {
		enc = true;
	    }
	}

	switch (e.getKeyCode()) {
	    case KeyEvent.VK_UP:
		if (veces <= 11) {
		    escena.getObjeto3D().getHijos().get(i).setModificado();
		    escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 5, 0);
		    veces++;
		} else {
		    System.out.print("La persiana no puede subir mas");
		}
		break;
	    case KeyEvent.VK_DOWN:
		if (veces >= 0) {
		    escena.getObjeto3D().getHijos().get(i).setModificado();
		    escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, -5, 0);
		    veces--;
		} else {
		    System.out.print("La persiana no puede bajar mas");
		}
		break;
	}

	canvas.repaint();
    }

    public void modificarPersona(KeyEvent e) {

	boolean enc = false;
	int i = -1;

	while (i < escena.getObjeto3D().getHijos().size() && !enc) {
	    i++;
	    if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.PERSONA) {
		enc = true;
	    }
	}

	Camara camara = escena.getCamara();
	switch (e.getKeyCode()) {

	    case KeyEvent.VK_RIGHT:
		escena.getObjeto3D().getHijos().get(i).setModificado();
		camara.desliza(new PuntoVector3D(-10, 0, 0, 1));
		escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(-10, 0, 0);
		escena.desplPersonaX -= 10;
		break;

	    case KeyEvent.VK_LEFT:
		escena.getObjeto3D().getHijos().get(i).setModificado();
		camara.desliza(new PuntoVector3D(10, 0, 0, 1));
		escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(10, 0, 0);
		escena.desplPersonaX += 10;
		break;

	    case KeyEvent.VK_UP:
		escena.getObjeto3D().getHijos().get(i).setModificado();
		camara.desliza(new PuntoVector3D(0, 0, -10, 1));
		escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 0, -10);
		escena.desplPersonaZ -= 10;
		break;

	    case KeyEvent.VK_DOWN:
		escena.getObjeto3D().getHijos().get(i).setModificado();
		camara.desliza(new PuntoVector3D(0, 0, 10, 1));
		escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 0, 10);
		escena.desplPersonaZ += 10;
		break;

	    case KeyEvent.VK_A:
		escena.getObjeto3D().getHijos().get(i).setModificado();
		camara.desliza(new PuntoVector3D(0, -10, 0, 1));
		escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, -10, 0);
		escena.desplPersonaY -= 10;
		break;

	    case KeyEvent.VK_Z:
		escena.getObjeto3D().getHijos().get(i).setModificado();
		camara.desliza(new PuntoVector3D(0, 10, 0, 1));
		escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 10, 0);
		escena.desplPersonaY += 10;
		break;
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
	}
	canvas.repaint();
    }

    public void teclado(KeyEvent e) {

	for (int i = 0; i < seleccionados.length; i++) {
	    if (seleccionados[i]) {
		escena.getObjeto3D().getHijos().get(i).setModificado();
		switch (e.getKeyCode()) {

		    case KeyEvent.VK_RIGHT:
			escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(10, 0, 0);
			break;
		    case KeyEvent.VK_LEFT:
			escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(-10, 0, 0);
			break;
		    case KeyEvent.VK_UP:
			escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 0, 10);
			break;
		    case KeyEvent.VK_DOWN:
			escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 0, -10);
			break;
		    case KeyEvent.VK_A:
			escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, -10, 0);
			break;
		    case KeyEvent.VK_Z:
			escena.getObjeto3D().getHijos().get(i).getMatriz().trasladar(0, 10, 0);
			break;
		    case KeyEvent.VK_Q:
			escena.getObjeto3D().getHijos().get(i).getMatriz().rotaX(1);
			break;
		    case KeyEvent.VK_W:
			escena.getObjeto3D().getHijos().get(i).getMatriz().rotaX(-1);
			break;
		    case KeyEvent.VK_E:
			escena.getObjeto3D().getHijos().get(i).getMatriz().rotaY(1);
			break;
		    case KeyEvent.VK_R:
			escena.getObjeto3D().getHijos().get(i).getMatriz().rotaY(-1);
			break;
		    case KeyEvent.VK_T:
			escena.getObjeto3D().getHijos().get(i).getMatriz().rotaZ(1);
			break;
		    case KeyEvent.VK_Y:
			escena.getObjeto3D().getHijos().get(i).getMatriz().rotaZ(-1);
			break;
		    case KeyEvent.VK_S:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaX(1.2);
			break;
		    case KeyEvent.VK_D:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaX(0.8);
			break;
		    case KeyEvent.VK_F:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaY(1.2);
			break;
		    case KeyEvent.VK_G:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaY(0.8);
			break;
		    case KeyEvent.VK_H:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaZ(1.2);
			break;
		    case KeyEvent.VK_J:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaZ(0.8);
			break;
		    case KeyEvent.VK_K:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaXYZ(1.2);
			break;
		    case KeyEvent.VK_L:
			escena.getObjeto3D().getHijos().get(i).getMatriz().escalaXYZ(0.8);
			break;
		    // Extra
		    case KeyEvent.VK_SHIFT:
			escena.cambiaCamara();
		}
	    }
	}
    }
}
