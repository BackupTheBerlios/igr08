package practica4.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.opengl.*;
import com.sun.opengl.util.*;

import practica4.Controlador.GL3D;
import practica4.Modelo.PuntoVector3D;
import practica4.Modelo.MallaPorRevolucion;
import practica4.util.Calculos;
import java.util.ArrayList;
import practica4.Modelo.Toro;

public class Principal extends JFrame {

    // Atributos
    private Container panel;
    private GLJPanel canvas;
    private GL3D escena;
    private JMenuBar menu;
    private JMenu archivo;
    private JMenuItem nuevo;
    private JMenuItem salir;
    private JMenu malla;
    private JMenuItem revolucion;
    private JMenuItem extrusion;
    private JMenu sobre;
    private JMenuItem autores;
    private JButton botonDefinirPuntos;
    private JButton botonGenerarMallaRevolucion;
    private JButton botonGenerarMallaExtrusion;
    private JButton botonAplicarSplines;
    private int tipoMalla;
    private boolean entradaDatos;
    private final Animator animacion;
    private ArrayList<PuntoVector3D> perfil;
    private MallaPorRevolucion mallaRevolucion;
    private Toro mallaExtrusion;

    // Constructora
    public Principal() {

	// Titulo de la ventana y tama�o
	setTitle("Pr�ctica 4");
	setSize(new Dimension(400, 475));
	super.setResizable(false);

	// Contenedor del editor
	panel = this.getContentPane();
	panel.setBackground(Color.blue);
	panel.setLayout(null);

	// Generamos el menu
	menu = new JMenuBar();
	archivo = new JMenu("Archivo");
	nuevo = new JMenuItem("Nuevo");
	salir = new JMenuItem("Salir");
	malla = new JMenu("Malla por");
	revolucion = new JMenuItem("Revoluci�n");
	extrusion = new JMenuItem("Extrusi�n");
	sobre = new JMenu("Sobre..");
	autores = new JMenuItem("Autores");

	menu.add(archivo);
	archivo.add(nuevo);
	archivo.add(salir);

	menu.add(malla);
	malla.add(revolucion);
	malla.add(extrusion);

	menu.add(sobre);
	sobre.add(autores);

	setJMenuBar(menu);

	// Creamos los botones
	botonDefinirPuntos = new JButton("Definir Perfil");
	botonDefinirPuntos.setBounds(0, 400, 100, 25);
	botonDefinirPuntos.setVisible(true);
	panel.add(botonDefinirPuntos);

	botonAplicarSplines = new JButton("Aplicar BSplines");
	botonAplicarSplines.setBounds(100, 400, 100, 25);
	botonAplicarSplines.setVisible(true);
	panel.add(botonAplicarSplines);

	botonGenerarMallaRevolucion = new JButton("Revoluci�n");
	botonGenerarMallaRevolucion.setBounds(200, 400, 100, 25);
	botonGenerarMallaRevolucion.setVisible(true);
	panel.add(botonGenerarMallaRevolucion);

	botonGenerarMallaExtrusion = new JButton("Extrusi�n");
	botonGenerarMallaExtrusion.setBounds(300, 400, 100, 25);
	botonGenerarMallaExtrusion.setVisible(true);
	panel.add(botonGenerarMallaExtrusion);

	// Definimos el tipo de malla actual a representar
	this.tipoMalla = 0;

	// Definimos un perfil por defecto
	this.perfil = new ArrayList<PuntoVector3D>();
	this.perfil.add(new PuntoVector3D(250, 330, 0, 1));
	this.perfil.add(new PuntoVector3D(250, 300, 0, 1));
	this.perfil.add(new PuntoVector3D(230, 270, 0, 1));
	this.perfil.add(new PuntoVector3D(200, 240, 0, 1));
	this.perfil.add(new PuntoVector3D(200, 210, 0, 1));
	this.perfil.add(new PuntoVector3D(200, 180, 0, 1));
	this.perfil.add(new PuntoVector3D(200, 150, 0, 1));
	this.perfil.add(new PuntoVector3D(230, 120, 0, 1));

	// Creamos el canvas de dibujo
	canvas = new GLJPanel();
	escena = new GL3D(400, 400);
	canvas.addGLEventListener(escena);
	canvas.setBounds(0, 0, 400, 400);
	canvas.addMouseListener(new ManejadorRaton());
	panel.add(canvas);

	// Transformamos el perfil a coordenadas de la escena
	this.perfil = escena.transformarPerfil(this.perfil);
	escena.setPerfil(this.perfil);

	// Animaci�n de la escena
	animacion = new Animator(canvas);
	animacion.start();

	// Entrada de datos desactivada
	entradaDatos = false;

	// Acci�n por defecto al cerrar la ventana
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	// Evento Oyente para la barra de menu "nuevo"         
	nuevo.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		perfil = new ArrayList<PuntoVector3D>();
		escena.actualizarPerfil(tipoMalla, perfil);
	    }
	});

	// Evento Oyente para la barra de menu "Autores"         
	autores.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "Iv�n Romero\nPedro S�nchez", "Autores",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	});

	// Evento Oyente para la barra de menu "Malla por Revoluci�n"
	revolucion.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		tipoMalla = 0;
		escena.actualizarPerfil(tipoMalla, perfil);
	    }
	});

	// Evento Oyente para la barra de menu "Malla por Extrusi�n"
	extrusion.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		escena.setTipo(1);
	    }
	});

	// Evento Oyente para el boton "Definir Perfil"         
	botonDefinirPuntos.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		perfil = new ArrayList<PuntoVector3D>();
		escena.setPerfil(perfil);
		entradaDatos = true;
	    }
	});

	// Evento Oyente para el boton "Aplicar Splines"         
	botonAplicarSplines.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		String dato = JOptionPane.showInputDialog(null, "Numero de puntos de control. Rango[" + perfil.size() + "...N]",
			"Datos de Entrada", 1);
		if (dato != null) {
		    int num = Integer.parseInt(dato);
		    perfil = new Calculos().calculaPuntosBSplines(perfil, num);
		    if (perfil.size() > num) {
			ArrayList<PuntoVector3D> perfilAux = new ArrayList<PuntoVector3D>();
			for (int i = 0; i < num - 2; i++) {
			    perfilAux.add(perfil.get(i));
			}
			perfil = (ArrayList<PuntoVector3D>) perfilAux.clone();
		    }
		    escena.setPerfil(perfil);
		    entradaDatos = false;
		}
	    }
	});

	// Evento Oyente para el boton "Malla Revoluci�n"         
	botonGenerarMallaRevolucion.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		mallaRevolucion = new MallaPorRevolucion(perfil, 0.5f);

		escena.actualizarMallaRev(tipoMalla, mallaRevolucion);
		escena.setGenerado(true);
		entradaDatos = false;
	    }
	});

	// Evento Oyente para el boton "Malla Extrusi�n"         
	botonGenerarMallaExtrusion.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		mallaExtrusion = new Toro(50.0f, 10.0f, 20, 10);
		

		escena.actualizarMallaExt(1, mallaExtrusion);
		escena.setGenerado(true);
		entradaDatos = false;
	    }
	});
	// A�adimos un evento para la acci�n de salida
	addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		new Thread(new Runnable() {

		    public void run() {
			animacion.stop();
			System.exit(0);
		    }
		}).start();
	    }
	});
    }

    // Eventos del Rat�n
    public class ManejadorRaton extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent evento) {
	    if (entradaDatos) {
		perfil.add(escena.convertirPuntoToPixel(new PuntoVector3D(evento.getX(), 400 - evento.getY(), 0, 1)));
		escena.setPerfil(perfil);
	    }
	}

	@Override
	public void mouseReleased(MouseEvent evento) {
	}

	@Override
	public void mouseClicked(MouseEvent evento) {
	}
    }

    // M�tood Main
    public static void main(String[] args) {
	Principal p4 = new Principal();
	p4.setVisible(true);
    }
}
