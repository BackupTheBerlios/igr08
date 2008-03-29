package practica4.Modelo;

import java.io.Serializable;
import java.util.ArrayList;


public class PuntoVector3D implements Serializable {
    // Atributos
    double x, y, z;
    int pv;          // PV=1 (punto) / PV=0 (vector)
    
    // Constructora por defecto
    public PuntoVector3D() {}
    
    // Constructora con parámetros 1
    public PuntoVector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pv = 1;
    }

    // Constructora con parámetros 2
    public PuntoVector3D(double x, double y, double z, int pv) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pv = pv;
    }
    
    // Método Clon
    public PuntoVector3D clonar() {
        PuntoVector3D aux = new PuntoVector3D(x, y, z, pv);
        return aux;
    }
    
    // Imprime puntoVector3D
    public String toString() {
        return "[" + String.valueOf(x) + "," + String.valueOf(y) + "," + String.valueOf(z) + "," + String.valueOf(pv) + "]"; 
    }
    
    // Getters & Setters
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void setZ(double z) {
        this.z = z;
    }
    
    public void setXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public int getPV() {
        return pv;
    }
  
    public void setPV(int pv) {
        this.pv = pv;
    }
    
    
    // Operaciones con puntos y vectores
    public void suma(PuntoVector3D PV0) {
        this.x = x + PV0.getX();
        this.y = y + PV0.getY();
        this.z = z + PV0.getZ();
    }
    
    public void resta(PuntoVector3D PV0) {
        this.x = x - PV0.getX();
        this.y = y - PV0.getY();
        this.z = z - PV0.getZ();
    }
    
    public void escala(float k) {
        this.x = k * x;
        this.y = k * y;
        this.z = k * z;
    }
    
    public double prodEsc(PuntoVector3D PV0) {
        return (x * PV0.getX() + y * PV0.getY() + z * PV0.getZ());
    }
    
    public PuntoVector3D normalIzda() {
        return new PuntoVector3D(-y, x, -z); //??
    }
    
    public float modulo() {
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    
    public float distancia(PuntoVector3D otroPunto) {
        PuntoVector3D aux = otroPunto.clonar();
        aux.resta(this);
        return aux.modulo();
    }
    
    public PuntoVector3D normaliza() {
        float mod = this.modulo();
        PuntoVector3D retVal = new PuntoVector3D(x / mod, y / mod, z / mod);
        return retVal;
    }
    
    
    public ArrayList<PuntoVector3D> calculaPuntos(PuntoVector3D p, float angRad){
        ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
        int nlados = getNumVerticesDeRevolucion(angRad);
        for (int i = 1; i <= nlados; i++) {
            retVal.add(giraPunto1(p,i*angRad));
        }
        return retVal;
    }
    
    private PuntoVector3D giraPunto1(PuntoVector3D p, float angRad){
        PuntoVector3D retVal = new PuntoVector3D((float)(p.getX()*Math.sin(angRad)), p.getY(), (float)(p.getX()*Math.cos(angRad)), 1);
        return retVal;
    }
    
    public PuntoVector3D giraPunto(double angRad){
        PuntoVector3D retVal = new PuntoVector3D((double)(this.getX()*Math.cos(angRad)), 
                                                 (double) this.getY(), 
                                                 (double)(this.getX()*Math.sin(angRad)), 1);
        return retVal;
    }
    
    public int getNumVerticesDeRevolucion(double angRad) {
        return (int)Math.floor(2 * Math.PI / angRad);
    }
}

