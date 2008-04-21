package practica5.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import com.sun.opengl.util.*;

import practica5.Controlador.GL3D;
import practica5.Modelo.Basic.Camara;
import practica5.Modelo.Basic.PuntoVector3D;
import practica5.Modelo.Basic.Color;
import practica5.Modelo.Basic.Malla;

public class Principal extends JFrame {

    //private static final String ETIQUETA_ACTIVA_NOMALES = "Activa Normales";
    //private static final String ETIQUETA_DESACTIVA_NOMALES = "Desactiva Normales";
    private static final Color color1 = new Color(1, 0, 0);
    private static final Color color2 = new Color(0, 1, 0);
    private static final Color color3 = new Color(0, 0, 1);
    private static final Color color4 = new Color(0, 0, 1);
    private static final Color color5 = new Color(1, 1, 0);
    private static final Color color6 = new Color(0, 1, 1);
    private static final Color color7 = new Color(1, 0, 1);
    private static final Color color8 = new Color(1, 1, 1);
   
    // Atributos
    private Container panel;
    private GLJPanel canvas;
    private GL3D escena;
    private JMenuBar menu;
    private JMenu archivo;
    private JMenuItem nuevo;
    private JMenuItem salir;
    private JMenu sobre;
    private JMenuItem autores;
    //private JButton botonCambiarModo;
    //private JButton botonDibujarNormales;
    private JPanel panelBotones;
   
    private ButtonGroup gBotones;
    private JRadioButton rbEscena;
    private JRadioButton rbMuebles;
    private JRadioButton rbLampara;
    
    private ButtonGroup gBotonesModo;
    private JRadioButton rbPuntos;
    private JRadioButton rbLineas;
    private JRadioButton rbSolido;
    
    private JCheckBox jcNormales;
    private JCheckBox jcTexturas;
       
    //private int tipoMalla;
    //private boolean entradaDatos;
    //private final Animator animacion;
    //private ArrayList<PuntoVector3D> perfil;
    //private Malla mallaActual;
    //private Camara camara;

    // Constructora
    public Principal() {

	// Titulo de la ventana y tama√±o
	setTitle("Practica 5");
	setSize(new Dimension(800, 600));
	super.setResizable(false);

	// Contenedor del editor
	panel = this.getContentPane();
	panel.setBackground(java.awt.Color.blue);
	panel.setLayout(new BorderLayout());

	// Generamos el menu
	menu = new JMenuBar();
	archivo = new JMenu("Archivo");
	nuevo = new JMenuItem("Nuevo");
	salir = new JMenuItem("Salir");
	sobre = new JMenu("Sobre...");
	autores = new JMenuItem("Autores");

	menu.add(archivo);
	archivo.add(nuevo);
	archivo.add(salir);
	menu.add(sobre);
	sobre.add(autores);
	setJMenuBar(menu);

	// Creamos los botones
	this.panelBotones = new JPanel();
	panelBotones.setLayout(new GridLayout(2, 2));
	panel.add(panelBotones, BorderLayout.SOUTH);

	//botonCambiarModo = new JButton("Cambiar Modo");
	//botonCambiarModo.setVisible(true);
	//panelBotones.add(botonCambiarModo);

	//botonDibujarNormales = new JButton(ETIQUETA_ACTIVA_NOMALES);
	//botonDibujarNormales.setVisible(true);
	//panelBotones.add(botonDibujarNormales);

        
	// Creamos el canvas de dibujo
	canvas = new GLJPanel();
	escena = new GL3D(800, 600);
        canvas.setLayout(null);
	canvas.addGLEventListener(escena);
	canvas.addMouseListener(new ManejadorRaton());
	canvas.addKeyListener(new ManejadorTeclado());
	panel.add(canvas, BorderLayout.CENTER);
        
        rbEscena = new JRadioButton("Escena");
        rbEscena.setForeground(java.awt.Color.green);
        rbEscena.setBackground(java.awt.Color.black);
        rbEscena.setBounds(10, 450, 80, 25);
        canvas.add(rbEscena);
    
        rbMuebles = new JRadioButton("Muebles");
        rbMuebles.setForeground(java.awt.Color.green);
        rbMuebles.setBackground(java.awt.Color.black);    
        rbMuebles.setBounds(10, 480, 120, 25);
        canvas.add(rbMuebles);

        rbLampara = new JRadioButton("L·mparas");
        rbLampara.setForeground(java.awt.Color.green);
        rbLampara.setBackground(java.awt.Color.black);
        rbLampara.setBounds(10, 510, 120, 25);
        canvas.add(rbLampara);
       
	gBotones = new ButtonGroup();
        gBotones.add(rbEscena);
        gBotones.add(rbMuebles);
        gBotones.add(rbLampara);  
        
        jcNormales = new JCheckBox("Normales");
        jcNormales.setForeground(java.awt.Color.white);
        jcNormales.setBackground(java.awt.Color.black);
        jcNormales.setBounds(700, 10, 80, 25);
        canvas.add(jcNormales);
        
        jcTexturas = new JCheckBox("Texturas");
        jcTexturas.setForeground(java.awt.Color.white);
        jcTexturas.setBackground(java.awt.Color.black);
        jcTexturas.setBounds(700, 40, 80, 25);
        canvas.add(jcTexturas);       

        rbPuntos = new JRadioButton("Puntos");
        rbPuntos.setForeground(java.awt.Color.pink);
        rbPuntos.setBackground(java.awt.Color.black);
        rbPuntos.setBounds(10, 10, 80, 25);
        canvas.add(rbPuntos);
    
        rbLineas = new JRadioButton("LÌneas");
        rbLineas.setForeground(java.awt.Color.pink);
        rbLineas.setBackground(java.awt.Color.black);    
        rbLineas.setBounds(10, 40, 120, 25);
        canvas.add(rbLineas);

        rbSolido = new JRadioButton("SÛlido");
        rbSolido.setForeground(java.awt.Color.pink);
        rbSolido.setBackground(java.awt.Color.black);
        rbSolido.setBounds(10, 70, 120, 25);
        canvas.add(rbSolido);
       
	gBotonesModo = new ButtonGroup();
        gBotonesModo.add(rbPuntos);
        gBotonesModo.add(rbLineas);
        gBotonesModo.add(rbSolido);  

	// Ponemos el oyente en los distintos elementos
	canvas.addKeyListener(new ManejadorTeclado());
	panel.addKeyListener(new ManejadorTeclado());
	//botonCambiarModo.addKeyListener(new ManejadorTeclado());
        //botonDibujarNormales.addKeyListener(new ManejadorTeclado());
        rbEscena.addKeyListener(new ManejadorTeclado());
        rbMuebles.addKeyListener(new ManejadorTeclado());
        rbLampara.addKeyListener(new ManejadorTeclado());
        jcNormales.addKeyListener(new ManejadorTeclado());
        jcTexturas.addKeyListener(new ManejadorTeclado());
        rbPuntos.addKeyListener(new ManejadorTeclado());
        rbLineas.addKeyListener(new ManejadorTeclado());
        rbSolido.addKeyListener(new ManejadorTeclado()); 
        
	// Accion por defecto al cerrar la ventana
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	// Evento Oyente para la barra de menu "nuevo"
	nuevo.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
	    }
	});

	// Evento Oyente para la barra de menu "Autores"
	autores.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(panel, "Iv·n Romero\nPedro S·nchez", "Autores",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	});

        // Evento Oyente para los Checks del tipo Modo
        rbPuntos.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		escena.getObjeto3D().setTipoMalla(Malla.GL_POINTS);
  		canvas.repaint();
	    }
	});
        
        rbLineas.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		escena.getObjeto3D().setTipoMalla(Malla.GL_LINES);
  		canvas.repaint();
	    }
	});
        
         rbSolido.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		escena.getObjeto3D().setTipoMalla(Malla.GL_POLYGON);
  		canvas.repaint();
	    }
	});       
        
        /*
	// Evento Oyente para el boton "Cambiar Modo"
	botonCambiarModo.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		Object[] opciones = {"Puntos", "Lineas", "Solidos"};
		int eleccion = JOptionPane.showOptionDialog(panel, "Cambiar Modo de Representacion a: ", "Datos de Entrada",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
		switch (eleccion) {
		    case 0:
			escena.getObjeto3D().setTipoMalla(Malla.GL_POINTS);
			break;
		    case 1:
			escena.getObjeto3D().setTipoMalla(Malla.GL_LINES);
			break;
		    case 2:
			escena.getObjeto3D().setTipoMalla(Malla.GL_POLYGON);
			break;
		    default:
			escena.getObjeto3D().setTipoMalla(Malla.GL_LINES);
			break;
		}
		canvas.repaint();
	    }
	});

	// Evento Oyente para el boton "Dibujar Normales"
	botonDibujarNormales.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		if (botonDibujarNormales.getText().equals(ETIQUETA_ACTIVA_NOMALES)) {
		    botonDibujarNormales.setText(ETIQUETA_DESACTIVA_NOMALES);
		} else {
		    botonDibujarNormales.setText(ETIQUETA_ACTIVA_NOMALES);
		}
	    }
	});
*/
	// A√±adimos un evento para la accion de salida
	addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		new Thread(new Runnable() {

		    public void run() {
			//animacion.stop();
			System.exit(0);
		    }
		}).start();
	    }
	});
    }

    // Eventos del Raton
    public class ManejadorRaton extends MouseAdapter {

	public void mousePressed(MouseEvent evento) {
	}

	public void mouseReleased(MouseEvent evento) {
	}

	public void mouseClicked(MouseEvent evento) {
	}
    }

    // Eventos con el teclado
    public class ManejadorTeclado extends KeyAdapter {

	public void keyPressed(KeyEvent evento) {

	    Camara camara = escena.getCamara();
	    switch (evento.getKeyCode()) {
		// Giros	   
		case KeyEvent.VK_Q:
		    camara.pitch(10);
		    escena.getObjeto3D().setColor(color2);
		    break;
		case KeyEvent.VK_W:
		    camara.pitch(-10);
		    break;
		case KeyEvent.VK_E:
		    camara.roll(5);
		    escena.getObjeto3D().setColor(color1);
		    break;
		case KeyEvent.VK_R:
		    camara.roll(-5);
		    break;
		case KeyEvent.VK_T:
		    camara.yaw(10);
		    escena.getObjeto3D().setColor(color3);
		    break;
		case KeyEvent.VK_Y:
		    camara.yaw(-10);
		    escena.getObjeto3D().setColor(color3);
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
	    System.out.println("tecla: " + evento.getKeyChar());
	}

	public void keyTyped(KeyEvent evento) {
	}

	public void keyReleased(KeyEvent evento) {
	}
    }

    // Metodo Main
    public static void main(String[] args) {
	Principal p5 = new Principal();
	p5.setVisible(true);
    }
}
