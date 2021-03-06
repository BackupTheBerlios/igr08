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
import practica5.Modelo.Basic.Objeto3D;

public class Principal extends JFrame {
    
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
    private JCheckBox jcBaldosas;
    private JCheckBox jcTexturas;
    private JCheckBox jcPersianas;
    private ButtonGroup gBotonesProyeccion;
    private JRadioButton rbOrtogonal;
    private JRadioButton rbPerspectiva;
    private JRadioButton rbOblicua;
    private JRadioButton rbPersona;
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
        canvas.setBackground(java.awt.Color.cyan);
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
        rbEscena.setSelected(true);
        canvas.add(rbEscena);
        
        rbMuebles = new JRadioButton("Muebles");
        rbMuebles.setForeground(java.awt.Color.green);
        rbMuebles.setBackground(java.awt.Color.black);
        rbMuebles.setBounds(10, 480, 80, 25);
        canvas.add(rbMuebles);
        
        rbLampara = new JRadioButton("Lampara");
        rbLampara.setForeground(java.awt.Color.green);
        rbLampara.setBackground(java.awt.Color.black);
        rbLampara.setBounds(10, 510, 80, 25);
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
        
        jcBaldosas = new JCheckBox("Baldosas");
        jcBaldosas.setForeground(java.awt.Color.white);
        jcBaldosas.setBackground(java.awt.Color.black);
        jcBaldosas.setBounds(700, 40, 80, 25);
        canvas.add(jcBaldosas);
        
        jcTexturas = new JCheckBox("Texturas");
        jcTexturas.setForeground(java.awt.Color.white);
        jcTexturas.setBackground(java.awt.Color.black);
        jcTexturas.setBounds(700, 70, 80, 25);
        canvas.add(jcTexturas);
        
        jcPersianas= new JCheckBox("Persianas");
        jcPersianas.setForeground(java.awt.Color.white);
        jcPersianas.setBackground(java.awt.Color.black);
        jcPersianas.setBounds(700, 100, 90, 25);
        canvas.add(jcPersianas);
        
        rbPuntos = new JRadioButton("Puntos");
        rbPuntos.setForeground(java.awt.Color.pink);
        rbPuntos.setBackground(java.awt.Color.black);
        rbPuntos.setBounds(10, 10, 70, 25);
        canvas.add(rbPuntos);
        
        rbLineas = new JRadioButton("Lineas");
        rbLineas.setForeground(java.awt.Color.pink);
        rbLineas.setBackground(java.awt.Color.black);
        rbLineas.setBounds(10, 40, 70, 25);
        canvas.add(rbLineas);
        
        rbSolido = new JRadioButton("Solido");
        rbSolido.setForeground(java.awt.Color.pink);
        rbSolido.setBackground(java.awt.Color.black);
        rbSolido.setBounds(10, 70, 60, 25);
        rbSolido.setSelected(true);
        canvas.add(rbSolido);
        
        gBotonesModo = new ButtonGroup();
        gBotonesModo.add(rbPuntos);
        gBotonesModo.add(rbLineas);
        gBotonesModo.add(rbSolido);
        
        rbOrtogonal = new JRadioButton("Ortogonal");
        rbOrtogonal.setForeground(java.awt.Color.yellow);
        rbOrtogonal.setBackground(java.awt.Color.black);
        rbOrtogonal.setBounds(700, 450, 120, 25);
        rbOrtogonal.setSelected(true);
        canvas.add(rbOrtogonal);
        
        rbPerspectiva = new JRadioButton("Perspectiva");
        rbPerspectiva.setForeground(java.awt.Color.yellow);
        rbPerspectiva.setBackground(java.awt.Color.black);
        rbPerspectiva.setBounds(700, 480, 120, 25);
        canvas.add(rbPerspectiva);
        
        rbOblicua = new JRadioButton("Oblicua");
        rbOblicua.setForeground(java.awt.Color.yellow);
        rbOblicua.setBackground(java.awt.Color.black);
        rbOblicua.setBounds(700, 510, 120, 25);
        canvas.add(rbOblicua);
        
        rbPersona = new JRadioButton("1 Persona");
        rbPersona.setForeground(java.awt.Color.yellow);
        rbPersona.setBackground(java.awt.Color.black);
        rbPersona.setBounds(700, 420, 120, 25);
        canvas.add(rbPersona);
        
        gBotonesModo = new ButtonGroup();
        gBotonesModo.add(rbPersona);
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
        jcBaldosas.addKeyListener(oyenteTeclado);
        jcTexturas.addKeyListener(oyenteTeclado);
        jcPersianas.addKeyListener(oyenteTeclado);
        rbPuntos.addKeyListener(oyenteTeclado);
        rbLineas.addKeyListener(oyenteTeclado);
        rbSolido.addKeyListener(oyenteTeclado);
        rbOrtogonal.addKeyListener(oyenteTeclado);
        rbPerspectiva.addKeyListener(oyenteTeclado);
        rbOblicua.addKeyListener(oyenteTeclado);
        rbPersona.addKeyListener(oyenteTeclado);
        
        escena.setVista(GL3D.PROY_ORTOGONAL);
        
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
                //escena.getCamara().setProjection(GL3D.PROY_ORTOGONAL);
                escena.setPerspectiva(GL3D.PROY_ORTOGONAL);
                
                oyenteTeclado.seleccionaObjeto(Objeto3D.ESCENA);
                rbEscena.setSelected(true);
                escena.setVista(GL3D.PROY_ORTOGONAL);
                System.out.println("ortogonal");
                canvas.repaint();
            }
        });
        
        rbPerspectiva.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // escena.getCamara().setProjection(GL3D.PROY_PERSPECTIVA);
                escena.setPerspectiva(GL3D.PROY_PERSPECTIVA);
                
                oyenteTeclado.seleccionaObjeto(Objeto3D.ESCENA);
                rbEscena.setSelected(true);
                escena.setVista(GL3D.VISTA_POR_DEFECTO);
                System.out.println("Perspectiva");
                canvas.repaint();
            }
        });
        
        
        rbOblicua.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                //escena.getCamara().setProjection(GL3D.PROY_OBLICUA);
                escena.setPerspectiva(GL3D.PROY_OBLICUA);
                
                oyenteTeclado.seleccionaObjeto(Objeto3D.ESCENA);
                rbEscena.setSelected(true);
                escena.setVista(GL3D.VISTA_POR_DEFECTO);
                System.out.println("Oblicua");
                canvas.repaint();
            }
        });
        
        rbPersona.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                escena.setPerspectiva(GL3D.CAMARA_PERSONA);
                oyenteTeclado.seleccionaObjeto(Objeto3D.PERSONA);
                
                System.out.println("1 PERSONA");
                canvas.repaint();
            }
        });
        
        jcNormales.addActionListener(new ActionListener() {
     
            public void actionPerformed(ActionEvent e) {
                
                for (int i=0; i<escena.getObjeto3D().getHijos().size(); i++) {
                    if (jcNormales.isSelected())
                        escena.getObjeto3D().getHijos().get(i).setNormalizado();
                    else 
                        escena.getObjeto3D().getHijos().get(i).setNormalizado();
                }
                canvas.repaint();
            }
        });
        
        jcBaldosas.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                if (jcBaldosas.isSelected()){
                    for (int i=0; i<escena.getObjeto3D().getHijos().size(); i++)
                        if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.SUELO)
                            escena.getObjeto3D().getHijos().get(i).setBaldosas();
                } else
                    for (int i=0; i<escena.getObjeto3D().getHijos().size(); i++)
                        if (escena.getObjeto3D().getHijos().get(i).getId() == Objeto3D.SUELO)
                            escena.getObjeto3D().getHijos().get(i).setBaldosas();
                canvas.repaint();
            }
        });
        
        jcTexturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                for (int i=0; i<escena.getObjeto3D().getHijos().size(); i++) {
                    if (jcTexturas.isSelected())
                        escena.getObjeto3D().getHijos().get(i).setTexturizado();
                    else 
                        escena.getObjeto3D().getHijos().get(i).setTexturizado();
                    
                }
                canvas.repaint();
            }
        });
        
        jcPersianas.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                if (jcPersianas.isSelected()) {
                    oyenteTeclado.seleccionaObjeto(Objeto3D.PERSIANA);
                } else {
                    if (rbEscena.isSelected())
                        oyenteTeclado.seleccionaObjeto(Objeto3D.ESCENA);
                    else if (rbMuebles.isSelected())
                        oyenteTeclado.seleccionaObjeto(Objeto3D.MUEBLES);
                    else
                        oyenteTeclado.seleccionaObjeto(Objeto3D.LAMPARA);
                }
                
                canvas.repaint();
            }
        });
        
        rbEscena.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                oyenteTeclado.seleccionaObjeto(Objeto3D.ESCENA);
                canvas.repaint();
            }
        });
        
        rbMuebles.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                oyenteTeclado.seleccionaObjeto(Objeto3D.MUEBLES);
                canvas.repaint();
            }
        });
        
        rbLampara.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                oyenteTeclado.seleccionaObjeto(Objeto3D.LAMPARA);
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