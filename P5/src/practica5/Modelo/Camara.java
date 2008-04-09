package practica5.Modelo;

import practica5.util.Conversiones;

public class Camara {
    
    private PuntoVector3D eye, look, up;
    private PuntoVector3D u, v, n;
    private float l, r, b, t, N, F;
    private float[] m= new float[16];
    
    /*eye = new PuntoVector3D(0,0,0);
      look = new PuntoVector3D(0,0,-1);
      up = new PuntoVector3D(0,1,0);
     */
    public Camara() {
        
    }
    
    private void setView(PuntoVector3D eye, PuntoVector3D look, PuntoVector3D up){
        
    }
    
    private void setProjection(){
        
    }
    
    private void setModelViewMatrix(){
      
    }
    
    public void roll(double angulo){
        double cs = Math.cos(Conversiones.g2r(angulo));
        double sn = Math.sin(Conversiones.g2r(angulo));
        PuntoVector3D t = u.clonar();
        u = new PuntoVector3D(cs*t.getX()-sn*v.getX(),cs*t.getY()-sn*v.getY(),cs*t.getZ()-sn*v.getZ());
        v = new PuntoVector3D(sn*t.getX()+cs*v.getX(),sn*t.getY()+cs*v.getY(),sn*t.getZ()+cs*v.getZ());
        setModelViewMatrix();
    }
    
    public void desliza(PuntoVector3D del){
    //******
        
        setModelViewMatrix();
    }
}
