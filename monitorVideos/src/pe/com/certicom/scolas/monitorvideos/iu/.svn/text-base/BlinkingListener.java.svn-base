/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitorvideos.iu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import pe.com.certicom.scolas.model.beans.GrillaLlamados;

/**
 *
 * @author Carlos
 */
public class BlinkingListener implements ActionListener {

    private RoundedPanel cajaActiva = null;
    private int tiempoTemporizador = 0;
    private int tiempoBlinking = 0;
    private Timer temporizador = null;
    private boolean resaltado = false;
    private boolean llamadoPorPrecola = false;
    private GrillaLlamados configuracionGrilla = null;

    public BlinkingListener(int tiempoBlinking,boolean llamadoPorPrecola){
        this.tiempoBlinking = tiempoBlinking;
        this.llamadoPorPrecola = llamadoPorPrecola;
    }

    public BlinkingListener(int tiempoBlinking, Timer temporizador){
        this.tiempoBlinking = tiempoBlinking;
        this.temporizador = temporizador;
        setTiempoTemporizador(0);
    }

    public void inicializar(){
        setTiempoTemporizador(0);
    }

    public void actionPerformed(ActionEvent e) {
        setTiempoTemporizador(getTiempoTemporizador() + 1);
        if(getTiempoTemporizador()>=tiempoBlinking*2){
            cajaActiva.setForeground(new Color(configuracionGrilla.getNumColorTextoR(),configuracionGrilla.getNumColorTextoG(),configuracionGrilla.getNumColorTextoB()));
            cajaActiva.setColor(llamadoPorPrecola);
            temporizador.stop();
            setTiempoTemporizador(0);
            temporizador.removeActionListener(this);
            temporizador = null;
            return;
        }
        //Aqui jugamos con la caja durante cierto tiempo...
        if(resaltado){
            //Si ya está resaltado entonces lo reseteamos
            //cajaActiva.setForeground(new Color(configuracionGrilla.getNumColorTextoR(),configuracionGrilla.getNumColorTextoG(),configuracionGrilla.getNumColorTextoB()));
            //cajaActiva.setColor(new Color(configuracionGrilla.getNumColorTextoR(),configuracionGrilla.getNumColorTextoG(),configuracionGrilla.getNumColorTextoB()));
            cajaActiva.setColor(llamadoPorPrecola);
            resaltado = false;
        }else{
            //Si no está resaltado lo resaltamos:
            cajaActiva.setForeground(new Color(configuracionGrilla.getNumColorResaltadoR(),configuracionGrilla.getNumColorResaltadoG(),configuracionGrilla.getNumColorResaltadoB()));
            cajaActiva.setColorResaltado(configuracionGrilla);
            resaltado = true;
        }
    }

    /**
     * @return the cajaActiva
     */
    public RoundedPanel getCajaActiva() {
        return cajaActiva;
    }

    /**
     * @param cajaActiva the cajaActiva to set
     */
    public void setCajaActiva(RoundedPanel cajaActiva) {
        this.cajaActiva = cajaActiva;
    }

    /**
     * @return the tiempoBlinking
     */
    public int getTiempoBlinking() {
        return tiempoBlinking;
    }

    /**
     * @param tiempoBlinking the tiempoBlinking to set
     */
    public void setTiempoBlinking(int tiempoBlinking) {
        this.tiempoBlinking = tiempoBlinking;
    }

    /**
     * @return the temporizador
     */
    public Timer getTemporizador() {
        return temporizador;
    }

    /**
     * @param temporizador the temporizador to set
     */
    public void setTemporizador(Timer temporizador) {
        this.temporizador = temporizador;
    }

    /**
     * @return the configuracionGrilla
     */
    public GrillaLlamados getConfiguracionGrilla() {
        return configuracionGrilla;
    }

    /**
     * @param configuracionGrilla the configuracionGrilla to set
     */
    public void setConfiguracionGrilla(GrillaLlamados configuracionGrilla) {
        this.configuracionGrilla = configuracionGrilla;
    }

    /**
     * @return the tiempoTemporizador
     */
    public int getTiempoTemporizador() {
        return tiempoTemporizador;
    }

    /**
     * @param tiempoTemporizador the tiempoTemporizador to set
     */
    public void setTiempoTemporizador(int tiempoTemporizador) {
        this.tiempoTemporizador = tiempoTemporizador;
    }

}
