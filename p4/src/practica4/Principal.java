package practica4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.opengl.*;
import com.sun.opengl.util.*;

public class Principal extends JFrame {
    
    // Atributos
    private Container panel;
    
    private GLJPanel canvas;
    private GL3D escena;
       
    // Constructora
    public Principal() {
        
        // Titulo de la ventana y tamaño
	setTitle("Práctica 4");
        setSize(new Dimension(400,400));
        super.setResizable(false);
        
        // Contenedor del editor
        panel = this.getContentPane();
        panel.setBackground(Color.black);
        panel.setLayout(null);
        
        
        // Creamos el canvas de dibujo
        canvas = new GLJPanel();
        escena = new GL3D();
        canvas.addGLEventListener(escena);
        canvas.setBounds(0,0,400,400);
        panel.add(canvas);
        
    }
    
    
    
    
    
    
    
    // Métood Main
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
