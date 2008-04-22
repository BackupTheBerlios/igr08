package practica5.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;

import practica5.Controlador.GL3D;
import practica5.Controlador.OyenteRaton;
import practica5.Controlador.OyenteTeclado;
import practica5.Modelo.Basic.Color;
import practica5.Modelo.Basic.Malla;

public class Principal extends JFrame {
    
    public static final Color color1 = new Color(1, 0, 0);
    public static final Color color2 = new Color(0, 1, 0);
    public static final Color color3 = new Color(0, 0, 1);
    public static final Color color4 = new Color(0, 0, 1);
    public static final Color color5 = new Color(1, 1, 0);
    public static final Color color6 = new Color(0, 1, 1);
    public static final Color color7 = new Color(1, 0, 1);
    public static final Color color8 = new Color(1, 1, 1);
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
    private ButtonGroup gBotonesProyeccion;
    private JRadioButton rbOrtogonal;
    private JRadioButton rbPerspectiva;
    private JRadioButton rbOblicua;
    private OyenteTeclado oyenteTeclado;
    private OyenteRaton oyenteRaton;
    
    // Constructora
    public Principal() {
        
        // Titulo de la ventana y tamaño
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
        
        // Creamos el canvas de dibujo
        canvas = new GLJPanel();
        escena = new GL3D(800, 600);
        oyenteTeclado = new OyenteTeclado(escena, canvas);
        oyenteRaton = new OyenteRaton(escena, canvas);
        canvas.setLayout(null);
        canvas.addGLEventListener(escena);
        canvas.addKeyListener(oyenteTeclado);
        canvas.addMouseListener(oyenteRaton);
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
        
        rbLampara = new JRadioButton("Lampara");
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
        
        rbLineas = new JRadioButton("Lineas");
        rbLineas.setForeground(java.awt.Color.pink);
        rbLineas.setBackground(java.awt.Color.black);
        rbLineas.setBounds(10, 40, 120, 25);
        canvas.add(rbLineas);
        
        rbSolido = new JRadioButton("Solido");
        rbSolido.setForeground(java.awt.Color.pink);
        rbSolido.setBackground(java.awt.Color.black);
        rbSolido.setBounds(10, 70, 120, 25);
        canvas.add(rbSolido);
        
        gBotonesModo = new ButtonGroup();
        gBotonesModo.add(rbPuntos);
        gBotonesModo.add(rbLineas);
        gBotonesModo.add(rbSolido);
        
        rbOrtogonal = new JRadioButton("Ortogonal");
        rbOrtogonal.setForeground(java.awt.Color.pink);
        rbOrtogonal.setBackground(java.awt.Color.black);
        rbOrtogonal.setBounds(700, 450, 120, 25);
        canvas.add(rbOrtogonal);
        
        rbPerspectiva = new JRadioButton("Perspectiva");
        rbPerspectiva.setForeground(java.awt.Color.pink);
        rbPerspectiva.setBackground(java.awt.Color.black);
        rbPerspectiva.setBounds(700, 480, 120, 25);
        canvas.add(rbPerspectiva);
        
        rbOblicua = new JRadioButton("Oblicua");
        rbOblicua.setForeground(java.awt.Color.pink);
        rbOblicua.setBackground(java.awt.Color.black);
        rbOblicua.setBounds(700, 510, 120, 25);
        canvas.add(rbOblicua);
        
        gBotonesModo = new ButtonGroup();
        gBotonesModo.add(rbOrtogonal);
        gBotonesModo.add(rbPerspectiva);
        gBotonesModo.add(rbOblicua);
        
        // Ponemos el oyente en los distintos elementos
        canvas.addKeyListener(oyenteTeclado);
        panel.addKeyListener(oyenteTeclado);
        rbEscena.addKeyListener(oyenteTeclado);
        rbMuebles.addKeyListener(oyenteTeclado);
        rbLampara.addKeyListener(oyenteTeclado);
        jcNormales.addKeyListener(oyenteTeclado);
        jcTexturas.addKeyListener(oyenteTeclado);
        rbPuntos.addKeyListener(oyenteTeclado);
        rbLineas.addKeyListener(oyenteTeclado);
        rbSolido.addKeyListener(oyenteTeclado);
        rbOrtogonal.addKeyListener(oyenteTeclado);
        rbPerspectiva.addKeyListener(oyenteTeclado);
        rbOblicua.addKeyListener(oyenteTeclado);
        
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
                JOptionPane.showMessageDialog(panel, "Ivan Romero\nPedro Sanchez", "Autores",
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
        
        rbOrtogonal.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                System.out.println("ortogonal");
                canvas.repaint();
            }
        });
        
        rbPerspectiva.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                System.out.println("Perspectiva");
                canvas.repaint();
            }
        });
        
        rbOblicua.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                System.out.println("Oblicua");
                canvas.repaint();
            }
        });
        
        jcNormales.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                if (jcNormales.isSelected()){
                    escena.getObjeto3D().setNormalesEnabled(true);
                } else {escena.getObjeto3D().setNormalesEnabled(false);}
                canvas.repaint();
            }
        });
        
        // Añadimos un evento para la accion de salida
        addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });
    }
    
    // Metodo Main
    public static void main(String[] args) {
        Principal p5 = new Principal();
        p5.setVisible(true);
    }
}