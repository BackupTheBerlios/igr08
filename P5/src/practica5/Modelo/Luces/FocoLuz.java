/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica5.Modelo.Luces;

import javax.media.opengl.GL;
import practica5.Modelo.Basic.Objeto3D;
import practica5.Modelo.Basic.PuntoVector3D;

public class FocoLuz extends Objeto3D{
    
 private PuntoVector3D posicion;
 private PuntoVector3D angulo;
 private float alfa;
 private int estado;

 ////////////////////////
public FocoLuz() {
    posicion = new PuntoVector3D(0,0,0,1);
    angulo = new PuntoVector3D(0,0,0,0);
    alfa = 45;
    //Por defecto encendida
    estado = 1;
}

public void posicionar(PuntoVector3D puntoLuz, PuntoVector3D anguloNuevo, GL gl){
    posicion.setX(puntoLuz.getX());
    posicion.setY(puntoLuz.getY());
    posicion.setZ(puntoLuz.getZ());
    angulo.setX(anguloNuevo.getX());
    angulo.setY(anguloNuevo.getY());
    angulo.setZ(anguloNuevo.getZ());
    float posicionLuz1[]={(float)puntoLuz.getX(),(float)puntoLuz.getY(),(float)puntoLuz.getZ(),1.0f};
    gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, posicionLuz1, 0);
    gl.glLightf(GL.GL_LIGHT1, GL.GL_SPOT_CUTOFF,alfa);
    float anguloLuz1[]={(float)anguloNuevo.getX(),
    (float)anguloNuevo.getY(),
    (float)anguloNuevo.getZ(),0.0f};
    
    gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPOT_DIRECTION,  anguloLuz1, 0);
}

public void refrescaPosicion(GL gl){
    gl.glMatrixMode(GL.GL_MODELVIEW);
    gl.glPushMatrix();
        gl.glMultMatrixd(this.getMatriz().matriz, 0);

        float posicionLuz1[]={(float)posicion.getX(),(float)posicion.getY(),(float)posicion.getZ(),1.0f};
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, posicionLuz1, 0);
        gl.glLightf(GL.GL_LIGHT1,GL. GL_SPOT_CUTOFF, alfa);
        float anguloLuz1[]={(float)angulo.getX(),(float)angulo.getY(),(float)angulo.getZ(),0.0f};

        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPOT_DIRECTION,  anguloLuz1 ,0);
    gl.glPopMatrix();
}

public void dibuja(GL gl){


    if (estado == 1){
	
	float LuzAmbiente[] = {0.4f, 0.4f, 0.4f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_AMBIENT,LuzAmbiente, 0);
        float LuzDifusa[]={0.5f, 0.5f, 0.5f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_DIFFUSE,LuzDifusa, 0);
    } else {
        float LuzAmbiente[]={0.0f, 0.0f, 0.0f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_AMBIENT,LuzAmbiente, 0);
        float LuzDifusa[]={0.0f, 0.0f, 0.0f, 1.0f};
        gl.glLightfv(GL.GL_LIGHT1,GL.GL_DIFFUSE,LuzDifusa, 0);

    }
        gl.glMultMatrixd(this.getMatriz().getMatriz(), 0);
        float posicionLuz1[]={(float)posicion.getX(),(float)posicion.getY(),(float)posicion.getZ(),1.0f};
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, posicionLuz1, 0);
        float anguloLuz1[]={(float)angulo.getX(),(float)angulo.getY(),(float)angulo.getZ(),0.0f};
        gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPOT_DIRECTION,  anguloLuz1, 0) ;
        gl.glLightf(GL.GL_LIGHT1, GL.GL_SPOT_CUTOFF,alfa);
}

public void darLuzDifusa(){
    float LuzDifusa[]={0.5f,0.5f,0.5f,1.0f};
    gl.glLightfv(GL.GL_LIGHT1,GL.GL_DIFFUSE,LuzDifusa , 0);
}

public void darLuzAmbiente(){
    float LuzAmbiente[]={0.4f,0.4f,0.4f,1.0f};
    gl.glLightfv(GL.GL_LIGHT1,GL.GL_AMBIENT,LuzAmbiente, 0);
}

public void darIntensidad(float t) {
    gl.glLightf(GL.GL_LIGHT1,GL.GL_SPOT_EXPONENT,t);
}

public void cambiaEstado(){
    if (estado == 1) {
        estado = 0;
    } else {
        estado = 1;
    }
}

public void setAlfa(float alfa){
    this.alfa = alfa;
}

public float getAlfa(){
 return alfa;
}    
    

}
