package practica5.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import com.sun.opengl.util.*;

import practica5.Controlador.GL3D;
import practica5.Modelo.PuntoVector3D;
import practica5.Modelo.MallaPorRevolucion;
import practica5.Modelo.Malla;
import practica5.util.Calculos;
import java.util.ArrayList;
import practica5.Modelo.Toro;

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
    private JButton botonDefinirPuntos;
    private JButton botonGenerarMallaRevolucion;
    private JButton botonGenerarMallaExtrusion;
    private JButton botonAplicarSplines;
    private JButton botonCambiarModo;
    private JButton botonDibujarNormales;
    private int tipoMalla;
    private boolean entradaDatos;
    private final Animator animacion;
    private ArrayList<PuntoVector3D> perfil;
    private Malla mallaActual;
    private JPanel panelBotones;
    
    // Constructora
    public Principal() {
        
        // Titulo de la ventana y tama駉
        setTitle("Pr醕tica 5");
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
        panelBotones.setLayout(new GridLayout(3, 3));
        panel.add(panelBotones, BorderLayout.SOUTH);
        
        botonDefinirPuntos = new JButton("Definir Perfil");
        botonDefinirPuntos.setVisible(true);
        panelBotones.add(botonDefinirPuntos);
        
        botonAplicarSplines = new JButton("BSplines");
        botonAplicarSplines.setVisible(true);
        panelBotones.add(botonAplicarSplines);
        
        botonGenerarMallaRevolucion = new JButton("Revoluci髇");
        botonGenerarMallaRevolucion.setVisible(true);
        panelBotones.add(botonGenerarMallaRevolucion);
        
        botonGenerarMallaExtrusion = new JButton("Extrusi髇");
        botonGenerarMallaExtrusion.setVisible(true);
        panelBotones.add(botonGenerarMallaExtrusion);
        
        botonCambiarModo = new JButton("Cambiar Modo");
        botonCambiarModo.setVisible(true);
        panelBotones.add(botonCambiarModo);
        
        botonDibujarNormales = new JButton("Activa Normales");
        botonDibujarNormales.setVisible(true);
        panelBotones.add(botonDibujarNormales);
        
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
        canvas.addMouseListener(new ManejadorRaton());
        panel.add(canvas, BorderLayout.CENTER);
        
        // Transformamos el perfil a coordenadas de la escena
        this.perfil = escena.transformarPerfil(this.perfil);
        escena.setPerfil(this.perfil);
        
        // Animacion de la escena
        animacion = new Animator(canvas);
        animacion.start();
        
        // Entrada de datos desactivada
        entradaDatos = false;
        
        // Accion por defecto al cerrar la ventana
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
                JOptionPane.showMessageDialog(null, "Iv醤 Romero\nPedro S醤chez", "Autores",
                        JOptionPane.INFORMATION_MESSAGE);
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
        
        // Evento Oyente para el boton "Malla Revoluci贸n"
        botonGenerarMallaRevolucion.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                //String datoNumVertices = "4";
                String datoAng = JOptionPane.showInputDialog("羘gulo de rotaci髇: ", "0.15");
                if (datoAng != null) {
                    
                    Object[] opciones = {"3", "4"};
                    int eleccion = JOptionPane.showOptionDialog(null, "N鷐ero de V閞tices de la cara: ", "Datos de Entrada",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
                    
                    int numVerticesCara = Integer.parseInt((String) opciones[eleccion]);
                    double anguloRadianes = Double.parseDouble(datoAng);
                    
                    mallaActual = new MallaPorRevolucion(perfil, numVerticesCara, anguloRadianes);
                    //  mallaRevolucion = new MallaPorRevolucion(perfil, numVerticesCara, anguloRadianes);
                    
                    //  escena.actualizarMallaRev(2, mallaRevolucion);
                    escena.actualizarMalla(2, mallaActual);
                    escena.setGenerado(true);
                    entradaDatos = false;
                }
            }
        });
        
        // Evento Oyente para el boton "Malla Extrusi贸n"
        botonGenerarMallaExtrusion.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                String SnP = JOptionPane.showInputDialog("N鷐ero de lados que aproximan la secci髇 del toroide", "10");
                String SnQ = JOptionPane.showInputDialog("N鷐ero de capas del toroide", "50");
                String Sr1 = JOptionPane.showInputDialog("Radio del toro", "150");
                String Sr2 = JOptionPane.showInputDialog("Radio de la secci贸n del toro", "20");
                
                int nP = Integer.parseInt(SnP);
                int nQ = Integer.parseInt(SnQ);
                float r1 = Float.parseFloat(Sr1);
                float r2 = Float.parseFloat(Sr2);
                
                mallaActual = new Toro(nP, nQ, r1, r2);
                escena.actualizarMalla(3, mallaActual);
                
                escena.setGenerado(true);
                entradaDatos = false;
            }
        });
        
        // Evento Oyente para el bot贸n "Cambiar Modo"
        botonCambiarModo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                Object[] opciones = {"Puntos", "L韓eas", "S髄idos"};
                int eleccion = JOptionPane.showOptionDialog(null, "Cambiar Modo de Representaci髇 a: ", "Datos de Entrada",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
                
                mallaActual.setTipoMalla(eleccion);
                
            }
        });
        
        
        // Evento Oyente para el bot贸n "Dibujar Normales"
        botonDibujarNormales.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                if (botonDibujarNormales.getText().equals("Activa Normales")) {
                    botonDibujarNormales.setText("DesactivaNormales");
                    mallaActual.setNormales(true);
                } else {
                    botonDibujarNormales.setText("Activa Normales");
                    mallaActual.setNormales(false);
                }
                
                
                
            }
        });
        
        // A帽adimos un evento para la acci贸n de salida
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
    
    // Eventos del Rat贸n
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
    
    // M茅tood Main
    public static void main(String[] args) {
        Principal p4 = new Principal();
        p4.setVisible(true);
    }
}
