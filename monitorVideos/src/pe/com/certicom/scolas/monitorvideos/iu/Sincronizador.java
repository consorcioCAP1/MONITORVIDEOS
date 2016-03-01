/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.certicom.scolas.monitorvideos.iu;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Sincronizador implements Runnable{

    private Date fechaReinicio;
    private VideoPlayer videoPlayer;

    public void run() {
        while(true){
            try {
                Thread.sleep(1 * 20);
                if(getFechaReinicio().before(new Date())){
                    //Reinicio los videos y termino la ejecución del hilo
                    videoPlayer.reiniciar();
                    break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Sincronizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the fechaReinicio
     */
    public Date getFechaReinicio() {
        return fechaReinicio;
    }

    /**
     * @param fechaReinicio the fechaReinicio to set
     */
    public void setFechaReinicio(Date fechaReinicio) {
        this.fechaReinicio = fechaReinicio;
    }

    /**
     * @return the videoPlayer
     */
    public VideoPlayer getVideoPlayer() {
        return videoPlayer;
    }

    /**
     * @param videoPlayer the videoPlayer to set
     */
    public void setVideoPlayer(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

}
