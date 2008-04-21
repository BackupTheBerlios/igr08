package practica5.Vista;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class grupoBotones extends JPanel {

    private JRadioButton rbEscena;
    private JRadioButton rbMuebles;
    private JRadioButton rbLampara;
    private ButtonGroup grupo;

    public grupoBotones() {
	super();
	initGUI();
    }

    private void initGUI() {
	try {
	    {
		rbEscena = new JRadioButton();
		this.add(rbEscena);
		rbEscena.setText("Escena");
	    }
	    {
		rbMuebles = new JRadioButton();
		this.add(rbMuebles);
		rbMuebles.setText("Muebles");
	    }
	    {
		rbLampara = new JRadioButton();
		this.add(rbLampara);
		rbLampara.setText("Lampara");
	    }
	    {
		grupo = new ButtonGroup();
		grupo.add(rbEscena);
		grupo.add(rbMuebles);
		grupo.add(rbLampara);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}


