package practica4.util;

import practica4.Modelo.Cara;
import practica4.Modelo.Malla;
import practica4.Modelo.PuntoVector3D;
import practica4.Modelo.VerticeNormal;

public class Tetraedro extends Malla {
    
    public Tetraedro() {
        
        PuntoVector3D v0 = new  PuntoVector3D(0,0,0);
        PuntoVector3D v1 = new  PuntoVector3D(50,0,0);
        PuntoVector3D v2 = new  PuntoVector3D(0,50,0);
        PuntoVector3D v3 = new  PuntoVector3D(0,0,50);
        
        this.vertices.add(v0);
        this.vertices.add(v1);
        this.vertices.add(v2);
        this.vertices.add(v3);
        
        PuntoVector3D n0 = new  PuntoVector3D(0,0,0);
        PuntoVector3D n1 = new  PuntoVector3D(1,0,0);
        PuntoVector3D n2 = new  PuntoVector3D(0,1,0);
        PuntoVector3D n3 = new  PuntoVector3D(0,0,1);
        
        n0 = n0.normaliza();
        n1 = n1.normaliza();
        n2 = n2.normaliza();
        n3 = n3.normaliza();
        
        this.normales.add(n0);
        this.normales.add(n1);
        this.normales.add(n2);
        this.normales.add(n3);
        
    /////    
        
        VerticeNormal vna = new VerticeNormal(1,0);
        VerticeNormal vnb = new VerticeNormal(2,0);
        VerticeNormal vnc = new VerticeNormal(3,0);
        
        Cara c = new Cara();
        
        c.setIndiceVerticeNormal(vna);
        c.setIndiceVerticeNormal(vnb);
        c.setIndiceVerticeNormal(vnc);
        
        this.caras.add(c);
        
        
//////////////////////////////////////////////////////////////////////
        vna = new VerticeNormal(3,1);
        vnb = new VerticeNormal(2,1);
        vnc = new VerticeNormal(0,1);
        
        c = new Cara();
        
        c.setIndiceVerticeNormal(vna);
        c.setIndiceVerticeNormal(vnb);
        c.setIndiceVerticeNormal(vnc);
        
        this.caras.add(c);
//////////////////////////////////////////////////////////////////////
        vna = new VerticeNormal(1,2);
        vnb = new VerticeNormal(3,2);
        vnc = new VerticeNormal(0,2);
        
        c = new Cara();
        
        c.setIndiceVerticeNormal(vna);
        c.setIndiceVerticeNormal(vnb);
        c.setIndiceVerticeNormal(vnc);
        
        this.caras.add(c);
//////////////////////////////////////////////////////////////////////                
                
        vna = new VerticeNormal(2,3);
        vnb = new VerticeNormal(0,3);
        vnc = new VerticeNormal(1,3);
        
        c = new Cara();
        
        c.setIndiceVerticeNormal(vna);
        c.setIndiceVerticeNormal(vnb);
        c.setIndiceVerticeNormal(vnc);
        
        this.caras.add(c);
//////////////////////////////////////////////////////////////////////
                
        
        
    }
    
}
