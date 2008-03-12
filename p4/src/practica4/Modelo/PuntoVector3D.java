package practica4.Modelo;

import java.io.Serializable;
import java.util.ArrayList;


public class PuntoVector3D implements Serializable {
//	---------------------------------------------------------------------------
    
//Atributos
    double x;
    double y;
    double z;
    int PV; // 1 para puntos , 0 para vectores
    
//Constructoras
    public PuntoVector3D() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }
    
    
    public PuntoVector3D(double valorX, double valorY, double valorZ) {
        x = valorX;
        y = valorY;
        z = valorZ;
    }
    
    public PuntoVector3D(double valorX, double valorY, double valorZ, int PV) {
        x = valorX;
        y = valorY;
        z = valorZ;
        this.PV = PV;
    }
    
    public String toString() {
        String aux;
        aux = Double.toString(x);
        aux += ",";
        aux += Double.toString(y);
        aux += ",";
        aux += Double.toString(z);
        return aux;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getZ() {
        return z;
    }
    
    public void setX(double x1) {
        x = x1;
    }
    
    public void setY(double y1) {
        y = y1;
    }
    
    public void setZ(double z1) {
        z = z1;
    }
    
    public void setXYZ(double x1, double y1, double z1) {
        x = x1;
        y = y1;
        z = z1;
    }
    
    public PuntoVector3D clonar() {
        PuntoVector3D aux = new PuntoVector3D(x, y, z);
        return aux;
    }
    
    public void suma(PuntoVector3D PV0) {
        x = x + PV0.getX();
        y = y + PV0.getY();
        z = z + PV0.getZ();
    }
    
    public void resta(PuntoVector3D PV0) {
        x = x - PV0.getX();
        y = y - PV0.getY();
        z = z - PV0.getZ();
    }
    
    public void escala(double k) {
        x = k * x;
        y = k * y;
        z = k * z;
    }
    
    public double prodEsc(PuntoVector3D PV0) {
        return (x * PV0.getX() + y * PV0.getY() + z * PV0.getZ());
    }
    
    public PuntoVector3D normalIzda() {
        return new PuntoVector3D(-y, x, -z); //??
    }
    
    public double modulo() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
    
    /**
     * Devuelve la distancia que separa dos puntos
     * @param otroPunto
     * @return
     */
    public double distancia(PuntoVector3D otroPunto) {
        PuntoVector3D aux = otroPunto.clonar();
        aux.resta(this);
        return aux.modulo();
    }
    
    public PuntoVector3D normaliza() {
        double mod = this.modulo();
        PuntoVector3D retVal = new PuntoVector3D(x / mod, y / mod, z / mod);
        return retVal;
    }
    
    
    public ArrayList<PuntoVector3D> calculaPuntos(PuntoVector3D p, double angRad){
        ArrayList<PuntoVector3D> retVal = new ArrayList<PuntoVector3D>();
        int nlados = (int)(2 * Math.PI / angRad);
        for (int i = 1; i <= nlados; i++) {
            retVal.add(giraPunto(p,i*angRad));
        }
        return retVal;
    }
    
    private PuntoVector3D giraPunto(PuntoVector3D p, double angRad){
        PuntoVector3D retVal = new PuntoVector3D(p.getX()*Math.sin(angRad),0,p.getX()*Math.cos(angRad),1);
        return retVal;
    }
}

