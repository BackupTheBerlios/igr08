package practica5.Vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import com.sun.opengl.util.*;

import practica5.Controlador.GL3D;
import practica5.Modelo.Basic.Camara;
import practica5.Modelo.Basic.PuntoVector3D;
import practica5.Modelo.Basic.MallaPorRevolucion;
import practica5.Modelo.Basic.Malla;
import practica5.util.Calculos;
import java.util.ArrayList;
import practica5.Modelo.Objetos.Toro;

public class Principal extends JFrame implements KeyListener{
    
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
    
    private JPanel panelBotones;
    
    //private int tipoMalla;
    //private boolean entradaDatos;
    //private final Animator animacion;
    //private ArrayList<PuntoVector3D> perfil;
    //private Malla mallaActual;
    
    private Camara camara;
    
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

        // Creamos el canvas de dibujo
        canvas = new GLJPanel();
        escena = new GL3D(800, 600);
        canvas.addGLEventListener(escena);
        canvas.addMouseListener(new ManejadorRaton());
        canvas.addKeyListener(new ManejadorTeclado());
        panel.add(canvas, BorderLayout.CENTER);
        
        // Transformamos el perfil a coordenadas de la escena
        //this.perfil = escena.transformarPerfil(this.perfil);
        //escena.setPerfil(this.perfil);
        
        // Animacion de la escena
        //animacion = new Animator(canvas);
        //animacion.start();
                
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
                
            }
        });
        
        // Evento Oyente para el boton "Dibujar Normales"
        botonDibujarNormales.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                if (botonDibujarNormales.getText().equals("Activa Normales")) {
                    botonDibujarNormales.setText("DesactivaNormales");
                } else {
                    botonDibujarNormales.setText("Activa Normales");
                }
            }
        });
        
        // Añadimos un evento para la accion de salida
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
        public void mousePressed(MouseEvent evento) {}
        public void mouseReleased(MouseEvent evento) {}
        public void mouseClicked(MouseEvent evento) {}
    }
    
    // Eventos con el teclado
    public class ManejadorTeclado extends KeyAdapter { 
        public void keyPressed(KeyEvent evento) {
            if (evento.getKeyCode() == KeyEvent.VK_R) 
                camara.roll(10.0);
            
            if (evento.getKeyCode() == KeyEvent.VK_P) 
                camara.pitch(10.0);
            
            if (evento.getKeyCode() == KeyEvent.VK_Y) 
                camara.yaw(10.0);
            
        }
        
        public void keyTyped(KeyEvent evento) {}
        public void keyReleased(KeyEvent evento) {}
    }    
    
    // Método Main
    public static void main(String[] args) {
        Principal p5 = new Principal();
        p5.setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
