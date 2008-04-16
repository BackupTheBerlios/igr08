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

public class Principal extends JFrame implements  KeyListener{
    
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
        
        // Titulo de la ventana y tamaño
        setTitle("Práctica 5");
        setSize(new Dimension(800, 600));
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
        panelBotones.setLayout(new GridLayout(1, 2));
        panel.add(panelBotones, BorderLayout.SOUTH);
        
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
                JOptionPane.showMessageDialog(null, "Iván Romero\nPedro Sánchez", "Autores",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // Evento Oyente para el boton "Cambiar Modo"
        botonCambiarModo.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                Object[] opciones = {"Puntos", "Líneas", "Sólidos"};
                int eleccion = JOptionPane.showOptionDialog(null, "Cambiar Modo de Representación a: ", "Datos de Entrada",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
                
                mallaActual.setTipoMalla(eleccion);
            }
        });
        
        // Evento Oyente para el boton "Dibujar Normales"
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
        
        // Añadimos un evento para la accion de salida
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
    
    // Eventos del Raton
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
    
    // MÃ©tood Main
    public static void main(String[] args) {
        Principal p4 = new Principal();
        p4.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        JOptionPane.showMessageDialog(null, "aa");
    }

    public void keyReleased(KeyEvent e) {
    }
}
