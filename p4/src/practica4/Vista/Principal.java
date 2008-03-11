package practica4.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.opengl.*;
import com.sun.opengl.util.*;

import practica4.Controlador.GL3D;

public class Principal extends JFrame {

    // Atributos
    private Container panel;
    private GLJPanel canvas;
    private GL3D escena;

    private JMenuBar menu;
    private JMenu opciones;
    private JMenuItem revolucion;
    private JMenuItem extrusion;
    private JMenu sobre;
    private JMenuItem autores;
    
    private final Animator animacion;
        
    // Constructora
    public Principal() {

	// Titulo de la ventana y tamaño
	setTitle("Práctica 4");
	setSize(new Dimension(400, 400));
	super.setResizable(false);

	// Contenedor del editor
	panel = this.getContentPane();
	panel.setBackground(Color.black);
	panel.setLayout(null);

        // Generamos el menu
	menu = new JMenuBar();
        opciones = new JMenu("Opciones");
	revolucion = new JMenuItem("Malla por Revolución");
	extrusion = new JMenuItem("Malla por Extrusión");
        sobre = new JMenu("Sobre..");
        autores = new JMenuItem("Autores");
        
        menu.add(opciones);
	opciones.add(revolucion);
	opciones.add(extrusion);
        menu.add(sobre);
        sobre.add(autores);
        
        setJMenuBar(menu);
        
	// Creamos el canvas de dibujo
	canvas = new GLJPanel();
	escena = new GL3D();
	canvas.addGLEventListener(escena);
	canvas.setBounds(0, 0, 400, 400);
	panel.add(canvas);
        
        // Animación de la escena
        animacion = new Animator(canvas);
        animacion.start();
        
        // Acción por defecto al cerrar la ventana
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Evento Oyente para la barra de menu "Autores"         
        autores.addActionListener (new ActionListener() {
             public void actionPerformed(ActionEvent e) {   
                 JOptionPane.showMessageDialog(null, "Iván Romero\nPedro Sánchez", "Autores",
                                               JOptionPane.INFORMATION_MESSAGE);  
             }
        });
        
        // Evento Oyente para la barra de menu "Malla por Revolución"
        revolucion.addActionListener (new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                escena.setTipo(0);
             }
        });   
        
        // Evento Oyente para la barra de menu "Malla por Extrusión"
        extrusion.addActionListener (new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                escena.setTipo(1);
             }
        });   
        
        // Añadimos un evento para la acción de salida
        addWindowListener(new WindowAdapter() {
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

    // Métood Main
    public static void main(String[] args) {
	Principal p4 = new Principal();
	p4.setVisible(true);
    }
}
