package practica4.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.opengl.*;
import com.sun.opengl.util.*;

import practica4.Controlador.GL3D;
import practica4.Modelo.PuntoVector3D;
import practica4.Modelo.MallaPorRevolucion;
import practica4.Modelo.Malla;
import practica4.util.Calculos;
import java.util.ArrayList;
import practica4.Modelo.Toro;
import practica4.util.Tetraedro;

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
    private Malla mallaActual;
    private JPanel panelBotones;

    // Constructora
    public Principal() {

        // Titulo de la ventana y tamaño
        setTitle("Práctica 4");
        setSize(new Dimension(425, 500));
        super.setResizable(false);

        // Contenedor del editor
        panel = this.getContentPane();
        panel.setBackground(Color.blue);
        panel.setLayout(new BorderLayout());

        // Generamos el menu
        menu = new JMenuBar();
        archivo = new JMenu("Archivo");
        nuevo = new JMenuItem("Nuevo");
        salir = new JMenuItem("Salir");
        malla = new JMenu("Malla por");
        revolucion = new JMenuItem("Revolución");
        extrusion = new JMenuItem("Extrusión");
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
        this.panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panel.add(panelBotones, BorderLayout.SOUTH);

        botonDefinirPuntos = new JButton("Definir Perfil");
        //botonDefinirPuntos.setBounds(0, 400, 100, 25);
        botonDefinirPuntos.setVisible(true);
        panelBotones.add(botonDefinirPuntos);

        botonAplicarSplines = new JButton("BSplines");
        //     botonAplicarSplines.setBounds(100, 400, 100, 25);
        botonAplicarSplines.setVisible(true);
        panelBotones.add(botonAplicarSplines);

        botonGenerarMallaRevolucion = new JButton("Revolución");
        //    botonGenerarMallaRevolucion.setBounds(200, 400, 100, 25);
        botonGenerarMallaRevolucion.setVisible(true);
        panelBotones.add(botonGenerarMallaRevolucion);

        botonGenerarMallaExtrusion = new JButton("Extrusión");
        //  botonGenerarMallaExtrusion.setBounds(300, 400, 100, 25);
        botonGenerarMallaExtrusion.setVisible(true);
        panelBotones.add(botonGenerarMallaExtrusion);

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
        escena = new GL3D(425, 425);
        canvas.addGLEventListener(escena);
        //canvas.setBounds(0, 0, 400, 400);
        canvas.addMouseListener(new ManejadorRaton());
        panel.add(canvas, BorderLayout.CENTER);

        // Transformamos el perfil a coordenadas de la escena
        this.perfil = escena.transformarPerfil(this.perfil);
        escena.setPerfil(this.perfil);

        // Animación de la escena
        animacion = new Animator(canvas);
        animacion.start();

        // Entrada de datos desactivada
        entradaDatos = false;

        // Acción por defecto al cerrar la ventana
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
                JOptionPane.showMessageDialog(null, "Iván Romero\nPedro Sánchez", "Autores",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Evento Oyente para la barra de menu "Malla por Revolución"
        revolucion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tipoMalla = 0;
                escena.actualizarPerfil(tipoMalla, perfil);
            }
        });

        // Evento Oyente para la barra de menu "Malla por Extrusión"
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

        // Evento Oyente para el boton "Malla Revolución"
        botonGenerarMallaRevolucion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                mallaRevolucion = new MallaPorRevolucion(perfil, 0.5f);

                escena.actualizarMallaRev(tipoMalla, mallaRevolucion);
                escena.setGenerado(true);
                entradaDatos = false;
            }
        });

        // Evento Oyente para el boton "Malla Extrusión"
        botonGenerarMallaExtrusion.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                /*              String SnP = JOptionPane.showInputDialog(null, "Número de lados que aproximan la sección del toroide",
                "Datos de Entrada 1/4", JOptionPane.QUESTION_MESSAGE);
                String SnQ = JOptionPane.showInputDialog(null, "Número de capas del toroide",
                "Datos de Entrada 2/4", JOptionPane.QUESTION_MESSAGE);
                String Sr1 = JOptionPane.showInputDialog(null, "Radio del toro",
                "Datos de Entrada 3/4", JOptionPane.QUESTION_MESSAGE);
                String Sr2 = JOptionPane.showInputDialog(null, "Radio de la sección del toro",
                "Datos de Entrada 2/4", JOptionPane.QUESTION_MESSAGE);
                 */

                String SnP = JOptionPane.showInputDialog("Número de lados que aproximan la sección del toroide", "10");
                String SnQ = JOptionPane.showInputDialog("Número de capas del toroide", "50");
                String Sr1 = JOptionPane.showInputDialog(null, "Radio del toro", "150");
                String Sr2 = JOptionPane.showInputDialog(null, "Radio de la sección del toro", "20");

                int nP = Integer.parseInt(SnP);
                int nQ = Integer.parseInt(SnQ);
                float r1 = Float.parseFloat(Sr1);
                float r2 = Float.parseFloat(Sr2);

                mallaActual = new Toro(nP, nQ, r1, r2);
                escena.actualizarMalla(3, mallaActual);
//		Principal.getFrames()[0].setSize(500, 500);

                /*   mallaActual = new Tetraedro();
                escena.actualizarMalla(3, mallaActual);
                 */
                escena.setGenerado(true);
                entradaDatos = false;
            }
        });
        // Añadimos un evento para la acción de salida
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

    // Eventos del Ratón
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

    // Métood Main
    public static void main(String[] args) {
        Principal p4 = new Principal();
        p4.setVisible(true);
    }
}
